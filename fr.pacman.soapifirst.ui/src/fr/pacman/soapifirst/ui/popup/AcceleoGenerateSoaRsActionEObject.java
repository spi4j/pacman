package fr.pacman.soapifirst.ui.popup;

import java.util.List;

import org.eclipse.core.runtime.ILog;
import org.eclipse.emf.ecore.EObject;

import fr.pacman.commons.ui.AcceleoGenerateSafranAction;
import fr.pacman.commons.ui.SafranGenerator_Abs;
import fr.pacman.soapifirst.ui.Activator;
import fr.pacman.soapifirst.ui.common.GenerateSoaRsFromEObject;

public class AcceleoGenerateSoaRsActionEObject extends AcceleoGenerateSafranAction<EObject> {

	@Override
	protected String getPluginId() {

		return Activator.c_PLUGIN_ID;
	}

	@Override
	protected ILog getLogger() {

		return Activator.getDefault().getLog();
	}

	@Override
	protected SafranGenerator_Abs<EObject> getSafranGenerator(EObject p_modelURI, List<? extends Object> p_arguments) {

		return new GenerateSoaRsFromEObject(p_modelURI, p_arguments);
	}
}
