package fr.pacman.start.ui.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.wizards.IWizardDescriptor;

import fr.pacman.start.ui.Activator;
import fr.pacman.start.ui.exception.WizardNotFoundException;

/**
 * 
 * @author MINARM
 *
 */
public class WizardUtil {

	public static final int c_CODE_OK = 0;
	public static final int c_CODE_KO = 1;
	public static final int c_CODE_KO_EXIST = 2;

	/**
	 * Constructeur prive pour eviter l'instanciation de la classe.
	 */
	private WizardUtil() {
		super();
	}

	/**
	 * Rechargement complet du workspace.
	 * 
	 * @param p_monitor
	 * @throws CoreException
	 */
	public static void refreshWorkspace(final SubMonitor p_monitor) throws CoreException {

		IWorkspace v_workspace = ResourcesPlugin.getWorkspace();
		v_workspace.getRoot().refreshLocal(IResource.DEPTH_INFINITE, p_monitor);
	}

	/**
	 * Méthode tres simple pour demander le rechargement d'un projet dans le
	 * workspace, et les eventuelles sauvegarde des editeurs ouverts.
	 * 
	 * @param p_projectName
	 * @param p_monitor
	 * @throws CoreException
	 */
	public static void refreshAndSaveProject(final SubMonitor p_monitor, final IProject p_project)
			throws CoreException {

		if (null != p_project && p_project.exists()) {
			p_project.refreshLocal(IResource.DEPTH_INFINITE, p_monitor.newChild(100));
			saveAllEditors();
		}
	}

	/**
	 * Méthode tres simple pour demander le rechargement d'un projet dans le
	 * workspace.
	 * 
	 * @param p_projectName
	 * @param p_monitor
	 * @throws CoreException
	 */
	public static void refreshProject(final SubMonitor p_monitor, final IProject p_project) throws CoreException {

		if (null != p_project && p_project.exists())
			p_project.refreshLocal(IResource.DEPTH_INFINITE, p_monitor.newChild(100));
	}

