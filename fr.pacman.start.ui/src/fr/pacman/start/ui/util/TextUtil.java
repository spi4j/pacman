package fr.pacman.start.ui.util;

/**
 *
 * @author MINARM
 *
 */
public class TextUtil {

	/**
	 * Constructeur privé pour éviter l'instanciation de la classe/.
	 */
	private TextUtil() {
		// EMPTY
	}

	public static final String c_ERR = "Une erreur est servenue lors de la création du projet : ";
	public static final String c_ERR_DIALOG_TITLE = "ATTENTION";
	public static final String c_ERR_PROPERTY = "Impossible de charger la propriété.";
	public static final String c_ERR_WIZARD = "Impossible de charger le wizard";
	public static final String c_ERR_PACKAGE_VALID = "Le package de l'application n'est pas renseigné.";
	public static final String c_ERR_AUTHOR_EMPTY = "Le nom de l'auteur pour les classes n'est pas renseigné.";
	public static final String c_ERR_PROJECT_EMPTY = "Le nom du projet n'est pas renseigné.";
	public static final String c_ERR_PROJECT_EXIST = "Le projet existe déjà dans l'espace de travail (ou sur le disque).";
	public static final String c_ERR_LIBRARIES_EMPTY = "Une (ou plusieurs) rubrique(s) dans l'ajout des librairies n'est pas renseignée ou n'est pas valide.";
	public static final String c_ERR_SPI4J_VERSION = "Le wizard ne peut charger la version en cours de SPI4J, vous devrez la saisir manuellement dans le fichier pom.xml du projet parent.";
	public static final String c_ERR_BUNDLE_MAVEN = "Le plugin Maven d'import de projet n'est pas installé. Ce plugin est nécessaire pour l'utilisation de Safr@n Pacman.";
	public static final String c_ERR_TITLE_CONFIG = "Pacman : configuration";
	public static final String c_ERR_SQL_TABLE_PREFIX_VALID = "Le préfixe des tables SQL n'est pas valide ( format : xxx_ ).";
	public static final String c_ERR_SQL_TABLE_SCHEMA_VALID = "Le schéma des tables SQL n'est pas valide ( format : xxx. ).";
	public static final String c_ERR_SQL_REQUIREMENT_PREFIX_VALID = "Le préfixe pour les requirements n'est pas valide";
	public static final String c_ERR_ORACLE_VALID = "Il est impossible de générer pour deux versions différentes d'Oracle.";
	public static final String c_ERR_SQL_ADD_FIELDS = "Veuillez remplir le nom pour les champs SQL additionnels.";
	public static final String c_ERR_WS_RD_STATE = "Microservices, la génération des services Web doit être cochée.";
	public static final String c_ERR_DB_NONE = "Le projet utilise une base de données mais aucune base n'a été cochée (H2 ne fonctionne qu'en mode embarqué)";
	public static final String c_ERR_DB_CHOICE = "Pour l'option base embarquée, seule la base de type H2 est authorisée.";

	public static final String c_WZD_MAIN_WINDOW_TITLE = "Pacman : générateur de code JAVA";
	public static final String c_WZD_MONITOR_PROJECT_CREATE = "Creation du projet Safr@n";
	public static final String c_WZD_MONITOR_PROJECT_SAFRAN = "Mise à jour du projet vers Safr@n";
	public static final String c_WZD_MONITOR_PROJECT_MAVEN = "Ajout de la nature Maven au projet";
	public static final String c_WZD_MONITOR_PROJECT_SUB = "Configuration des sous projets";
	public static final String c_WZD_MONITOR_PROJECT_EMF = "Ajout de la nature EMF au projet de modélisation";
	public static final String c_WZD_MONITOR_PROJECT_EMFT = c_WZD_MONITOR_PROJECT_EMF + " - Tentative : ";

