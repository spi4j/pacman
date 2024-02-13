package fr.pacman.start.ui.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contient une liste de constantes utilisees pour le wizard dans le cadre de la
 * creation d'une nouvelle application.
 * 
 * @author MINARM
 */
public class PacmanConfig {

	/**
	 * Constructeur prive pour eviter l'instanciation de la classe.
	 */
	private PacmanConfig() {
		super();
	}

	public static final String c_MAVEN_NATURE_ID = "org.eclipse.m2e.core.maven2Nature";
	public static final String c_MAVEN_IMPORT_WIZARD_ID = "org.eclipse.m2e.core.wizards.Maven2ImportWizard";
	public static final String c_HTTP_NONE = "";
	public static final String c_HTTP_JETTY = "Jetty";
	public static final String c_HTTP_TOMCAT = "Tomcat";
	public static final String c_HTTP_TOMEE = "Tomee";
	public static final String c_PROJECT_MODEL_EXTENSION = "-model";
	public static final String c_PROJECT_PACKAGE_DEFAULT = "fr.";
	public static final String c_CARRIAGE_RETURN_W = "WINDOWS";
	public static final String c_CARRIAGE_RETURN_L = "UNIX";
	public static final String c_BOOL_STR_YES = "true";
	public static final String c_BOOL_STR_NO = "false";
	public static final String c_BOOL_STR_1 = "1";
	public static final String c_BOOL_STR_0 = "0";
	public static final String c_DEFAULT_SQL_DATE = "current_date";
	public static final String c_VERSION_INIT_CURRENT = "current";
	public static final String c_VERSION_INIT_NONE = "none";
	public static final String c_IHM_TYPE_NO_DBDD = "JSF";
	public static final String c_MODEL_AIRD_NAME = "representations.aird";
	public static final String c_JAVA_11 = "version 11";
	public static final String c_JAVA_12 = "version 12";
	public static final String c_JAVA_13 = "version 13";
	public static final String c_JAVA_14 = "version 14";
	public static final String c_JAVA_15 = "version 15";
	public static final String c_JAVA_16 = "version 16";
	public static final String c_JAVA_17 = "version 17";
	public static final String c_JAVA_18 = "version 18";
	public static final String c_JAVA_19 = "version 19";
	public static final String c_JAVA_20 = "version 20";
	public static final String c_NORME_SUN = "SUN";
	public static final String c_NORME_SAFRAN = "SAFRAN";
	public static final String c_CLIENT_SWING = "SWING";
	public static final String c_CLIENT_GWT = "GWT";
	public static final String c_CLIENT_JSF = "JSF";
	public static final String c_CLIENT_JSP = "JSP";
	public static final String c_ORACLE = "Oracle";
	public static final String c_ORACLE_32 = "Oracle_32";
	public static final String c_POSTGRESQL = "PostgreSQL";
	public static final String c_MYSQL = "MySQL";
	public static final String c_H2 = "H2";
	public static final String c_MARIA_DB = "MariaDB";
	public static final String c_AUTHOR = "safr@n";
	public static final String c_REQUIREMENT_PREFIX = "REQ_";
	public static final String c_REQUIREMENT_LEVEL = "0";
	public static final String c_XTOPSUP = "xtopsup";
	public static final String c_XDMAJ = "xdmaj";

	// Initialisation des listes deroulantes.
	public static final String[] c_USE_BDD_LIST = new String[] { "OUI", "NON" };
	public static final String[] c_JAVA_NAMING_LIST = new String[] { c_NORME_SUN, c_NORME_SAFRAN };
	public static final String[] c_IHM_TYPE_LIST = new String[] { "", c_CLIENT_SWING, c_CLIENT_GWT, c_CLIENT_JSF,
			c_CLIENT_JSP };
	
	public static final String[] c_JAVA_VERSION_LIST = new String[] { c_JAVA_11, c_JAVA_12, c_JAVA_13, c_JAVA_14,
			c_JAVA_15, c_JAVA_16, c_JAVA_17, c_JAVA_18, c_JAVA_19, c_JAVA_20 };
	
	public static final String[] c_HTTP_SERVER_LIST = new String[] { c_HTTP_NONE, c_HTTP_TOMCAT };
	public static final String[] c_SQL_TYPE_COLUMN_LIST = new String[] { "Binary", "Boolean", "Date", "Double", "Float",
			"Integer", "Long", "String", "Time", "Timestamp" };

	public static final String[] c_SQL_NB_COLUMNS_LIST = new String[] {

			"", "Un champ", "Deux champs", "Trois champs", "Quatre champs" };

	public static final String[] c_SQL_NB_LIBRARIES_LIST = new String[] {

			"", "Une librairie", "Deux librairies", "Trois librairies", "Quatre librairies" };

	// Liste des codes pour les fichiers de modelisation.
	public static final String c_MODEL_ENTITY_CODE = "E";
	public static final String c_MODEL_DATABASE_CODE = "D";
	public static final String c_MODEL_REQUIREMENT_CODE = "R";
	public static final String c_MODEL_CINEMATIC_CODE = "C";
	public static final String c_MODEL_SOA_CODE = "S";

