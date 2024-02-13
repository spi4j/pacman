package fr.pacman.start.ui.util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.danalysis.SaveSessionJob;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.obeonetwork.dsl.cinematic.CinematicFactory;
import org.obeonetwork.dsl.cinematic.CinematicRoot;
import org.obeonetwork.dsl.database.DataBase;
import org.obeonetwork.dsl.database.DatabaseFactory;
import org.obeonetwork.dsl.entity.EntityFactory;
import org.obeonetwork.dsl.entity.Root;
import org.obeonetwork.dsl.requirement.Repository;
import org.obeonetwork.dsl.requirement.RequirementFactory;
import org.obeonetwork.dsl.soa.SoaFactory;
import org.obeonetwork.dsl.soa.System;
import org.obeonetwork.dsl.typeslibrary.TypesLibrary;

import fr.pacman.start.ui.exception.PacmanInitModelException;

/**
 * Classe utilitaire pour tout ce qui concerne la creation d'un projet de
 * modelisation, de ses fichiers de modelisation, de leur representations
 * associees et des 'viewpoints'.
 *
 * (cf : org.obeonetwork.dsl.is.ui.wizards.AbstractISNewModelWizard)
 *
 * @author MINARM
 */
@SuppressWarnings("restriction")
public class SiriusUtil {

	/**
	 * Constructeur prive pour eviter l'instanciation de la classe.
	 */
	private SiriusUtil() {
		super();
	}

	/**
	 * Ajout de la nature EMF au projet avec le fichier .aird Activation des 'view
	 * points' (en fonction de ceux qui ont ete demandes par l'utilisateur).
	 * Creation des fichiers de modelisation et des representations associees.
	 *
	 * @param p_monitor         l'objet de monitoring.
	 * @param p_project         l'objet contenant le projet de modelisation.
	 * @param p_applicationName le nom de l'application.
	 * @param p_lstModelCodes   la liste des codes pour les fichiers de modelisation
	 *                          a generer.
	 * @throws CoreException
	 * @throws PacmanInitModelException
	 */
	public static void addModelingResources(final SubMonitor p_monitor, final IProject p_project,
			final String p_applicationName, final List<String> p_lstModelCodes)
			throws CoreException, PacmanInitModelException {

		if (!p_project.exists())
			throw new PacmanInitModelException("Projet non trouvé !");

		// On ajoute la nature Modeling (creation du 'representations.aird').
		ModelingProjectManager.INSTANCE.convertToModelingProject(p_project, p_monitor.newChild(100));

		// Recuperation de la session pour le nouveau projet.
		final Session v_session = SessionManager.INSTANCE.getSession(getMainRepresentationsUri(p_project), p_monitor);

		// Verifie que la session a bien ete recuperee.
		if (null == v_session)
			throw new PacmanInitModelException("Aucune session !");

		// Creation des ressources de modelisation.
		createModelingResources(v_session, p_lstModelCodes, p_project, p_applicationName, p_monitor);
	}

