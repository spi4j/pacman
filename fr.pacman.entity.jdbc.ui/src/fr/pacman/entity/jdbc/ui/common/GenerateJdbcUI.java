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
package fr.pacman.entity.jdbc.ui.common;

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
import fr.pacman.entity.api.GenerateEntityItfApiSoaLight;
import fr.pacman.entity.api.GenerateEnumEntity;
import fr.pacman.entity.api.GeneratePersistenceApi;
import fr.pacman.entity.jdbc.GenerateClientGwtSoaLight;
import fr.pacman.entity.jdbc.GenerateEntityJdbc;
import fr.pacman.entity.jdbc.GenerateEntityJdbcSoaLight;

/**
 * Main entry point of the 'Entity' generation module.
 */
public class GenerateJdbcUI extends SafranGenerator_Abs<IFile> {

	/**
	 * Constructeur.
	 * 
	 * @param p_modelURI
	 *            le modèle
	 * @param p_arguments
	 *            les arguments de génération
	 */
	public GenerateJdbcUI(final IFile p_modelURI, final List<? extends Object> p_arguments) {
		super(p_modelURI, p_arguments);
	}

	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(final IFile p_file,
			final List<? extends Object> p_arguments) throws IOException {
		final URI v_modelURI = getModelUri();
		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
		if (!matchingLayerEnable()) {
			v_generators.add(new GenerateEntityItfApiSoaLight(v_modelURI, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEnumEntity(v_modelURI, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEntityJdbcSoaLight(v_modelURI, getServerProject(), p_arguments));
			if (needsClientGwt()) {
				v_generators.add(new GenerateClientGwtSoaLight(v_modelURI, getClientGwtProject(), p_arguments));
			}
		} else {
			v_generators.add(new GeneratePersistenceApi(v_modelURI, getServerProject(), p_arguments));
			v_generators.add(new GenerateEnumEntity(v_modelURI, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEntityJdbc(v_modelURI, getServerProject(), p_arguments));
		}
		return v_generators;
	}

	@Override
	public List<String> getProjectsNamesToRefresh() {
		return Arrays.asList(getModelProjectName(), getServerProjectName(), getCommonsProjectName());
	}
	
	@Override
	public List<Boolean> getValuesOfIncompatibleProperties() {
		return Arrays.asList(Boolean.parseBoolean(ProjectProperties.getIsLibraryRs()));
	}
	
	@Override
	public List<SafranGenerator_Enum> getValuesOfCompatibleModels() {
		return Arrays.asList(SafranGenerator_Enum.ENTITY);
	}
}
