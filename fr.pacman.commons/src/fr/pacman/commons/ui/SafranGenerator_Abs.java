/*******************************************************************************
 * Copyright (c) 2008, 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.commons.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.engine.utils.AcceleoLaunchingUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import fr.pacman.commons.Activator;
import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.errorgen.ErrorGeneration;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.services.ImportsUtils;
import fr.pacman.commons.services.PlugInUtils;
import fr.pacman.commons.services.StringUtils;

/**
 * Main entry point of a generation module.
 * @param <Type>
 *           le type d'element attendu en entree
 * 
 * @TODO : modifications a effectuer. Attention aux valeurs nulles on simplifie pour l'instant mais voir si la propriete n'est pas remontee.
 */
public abstract class SafranGenerator_Abs<Type>
{

   /**
    * The input of generation (IFile or EObject).
    */
   protected final Type _input;

   /**
    * The other arguments.
    */
   private final List<? extends Object> _arguments;

   /**
    * Constructor.
    * 
    * @param p_input
    *           The input of generation (URI or EObject)
    * @param p_arguments
    *           are the other arguments
    */
   public SafranGenerator_Abs (final Type p_input, final List<? extends Object> p_arguments)
   {
      this._input = p_input;
      this._arguments = p_arguments;
   }

   /**
    * Launches the generation. For the properties manager, the exit procedure is only for writing the properties files in case of change and not for closing any resources, so if an exception is thrown during he generation, it is not important (no finally).
    * 
    * @param p_monitor
    *           This will be used to display progress information to the user.
    * @throws IOException
    *            Thrown when the output cannot be saved.
    */
   public void doGenerate (final IProgressMonitor p_monitor) throws IOException
   {
      // RAZ des erreurs eventuelles dans le "Error Log"
      ErrorGeneration.clear();

      // Chargement de l'ensemble des fichiers '.properties' pour le generateur.
      PacmanPropertiesManager.initProperties(getModelPath());

      // Reinitialisation de certains services.
      resetListOfStaticServices();

      // Check if the type of model or type of project is compatible with the generator else stop.
      if (hasIncompatibleModel() || hasIncompatibleProperties()) return;

      // Enregistrement et lancement de l'ensemble des generateurs.
      for (final PacmanGenerator_Abs v_generator : getPacmanGenerators(_input, _arguments))
      {
         if (v_generator.getTargetFolder() != null)
         {
            // Gestion de la tracabilité
            final String v_generationID = AcceleoLaunchingUtil.computeUIProjectID(v_generator.getProjectName(),
                     v_generator.getClass().getName(), _input.toString(),
                     v_generator.getTargetFolder().getAbsolutePath().toString(), new ArrayList<String>());

            v_generator.setGenerationID(v_generationID);
         }
         // Activation du generateur.
         v_generator.doGenerate(BasicMonitor.toMonitor(p_monitor));
      }

      // Exit for the properties manager (if the generation launched).
      PacmanPropertiesManager.exit();

      // Ajout des erreurs eventuelles dans le "Error Log"
      ErrorGeneration.doIfThrowErrorGenerationException();
   }

   /**
    * Recupere le chemin du projet model.
    * 
    * @return l'URI du modele en entrée
    */
   protected URI getModelUri ()
   {
      final URI v_modelUri;
      if (_input instanceof IFile)
      {
         v_modelUri = URI.createPlatformResourceURI(((IFile) _input).getFullPath().toString(), true);
      }
      else if (_input instanceof EObject)
      {
         v_modelUri = StringUtils.getUriFromEObject((EObject) _input);
      }
      else if (_input instanceof URI)
      {
         v_modelUri = (URI) _input;
      }
      else
      {
         throw new IllegalArgumentException(
                  "Le type d'objet en entrée de la génération n'est pas géré : " + _input.getClass());
      }
      return v_modelUri;
   }

   /**
    * @TODO : a voir comment deplacer cette methode .....
    * @return
    */
   protected String getModelPath ()
   {
      if (_input instanceof IFile)
      {
         return StringUtils.getModuleFolderFromUri(getModelUri());
      }
      else if (_input instanceof EObject)
      {
         return StringUtils.getModuleFolderFromEObject((EObject) _input);
      }
      return null;
   }

   /**
    * Check if one of the properties incompatible with the generator is active.
    * 
    * @return boolean to indicate if we stop the generator or if we continue.
    */
   protected boolean hasIncompatibleProperties ()
   {
      if (null == getValuesOfIncompatibleProperties())
         return Boolean.FALSE;

      for (Boolean v_valueOfProperty : getValuesOfIncompatibleProperties())
      {
         if (v_valueOfProperty)
         {
            PlugInUtils.displayError("Pacman",
                     "Les options prises lors de la création "
                              + "de ce projet ne permettent pas l'utilisation de ce générateur. \n\r"
                              + "La génération va être stoppée.");
            return Boolean.TRUE;
         }
      }
      return Boolean.FALSE;
   }