	/**
	 * Creation des differentes ressources de modelisation pour le projet en
	 * fonction des ressources demandees par le developpeur lors de la creation du
	 * projet.
	 *
	 * L'ouverture des representations est effectuee dans un deuxieme temps, cela
	 * est uniquement lie a une problematique visuelle lors de la creation de
	 * multiples resources, si un editeur est ouvert, il se met a 'clignoter' lors
	 * de la creation de nouvelles resources.
	 *
	 * @param p_session         la session Sirius.
	 * @param p_lstModelCodes   la liste des codes pour les fichiers de
	 *                          modelisation.
	 * @param p_applicationName le nom de l'application.
	 * @param p_monitor         l'objet de monitoring.
	 */
	private static void createModelingResources(final Session p_session, final List<String> p_lstModelCodes,
			final IProject p_project, final String p_applicationName, final SubMonitor p_monitor)
			throws PacmanInitModelException {

		Map<String, DRepresentation> v_lstCreatedRepresentations = new HashMap<String, DRepresentation>();

		for (String v_code : p_lstModelCodes) {

			// On recupere les infos pour le model.
			SiriusModelDescriptor v_modelHelper = PacmanConfig.c_MODELS.get(v_code);

			// Recuperation de l'objet racine necessaire pour la creation du fichier.
			EObject v_eObject = getEObjectForModeling(p_session, v_code, p_applicationName, p_project);

			// Creation du fichier de modelisation.
			createModelingFile(p_session, getModelFileUri(p_project, p_applicationName, v_modelHelper), v_modelHelper,
					v_eObject, p_monitor);

			// Activation des viewPoints associes au fichier de modelisation.
			activateViewPoints(p_session, v_modelHelper, p_monitor);

			// Creation des representations associees au fichier de modelisation.
			createRepresentations(p_session, v_modelHelper, v_eObject, p_monitor, p_applicationName, v_lstCreatedRepresentations);
		}

		// On ouvre les representations uniquement lorsque toutes les ressources sont
		// crees afin d'ameliorer le 'confort visuel'.
		openRepresentations(p_session, p_monitor, v_lstCreatedRepresentations);
	}

	/**
	 * Recuperation des parametres de construction pour les fichiers de modelisation
	 * du projet. (On fonctionne avec un 'if' tres basique pour l'instant, pas
	 * necessaire de mettre en place un pattern plus evolue pour cette
	 * fonctionnalite 'mineure' dans Pacman...).
	 *
	 * @param p_session         la session Sirius.
	 * @param p_code            le code pour le fichier de modelisation a creer.
	 * @param p_applicationName le nom general de l'application.
	 * @param p_project
	 * @return
	 * @throws PacmanInitModelException
	 */
	private static EObject getEObjectForModeling(final Session p_session, final String p_code,
			final String p_applicationName, final IProject p_project) throws PacmanInitModelException {

		EObject v_EObject = null;

		if (PacmanConfig.c_MODEL_ENTITY_CODE.equals(p_code)) {
			v_EObject = EntityFactory.eINSTANCE.createRoot();
			((Root) v_EObject).setCreatedOn(new Date());
		}

		if (PacmanConfig.c_MODEL_CINEMATIC_CODE.equals(p_code)) {
			v_EObject = CinematicFactory.eINSTANCE.createCinematicRoot();
			((CinematicRoot) v_EObject).setCreatedOn(new Date());
			((CinematicRoot) v_EObject).setName(p_applicationName);
		}

		if (PacmanConfig.c_MODEL_DATABASE_CODE.equals(p_code)) {
			v_EObject = DatabaseFactory.eINSTANCE.createDataBase();
			((DataBase) v_EObject).setName(p_applicationName + "-mdl"); // Entorse a corriger.

			ResourceSet resourceSet = p_session.getSessionResource().getResourceSet();
			Resource v_typesLibraryResource = resourceSet
					.getResource(URI.createURI(PacmanConfig.c_DATABASE_LOGICAL_PATHMAP), true);

			if (v_typesLibraryResource != null) {
				EObject v_typesRoot = v_typesLibraryResource.getContents().get(0);
				if (v_typesRoot instanceof TypesLibrary) {
					((DataBase) v_EObject).getUsedLibraries().add((TypesLibrary) v_typesRoot);
				}
			}
		}

		if (PacmanConfig.c_MODEL_REQUIREMENT_CODE.equals(p_code)) {
			v_EObject = RequirementFactory.eINSTANCE.createRepository();
			((Repository) v_EObject).setName(p_project.getName());
		}

		if (PacmanConfig.c_MODEL_SOA_CODE.equals(p_code)) {
			v_EObject = SoaFactory.eINSTANCE.createSystem();
			((System) v_EObject).setCreatedOn(new Date());
		}

		// Garantie que l'on travaille bien sur un EObject.
		if (null == v_EObject)
			throw new PacmanInitModelException("Aucun objet EObject pour le code : " + p_code);

		return v_EObject;
	}

