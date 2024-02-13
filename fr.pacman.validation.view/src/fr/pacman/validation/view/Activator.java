package fr.pacman.validation.view;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin
{

   // The plug-in ID
   public static final String c_PLUGIN_ID = "fr.pacman.validation.view"; //$NON-NLS-1$

   // The shared instance
   private static Activator plugin;

   /**
    * {@inheritDoc}
    * 
    * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
    * @generated
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
    * @generated
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

   /**
    * Returns an image descriptor for the image file at the given plug-in
    * relative path
    * 
    * @param p_path
    *           the path
    * @return the image descriptor
    */
   public static ImageDescriptor getImageDescriptor (final String p_path)
   {
      return imageDescriptorFromPlugin(c_PLUGIN_ID, p_path);
   }
}
