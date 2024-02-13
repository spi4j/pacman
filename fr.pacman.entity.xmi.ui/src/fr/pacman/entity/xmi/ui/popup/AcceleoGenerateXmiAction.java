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
package fr.pacman.entity.xmi.ui.popup;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILog;

import fr.pacman.commons.ui.AcceleoGenerateSafranAction;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.entity.xmi.ui.Activator;
import fr.pacman.entity.xmi.ui.common.GenerateXmiUI;

/**
 * Entity code generation.
 */
public class AcceleoGenerateXmiAction extends AcceleoGenerateSafranAction<IFile>
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
   protected SafranGenerator_Abs<IFile> getSafranGenerator (final IFile p_modelURI, final List<? extends Object> p_arguments)
   {
      return new GenerateXmiUI(p_modelURI, p_arguments);
   }

}