	/**
	 * Creation de l'URI pour le fichier de modelisation.
	 *
	 * @param p_project         le projet en cours.
	 * @param p_applicationName le nom de l'application.
	 * @param p_modelHelper     le conteneur d'informations pour le fichier de
	 *                          modelisation en cours.
	 * @return l'URI du fichier de modelisation.
	 */
	private static URI getModelFileUri(final IProject p_project, final String p_applicationName,
			final SiriusModelDescriptor p_modelHelper) {

		StringBuffer v_strUri = new StringBuffer(File.separator);
		v_strUri.append(p_project.getName()).append(File.separator);
		v_strUri.append(p_applicationName + p_modelHelper.get_modelExt());
		return URI.createPlatformResourceURI(v_strUri.toString(), Boolean.TRUE);
	}

	/**
	 * Creation de l'URI pour le fichier de representations (.aird).
	 *
	 * @param p_project le projet en cours.
	 * @return l'URI du fichier de representation.
	 */
	private static URI getMainRepresentationsUri(final IProject p_project) {

		StringBuffer v_strUri = new StringBuffer(File.separator);
		v_strUri.append(p_project.getName()).append(File.separator);
		v_strUri.append(PacmanConfig.c_MODEL_AIRD_NAME);
		return URI.createPlatformResourceURI(v_strUri.toString(), Boolean.TRUE);
	}

	/**
	 * Creation de l'URI pour un viewPoint.
	 *
	 * @param p_strViewPointUri une partie de l'URI en chaine de caracteres.
	 * @return l'URI complete.
	 */
	private static URI getViewPointURI(final String p_strViewPointUri) {
		return URI.createURI(PacmanConfig.c_VP_URI_BASE + p_strViewPointUri);
	}

	/**
	 * Activation des 'viewpoints' en fonction du fichier de modelisation qui a ete
	 * demande.
	 *
	 * @param p_session     la session Sirius.
	 * @param p_modelHelper le conteneur d'informations pour le fichier de
	 *                      modelisation en cours.
	 * @param p_monitor     l'objet de monitoring.
	 */
	private static void activateViewPoints(final Session p_session, final SiriusModelDescriptor p_modelHelper,
			final SubMonitor p_monitor) throws PacmanInitModelException {

		// On parcourt la liste des URIs recuperees.
		for (String v_viewPointURI : p_modelHelper.get_viewURIs()) {
			// On active les 'viewpoints' concernes pour le model.
			activateViewPoint(p_session, getViewPointURI(v_viewPointURI), p_monitor);
		}
	}

	/**
	 * Activation des 'viewpoints' en fonction du choix utilisateur.
	 *
	 * @param p_session      la session Sirius.
	 * @param p_viewpointURI l'URI d'un viewpoint dans ceux disponibles dans la
	 *                       registry.
	 * @param p_monitor      l'objet de monitoring.
	 */
	private static void activateViewPoint(final Session p_session, final URI p_viewpointURI, final SubMonitor p_monitor)
			throws PacmanInitModelException {

		// Recuperation du viewpoint dans la registry.
		final Viewpoint v_viewpoint = ViewpointRegistry.getInstance().getViewpoint(p_viewpointURI);

		// Verifie que l'on a bien recupere le viewpoint.
		if (null == v_viewpoint)
			throw new PacmanInitModelException("Impossible de récupérer le viewPoint : " + p_viewpointURI);

		// Lance la commande d'activation.
		p_session.getTransactionalEditingDomain().getCommandStack()
				.execute(new RecordingCommand(p_session.getTransactionalEditingDomain()) {
					@Override
					protected void doExecute() {
						final ViewpointSelectionCallback selection = new ViewpointSelectionCallback();
						selection.selectViewpoint(v_viewpoint, p_session, p_monitor);
					}
				});
	}

