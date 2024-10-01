package fr.pacman.commons.main;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.Module;
import org.eclipse.acceleo.OpenModeKind;
import org.eclipse.acceleo.Template;
import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.AcceleoEvaluator;
import org.eclipse.acceleo.aql.evaluation.GenerationResult;
import org.eclipse.acceleo.aql.evaluation.strategy.DefaultGenerationStrategy;
import org.eclipse.acceleo.aql.evaluation.strategy.DefaultWriterFactory;
import org.eclipse.acceleo.aql.evaluation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.aql.evaluation.writer.IAcceleoWriter;
import org.eclipse.acceleo.aql.parser.AcceleoParser;
import org.eclipse.acceleo.aql.parser.ModuleLoader;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.ast.ASTNode;
import org.eclipse.acceleo.query.ast.EClassifierTypeLiteral;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.runtime.impl.namespace.ClassLoaderQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.pacman.commons.ui.PacmanUIGeneratorsReport;

/**
 * 
 * @author MINARM
 */
public abstract class PacmanGenerator_Abs {

	/**
	 * 
	 */
	protected List<String> _resources;

	/**
	 * The selected (source) EObject for code generation. If the selection is a
	 * IResource then the EObject is set to null.
	 */
	protected EObject _selectedEObject;

	/**
	 * The root base path for code generation.
	 */
	private String _rootPath;

	/**
	 * Creates the default {@link ResourceSet}.
	 * 
	 * @return the created default {@link ResourceSet}
	 * @generated
	 */
	protected ResourceSet createDefaultResourceSet() {
		return new ResourceSetImpl();
	}

	/**
	 * Set the list of selected resources for the code generator.
	 * 
	 * @param p_resources the list of selected resources.
	 */
	public void setResources(List<String> p_resources) {
		_resources = p_resources;
	}

	/**
	 * Set the root base path for code generation.
	 * 
	 * @param p_baseTarget the root base path for code generation
	 */
	public void setRootPath(String p_rootPath) {
		_rootPath = p_rootPath;
	}

	/**
	 * 
	 * @param p_selectedEObject
	 */
	public void setSelectedEObject(EObject p_selectedEObject) {
		_selectedEObject = p_selectedEObject;
	}

	/**
	 * Get the list of templates to execute in the selected generator, depending the
	 * type of the selected object.
	 * 
	 * @return the list of templates to execute.
	 */
	protected abstract List<String> getTemplates();

	/**
	 * Get the name for the project specifically impacted by code generation.
	 * 
	 * @return the subproject name.
	 */
	public abstract String getSubProjectName();

	/**
	 * Get the module full qualified name.
	 * 
	 * @return the module qualified name under string format.
	 */
	public abstract String getModuleQualifiedName();

	/**
	 * Get the code generation options.
	 * 
	 * @return a list of options for code generation.
	 */
	protected abstract Map<String, String> getOptions();

	/**
	 * Creates the {@link ResourceSet} for models.
	 * 
	 * @param p_generationKey the generation key
	 * @param p_options       the {@link Map} of options
	 * @param p_exceptions    the {@link List} of exceptions
	 * @param p_resourceSet   the default {@link ResourceSet}
	 * @return the created {@link ResourceSet} for models
	 */
	protected ResourceSet createResourceSetForModel(Object p_generationKey, Map<String, String> p_options,
			List<Exception> p_exceptions, ResourceSet p_resourceSet) {
		return AQLUtils.createResourceSetForModels(p_exceptions, p_generationKey, p_resourceSet, p_options);
	}

	/**
	 * Get the list off all templates to execute for the acceleo module
	 * 
	 * @param module the acceleo module.
	 * 
	 * @return a list of templates to execute, depending the type of the selected
	 *         resource.
	 */
	protected List<Template> getTemplatesToExecute(Module p_module) {
		List<Template> v_templatesToExecute = new ArrayList<>();
		for (Template v_template : AcceleoUtil.getMainTemplates(p_module)) {
			if (getTemplates().contains(v_template.getName())) {
				v_templatesToExecute.add(v_template);
			}
		}
		return v_templatesToExecute;
	}

	protected abstract List<EObject> getValues(IQualifiedNameQueryEnvironment queryEnvironment,
			final Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels);