	public static final String c_LBL_YES = "OUI";
	public static final String c_LBL_NO = "NON";
	public static final String c_LBL_NONE = "none";
	public static final String c_LBL_CURRENT = "current";
	public static final String c_LBL_DIALOG = "Propriétés du projet Safr@n";
	public static final String c_LBL_TITLE = "Nouveau projet Safr@n";
	public static final String c_LBL_TITLE_MAVEN = "Pacman start";
	public static final String c_LBL_DESCRIPTION = "Entrez les paramètres pour la création du projet";
	public static final String c_LBL_TB_CONFIG = "Configuration";
	public static final String c_LBL_TB_GENERATE = "Génération";
	public static final String c_LBL_TB_MODELING = "Modélisation";
	public static final String c_LBL_TB_ODATABASE = "Options BDD";
	public static final String c_LBL_TB_LIBRARY = "Librairies";
	public static final String c_LBL_TB_VERSIONNING = "Versions";
	public static final String c_LBL_TB_VARIOUS = "Divers";
	public static final String c_LBL_DEFAULT_AUTHOR = "Safr@n";
	public static final String c_LBL_XTOSUP = "Supp. logique";
	public static final String c_LBL_XDMAJ = "Mise à jour";
	public static final String c_LBL_PACKAGE_NAME = "Package de l'application";
	public static final String c_LBL_TABLE_PREFIX = "Préfixe pour l'ensemble des tables";
	public static final String c_LBL_TABLE_SPACE = "Tablespace pour les index (Oracle)";
	public static final String c_LBL_TABLE_SCHEMA = "Schéma pour l'ensemble des tables";
	public static final String c_LBL_COLUMNS_FREE = "Champs supplémentaires libres";
	public static final String c_LBL_LIBRARIES_FREE = "Librairies supplémentaires libres";
	public static final String c_LBL_COLUMNS_SUPMAJ = "Champs supplémentaires spécifiques";
	public static final String c_LBL_PROJECT_NAME = "Nom du projet";
	public static final String c_LBL_JAVA_NAMING = "Type de nommage du code Java";
	public static final String c_LBL_HTTP_EMBEDDED_SERVER = "Serveur HTTP embarqué";
	public static final String c_LBL_IHM_TYPE = "Type d'IHM";
	public static final String c_LBL_JAVA_VERSION = "Version pour la compilation JAVA";
	public static final String c_LBL_SPI4J_VERSION = "Version de la librairie SPI4J";
	public static final String c_LBL_BDD = "Type(s) de base(s) de données";
	public static final String c_LBL_BDD_USE = "Utilisation de base(s) de données";
	public static final String c_LBL_BT_SELECT_ALL = "Tout sélectionner";
	public static final String c_LBL_BT_UNSELECT_ALL = "Tout désélectionner";
	public static final String c_LBL_MODEL = "Fichiers de modélisation à générer";
	public static final String c_LBL_TU_CRUD = "Tests unitaires pour les services CRUD";
	public static final String c_LBL_GEN_BATCH = "Implémentation de traitements batchs";
	public static final String c_LBL_CREATE_GE = "Création d'application de gestion des entités (CRUD)";
	public static final String c_LBL_SIMPLE_ARCH = "Architecture Simplifiée (Sans couche DTO)";
	public static final String c_LBL_REQUIREMENTS = "Implémentation des services des exigences";
	public static final String c_LBL_SOA_WS = "Génération de Webservices (SOAP/REST)";
	public static final String c_LBL_SOA_WMS = "Génération de Microservices (REST)";
	public static final String c_LBL_SECURITY = "Implémentation de la sécurité";
	public static final String c_LBL_HEALTH_API = "Génération de l'API d'observabilité";
	public static final String c_LBL_TEST = "Génération de tests de comportement";
	public static final String c_LBL_TEST_TOOLTIP = "Option valable pour l'IHM de type GWT Ou SWING";
	public static final String c_LBL_EJB = "Génération des services type EJB";
	public static final String c_LBL_CARRIAGE_RETURN = "Retour chariot souhaité";
	public static final String c_LBL_USE_CONFIG = "Fichiers de config gérés par Spi4j";
	public static final String c_LBL_CARRIAGE_RETURN_W = "Windows (\\r\\n)";
	public static final String c_LBL_CARRIAGE_RETURN_L = "Linux (\\n)";
	public static final String c_LBL_PROJECT_LIBRARY = "Le projet est une librairie";
	public static final String c_LBL_PROJECT_LIBRARY_RS = "Le projet est une librairie 'Import REST'";
	public static final String c_LBL_LIBRARY_NAME = "Nom de la librairie (ArtifactId)";
	public static final String c_LBL_LIBRARY_PACKAGE = "Package de la librairie (GroupId)";
	public static final String c_LBL_LIBRARY_VERSION = "Version de la librairie";
	public static final String c_LBL_LIBRARY_PREFIX = "Préfixe pour les tables de la base de données";
	public static final String c_LBL_LIBRARY_SCHEMA = "Schéma pour la base de données";
	public static final String c_LBL_LIBRARY_DATABASE = "Accède à la base de données";
	public static final String c_LBL_MODEL_ENTITY = "Modélisation Entité";
	public static final String c_LBL_MODEL_DATABASE = "Modélisation Base de données";
	public static final String c_LBL_MODEL_REQUIREMENT = "Modélisation Requirement";
	public static final String c_LBL_MODEL_CINEMATIC = "Modélisation Cinématique";
	public static final String c_LBL_MODEL_SOA = "Modélisation Soa";
	public static final String c_LBL_SQL_COLUMN = "Champ n° ";
	public static final String c_LBL_USE_LIBRARY = "Librairie n° ";
	public static final String c_LBL_SQL_COLUMN_DEFAULT = "Valeur par défaut";
	public static final String c_LBL_SQL_COLUMN_NAME = "Nom";
	public static final String c_LBL_SQL_COLUMN_TYPE = "Type";
	public static final String c_LBL_SQL_COLUMN_SIZE = "Taille";
	public static final String c_LBL_SQL_COLUMN_NULL = "Accepte la valeur nul";
	public static final String c_LBL_SQL_COLUMN_COMMENT = "Commentaire";
	public static final String c_LBL_AUTHOR = "Auteur";
	public static final String c_LBL_DEBUG = "Mode debug";
	public static final String c_LBL_FETCHING = "Fetching Strategy";
	public static final String c_LBL_LOG4J = "Génération des fichiers log4j2.xml";
	public static final String c_LBL_HK2 = "Api Rest - Utilisation injection CDI";
	public static final String c_LBL_EH2 = "Base H2 embarquée";
	public static final String c_LBL_REQUIREMENT_PREFIX = "Requirements - Préfixe";
	public static final String c_LBL_REQUIREMENT_LEVEL = "Requirements - Niveaux";
	public static final String c_LBL_REQUIREMENT_VERSION = "Requirements - Init. Version";
	public static final String c_LBL_CRUD = "Génération application CRUD";
	public static final String c_TIP_XTOSUP = "Suppression logique";
	public static final String c_TIP_XDMAJ = "Date de mise à jour";
	
