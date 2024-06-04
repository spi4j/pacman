//Start of user code copyright
//End of user code

package fr.pacman.soa.ide.ui;

//Start of user code imports
import java.io.File;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.Module;
import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.AcceleoEvaluator;
import org.eclipse.acceleo.aql.evaluation.GenerationResult;
import org.eclipse.acceleo.aql.evaluation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.aql.ide.ui.dialog.AbstractResourceSelectionDialog;
import org.eclipse.acceleo.aql.ide.ui.dialog.FolderSelectionDialog;
import org.eclipse.acceleo.aql.parser.AcceleoParser;
import org.eclipse.acceleo.query.ast.ASTNode;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.ide.runtime.impl.namespace.OSGiQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.dsl.soa.System;
import org.osgi.framework.Bundle;

import fr.pacman.soa.GenerateServerSoaGenerator;

//End of user code

/**
 * Eclipse launcher for fr::pacman::soa::generateServerSoa.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 * @generated
 */
public class GenerateServerSoaGeneratorEclipse extends GenerateServerSoaGenerator {

	/**
	 * The selected value.
	 * 
	 * @generated
	 */
	private final List<EObject> values;

	/**
	 * Constructor.
	 * 
	 * @param selected
	 *                 the selected {@link System}
	 * @generated
	 */
	public GenerateServerSoaGeneratorEclipse(System selected) {
    super(Collections.emptyList(), getTarget(selected));
    this.values = Collections.singletonList(selected);
  }

	/**
	 * Constructor.
	 * 
	 * @param selected
	 *                 the selected {@link IFile}
	 * @generated
	 */
	public GenerateServerSoaGeneratorEclipse(IFile selected) {
    super(Collections.singletonList(selected.getLocation().toString()), getTarget(selected));
    this.values = null;
  }

	/**
	 * @generated
	 */
	@Override
	protected List<EObject> getValues(IQualifiedNameQueryEnvironment queryEnvironment,
			Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels) {
    final List<EObject> res;

    if (values != null) {
      res = values;
    } else {
      res = super.getValues(queryEnvironment, valuesCache, type, resourceSetForModels);
    }

    return res;
    
  }

	/**
	 * @generated
	 */
	@Override
	protected void loadResources(ResourceSet resourceSetForModels, List<String> resources) {
    for (String resource : resources) {
      resourceSetForModels.getResource(URI.createFileURI(resource), true);
    }
  }

	/**
	 * @generated
	 */
	@Override
	protected void standaloneInitialization(ResourceSet resourceSetForModels) {
    // nothing to do here
  }

	/**
	 * @generated
	 */
	@Override
	protected IQualifiedNameResolver createResolver() {
    final String bundleIdentifier = "fr.pacman.soa";
    final Bundle bundle = Platform.getBundle(bundleIdentifier);
    if (bundle == null || bundle.getState() == Bundle.UNINSTALLED) {
      Activator.getDefault().log(new Status(IStatus.ERROR, getClass(), "The Bundle " + bundleIdentifier
          + " must be available in the target platform."));
    }
    return new OSGiQualifiedNameResolver(bundle, AcceleoParser.QUALIFIER_SEPARATOR);
  }

	/**
	 * Gets the target folder for the selected {@link System} or selected
	 * {@link IFile}.
	 * 
	 * @param selected
	 *                 the model {@link System} or selected {@link IFile}
	 * @return the target folder for the selected {@link System} or selected
	 *         {@link IFile}
	 * @generated
	 */
	private static String getTarget(Object selected) {
    final String res;

    final AbstractResourceSelectionDialog dialog = new FolderSelectionDialog(PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getShell(), "Select the destination folder", "");
    final int dialogResult = dialog.open();
    if ((dialogResult == IDialogConstants.OK_ID) && dialog.getFileName() != null && !dialog.getFileName()
        .isEmpty()) {
      final Path location = new Path(dialog.getFileName());
      IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
      if (location.segmentCount() == 1) {
        res = workspaceRoot.getProject(location.segment(0)).getLocation().toFile().getAbsolutePath();
      } else {
        res = workspaceRoot.getFolder(location).getLocation().toFile().getAbsolutePath();
      }
    } else {
      res = null;
    }

    return res;
  }

