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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.entity.Root;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.services.PlugInUtils;
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
public class GenerateJdbcUIFromEObject extends SafranGenerator_Abs<EObject> {

	/**
	 * Constructeur.
	 * 
	 * @param p_object
	 *            le modèle
	 * @param p_arguments
	 *            les arguments de génération
	 */
	public GenerateJdbcUIFromEObject(final EObject p_object, final List<? extends Object> p_arguments) {
		super(p_object, p_arguments);
	}

	@Override
	public void doGenerate(final IProgressMonitor p_monitor) throws IOException {
		// Vérification que l'objet du modèle en entrée contient bien un Root
		if (getParent(_input, Root.class) == null) {
			PlugInUtils.displayInformation("Attention",
					"Il est conseillé de lancer la génération partielle à partir du fichier ois (qui contient la racine de la modélisation Entity)");
		}
		super.doGenerate(p_monitor);
	}

	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(final EObject p_input,
			final List<? extends Object> p_arguments) throws IOException {
		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
		if (!matchingLayerEnable()) {
			v_generators.add(new GenerateEntityItfApiSoaLight(p_input, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEnumEntity(p_input, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEntityJdbcSoaLight(p_input, getServerProject(), p_arguments));
			if (needsClientGwt()) {
				v_generators.add(new GenerateClientGwtSoaLight(p_input, getClientGwtProject(), p_arguments));
			}
		} else {
			v_generators.add(new GeneratePersistenceApi(p_input, getServerProject(), p_arguments));
			v_generators.add(new GenerateEnumEntity(p_input, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEntityJdbc(p_input, getServerProject(), p_arguments));
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
