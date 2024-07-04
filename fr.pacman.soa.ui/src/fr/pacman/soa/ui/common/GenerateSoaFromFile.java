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
package fr.pacman.soa.ui.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Enum;
import fr.pacman.soa.GenerateClientGwtSoa;
import fr.pacman.soa.GenerateCommonSoa;
import fr.pacman.soa.GenerateEnumDto;
import fr.pacman.soa.GenerateServerSoa;
import fr.pacman.soalight.GenerateClientGwtSoaLight;
import fr.pacman.soalight.GenerateCommonSoaLight;
import fr.pacman.soalight.GenerateServerSoaLight;

/**
 * Main entry point of the 'SOA' generation module.
 */
public class GenerateSoaFromFile extends SafranGenerator_Abs<IFile> {

	/**
	 * Constructeur.
	 * 
	 * @param p_modelURI  le modèle
	 * @param p_arguments les arguments de génération
	 */
	public GenerateSoaFromFile(final IFile p_modelURI, final List<? extends Object> p_arguments) {
		super(p_modelURI, p_arguments);
	}

	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(final IFile p_file,
			final List<? extends Object> p_arguments) throws IOException {

		final URI v_modelURI = getModelUri();

		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();

		// pour la generation côté serveur on a besoin de connaïtre les requirements
		// liées aux services, donc on va charger les fichiers .requirement
		if (matchingLayerEnable()) {
			v_generators
					.add(new GenerateServerSoa(initializeModelContents(v_modelURI), getServerProject(), p_arguments));
			v_generators.add(new GenerateCommonSoa(v_modelURI, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEnumDto(v_modelURI, getCommonsProject(), p_arguments));
			if (needsClientGwt()) {
				v_generators.add(new GenerateClientGwtSoa(v_modelURI, getClientGwtProject(), p_arguments));
			}
		} else {
			v_generators.add(
					new GenerateServerSoaLight(initializeModelContents(v_modelURI), getServerProject(), p_arguments));
			v_generators.add(new GenerateCommonSoaLight(v_modelURI, getCommonsProject(), p_arguments));
			v_generators.add(new GenerateEnumDto(v_modelURI, getCommonsProject(), p_arguments));
			if (needsClientGwt()) {
				v_generators.add(new GenerateClientGwtSoaLight(v_modelURI, getClientGwtProject(), p_arguments));
			}
		}
		return v_generators;
	}

	@Override
	protected List<String> getProjectsNamesToRefresh() {
		final List<String> v_result = new ArrayList<String>();
		v_result.add(getModelProjectName());
		v_result.add(getServerProjectName());
		v_result.add(getCommonsProjectName());
		if (needsClientGwt()) {
			v_result.add(getClientGwtProjectName());
		}
		return v_result;
	}

	/**
	 * Initialise le contenu du modele en chargeant des ressources supplementaires
	 * (les .requirement).
	 * 
	 * @param p_modelURI l'URI du modele de base de la generation
	 * @return la racine du modèle
	 */
	private EObject initializeModelContents(final URI p_modelURI) {
		/*
		 * Cette méthode va charger toutes les ressources nécessaires dans un même
		 * ResourceSet
		 */
		final ResourceSet v_resourceSet = new ResourceSetImpl();

		final Resource v_mainModel = v_resourceSet.getResource(p_modelURI, true);
		for (final String v_reqUri : getRequirements()) {
			final URI v_requirementsURI = URI.createPlatformResourceURI(v_reqUri, true);
			v_resourceSet.getResource(v_requirementsURI, true);
		}
		return v_mainModel.getContents().get(0);
	}

	/**
	 * Recherche des fichiers .requirement a cote du modele.
	 * 
	 * @return les fichiers requirement dans le meme dossier que le modele utilise
	 *         pour la generation
	 */
	private List<String> getRequirements() {
		final List<String> v_propertiesFilesPath = new ArrayList<String>();

		// We get the folder containing the model
		final IWorkspace v_wksp = ResourcesPlugin.getWorkspace();
		final IWorkspaceRoot v_root = v_wksp.getRoot();
		final IFile v_modelFile = v_root.getFile(new Path(getModelUri().toPlatformString(true)));
		String v_fullPath = v_modelFile.getFullPath().toString();
		String[] v_fullPathSplit = v_fullPath.split("/");
		IContainer v_container = v_modelFile.getParent();
		while (!v_container.getName().equals(v_fullPathSplit[1])) {
			v_container = v_container.getParent();
		}

		// Now we look at the children to list the ".requirement" files
		String v_req = null;
		try {
			IResource[] v_siblings = v_container.members();
			for (final IResource v_sibling : v_siblings) {
				v_req = recupRequirementModel(v_sibling);
				if (v_req != null)
					break;
			}
		} catch (final CoreException v_e) {
			// do nothing
			return v_propertiesFilesPath;
		}

		if (v_req != null)
			v_propertiesFilesPath.add(v_req);
		return v_propertiesFilesPath;
	}

	/**
	 * 
	 * @param p_resource
	 * @return
	 */
	private String recupRequirementModel(IResource p_resource) {
		if (p_resource.getType() == IResource.FILE) {
			if ("requirement".equals(p_resource.getFileExtension())) {
				return p_resource.getFullPath().toString();
			}
		} else if (p_resource.getType() == IResource.FOLDER) {
			try {
				IResource[] v_siblings = ((IContainer) p_resource).members();
				for (final IResource v_sibling : v_siblings) {
					return recupRequirementModel(v_sibling);
				}
			} catch (final CoreException v_e) {
				// do nothing
				return null;
			}
		}

		return null;
	}

	@Override
	protected List<Boolean> getValuesOfIncompatibleProperties() {
		return Arrays.asList(Boolean.parseBoolean(ProjectProperties.getIsLibraryRs()));
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