	/**
	 * @generated
	 */
	@Override
	public void generate() {
    if (target != null) {
      super.generate();
    }
  }

	/**
	 * Prints the diagnostics for the given {@link GenerationResult}.
	 * 
	 * @param generationResult
	 *                         the {@link GenerationResult}
	 * @generated
	 */
	protected void printDiagnostics(GenerationResult generationResult) {
    if (generationResult.getDiagnostic().getSeverity() > Diagnostic.INFO) {
      printDiagnostic(generationResult.getDiagnostic());
    }
  }

	/**
	 * Prints the given {@link Diagnostic} for the given {@link PrintStream}.
	 * 
	 * @param stream
	 *                    the {@link PrintStream}
	 * @param diagnostic
	 *                    the {@link Diagnostic}
	 * @param indentation
	 *                    the current indentation
	 * @generated
	 */
	protected void printDiagnostic(Diagnostic diagnostic) {
    if (diagnostic.getMessage() != null) {
      final String location;
      if (!diagnostic.getData().isEmpty() && diagnostic.getData().get(0) instanceof ASTNode) {
        location = AcceleoUtil.getLocation((ASTNode)diagnostic.getData().get(0)) + ": ";
      } else {
        location = "";
      }
      switch (diagnostic.getSeverity()) {
        case Diagnostic.INFO:
          Activator.getDefault().log(new Status(IStatus.INFO, diagnostic.getSource(),
              location + diagnostic.getMessage(), diagnostic.getException()));
          break;

            case Diagnostic.WARNING:
          Activator.getDefault().log(new Status(IStatus.WARNING, diagnostic.getSource(),
              location + diagnostic.getMessage(), diagnostic.getException()));
          break;

            case Diagnostic.ERROR:
          Activator.getDefault().log(new Status(IStatus.ERROR, diagnostic.getSource(),
              location + diagnostic.getMessage(), diagnostic.getException()));
          break;
      }
    }
    for (Diagnostic child : diagnostic.getChildren()) {
      printDiagnostic(child);
    }
  }

	/**
	 * Before the generation starts.
	 * 
	 * @param evaluator
	 *                             the {@link AcceleoEvaluator}
	 * @param queryEnvironment
	 *                             the {@link IQualifiedNameQueryEnvironment}
	 * @param module
	 *                             the {@link Module}
	 * @param resourceSetForModels
	 *                             the {@link ResourceSet} containing loaded models
	 * @param generationStrategy
	 *                             the {@link IAcceleoGenerationStrategy}
	 * @param destination
	 *                             destination {@link URI}
	 * @param logURI
	 *                             the {@link URI} for logging if nay,
	 *                             <code>null</code> otherwise
	 * @generated NOT
	 */
	@Override
	protected void beforeGeneration(AcceleoEvaluator evaluator, IQualifiedNameQueryEnvironment queryEnvironment,
			Module module, ResourceSet resourceSetForModels, IAcceleoGenerationStrategy strategy, URI destination,
			URI logURI) {

		File modelFile = null;
		if (values != null) {
			for (EObject value : values) {
				final Resource resource = value.eResource();
				resource.getURI();
				final String workspacePath = "/"
						+ resource.getURI().deresolve(URI.createURI("platform:/resource/")).toString();
				final IFile workspaceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(workspacePath));
				modelFile = workspaceFile.getLocation().toFile();
				initializePacmanProperties(modelFile);
				break;
			}
		} else {
			super.beforeGeneration(evaluator, queryEnvironment, module, resourceSetForModels, strategy, destination,
					logURI);
		}
	}

	/**
	 * @generated
	 */
	@Override
	protected void afterGeneration(GenerationResult generationResult) {
    super.afterGeneration(generationResult);

    // refresh if the generated files are in the workspace
    final File targetFolder = new File(target);
    final IContainer targetWorkspaceContainer = ResourcesPlugin.getWorkspace().getRoot()
        .getContainerForLocation(new Path(targetFolder.getAbsolutePath()));
    if (targetWorkspaceContainer != null) {
      try {
        targetWorkspaceContainer.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
      } catch (CoreException e) {
        Activator.getDefault().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "could not refresh "
            + targetWorkspaceContainer.getFullPath(), e));
      }
    }
  }

}
