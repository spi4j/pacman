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
package fr.pacman.cinematic.jsp.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.cdo.eresource.CDOResource;

import fr.pacman.cinematic.api.GenerateCinematicApi;
import fr.pacman.cinematic.jsp.GenerateCinematicJsp;
import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGeneratorOld_Abs;
import fr.pacman.commons.ui.SafranGeneratorOld_Abs;
import fr.pacman.commons.ui.PacmanGenerator_Enum;

/**
 * Main entry point of the 'Entity' generation module.
 */
public class GenerateJspUICDOResource extends SafranGeneratorOld_Abs<CDOResource> {

	/**
	 * Constructeur.
	 * 
	 * @param p_modelURI
	 *            le modèle
	 * @param p_arguments
	 *            les arguments de génération
	 */
	public GenerateJspUICDOResource(final CDOResource p_modelURI, final List<? extends Object> p_arguments) {
		super(p_modelURI, p_arguments);
	}

	@Override
	protected List<PacmanGeneratorOld_Abs> getPacmanGenerators(final CDOResource p_file,
			final List<? extends Object> p_arguments) throws IOException {
		final List<PacmanGeneratorOld_Abs> v_generators = new ArrayList<PacmanGeneratorOld_Abs>();
		v_generators.add(new GenerateCinematicApi(p_file, getClientJspProject(), p_arguments));
		v_generators.add(new GenerateCinematicJsp(p_file, getClientJspProject(), p_arguments));
		return v_generators;
	}

	@Override
	public List<String> getProjectsNamesToRefresh() {
		return Arrays.asList(getModelProjectName(), getClientJspProjectName());
	}
	
	@Override
	public List<Boolean> getValuesOfIncompatibleProperties() {
		return Arrays.asList(Boolean.parseBoolean(ProjectProperties.getIsLibraryRs()),
				"JSP".equalsIgnoreCase(ProjectProperties.getClient()) ? Boolean.FALSE : Boolean.TRUE);
	}
	
	@Override
	public List<PacmanGenerator_Enum> getValuesOfCompatibleModels() {
		return Arrays.asList(PacmanGenerator_Enum.CINEMATIC);
	}
}
