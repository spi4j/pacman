package fr.pacman.test;

import static org.junit.jupiter.api.Assertions.fail;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.obeonetwork.dsl.cinematic.util.CinematicResourceFactoryImpl;
import org.obeonetwork.dsl.entity.util.EntityResourceFactoryImpl;
import org.obeonetwork.dsl.environment.util.EnvironmentResourceFactoryImpl;
import org.obeonetwork.dsl.requirement.util.RequirementResourceFactoryImpl;
import org.obeonetwork.dsl.soa.util.SoaResourceFactoryImpl;

/**
 * Classe utilitaire de chargement de modele (meta-models).
 * 
 * @apiNote Il est absolument impératif de charger l'ensemble des ressources
 *          (même avec la version 3.x d'Acceleo) et notamment la ressource
 *          concernant l'environnement.
 * 
 * @author MinDef
 */
public final class ModelLoader4Test {

	/**
	 * Chemin pour le chargement du fichier des types (environnement).
	 */
	private static final String c_pathEnvironmentForTests = "../fr.pacman.test/resources/model/application.environment";

	/**
	 * Constructeur prive.
	 */
	private ModelLoader4Test() {
		super();
	}

	/**
	 * Initialise le contenu du modele en chargeant des ressources supplementaires
	 * (les .requirement).
	 * 
	 * @param p_modelURI l'URI du modèle de base de la génération
	 * @return la racine du modèle
	 */
	public static EObject initializeModelContents(final URI p_modelURI) {
		/*
		 * Cette methode va charger toutes les ressources necessaires dans un meme
		 * ResourceSet
		 */
		final ResourceSet v_resourceSet = new ResourceSetImpl();

		try {
			// Chargement Entity.
			v_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.entity.EntityPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.entity.EntityPackage.eINSTANCE);
			v_resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("entity",
					new EntityResourceFactoryImpl());

			// Chargement SOA.
			v_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.soa.SoaPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.soa.SoaPackage.eINSTANCE);
			v_resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("soa",
					new SoaResourceFactoryImpl());

			// Chargement Requirement.
			v_resourceSet.getPackageRegistry().put(
					org.obeonetwork.dsl.requirement.RequirementPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.requirement.RequirementPackage.eINSTANCE);
			v_resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("requirement",
					new RequirementResourceFactoryImpl());

			// Chargement Environment.
			v_resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("environment",
					new EnvironmentResourceFactoryImpl());

			// Chargement du fichier d'environnement (suite). 
			final URI v_environmentURI = URI.createURI(c_pathEnvironmentForTests, true);
			v_resourceSet.getResource(v_environmentURI, true);

			// Chargement Cinematic
			v_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.cinematic.CinematicPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.cinematic.CinematicPackage.eINSTANCE);
			v_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.cinematic.flow.FlowPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.cinematic.flow.FlowPackage.eINSTANCE);
			v_resourceSet.getPackageRegistry().put(
					org.obeonetwork.dsl.cinematic.toolkits.ToolkitsPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.cinematic.toolkits.ToolkitsPackage.eINSTANCE);
			v_resourceSet.getPackageRegistry().put(org.obeonetwork.dsl.cinematic.view.ViewPackage.eINSTANCE.getNsURI(),
					org.obeonetwork.dsl.cinematic.view.ViewPackage.eINSTANCE);
			v_resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cinematic",
					new CinematicResourceFactoryImpl());
//
//			// Chargement du fichier toolkit
//			//v_resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cinematic_toolkits",
//			//		new ToolkitsResourceFactoryImpl());
//			//v_resourceSet.getResource(URI.createURI(FileHelper4Test.getToolkitPath(), true), true);
//
		} catch (final NoClassDefFoundError v_e) {
			fail("Impossible de charger la resource suivante : " + v_e.getMessage());
		}

		final Resource v_mainModel = v_resourceSet.getResource(p_modelURI, true);

		return v_mainModel.getContents().get(0);
	}
}
