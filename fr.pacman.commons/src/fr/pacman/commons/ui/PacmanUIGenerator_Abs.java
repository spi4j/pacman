package fr.pacman.commons.ui;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.GenerationResult;
import org.eclipse.acceleo.aql.parser.AcceleoParser;
import org.eclipse.acceleo.query.ast.ASTNode;
import org.eclipse.acceleo.query.ide.runtime.impl.namespace.OSGiQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.actions.OrganizeImportsAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import fr.pacman.commons.Activator;
import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.errorgen.ErrorGeneration;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.services.ImportsUtils;
import fr.pacman.commons.services.PlugInUtils;

/**
 * 
 * @author MINARM
 */
public abstract class PacmanUIGenerator_Abs {

	/**
	 * 
	 */
	// protected List<EObject> _values;

	/**
	 * The root path for the project. Serves as a basis for calculation of all other
	 * paths (generation, refresh...).
	 */
	private File _basePath;

	/**
	 * The file extension for the model file.
	 */
	// TODO passer en liste..
	private String _modelExt;

	/**
	 * 
	 */
	private List<String> _resources = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param p_selectedResource the selected file resource, here the selected
	 *                           resource is a model file.
	 */
	public PacmanUIGenerator_Abs(IResource p_selectedResource) {

		_resources = Collections.singletonList(p_selectedResource.getLocation().toString());
		_basePath = new File(p_selectedResource.getLocation().toString()).getParentFile();
		_modelExt = URI.createPlatformResourceURI(((IFile) p_selectedResource).getFullPath().toString(), true)
				.fileExtension();
	}

	/**
	 * Constructor.
	 * 
	 * @param p_selectedEObjects
	 */
	public PacmanUIGenerator_Abs(List<EObject> p_selectedEObjects) {

	}

	/**
	 * Get the list of all project names to be refreshed after code generation.
	 * 
	 * @return the list of project to refresh after all generations.
	 */
	protected abstract List<String> getProjectsToRefresh();

	/**
	 * Get the list of incompatible properties for the code generation. If the
	 * boolean result of one of the properties is 'true', it means the property is
	 * enabled and the generator cannot be launched.
	 * 
	 * @return the list of incompatible properties for the code generation.
	 */
	protected abstract List<String> getIncompatibleProperties();

	/**
	 * Get the list of all compatible model files for the code generation. The
	 * generator cannot be launched if the extension of the modeling file is not the
	 * correct one.
	 * 
	 * @return the list off all compatible model files extensions.
	 */
	protected abstract List<SafranGenerator_Enum> getCompatibleModels();

	/**
	 * Return the list of generators to execute for code generation.
	 * 
	 * @return the list of all generators to execute.
	 */
	protected abstract List<PacmanGenerator_Abs> getGenerators();

	/**
	 * Return the plugin identification under string format.
	 * 
	 * @return the unique plugin identification string.
	 */
	protected abstract String getPluginId();

	/**
	 * Main method for the class. Launches all previously registered generators.
	 */
	public void generate() {

		// TODO : A compléter....
		final IRunnableWithProgress v_operation = new IRunnableWithProgress() {

			@Override
			public void run(final IProgressMonitor p_monitor) {
				try {

					// Clears any errors and warnings in the error viewing tab ( "Error Log").
					ErrorGeneration.clear();

					// Loading all '.properties' files for the generator.
					PacmanPropertiesManager.initProperties(_basePath.getPath());

					// Resetting some services.
					ImportsUtils.resetAdditionalTypes();

					// Checking resource compatibility and build properties.
					if (hasIncompatibleModel() || hasIncompatibleProperties())
						return;

					try {
						// Launch each registered generator.
						for (PacmanGenerator_Abs v_generator : getGenerators()) {
							v_generator.setResources(_resources);
							v_generator.setBaseTarget(_basePath.getParent());
							v_generator.generate();
						}
					} catch (final Exception p_e) {
//	                           final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), p_e.getMessage(), p_e);
//	                           getLogger().log(v_status);

					} finally {
						// printDiagnostics(evaluator.getGenerationResult());

						// Refresh (and organize) imports and refresh all impacted projects.
						refreshProjects();

						// Exit for the properties manager (if the generation launched).
						PacmanPropertiesManager.exit();

						// Add possible errors in the "Error Log".
						ErrorGeneration.doIfThrowErrorGenerationException();
					}

				} catch (final CoreException p_e) {
//			           final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), p_e);
//		                  getLogger().log(v_status);
				}
			}
		};

		try {

			PlatformUI.getWorkbench().getProgressService().run(true, true, v_operation);

		} catch (final InvocationTargetException v_e) {

		} catch (final InterruptedException v_e) {

		}
	}

	/**
	 * 
	 * @param generationResult
	 */
	protected void printDiagnostics(GenerationResult generationResult) {
		if (generationResult.getDiagnostic().getSeverity() > Diagnostic.INFO) {
			printDiagnostic(generationResult.getDiagnostic());
		}
	}

	/**
	 * 
	 * @param diagnostic
	 */
	protected void printDiagnostic(Diagnostic diagnostic) {
		if (diagnostic.getMessage() != null) {
			final String location;
			if (!diagnostic.getData().isEmpty() && diagnostic.getData().get(0) instanceof ASTNode) {
				location = AcceleoUtil.getLocation((ASTNode) diagnostic.getData().get(0)) + ": ";
			} else {
				location = "";
			}
			switch (diagnostic.getSeverity()) {
			case Diagnostic.INFO:
				Activator.getDefault().getLog().log(new Status(IStatus.INFO, diagnostic.getSource(),
						location + diagnostic.getMessage(), diagnostic.getException()));
				break;

			case Diagnostic.WARNING:
				Activator.getDefault().getLog().log(new Status(IStatus.WARNING, diagnostic.getSource(),
						location + diagnostic.getMessage(), diagnostic.getException()));
				break;

			case Diagnostic.ERROR:
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, diagnostic.getSource(),
						location + diagnostic.getMessage(), diagnostic.getException()));
				break;
			}
		}
		for (Diagnostic child : diagnostic.getChildren()) {
			printDiagnostic(child);
		}
	}

