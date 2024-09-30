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
package fr.pacman.cinematic.jsp.ui.popup;

import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.emf.cdo.eresource.CDOResource;

import fr.pacman.cinematic.jsp.Activator;
import fr.pacman.cinematic.jsp.ui.common.GenerateJspUICDOResource;
//import fr.pacman.cinematic.jsf.ui.common.GenerateJsfUICDOResource;
import fr.pacman.commons.ui.AcceleoGenerateSafranActionOld;
import fr.pacman.commons.ui.SafranGeneratorOld_Abs;

/**
 * Entity code generation.
 */
public class AcceleoGenerateJspActionCDOResource extends AcceleoGenerateSafranActionOld<CDOResource>
{

   @Override
   protected ILog getLogger ()
   {
      return Activator.getDefault().getLog();
   }

   @Override
   protected String getPluginId ()
   {
      return Activator.c_PLUGIN_ID;
   }

   @Override
   protected SafranGeneratorOld_Abs<CDOResource> getSafranGenerator (final CDOResource p_modelURI, final List<? extends Object> p_arguments)
   {
      return new GenerateJspUICDOResource(p_modelURI, p_arguments);	  
   }

}
