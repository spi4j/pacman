package fr.pacman.entity.dbreferentiel.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.Logger;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.ui.PacmanUIGenerator_Abs;
import fr.pacman.commons.ui.PacmanUIGenerator_Enum;
import fr.pacman.entity.api.dbreferentiel.GenerateServerDbReferentiel;

/**
 * UI Generator for database referentiel based on ".entity" model file.
 * 
 * @author MINARM
 */
public class DbReferentielUIGenerators extends PacmanUIGenerator_Abs {

	/**
	 * Constructor.
	 * 
	 * @param p_selected the selected resource, in this case the model file.
	 */
	public DbReferentielUIGenerators(final IFile p_selected) {
		super(p_selected);
	}

	/**
	 * Get the list of all generators to execute the code generation.
	 * 
	 * @return a list of generators to execute.
	 */
	@Override
	protected List<PacmanGenerator_Abs> getGenerators() {
		final List<PacmanGenerator_Abs> v_generators = new ArrayList<>();
		v_generators.add(new GenerateServerDbReferentiel());
		return v_generators;
	}

	/**
	 * Get the list for all projects to refresh after the generation. Add here all
	 * necessary project names issued from the {@link PacmanPropertiesManager}.
	 * 
	 * @return a list of projects to refresh after code generation.
	 */
	@Override
	protected List<String> getSubProjectsToRefresh() {
		final List<String> v_projects = new ArrayList<String>();
		v_projects.add(ProjectProperties.getModelProjectName());
		v_projects.add(ProjectProperties.getServerProjectName());
		return v_projects;
	}

	/**
	 * Get the list for all incompatible properties issued form the project
	 * properties file. If a 'true' value is found in the list of the properties
	 * values, it means the property is enabled and the code generator execution is
	 * stopped. Add here all incompatible properties issued from the project
	 * properties file, with the help of the {@link PacmanPropertiesManager}.
	 * 
	 * @return a list of all incompatible properties issued from the project
	 *         properties file
	 */
	@Override
	protected List<String> getIncompatibleProperties() {
		return Arrays.asList(ProjectProperties.getIsLibraryRs());
	}

	/**
	 * Get the list for all compatible model files. If the selected model file is
	 * not present, the code generator execution is stopped. Add here all compatible
	 * model files.
	 * 
	 * @return a list of all compatible model files
	 */
	@Override
	protected List<PacmanUIGenerator_Enum> getCompatibleModels() {
		return Arrays.asList(PacmanUIGenerator_Enum.ENTITY);
	}

	/**
	 * Flag for automatic import management. If set to 'true', at the end of
	 * generation, the 'CTRL + SHIFT + O' key sequence is sent to Eclipse for
	 * automatic import management.
	 * 
	 * @return 'true' to activate the automatic import management else 'false'.
	 */
	@Override
	protected boolean getOrganizeImports() {
		return false;
	}

	/**
	 * Get the id for the plugin.
	 * 
	 * @return the id for the plugin.
	 */
	@Override
	protected String getPluginId() {
		return Activator.PLUGIN_ID;
	}
	
	/**
	 * Get the logger for the plugin.
	 * 
	 * @return the logger for the plugin.
	 */
	@Override
	protected Logger getLogger() {
		return Activator.getDefault().getPluginLogger();
	}
}