	/**
	 * Sauvegarde automatique de l'ensemble des editeurs qui sont en etat 'dirty'
	 * (donc a sauvegarder). Pour l'instant pas de fenetre de confirmation.
	 */
	private static void saveAllEditors() {

		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbench v_workbench = PlatformUI.getWorkbench();
				if (null != v_workbench) {
					v_workbench.saveAllEditors(Boolean.FALSE);
					// v_workbench.restart(Boolean.TRUE);
				}
			}
		});
	}

	/**
	 * Méthode tres simple pour retourner un IStatus directement dans une
	 * CoreException.
	 * 
	 * @param p_e
	 * @return v_status
	 */
	public static IStatus sendErrorStatus(Exception p_e, final String p_pluginId) {

		IStatus v_status = new Status(IStatus.ERROR, p_pluginId, p_e.getMessage(), p_e);
		Activator.getDefault().getLog().log(v_status);
		return v_status;
	}

	/**
	 * Demande le chargement d'un Wizard present sur la plate-forme. On cherche dans
	 * les differentes Registry et si on trouve le Wizard, il est charge à partir de
	 * son descripteur.
	 * 
	 * @param p_id
	 * @return
	 * @throws CoreException
	 */
	private static IWorkbenchWizard loadExternalWizard(final String p_id)
			throws CoreException, WizardNotFoundException {

		// On tente de le charger dans la catégorie des "new".
		IWizardDescriptor v_descriptor = PlatformUI.getWorkbench().getNewWizardRegistry().findWizard(p_id);

		// On tente de le charger dans la catégorie des "import".
		if (v_descriptor == null) {
			v_descriptor = PlatformUI.getWorkbench().getImportWizardRegistry().findWizard(p_id);
		}
		// On tente de le charger dans la catégorie des "export".
		if (v_descriptor == null) {
			v_descriptor = PlatformUI.getWorkbench().getExportWizardRegistry().findWizard(p_id);
		}

		if (v_descriptor == null)
			throw new WizardNotFoundException(TextUtil.c_ERR_WIZARD);

		return v_descriptor.createWizard();
	}

	/**
	 * Execute un Wizard present sur la plate-forme dans son propre processus.
	 * 
	 * @param p_id
	 * @param p_args
	 */
	public static int executeExternalWizard(final IParametrizedExternalWizard p_configurator) {

		final int[] v_result = new int[1];
		v_result[0] = c_CODE_OK;

		Display.getDefault().syncExec(new Runnable() {

			@Override
			public void run() {

				try {

					boolean v_perform = executeExternalWizardInDialogBox(p_configurator);

					if (!v_perform)
						v_result[0] = c_CODE_KO;

				} catch (CoreException e) {

					v_result[0] = c_CODE_KO;

				} catch (WizardNotFoundException e) {

					v_result[0] = c_CODE_KO_EXIST;
				}
			}
		});

		return v_result[0];
	}

	/**
	 * Cette methode permet de charger un wizard sans l'afficher et de lancer sa
	 * méthode "performFinish". Ainsi le wizard effectue son action sans aucune
	 * action utilisateur (l'IHM n'est pas affichee).
	 * 
	 * ATTENTION : Ce type de fonctionnement n'est absolument pas optimal. Il permet
	 * pour l'instant juste d'effectuer le travail demande en attendant de trouver
	 * une solution plus elegante et perenne, a savoir :
	 * 
	 * - Attaquer directement le / les plugin(s) approprié(s).
	 * 
	 * - Coder avec les API du / des plugin(s).
	 * 
	 * - Au pire, lancer le wizard sans passer par la WizardDialog et devoir passer
	 * par un processus (Runnable).
	 * 
	 * @param p_id
	 * @param p_args
	 * @return
	 * @throws CoreException
	 */
	private static boolean executeExternalWizardInDialogBox(final IParametrizedExternalWizard p_configurator)
			throws CoreException, WizardNotFoundException {

		boolean v_perform = Boolean.FALSE;

		try {

			final IWorkbenchWizard v_wizard = loadExternalWizard(p_configurator.getWizardId());

			if (v_wizard != null) {
				p_configurator.initExternalWizard(v_wizard);
				Shell v_shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				WizardDialog v_wd = new WizardDialog(v_shell, v_wizard);
				v_wd.create();

				// Activation du wizard.
				v_perform = v_wd.getCurrentPage().getWizard().performFinish();

				// Travail effectue.
				v_wd = null;
			}
		} catch (WizardNotFoundException e) {

			throw e; // Pour l'instant.

		} catch (Exception e) {

			throw new CoreException(sendErrorStatus(e, p_configurator.getWizardId()));
		}

		return v_perform;
	}

	/**
	 * Ouvre un wizard disponible sur la plate-forme et l'affiche pour
	 * l'utilisateur.
	 * 
	 * @param p_id
	 * @param p_title
	 * @throws CoreException
	 * @throws WizardNotFoundException
	 */
	public static int openExternalWizard(final String p_id, final String p_title) throws CoreException {

		final int[] v_result = new int[1];
		v_result[0] = c_CODE_OK;

		try {

			final IWorkbenchWizard v_wizard = loadExternalWizard(p_id);

			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {

					if (v_wizard != null) {
						Shell v_shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						WizardDialog v_wd = new WizardDialog(v_shell, v_wizard);
						v_wd.create();
						v_wd.setTitle(p_title);
						v_wd.open();
					}
				}
			});

		} catch (WizardNotFoundException e) {
			v_result[0] = c_CODE_KO_EXIST;
		}

		return v_result[0];
	}

	/**
	 * 
	 * @param p_title
	 * @param p_message
	 */
	public static void displayMessageInDialog(final String p_title, final String p_message) {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), p_title,
						p_message);
			}
		});
	}

	/**
	 * Interface pour le parametrage éventuel d'un wizard que l'on desire activer
	 * sans interface utilisateur.
	 * 
	 * @author MINARM.
	 *
	 */
	public interface IParametrizedExternalWizard {

		public abstract String getWizardId();

		public abstract void initExternalWizard(IWizard p_wizard) throws Exception;
	}
}
