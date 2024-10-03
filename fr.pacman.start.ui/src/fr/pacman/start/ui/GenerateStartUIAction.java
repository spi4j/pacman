package fr.pacman.start.ui;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.ui.PacmanUIGeneratorsReport;
import fr.pacman.start.GenerateStart;
import fr.pacman.start.ui.exception.PacmanInitModelException;
import fr.pacman.start.ui.util.ConfigUtil;
import fr.pacman.start.ui.util.PacmanConfig;
import fr.pacman.start.ui.util.SiriusUtil;
import fr.pacman.start.ui.util.TextUtil;
import fr.pacman.start.ui.util.WizardUtil;
import fr.pacman.start.ui.util.WizardUtil.IParametrizedExternalWizard;

/**
 * Wizard pour la creation d'un projet de type Safran par le biais de
 * "new->project menu".
 * 
 * 
 * @author MINARM.
 *
 */
public class GenerateStartUIAction extends Wizard implements INewWizard {

	/**
	 * La page de configuration pour le projet Safran.
	 */
	private PropertiesPage _pageOne;

	/**
	 * La version de spi4J à utiliser.
	 */
	private String _spi4JVersion = "";

	/**
	 * Recupere la version de SPI4J dans un fichier de proprietes. Cette propriete
	 * sera juste affichee pour information au developpeur lors de la creation du
	 * projet.
	 */
	@Override
	public void init(IWorkbench p_workbench, IStructuredSelection p_selection) {

		_spi4JVersion = ConfigUtil.loadProperty("config", "SPI4JVersion");
		setWindowTitle(TextUtil.c_WZD_MAIN_WINDOW_TITLE);
	}

	/**
	 * Lancement du job de creation pour le squelette d'un projet Safran, une fois
	 * l'ensemble des parametres saisis par le developpeur.
	 */
	@Override
	public boolean performFinish() {

		final Map<String, String> v_startProperties = initProperties();

		Job v_job = new Job(TextUtil.c_WZD_MONITOR_PROJECT_CREATE) {

			@Override
			public IStatus run(IProgressMonitor p_monitor) {

				IProject v_project = null;
				SubMonitor v_subMonitor = SubMonitor.convert(p_monitor, 100);

				try {

					v_subMonitor.setTaskName(TextUtil.c_WZD_MONITOR_PROJECT_CREATE);
					v_project = createProject(v_subMonitor, v_startProperties);

					v_subMonitor.setTaskName(TextUtil.c_WZD_MONITOR_PROJECT_SAFRAN);
					upgradeProjectWithSafran(v_subMonitor, v_project, v_startProperties);

					v_subMonitor.setTaskName(TextUtil.c_WZD_MONITOR_PROJECT_MAVEN);
					addMavenNatureToProject(v_subMonitor, v_project);

					v_subMonitor.setTaskName(TextUtil.c_WZD_MONITOR_PROJECT_SUB);
					configureSubProjectsWithMaven(v_project);

					WizardUtil.refreshProject(v_subMonitor, v_project);

					v_subMonitor.setTaskName(TextUtil.c_WZD_MONITOR_PROJECT_EMF);
					addEMFNatureToProjectModel(v_subMonitor, v_project, 0);

				} catch (Exception p_e) {

					return WizardUtil.sendErrorStatus(p_e, Activator.c_PLUGIN_ID);

				} finally {

					try {

						WizardUtil.refreshAndSaveProject(v_subMonitor, v_project);

					} catch (CoreException v_e) {

						return WizardUtil.sendErrorStatus(v_e, Activator.c_PLUGIN_ID);
					}
				}
				return Status.OK_STATUS;
			}
		};

		v_job.schedule();
		return Boolean.TRUE;
	}