	public static final String c_TLP_PROJECT_NAME = "Le nom du projet avec lequel le squelette de l'application sera généré.";
	public static final String c_TLP_IHM_TYPE = "L'interface client pour le projet.";
	public static final String c_TLP_BDD_USE = "Inqique si le projet est en relation avec une base de données.";
	public static final String c_TLP_TEST = "Utilisation de scripts de type Jbehave pour les tests de comportement.";
	public static final String c_TLP_PROJECT_LIBRARY = "Le projet est un fichier jar réutilisable dans un autre projet.";
	public static final String c_TLP_COLUMNS_FREE = "Ajouter automatiquement un champ pour l'ensemble des tables.";
	public static final String c_TLP_AUTHOR = "Le nom de l'auteur ou de l'organisme à indiquer pour l'ensemble des commentaires.";
	public static final String c_TLP_HK2 = "Uilisation CDI HK2 (Jersey) ou de simple fabriques (pattern factory).";
	public static final String c_TLP_REQUIREMENT_PREFIX = "Ajout automatique d'un préfixe devant l'ensemble de règles de gestion.";
	public static final String c_TLP_CRUD = "Accessible uniquement si activation des services REST / SOAP.";
	public static final String c_TLP_REQUIREMENT_LEVEL = "";
	public static final String c_TLP_NO = "";

	public static final String c_TLP_USE_CONFIG = "Utilisation SPI4J pour la gestion des fichiers de configuration de l'application."
			+ "\nDifférents formats supportés (xml, json, yaml, etc...)";
	public static final String c_TLP_COLUMNS_SUPMAJ = "Ajouter automatiquement un champ pour l'ensemble des tables."
			+ "\nTimestamp de mise à jour (et/ou) Indicateur de suppression (delete physique interdit pour la base).";
	public static final String c_TLP_BDD_TABLE_PREFIX = "Ajouter automatiquement un préfixe devant le nom de l'ensemble des tables."
			+ "\nLa syntaxe du préfixe est avec toujours un underscore : 'XX_'.";
	public static final String c_TLP_BDD_SCHEMA = "Ajouter automatiquement un schema devant le nom de l'ensemble des tables."
			+ "\nLa syntaxe du schéma est toujours avec un point : 'XX.'.";
	public static final String c_TLP_LIBRARIES_FREE = "Le projet utilise des librairies externes à rajouter\n"
			+ "automatiquement comme dépendance dans les fichiers Maven.";
	public static final String c_TLP_PROJECT_LIBRARY_RS = "Le projet est un fichier jar dédié aux services REST, réutilisable dans un autre projet."
			+ "La modélisation est déduite d'un import de fichier au format swagger\n";
	public static final String c_TLP_PACKAGE_NAME = "Le nom pour le package racine de l'application sous lequel"
			+ "\npositionner les classes et l'ensemble des autres packages.";
	public static final String c_TLP_MODEL = "Liste des fichiers de modélisation à générer."
			+ "\nIl est toujours possible de rajouter les fichiers à postériori.";
	public static final String c_TLP_BDD = "La (ou les) base(s) de données à utiliser dans le cadre du projet."
			+ "\nLa base H2 est toujours utilisée pour les tests unitaires";
	public static final String c_TLP_JAVA_NAMING = "La norme de nommage pour l'ensemble des méthode et variables,"
			+ "\nSUN : nommage standard, SAFRAN : nommage spécifique et plus détaillé.";
	public static final String c_TLP_SECURITY = "Utilisation de la couche spécifique spi4j pour la gestion "
			+ "des rôles et des utilisateurs.";
	public static final String c_TLP_HEALTH_API = "Génère automatique une API rest pour létat de santé de l'application.";
	public static final String c_ERR_MODEL_CONFIG = "Impossible de finaliser la configuration du projet de modélisation."
			+ System.getProperty("line.separator") + "Veuillez terminer manuellement la configuration "
			+ "(activation des 'Viewpoints' et création des models. Cause de l'erreur : ";
	public static final String c_ERR_MODEL = "Impossible de finaliser la configuration du projet de modélisation."
			+ System.getProperty("line.separator") + "Veuillez terminer manuellement la configuration ";
	
	public static final String c_DLG_HEALTH_TITLE = "Activation de l'observabilité";
	public static final String c_DLG_HEALTH_QUESTION = "L'activation de l'api d'observabilité entraine l'activation automatique de la génération de services web, confirmez vous cette option ?";
	

}