	/**
	 * Creation des fichiers de modelisation en fonction du choix utilisateur.
	 *
	 * @param p_session     la session Sirius.
	 * @param p_modelhelper le conteneur d'informations pour le fichier de
	 *                      modelisation en cours.
	 * @param p_eObject     l'objet racine pour la ressource.
	 * @param p_monitor     l'objet de monitoring.
	 * @return la resource qui a ete cree.
	 */
	private static Resource createModelingFile(final Session p_session, final URI p_uri,
			final SiriusModelDescriptor p_modelhelper, final EObject p_eObject, final SubMonitor p_monitor) {

		final Resource v_resource = p_session.getTransactionalEditingDomain().getResourceSet().createResource(p_uri);

		// Lance la commande de creation.
		p_session.getTransactionalEditingDomain().getCommandStack()
				.execute(new RecordingCommand(p_session.getTransactionalEditingDomain()) {
					@Override
					protected void doExecute() {

						v_resource.getContents().add(p_eObject);
						p_session.addSemanticResource(p_uri, p_monitor);
					}
				});

		// Sauvegarde de la session.
		p_session.save(p_monitor);
		return v_resource;
	}

	/**
	 * Creation des representations associees a un fichier de modelisation.
	 *
	 * @param session                     la session Sirius.
	 * @param p_modelHelper               le conteneur d'informations pour le
	 *                                    fichier de modelisation en cours.
	 * @param p_eObject                   l'objet racine pour la resource.
	 * @param p_monitor                   l'objet de monitoring.
	 * @param p_applicationName           le nom du projet.
	 * @param p_lstCreatedRepresentations la liste de representations crees.
	 */
	private static void createRepresentations(final Session p_session, final SiriusModelDescriptor p_modelHelper,
			final EObject p_eObject, final SubMonitor p_monitor, String p_applicationName, 
			Map<String, DRepresentation> p_lstCreatedRepresentations) throws PacmanInitModelException {

		// Teste si on a bien récupéré l'ensemble des descriptions pour les représentations.
		if (null == p_modelHelper.get_descIDs() || p_modelHelper.get_descIDs().isEmpty())
			throw new PacmanInitModelException("Impossible de recuperer les ids de description !");

		// Mise en place du fix suite à l'ouverture des représentations (namespace).
		// Déplacé dans createRepresentation(....).
		// ensureNoCDOSaveInProgress();

		for (String v_descID : p_modelHelper.get_descIDs()) {

			// Recuperation de la description pour la representation.
			RepresentationDescription v_desc = getRepresentationDescription(p_session, p_eObject, v_descID);

			if (null == v_desc)
				throw new PacmanInitModelException("Impossible de trouver la description : " + v_descID);
						
			// Creation de la representation.
			DRepresentation v_newRepresentation = createRepresentation(p_session, v_desc,
					getRepresentationName(p_applicationName + " - " + v_descID), p_eObject, p_monitor);

			// On ajoute la representation dans la liste des creations.
			if (null != v_newRepresentation)
				p_lstCreatedRepresentations.put(v_descID, v_newRepresentation);
		}
	}

	/**
	 * Fix du [08/10/20] pour le namespace qui n'apparait pas dans le diagramme (aléatoire).
	 */
	private static void ensureNoCDOSaveInProgress() {

		// Ensure that there is no save in progress.
		// Otherwise, when the representation will be added to the resource
		// (createRepresentation-->CreateRepresentationCommand) can be problematic.
		// Indeed, during the save, at a specific time (ResourceSaveDiagnose.hasDifferentSerialization),
		// the eSetDeliver is disabled. So in this condition, no adapter is added to the added representation.

		try {
			Job.getJobManager().join(SaveSessionJob.FAMILY, new NullProgressMonitor());
		} catch (OperationCanceledException | InterruptedException e) {
			// Ignore these exceptions. The join is just here to avoid to have a save in progress.
		}
	}