	/**
	 * 
	 * Recupere les informations du formulaire (unique page du Wizard). Cette
	 * methode ne devrait lancer aucune exception.
	 * 
	 * @return v_properties
	 */
	private Map<String, String> initProperties() {

		Map<String, String> v_properties = new HashMap<String, String>();
		v_properties.put(GenerateStart.c_PROP_APPLICATION_NAME, _pageOne.getApplicationName());
		v_properties.put(GenerateStart.c_PROP_PACKAGE_NAME, _pageOne.getPackageName());
		v_properties.put(GenerateStart.c_PROP_NAMING_TYPE, _pageOne.getNamingCode());
		v_properties.put(GenerateStart.c_PROP_CLIENT_TYPE, _pageOne.getIhm());
		v_properties.put(GenerateStart.c_DATEBASE_TYPES, _pageOne.getDataBasesNames());
		v_properties.put(GenerateStart.c_PROP_EJB_SRV, _pageOne.getSrvEjb());
		v_properties.put(GenerateStart.c_PROP_REQ_SRV, _pageOne.getSrvReq());
		v_properties.put(GenerateStart.c_PROP_CR_FORMAT, _pageOne.getTypeRC());
		v_properties.put(GenerateStart.c_PROP_SRV_CRUD_TU, _pageOne.getUnitTests());
		v_properties.put(GenerateStart.c_PROP_GEN_WS, _pageOne.getSrvWS());
		v_properties.put(GenerateStart.c_PROP_GEN_WMS, _pageOne.getSrvWMS());
		v_properties.put(GenerateStart.c_PROP_GEN_MATCHING, _pageOne.getGenerateMatching());
		v_properties.put(GenerateStart.c_PROP_USE_CONFIG, _pageOne.getUseConfig());
		v_properties.put(GenerateStart.c_PROP_GEN_BDD, _pageOne.getGenBDD());
		v_properties.put(GenerateStart.c_PROP_IS_LIBRARY, _pageOne.getIsLibrary());
		v_properties.put(GenerateStart.c_PROP_IS_LIBRARY_RS, _pageOne.getIsLibraryRs());
		v_properties.put(GenerateStart.c_PROP_LIB_ADD_JAR, _pageOne.getAddLibraries());
		v_properties.put(GenerateStart.c_PROP_JAVA_VERSION, _pageOne.getJavaVersion());
		v_properties.put(GenerateStart.c_PROP_USE_SECURITY, _pageOne.getUseSecurity());
		v_properties.put(GenerateStart.c_PROP_SQL_TABLE_PREFIX, _pageOne.getSqlTablePrefix());
		v_properties.put(GenerateStart.c_PROP_SQL_TABLE_SCHEMA, _pageOne.getSqlTableSchema());
		v_properties.put(GenerateStart.c_PROP_SQL_TABLESPACE, _pageOne.getSqlTableSpace());
		v_properties.put(GenerateStart.c_PROP_SQL_ADD_COLUMNS, _pageOne.getSqlAddColumns());
		v_properties.put(GenerateStart.c_PROP_FETCHING_STRATEGY, _pageOne.getFetchingStrategy());
		v_properties.put(GenerateStart.c_PROP_REQUIREMENT_LEVEL, _pageOne.getRequirementLevel());
		v_properties.put(GenerateStart.c_PROP_REQUIREMENT_PREFIX, _pageOne.getRequirementPrefix());
		v_properties.put(GenerateStart.c_PROP_REQUIREMENT_VERSION, _pageOne.getRequirementVersion());
		v_properties.put(GenerateStart.c_PROP_HTTP_EMBEDDED_SERVER, _pageOne.getHttpEmbeddedServer());
		v_properties.put(GenerateStart.c_PROP_H2_EMBEDDED, _pageOne.getH2EmbeddedDatabase());
		v_properties.put(GenerateStart.c_PROP_AUTHOR_NAME, _pageOne.getAuthorName());
		v_properties.put(GenerateStart.c_PROP_MODE_DEBUG, _pageOne.getModeDebug());
		v_properties.put(GenerateStart.c_PROP_PROJECT_VERSION, "0.0.1-SNAPSHOT");
		v_properties.put(GenerateStart.c_PROP_SPI4J_VERSION, _spi4JVersion);
		v_properties.put(GenerateStart.c_PROP_LOG4J, _pageOne.getLog4j());
		v_properties.put(GenerateStart.c_PROP_APP_CRUD, _pageOne.getCrud());
		v_properties.put(GenerateStart.c_PROP_INJECT_HK2, _pageOne.getHk2());
		// On rajoute les proprietes additionnelles si elles existent.
		v_properties.putAll(_pageOne.getSqlAddColumnsDetail());
		v_properties.putAll(_pageOne.getAddLibrariesDetail());

		return v_properties;
	}

	/**
	 * Creation du projet Java.
	 * 
	 * @param p_monitor
	 * @param p_projectName
	 * @return
	 * @throws CoreException
	 */
	private IProject createProject(final SubMonitor p_monitor, final Map<String, String> p_startProperties)
			throws CoreException {

		final String p_appliName = p_startProperties.get(GenerateStart.c_PROP_APPLICATION_NAME);

		if (null == p_appliName || p_appliName.isEmpty())
			throw new NullPointerException(TextUtil.c_ERR_PROJECT_EMPTY);

		IWorkspaceRoot v_root = ResourcesPlugin.getWorkspace().getRoot();
		IProject v_project = v_root.getProject(p_appliName);

		if (!v_project.exists()) {
			v_project.create(p_monitor.newChild(50));
			v_project.open(p_monitor.newChild(50));
		}

		return v_project;
	}

	/**
	 * Ajout de l'ensemble de la configuration pour un projet de type Safran.
	 * 
	 * @param p_monitor
	 * @param p_project
	 * @param p_properties
	 * @throws CoreException
	 */
	private void upgradeProjectWithSafran(final SubMonitor p_monitor, IProject p_project,
			Map<String, String> p_properties) throws CoreException {

		try {
			File v_file = new File((p_project).getLocation().toString());
			final String v_modelPath = v_file.getAbsolutePath() + File.separator
					+ p_properties.get(GenerateStart.c_PROP_APPLICATION_NAME) + "-model";
			PacmanPropertiesManager.initProperties(v_modelPath, p_properties);
			PacmanUIGeneratorsReport.reset();
			
			GenerateStart v_generator = new GenerateStart(v_file);
			v_generator.updateWithSafranProject(p_properties);

		} catch (IOException e) {

			throw new CoreException(WizardUtil.sendErrorStatus(e, Activator.c_PLUGIN_ID));

		} finally {

			PacmanUIGeneratorsReport.log(true);
			PacmanPropertiesManager.exit();
		}
	}

