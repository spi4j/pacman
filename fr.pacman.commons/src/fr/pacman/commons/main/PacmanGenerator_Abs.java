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
package fr.pacman.commons.main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.acceleo.common.preference.AcceleoPreferences;
import org.eclipse.acceleo.engine.generation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator;
import org.eclipse.acceleo.engine.service.AcceleoService;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import fr.pacman.commons.convention.project.ProjectProperties;

/**
 * Entry point of the 'Generate' generation module.
 */
public abstract class PacmanGenerator_Abs extends AbstractAcceleoGenerator
{

   /**
    * @return le nom du module de generation
    */
   protected abstract String getModuleFileName ();

   /**
    * @return la liste des templates de generation
    */
   protected abstract String[] getModuleTemplates ();

   /**
    * L'URI du modele.
    */
   protected URI _modelURI;

   /**
    * Les eventuels arguments.
    */
   protected List<? extends Object> _arguments;

   /**
    * Constructeur.
    */
   public PacmanGenerator_Abs ()
   {
      super();
   }

   /**
    * This allows clients to instantiates a generator with all required information.
    * 
    * @param p_model
    *           We'll iterate over the content of this element to find Objects matching the first parameter of the template we need to call.
    * @param p_targetFolder
    *           This will be used as the output folder for this generation : it will be the base path against which all file block URLs will be resolved.
    * @param p_arguments
    *           If the template which will be called requires more than one argument taken from the model, pass them here.
    * @throws IOException
    *            This can be thrown in three scenarios : the module cannot be found, it cannot be loaded, or the model cannot be loaded.
    */
   public PacmanGenerator_Abs (final EObject p_model, final File p_targetFolder,
            final List<? extends Object> p_arguments) throws IOException
   {
      initialize(p_model, p_targetFolder, p_arguments);
   }

   /**
    * This allows clients to instantiates a generator with all required information.
    * 
    * @param p_modelURI
    *           URI where the model on which this generator will be used is located.
    * @param p_targetFolder
    *           This will be used as the output folder for this generation : it will be the base path against which all file block URLs will be resolved.
    * @param p_arguments
    *           If the template which will be called requires more than one argument taken from the model, pass them here.
    * @throws IOException
    *            This can be thrown in three scenarios : the module cannot be found, it cannot be loaded, or the model cannot be loaded.
    */
   public PacmanGenerator_Abs (final URI p_modelURI, final File p_targetFolder,
            final List<? extends Object> p_arguments) throws IOException
   {
      initialize(p_modelURI, p_targetFolder, p_arguments);
   }

   @Override
   public void initialize (final EObject p_model, final File p_folder, final List<?> p_arguments) throws IOException
   {
      super.initialize(p_model, p_folder, p_arguments);
   }

   @Override
   public void initialize (final URI p_modelURI, final File p_folder, final List<?> p_arguments) throws IOException
   {
      _modelURI = p_modelURI;
      _arguments = p_arguments;
      super.initialize(p_modelURI, p_folder, p_arguments);
   }

   /**
    * This will be used to get the list of templates that are to be launched by this launcher.
    * 
    * @return The list of templates to call on the module {@link #getModuleName()}.
    */
   @Override
   public String[] getTemplateNames ()
   {
      return getModuleTemplates();
   }

   /**
    * This will be called in order to find and load the module that will be launched through this launcher. We expect this name not to contain file extension, and the module to be located beside the launcher.
    * 
    * @return The name of the module that is to be launched.
    */
   @Override
   public String getModuleName ()
   {
      return getModuleFileName();
   }

   /**
    * This can be used to update the resource set's package registry with all needed EPackages.
    * 
    * @param p_resourceSet
    *           The resource set which registry has to be updated.
    * @generated
    */
   @Override
   public void registerPackages (final ResourceSet p_resourceSet)
   {
      super.registerPackages(p_resourceSet);
   }

   /**
    * @return le nom du projet du generateur.
    */
   public abstract String getProjectName ();
   
   /**
    * @return le generateur doit il activer ou desactiver le cache pour les queries.
    */
   public abstract boolean getSwitchQueryCache ();

   /**
    * Methode principale pour le generateur.
    */
   @Override
   public void doGenerate (final Monitor p_monitor) throws IOException
   {
         // Par defaut le resultat des queries Acceleo sont mis en cache, ce qui 
         // peut perturber le fonctionnement des services Java Statefull. Il est 
         // donc possible pour certains générateurs de demander expressement la 
         // desactivation du cache.
         AcceleoPreferences.switchQueryCache(getSwitchQueryCache());
         
         super.doGenerate(p_monitor);
   }

   @Override
   protected AcceleoService createAcceleoService ()
   {
      final AcceleoService v_service = super.createAcceleoService();
      return v_service;
   }

   @Override
   public IAcceleoGenerationStrategy getGenerationStrategy ()
   {
      String v_lineDelimiter = ProjectProperties.getDelimiter();

      if ("WINDOWS".equals(v_lineDelimiter))
         v_lineDelimiter = "\r\n";

      if ("UNIX".equals(v_lineDelimiter))
         v_lineDelimiter = "\n";

      if (null == v_lineDelimiter)
         v_lineDelimiter = System.getProperty("line.separator");

      // Pour l'instant on force.
      return new PacmanStrategy(v_lineDelimiter);
   }
}
