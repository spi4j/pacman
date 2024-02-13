package fr.pacman.validation.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.obeonetwork.dsl.environment.impl.TypeImpl;
import org.obeonetwork.dsl.requirement.Repository;

import fr.pacman.validation.services.rules.DslValidationRuleNokBean;
import fr.pacman.validation.services.rules.ValidationLevel_Enum;

/**
 * Tests unitaires sur 'DslValidationRuleDefinitionService'.
 */
public class DslValidationRuleDefinitionService_Test
{
   static final String c_valeurBidonPourLeTest = "Une valeur bidon pour le test";

   /**
    * Obtenir la méthode d'implémentation de la validation d'exigence.
    */
   @Test
   public void findMethodOfRequirementDsl ()
   {
      try
      {
         // Instancier le service de definition des regles avec un referentiel
         // de tests
         final DslValidationRuleDefinitionService v_RequirementDslDefinitionService = new DslValidationRuleDefinitionService(Arrays.asList(RequirementDslReferentiel4Tests_Enum.values()));
         // Ouvrir le service
         v_RequirementDslDefinitionService.openService();

         // Trouver l'instance de Method
         final List<Method> v_Methods = v_RequirementDslDefinitionService.findMethodsForRule(RequirementDslReferentiel4Tests_Enum.DslRequirement_Check002);

         // Fermer le service
         v_RequirementDslDefinitionService.closeService();

         // Verifier le nom de la méthode obtenue
         assertTrue(v_Methods.get(0).getName().equals("checkIdentOblig_sce"), "La méthode trouvée est incorrecte");
      }
      catch (final Exception v_e)
      {
         throw new IllegalArgumentException("Problème lors du test : ", v_e);
      }
   }

   /**
    * Exécuter la méthode d'implémentation de la validation d'exigence.
    */
   @Test
   public void executeMethodOfRequirementDsl ()
   {
      try
      {
         // Instancier le service de definition des regles avec un referentiel
         // de tests
         final DslValidationRuleDefinitionService v_RequirementDslDefinitionService = new DslValidationRuleDefinitionService(Arrays.asList(RequirementDslReferentiel4Tests_Enum.values()));
         // Ouvrir le service
         v_RequirementDslDefinitionService.openService();

         // Trouver l'instance de Method
         final List<Method> v_Methods = v_RequirementDslDefinitionService.findMethodsForRule(RequirementDslReferentiel4Tests_Enum.DslSoaDto_Check200);

         // Obtenir une instance de la classe d'implémentation de l'exigence DSM
         final DslValidationRuleService_Itf v_instanceImplem = RequirementDslReferentiel4Tests_Enum.DslSoaDto_Check200.get_ClassImplem().newInstance();
         // Invoquer la méthode
         // Utilisation d'un EObject bidon
         final Object v_entryPointDsl = new TypeImpl()
         {
         };
         // Obtenir le paramètre de l'exigence
         final Object[] v_paramRequirement = RequirementDslReferentiel4Tests_Enum.DslSoaDto_Check200.get_tab_paramRule();
         v_Methods.get(0).invoke(v_instanceImplem, v_entryPointDsl, v_paramRequirement);
         // Fermer le service
         v_RequirementDslDefinitionService.closeService();

         // Verifier que la valeur à été affectée dans la méthode
         assertTrue(((MonParam) v_paramRequirement[0]).get_valeur().equals(c_valeurBidonPourLeTest), "La valeur obtenue lors de l'appel de la méthode d'implémentation de l'exigence est incorrecte");
      }
      catch (final Exception v_e)
      {
         throw new IllegalArgumentException("Problème lors du test : ", v_e);
      }
   }

   /**
    * Tester le changement d'etat d'une regle (activee --> desactivee).
    */
   @Test
   public void setActivateRequirement ()
   {
      boolean v_errorGenerated = false;
      
      try
      {
         // Instancier le service de definition des regles avec un referentiel
         // de tests
         final DslValidationRuleDefinitionService v_RequirementDslDefinitionService = new DslValidationRuleDefinitionService(Arrays.asList(RequirementDslReferentiel4Tests_Enum.values()));
         // Ouvrir le service
         v_RequirementDslDefinitionService.openService();

         // Forcer une regle e l'etat 'desactivee'
         v_RequirementDslDefinitionService.setActivateRule("pasId", false);
      }
      catch (final IllegalArgumentException v_err)
      {
         // Si le message d'erreur est correct
         assertTrue(v_err.getMessage().startsWith("Changement d'etat de la regle impossible : identifiant \"") == true, "Le libelle du message d'erreur est incorrect");
         v_errorGenerated = true;
      }
      
      // Cette assertion permet d'utiliser Junit 5 avec Java 7 (pas de lambda).
      assertTrue(v_errorGenerated == true, "Une erreur IllegalArgumentException aurait due être générée.");
   }

