package fr.pacman.commons.ui;

import org.eclipse.acceleo.aql.evaluation.GenerationResult;
import org.eclipse.emf.common.util.Diagnostic;

/**
 * 
 * @author MINARM
 */
public class PacmanGeneratorsReport {

	/**
	 * 
	 */
	private int _nbFiles;

	/**
	 * 
	 */
	private int _nbWarns;

	/**
	 * 
	 */
	private int _nbErrors;

	/**
	 * 
	 */
	private int _nbLostFiles;

	/**
	 * 
	 * @param p_generationResult
	 */
	public void addDiagnostic(final GenerationResult p_generationResult) {

		if (null == p_generationResult)
			return;
		
		_nbFiles += p_generationResult.getGeneratedFiles().size();
		_nbLostFiles += p_generationResult.getLostFiles().size();
		if (p_generationResult.getDiagnostic().getSeverity() > Diagnostic.INFO) {
			// p_generationResult.getDiagnostic().
		}
		// _nbErrors += generationResult.getDiagnostic()
	}

	public int getNbFiles() {
		return _nbFiles;
	}

	public int getNbWarns() {
		return _nbWarns;
	}

	public int getNbErrors() {
		return _nbErrors;
	}

	public int getNbLostFiles() {
		return _nbLostFiles;
	}
}
