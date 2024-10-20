package fr.pacman.commons.services;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.PlatformUI;

import fr.pacman.commons.Activator;

public class PlugInUtils {

	/**
	 * Affiche une information.
	 * 
	 * @param p_title   le titre
	 * @param p_message le message
	 */
	public static void displayInformation(final String p_title, final String p_message) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				try {    
					//MessageDialog.setDefaultImage(new Image(PlatformUI.getWorkbench().getDisplay(),"icons/safran.png"));
					MessageDialog.openInformation(PlatformUI.getWorkbench().getDisplay().getActiveShell(), p_title,
							p_message);
				} catch (final IllegalStateException v_e) {
					MessageDialog.openError(null, p_title, p_message);
				}
			}
		});
	}

	/**
	 * Affichage d'une popup d'erreur.
	 * 
	 * @param p_title   le titre de la popup
	 * @param p_message le message d'erreur
	 */
	public static void displayError(final String p_title, final String p_message) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				try {
					MessageDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), p_title,
							p_message);
				} catch (final IllegalStateException v_e) {
					MessageDialog.openError(null, p_title, p_message);
				}
			}
		});
	}

	/**
	 * Cherche le chemin d'un projet par son nom.
	 * 
	 * @param p_name le nom du projet
	 * @return le chemin vers le projet
	 */
	public static File findProjectByName(final String p_name) {
		String v_path = null;
		try {
			v_path = ResourcesPlugin.getWorkspace().getRoot().getProject(p_name).getLocationURI().getPath();

			return new File(v_path);
		} catch (final Exception v_e) {

			String v_err = " - nom du projet : " + p_name + "\n - path du projet : " + v_path;

			Activator.getDefault().getLog().log(
					new Status(Status.WARNING, Activator.c_PLUGIN_ID, "Impossible de récupérer le projet  : " + v_err));

			displayError("Impossible de récupérer le projet", v_err);
			return null;
		}
	}
}
