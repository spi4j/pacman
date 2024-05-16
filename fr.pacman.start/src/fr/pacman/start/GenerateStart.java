/*******************************************************************************
 * Copyright (c) 2008, 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.start;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.overview.impl.OverviewFactoryImpl;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.configuration.GenerateConfiguration;

/**
 * Entry point of the 'Entity' generation module.
 * 
 * @generated NOT
 */
public class GenerateStart extends PacmanGenerator_Abs {
	/**
	 * Liste des fichiers binaires qui seront copies par Pacman Start vers le projet
	 * cible dans le cadre d'un projet SWING.
	 */
	private static final List<String> c_SWING_FILES = Arrays.asList("lib/ant-contrib-0.6.jar", "lib/Pack200Task.jar",
			"lib/setModificationTimeAntTask.jar", "lib/setModificationTimeFromManifestAntTask.jar",
			"src/main/webapp/WEB-INF/lib/jnlp-servlet.jar", "src/main/webapp/favicon.ico",
			"src/main/webapp/safran-big.png", "src/main/webapp/safran-small.png", "src/main/webapp/safran.png");

	/**
	 * Liste des fichiers qui seront copiés par Pacman Start vers le projet cible
	 * dans le cadre d'un projet REST (Export uniquement) Pour l'instant pas de Zip,
	 * éviter de charger encore une dépendance suplémentaire. A voir toutefois si
	 * plus quant même pas plus propre de copier un zip et de le dézipper dans la
	 * cible ...
	 */
	private static final List<String> c_SWAGGER_FILES = Arrays.asList("src/main/webapp/swagger/favicon-16x16.png",
			"src/main/webapp/swagger/favicon-32x32.png", "src/main/webapp/swagger/index.html",
			"src/main/webapp/swagger/oauth2-redirect.html", "src/main/webapp/swagger/swagger-ui-bundle.js",
			"src/main/webapp/swagger/swagger-ui-bundle.js.map", "src/main/webapp/swagger/swagger-ui-es-bundle-core.js",
			"src/main/webapp/swagger/swagger-ui-es-bundle-core.js.map",
			"src/main/webapp/swagger/swagger-ui-es-bundle.js", "src/main/webapp/swagger/swagger-ui-es-bundle.js.map",
			"src/main/webapp/swagger/swagger-ui-standalone-preset.js",
			"src/main/webapp/swagger/swagger-ui-standalone-preset.js.map", "src/main/webapp/swagger/swagger-ui.css",
			"src/main/webapp/swagger/swagger-ui.css.map", "src/main/webapp/swagger/swagger-ui.js",
			"src/main/webapp/swagger/swagger-ui.js.map");

	/**
	 * The name of the module.
	 */
	public static final String c_MODULE_FILE_NAME = "generateStart";

	/**
	 * The name of the templates that are to be generated.
	 */
	public static final String[] c_TEMPLATE_NAMES = { "generateStart" };

