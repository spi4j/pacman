package fr.pacman.commons.main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.acceleo.Module;
import org.eclipse.acceleo.OpenModeKind;
import org.eclipse.acceleo.Template;
import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.AcceleoEvaluator;
import org.eclipse.acceleo.aql.evaluation.strategy.DefaultGenerationStrategy;
import org.eclipse.acceleo.aql.evaluation.strategy.DefaultWriterFactory;
import org.eclipse.acceleo.aql.evaluation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.aql.evaluation.writer.IAcceleoWriter;
import org.eclipse.acceleo.aql.parser.AcceleoParser;
import org.eclipse.acceleo.aql.parser.ModuleLoader;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.ast.EClassifierTypeLiteral;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.ide.runtime.impl.namespace.OSGiQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.ClassLoaderQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.obeonetwork.dsl.environment.Namespace;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import fr.pacman.commons.ui.PacmanUIGeneratorsReport;

/**
 * 
 * @author MINARM
 */
public abstract class PacmanGenerator_Abs {

	/**
	 * All selected resources (files) for code generation. For now only one resource
	 * is allowed but the system tends to be designed for multiple resources. If the
	 * selection is an EObject, the list is empty.
	 */
	protected List<String> _resources;

	/**
	 * All selected EObjects for code generation. If the selection is a IResource,
	 * the list is empty. .
	 */
	protected List<EObject> _values;

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
	 * @param p_values
	 */
	public void setValues(List<EObject> p_values) {
		_values = p_values;
	}

	/**
	 * Get the list of templates to execute in the selected generator, depending the
	 * type of the selected object.
	 * 
	 * @return the list of templates to execute.
	 */
	protected abstract Map<String, SelectionType_Enum> getTemplates();

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
	 * 
	 * @param queryEnvironment
	 * @param valuesCache
	 * @param type
	 * @param resourceSetForModels
	 * @return
	 */
	protected abstract List<EObject> getValues(IQualifiedNameQueryEnvironment queryEnvironment,
			final Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels);

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
	 * Get the list off all templates to execute for the acceleo module. For now,
	 * don't do anything if the list of templates is empty, a simple message will be
	 * displayed to the user.
	 * 
	 * @param module the acceleo module.
	 * 
	 * @return a list of templates to execute, depending the type of the selected
	 *         resource.
	 */
	protected List<Template> getTemplatesToExecute(Module p_module) {
		List<Template> v_templatesToExecute = new ArrayList<>();
		SelectionType_Enum v_selectionType = getSelectiontTypeForTemplates();

		for (Template v_template : AcceleoUtil.getMainTemplates(p_module)) {
			for (Entry<String, SelectionType_Enum> v_entry : getTemplates().entrySet()) {
				if (v_entry.getKey().equals(v_template.getName())) {
					if (v_entry.getValue() == v_selectionType)
						v_templatesToExecute.add(v_template);
				}
			}
		}
		return v_templatesToExecute;
	}

	/**
	 * Return the internal type for the user selection. Allow to select the right
	 * template from the concerned generator. Default value is ROOT, this value is
	 * notably used for the start project. To be completed when needed.
	 * 
	 * @return the type of the initial selection.
	 */
	private SelectionType_Enum getSelectiontTypeForTemplates() {
		SelectionType_Enum v_selectionType;

		if (null != _resources && !_resources.isEmpty()) {
			v_selectionType = SelectionType_Enum.FILE;
		} else if (null != _values && !_values.isEmpty()) {
			EObject v_obj = _values.get(0);
			if (v_obj instanceof Namespace) {
				v_selectionType = SelectionType_Enum.NAMESPACE;
			} else {
				v_selectionType = SelectionType_Enum.EOBJECT;
			}
		} else {
			v_selectionType = SelectionType_Enum.ROOT;
		}
		return v_selectionType;
	}

	/**
	 * 
	 */
	// TODO si on lance plusieurs générateurs certainement déplacer certaines
	// parties à mutualiser (au niveau UI ?) ailleurs.????
	public void generate() {
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

				final List<EObject> values = getValuesFromInitialSelection(queryEnvironment, valuesCache,
						eClassifierTypeLiteral, resourceSetForModels);

				final String parameterName = template.getParameters().get(0).getName();
				Map<String, Object> variables = new LinkedHashMap<>();
				for (EObject value : values) {
					variables.put(parameterName, value);

					// TODO a finaliser ---------------------------------
					StringBuffer v_strUri = new StringBuffer(_rootPath + File.separator);
					if (null != getSubProjectName() && !getSubProjectName().isEmpty())
						v_strUri.append(getSubProjectName() + File.separator);
					URI targetURI = URI.createFileURI(v_strUri.toString());
					// ---------------------------------------------------
					URI logURI = null;
					AcceleoUtil.generate(template, variables, evaluator, queryEnvironment, strategy, targetURI, logURI);
				}
			}
		} finally {

			AQLUtils.cleanResourceSetForModels(generationKey, resourceSetForModels);
			AcceleoUtil.cleanServices(queryEnvironment, resourceSetForModels);
			PacmanUIGeneratorsReport.addCodeGenerationResult(evaluator.getGenerationResult());
		}
	}

	/**
	 * 
	 * @param queryEnvironment
	 * @param valuesCache
	 * @param type
	 * @param resourceSetForModels
	 * @return
	 */
	private List<EObject> getValuesFromInitialSelection(IQualifiedNameQueryEnvironment queryEnvironment,
			final Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels) {
		if (null != _values && !_values.isEmpty()) {
			return _values;
		} else {
			return getValues(queryEnvironment, valuesCache, type, resourceSetForModels);
		}
	}

	/**
	 * Creates the {@link IQualifiedNameResolver}.
	 * 
	 * @return the created {@link IQualifiedNameResolver}
	 * @generated
	 */
	protected IQualifiedNameResolver createResolver() {
		if (EMFPlugin.IS_OSGI_RUNNING) {
			final Bundle bundle = FrameworkUtil.getBundle(this.getClass());
			return new OSGiQualifiedNameResolver(bundle, AcceleoParser.QUALIFIER_SEPARATOR);
		} else {
			return new ClassLoaderQualifiedNameResolver(this.getClass().getClassLoader(),
					AcceleoParser.QUALIFIER_SEPARATOR);
		}
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

	/**
	 * 
	 * @param resourceSetForModels
	 * @param resources
	 */
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
	 * Internal enumeration for selection types. Namespace and Root are EObject but
	 * they may behave differently. To be completed as needed.
	 */
	public enum SelectionType_Enum {
		FILE, NAMESPACE, ROOT, COMPONENT, EOBJECT, SERVICE
	}
}
