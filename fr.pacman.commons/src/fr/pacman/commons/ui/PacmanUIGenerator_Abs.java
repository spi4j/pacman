package fr.pacman.commons.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.acceleo.aql.parser.AcceleoParser;
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
import org.eclipse.emf.common.util.Logger;
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
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.services.ImportsUtils;
import fr.pacman.commons.services.PlugInUtils;

/**
 * Abstract class for all UI generators
 * 
 * @author MINARM
 */
public abstract class PacmanUIGenerator_Abs {

	/**
	 * 
	 */
	// protected List<EObject> _values;

	/**
	 * The root path for the project. Serves as basis for calculation of all other
	 * paths (generation, refresh...).
	 */
	private File _rootPath;

	/**
	 * The file extension for the model file. The extension of the selected file
	 * allows to know if the file is suitable for the generator.
	 */
	private String _modelExt;

	/**
	 * The selected (source) EObject for code generation. If the selection is a
	 * IResource then the EObject is set to null.
	 */
	private EObject _selectedEObject;

	/**
	 * All necessary resources for the generators.
	 */
	private List<String> _resources = new ArrayList<>();

	/**
	 * Constructor.
	 * 
	 * @param p_selectedResource the selected file resource, here the selected
	 *                           resource for pacman is a model file. Initially,
	 *                           there are exactly four types of resource : files,
	 *                           folders, projects and the workspace root.
	 */
	public PacmanUIGenerator_Abs(IResource p_selectedResource) {
		_resources = Collections.singletonList(p_selectedResource.getLocation().toString());
		_rootPath = new File(p_selectedResource.getLocation().toString()).getParentFile();
		_modelExt = URI.createPlatformResourceURI(((IFile) p_selectedResource).getFullPath().toString(), true)
				.fileExtension();
	}

	/**
	 * Constructor.
	 * 
	 * @param p_selectedEObjects the selected....
	 */
	public PacmanUIGenerator_Abs(EObject p_selectedEObject) {
		_selectedEObject = p_selectedEObject;
	}

	/**
	 * Get the list of all subproject names to be refreshed after code generation.
	 * Allows Eclipse IDE to take new or modified files in account.
	 * 
	 * @return the list of project to refresh after all generations.
	 */
	protected abstract List<String> getSubProjectsToRefresh();

	/**
	 * Get the list of all incompatible properties for code generation. If the
	 * boolean resulted of one of the properties is set to 'true', it means the
	 * property is enabled and the generator cannot be launched.
	 * 
	 * @return the list of incompatible properties for the selected generator.
	 */
	protected abstract List<String> getIncompatibleProperties();

	/**
	 * Get the list of all compatible model files for the code generation. The
	 * generator cannot be launched if the extension of the modeling file is not
	 * correct.
	 * <p>
	 * Tends to become useless now, with the new UI menu layouts associated to
	 * extensions, but preserved as double security.
	 * 
	 * @return the list off all compatible model file extensions.
	 */
	protected abstract List<PacmanUIGenerator_Enum> getCompatibleModels();

	/**
	 * Flag for automatic import management.
	 * 
	 * @return 'true' to activate the automatic import management else 'false'.
	 */
	protected abstract boolean getOrganizeImports();

	/**
	 * Return the list of generators to execute for code generation.
	 * 
	 * @return the list of all generators to execute.
	 */
	protected abstract List<PacmanGenerator_Abs> getGenerators();

	/**
	 * Return the plugin's unique identification under string format.
	 * 
	 * @return the unique plugin identification string.
	 */
	protected abstract String getPluginId();

	/**
	 * Return the specific logger for the plugin
	 * 
	 * @return the logger for the plugin.
	 */
	protected abstract Logger getLogger();