	public static final String c_PROP_APPLICATION_NAME = "idAppli";
	public static final String c_PROP_PACKAGE_NAME = "package";
	public static final String c_PROP_NAMING_TYPE = "naming.type";
	public static final String c_PROP_CLIENT_TYPE = "client.type";
	public static final String c_PROP_SPI4J_VERSION = "spi4j.version";
	public static final String c_PROP_JAVA_VERSION = "java.version";
	public static final String c_DATEBASE_TYPES = "database.type";
	public static final String c_PROP_EJB_SRV = "ejbservice.enabled";
	public static final String c_PROP_USE_BATCH = "batch.layer.enabled";
	public static final String c_PROP_REQ_SRV = "servicerequirements.enabled";
	public static final String c_PROP_SRV_CRUD_TU = "tests.crud.enabled";
	public static final String c_PROP_CR_FORMAT = "new.line";
	public static final String c_PROP_GEN_MATCHING = "matching.layer.enabled";
	public static final String c_PROP_USE_CONFIG = "config.files.frwk.enabled";
	public static final String c_PROP_APP_CRUD = "application.crud.enabled";
	public static final String c_PROP_GEN_WS = "ws.layer.enabled";
	public static final String c_PROP_GEN_WMS = "wms.layer.enabled";
	public static final String c_PROP_GEN_BDD = "tests.bdd.enabled";
	public static final String c_PROP_IS_LIBRARY = "project.isLibrary";
	public static final String c_PROP_IS_LIBRARY_RS = "project.isLibraryRs";
	public static final String c_PROP_USE_LIBRARIES = "project.useLibraries";
	public static final String c_PROP_ADD_LIBRARY_NAME = "library.name";
	public static final String c_PROP_ADD_LIBRARY_PACKAGE = "library.package";
	public static final String c_PROP_ADD_LIBRARY_VERSION = "library.version";
	public static final String c_PROP_ADD_LIBRARY_DATABASE = "library.database";
	public static final String c_PROP_AUTHOR_NAME = "author";
	public static final String c_PROP_INJECT_HK2 = "ws.layer.hk2.enabled";
	public static final String c_PROP_REQUIREMENT_LEVEL = "requirement.category.base.level";
	public static final String c_PROP_REQUIREMENT_PREFIX = "requirement.prefix";
	public static final String c_PROP_MODE_DEBUG = "pacman.mode.debug";
	public static final String c_PROP_USE_HEALTH_API = "ws.health.enabled";
	public static final String c_PROP_LOG4J = "log4j.enabled";
	public static final String c_PROP_H2_EMBEDDED = "h2.embedded.enabled";
	public static final String c_PROP_FETCHING_STRATEGY = "fetchingstrategy.enabled";
	public static final String c_PROP_USE_SECURITY = "spi4j.security.enabled";
	public static final String c_PROP_SQL_TABLE_PREFIX = "server.sql.table.prefix";
	public static final String c_PROP_SQL_TABLE_SCHEMA = "server.sql.table.schema";
	public static final String c_PROP_REQUIREMENT_VERSION = "test.requirement.versionning.initial";
	public static final String c_PROP_SQL_TABLESPACE = "server.sql.oracle.index.tablespace";
	public static final String c_PROP_HTTP_EMBEDDED_SERVER = "http.embedded.server";
	public static final String c_PROP_LIB_ADD_JAR = "server.jar.additional";
	public static final String c_PROP_LIB_ADD_JAR_BASE = "server.jar.additional.{$name}";
	public static final String c_PROP_LIB_ADD_JAR_NAME = "server.jar.additional.{$name}.name";
	public static final String c_PROP_LIB_ADD_JAR_PACKAGE = "server.jar.additional.{$name}.package";
	public static final String c_PROP_LIB_ADD_JAR_VERSION = "server.jar.additional.{$name}.version";
	public static final String c_PROP_LIB_ADD_JAR_DATABASE = "server.jar.additional.{$name}.database";	
	public static final String c_PROP_LIB_ADD_JAR_PREFIX = "server.jar.additional.{$name}.table.prefix";
	public static final String c_PROP_LIB_ADD_JAR_SCHEMA = "server.jar.additional.{$name}.database.schema";
	public static final String c_PROP_SQL_ADD_COLUMNS = "server.sql.table.additional_fields";
	public static final String c_PROP_SQL_ADD_COLUMNS_BASE = "server.sql.table.additional_field.{$name}";
	public static final String c_PROP_SQL_ADD_COLUMNS_TYPE = "server.sql.table.additional_field.{$name}.type";
	public static final String c_PROP_SQL_ADD_COLUMNS_DEFAULT = "server.sql.table.additional_field.{$name}.default";
	public static final String c_PROP_SQL_ADD_COLUMNS_SIZE = "server.sql.table.additional_field.{$name}.size"; 
	public static final String c_PROP_SQL_ADD_COLUMNS_NAME = "server.sql.table.additional_field.{$name}.name";
	public static final String c_PROP_SQL_ADD_COLUMNS_COMMENT = "server.sql.table.additional_field.{$name}.comment";  
	public static final String c_PROP_SQL_ADD_COLUMNS_NOT_NULL = "server.sql.table.additional_field.{$name}.notnull";
	
	// Evite pour la couche UI d'avoir a faire l'import du plugin configuration.
	public static final String c_PROP_PROJECT_VERSION = GenerateConfiguration.c_PROP_PROJECT_VERSION;

	private Map<String, String> _startProperties;

	private String _modelPath;

	/**
	 * Allows the public constructor to be used. Note that a generator created this
	 * way cannot be used to launch generations before one of initialize(EObject,
	 * File, List) or initialize(URI, File, List) is called.
	 * <p>
	 * The main reason for this constructor is to allow clients of this generation
	 * to call it from another Java file, as it allows for the retrieval of
	 * {@link #getProperties()} and {@link #getGenerationListeners()}.
	 * </p>
	 */
	public GenerateStart() {
		super();
	}

