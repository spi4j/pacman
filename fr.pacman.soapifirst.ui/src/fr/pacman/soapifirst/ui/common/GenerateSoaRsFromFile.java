package fr.pacman.soapifirst.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Enum;
import fr.pacman.soapifirst.GenerateCommonSoaRs;
import fr.pacman.soapifirst.GenerateServerSoaRs;

/**
 * Main entry point of the 'SOA API FIRST' generation module.
 */
public class GenerateSoaRsFromFile extends SafranGenerator_Abs<IFile> {

	/**
	 * Constructor.
	 * 
	 * @param p_modelURI  : the uri for the model project.
	 * @param p_arguments : the parameters for generation.
	 */
	public GenerateSoaRsFromFile(IFile p_input, List<? extends Object> p_arguments) {
		super(p_input, p_arguments);
	}

	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(IFile p_input, List<? extends Object> p_arguments)
			throws IOException {

		final URI v_modelURI = getModelUri();

		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
		v_generators.add(new GenerateServerSoaRs(v_modelURI, getServerProject(), p_arguments));
		v_generators.add(new GenerateCommonSoaRs(v_modelURI, getCommonsProject(), p_arguments));

		return v_generators;
	}

	@Override
	public List<String> getProjectsNamesToRefresh() {

		final List<String> v_result = new ArrayList<String>();
		v_result.add(getModelProjectName());
		v_result.add(getServerProjectName());
		v_result.add(getCommonsProjectName());
		return v_result;
	}

	@Override
	public List<Boolean> getValuesOfIncompatibleProperties() {
		return Arrays.asList(!Boolean.parseBoolean(ProjectProperties.getIsLibraryRs()));
	}
	
	@Override
	public List<SafranGenerator_Enum> getValuesOfCompatibleModels() {
		return Arrays.asList(SafranGenerator_Enum.SOA);
	}
}
