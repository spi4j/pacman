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
package fr.pacman.entity.api.dbreferentiel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.entity.api.Activator;

/**
 * Entry point of the 'GenerateDbReferentiel' generation module.
 * 
 * @generated NOT
 */
public class GenerateDbReferentiel extends PacmanGenerator_Abs
{
   /**
    * The name of the module.
    */
   public static final String c_MODULE_FILE_NAME = "generateDbReferentiel";

   /**
    * The name of the templates that are to be generated.
    */
   public static final String[] c_TEMPLATE_NAMES =
   {"generate", };

   /**
    * Allows the public constructor to be used. Note that a generator created this way cannot be used to launch generations before one of initialize(EObject, File, List) or initialize(URI, File, List) is called.
    * <p>
    * The main reason for this constructor is to allow clients of this generation to call it from another Java file, as it allows for the retrieval of {@link #getProperties()} and {@link #getGenerationListeners()}.
    * </p>
    */
   public GenerateDbReferentiel ()
   {
      super();
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
   public GenerateDbReferentiel (final URI p_modelURI, final File p_targetFolder, final List<? extends Object> p_arguments) throws IOException
   {
      super(p_modelURI, p_targetFolder, p_arguments);
   }

   /**
    * This can be used to launch the generation from a standalone application.
    * 
    * @param p_args
    *           Arguments of the generation.
    */
   public static void main (final String[] p_args)
   {
      try
      {
         if (p_args.length < 2)
         {
            throw new RuntimeException("Arguments not valid : {model, folder}.");
         }
         else
         {
            final URI v_modelURI = URI.createFileURI(p_args[0]);
            final File v_folder = new File(p_args[1]);
            final List<String> v_arguments = new ArrayList<String>();
            for (int v_i = 2; v_i < p_args.length; v_i++)
            {
               v_arguments.add(p_args[v_i]);
            }
            final GenerateDbReferentiel v_generator = new GenerateDbReferentiel(v_modelURI, v_folder, v_arguments);
            v_generator.doGenerate(new BasicMonitor());
         }
      }
      catch (final IOException v_e)
      {
         v_e.printStackTrace(); 
      }
   }

   /**
    * This can be used to update the resource set's package registry with all needed EPackages.
    * 
    * @param p_resourceSet
    *           The resource set which registry has to be updated.
    */
   @Override
   public void registerPackages (final ResourceSet p_resourceSet)
   {
      super.registerPackages(p_resourceSet);
      p_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.entity.EntityPackage.eINSTANCE.getNsURI(), org.obeonetwork.dsl.entity.EntityPackage.eINSTANCE);
      p_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.entity.extensionUtilities.ExtensionUtilitiesPackage.eINSTANCE.getNsURI(), org.obeonetwork.dsl.entity.extensionUtilities.ExtensionUtilitiesPackage.eINSTANCE);
      p_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.environment.EnvironmentPackage.eINSTANCE.getNsURI(), org.obeonetwork.dsl.environment.EnvironmentPackage.eINSTANCE);
      // resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
   }

   @Override
   public String[] getModuleTemplates ()
   {
      return c_TEMPLATE_NAMES;
   }

   @Override
   public String getModuleFileName ()
   {
      return c_MODULE_FILE_NAME;
   }

   @Override
   public String getProjectName ()
   {
      return Activator.c_PLUGIN_ID;
   }

   @Override
   public boolean getSwitchQueryCache ()
   {
      return Boolean.TRUE;
   }
}
