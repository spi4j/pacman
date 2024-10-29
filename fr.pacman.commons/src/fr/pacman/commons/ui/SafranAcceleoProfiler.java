package fr.pacman.commons.ui;

import java.io.IOException;

import org.eclipse.acceleo.common.preference.AcceleoPreferences;
import org.eclipse.acceleo.engine.utils.AcceleoEngineUtils;
import org.eclipse.acceleo.profiler.Profiler;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class SafranAcceleoProfiler {

	private final Profiler _profiler;
	private static IProject _project;

	SafranAcceleoProfiler() {
		_profiler = new Profiler();
		AcceleoEngineUtils.setProfiler(_profiler);
		AcceleoPreferences.switchProfiler(true);
	}
	
	

	public static void set_project(IProject _project) {
		SafranAcceleoProfiler._project = _project;
	}


	/**
	 * 
	 * @throws IOException
	 */
	void write() throws IOException {
		if (isProfilerAvailable()) {
			ResourceSet v_rs = _profiler.getProfileResource().getEntries().get(0).getMonitored().eResource()
					.getResourceSet();

			URI logicalURI = URI.createURI("../../../../../../../../git/pacman/");
			URI physicalURI = URI.createURI("file:/home/vrichard/git/pacman/");
			v_rs.getURIConverter().getURIMap().put(logicalURI, physicalURI);
			_profiler.save(_project.getLocation().append("profile.mtlp").toString());
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isProfilerAvailable() {
		return _profiler != null
				&& _profiler.getProfileResource().getEntries().get(0).getMonitored().eResource() != null;
	}
}