   /**
    * Check if the model is incompatible with the generator.
    * 
    * @return boolean to indicate if we have to stop the generator.
    */
   protected boolean hasIncompatibleModel ()
   {
      
      if (null == getValuesOfCompatibleModels())
         return Boolean.FALSE;

      for (SafranGenerator_Enum v_model : getValuesOfCompatibleModels())
      {
         if (v_model.get_value().equals(getModelUri().fileExtension()))
            return Boolean.FALSE;
      }

      PlugInUtils.displayError("Pacman", "Le fichier ou l'objet de modélisation "
               + "ne permet pas l'utilisation de ce générateur. \n\r" + "La génération va être stoppée.");

      return Boolean.TRUE;
   }
   
   /**
    * Pas de pattern Observer (classes static). Si des services java on besoin 
    * d'une reinitialisation a chaque generation les positionner ici.
    */
   protected void resetListOfStaticServices() {
      
      ImportsUtils.resetAdditionalTypes();
   }

   /**
    * Retourne la liste des generateurs a utiliser pour ce plugin.
    * 
    * @param p_input
    *           l'entree de la generation
    * @param p_arguments
    *           les arguments
    * @return la liste des generateurs a appeler
    * @throws IOException
    *            erreur lors de l'instanciation d'un generateur
    */
   protected abstract List<PacmanGenerator_Abs> getPacmanGenerators (Type p_input, List<? extends Object> p_arguments)
            throws IOException;

   /**
    * Retourne la liste des projets a rafraichir une fois les generations effectuees.
    * 
    * @return le nom des projets a rafraichir apres generation
    */
   public abstract List<String> getProjectsNamesToRefresh ();

   /**
    * Retourne la liste des retours de proprietes INCOMPATIBLES avec le generateur.
    * 
    * @return la liste des booleens pour ces proprietes.
    */
   public abstract List<Boolean> getValuesOfIncompatibleProperties ();

   /**
    * Retourne la liste des fichiers de modelisation COMPATIBLES pour lancer le generateur.
    * 
    * @return l'extension necessaire sous forme d'enumeration (ex : .entity, .soa, etc...)
    */
   public abstract List<SafranGenerator_Enum> getValuesOfCompatibleModels ();

   /**
    * Cherche un objet parent de type donne pour un objet.
    * 
    * @param p_obj
    *           l'objet source
    * @param p_type
    *           le type du parent recherche
    * @param <T>
    *           le type du parent recherche
    * @return le parent trouve avec le type p_type, ou null si aucun parent de ce type n'a ete trouve
    */
   @SuppressWarnings("unchecked")
   protected static <T> T getParent (final EObject p_obj, final Class<T> p_type)
   {
      EObject v_parent = p_obj.eContainer();
      while (v_parent != null && !(p_type.isAssignableFrom(v_parent.getClass())))
      {
         v_parent = v_parent.eContainer();
      }
      if (v_parent != null)
      {
         return (T) v_parent;
      }
      return null;
   }
   
   /**
    * Indique si  le generateur fonctionne en mode 'debug'.
    * 
    * @return true si mode debug.
    */
   protected Boolean isModeDebug () {
    
      return Boolean.valueOf(ProjectProperties.getIsDebugMode());
   }
   
   /**
    * Indique si le générateur s'occupe du formattage automatique des imports.
    * 
    * @return true si formattage auto des imports demandé.
    */
   protected Boolean isFormatImports () {
      
      return Boolean.valueOf(ProjectProperties.getIsFormatImports());
   }

   /**
    * LEGACY - DEBUT (Ne devrait pas etre dans cette classe).
    */

   /**
    * @return le projet serveur (lu dans le fichier properties)
    */
   protected File getServerProject ()
   {
      return getProjectFromName(getServerProjectName());
   }

   /**
    * @return le projet commons (lu dans le fichier properties)
    */
   protected File getCommonsProject ()
   {
      return getProjectFromName(getCommonsProjectName());
   }

   /**
    * @return le projet client GWT (lu dans le fichier properties)
    */
   protected File getClientGwtProject ()
   {
      return getProjectFromName(getClientGwtProjectName());
   }

   /**
    * @return le projet client JSF (lu dans le fichier properties)
    */
   protected File getClientJsfProject ()
   {
      return getProjectFromName(getClientJsfProjectName());
   }

