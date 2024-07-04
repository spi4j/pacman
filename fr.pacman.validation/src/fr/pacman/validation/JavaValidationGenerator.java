package fr.pacman.validation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.validation.config.ConfigurationManager_Itf;
import fr.pacman.validation.config.XmlConfigurationManager;
import fr.pacman.validation.services.DslValidationRuleDefinitionService;
import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRule_Itf;
import fr.pacman.validation.services.Dsl_Enum;
import fr.pacman.validation.services.ValidationService;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Lanceur de la validation (pour chargement du modèle).
 * 
 * @author MINARM
 */
public class JavaValidationGenerator extends PacmanGenerator_Abs
{

   private static final ConfigurationManager_Itf c_config = new XmlConfigurationManager();

   private Dsl_Enum[] _dslsToValidate = Dsl_Enum.values();

   private DslValidationRule_Itf[] _rulesToValidate = null;

   private String _configurationFileDirectoryPath;

   /**
    * This allows clients to instantiates a generator with all required
    * information.
    * 
    * @param p_uri
    *           We'll iterate over the content of this element to find Objects
    *           matching the first parameter of the template we need to call.
    * @throws IOException
    *            This can be thrown in two scenarios : the module cannot be
    *            found, or it cannot be loaded.
    */
   public JavaValidationGenerator (final URI p_uri) throws IOException
   {
      super(p_uri, null, Collections.emptyList());
   }

   /**
    * This allows clients to instantiates a generator with all required
    * information.
    * 
    * @param p_model
    *           We'll iterate over the content of this element to find Objects
    *           matching the first parameter of the template we need to call.
    * @throws IOException
    *            This can be thrown in two scenarios : the module cannot be
    *            found, or it cannot be loaded.
    */
   public JavaValidationGenerator (final EObject p_model) throws IOException
   {
      super(p_model, null, Collections.emptyList());
   }

   /**
    * @param p_Uri
    *           uri du model
    * @throws IOException
    *            RAS
    */
   public void setModelURI (final String p_Uri) throws IOException
   {
      initialize(URI.createFileURI(p_Uri), new File("gen/"), new ArrayList<String>());
   }

   @Override
   protected String getModuleFileName ()
   {
      return "empty";
   }

   @Override
   protected String[] getModuleTemplates ()
   {
      return null;
   }

   @Override
   public String getProjectName ()
   {
      return "fr.pacman.validation";
   }

   @Override
   protected boolean getSwitchQueryCache ()
   {
      return Boolean.TRUE;
   }

   @Override
   public void doGenerate (final Monitor p_monitor) throws IOException
   {
      final EObject v_root = model;

      // Lecture des règles à vérifier
      final String v_configFileName = ProjectProperties.getValidationConfigFile();

      final DslValidationRuleDefinitionService v_validationService;

      // Aucun fichier de configuration défini : on utilise les propriétés par
      // défaut
      if (v_configFileName == null)
      {
         v_validationService = new DslValidationRuleDefinitionService();
      }
      else
      {
         // Lecture du fichier de configuration existant
         if (_configurationFileDirectoryPath == null)
         {
            // _configurationFileDirectoryPath =
            // getModelDirectory().getAbsolutePath();
         }
         final File v_configFile = new File(_configurationFileDirectoryPath, v_configFileName);
         List<? extends DslValidationRule_Itf> v_rules;
         try
         {
            v_rules = c_config.createRulesFromFile(v_configFile);

            // Ecriture du nouveau fichier de configuration (pour intégration
            // des nouvelles règles)
            c_config.writeRulesInFile(v_rules, v_configFile);
         }
         catch (final IOException v_e)
         {
            final String v_initOnError = ProjectProperties.getValidationInitOnError();
            v_rules = Arrays.asList(DslValidationRuleReferentiel_Enum.values());
            if (!v_configFile.exists() || (v_initOnError != null && Boolean.parseBoolean(v_initOnError)))
            {
               // System.out.println("Initialisation du fichier de
               // configuration...");
               c_config.writeRulesInFile(v_rules, v_configFile);
            }
            else
            {
               throw v_e;
            }
         }

         // Instanciation du service de validation avec les règles lues
         v_validationService = new DslValidationRuleDefinitionService(v_rules);
      }
      // Ouvrir le service
      v_validationService.openService();

      final List<DslValidationRuleNokBean> v_lstExigencesNok = new ArrayList<DslValidationRuleNokBean>();
      // Executer les méthodes d'implémentation des exigences Dsm
      for (final Dsl_Enum v_dsl : _dslsToValidate)
      {
         v_lstExigencesNok.addAll(v_validationService.runRulesOfDsl(v_dsl, v_root, _rulesToValidate));
      }
      for (final DslValidationRuleNokBean v_ruleNokBean : v_lstExigencesNok)
      {
         ValidationService.addProblem(v_ruleNokBean);
      }
   }

   /**
    * @param p_path
    *           le chemin du fichier de configuration de la validation
    */
   public void setConfigurationFileDirectoryPath (final String p_path)
   {
      _configurationFileDirectoryPath = p_path;
   }

   /**
    * Filtrer la validation sur un ou plusieurs DSL.
    * 
    * @param p_dsls
    *           les DSL à valider
    */
   public void filterDsl (final Dsl_Enum... p_dsls)
   {
      _dslsToValidate = p_dsls;
   }

   /**
    * Filtrer la validation sur une ou plusieurs règles.
    * 
    * @param p_rules
    *           les règles à vérifier
    */
   public void filterRule (final DslValidationRule_Itf... p_rules)
   {
      _rulesToValidate = p_rules;
   }

}