	// Liste des constantes OBEO (privees donc oblige de les re-stocker ici).
	public static final String c_DATABASE_LOGICAL_PATHMAP = "pathmap://LogicalDBTypes";
	public static final String c_EXT_ENTITY = ".entity";
	public static final String c_EXT_DATABASE = "-mdl.database";
	public static final String c_EXT_REQUIREMENT = ".requirement";
	public static final String c_EXT_CINEMATIC = ".cinematic";
	public static final String c_EXT_SOA = ".soa";
	public static final String c_EXT_ENVIRONMENT = ".environment";
	public static final String c_RP_ENTITY_NAMESPACE = "Entities Namespaces Hierarchy";
	public static final String c_RP_ENTITY_PHYSICAL = "EV_Entities_PhysicalNames";
	public static final String c_RP_DATABASE_DIAGRAM = "Database Diagram";
	public static final String c_RP_REQUIREMENT_TABLE = "Requirements Table";
	public static final String c_RP_CINEMATIC_PACKAGE = "Package Diagram";
	public static final String c_RP_CINEMATIC_STRUCTURE = "UI Structure";
	public static final String c_RP_SOA_DIAGRAM = "SOA Diagram";
	public static final String c_RP_SOA_NAMESPACE = "DTO Namespaces Hierarchy";
	public static final String c_RP_SOA_PHYSICAL = "EV_DTO_PhysicalNames";
	public static final String c_RP_ENVIRONMENT_TABLE = "E_Environment";
	public static final String c_VP_URI_BASE = "viewpoint:/";
	public static final String c_VP_URI_DATABASE = "org.obeonetwork.dsl.database.design/Database";
	public static final String c_VP_URI_ENVIRONMENT = "org.obeonetwork.dsl.environment.properties/Environment Views";
	public static final String c_VP_URI_ENTITY = "org.obeonetwork.dsl.entity.design/Entity Views";
	public static final String c_VP_URI_ENTITY_CONSOLIDATED = "org.obeonetwork.is.design/Entity (Safr@n consolidated view)";
	public static final String c_VP_URI_REQUIREMENTS = "org.obeonetwork.dsl.requirement.design/Requirements";
	public static final String c_VP_URI_GRAAL_CONSOLIDATED = "org.obeonetwork.graal.design/Requirements (Graal consolidated view)";
	public static final String c_VP_URI_SOA_CONSOLIDATED = "org.obeonetwork.is.design/SOA (Safr@n consolidated view)";
	public static final String c_VP_URI_SOA = "org.obeonetwork.dsl.soa.design/SOA Views";
	public static final String c_VP_URI_CINEMATIC = "org.obeonetwork.dsl.cinematic.design/Cinematic Views";

	// Assemblage des versions pour Java
	public static final Map<String, String> c_JAVA_VERSION;

	// Liste des representations a ouvrir automatiquement a la creation du projet.
	public static final List<String> c_RP_TO_OPEN;

	// Assemblage des infos pour creation des modelisations.
	public static final Map<String, SiriusModelDescriptor> c_MODELS;

	// Initialisation de la map des versions Java.
	static {

		c_JAVA_VERSION = new HashMap<String, String>();

		c_JAVA_VERSION.put(c_JAVA_11, "11");
		c_JAVA_VERSION.put(c_JAVA_12, "12");
		c_JAVA_VERSION.put(c_JAVA_13, "13");
		c_JAVA_VERSION.put(c_JAVA_14, "14");
		c_JAVA_VERSION.put(c_JAVA_15, "15");
		c_JAVA_VERSION.put(c_JAVA_16, "16");
		c_JAVA_VERSION.put(c_JAVA_17, "17");
		c_JAVA_VERSION.put(c_JAVA_18, "18");
		c_JAVA_VERSION.put(c_JAVA_19, "19");
		c_JAVA_VERSION.put(c_JAVA_20, "20");
	}

	// Initialisation de la liste des representations a ouvrir.
	static {

		c_RP_TO_OPEN = new ArrayList<String>();

		c_RP_TO_OPEN.add(c_RP_ENTITY_NAMESPACE);
		c_RP_TO_OPEN.add(c_RP_SOA_DIAGRAM);
	}

	// Initialisation de la map des infos.
	static {

		c_MODELS = new HashMap<String, SiriusModelDescriptor>();

		c_MODELS.put(c_MODEL_ENTITY_CODE,
				new SiriusModelDescriptor(c_EXT_ENTITY, Arrays.asList(c_RP_ENTITY_NAMESPACE, c_RP_ENTITY_PHYSICAL),
						Arrays.asList(c_VP_URI_ENTITY, c_VP_URI_ENTITY_CONSOLIDATED, c_VP_URI_ENVIRONMENT)));

		c_MODELS.put(c_MODEL_DATABASE_CODE, new SiriusModelDescriptor(c_EXT_DATABASE,
				Arrays.asList(c_RP_DATABASE_DIAGRAM), Arrays.asList(c_VP_URI_DATABASE)));

		c_MODELS.put(c_MODEL_REQUIREMENT_CODE,
				new SiriusModelDescriptor(c_EXT_REQUIREMENT, Arrays.asList(c_RP_REQUIREMENT_TABLE),
						Arrays.asList(c_VP_URI_ENVIRONMENT, c_VP_URI_REQUIREMENTS, c_VP_URI_GRAAL_CONSOLIDATED)));

		c_MODELS.put(c_MODEL_CINEMATIC_CODE,
				new SiriusModelDescriptor(c_EXT_CINEMATIC,
						Arrays.asList(c_RP_CINEMATIC_STRUCTURE, c_RP_CINEMATIC_PACKAGE),
						Arrays.asList(c_VP_URI_CINEMATIC, c_VP_URI_ENVIRONMENT)));

		c_MODELS.put(c_MODEL_SOA_CODE,
				new SiriusModelDescriptor(c_EXT_SOA,
						Arrays.asList(c_RP_SOA_DIAGRAM, c_RP_SOA_NAMESPACE, c_RP_SOA_PHYSICAL),
						Arrays.asList(c_VP_URI_SOA_CONSOLIDATED, c_VP_URI_SOA, c_VP_URI_ENVIRONMENT)));
	}
}
