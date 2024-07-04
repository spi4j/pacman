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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.soa.Component;
import org.obeonetwork.dsl.soa.Service;

import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * Entry point of the 'GenerateCommonSoaRs' generation module.
 *
 * @generated NOT
 */
public class GenerateCommonSoaRs extends PacmanGenerator_Abs {

	/**
	 * The name of the module.
	 */
	public static final String c_MODULE_FILE_NAME = "generateCommonSoaRs";

	/**
	 * The name of the templates that are to be generated.
	 */
	private final String[] _TEMPLATE_NAMES;

	/**
	 * Allows the public constructor to be used. Note that a generator created this
	 * way cannot be used to launch generations before one of
	 * {@link #initialize(EObject, File, List)} or
	 * {@link #initialize(URI, File, List)} is called.
	 * <p>
	 * The main reason for this constructor is to allow clients of this generation
	 * to call it from another Java file, as it allows for the retrieval of
	 * {@link #getProperties()} and {@link #getGenerationListeners()}.
	 * </p>
	 *
	 * @generated
	 */
//    public GenerateCommonSoaRs() {
//        // Empty implementation
//    }

	/**
	 * This allows clients to instantiates a generator with all required
	 * information.
	 * 
	 * @param modelURI     URI where the model on which this generator will be used
	 *                     is located.
	 * @param targetFolder This will be used as the output folder for this
	 *                     generation : it will be the base path against which all
	 *                     file block URLs will be resolved.
	 * @param arguments    If the template which will be called requires more than
	 *                     one argument taken from the model, pass them here.
	 * @throws IOException This can be thrown in three scenarios : the module cannot
	 *                     be found, it cannot be loaded, or the model cannot be
	 *                     loaded.
	 * @generated
	 */
	public GenerateCommonSoaRs(URI p_modelURI, File p_targetFolder, List<? extends Object> p_arguments)
			throws IOException {

		super(p_modelURI, p_targetFolder, p_arguments);
		_TEMPLATE_NAMES = new String[] { "GenerateCommonSystem" };
	}

	/**
	 * Constructor.
	 * 
	 * @param p_input
	 * @param p_targetFolder
	 * @param p_arguments
	 * @throws IOException
	 */
	public GenerateCommonSoaRs(final EObject p_model, final File p_targetFolder,
			final List<? extends Object> p_arguments) throws IOException {

		super(p_model, p_targetFolder, p_arguments);

		if (p_model instanceof Component) {
			_TEMPLATE_NAMES = new String[] { "GenerateCommonComponent" };

		} else if (p_model instanceof Service) {
			_TEMPLATE_NAMES = new String[] { "GenerateCommonService" };

		} else {
			_TEMPLATE_NAMES = new String[] { "GenerateCommonSystem" };
			// throw new IllegalStateException("Type d'objet non géré : " +
			// p_model.getClass().getName());
		}
	}

	/**
	 * This can be used to launch the generation from a standalone application.
	 * 
	 * @param args Arguments of the generation.
	 * @generated
	 */
	public static void main(String[] args) {
		try {
			if (args.length < 2) {
				System.out.println("Arguments not valid : {model, folder}.");
			} else {
				URI modelURI = URI.createFileURI(args[0]);
				File folder = new File(args[1]);

				List<String> arguments = new ArrayList<String>();

				/*
				 * If you want to change the content of this method, do NOT forget to change the
				 * "@generated" tag in the Javadoc of this method to "@generated NOT". Without
				 * this new tag, any compilation of the Acceleo module with the main template
				 * that has caused the creation of this class will revert your modifications.
				 */

				/*
				 * Add in this list all the arguments used by the starting point of the
				 * generation If your main template is called on an element of your model and a
				 * String, you can add in "arguments" this "String" attribute.
				 */

				GenerateCommonSoaRs generator = new GenerateCommonSoaRs(modelURI, folder, arguments);

				/*
				 * Add the properties from the launch arguments. If you want to programmatically
				 * add new properties, add them in "propertiesFiles" You can add the absolute
				 * path of a properties files, or even a project relative path. If you want to
				 * add another "protocol" for your properties files, please override
				 * "getPropertiesLoaderService(AcceleoService)" in order to return a new
				 * property loader. The behavior of the properties loader service is explained
				 * in the Acceleo documentation (Help -> Help Contents).
				 */

				for (int i = 2; i < args.length; i++) {
					generator.addPropertiesFile(args[i]);
				}

				generator.doGenerate(new BasicMonitor());
			}
		} catch (IOException e) {
			e.printStackTrace();
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
		return Boolean.TRUE;
	}
}
