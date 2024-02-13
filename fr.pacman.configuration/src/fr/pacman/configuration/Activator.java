package fr.pacman.configuration;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

public class Activator extends Plugin {

	/**
	 * The plug-in ID.
	 */
	public static final String c_PLUGIN_ID = "fr.pacman.configuration";

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
	public void start(final BundleContext p_context) throws Exception 
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
	public void stop(final BundleContext p_context) throws Exception 
	{
		plugin = null;
		super.stop(p_context);
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
}