	/**
	 * Ajout de la nature "Maven" au projet principal (projet parent).
	 * 
	 * @param p_monitor
	 * @param p_project
	 * @throws CoreException
	 */
	private void addMavenNatureToProject(final SubMonitor p_monitor, final IProject p_project) throws CoreException {

		IProjectDescription v_description;
		v_description = p_project.getDescription();
		String[] v_natures = v_description.getNatureIds();
		String[] v_newNatures = new String[v_natures.length + 1];
		System.arraycopy(v_natures, 0, v_newNatures, 0, v_natures.length);
		v_newNatures[v_natures.length] = PacmanConfig.c_MAVEN_NATURE_ID;

		// Validation des natures.
		IWorkspace v_workspace = ResourcesPlugin.getWorkspace();
		IStatus v_status = v_workspace.validateNatureSet(v_newNatures);

		// On applique la nature uniquement si la validation est OK.
		if (v_status.getCode() != IStatus.OK)
			throw new CoreException(v_status);

		v_description.setNatureIds(v_newNatures);
		p_project.setDescription(v_description, p_monitor.newChild(100));
	}

	/**
	 * 
	 * @throws CoreException
	 */
	private void configureSubProjectsWithMaven(final IProject p_project) throws CoreException {

		final String v_workspace = p_project.getWorkspace().getRoot().getLocation().toPortableString();

		final int v_result = WizardUtil.executeExternalWizard(new IParametrizedExternalWizard() {

			@Override
			public void initExternalWizard(IWizard p_wizard) throws Exception {

				Field v_wizardField = p_wizard.getClass().getDeclaredField("locations");
				v_wizardField.setAccessible(Boolean.TRUE);
				v_wizardField.set(p_wizard, Collections.singletonList(v_workspace));
			}

			@Override
			public String getWizardId() {

				return PacmanConfig.c_MAVEN_IMPORT_WIZARD_ID;
			}
		});

		if (WizardUtil.c_CODE_KO_EXIST == v_result)
			WizardUtil.displayMessageInDialog(TextUtil.c_ERR_DIALOG_TITLE, TextUtil.c_ERR_BUNDLE_MAVEN);

		if (WizardUtil.c_CODE_KO == v_result)
			WizardUtil.openExternalWizard(PacmanConfig.c_MAVEN_IMPORT_WIZARD_ID, TextUtil.c_LBL_TITLE_MAVEN);
	}

	/**
	 * Ajout de la nature EMF au projet de modelisation. Dans quelques rares cas, il
	 * est possible que le systeme n'ait pas encore eu le temps de finaliser
	 * l'ecriture des differents projets sur le disque (depend de la charge
	 * processeur,etc...).
	 * 
	 * On attend donc jusqu'a ce que le projet de modelisation ait ete complete puis
	 * on lance l'ajout des donnees de modélisation. Si cela prend trop de temps, on
	 * stoppe et on affiche un message à l'utilisateur pour finaliser a la main la
	 * configuration du projet de modelisation. Utilisation de semaphore ?
	 *
	 * @param p_monitor
	 * @param p_project
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws PacmanInitModelException
	 */
	private void addEMFNatureToProjectModel(final SubMonitor p_monitor, final IProject p_project, int p_cpt)
			throws CoreException, InterruptedException, PacmanInitModelException {

		if (p_cpt == 5) {
			throw new PacmanInitModelException(TextUtil.c_ERR_MODEL);
		}

		IWorkspace v_workspace = ResourcesPlugin.getWorkspace();
		final IWorkspaceRoot v_root = v_workspace.getRoot();
		String v_projectModelName = p_project.getName() + PacmanConfig.c_PROJECT_MODEL_EXTENSION;
		IProject v_project = v_root.getProject(v_projectModelName);

		// On attend que l'ecriture du projet ait ete finalisee sur le disque.
		File v_pom = new File(v_project.getLocation() + File.separator + "pom.xml");

		// Boucle tant que le fichier n'est pas trouve.
		if (!v_pom.isFile()) {
			p_monitor.setTaskName(TextUtil.c_WZD_MONITOR_PROJECT_EMFT + p_cpt + "/6");
			Thread.sleep(10000);
			addEMFNatureToProjectModel(p_monitor, p_project, p_cpt + 1);
		}

		// Ajout de toutes les donnees de modelisation.
		SiriusUtil.addModelingResources(p_monitor, v_project, _pageOne.getApplicationName(), _pageOne.getModelCodes());
	}

	@Override
	public void addPages() {
		_pageOne = new PropertiesPage();
		_pageOne.setSpiVersion(_spi4JVersion);
		addPage(_pageOne);
	}
}
