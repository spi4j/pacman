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
package fr.pacman.cinematic.swing.ui.popup;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILog;

import fr.pacman.cinematic.swing.ui.Activator;
import fr.pacman.cinematic.swing.ui.common.GenerateSwingUI;
import fr.pacman.commons.ui.AcceleoGenerateSafranActionOld;
import fr.pacman.commons.ui.SafranGeneratorOld_Abs;

/**
 * Entity code generation.
 */
public class AcceleoGenerateSwingAction extends AcceleoGenerateSafranActionOld<IFile>
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
   protected SafranGeneratorOld_Abs<IFile> getSafranGenerator (final IFile p_modelURI, final List<? extends Object> p_arguments)
   {
      return new GenerateSwingUI(p_modelURI, p_arguments);
   }

}