   /**
    * @return le projet contenant le pom.xml principal (lu dans le fichier properties)
    */
   protected File getRootProject ()
   {
      return getProjectFromName(getRootProjectName());
   }

   /**
    * @return le projet client SWING (lu dans le fichier properties)
    */
   protected File getClientSwingProject ()
   {
      return getProjectFromName(getClientSwingProjectName());
   }

   /**
    * @return le projet client JSP (lu dans le fichier properties)
    */
   protected File getClientJspProject ()
   {
      return getProjectFromName(getClientJspProjectName());
   }

   /**
    * @return le projet courant, contenant le modèle utilisé pour la génération
    */
   protected File getModelProject ()
   {
      return getProjectFromName(getModelProjectName());
   }

   /**
    * @return le projet d'integration pour le swing,
    */
   protected File getIntegrationProject ()
   {
      return getProjectFromName(getIntegrationProjectName());
   }

   /**
    * @return le nom du projet serveur (lu dans le fichier properties).
    */
   protected String getServerProjectName ()
   {
      return ProjectProperties.getServerProjectName();
   }

   /**
    * @return le nom du projet commons (lu dans le fichier properties).
    */
   protected String getCommonsProjectName ()
   {
      return ProjectProperties.getCommonProjectName();
   }

   /**
    * @return le nom du projet client GWT (lu dans le fichier properties).
    */
   protected String getClientGwtProjectName ()
   {
      return ProjectProperties.getClientGwtProjectName();
   }

   /**
    * @return le nom du projet client SWING (lu dans le fichier properties).
    */
   protected String getClientSwingProjectName ()
   {
      return ProjectProperties.getClientSwingProjectName();
   }

   /**
    * @return le nom du projet client SWING (lu dans le fichier properties).
    */
   protected String getClientJsfProjectName ()
   {
      return ProjectProperties.getClientJsfProjectName();
   }
   
   /**
    * @return le nom du projet client JSF (lu dans le fichier properties).
    */
   protected String getClientJspProjectName () 
   {
      return ProjectProperties.getClientJspProjectName();   
   }

   /**
    * @return le nom du projet conteneur (lu dans le fichier properties).
    */
   protected String getRootProjectName ()
   {
      return ProjectProperties.getApplicationName();
   }

   /**
    * @return le nom du projet qui contient les modeles (lu dans le fichier properties).
    */
   protected String getModelProjectName ()
   {
      return ProjectProperties.getModelProjectName();
   }

   /**
    * @return le nom du projet d'integration (lu dans le fichier properties)
    */
   protected String getIntegrationProjectName ()
   {
      return ProjectProperties.getClientIntegrationProjectName();
   }

   /**
    * @return true si client.type contient GWT (lu dans le fichier properties)
    * 
    * @TODO : attention a la valeur null !!! a modifier.
    */
   protected boolean needsClientGwt ()
   {
      if (ProjectProperties.getClient().toUpperCase().indexOf("GWT") != -1)
         return Boolean.TRUE;
      return Boolean.FALSE;
   }

   /**
    * 
    * @return la valeur de matching.layer.enabled (lu dans le fichier properties).
    */
   protected boolean matchingLayerEnable ()
   {
      return Boolean.parseBoolean(ProjectProperties.getUseMatching());
   }

   /**
    * 
    * @return la valeur de tests.bdd.enabled (lu dans le fichier properties).
    */
   protected boolean bddTestsEnable ()
   {
      return Boolean.parseBoolean(ProjectProperties.getUseTestBDD());
   }

   /**
    * 
    * @return la valeur de project.isLibrary (lu dans le fichier properties).
    */
   protected boolean isLibrary ()
   {
      return Boolean.parseBoolean(ProjectProperties.getIsLibrary());
   }

   /**
    * Retourne le projet par son nom ou la destination par défaut.
    * 
    * @param p_name
    *           le nom du projet
    * @return le projet par son nom ou la destination par défaut
    * 
    */
   private File getProjectFromName (final String p_name)
   {
      File v_target = null;

      if (p_name != null)
      {
         final File v_project = PlugInUtils.findProjectByName(p_name);

         if (v_project != null)
            v_target = v_project;
      }

      if (v_target == null && (p_name == null || !p_name.equalsIgnoreCase(getModelProjectName())))
      {
         Activator.getDefault().getLog()
                  .log(new Status(Status.WARNING, Activator.c_PLUGIN_ID,
                           "Projet inconnu : " + p_name + ". Utilisation du projet de modélisation ("
                                    + getModelProjectName() + ") pour la génération."));

         v_target = getProjectFromName(getModelProjectName());
      }
      return v_target;
   }

   /**
    * LEGACY - FIN
    */
}
