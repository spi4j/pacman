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
package fr.pacman.cinematic.jsf.ui.popup;

import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.emf.cdo.eresource.CDOResource;

import fr.pacman.cinematic.jsf.ui.Activator;
import fr.pacman.cinematic.jsf.ui.common.GenerateJsfUICDOResource;
//import fr.pacman.cinematic.jsf.ui.common.GenerateJsfUICDOResource;
import fr.pacman.commons.ui.AcceleoGenerateSafranAction;
import fr.pacman.commons.ui.SafranGenerator_Abs;

/**
 * Entity code generation.
 */
public class AcceleoGenerateJsfActionCDOResource extends AcceleoGenerateSafranAction<CDOResource>
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
   protected SafranGenerator_Abs<CDOResource> getSafranGenerator (final CDOResource p_modelURI, final List<? extends Object> p_arguments)
   {
      return new GenerateJsfUICDOResource(p_modelURI, p_arguments);	  
   }

}
