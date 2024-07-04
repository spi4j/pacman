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

import org.eclipse.acceleo.common.utils.ModelUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.obeonetwork.dsl.soa.System;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.services.PlugInUtils;
import fr.pacman.commons.services.StringUtils;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.commons.ui.SafranGenerator_Enum;
import fr.pacman.soa.GenerateClientGwtSoa;
import fr.pacman.soa.GenerateCommonSoa;
import fr.pacman.soa.GenerateServerSoa;
import fr.pacman.soalight.GenerateClientGwtSoaLight;
import fr.pacman.soalight.GenerateCommonSoaLight;
import fr.pacman.soalight.GenerateServerSoaLight;

/**
 * Main entry point of the 'SOA' generation module.
 */
public class GenerateSoaFromEObject extends SafranGenerator_Abs<EObject> {

	/**
	 * Constructeur.
	 * 
	 * @param p_object
	 *            le modele
	 * @param p_arguments
	 *            les arguments de generation
	 */
	public GenerateSoaFromEObject(final EObject p_object, final List<? extends Object> p_arguments) {
		super(p_object, p_arguments);
	}

	@Override
	public void doGenerate(final IProgressMonitor p_monitor) throws IOException {
		// Verification que l'objet du modele en entree contient bien un System
		if (getParent(_input, System.class) == null) {
			PlugInUtils.displayInformation("Attention",
					"Il est conseillé de lancer la génération partielle à partir du fichier ois (qui contient la racine de la modélisation SOA)");
		}
		super.doGenerate(p_monitor);
	}

	@Override
	protected List<PacmanGenerator_Abs> getPacmanGenerators(final EObject p_input,
			final List<? extends Object> p_arguments) throws IOException {
		final List<PacmanGenerator_Abs> v_generators = new ArrayList<PacmanGenerator_Abs>();
		// pour la génération serveur on a besoin de connaitre les
		// requirements liées aux services, donc on va charger les fichiers
		// .requirement
		if (matchingLayerEnable()) {
			v_generators.add(new GenerateServerSoa(initializeModelContents(p_input), getServerProject(), p_arguments));
			v_generators.add(new GenerateCommonSoa(p_input, getCommonsProject(), p_arguments));
			if (needsClientGwt()) {
				v_generators.add(new GenerateClientGwtSoa(p_input, getClientGwtProject(), p_arguments));
			}
		} else {

			v_generators
					.add(new GenerateServerSoaLight(initializeModelContents(p_input), getServerProject(), p_arguments));
			v_generators.add(new GenerateCommonSoaLight(p_input, getCommonsProject(), p_arguments));
			if (needsClientGwt()) {
				v_generators.add(new GenerateClientGwtSoaLight(p_input, getClientGwtProject(), p_arguments));
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
	 * @param p_input
	 *            l'URI du modele de base de la generation
	 * @return la racine du modele
	 */
	private EObject initializeModelContents(final EObject p_input) {
		/*
		 * Cette methode va charger toutes les ressources necessaires dans un meme
		 * ResourceSet
		 */
		final ResourceSet v_resourceSet = new ResourceSetImpl();
		final Resource v_mainModel = ModelUtils.attachResource(StringUtils.getUriFromEObject(p_input), v_resourceSet,
				p_input);
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
		final List<String> v_requirementsFilesPath = new ArrayList<String>();

		// We get the folder containing the model
		final IWorkspace v_wksp = ResourcesPlugin.getWorkspace();
		final IWorkspaceRoot v_root = v_wksp.getRoot();
		final IFile v_modelFile = v_root.getFile(new Path(getModelUri().toPlatformString(true)));
		IContainer v_container = v_modelFile.getParent();

		for (int v_i = 0; v_container != null && v_i < 3; v_i++) {
			addRequirementsFromContainer(v_container, v_requirementsFilesPath);
			v_container = v_container.getParent();
		}

		return v_requirementsFilesPath;
	}

	/**
	 * Recherche des fichiers .requirement et ajoute leur chemin dans la liste
	 * 
	 * @param p_container
	 *            le répertoire de recherche
	 * @param p_requirementsFilesPath
	 *            la liste dans laquelle ajouter le chemin des fichies .requirement
	 */
	private void addRequirementsFromContainer(final IContainer p_container,
			final List<String> p_requirementsFilesPath) {
		// Now we look at the children to list the ".requirement" files
		String v_req = null;
		try {
			IResource[] v_siblings = p_container.members();
			for (final IResource v_sibling : v_siblings) {
				v_req = recupRequirementModel(v_sibling);
				if (v_req != null) {
					p_requirementsFilesPath.add(v_req);
					break;
				}
			}
		} catch (final CoreException v_e) {
			// do nothing
			return;
		}
	}

	/*
	 * @param p_reosurce
	 * 
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
