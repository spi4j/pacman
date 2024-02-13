package fr.pacman.start.ui.util;

import java.util.List;

/**
 * Petite classe utilitaire pour le stockage des informations dans le cadre de
 * la generation des modelisations, des 'viewpoints' et des representations pour
 * Sirius.
 * 
 * @author MINARM
 *
 */
public class SiriusModelDescriptor {

	String _modelExt;
	List<String> _viewpointURIs;
	List<String> _descIDs;

	SiriusModelDescriptor(final String p_modelExt, List<String> p_lstDescIDs, final List<String> p_lstViewURIs) {

		_modelExt = p_modelExt;
		_descIDs = p_lstDescIDs;
		_viewpointURIs = p_lstViewURIs;
	}

	public List<String> get_viewURIs() {
		return _viewpointURIs;
	}

	public String get_modelExt() {
		return _modelExt;
	}

	public List<String> get_descIDs() {
		return _descIDs;
	}
}