   /**
    * Exécuter toutes les méthodes d'implémentation de la validation des
    * exigences d'un Dsl.
    */
   @Test
   public void runRequirementOfDsl ()
   {
      // Instancier le service de definition des regles avec un referentiel de
      // tests
      final DslValidationRuleDefinitionService v_RequirementDslDefinitionService = new DslValidationRuleDefinitionService(Arrays.asList(RequirementDslReferentiel4Tests_Enum.values()));
      // Ouvrir le service
      v_RequirementDslDefinitionService.openService();

      // Utilisation d'un EObject bidon
      final EObject v_entryPointDsl = new TypeImpl()
      {
      };
      // Executer les méthodes d'implémentation des exigences Dsl
      final List<DslValidationRuleNokBean> v_lstExigencesNok = v_RequirementDslDefinitionService.runRulesOfDsl(Dsl_Enum.DslRequirement, v_entryPointDsl);

      // Fermer le service
      v_RequirementDslDefinitionService.closeService();

      // Vérifier
      assertTrue(v_lstExigencesNok.isEmpty(), "Il ne devrait pas y avoir d'exigence non satisfaite");
      // assertTrue(
      // "Il devrait y avoir un point d'entrée mémorisé sur l'exigence de
      // modélisation non satisfaite",
      // v_lstExigencesNok.get(0).get_entryPointDsl() != null);
      // System.out.println(v_lstExigencesNok.toString());
   }

}

/**
 * Définition des constantes pour les paramètres supplémentaires des méthodes
 * d'implémentation. Rappel : la signature d'une méthode d'implémentation est la
 * suivante "EObject p_entryPointDsl, Object[] p_paramsRequirement", par exemple
 * : <code>
 * -@DslValidationRule(DslValidationRuleReferentiel_Enum.DslRequirement_Check001) public void checkIdentUnique_sce (EObject p_entryPointDsl, Object[] p_paramsRequirement) { ... }
 * </code>
 */
final class ParamRequirement4Tests
{
   /**
    * Le nombre maximum de caractères pour le champs 'Name' d'une Requirement
    */
   public static final Integer c_nbCaracMax4NameRequirement = 100;

   /**
    * Constructeur privé.
    */
   private ParamRequirement4Tests ()
   {
      super();
   }
}

/**
 * Enumeration des regles du referentiel.
 */
enum RequirementDslReferentiel4Tests_Enum implements DslValidationRule_Itf
{
   /** DSM Requirement */
   // L'identifiant de l'exigence doit etre unique
   DslRequirement_Check001(Dsl_Enum.DslRequirement + "_Check001", "L'identifiant de l'exigence doit etre unique", null, DslRequirement4Tests.class, Dsl_Enum.DslRequirement, ValidationLevel_Enum.Error,
         true),
   // L'identifiant de l'exigence est obligatoire
   DslRequirement_Check002(Dsl_Enum.DslRequirement + "_Check002", "L'identifiant de l'exigence est obligatoire", null, DslRequirement4Tests.class, Dsl_Enum.DslRequirement, ValidationLevel_Enum.Error,
         true),
   // La description de l'exigence est obligatoire
   DslRequirement_Check003(Dsl_Enum.DslRequirement + "_Check003", "La description de l'exigence est obligatoire", null, DslRequirement4Tests.class, Dsl_Enum.DslRequirement, ValidationLevel_Enum.Error,
         false),
   // Le champs 'name' ne doit pas dépassé 'c_nbCaracMax4NameRequirement'
   // caractères
   DslRequirement_Check004(Dsl_Enum.DslRequirement + "_Check004", "Le champs 'Name' de la Requirement ne doit pas dépassé " + ParamRequirement4Tests.c_nbCaracMax4NameRequirement + " caractères",
         new Object[]
         {ParamRequirement4Tests.c_nbCaracMax4NameRequirement }, DslRequirement4Tests.class, Dsl_Enum.DslRequirement, ValidationLevel_Enum.Error, true),

   /** DSM Entity */
   // Une seule relation navigable dans la definition de 2 relations opposees
   DslEntity_Check001(Dsl_Enum.DslEntity + "_Check001", "Une seule relation navigable dans la definition de 2 relations opposees", null, null, Dsl_Enum.DslEntity, ValidationLevel_Enum.Error, true),

