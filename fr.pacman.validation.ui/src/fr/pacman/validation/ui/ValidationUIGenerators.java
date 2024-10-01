package fr.pacman.validation.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.obeonetwork.dsl.soa.System;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.ui.PacmanUIGenerator_Enum;
import fr.pacman.commons.ui.PacmanUIGenerator_Abs;
import fr.pacman.validation.GenerateJavaValidation;

/**
 * UI Generator for validation.
 * 
 * @author MINARM
 */
public class ValidationUIGenerators extends PacmanUIGenerator_Abs {

	/**
	 * Constructor.
	 * 
	 * @param p_selected the selected resource, in this case the root EObject for
	 *                   the model file.
	 */
	public ValidationUIGenerators(final System p_selected) {
		super(p_selected);
	}

	/**
	 * Constructor.
	 * 
	 * @param p_selected the selected resource, in this case the model file.
	 */
	public ValidationUIGenerators(final IFile p_selected) {
		super(p_selected);
	}

	/**
	 * Get the list of all generators to execute for code generation.
	 * 
	 * @return a list of generators to execute.
	 */
	@Override
	protected List<PacmanGenerator_Abs> getGenerators() {
		final List<PacmanGenerator_Abs> v_generators = new ArrayList<>();
		v_generators.add(new GenerateJavaValidation());
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
		return Collections.emptyList();
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
		return null;
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
		return null;
	}

	/**
	 * Flag for automatic import management.
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
}
