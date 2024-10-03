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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.dsl.overview.impl.OverviewFactoryImpl;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.ui.PacmanUIGeneratorsReport;
import fr.pacman.configuration.GenerateConfiguration;

/**
 * Here no concept of subproject as the creation start at the root path for the
 * project.
 * 
 * @author MINARM
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

	public static final String c_PROP_APPLICATION_NAME = "idAppli";
	public static final String c_PROP_PACKAGE_NAME = "package";
	public static final String c_PROP_NAMING_TYPE = "naming.type";
	public static final String c_PROP_CLIENT_TYPE = "client.type";
	public static final String c_PROP_SPI4J_VERSION = "spi4j.version";
	public static final String c_PROP_JAVA_VERSION = "java.version";
	public static final String c_DATEBASE_TYPES = "database.type";
	public static final String c_PROP_EJB_SRV = "ejbservice.enabled";
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

	/**
	 * Constructeur.
	 * 
	 * @param p_targetFolder
	 * @throws IOException
	 */
	public GenerateStart(final File p_targetFolder) throws IOException {
		setResources(Collections.emptyList());
		setRootPath(p_targetFolder.getAbsolutePath());
	}

	/**
	 * Do the generation for project creation and copy files if needed (SWING /
	 * EXPORT REST).
	 */
	public void updateWithSafranProject() throws IOException {
		super.generate();
		copyFiles();
	}

	@Override
	protected List<String> getTemplates() {
		List<String> v_templates = new ArrayList<>();
		v_templates.add("generateStart");
		return v_templates;
	}

	@Override
	public String getSubProjectName() {
		return null;
	}

	@Override
	public String getModuleQualifiedName() {
		return "fr::pacman::start::generateStart";
	}

	@Override
	protected Map<String, String> getOptions() {
		Map<String, String> v_res = new LinkedHashMap<>();
		v_res.put(AcceleoUtil.LOG_URI_OPTION, "acceleo.log");
		v_res.put(AcceleoUtil.NEW_LINE_OPTION, System.lineSeparator());
		return v_res;
	}

	@Override
	protected List<EObject> getValues(IQualifiedNameQueryEnvironment queryEnvironment,
			Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels) {

		final List<EObject> values = new ArrayList<>();
		values.add(OverviewFactoryImpl.init().createRoot());
		return values;
	}

	/**
	 * Copie des fichiers binaires a cote des fichiers generes si le projet est de
	 * type SWING ou si le projet a des services web.
	 * 
	 * @throws IOException e
	 */
	private void copyFiles() throws IOException {

//		final File v_targetFolder = getTargetFolder();
//		final String v_targetProject = _startProperties.get(c_PROP_APPLICATION_NAME);
//
//		if ("SWING".equalsIgnoreCase(_startProperties.get(c_PROP_CLIENT_TYPE))) {
//			final File v_folder = new File(v_targetFolder, v_targetProject + "-javawebstart");
//			for (final String v_file : c_SWING_FILES) {
//				copyFile(v_file, v_folder);
//			}
//		}
//
//		if (Boolean.valueOf(_startProperties.get(c_PROP_GEN_WS))) {
//			final File v_folder = new File(v_targetFolder, v_targetProject + "-webapp");
//			for (final String v_file : c_SWAGGER_FILES) {
//				copyFile(v_file, v_folder);
//			}
//		}
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
			if (null != v_inputStream) {
				v_inputStream.close();
			}
		}
	}
}