	/**
	 * Vide les identifiants de "EV_" (si besoin) afin de creer un nom plus lisible
	 * pour la representation (l'utilisateur pourra toujours modifier le nom plus
	 * tard).
	 *
	 * @param p_name le nom de la representation.
	 * @return
	 */
	private static String getRepresentationName(final String p_name) {

		if (p_name.indexOf("EV_") == -1)
			return p_name;

		String v_name = p_name.replace("EV_", "");
		v_name = v_name.replaceAll("_", " ");
		return v_name;
	}

	/**
	 * Recupere la description (et donc le meta-model) de la representation.
	 *
	 * @param p_session   la session Sirius.
	 * @param p_object    l'objet sur lequel porte la representation.
	 * @param p_repDescID l'identifiant de la representation.
	 * @return l'objet de description pour la representation.
	 */
	private static RepresentationDescription getRepresentationDescription(final Session p_session,
			final EObject p_object, final String p_repDescID) {

		for (final RepresentationDescription v_representation : DialectManager.INSTANCE
				.getAvailableRepresentationDescriptions(p_session.getSelectedViewpoints(Boolean.FALSE), p_object)) {
			if (p_repDescID.equals(v_representation.getName())) {
				return v_representation;
			}
		}
		return null;
	}

	/**
	 * Envoi la commande de creation pour la representation.
	 *
	 * @param p_session     la session Sirius.
	 * @param p_description la description (avec le meta-model).
	 * @param p_name        le nom a afficher pour la representation.
	 * @param p_object      l'objet auquel est associe la representation.
	 * @param p_monitor     l'objet de monitoring.
	 * @return
	 */
	private static DRepresentation createRepresentation(final Session p_session,
			final RepresentationDescription p_description, final String p_name, final EObject p_object,
			final IProgressMonitor p_monitor) {
		
		// Fix de la fiche SAFRAN-1047.
		ensureNoCDOSaveInProgress();

		CreateRepresentationCommand v_cmd = new CreateRepresentationCommand(p_session, p_description, p_object, p_name,
				p_monitor);
		p_session.getTransactionalEditingDomain().getCommandStack().execute(v_cmd);
		return v_cmd.getCreatedRepresentation();
	}

	/**
	 * Demande l'ouverture de toutes les representations qui sont dans la liste des
	 * representations a ouvrir et qui ont ete crees en fonction des fichiers de
	 * modilisation demandes par le developpeur.
	 *
	 * @param p_session            la session Sirius.
	 * @param p_monitor            l'objet de monitoring
	 * @param p_lstRepresentations la map des representation qui on ete crees.
	 */
	private static void openRepresentations(final Session p_session, final IProgressMonitor p_monitor,
			final Map<String, DRepresentation> p_lstRepresentations) {

		for (Entry<String, DRepresentation> v_entry : p_lstRepresentations.entrySet()) {
			if (PacmanConfig.c_RP_TO_OPEN.contains(v_entry.getKey())) {
				openRepresentation(p_session, (DRepresentation) v_entry.getValue(), p_monitor);
			}
		}
	}

	/**
	 * Commande d'ouverture de la representation.
	 *
	 * @param p_session        la session Sirius.
	 * @param p_representation la representation
	 * @param p_monitor        l'objet de monitoring
	 */
	private static void openRepresentation(final Session p_session, final DRepresentation p_representation,
			final IProgressMonitor p_monitor) {

		final IEditingSession v_editingEdition = SessionUIManager.INSTANCE.getUISession(p_session);
		final IEditorPart v_editorPart = DialectUIManager.INSTANCE.openEditor(p_session, p_representation, p_monitor);

		if (v_editorPart != null && v_editingEdition != null)
			v_editingEdition.attachEditor((DialectEditor) v_editorPart);
	}
}
