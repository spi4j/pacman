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
package fr.pacman.soa;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends Plugin
{

   /**
    * The plug-in ID.
    */
   public static final String c_PLUGIN_ID = "fr.pacman.soa";

   /**
    * The shared instance.
    */
   private static Activator plugin;

   /**
    * {@inheritDoc}
    * 
    * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
    */
   @Override
   public void start (final BundleContext p_context) throws Exception 
   {
      super.start(p_context);
      plugin = this;
   }

   /**
    * {@inheritDoc}
    * 
    * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
    */
   @Override
   public void stop (final BundleContext p_context) throws Exception 
   {
      plugin = null;
      super.stop(p_context);
   }

   /**
    * Returns the shared instance.
    * 
    * @return the shared instance
    */
   public static Activator getDefault ()
   {
      return plugin;
   }

}