	/**
	 * This allows clients to instantiates a generator with all required
	 * information.
	 * 
	 * @param p_targetFolder This will be used as the output folder for this
	 *                       generation : it will be the base path against which all
	 *                       file block URLs will be resolved.
	 * @param p_properties   If the template which will be called requires more than
	 *                       one argument taken from the model, pass them here.
	 * @throws IOException This can be thrown in three scenarios : the module cannot
	 *                     be found, it cannot be loaded, or the model cannot be
	 *                     loaded.
	 */
	public GenerateStart(final File p_targetFolder, final Map<String, String> p_properties) throws IOException {

		_startProperties = p_properties;
		_modelPath = p_targetFolder.getAbsolutePath() + File.separator + _startProperties.get(c_PROP_APPLICATION_NAME)
				+ "-model";

		PacmanPropertiesManager.initProperties(_modelPath, p_properties);
		initialize((EObject) OverviewFactoryImpl.init().createRoot(), p_targetFolder, Collections.emptyList());
	}

	/**
	 * This can be used to launch the generation from a standalone application.
	 * 
	 * @param p_args Arguments of the generation.
	 */
	public static void main(final String[] p_args) {
		try {
			if (p_args.length < 2) {
				throw new RuntimeException("Arguments not valid : {model, folder}.");
			} else {
				// final URI v_modelURI = URI.createFileURI(p_args[0]);
				final File v_folder = new File(p_args[1]);
				final List<String> v_arguments = new ArrayList<String>();
				v_arguments.add(p_args[0]);
				for (int v_i = 2; v_i < p_args.length; v_i++) {
					v_arguments.add(p_args[v_i]);
				}
				final Map<String, String> v_startProperties = new HashMap<String, String>();
				final String v_projectName = System.getProperty("projectName");
				v_startProperties.put(GenerateStart.c_PROP_APPLICATION_NAME, v_projectName);
				final GenerateStart v_generator = new GenerateStart(v_folder, v_startProperties);

				v_generator.doGenerate(new BasicMonitor());
			}
		} catch (final IOException v_e) {
			v_e.printStackTrace(); 
		}
	}

	@Override
	public void doGenerate(final Monitor p_monitor) throws IOException {

		super.doGenerate(p_monitor);

		// Copie de fichiers (SWING / EXPORT REST).
		copyFiles();

		// Ecriture des proprietes dans les '.properties'.
		PacmanPropertiesManager.exit();
	}

	/**
	 * Copie des fichiers binaires a cote des fichiers generes si le projet est de
	 * type SWING ou si le projet a des services web.
	 * 
	 * @throws IOException e
	 */
	private void copyFiles() throws IOException {

		final File v_targetFolder = getTargetFolder();
		final String v_targetProject = _startProperties.get(c_PROP_APPLICATION_NAME);

		if ("SWING".equalsIgnoreCase(_startProperties.get(c_PROP_CLIENT_TYPE))) {
			final File v_folder = new File(v_targetFolder, v_targetProject + "-javawebstart");
			for (final String v_file : c_SWING_FILES) {
				copyFile(v_file, v_folder);
			}
		}

		if (Boolean.valueOf(_startProperties.get(c_PROP_GEN_WS))) {
			final File v_folder = new File(v_targetFolder, v_targetProject + "-webapp");
			for (final String v_file : c_SWAGGER_FILES) {
				copyFile(v_file, v_folder);
			}
		}
	}

	/**
	 * Copie un fichier du plugin a cote des fichiers generes.
	 * 
	 * @param p_fileName String
	 * @param p_folder   File
	 * @throws IOException e
	 */
	private void copyFile(final String p_fileName, final File p_folder) throws IOException {

		final Path v_path = new Path("resources/" + p_fileName);
		final URL v_resourceUrl = FileLocator.find(Platform.getBundle(Activator.c_PLUGIN_ID), v_path, null);
		final InputStream v_inputStream = v_resourceUrl.openStream();
		try {
			final File v_file = new File(p_folder, p_fileName);
			v_file.getParentFile().mkdirs();
			final OutputStream v_outputStream = new FileOutputStream(v_file);
			try {
				final byte[] v_buffer = new byte[4096];
				int v_read = v_inputStream.read(v_buffer);
				while (v_read != -1) {
					v_outputStream.write(v_buffer, 0, v_read);
					v_read = v_inputStream.read(v_buffer);
				}
			} finally {
				v_outputStream.close();
			}
		} finally {
			if(null != v_inputStream) {
				v_inputStream.close();
			}
		}
	}

	@Override
	public String[] getModuleTemplates() {
		return c_TEMPLATE_NAMES;
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
	public boolean getSwitchQueryCache() {
		return Boolean.TRUE;
	}
}
