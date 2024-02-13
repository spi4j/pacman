package fr.pacman.soapifirst.ui.popup;

import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.emf.cdo.eresource.CDOResource;

import fr.pacman.commons.ui.AcceleoGenerateSafranAction;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.soapifirst.ui.Activator;
import fr.pacman.soapifirst.ui.common.GenerateSoaRsFromCDOResource;

/**
 * Soa import swagger code generation.
 */
public class AcceleoGenerateSoaRsActionCDOResource extends AcceleoGenerateSafranAction<CDOResource> {

	@Override
	protected String getPluginId() {
		return Activator.c_PLUGIN_ID;
	}

	@Override
	protected ILog getLogger() {
		return Activator.getDefault().getLog();
	}

	@Override
	protected SafranGenerator_Abs<CDOResource> getSafranGenerator(CDOResource p_modelURI,
			List<? extends Object> p_arguments) {
		return new GenerateSoaRsFromCDOResource(p_modelURI, p_arguments);
	}
}
