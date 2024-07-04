package fr.pacman.soapifirst.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.cdo.eresource.CDOResource;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Enum;
import fr.pacman.soapifirst.GenerateCommonSoaRs;
import fr.pacman.soapifirst.GenerateServerSoaRs;

/**
 * Main entry point of the 'SOA API FIRST' generation module.
 */
public class GenerateSoaRsFromCDOResource extends SafranGenerator_Abs<CDOResource> {

	/**
	 * Constructor.
	 * 
	 * @param p_object
	 *            The model.
	 * @param p_arguments
	 *            The arguments for the generation.
	 */
	public GenerateSoaRsFromCDOResource(CDOResource p_input, List<? extends Object> p_arguments) {
		super(p_input, p_arguments);
	}

	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(CDOResource p_input, List<? extends Object> p_arguments)
			throws IOException {

		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
		
		v_generators.add(new GenerateServerSoaRs(p_input, getServerProject(), p_arguments));
		v_generators.add(new GenerateCommonSoaRs(p_input, getCommonsProject(), p_arguments));

		return v_generators;
	}

	@Override
	protected List<String> getProjectsNamesToRefresh() {

		final List<String> v_result = new ArrayList<String>();
		v_result.add(getModelProjectName());
		v_result.add(getServerProjectName());
		v_result.add(getCommonsProjectName());
		return v_result;
	}
	
	@Override
	protected List<Boolean> getValuesOfIncompatibleProperties() {
		return Arrays.asList(!Boolean.parseBoolean(ProjectProperties.getIsLibraryRs()));
	}
	
	@Override
	protected List<SafranGenerator_Enum> getValuesOfCompatibleModels() {
		return Arrays.asList(SafranGenerator_Enum.SOA);
	}
	
	@Override
	protected boolean getOrganizeImports() {
		return true;
	}
}