   /** DSM SOA - DTO */
   // Une seule relation navigable dans la definition de 2 relations opposees
   DslSoaDto_Check001(Dsl_Enum.DslSoaDto + "_Check001", "Une seule relation navigable dans la definition de 2 relations opposees", null, DslSoaDto4Tests.class, Dsl_Enum.DslSoaDto,
         ValidationLevel_Enum.Error, true),
   // Il ne faut pas dépasser le nombre de propriétés max
   DslSoaDto_Check002(Dsl_Enum.DslSoaDto + "_Check002", "Il ne faut pas dépasser le nombre de propriétés max", new Object[]
   {"20" }, DslSoaDto4Tests.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Error, true),

   // Pas de caracteres speciaux dans les noms
   DslSoaDto_Check100(Dsl_Enum.DslSoaDto + "_Check100", "Pas de caracteres speciaux dans les noms", null, DslSoaDto4Tests.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Information, false),

   // Règle pour vérifier l'execution de la méthode d'implémentation
   DslSoaDto_Check200(Dsl_Enum.DslSoaDto + "_Check200", "Règle pour vérifier l'execution de la méthode d'implémentation", new Object[]
   {new MonParam() }, DslSoaDto4Tests.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Error, true);

   /** Identifiant de la regle */
   private String _id;

   /** Le libelle de la regle */
   private String _libRequirement;

   /** Un paramètre éventuel de l'exigence (ex : nb max attributs) */
   private Object[] _paramRequirements;

   /** Un parametre e la regle (optionnel) */
   private Class<? extends DslValidationRuleService_Itf> _ClassImplem;

   /** Le DSM sur lequel la regle s'applique */
   private Dsl_Enum _RequirementDsl_Enum;

   /** Niveau de la regle (error, warning, info) */
   private ValidationLevel_Enum _RequirementLevel;

   /** Regle activee (ou pas) */
   private boolean _activateRequirement;

   /**
    * Constructeur interne de l'enumeration.
    * 
    * @param p_id
    *           L'identifiant de la regle.
    * @param p_libRequirement
    *           Le libelle de la regle.
    * @param p_paramRequirements
    *           Un paramètre éventuel de l'exigence (ex : nb max attributs).
    * @param p_ClassImplem
    *           Le parametre optionnel de la regle.
    * @param p_RequirementDsl_Enum
    *           Le DSM sur lequel la regle s'applique.
    * @param p_RequirementLevel
    *           Le niveau de la regle (erreur, avertissement ou info).
    * @param p_activateRequirement
    *           'true' si la regle est activee.
    */
   private RequirementDslReferentiel4Tests_Enum (final String p_id, final String p_libRequirement, final Object[] p_paramRequirements,
         final Class<? extends DslValidationRuleService_Itf> p_ClassImplem, final Dsl_Enum p_RequirementDsl_Enum, final ValidationLevel_Enum p_RequirementLevel, final boolean p_activateRequirement)
   {
      _id = p_id;
      _libRequirement = p_libRequirement;
      _ClassImplem = p_ClassImplem;
      _paramRequirements = p_paramRequirements;
      _RequirementDsl_Enum = p_RequirementDsl_Enum;
      _RequirementLevel = p_RequirementLevel;
      _activateRequirement = p_activateRequirement;
   }

   @Override
   public String get_id ()
   {
      return _id;
   }

   @Override
   public void set_id (final String p_id)
   {
      this._id = p_id;
   }

   @Override
   public String get_libRule ()
   {
      return _libRequirement;
   }

   @Override
   public void set_libRule (final String p_libRequirement)
   {
      this._libRequirement = p_libRequirement;
   }

   @Override
   public Object[] get_tab_paramRule ()
   {
      return _paramRequirements;
   }

   @Override
   public void set_tab_paramRule (final Object[] p_paramRequirements)
   {
      _paramRequirements = p_paramRequirements;
   }

   @Override
   public Class<? extends DslValidationRuleService_Itf> get_ClassImplem ()
   {
      return _ClassImplem;
   }

   @Override
   public void set_ClassImplem (final Class<? extends DslValidationRuleService_Itf> p_ClassImplem)
   {
      this._ClassImplem = p_ClassImplem;
   }

   @Override
   public ValidationLevel_Enum get_RuleLevel ()
   {
      return _RequirementLevel;
   }

   @Override
   public void set_RuleLevel (final ValidationLevel_Enum p_RequirementLevel)
   {
      this._RequirementLevel = p_RequirementLevel;
   }

   @Override
   public Dsl_Enum get_RuleDsl_Enum ()
   {
      return _RequirementDsl_Enum;
   }

   @Override
   public void set_RuleDsl_Enum (final Dsl_Enum p_RequirementDsl_Enum)
   {
      this._RequirementDsl_Enum = p_RequirementDsl_Enum;
   }

   @Override
   public boolean is_activateRule ()
   {
      return _activateRequirement;
   }

   @Override
   public void set_activateRule (final boolean p_activateRequirement)
   {
      this._activateRequirement = p_activateRequirement;
   }

