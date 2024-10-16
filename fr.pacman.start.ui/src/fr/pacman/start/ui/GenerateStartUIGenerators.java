package fr.pacman.start.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.Logger;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.PacmanUIGenerator_Abs;
import fr.pacman.commons.ui.PacmanUIGenerator_Enum;

/**
 * 
 * @author MINARM
 */
public class GenerateStartUIGenerators extends PacmanUIGenerator_Abs {

	public GenerateStartUIGenerators(IProject p_project) {
		super(p_project);
	}

	@Override
	protected List<String> getSubProjectsToRefresh() {
		final List<String> v_projects = new ArrayList<String>();
		v_projects.add(ProjectProperties.getServerProjectName());
		v_projects.add(ProjectProperties.getCommonProjectName());
		v_projects.add(ProjectProperties.getClientGwtProjectName());
		v_projects.add(ProjectProperties.getClientJsfProjectName());
		v_projects.add(ProjectProperties.getClientJspProjectName());
		v_projects.add(ProjectProperties.getClientSwingProjectName());
		v_projects.add(ProjectProperties.getClientJavaWebStartProjectName());
		v_projects.add(ProjectProperties.getClientIntegrationProjectName());
		v_projects.add(ProjectProperties.getWebappProjectName());
		return v_projects;
	}

	@Override
	protected List<String> getIncompatibleProperties() {
		return null;
	}

	@Override
	protected List<PacmanUIGenerator_Enum> getCompatibleModels() {
		return null;
	}

	@Override
	protected boolean getOrganizeImports() {
		return true;
	}

	@Override
	protected List<PacmanGenerator_Abs> getGenerators() {
		return null;
	}

	@Override
	protected String getPluginId() {
		return null;
	}

	@Override
	protected Logger getLogger() {
		return null;
	}

	public void updateIDEAfterCodeGeneration() throws CoreException {

		super.updateIDEAfterCodeGeneration();
	}
}