	/**
	 * Main method for the class. Launches all previously registered generators.
	 */
	public void generate() {

		final IRunnableWithProgress v_operation = new IRunnableWithProgress() {
			@Override
			public void run(final IProgressMonitor p_monitor) {

				PacmanPropertiesManager.initProperties(_rootPath.getPath());
				ImportsUtils.resetAdditionalTypes();
				PacmanUIGeneratorsReport.reset();

				if (hasIncompatibleModel() || hasIncompatibleProperties())
					return;

				for (PacmanGenerator_Abs v_generator : getGenerators()) {
					v_generator.setResources(_resources);
					v_generator.setSelectedEObject(_selectedEObject);
					v_generator.setRootPath(_rootPath.getParent());
					v_generator.generate();
				}
			}
		};

		try {
			PlatformUI.getWorkbench().getProgressService().run(true, true, v_operation);

		} catch (final Exception p_e) {
			displayPopUpAlert(p_e);

		} finally {

			try {
				updateIDEAfterCodeGeneration();
				// ErrorGeneration.doIfThrowErrorGenerationException(); Voir si utile doublon
				// etc....
				PacmanUIGeneratorsReport.log(true);
				// ErrorGeneration.clear(); // Voir si utile doublon etc...
				PacmanPropertiesManager.exit();

			} catch (Exception p_e) {
				displayPopUpAlert(p_e);
			}
		}
	}

	/**
	 * 
	 * @param p_e
	 * @param p_statusCode
	 */
	private void displayPopUpAlert(final Exception p_e) {
		String v_msg = p_e.getMessage();
		if (p_e.getCause() != null) {
			v_msg = p_e.getCause().getMessage();
		}
		PlugInUtils.displayError("Pacman", "Une erreur fatale a été détectée lors de la génération : " + p_e
				+ "\n\rCause de l'erreur : " + v_msg + "\n\rLa génération va être stoppée.");
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
	 * Refresh all registered projects after code generation. Allows to take new
	 * created or modified files into account for the Eclipe IDE. If the list of
	 * subprojects is null or empty it means we have to refresh the root project
	 * with all subprojects. Also make the ide organize and complete all imports for
	 * java classes.
	 */
	protected void updateIDEAfterCodeGeneration() throws CoreException {

		List<String> v_subProjectsNamesToRefresh = getSubProjectsToRefresh();
		if (v_subProjectsNamesToRefresh == null || v_subProjectsNamesToRefresh.isEmpty())
			v_subProjectsNamesToRefresh = Collections.singletonList("");

		for (String v_subProjectToRefresh : v_subProjectsNamesToRefresh) {
			final File v_targetFolder = new File(_rootPath.getParent() + File.separator + v_subProjectToRefresh);
			final IContainer v_targetWorkspaceContainer = ResourcesPlugin.getWorkspace().getRoot()
					.getContainerForLocation(new Path(v_targetFolder.getAbsolutePath()));

			if (v_targetWorkspaceContainer != null) {
				try {
					v_targetWorkspaceContainer.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
					final IWorkbenchPartSite v_targetSite = getTargetSite();
					if (Boolean.valueOf(ProjectProperties.getIsFormatImports()) && getOrganizeImports()
							&& v_targetSite != null && PacmanUIGeneratorsReport.getNbFiles() > 0) {
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
	// fille.....A quoi sert cette methode, non appelée ???
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
	 * the selected model file must be present in the list of previously registered
	 * extensions.
	 * 
	 * @return 'true' if any incompatible model file is detected.
	 */
	protected boolean hasIncompatibleModel() {

		if (null == getCompatibleModels() || _resources.isEmpty())
			return false;

		for (PacmanUIGenerator_Enum v_model : getCompatibleModels()) {
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
	 * Get the active (current) workbench window. If no active workbench, take the
	 * first in workbench list.
	 * 
	 * @return The current workbench window.
	 */
	private IWorkbenchWindow getWorkbenchWindow() {
		IWorkbench v_workbench = PlatformUI.getWorkbench();

		if (hasView())
			return v_workbench.getActiveWorkbenchWindow();

		if (v_workbench.getWorkbenchWindowCount() > 0)
			return v_workbench.getWorkbenchWindows()[0];

		return null;
	}

	/**
	 * Get the target site.
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
