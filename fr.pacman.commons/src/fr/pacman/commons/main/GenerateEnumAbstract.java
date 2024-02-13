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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

/**
 * Entry point of the 'Entity' generation module.
 * 
 * @generated NOT
 */
public abstract class GenerateEnumAbstract extends PacmanGenerator_Abs
{
	 /**
	    * The name of the module.
	    *
	    * @generated
	    */
      
	   protected String c_MODULE_FILE_NAME;
	   
	   /**
	    * The name of the templates that are to be generated.
	    */
	   protected  String[] _TEMPLATE_NAMES;

   /**
    * This allows clients to instantiates a generator with all required information.
    * 
    * @param p_model
    *           We'll iterate over the content of this element to find Objects matching the first parameter of the template we need to call.
    * @param p_targetFolder
    *           This will be used as the output folder for this generation : it will be the base path against which all file block URLs will be resolved.
    * @param p_arguments
    *           If the template which will be called requires more than one argument taken from the model, pass them here.
    * @return 
    * @throws IOException
    *            This can be thrown in three scenarios : the module cannot be found, it cannot be loaded, or the model cannot be loaded.
    */
   public GenerateEnumAbstract(final EObject p_model, final File p_targetFolder, final List<? extends Object> p_arguments) throws IOException
   {     
      super(p_model, p_targetFolder, p_arguments);         
      _TEMPLATE_NAMES = new String[]{"generateEnum"};
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
   public GenerateEnumAbstract(final URI p_modelURI, final File p_targetFolder, final List<? extends Object> p_arguments) throws IOException
   {
      
      super(p_modelURI, p_targetFolder, p_arguments);
      _TEMPLATE_NAMES = new String[]{"generateEnum"};
   }
 
   @Override
   public String[] getModuleTemplates ()
   {
      return _TEMPLATE_NAMES;
   }

   @Override
   public String getModuleFileName ()
   {
      return c_MODULE_FILE_NAME;
   }

   public abstract String getProjectName ();
}