	/**
	 * 
	 */
	// TODO si on lance plusieurs générateurs certainement déplacer certaines
	// parties à mutualiser (au niveau UI ?) ailleurs.????
	public void generate(final PacmanUIGeneratorsReport p_diagnostics) {
		final String moduleQualifiedName = getModuleQualifiedName();
		final Map<String, String> options = getOptions();
		final Object generationKey = new Object();
		final List<Exception> exceptions = new ArrayList<>();
		final ResourceSet resourceSet = createDefaultResourceSet();
		final ResourceSet resourceSetForModels = createResourceSetForModel(generationKey, options, exceptions,
				resourceSet);
		//
		loadResources(resourceSetForModels, _resources);

		// prepare Acceleo environment
		final IQualifiedNameResolver resolver = createResolver();
		final IQualifiedNameQueryEnvironment queryEnvironment = createAcceleoQueryEnvironment(options, resolver,
				resourceSetForModels);
		AcceleoEvaluator evaluator = createAcceleoEvaluator(resolver, queryEnvironment);
		final IAcceleoGenerationStrategy strategy = createGenerationStrategy(resourceSetForModels);

		final Module module = (Module) resolver.resolve(moduleQualifiedName);
		// final URI logURI = AcceleoUtil.getlogURI(targetURI,
		// options.get(AcceleoUtil.LOG_URI_OPTION));

		try {
			final Map<EClass, List<EObject>> valuesCache = new LinkedHashMap<>();
			for (Template template : getTemplatesToExecute(module)) {
				final EClassifierTypeLiteral eClassifierTypeLiteral = (EClassifierTypeLiteral) template.getParameters()
						.get(0).getType().getAst();
				final List<EObject> values = getValues(queryEnvironment, valuesCache, eClassifierTypeLiteral,
						resourceSetForModels);

				final String parameterName = template.getParameters().get(0).getName();
				Map<String, Object> variables = new LinkedHashMap<>();
				for (EObject value : values) {
					variables.put(parameterName, value);

					// TODO a finaliser ---------------------------------
					StringBuffer v_strUri = new StringBuffer(_rootPath + File.separator);
					if (null != getSubProjectName() && !getSubProjectName().isEmpty())
						v_strUri.append(getSubProjectName() + File.separator);
					URI targetURI = URI.createFileURI(v_strUri.toString());
					System.out.println("Generation de : " + v_strUri.toString());
					// ---------------------------------------------------
					URI logURI = null;
					AcceleoUtil.generate(template, variables, evaluator, queryEnvironment, strategy, targetURI, logURI);
				}
			}
		} finally {

			AQLUtils.cleanResourceSetForModels(generationKey, resourceSetForModels);
			AcceleoUtil.cleanServices(queryEnvironment, resourceSetForModels);
			p_diagnostics.addDiagnostic(evaluator.getGenerationResult());
			// printDiagnostics(evaluator.getGenerationResult());
		}
	}

	/**
	 * Creates the {@link IQualifiedNameResolver}.
	 * 
	 * @return the created {@link IQualifiedNameResolver}
	 * @generated
	 */
	protected IQualifiedNameResolver createResolver() {
		return new ClassLoaderQualifiedNameResolver(this.getClass().getClassLoader(),
				AcceleoParser.QUALIFIER_SEPARATOR);
	}

	/**
	 * 
	 * @param resourceSetForModels
	 * @return
	 */
	protected IAcceleoGenerationStrategy createGenerationStrategy(ResourceSet resourceSetForModels) {
		final IAcceleoGenerationStrategy strategy = new DefaultGenerationStrategy(
				resourceSetForModels.getURIConverter(), new DefaultWriterFactory()) {
			@Override
			public IAcceleoWriter createWriterFor(URI uri, OpenModeKind openMode, Charset charset, String lineDelimiter)
					throws IOException {
				// System.out.println(uri.toString());
				return super.createWriterFor(uri, openMode, charset, lineDelimiter);
			}
		};

		return strategy;
	}

	/**
	 * Registers the given {@link EPackage} in the given
	 * {@link IQualifiedNameQueryEnvironment} recursively.
	 * 
	 * @param environment the {@link IQualifiedNameQueryEnvironment}
	 * @param ePackage    the {@link EPackage}
	 * @generated
	 */
	public static void registerEPackage(IQualifiedNameQueryEnvironment environment, EPackage ePackage) {
		environment.registerEPackage(ePackage);
		for (EPackage child : ePackage.getESubpackages()) {
			registerEPackage(environment, child);
		}
	}

