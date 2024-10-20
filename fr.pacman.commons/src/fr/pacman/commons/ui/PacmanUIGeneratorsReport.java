package fr.pacman.commons.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.aql.evaluation.GenerationResult;
import org.eclipse.acceleo.query.ast.ASTNode;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;

import fr.pacman.commons.Activator;
import fr.pacman.commons.services.PlugInUtils;

/**
 * 
 * @author MINARM
 */
public abstract class PacmanUIGeneratorsReport {

	/**
	 * 
	 */
	private static int _nbFiles;

	/**
	 * 
	 */
	private static int _nbInfos;

	/**
	 * 
	 */
	private static int _nbWarns;

	/**
	 * 
	 */
	private static int _nbErrors;

	/**
	 * 
	 */
	private static int _nbLostFiles;

	/**
	 * 
	 */
	private static Map<String, Integer> _detailNbFiles = new HashMap<>();

	/**
	 * 
	 */
	private static List<GenerationResult> _generationResults = new ArrayList<>();

	/**
	 * 
	 */
	public static void reset() {
		_generationResults.clear();
		_detailNbFiles.clear();
		_nbErrors = 0;
		_nbFiles = 0;
		_nbLostFiles = 0;
		_nbWarns = 0;
	}

	/**
	 * 
	 * @param p_generationResult
	 * @param p_subProjectName
	 */
	public static void addGenerationResult(final GenerationResult p_generationResult, final String p_subProjectName) {

		if (null == p_generationResult)
			return;// ou exception ?

		_generationResults.add(p_generationResult);
		_nbFiles += p_generationResult.getGeneratedFiles().size();
		_nbLostFiles += p_generationResult.getLostFiles().size();
		updateDetailNbFiles(p_generationResult, p_subProjectName);
	}

	/**
	 * @param p_generationResult
	 * @param p_subProjectName
	 */
	private static void updateDetailNbFiles(final GenerationResult p_generationResult, final String p_subProjectName) {

		// If no subproject as we are in creation phase.
		if (null == p_subProjectName)
			return;

		if (!_detailNbFiles.containsKey(p_subProjectName)) {
			_detailNbFiles.put(p_subProjectName, p_generationResult.getGeneratedFiles().size());
		} else {
			int nbFiles = _detailNbFiles.get(p_subProjectName);
			_detailNbFiles.put(p_subProjectName, nbFiles += p_generationResult.getGeneratedFiles().size());
		}
	}

	/**
	 * @param p_displayPopup
	 */
	public static void log(final boolean p_displayPopup) {
		for (GenerationResult v_genResult : _generationResults) {
			if (v_genResult.getDiagnostic().getSeverity() > Diagnostic.INFO) {
				log(v_genResult.getDiagnostic());
			}
		}
		if (p_displayPopup)
			PlugInUtils.displayInformation("Pacman", "Rapport de génération\n\r" + getPopUpCodeGenerationReport());
	}

	/**
	 * 
	 * @param p_diagnostic
	 */
	private static void log(Diagnostic p_diagnostic) {
		if (p_diagnostic.getMessage() != null) {
			final String location;
			if (!p_diagnostic.getData().isEmpty() && p_diagnostic.getData().get(0) instanceof ASTNode) {
				location = AcceleoUtil.getLocation((ASTNode) p_diagnostic.getData().get(0)) + ": ";
			} else {
				location = "";
			}
			switch (p_diagnostic.getSeverity()) {
			case Diagnostic.INFO:
				Activator.getDefault().getLog().log(new Status(IStatus.INFO, p_diagnostic.getSource(),
						location + p_diagnostic.getMessage(), p_diagnostic.getException()));
				_nbInfos++;
				break;

			case Diagnostic.WARNING:
				Activator.getDefault().getLog().log(new Status(IStatus.WARNING, p_diagnostic.getSource(),
						location + p_diagnostic.getMessage(), p_diagnostic.getException()));
				_nbWarns++;
				break;

			case Diagnostic.ERROR:
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, p_diagnostic.getSource(),
						location + p_diagnostic.getMessage(), p_diagnostic.getException()));
				_nbErrors++;
				break;
			}
		}

		for (Diagnostic child : p_diagnostic.getChildren()) {
			log(child);
		}
	}

	/**
	 * 
	 * @return
	 */
	private static String getPopUpCodeGenerationReport() {
		StringBuffer v_codeGenerationReport = new StringBuffer();
		if (_nbFiles == 0) {
			v_codeGenerationReport.append("Aucun fichier n'a été généré !");
		} else {
			v_codeGenerationReport.append(_nbFiles).append(" fichier(s) généré(s).");
			if (!_detailNbFiles.isEmpty()) {
				v_codeGenerationReport.append("\n\rDétail du nombre de fichiers par projet : ");
				for (Entry<String, Integer> v_entry : _detailNbFiles.entrySet()) {
					v_codeGenerationReport.append("\n\r - " + "Projet " + v_entry.getKey() + " : ");
					v_codeGenerationReport.append(v_entry.getValue() + " fichier(s).");
				}
			}
		}
		if (_nbInfos > 0)
			v_codeGenerationReport
					.append("\n\rDes informations supplémentaires sont présentes au niveau de l'onglet 'Error Log'.");

		if (_nbErrors > 0 || _nbWarns > 0 || _nbLostFiles > 0) {
			v_codeGenerationReport.append("\n\rATTENTION, problème(s) détecté(s) ! ");
			v_codeGenerationReport.append(
					"Consultez l'onglet 'Error Log' afin d'obtenir le détail des différentes problématiques rencontrées.");
		}

		if (_nbLostFiles > 0)
			v_codeGenerationReport.append("\n\r").append(_nbLostFiles).append(" fichier(s) de type '.lost'.");

		if (_nbErrors > 0)
			v_codeGenerationReport.append("\n\r").append(_nbErrors).append(" erreur(s) de génération.");

		if (_nbWarns > 0)
			v_codeGenerationReport.append("\n\r").append(_nbWarns).append(" alerte(s) de génération.");

		return v_codeGenerationReport.toString();
	}

	/**
	 * 
	 * @return
	 */
	public static int getNbFiles() {
		return _nbFiles;
	}
}
