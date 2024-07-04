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
package fr.pacman.commons.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.actions.OrganizeImportsAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.services.StringUtils;

/**
 * Entity code generation.
 * 
 * @param <Type> le type d'element attendu en entree
 */
public abstract class AcceleoGenerateSafranAction<Type> extends ActionDelegate implements IActionDelegate {

	/**
	 * Selected model files.
	 */
	protected List<Type> _selection;

	/**
	 * La vue (si souhaitée par le plugin).
	 */
	protected IViewPart _view;

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.actions.ActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
	 *      org.eclipse.jface.viewers.ISelection)
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void selectionChanged(final IAction p_action, final ISelection p_selection) {
		if (p_selection instanceof IStructuredSelection) {
			_selection = ((IStructuredSelection) p_selection).toList();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
	 * @generated
	 */
	@Override
	public void run(final IAction p_action) {
		if (_selection != null) {
			// Gestion de la vue si besoin
			if (hasView()) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(getViewId());
				} catch (PartInitException v_e) {
					final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
					getLogger().log(v_status);
				}

				_view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(getViewId());
			}

			final IRunnableWithProgress v_operation = new IRunnableWithProgress() {
				@Override
				public void run(final IProgressMonitor p_monitor) {
					try {
						// vider le cache des propriétés
						// PacmanPropertiesManager.clearProperties();
						for (final Type v_node : _selection) {
							final SafranGenerator_Abs<Type> v_generator;
							final IProject v_modelProject = getProjectFromNode(v_node);
							v_generator = getSafranGenerator(v_node, getArguments());

							if (hasView() && v_modelProject != null) {
								updateView(v_modelProject);
							}

							if (v_generator != null) {
								try {
									v_generator.doGenerate(p_monitor);
								} catch (final IOException v_e) {
									final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(),
											v_e);
									getLogger().log(v_status);
								} finally {
									if (v_modelProject != null) {
										final IWorkbenchPartSite v_targetSite = getTargetSite();

										// Recuperation de la liste des projets a rafraichir.
										List<String> v_projectsNamesToRefresh = v_generator.getProjectsNamesToRefresh();
										// Toujours le projet '-common' avant les autres pour les imports (si present).
										Collections.sort(v_projectsNamesToRefresh);

										for (final String v_projectName : v_projectsNamesToRefresh) {
											getLogger().log(new Status(Status.INFO, getPluginId(),
													"Projet à rafraichir : " + v_projectName));
											final IWorkspace v_workspace = v_modelProject.getWorkspace();
											if (v_workspace != null) {
												final IProject v_projectToRefresh = v_workspace.getRoot()
														.getProject(v_projectName);
												if (v_projectToRefresh.exists()) {
													v_projectToRefresh.refreshLocal(IResource.DEPTH_INFINITE,
															p_monitor);

													// Lance la reorganisation des imports.
													if (null != v_targetSite
															&& Boolean.valueOf(ProjectProperties.getIsFormatImports())
															&& v_generator.getOrganizeImports())
														organizeImports(v_projectToRefresh, v_targetSite);
												}
											}
										}
									}
								}
							}
						}
					} catch (final CoreException v_e) {
						final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
						getLogger().log(v_status);
					}
				}
			};

			try {
				PlatformUI.getWorkbench().getProgressService().run(true, true, v_operation);
				System.out.println("fin");

			} catch (final InvocationTargetException v_e) {
				final IStatus v_status;
				// Si pas de message
				if (v_e.getMessage() == null || v_e.getMessage().isEmpty()) {
					// Instancier le status avec le message de la cause
					v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getCause().getMessage(), v_e.getCause());
				}
				// Si on a un message
				else {
					// Instancier le status avec le message de l'erreur
					v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
				}
				getLogger().log(v_status);
			} catch (final InterruptedException v_e) {
				final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
				getLogger().log(v_status);
			}
		}
	}

	/**
	 * Recherche le projet contenant le modèle en entrée
	 * 
	 * @param p_node le noeud correspondant à l'entrée de la génération
	 * @return le projet contenant le modèle
	 */
	protected IProject getProjectFromNode(final Type p_node) {
		// on a fait un clic droit sur le fichier
		if (p_node instanceof IFile) {
			final IFile v_model = (IFile) p_node;
			return v_model.getProject();
		}
		// on a fait un clic droit sur un noeud du modèle
		else if (p_node instanceof EObject) {
			final EObject v_obj = (EObject) p_node;
			final IPath v_path = new Path(StringUtils.getUriFromEObject(v_obj).toPlatformString(true));
			final IWorkspace v_wksp = ResourcesPlugin.getWorkspace();
			final IWorkspaceRoot v_root = v_wksp.getRoot();
			final IResource v_model = v_root.getFile(v_path);
			return v_model.getProject();
		} else {
			final IStatus v_status = new Status(IStatus.WARNING, getPluginId(), "Type inconnu : " + p_node.getClass());
			getLogger().log(v_status);
		}
		return null;
	}

	/**
	 * Organize imports (CTRL + SHIFT + O) over all generated Java classes for
	 * refreshed projects.
	 * 
	 * @param p_project
	 * @param p_targetSite
	 * @throws CoreException
	 */
	private void organizeImports(final IProject p_project, final IWorkbenchSite p_targetSite) throws CoreException {
		Runnable v_job = new Runnable() {
			@Override
			public void run() {
				OrganizeImportsAction v_org = new OrganizeImportsAction(p_targetSite);
				try {
					IJavaProject v_prj = null;
					if (p_project.exists() && p_project.hasNature(JavaCore.NATURE_ID)) {
						v_prj = JavaCore.create(p_project);
						IStructuredSelection v_selection = new StructuredSelection(v_prj);
						v_org.run(v_selection);
					}
				} catch (CoreException ce) {
					ce.printStackTrace();
				}
			}
		};
		getWorkbenchWindow().getShell().getDisplay().syncExec(v_job);
	}

	/**
	 * Get the active (current) workbench window.
	 * 
	 * @return The current workbench window.
	 */
	private IWorkbenchWindow getWorkbenchWindow() {
		IWorkbench v_workbench = PlatformUI.getWorkbench();

		// Try to get the active workbench.
		if (hasView())
			return v_workbench.getActiveWorkbenchWindow();

		// If no active workbench, take the first in workbench list.
		if (v_workbench.getWorkbenchWindowCount() > 0)
			return v_workbench.getWorkbenchWindows()[0];

		return null;
	}

	/**
	 * Get the target site for import organization.
	 * 
	 * @return The target site.
	 */
	private IWorkbenchPartSite getTargetSite() {
		IWorkbenchPartSite v_targetSite = null;
		IWorkbenchWindow v_workbenchWindow = getWorkbenchWindow();

		if (null != getWorkbenchWindow())
			v_targetSite = v_workbenchWindow.getPartService().getActivePart().getSite();

		return v_targetSite;
	}

	/**
	 * Computes the arguments of the generator.
	 * 
	 * @return the arguments
	 * @generated
	 */
	protected List<? extends Object> getArguments() {
		return new ArrayList<String>();
	}

	/**
	 * Booléen indiquant si ce plugin gère une vue Eclipse (ex : vue de problèmes).
	 * 
	 * @return retourne false par défaut, mais peut être surchargé.
	 */
	protected boolean hasView() {
		return false;
	}

	/**
	 * @return l'id de la vue à remplir (null par défaut, car pas de vue)
	 */
	protected String getViewId() {
		return null;
	}

	/**
	 * Met à jour la vue.
	 * 
	 * @param p_project le projet du modèle
	 */
	protected void updateView(final IProject p_project) {
		// Pas de vue par défaut
	}

	/**
	 * @return l'id du plugin
	 */
	protected abstract String getPluginId();

	/**
	 * @return le logger du plugin
	 */
	protected abstract ILog getLogger();

	/**
	 * Retourne le générateur utilisé par ce plugin
	 * 
	 * @param p_modelURI  le modèle
	 * @param p_arguments les arguments de la génération
	 * @return le générateur utilisé par ce plugin
	 */
	protected abstract SafranGenerator_Abs<Type> getSafranGenerator(Type p_modelURI,
			List<? extends Object> p_arguments);

}
