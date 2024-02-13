package fr.pacman.soapifirst.ui.popup;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILog;

import fr.pacman.commons.ui.AcceleoGenerateSafranAction;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.soapifirst.ui.Activator;
import fr.pacman.soapifirst.ui.common.GenerateSoaRsFromFile;

/**
 * Soa import swagger code generation.
 */
public class AcceleoGenerateSoaRsAction extends AcceleoGenerateSafranAction<IFile> {

	@Override
	protected String getPluginId() {
		return Activator.c_PLUGIN_ID;
	}

	@Override
	protected ILog getLogger() {
		return Activator.getDefault().getLog();
	}

	@Override
	protected SafranGenerator_Abs<IFile> getSafranGenerator(IFile p_modelURI, List<? extends Object> p_arguments) {
		return new GenerateSoaRsFromFile(p_modelURI, p_arguments);
	}
}