   @Override
   public String toString ()
   {
      return super.toString() + "\n_id = " + _id + "\n_libRequirement = " + _libRequirement + "\n_paramRequirements = " + _paramRequirements + "\n_ClassImplem = " + _ClassImplem
            + "\n_RequirementLevel = " + _RequirementLevel + "\n_RequirementDsl_Enum = " + _RequirementDsl_Enum + "\n_activateRequirement = " + _activateRequirement;
   }
} // FIN de l'enumeration 'RequirementDslReferentiel4Tests_Enum'

/**
 * Implémentation des règles de validation du DSM SOA-DTO pour les tests.
 */
class DslSoaDto4Tests implements DslValidationRuleService_Itf
{
   /**
    * Vérification de l'exigence.
    * 
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @RequirementDsl4Tests(RequirementDslReferentiel4Tests_Enum.DslSoaDto_Check001)
   public List<DslValidationRuleNokBean> checkOnlyOneRelationNavigable_sce (final Object p_entryPointDsl, final Object p_paramRequirement)
   {
      // Retour OK
      return Collections.emptyList();
   }

   /**
    * Vérification de l'exigence.
    * 
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @RequirementDsl4Tests(RequirementDslReferentiel4Tests_Enum.DslSoaDto_Check002)
   public List<DslValidationRuleNokBean> checkMaxOfPropertiesInDto_sce (final Object p_entryPointDsl, final Object p_paramRequirement)
   {
      // System.out.println("Méthode non implémentée "
      // + "\np_entryPointDsl="
      // + p_entryPointDsl
      // + "\np_paramRequirement = "
      // + p_paramRequirement);
      // TODO Méthode non implémentée
      return Collections.emptyList();
   }

   /**
    * Vérification de l'exigence.
    * 
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirements
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @RequirementDsl4Tests(RequirementDslReferentiel4Tests_Enum.DslSoaDto_Check200)
   public List<DslValidationRuleNokBean> checkPourVerifierAppelMethodImplem_sce (final Object p_entryPointDsl, final Object[] p_paramRequirements)
   {
      final MonParam v_MonParam = (MonParam) p_paramRequirements[0];
      v_MonParam.set_valeur(DslValidationRuleDefinitionService_Test.c_valeurBidonPourLeTest);

      // Retour OK
      return Collections.emptyList();
   }
} // FIN classe 'DslSoaDto4Tests'

/**
 * Implémentation des règles de validation du DSM Requirement pour les tests.
 */
class DslRequirement4Tests implements DslValidationRuleService_Itf
{
   /**
    * Vérification de l'exigence.
    * 
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @RequirementDsl4Tests(RequirementDslReferentiel4Tests_Enum.DslRequirement_Check001)
   public List<DslValidationRuleNokBean> checkIdentUnique_sce (final Repository p_entryPointDsl, final Object p_paramRequirement)
   {
      // Retour OK
      return Collections.emptyList();
   }

   /**
    * Vérification de l'exigence.
    * 
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @RequirementDsl4Tests(RequirementDslReferentiel4Tests_Enum.DslRequirement_Check002)
   public List<DslValidationRuleNokBean> checkIdentOblig_sce (final Repository p_entryPointDsl, final Object[] p_paramRequirement)
   {
      // Simuler une exigence NOK
      return Arrays.asList(new DslValidationRuleNokBean(RequirementDslReferentiel4Tests_Enum.DslRequirement_Check002, p_entryPointDsl));
   }

   /**
    * Vérification de l'exigence.
    * 
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @RequirementDsl4Tests(RequirementDslReferentiel4Tests_Enum.DslRequirement_Check004)
   public List<DslValidationRuleNokBean> checkFikledNameOblig_sce (final Repository p_entryPointDsl, final Object p_paramRequirement)
   {
      // Retour OK
      return Collections.emptyList();
   }

} // FIN classe DslRequirement4Tests

/**
 * Un paramètre supplémentaire pour une exigence de modélisation (ex : max de
 * qqchose).
 */
class MonParam
{
   /** La valeur */
   private String _valeur;

   /**
    * Obtenir la valeur du paramètre.
    * 
    * @return La valeur.
    */
   public String get_valeur ()
   {
      return _valeur;
   }

   /**
    * Affecter la valeur.
    * 
    * @param p_valeur
    *           La valeur.
    */
   public void set_valeur (final String p_valeur)
   {
      this._valeur = p_valeur;
   }

}

/**
 * L'annotation pour une méthode de vérification d'exigences pour les tests.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface RequirementDsl4Tests
{
   /**
    * L'exigence du DSM.
    */
   RequirementDslReferentiel4Tests_Enum value();
}