	protected void loadResources(ResourceSet resourceSetForModels, List<String> resources) {
		for (String resource : resources) {
			resourceSetForModels.getResource(URI.createFileURI(resource), true);
		}
	}

	/**
	 * Creates the {@link IQualifiedNameQueryEnvironment}.
	 * 
	 * @param options              the {@link Map} of options
	 * @param resolver             the {@link IQualifiedNameResolver}
	 * @param resourceSetForModels the {@link ResourceSet} for models
	 * @return the created {@link IQualifiedNameQueryEnvironment}
	 * @generated
	 */
	protected IQualifiedNameQueryEnvironment createAcceleoQueryEnvironment(Map<String, String> options,
			IQualifiedNameResolver resolver, ResourceSet resourceSetForModels) {
		final IQualifiedNameQueryEnvironment queryEnvironment = AcceleoUtil.newAcceleoQueryEnvironment(options,
				resolver, resourceSetForModels, false);
		for (String nsURI : new ArrayList<String>(EPackage.Registry.INSTANCE.keySet())) {
			registerEPackage(queryEnvironment, EPackage.Registry.INSTANCE.getEPackage(nsURI));
		}

		return queryEnvironment;
	}

	/**
	 * Creates the {@link AcceleoEvaluator}
	 * 
	 * @param resolver         the {@link IQualifiedNameResolver}
	 * @param queryEnvironment the {@link IQualifiedNameQueryEnvironment}
	 * @return the created {@link AcceleoEvaluator}
	 * @generated
	 */
	protected AcceleoEvaluator createAcceleoEvaluator(IQualifiedNameResolver resolver,
			IQualifiedNameQueryEnvironment queryEnvironment) {
		AcceleoEvaluator evaluator = new AcceleoEvaluator(queryEnvironment.getLookupEngine(), System.lineSeparator());
		resolver.addLoader(new ModuleLoader(new AcceleoParser(), evaluator));
		resolver.addLoader(new JavaLoader(AcceleoParser.QUALIFIER_SEPARATOR, false));

		return evaluator;
	}

	/**
	 * Prints the diagnostics for the given {@link GenerationResult}.
	 * 
	 * @param generationResult the {@link GenerationResult}
	 * @generated
	 */
	protected void printDiagnostics(GenerationResult generationResult) {
		if (generationResult.getDiagnostic().getSeverity() > Diagnostic.INFO) {
			PrintStream stream;
			switch (generationResult.getDiagnostic().getSeverity()) {
			case Diagnostic.WARNING:
				stream = System.out;
				stream.println("WARNING");
				break;
			case Diagnostic.ERROR:
				// Fall-through
			default:
				// Shouldn't happen as we only show warnings and errors
				stream = System.err;
				stream.println("ERROR");
				break;
			}
			printDiagnostic(stream, generationResult.getDiagnostic(), "");
		}
	}

	/**
	 * Prints the given {@link Diagnostic} for the given {@link PrintStream}.
	 * 
	 * @param stream      the {@link PrintStream}
	 * @param diagnostic  the {@link Diagnostic}
	 * @param indentation the current indentation
	 * @generated
	 */
	protected void printDiagnostic(PrintStream stream, Diagnostic diagnostic, String indentation) {
		String nextIndentation = indentation;
		if (diagnostic.getMessage() != null) {
			stream.print(indentation);
			switch (diagnostic.getSeverity()) {
			case Diagnostic.INFO:
				stream.print("INFO ");
				break;

			case Diagnostic.WARNING:
				stream.print("WARNING ");
				break;

			case Diagnostic.ERROR:
				stream.print("ERROR ");
				break;
			}
			if (!diagnostic.getData().isEmpty() && diagnostic.getData().get(0) instanceof ASTNode) {
				stream.print(AcceleoUtil.getLocation((ASTNode) diagnostic.getData().get(0)));
			}
			stream.println(": " + diagnostic.getMessage());
			nextIndentation += "\t";
		}
		for (Diagnostic child : diagnostic.getChildren()) {
			printDiagnostic(stream, child, nextIndentation);
		}
	}
}
