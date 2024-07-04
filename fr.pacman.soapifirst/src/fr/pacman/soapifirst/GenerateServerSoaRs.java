/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.soapifirst;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.soa.Component;
import org.obeonetwork.dsl.soa.Service;

import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * Entry point of the 'GenerateServerSoaRs' generation module.
 *
 * @generated NOT
 */
public class GenerateServerSoaRs extends PacmanGenerator_Abs {

	/**
	 * The name of the module.
	 */
	public static final String c_MODULE_FILE_NAME = "generateServerSoaRs";

	/**
	 * The name of the templates that are to be generated.
	 */
	private final String[] _TEMPLATE_NAMES;

	/**
	 * Constructor.
	 * 
	 * @param v_modelURI
	 * @param p_targetFolder
	 * @param p_arguments
	 * @throws IOException
	 */
	public GenerateServerSoaRs(final URI p_modelURI, final File p_targetFolder,
			final List<? extends Object> p_arguments) throws IOException {

		super(p_modelURI, p_targetFolder, p_arguments);
		_TEMPLATE_NAMES = new String[] { "GenerateServerSystem" };
	}

	/**
	 * Constructor.
	 * 
	 * @param p_input
	 * @param p_targetFolder
	 * @param p_arguments
	 * @throws IOException
	 */
	public GenerateServerSoaRs(final EObject p_model, final File p_targetFolder,
			final List<? extends Object> p_arguments) throws IOException {

		super(p_model, p_targetFolder, p_arguments);

		if (p_model instanceof Component) {
			_TEMPLATE_NAMES = new String[] { "GenerateServerComponent" };

		} else if (p_model instanceof Service) {
			_TEMPLATE_NAMES = new String[] { "GenerateServerService" };

		} else {
			_TEMPLATE_NAMES = new String[] { "GenerateServerSystem" };
			// throw new IllegalStateException("Type d'objet non géré : " +
			// p_model.getClass().getName());
		}
	}

	@Override
	public String[] getModuleTemplates() {
		return _TEMPLATE_NAMES;
	}

	@Override
	public String getModuleFileName() {
		return c_MODULE_FILE_NAME;
	}

	@Override
	public String getProjectName() {
		return Activator.c_PLUGIN_ID;
	}

	@Override
	protected boolean getSwitchQueryCache() {
		return Boolean.FALSE;
	}
}
