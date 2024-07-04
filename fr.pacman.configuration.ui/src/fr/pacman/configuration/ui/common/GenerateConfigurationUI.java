package fr.pacman.configuration.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Enum;
import fr.pacman.configuration.GenerateConfiguration;

public class GenerateConfigurationUI extends SafranGenerator_Abs<IFile> {

	public GenerateConfigurationUI(IFile p_modelURI, List<? extends Object> p_arguments) {
		super(p_modelURI, p_arguments);
	}

	/**
	 * IMPORTANT : pour pouvoir ecrire les differents fichiers facilement aux bons
	 * endroits, on reprend le chemin de base du projet pour le "targerFolder" avec
	 * la methode "getRootProject()". Ainsi, dans les differents scripts .mtl, il
	 * suffit de recuperer simplement les noms des differents sous-projets, on se
	 * retrouve dans la situation initiale du starter.
	 */
	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(IFile p_file, List<? extends Object> p_arguments)
			throws IOException {

		final URI v_modelURI = getModelUri();
		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
		v_generators.add(new GenerateConfiguration(v_modelURI, getRootProject(), p_arguments));
		return v_generators;
	}

	@Override
	protected List<String> getProjectsNamesToRefresh() {

		// On prend la racine du projet.
		ArrayList<String> v_projectsToRefresh = new ArrayList<>();
		v_projectsToRefresh.add(getRootProjectName());
		return v_projectsToRefresh;
	}

	@Override
	protected List<Boolean> getValuesOfIncompatibleProperties() {
		return null;
	}

	@Override
	protected List<SafranGenerator_Enum> getValuesOfCompatibleModels() {
		return Arrays.asList(SafranGenerator_Enum.ENTITY, SafranGenerator_Enum.SOA);
	}
	
	@Override
	protected boolean getOrganizeImports() {
		return true;
	}
}
