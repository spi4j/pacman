package fr.pacman.soa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.acceleo.Module;
import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.AcceleoEvaluator;
import org.eclipse.acceleo.aql.evaluation.writer.DefaultGenerationStrategy;
import org.eclipse.acceleo.aql.evaluation.writer.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.aql.parser.AcceleoParser;
import org.eclipse.acceleo.aql.parser.ModuleLoader;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.impl.namespace.ClassLoaderQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.obeonetwork.dsl.entity.EntityPackage;
import org.obeonetwork.dsl.entity.util.EntityResourceFactoryImpl;
import org.obeonetwork.dsl.overview.OverviewPackage;
import org.obeonetwork.dsl.overview.util.OverviewResourceFactoryImpl;
import org.obeonetwork.dsl.requirement.RequirementPackage;
import org.obeonetwork.dsl.requirement.util.RequirementResourceFactoryImpl;
import org.obeonetwork.dsl.soa.SoaPackage;
import org.obeonetwork.dsl.soa.util.SoaResourceFactoryImpl;

import fr.pacman.commons.errorgen.ErrorGeneration;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.services.ImportsUtils;

public class MainAcceleo4ServerSoa {

	public static void main(String[] args) throws IOException {
		// inputs
		final String moduleQualifiedName = "fr::pacman::soa::generateServerSoa";
		final File modelFile = new File(
				"/home/development/bin/eclipse-2022-12-Acceleo/runtime-New_configuration/test/test-model/E-BookStore.soa");
		final String outputFolder = "/home/development/bin/eclipse-2022-12-Acceleo/runtime-New_configuration/test/test-server/acceleo4";

		// for testing purpose. force regeneration of protected areas
		// TODO remove for production
		File destinaltionFolder = new File(
				"/home/development/bin/eclipse-2022-12-Acceleo/runtime-New_configuration/test/test-server/src");
		if (destinaltionFolder.exists()) {
			Files.walk(destinaltionFolder.toPath())
					.sorted(Comparator.reverseOrder())
					.map(Path::toFile)
					.forEach(File::delete);
		}

		// standalone initialization
		SoaPackage.eINSTANCE.getName();
		RequirementPackage.eINSTANCE.getName();
		EntityPackage.eINSTANCE.getName();
		OverviewPackage.eINSTANCE.getName();

		// RAZ des erreurs eventuelles dans le "Error Log"
		ErrorGeneration.clear();

		// Chargement de l'ensemble des fichiers '.properties' pour le generateur.
		final String modelPath = modelFile.getParent();
		PacmanPropertiesManager.initProperties(modelPath);

		// Reinitialisation de certains services.
//		resetListOfStaticServices();
		ImportsUtils.resetAdditionalTypes();

		// Check if the type of model or type of project is compatible with the
		// generator else stop.
//		if (hasIncompatibleModel() || hasIncompatibleProperties())
//			return;

		// Enregistrement et lancement de l'ensemble des generateurs.
		// create the ResourceSet
		final Object generationKey = new Object();
		final Map<String, String> options = new LinkedHashMap<>();
		final ArrayList<Exception> exceptions = new ArrayList<>();

		final IQualifiedNameResolver resolver = new ClassLoaderQualifiedNameResolver(
				MainAcceleo4ServerSoa.class.getClassLoader(), AcceleoParser.QUALIFIER_SEPARATOR);
		final ResourceSet resourceSet = new ResourceSetImpl();

		// needed for standalone model loading
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(SoaPackage.eNAME,
				new SoaResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(RequirementPackage.eNAME,
				new RequirementResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(EntityPackage.eNAME,
				new EntityResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(OverviewPackage.eNAME,
				new OverviewResourceFactoryImpl());
		resourceSet.getURIConverter().getURIMap().put(
				URI.createURI("platform:/plugin/org.obeonetwork.dsl.environment.common/model/obeo.environment"),
				URI.createURI(
						"/home/development/git/InformationSystem/models/environment/plugins/org.obeonetwork.dsl.environment.common/model/obeo.environment"));

		final ResourceSet resourceSetForModels = AQLUtils.createResourceSetForModels(exceptions, generationKey,
				resourceSet, options);
		final Resource model = resourceSetForModels.getResource(URI.createFileURI(modelFile.getAbsolutePath()), true);

		final IQualifiedNameQueryEnvironment queryEnvironment = AcceleoUtil.newAcceleoQueryEnvironment(options,
				resolver, resourceSetForModels, false);
		for (String nsURI : new ArrayList<String>(EPackage.Registry.INSTANCE.keySet())) {
			registerEPackage(queryEnvironment, EPackage.Registry.INSTANCE.getEPackage(nsURI));
		}

		AcceleoEvaluator evaluator = new AcceleoEvaluator(queryEnvironment.getLookupEngine(), System.lineSeparator());
		resolver.addLoader(new ModuleLoader(new AcceleoParser(), evaluator));
		resolver.addLoader(new JavaLoader(AcceleoParser.QUALIFIER_SEPARATOR, false));

		final Module module = (Module) resolver.resolve(moduleQualifiedName);
		final URI destination = URI.createFileURI(outputFolder);
		final IAcceleoGenerationStrategy strategy = new DefaultGenerationStrategy(
				resourceSetForModels.getURIConverter());
		final URI logURI = AcceleoUtil.getlogURI(destination, "acceleo.log");
		try {
			AcceleoUtil.generate(evaluator, queryEnvironment, module, model, strategy,
					destination, logURI);
		} finally {
			AQLUtils.cleanResourceSetForModels(generationKey, resourceSetForModels);
			AcceleoUtil.cleanServices(queryEnvironment, resourceSetForModels);
		}

		// Exit for the properties manager (if the generation launched).
		PacmanPropertiesManager.exit();

		// Ajout des erreurs eventuelles dans le "Error Log"
		ErrorGeneration.doIfThrowErrorGenerationException();
	}

	/**
	 * Registers the given {@link EPackage} in the given
	 * {@link IQualifiedNameQueryEnvironment} recursively.
	 * 
	 * @param environment
	 *                    the {@link IQualifiedNameQueryEnvironment}
	 * @param ePackage
	 *                    the {@link EPackage}
	 */
	private static void registerEPackage(IQualifiedNameQueryEnvironment environment, EPackage ePackage) {
		environment.registerEPackage(ePackage);
		for (EPackage child : ePackage.getESubpackages()) {
			registerEPackage(environment, child);
		}
	}

}