//	protected boolean beforeGeneration(AcceleoEvaluator evaluator, IQualifiedNameQueryEnvironment queryEnvironment,
//			Module module, ResourceSet resourceSetForModels, IAcceleoGenerationStrategy strategy, URI destination,
//			URI logURI) {
//
////		File modelFile = null;
////		if (values != null) {
////			for (EObject value : values) {
////				final Resource resource = value.eResource();
////				resource.getURI();
////				final String workspacePath = "/"
////						+ resource.getURI().deresolve(URI.createURI("platform:/resource/")).toString();
////				final IFile workspaceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath));
////				modelFile = workspaceFile.getLocation().toFile();
////				initializePacmanProperties(modelFile);
////				break;
////			}
////		} else {
////			// super.beforeGeneration(evaluator, queryEnvironment, module,
////			// resourceSetForModels, strategy, destination,
////			// logURI);
////
////			// File modelFile = null;
////			for (String resource : _resources) {
////				modelFile = new File(resource);
////				break;
////			}
////			initializePacmanProperties(modelFile);
////		}
//	}

	/**
	 * After code generation, refresh all registered projects. Allows to take new
	 * files into account for the Eclipe IDE.
	 */
	protected void refreshProjects() throws CoreException {

		List<String> v_projectsNamesToRefresh = getProjectsToRefresh();
		Collections.sort(v_projectsNamesToRefresh);

		for (String v_projectToRefresh : v_projectsNamesToRefresh) {

			final File v_targetFolder = new File(_basePath.getParent() + File.separator + v_projectToRefresh);
			final IContainer v_targetWorkspaceContainer = ResourcesPlugin.getWorkspace().getRoot()
					.getContainerForLocation(new Path(v_targetFolder.getAbsolutePath()));

			if (v_targetWorkspaceContainer != null) {
				try {
					v_targetWorkspaceContainer.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
					final IWorkbenchPartSite v_targetSite = getTargetSite();
					if (Boolean.valueOf(ProjectProperties.getIsFormatImports()) && v_targetSite != null) {
						refreshImports(v_targetWorkspaceContainer.getProject(), v_targetSite);
					}
				} catch (CoreException p_e) {
					Activator.getDefault().getLog().log(new Status(IStatus.ERROR, getPluginId(),
							"Impossible de rafraîchir " + v_targetWorkspaceContainer.getFullPath(), p_e));
				}
			}
		}
	}

	// TODO attention au bundleIdentifier qui doit redescendre dans la classe
	// fille.....
	protected IQualifiedNameResolver createResolver() {
		final String bundleIdentifier = "fr.pacman.soa";
		final Bundle bundle = Platform.getBundle(bundleIdentifier);
		if (bundle == null || bundle.getState() == Bundle.UNINSTALLED) {
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, getClass(),
					"The Bundle " + bundleIdentifier + " must be available in the target platform."));
		}
		return new OSGiQualifiedNameResolver(bundle, AcceleoParser.QUALIFIER_SEPARATOR);
	}

	/**
	 * Check if the selected model file has the good extension. The extension for
	 * the selected model file must be must be present in the list of previously
	 * registered extensions
	 * 
	 * @return 'true' if any incompatible model file is detected.
	 */
	// TODO : passer en liste.
	protected boolean hasIncompatibleModel() {

		if (null == getCompatibleModels() || _resources.isEmpty())
			return false;

		for (SafranGenerator_Enum v_model : getCompatibleModels()) {
			if (v_model.get_value().equals(_modelExt))
				return false;
		}

		PlugInUtils.displayError("Pacman", "Le fichier ou l'objet de modélisation "
				+ "ne permet pas l'utilisation de ce générateur. \n\r" + "La génération va être stoppée.");

		return true;
	}

	/**
	 * Check if all properties of the selected project are compatibles. The UI
	 * generator send the list of boolean values ​​for all incompatible properties,
	 * if a single boolean has the value 'true' it means the property was activated
	 * for the project.
	 * 
	 * @return 'true' if any boolean value of incompatible property is detected.
	 */
	protected boolean hasIncompatibleProperties() {

		if (null == getIncompatibleProperties())
			return false;

		for (String v_valueOfProperty : getIncompatibleProperties()) {
			if (Boolean.valueOf(v_valueOfProperty)) {
				PlugInUtils.displayError("Pacman",
						"Les options prises lors de la création "
								+ "de ce projet ne permettent pas l'utilisation de ce générateur. \n\r"
								+ "La génération va être stoppée.");
				return true;
			}
		}
		return false;
	}

	/**
	 * Organize imports (CTRL + SHIFT + O) over all generated Java classes for
	 * refreshed projects.
	 * 
	 * @param p_project
	 * @param p_targetSite
	 * @throws CoreException
	 */
	private void refreshImports(final IProject p_project, final IWorkbenchSite p_targetSite) throws CoreException {
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
	 * Get the target site for refresh import.
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
	 * Return 'true' if the plugin must handle an Eclipse view (ErrorLog, Problems,
	 * etc.. )
	 * 
	 * @return 'false' by default, but can be overridden.
	 */
	// TODO a compléter.
	protected boolean hasView() {

		return false;
	}
}
