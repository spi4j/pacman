package fr.pacman.commons.convention.project;

import java.util.Map;
import java.util.Map.Entry;

import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.properties.PacmanProperty;
import fr.pacman.commons.properties.PacmanPropertyStatus_Enum;
import fr.pacman.commons.properties.PacmanPropertyStrategy_Abs;
import fr.pacman.commons.properties.PacmanPropertyTrigger_Enum;

/**
 * Liste de l'ensemble des proprietes pour une generation PacMan. 
 * Les proprietes presentes ici sont les proprietes generiques de l'application, 
 * celles pour les conventions de nommage sont dans le package '.rule'.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * Pour l'instant il y a de nombreuses redondances pour les variables qui 
 * concernent les clients. Cela multiplie le nombre de variables avec souvent 
 * les memes valeurs par defaut mais permet de laisser toute latitude sur la 
 * valeur par defaut. A voir dans un second temps si on simplifie.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * @author MINARM
 * 
 * @TODO Modifications necessaires.
 * 
 */
public class ProjectProperties extends PacmanPropertiesCategory_Abs
{
   private static final String c_idParam_version = "version";

   private static final String c_idParam_java_version = "java.version";

   private static final String c_idParam_spi4j_version = "spi4j.version";

   private static final String c_idParam_is_debug = "pacman.mode.debug";
   
   private static final String c_idParam_is_formatImports = "pacman.import.format";
   
   private static final String c_idParam_is_clearUserCode = "pacman.userCode.clear";
   
   private static final String c_idParam_is_clearUserCodeHash = "pacman.userCode.hash.clear";

   private static final String c_idParam_is_display_report = "pacman.report.display";

   private static final String c_idParam_is_formatClasses = "pacman.classes.format";
   
   private static final String c_idParam_isLazyLoading = "dsl.entity.isLazyLoading";

   private static final String c_idParam_appli = "idAppli";

   private static final String c_idParam_author = "author";

   private static final String c_idParam_package = "package";

   private static final String c_idParam_typeNaming = "naming.type";

   private static final String c_idParam_database_type = "database.type";

   private static final String c_idParam_client_type = "client.type";
   
   private static final String c_idParam_use_batch = "batch.layer.enabled";
   
   private static final String c_idParam_use_spi4j_config_frwk = "config.files.frwk.enabled";

   private static final String c_idParam_requirement_prefix = "requirement.prefix";
   
   private static final String c_idParam_server_libraries= "server.jar.additional";

   private static final String c_idParam_server_sql_table_prefix = "server.sql.table.prefix";
   
   private static final String c_idParam_server_sql_table_schema = "server.sql.table.schema";
   
   private static final String c_idParam_server_sql_table_xdmaj = "server.sql.table.additional_field.xdmaj";

   private static final String c_idParam_server_sql_table_xdmaj_name = "server.sql.table.additional_field.xdmaj.name";

   private static final String c_idParam_server_sql_table_xdmaj_type = "server.sql.table.additional_field.xdmaj.type";

   private static final String c_idParam_server_sql_table_xdmaj_size = "server.sql.table.additional_field.xdmaj.size";

   private static final String c_idParam_server_sql_table_xdmaj_notnull = "server.sql.table.additional_field.xdmaj.notnull";

   private static final String c_idParam_server_sql_table_xdmaj_comment = "server.sql.table.additional_field.xdmaj.comment";
   
   private static final String c_idParam_server_sql_table_xdmaj_default = "server.sql.table.additional_field.xdmaj.default";
   
   private static final String c_idParam_server_sql_table_xtopsup = "server.sql.table.additional_field.xtopsup";

   private static final String c_idParam_server_sql_table_xtopsup_name = "server.sql.table.additional_field.xtopsup.name";

   private static final String c_idParam_server_sql_table_xtopsup_type = "server.sql.table.additional_field.xtopsup.type";

   private static final String c_idParam_server_sql_table_xtopsup_default = "server.sql.table.additional_field.xtopsup.default";

   private static final String c_idParam_server_sql_table_xtopsup_notnull = "server.sql.table.additional_field.xtopsup.notnull";
   
   private static final String c_idParam_server_sql_table_xtopsup_comment = "server.sql.table.additional_field.xtopsup.comment";
   
   private static final String c_idParam_server_sql_table_xtopsup_size = "server.sql.table.additional_field.xtopsup.size";

   private static final String c_idParam_server_sql_oracle_index_tablespace = "server.sql.oracle.index.tablespace";
   
   private static final String c_idParam_server_sql_idsuffix_enabled = "server.sql.id.suffix_enabled";

   private static final String c_idParam_fetchingstrategy_enabled = "fetchingstrategy.enabled";

   private static final String c_idParam_servicerequirements_enabled = "servicerequirements.enabled";
   
   private static final String c_idParam_enums_package = "commons.enums.package";

   private static final String c_idParam_log4j_enabled = "log4j.enabled";

   private static final String c_idParam_ejbservice_enabled = "ejbservice.enabled";
   
   private static final String c_idParam_http_embedded_server = "http.embedded.server";
   
   private static final String c_idParam_h2_embedded_database = "h2.embedded.enabled";

   private static final String c_idParam_report_project = "report.project";

   private static final String c_idParam_server_project = "server.project";

   private static final String c_idParam_server_source_dir = "server.source.dir";

   private static final String c_idParam_server_resources_dir = "server.resources.dir";

   private static final String c_idParam_server_test_dir = "server.test.dir";

   private static final String c_idParam_server_sql_dir = "server.sql.dir";

   private static final String c_idParam_server_xmi_dir = "server.xmi.dir";

   private static final String c_idParam_server_cxf_pkg = "server.cfx.pkg";

   private static final String c_idParam_commons_project = "commons.project";

   private static final String c_idParam_security_enabled = "spi4j.security.enabled";

   private static final String c_idParam_integration_project = "client.integration.project";

   private static final String c_idParam_integration_source_dir = "client.integration.source.dir";

   private static final String c_idParam_integration_launcher_pkg = "client.integration.launcher.pkg";

   private static final String c_idParam_commons_source_dir = "commons.source.dir";

   private static final String c_idParam_commons_test_dir = "commons.test.dir";

   private static final String c_idParam_commons_conf_dir = "commons.conf.dir";

   private static final String c_idParam_commons_test_resources_dir = "commons.test.resources.dir";

   private static final String c_idParam_tests_crud_enabled = "tests.crud.enabled";

   private static final String c_idParam_requirement_category_base_level = "requirement.category.base.level";

   private static final String c_idParam_client_gwt_project = "client.gwt.project";

   private static final String c_idParam_client_swing_project = "client.swing.project";
   
   private static final String c_idParam_client_jsp_project = "client.jsp.project";

   private static final String c_idParam_client_jsf_project = "client.jsf.project";
   
   private static final String c_idParam_client_jsp_source_dir = "client.jsp.source.dir";

   private static final String c_idParam_client_gwt_source_dir = "client.gwt.source.dir";

   private static final String c_idParam_client_swing_source_dir = "client.swing.source.dir";
   
   private static final String c_idParam_client_jsf_test_dir = "client.jsf.test.dir";
   
   private static final String c_idParam_client_jsp_test_dir = "client.jsp.test.dir";

   private static final String c_idParam_client_swing_test_dir = "client.swing.test.dir";
   
   private static final String c_idParam_client_jsp_resources_dir = "client.jsp.resources.dir";

   private static final String c_idParam_client_swing_resources_dir = "client.swing.resources.dir";

   private static final String c_idParam_client_jsf_source_dir = "client.jsf.source.dir";

   private static final String c_idParam_client_jsf_context_dir = "client.jsf.context.dir";

   private static final String c_idParam_client_gwt_resources_dir = "client.gwt.resources.dir";

   private static final String c_idParam_client_gwt_test_dir = "client.gwt.test.dir";

   private static final String c_idParam_client_jsf_resources_dir = "client.jsf.resources.dir";

   private static final String c_idParam_client_gwt_webapp_dir = "client.gwt.webapp.dir";

   private static final String c_idParam_client_gwt_webinf_dir = "client.gwt.webinf.dir";

   private static final String c_idParam_client_jsf_webapp_dir = "client.jsf.webapp.dir";

   private static final String c_idParam_client_jsf_webinf_dir = "client.jsf.webinf.dir";
   
   private static final String c_idParam_client_jsp_webapp_dir = "client.jsp.webapp.dir";

   private static final String c_idParam_client_jsp_webinf_dir = "client.jsp.webinf.dir";
   
   private static final String c_idParam_client_jsp_topbottom_force = "client.jsp.topbottom.force";

   private static final String c_idParam_webapp_project = "webapp.project";

   private static final String c_idParam_webapp_webinf_dir = "webapp.webinf.dir";
   
   private static final String c_idParam_webapp_webapp_dir = "webapp.webapp.dir";
   
   private static final String c_idParam_webapp_source_dir = "webapp.source.dir";
   
   private static final String c_idParam_webapp_resource_dir = "webapp.resource.dir";

   private static final String c_idParam_model_project = "model.project";

   private static final String c_idParam_delivery_project = "delivery.project";

   private static final String c_idParam_webstart_project = "client.jwebstart.project";

   private static final String c_idParam_webstart_source_dir = "client.jwebstart.source.dir";

   private static final String c_idParam_webstart_resources_dir = "client.jwebstart.resources.dir";

   private static final String c_idParam_webstart_webinf_dir = "client.jwebstart.webinf.dir";

   private static final String c_idParam_webstart_key_dir = "client.jwebstart.key.dir";

   private static final String c_idParam_validation_config_file = "validation.config.file";

   private static final String c_idParam_validation_init_on_error = "validation.init.on.error";

   private static final String c_idParam_deprecated_soa_usercode_params = "deprecated.soa.usercode.params";

   private static final String c_idParam_new_line = "new.line";

   private static final String c_idParam_matching_layer_enabled = "matching.layer.enabled";

   private static final String c_idParam_appli_crud_enabled = "application.crud.enabled";

   private static final String c_idParam_tests_bdd_enabled = "tests.bdd.enabled";

   private static final String c_idParam_is_library = "project.isLibrary";

   private static final String c_idParam_is_library_rs = "project.isLibraryRs";
   
   private static final String c_idParam_rootfiles_generate_enabled = "project.rootfiles.generate.enabled";

   private static final String c_idParam_ws_enabled = "ws.layer.enabled";
   
   private static final String c_idParam_wms_enabled = "wms.layer.enabled";
   
   private static final String c_idParam_ws_health_enabled = "ws.health.enabled";
   
   private static final String c_idParam_ws_security_scheme_id = "ws.security.scheme.spi4id";

   private static final String c_idParam_ws_hk2_enabled = "ws.layer.hk2.enabled";

   private static final String c_idParam_server_sql_table_add_fields = "server.sql.table.additional_fields";
   
   private static final String c_idParam_paging_mode = "paging.mode"; 
            
   private static final String c_idParam_paging_total_count = "paging.total.count.key"; 
          
   private static final String c_idParam_paging_current_page_idx = "paging.current.page.idx.key";
   
   private static final String c_idParam_paging_page_count = "paging.page.count.key"; 
   
   private static final String c_idParam_paging_current_page_size  = "paging.current.page.size.key";
  
   
   // LEGACY A MODIFIER

   public static final String c_idParam_test_requirement_versionning_initial = "test.requirement.versionning.initial";

   public static final String c_valParam_test_requirement_versionning_initial_none = "none";

   public static final String c_valParam_test_requirement_versionning_initial_current = "current";

   @Override
   protected String get_propertiesFileName ()
   {
      return "project.properties";
   }

   @Override
   protected boolean is_defaultFileForAdditionalproperties ()
   {
      return Boolean.TRUE;
   }

   @Override
   protected PacmanProperty[] initPacmanProperties ()
   {
      return new PacmanProperty[]
      {
               PacmanProperty.newRequired(c_idParam_appli, "", 
                        "Le nom de l'application (sert de prefixe pour l'ensemble des projets)", 
                        new NameProjectStrategy()),
               
               PacmanProperty.newRequired(c_idParam_java_version, "8", 
                        "La version de Java pour la compilation des projets"),           
            
               PacmanProperty.newRequired(c_idParam_author, System.getProperty("user.name", "MINARM"),
                        "L'auteur par defaut des fichiers generes"),

               PacmanProperty.newRequired(c_idParam_package, "com.mycompany.myproject",
                        "Le package racine des sources du projet"),

               PacmanProperty.newRequired(c_idParam_version, "", 
                        "La version de l'application"),

               PacmanProperty.newRequired(c_idParam_spi4j_version, "",
                        "La version de SPI4J utilisee par les generateurs"),

               PacmanProperty.newRequired(c_idParam_database_type, "H2",
                        "Type(s) de base(s) de donnees (Oracle / Oracle_32 / H2 / MySQL / MariaDB / PostgreSQL"),

               PacmanProperty.newRequired(c_idParam_client_type, "",
                        "Type de client (SWING / GWT / JSF / JSP)",
                        new ClientProjectStrategy()),

               PacmanProperty.newRequired(c_idParam_typeNaming, "SUN", 
                        "Norme de nommage pour Java (SUN / SAFRAN / CUSTOM)", 
                        new NormeProjectStrategy()),

               PacmanProperty.newRequired(c_idParam_new_line, "\r\n", 
                        "Delimiteur de ligne (WINDOWS / LINUX)"),
               
               PacmanProperty.newRequired(c_idParam_requirement_prefix, "REQ_", 
                        "Le prefixe pour les annotations requirement"),

               PacmanProperty.newRequired(c_idParam_server_sql_table_prefix, "",
                        "Prefixe pour les tables de l'application ( format : xxx_ )"),
               
               PacmanProperty.newRequired(c_idParam_server_sql_table_schema, "",
                        "Nom du schema pour les tables de l'application ( format : xxx. )"),               
               
               PacmanProperty.newRequired(c_idParam_server_sql_table_add_fields, "",
                        "Champs additionnels pour les tables de l'application"),

               PacmanProperty.newRequired(c_idParam_server_sql_oracle_index_tablespace, "",
                        "Tablespace specifique (si base Oracle)"),

               PacmanProperty.newRequired(c_idParam_is_debug, "false",
                        "Flag indiquant si le mode debug pour PacMan est actif (plus d'informations en cas d'erreur de generation)"),

               PacmanProperty.newRequired(c_idParam_is_formatImports, "true",
                        "Flag indiquant si le formattage auto des imports est actif (CTRL + SHIFT + O)"),
               
               PacmanProperty.newRequired(c_idParam_is_clearUserCode, "false",
                        "Flag indiquant si les identifiants de balise user code sont en clair (non actif par defaut)"),               
               
               PacmanProperty.newRequired(c_idParam_is_clearUserCodeHash, "false", 
                       "Flag indiquant la generation de commentaire pour la balise user code (non actif par defaut et lie avec l'utilisation du hash)"),

               PacmanProperty.newRequired(c_idParam_is_formatClasses, "true",
                       "Flag indiquant si le formattage auto des classes java est actif (CTRL + SHIFT + F)"),
               
               PacmanProperty.newRequired(c_idParam_is_display_report, "true",
                       "Flag indiquant si la generation doit afficher un rapport"),

               PacmanProperty.newRequired(c_idParam_isLazyLoading, "false",
                        "Flag indiquant si la generation de la couche de persistance (= Entity) se fait avec les methodes de LazyLoading"),

               PacmanProperty.newRequired(c_idParam_fetchingstrategy_enabled, "false",
                        "Flag indiquant si la fetching strategy doit etre generee dans l'application (non generee par defaut)"),
               
               PacmanProperty.newRequired(c_idParam_use_batch, "false",
            		    "Flag indiquant si des services de traitement automatique doivent etre generes (non genere par defaut)"),

               PacmanProperty.newRequired(c_idParam_servicerequirements_enabled, "true",
                        "Flag indiquant si les service requirements doivent etre generes dans l'application (generes par defaut)"),

               PacmanProperty.newRequired(c_idParam_log4j_enabled, "true",
                        "Flag indiquant si le log4j2.xml doit etre genere dans l'application (genere par defaut)"),

               PacmanProperty.newRequired(c_idParam_ejbservice_enabled, "false",
                        "Flag indiquant si les annotations ejb doivent etre generees dans l'application (non generees par defaut)"),

               PacmanProperty.newRequired(c_idParam_matching_layer_enabled, "true",
                        "Flag pour la generation de la couche Matching et les objets DTO (generee par defaut)"),

               PacmanProperty.newRequired(c_idParam_security_enabled, "false",
                        "Flag pour la generation de la couche de securite (serveur / client) (non generee par defaut)"),

               PacmanProperty.newRequired(c_idParam_appli_crud_enabled, "false",
                        "Flag indiquant si une application des gestion des entites (crud) doit etre generee (non generee par defaut)"),

               PacmanProperty.newRequired(c_idParam_tests_bdd_enabled, "false",
                        "Flag indiquant si les tests de comportement (JBehave) doivent etre generes (non generes par defaut)"),

               PacmanProperty.newRequired(c_idParam_is_library, "false",
                        "Flag indiquant si le projet va servir comme librairie"),

               PacmanProperty.newRequired(c_idParam_is_library_rs, "false",
                        "Flag indiquant si le projet va servir comme librairie avec un import swagger", 
                        new WSLibraryStrategy()),
               
               PacmanProperty.newRequired(c_idParam_rootfiles_generate_enabled, "false",
                        "Flag indiquant si on permet la regeneration des fichiers pom.xml, web.xml et log4j2.xml (non par defaut)"
                        + "\n# ATTENTION : IL S'AGIT D'UNE RESTAURATION D'USINE ! PERTE DES VERSIONS ET DES AJOUTS" ),

               PacmanProperty.newRequired(c_idParam_ws_enabled, "false",
                        "Flag indiquant si on veut generer des services web (non genere par defaut)",
                        new WSProjectStrategy()),
               
               PacmanProperty.newRequired(c_idParam_wms_enabled, "false",
                        "Flag indiquant si on veut generer des micro services web (non genere par defaut)"),
               
               PacmanProperty.newRequired(c_idParam_ws_health_enabled, "false",
                       "Flag indiquant si on veut generer une api d'observabilite pour l'application (non genere par defaut)"),

               PacmanProperty.newRequired(c_idParam_ws_hk2_enabled, "true",
                        "Flag indiquant si on veut utiliser l'injection pour les services web REST (oui par defaut)"),

               PacmanProperty.newRequired(c_idParam_tests_crud_enabled, "true",
                        "Flag indiquant si les tests unitaires sur le crud doivent etre generes (generes par defaut)"),

               PacmanProperty.newRequired(c_idParam_requirement_category_base_level, "0",
                        "Niveau de base pour le decoupage des exigences dans le code genere (0 : aucun decoupage)"),

               PacmanProperty.newRequired(c_idParam_test_requirement_versionning_initial,
                        c_valParam_test_requirement_versionning_initial_none,
                        "Version initiale (cf. \"set_versionImplem()\") mise lors de la premiere generation pour " 
                        		 + "les tests de versionning d'exigence \n# (\""
                                 + c_valParam_test_requirement_versionning_initial_none
                                 + "\" : exigence non implementee ou \""
                                 + c_valParam_test_requirement_versionning_initial_current
                                 + "\" pour la version du modele)"),

               PacmanProperty.newRequired(c_idParam_validation_config_file, "validation.xml",
                        "Fichier de configuration des regles de validation"),

               PacmanProperty.newRequired(c_idParam_validation_init_on_error, "true",
                        "Initialisation du fichier de configuration en cas d'erreur de celui-ci"),
               
               PacmanProperty.newRequired(c_idParam_use_spi4j_config_frwk, "false", 
                        "Utilisation du framework spi4j pour la gestion de fichiers de configuration"),

               PacmanProperty.newRequired(c_idParam_deprecated_soa_usercode_params, "true",
                        "Utilisation des parametres pour le user code des methodes soa"),
               
               PacmanProperty.newConditional(c_idParam_ws_security_scheme_id, "", 
                        "Schema de securite a generer si librairie REST et plusieurs schemas (vide par defaut)"),

               PacmanProperty.newRequired(c_idParam_server_project, "{$idAppli}-server",
                        "Projet Eclipse pour la partie serveur de l'application"),

               PacmanProperty.newRequired(c_idParam_server_source_dir, "src/main/java",
                        "Dossier de generation des sources serveur"),

               PacmanProperty.newRequired(c_idParam_server_resources_dir, "src/main/resources",
                        "Dossier de generation des sources serveur"),

               PacmanProperty.newRequired(c_idParam_server_test_dir, "src/test/java",
                        "Dossier de generation des sources test serveur"),

               PacmanProperty.newRequired(c_idParam_server_sql_dir, "src/main/sql",
                        "Dossier de generation des sources sql serveur"),

               PacmanProperty.newRequired(c_idParam_server_xmi_dir, "src/main/xmi",
                        "Dossier de generation des sources xmi serveur"),

               PacmanProperty.newRequired(c_idParam_server_cxf_pkg, "ws.servlet",
                        "Package pour la generation des servlets CXF (SOAP)"),
               
               PacmanProperty.newRequired(c_idParam_enums_package, "", 
            		   "Package specifique pour l'ensemble des enumerations modelisees (vide par defaut)"
            		   + "\n# Si vide les enumerations sont generees au niveau de leur emplacement de modelisation"),                

               PacmanProperty.newRequired(c_idParam_commons_project, "{$idAppli}-commons",
                        "Projet Eclipse de la partie commune entre la partie cliente et la partie serveur"),

               PacmanProperty.newRequired(c_idParam_commons_source_dir, "src/main/java",
                        "Dossier de generation des sources common"),

               PacmanProperty.newRequired(c_idParam_commons_conf_dir, "conf",
                        "Dossier du repertoire de configuration common"),

               PacmanProperty.newRequired(c_idParam_commons_test_dir, "src/test/java",
                        "Dossier de generation des sources test common"),

               PacmanProperty.newRequired(c_idParam_commons_test_resources_dir, "src/test/resources",
                        "Dossier de generation des resources de test de commons"),

               PacmanProperty.newRequired(c_idParam_model_project, "{$idAppli}-model",
                        "Projet Eclipse de la partie modele de l'application"),

               PacmanProperty.newRequired(c_idParam_delivery_project, "{$idAppli}-livraison-hebergement",
                        "Projet Eclipse de la partie livraison de l'application"),
               
               PacmanProperty.newConditional(c_idParam_http_embedded_server, "", 
                        "Mise en place d'un serveur http embarque (jetty, tomcat)"),

               PacmanProperty.newConditional(c_idParam_h2_embedded_database, "", 
                        "Mise en place d'une base h2 embarque (non actif par defaut)"),
               
               PacmanProperty.newConditional(c_idParam_report_project, "{$idAppli}-report",
                        "Projet Eclipse pour le reporting"),               
              
               PacmanProperty.newConditional(c_idParam_webapp_project, "{$idAppli}-webapp",
                        "Projet Eclipse de la partie Webapp pour les services Web "),

               PacmanProperty.newConditional(c_idParam_webapp_webinf_dir, "src/main/webapp/WEB-INF",
                        "Dossier de generation pour les fichiers de configuration du projet"),
               
               PacmanProperty.newConditional(c_idParam_webapp_webapp_dir, "src/main/webapp",
                        "Dossier de generation des sources html our la webapp"),
               
               PacmanProperty.newConditional(c_idParam_webapp_source_dir, "src/main/java",
                        "Dossier de generation des sources webapp"),     
               
               PacmanProperty.newConditional(c_idParam_webapp_resource_dir, "src/main/resources",
                        "Dossier de generation des resources webapp"),                    
   
               PacmanProperty.newConditional(c_idParam_integration_project, "{$idAppli}-integration",
                        "Projet Eclipse de la partie Integration pour un client swing"),

               PacmanProperty.newConditional(c_idParam_integration_source_dir, "src/main/java",
                        "Dossier de generation des sources integration"),

               PacmanProperty.newConditional(c_idParam_integration_launcher_pkg, "ui.main",
                        "Package pour le lanceur du client"),

               PacmanProperty.newConditional(c_idParam_webstart_project, "{$idAppli}-javawebstart",
                        "Projet Eclipse de la partie modele de l'application"),

               PacmanProperty.newConditional(c_idParam_webstart_source_dir, "src/main/java",
                        "Dossier de generation des sources javawebstart"),

               PacmanProperty.newConditional(c_idParam_webstart_webinf_dir, "src/main/webapp/WEB-INF",
                        "Dossier de generation pour les fichiers de configuration du javawebstart"),

               PacmanProperty.newConditional(c_idParam_webstart_resources_dir, "src/main/resources",
                        "Dossier de generation des resources de test de javawebstart"),

               PacmanProperty.newConditional(c_idParam_webstart_key_dir, "key",
                        "Dossier de generation pour les cles javawebstart"),

               PacmanProperty.newConditional(c_idParam_client_gwt_project, "{$idAppli}-client-gwt",
                        "Projet Eclipse du client GWT"),

               PacmanProperty.newConditional(c_idParam_client_gwt_source_dir, "src/main/java",
                        "Dossier de generation des sources du client GWT"),
               
               PacmanProperty.newConditional(c_idParam_client_jsp_source_dir, "src/main/java",
                        "Dossier de generation des sources du client JSP"),

               PacmanProperty.newConditional(c_idParam_client_gwt_resources_dir, "src/main/resources",
                        "Dossier de generation des ressources du client GWT"),

               PacmanProperty.newConditional(c_idParam_client_gwt_webapp_dir, "src/main/webapp",
                        "Dossier de generation de la webapp du client GWT"),

               PacmanProperty.newConditional(c_idParam_client_gwt_webinf_dir, "src/main/webapp/WEB-INF",
                        "Dossier de generation des fichiers de configuration du client GWT"),

               PacmanProperty.newConditional(c_idParam_client_gwt_test_dir, "src/test/java",
                        "Dossier de generation pour les tests du client GWT"),

               PacmanProperty.newConditional(c_idParam_client_swing_project, "{$idAppli}-client-swing",
                        "Projet Eclipse du client SWING"),

               PacmanProperty.newConditional(c_idParam_client_swing_source_dir, "src/main/java",
                        "Dossier de generation des sources du client Swing"),

               PacmanProperty.newConditional(c_idParam_client_swing_test_dir, "src/test/java",
                        "Dossier de generation pour les tests du client Swing"),

               PacmanProperty.newConditional(c_idParam_client_swing_resources_dir, "src/main/resources",
                        "Dossier de generation des ressources du client Swing"),

               PacmanProperty.newConditional(c_idParam_client_jsf_webinf_dir, "src/main/webapp/WEB-INF",
                        "Dossier de generation des fichiers de configuration pour le client JSF"),

               PacmanProperty.newConditional(c_idParam_client_jsf_test_dir, "src/test/java",
                        "Dossier de generation pour les tests du client JSF"),

               PacmanProperty.newConditional(c_idParam_client_jsf_context_dir, "src/main/context",
                        "Dossier pour la definition du contexte du client JSF"),

               PacmanProperty.newConditional(c_idParam_client_jsf_project, "{$idAppli}-client-jsf",
                        "Projet Eclipse du client JSF"),

               PacmanProperty.newConditional(c_idParam_client_jsf_source_dir, "src/main/java",
                        "Dossier de generation des sources du client JSF"),

               PacmanProperty.newConditional(c_idParam_client_jsf_resources_dir, "src/main/resources",
                        "Dossier de generation des ressources du client JSF"),

               PacmanProperty.newConditional(c_idParam_client_jsf_webapp_dir, "src/main/webapp",
                        "Dossier de generation de la webapp du client JSF"),
               
               PacmanProperty.newConditional(c_idParam_client_jsp_project, "{$idAppli}-client-jsp",
                        "Projet Eclipse du client JSP"),
               
               PacmanProperty.newConditional(c_idParam_client_jsp_test_dir, "src/test/java",
                        "Dossier de generation pour les tests du client JSP"),

               PacmanProperty.newConditional(c_idParam_client_jsp_source_dir, "src/main/java",
                        "Dossier de generation des sources du client JSP"),

               PacmanProperty.newConditional(c_idParam_client_jsp_resources_dir, "src/main/resources",
                        "Dossier de generation des ressources du client JSP"),

               PacmanProperty.newConditional(c_idParam_client_jsp_webapp_dir, "src/main/webapp",
                        "Dossier de generation de la webapp du client JSP"), 
               
               PacmanProperty.newConditional(c_idParam_client_jsp_webinf_dir, "src/main/webapp/WEB-INF",
                        "Dossier de generation des fichiers de configuration pour le client JSP"),
               
               PacmanProperty.newConditional(c_idParam_client_jsp_topbottom_force, "false", 
                        "Si application securisee, force l'affichage header-footer sur la premiere page"),
               
               PacmanProperty.newRequired(c_idParam_server_libraries, "", 
                        "Champs additionnels pour les librairies supplementaires de l'application"),     

               PacmanProperty.newRequired(c_idParam_server_sql_table_add_fields, "",
                        "Champs additionnels pour les tables SQL de l'application", 
                        new AdditionalFieldsStrategy()), 
               
               PacmanProperty.newConditional(c_idParam_server_sql_table_xdmaj_name, "XDMAJ",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xdmaj_type, "Date",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xdmaj_size, "",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xdmaj_notnull, "true",
                        "Champ additionnel pour les tables SQL"),
               
               PacmanProperty.newConditional(c_idParam_server_sql_table_xdmaj_default, "current_date",
                        "Champ additionnel pour les tables SQL"),
               
               PacmanProperty.newConditional(c_idParam_server_sql_table_xdmaj_comment, "Date de mise a jour de la ligne.",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xtopsup_name, "XTOPSUP",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xtopsup_type, "XtopSup",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xtopsup_default, "0",
                        "Champ additionnel pour les tables SQL"),

               PacmanProperty.newConditional(c_idParam_server_sql_table_xtopsup_notnull, "true",
                        "Champ additionnel pour les tables SQL"), 
                        
               PacmanProperty.newConditional(c_idParam_server_sql_table_xtopsup_comment, "Indicateur de suppression logique.",
                        "Champ additionnel pour les tables SQL"),
                                 
               PacmanProperty.newConditional(c_idParam_server_sql_table_xtopsup_size, "1",
                         "Champ additionnel pour les tables SQL"), 
               
               PacmanProperty.newRequired(c_idParam_server_sql_idsuffix_enabled, "true", 
            		   	"Ajoute automatiquement le suffixe '_ID' pour le nom physique des references (actif par defaut)"),
               
               PacmanProperty.newRequired(c_idParam_paging_mode, "",
                        "Mode de fonctionnement pour la pagination (auto, user, vide par defaut)", 
                        new PagingModeStrategy()), 
      
               PacmanProperty.newConditional(c_idParam_paging_total_count, "Resource-Count",
                        "Propriete dans l'en-tete pour le stockage du nombre total d'occurences"), 
               
               PacmanProperty.newConditional(c_idParam_paging_current_page_idx, "Current-Page",
                        "Propriete dans l'en-tete pour le stockage de l'index de page courante"), 
               
               PacmanProperty.newConditional(c_idParam_paging_page_count, "Page-Count",
                        "Propriete dans l'en-tete pour le stockage du nombre de pages"),
               
               PacmanProperty.newConditional(c_idParam_paging_current_page_size, "Current-Page-Size",
                        "Propriete dans l'en-tete pour le stockage du nombre d'occurences pour la page courante")};
   }

   /**
    * Definition des strategies pour les proprietes.
    */

   /**
    * Strategie pour le nommage des sous projets.
    */
   private class NameProjectStrategy extends PacmanPropertyStrategy_Abs
   {
      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ONSTART;
      }

      @Override
      protected void updateProperty(PacmanProperty p_pacmanProperty) {
         if(p_pacmanProperty.get_value().indexOf("{$") != -1) { 
            String p_newValue = p_pacmanProperty.get_value()
                     .replace("{$" + get_refId() + "}", get_refValue());
            p_pacmanProperty.update_value(p_newValue);
         }
      }

      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         updateProperty(p_pacmanProperties.get(c_idParam_model_project));
         updateProperty(p_pacmanProperties.get(c_idParam_commons_project));
         updateProperty(p_pacmanProperties.get(c_idParam_server_project));
         updateProperty(p_pacmanProperties.get(c_idParam_delivery_project));
         updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_project));
         updateProperty(p_pacmanProperties.get(c_idParam_client_swing_project));
         updateProperty(p_pacmanProperties.get(c_idParam_integration_project));
         updateProperty(p_pacmanProperties.get(c_idParam_webstart_project));
         updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_project));
         updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_project));
         updateProperty(p_pacmanProperties.get(c_idParam_webapp_project));
         updateProperty(p_pacmanProperties.get(c_idParam_report_project));
      }
   }

   /**
    * Strategie pour le type de client.
    */
   private class ClientProjectStrategy extends PacmanPropertyStrategy_Abs
   {
      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ALWAYS;
      }

      @Override
      protected void updateProperty (PacmanProperty p_pacmanProperty)
      {
         p_pacmanProperty.set_status(PacmanPropertyStatus_Enum.REQUIRED);
      }

      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         if("swing".equalsIgnoreCase(get_refValue())) {
            updateProperty(p_pacmanProperties.get(c_idParam_client_swing_project)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_swing_resources_dir)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_swing_source_dir)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_swing_test_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webstart_key_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webstart_project));
            updateProperty(p_pacmanProperties.get(c_idParam_webstart_resources_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webstart_source_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webstart_webinf_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_project));
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_webinf_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_source_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_integration_project));
            updateProperty(p_pacmanProperties.get(c_idParam_integration_source_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_integration_launcher_pkg));
         }
         
         if("gwt".equalsIgnoreCase(get_refValue())) {
            updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_project)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_resources_dir)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_source_dir)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_test_dir));   
            updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_webapp_dir)); 
            updateProperty(p_pacmanProperties.get(c_idParam_client_gwt_webinf_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_http_embedded_server));
            updateProperty(p_pacmanProperties.get(c_idParam_h2_embedded_database));
         }
         
         if("jsf".equalsIgnoreCase(get_refValue())) {
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_context_dir));           
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_project));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_resources_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_source_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_test_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_webapp_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsf_webinf_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_http_embedded_server));
            updateProperty(p_pacmanProperties.get(c_idParam_h2_embedded_database));
         }
         
         if("jsp".equalsIgnoreCase(get_refValue())) {
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_project));   
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_source_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_resources_dir));   
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_test_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_webapp_dir));     
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_webinf_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_client_jsp_topbottom_force));
            updateProperty(p_pacmanProperties.get(c_idParam_http_embedded_server));
            updateProperty(p_pacmanProperties.get(c_idParam_h2_embedded_database));
         }
      }
   }
   
   /**
    * Strategie pour la librairie.
    */
   private class WSLibraryStrategy extends PacmanPropertyStrategy_Abs
   {
      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ALWAYS;
      }

      @Override
      protected void updateProperty (PacmanProperty p_pacmanProperty)
      {
         p_pacmanProperty.set_status(PacmanPropertyStatus_Enum.REQUIRED);
      }
      
      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         if(Boolean.valueOf(get_refValue())) 
            updateProperty(p_pacmanProperties.get(c_idParam_ws_security_scheme_id));
      }
   }

   /**
    * Strategie pour la webapp.
    */
   private class WSProjectStrategy extends PacmanPropertyStrategy_Abs
   {
      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ALWAYS;
      }

      @Override
      protected void updateProperty(PacmanProperty p_pacmanProperty) 
      {
         p_pacmanProperty.set_status(PacmanPropertyStatus_Enum.REQUIRED);
      }

      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         if(Boolean.parseBoolean(get_refValue())) {
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_project));
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_webinf_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_webapp_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_webapp_source_dir));
            updateProperty(p_pacmanProperties.get(c_idParam_http_embedded_server));
            updateProperty(p_pacmanProperties.get(c_idParam_h2_embedded_database));
         }
      }
   }
   
   /**
    * Strategie pour la norme de nommage des methodes , classes, proprietes, etc...
    */
   private class NormeProjectStrategy extends PacmanPropertyStrategy_Abs {

      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ONCREATE_CHANGE;
      }

      @Override
      protected void updateProperty (PacmanProperty p_pacmanProperty)
      {
         if("sun".equalsIgnoreCase(get_refValue()))
            p_pacmanProperty.set_valueFromIndexedDefaultValue(0);
         
         if("safran".equalsIgnoreCase(get_refValue()))
            p_pacmanProperty.set_valueFromIndexedDefaultValue(1);
      }

      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         for (Entry<String, PacmanProperty> v_entry : p_pacmanProperties.entrySet())
         {
            if(v_entry.getValue().getPropertyFileName().equals("nommage.properties"))
               updateProperty( v_entry.getValue());
         }  
      }
   }
   
   /**
    * Strategie pour la pagination.
    */
   private class PagingModeStrategy extends PacmanPropertyStrategy_Abs {

      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ALWAYS;
      }
      
      @Override
      protected void updateProperty (PacmanProperty p_pacmanProperty)
      {
         p_pacmanProperty.set_status(PacmanPropertyStatus_Enum.REQUIRED);
      }
      
      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         if(!get_refValue().isEmpty()) {
            updateProperty(p_pacmanProperties.get(c_idParam_paging_current_page_idx));
            updateProperty(p_pacmanProperties.get(c_idParam_paging_current_page_size));
            updateProperty(p_pacmanProperties.get(c_idParam_paging_page_count));
            updateProperty(p_pacmanProperties.get(c_idParam_paging_total_count));
         }
      }
   }

   /**
    * Fonctionnement particulier pour les proprietes additionnelles. On se contente de lancer une exception si non trouvee.
    * 
    * @author MINARM.
    */
   private class AdditionalFieldsStrategy extends PacmanPropertyStrategy_Abs
   {

      @Override
      protected PacmanPropertyTrigger_Enum get_strategyTrigger ()
      {
         return PacmanPropertyTrigger_Enum.ALWAYS;
      }

      @Override
      protected void updateProperty (PacmanProperty p_pacmanProperty)
      {
         p_pacmanProperty.set_status(PacmanPropertyStatus_Enum.REQUIRED);
      }

      @Override
      protected void doStrategy (Map<String, PacmanProperty> p_pacmanProperties)
      {
         if (null != get_refValue() && !get_refValue().isEmpty())
         {
            for (String v_key : get_refValue().split(","))
            {
               if(c_idParam_server_sql_table_xdmaj.equals(v_key)) {
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xdmaj_name));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xdmaj_comment));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xdmaj_notnull));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xdmaj_size));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xdmaj_type));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xdmaj_default));
               }
               
               if(c_idParam_server_sql_table_xtopsup.equals(v_key)) {
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xtopsup_name));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xtopsup_comment));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xtopsup_notnull));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xtopsup_size));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xtopsup_type));
                  updateProperty(p_pacmanProperties.get(c_idParam_server_sql_table_xtopsup_default));
               }
            }
         }
      }
   }
   

   /**
    * Recuperation des proprietes par le PacmanPropertiesManager.
    */

   public static String getApplicationName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_appli);
   }

   public static String getApplicationNameJavaService(Object object){return getApplicationName();}

   public static String getReportProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_report_project);
   }

   public static String getReportProjectNameJavaService(Object object){return getReportProjectName();}

   public static String getModelProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_model_project);
   }

   public static String getModelProjectNameJavaService(Object object){return getModelProjectName();}

   public static String getClientJavaWebStartProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webstart_project);
   }

   public static String getClientJavaWebStartProjectNameJavaService(Object object){return getClientJavaWebStartProjectName();}

   public static String getClientSwingProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_swing_project);
   }

   public static String getClientSwingProjectNameJavaService(Object object){return getClientSwingProjectName();}

   public static String getClientIntegrationProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_integration_project);
   }

   public static String getClientIntegrationProjectNameJavaService(Object object){return getClientIntegrationProjectName();}

   public static String getClientJsfProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_project);
   }

   public static String getClientJsfProjectNameJavaService(Object object){return getClientJsfProjectName();}

   public static String getClientGwtProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_gwt_project);
   }

   public static String getClientGwtProjectNameJavaService(Object object){return getClientGwtProjectName();}

   public static String getCommonProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_commons_project);
   }

   public static String getCommonProjectNameJavaService(Object object){return getCommonProjectName();}

   public static String getDeliveryProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_delivery_project);
   }

   public static String getDeliveryProjectNameJavaService(Object object){return getDeliveryProjectName();}

   public static String getServerProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_project);
   }

   public static String getServerProjectNameJavaService(Object object){return getServerProjectName();}

   public static String getWebappProjectName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webapp_project);
   }

   public static String getWebappProjectNameJavaService(Object object){return getWebappProjectName();}

   public static String getApplicationPackage ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_package);
   }

   public static String getApplicationPackageJavaService(Object object){return getApplicationPackage();}

   public static String getClientJsfSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_source_dir);
   }

   public static String getClientJsfSourceDirJavaService(Object object){return getClientJsfSourceDir();}

   public static String getClientJsfResourcesDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_resources_dir);
   }

   public static String getClientJsfResourcesDirJavaService(Object object){return getClientJsfResourcesDir();}

   public static String getClientJsfWebappDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_webapp_dir);
   }

   public static String getClientJsfWebappDirJavaService(Object object){return getClientJsfWebappDir();}

   public static String getClientJsfWebDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_webinf_dir);
   }

   public static String getClientJsfWebDirJavaService(Object object){return getClientJsfWebDir();}

   public static String getClientJsfTestDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_test_dir);
   }

   public static String getClientJsfTestDirJavaService(Object object){return getClientJsfTestDir();}

   public static String getClientJsfContextDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsf_context_dir);
   }

   public static String getClientJsfContextDirJavaService(Object object){return getClientJsfContextDir();}

   public static String getClientGwtSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_gwt_source_dir);
   }

   public static String getClientGwtSourceDirJavaService(Object object){return getClientGwtSourceDir();}

   public static String getClientGwtResourcesDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_gwt_resources_dir);
   }

   public static String getClientGwtResourcesDirJavaService(Object object){return getClientGwtResourcesDir();}

   public static String getClientGwtWebappDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_gwt_webapp_dir);
   }

   public static String getClientGwtWebappDirJavaService(Object object){return getClientGwtWebappDir();}

   public static String getClientGwtWebDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_gwt_webinf_dir);
   }

   public static String getClientGwtWebDirJavaService(Object object){return getClientGwtWebDir();}

   public static String getClientGwtTestDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_gwt_test_dir);
   }

   public static String getClientGwtTestDirJavaService(Object object){return getClientGwtTestDir();}

   public static String getClientIntegrationSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_integration_source_dir);
   }

   public static String getClientIntegrationSourceDirJavaService(Object object){return getClientIntegrationSourceDir();}

   public static String getClientIntegrationLauncherPackage ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_integration_launcher_pkg);
   }

   public static String getClientIntegrationLauncherPackageJavaService(Object object){return getClientIntegrationLauncherPackage();}

   public static String getClientJWebStartWebDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webstart_webinf_dir);
   }

   public static String getClientJWebStartWebDirJavaService(Object object){return getClientJWebStartWebDir();}

   public static String getClientJWebStartSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webstart_source_dir);
   }

   public static String getClientJWebStartSourceDirJavaService(Object object){return getClientJWebStartSourceDir();}

   public static String getClientJWebStartResourcesDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webstart_resources_dir);
   }

   public static String getClientJWebStartResourcesDirJavaService(Object object){return getClientJWebStartResourcesDir();}
   
   public static String getClientJspProjectName () 
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_project);
   }

   public static String getClientJspProjectNameJavaService(Object object){return getClientJspProjectName();}

   public static String getClientJspSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_source_dir);
   }

   public static String getClientJspSourceDirJavaService(Object object){return getClientJspSourceDir();}

   public static String getClientJspTestDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_test_dir);
   }

   public static String getClientJspTestDirJavaService(Object object){return getClientJspTestDir();}

   public static String getClientJspResourcesDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_resources_dir);
   }

   public static String getClientJspResourcesDirJavaService(Object object){return getClientJspResourcesDir();}

   public static String getClientJspWebappDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_webapp_dir);
   }

   public static String getClientJspWebappDirJavaService(Object object){return getClientJspWebappDir();}

   public static String getClientJspWebDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_webinf_dir);
   }

   public static String getClientJspWebDirJavaService(Object object){return getClientJspWebDir();}

   public static String getClientJWebStartKeyDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webstart_key_dir);
   }

   public static String getClientJWebStartKeyDirJavaService(Object object){return getClientJWebStartKeyDir();}

   public static String getServerSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_source_dir);
   }

   public static String getServerSourceDirJavaService(Object object){return getServerSourceDir();}

   public static String getServerResourcesDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_resources_dir);
   }

   public static String getServerResourcesDirJavaService(Object object){return getServerResourcesDir();}

   public static String getServerTestDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_test_dir);
   }

   public static String getServerTestDirJavaService(Object object){return getServerTestDir();}

   public static String getServerSQLDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_sql_dir);
   }

   public static String getServerSQLDirJavaService(Object object){return getServerSQLDir();}

   public static String getServerXMIDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_xmi_dir);
   }

   public static String getServerXMIDirJavaService(Object object){return getServerXMIDir();}

   public static String getPackageCFXServer ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_cxf_pkg);
   }

   public static String getPackageCFXServerJavaService(Object object){return getPackageCFXServer();}

   public static String getCommonsSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_commons_source_dir);
   }

   public static String getCommonsSourceDirJavaService(Object object){return getCommonsSourceDir();}

   public static String getCommonsConfDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_commons_conf_dir);
   }

   public static String getCommonsConfDirJavaService(Object object){return getCommonsConfDir();}

   public static String getCommonsTestDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_commons_test_dir);
   }

   public static String getCommonsTestDirJavaService(Object object){return getCommonsTestDir();}

   public static String getCommonsTestResourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_commons_test_resources_dir);
   }

   public static String getCommonsTestResourceDirJavaService(Object object){return getCommonsTestResourceDir();}

   public static String getWebappWebDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webapp_webinf_dir);
   }

   public static String getWebappWebDirJavaService(Object object){return getWebappWebDir();}
   
   public static String getWebappDir()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webapp_webapp_dir);
   }

   public static String getWebappDirJavaService(Object object){return getWebappDir();}
   
   public static String getWebappSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webapp_source_dir);
   }

   public static String getWebappSourceDirJavaService(Object object){return getWebappSourceDir();}

   public static String getWebappResourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_webapp_resource_dir);
   }

   public static String getWebappResourceDirJavaService(Object object){return getWebappResourceDir();}
   
   public static String getClientSwingSourceDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_swing_source_dir);
   }

   public static String getClientSwingSourceDirJavaService(Object object){return getClientSwingSourceDir();}

   public static String getClientSwingResourcesDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_swing_resources_dir);
   }

   public static String getClientSwingResourcesDirJavaService(Object object){return getClientSwingResourcesDir();}

   public static String getClientSwingTestDir ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_swing_test_dir);
   }

   public static String getClientSwingTestDirJavaService(Object object){return getClientSwingTestDir();}

   public static String getClient ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_type);
   }

   public static String getClientJavaService(Object object){return getClient();}

   public static String getNaming ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_typeNaming);
   }

   public static String getNamingJavaService(Object object){return getNaming();}

   public static String getSpi4JVersion ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_spi4j_version);
   }

   public static String getSpi4JVersionJavaService(Object object){return getSpi4JVersion();}

   public static String getDataBasesNames ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_database_type);
   }

   public static String getDataBasesNamesJavaService(Object object){return getDataBasesNames();}

   public static String getDelimiter ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_new_line);
   }

   public static String getDelimiterJavaService(Object object){return getDelimiter();}

   public static String getUseServiceRequirements ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_servicerequirements_enabled);
   }

   public static String getUseServiceRequirementsJavaService(Object object){return getUseServiceRequirements();}

   public static String getUseEjb ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_ejbservice_enabled);
   }

   public static String getUseEjbJavaService(Object object){return getUseEjb();}

   public static String getUseTestsCRUD ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_tests_crud_enabled);
   }

   public static String getUseTestsCRUDJavaService(Object object){return getUseTestsCRUD();}

   public static String getAppCRUD ()
   {
      // return PacmanPropertiesManager.get_property(c_idParam_appli);
      return null;
   }

   public static String getAppCRUDJavaService(Object object){return getAppCRUD();}

   public static String getUseMatching ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_matching_layer_enabled);
   }

   public static String getUseMatchingJavaService(Object object){return getUseMatching();}

   public static String getUseSpi4jSecurity ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_security_enabled);
   }

   public static String getUseSpi4jSecurityJavaService(Object object){return getUseSpi4jSecurity();}

   public static String getUseTestBDD ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_tests_bdd_enabled);
   }

   public static String getUseTestBDDJavaService(Object object){return getUseTestBDD();}

   public static String getIsLibrary ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_is_library);
   }

   public static String getIsLibraryJavaService(Object object){return getIsLibrary();}

   public static String getIsLibraryRs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_is_library_rs);
   }

   public static String getIsLibraryRsJavaService(Object object){return getIsLibraryRs();}
   
   public static String getHasToGenerateRootFiles ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_rootfiles_generate_enabled);
   }

   public static String getHasToGenerateRootFilesJavaService(Object object){return getHasToGenerateRootFiles();}

   public static String getUseWS ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_ws_enabled);
   }

   public static String getUseWSJavaService(Object object){return getUseWS();}
   
   public static String getUseWMS()
   {
      return PacmanPropertiesManager.get_property(c_idParam_wms_enabled);
   }

   public static String getUseWMSJavaService(Object object){return getUseWMS();}

   public static String getUseWsServiceInjection ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_ws_hk2_enabled);
   }

   public static String getUseWsServiceInjectionJavaService(Object object){return getUseWsServiceInjection();}
   
   public static String getWsSecuritySchemeId() 
   {
      return PacmanPropertiesManager.get_property(c_idParam_ws_security_scheme_id);
   }

   public static String getWsSecuritySchemeIdJavaService(Object object){return getWsSecuritySchemeId();}

   public static String getVersion ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_version);
   }

   public static String getVersionJavaService(Object object){return getVersion();}

   public static String getValidationConfigFile ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_validation_config_file);
   }

   public static String getValidationInitOnError ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_validation_init_on_error);
   }

   public static String getRequirementCategoryBaseLevel ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_requirement_category_base_level);
   }

   public static String getRequirementCategoryBaseLevelJavaService(Object object){return getRequirementCategoryBaseLevel();}

   public static String getSqlTableAdditionalFields ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_sql_table_add_fields);
   }

   public static String getSqlTableAdditionalFieldsJavaService(Object object){return getSqlTableAdditionalFields();}
   
   public static String getLibrariesAdditionalJars ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_libraries);
   }

   public static String getLibrariesAdditionalJarsJavaService(Object object){return getLibrariesAdditionalJars();}

   public static final String getIsApplicationCrud ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_appli_crud_enabled);
   }

   public static final String getIsApplicationCrudJavaService(Object object){return getIsApplicationCrud();}
   
   public static final String getUseConfigFilesSpi4jFrwk() 
   {
      return PacmanPropertiesManager.get_property(c_idParam_use_spi4j_config_frwk);
   }

   public static final String getUseConfigFilesSpi4jFrwkJavaService(Object object){return getUseConfigFilesSpi4jFrwk();}

   public static String getUseLog4J ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_log4j_enabled);
   }

   public static String getUseLog4JJavaService(Object object){return getUseLog4J();}

   public static String getAuthorName ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_author);
   }

   public static String getAuthorNameJavaService(Object object){return getAuthorName();}

   public static String getRequirementPrefix ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_requirement_prefix);
   }

   public static String getRequirementPrefixJavaService(Object object){return getRequirementPrefix();}

   public static String getSQLTablePrefix ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_sql_table_prefix);
   }

   public static String getSQLTablePrefixJavaService(Object object){return getSQLTablePrefix();}
   
   public static String getSQLTableSchema ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_sql_table_schema);
   }

   public static String getSQLTableSchemaJavaService(Object object){return getSQLTableSchema();}  
   
   public static String getSQLOracleIndexTableSpace ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_sql_oracle_index_tablespace);
   }

   public static String getSQLOracleIndexTableSpaceJavaService(Object object){return getSQLOracleIndexTableSpace();}

   public static String getUseDeprecatedSOAUserCodeParams ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_deprecated_soa_usercode_params);
   }

   public static String getUseDeprecatedSOAUserCodeParamsJavaService(Object object){return getUseDeprecatedSOAUserCodeParams();}

   public static String getUseFetchingStrategy ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_fetchingstrategy_enabled);
   }

   public static String getUseFetchingStrategyJavaService(Object object){return getUseFetchingStrategy();}

   public static String getJavaVersion ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_java_version);
   }

   public static String getJavaVersionJavaService(Object object){return getJavaVersion();}
   
   public static String getPagingMode ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_paging_mode);
   }

   public static String getPagingModeJavaService(Object object){return getPagingMode();}
   
   public static String getPagingTotalCountKey ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_paging_total_count);
   }

   public static String getPagingTotalCountKeyJavaService(Object object){return getPagingTotalCountKey();}
   
   public static String getPagingCurrentPageSizeKey ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_paging_current_page_size);
   }

   public static String getPagingCurrentPageSizeKeyJavaService(Object object){return getPagingCurrentPageSizeKey();}
   
   public static String getPagingCurrentPageIdxKey ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_paging_current_page_idx);
   }

   public static String getPagingCurrentPageIdxKeyJavaService(Object object){return getPagingCurrentPageIdxKey();}
   
   public static String getJavaPagingCount ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_paging_page_count);
   }

   public static String getJavaPagingCountJavaService(Object object){return getJavaPagingCount();}
   
   public static String getIsDebugMode () 
   {   
      return PacmanPropertiesManager.get_property(c_idParam_is_debug);
   }
   
   public static String getCommonEnumsPackage () 
   {
	   return PacmanPropertiesManager.get_property(c_idParam_enums_package);
   }

   public static String getCommonEnumsPackageJavaService(Object object){return getCommonEnumsPackage();}
   
   public static String getIsFormatImports () {
      
      return PacmanPropertiesManager.get_property(c_idParam_is_formatImports);
   }
   
   public static String getHttpEmbeddedServer() {
      
      return PacmanPropertiesManager.get_property(c_idParam_http_embedded_server);
   }

   public static String getHttpEmbeddedServerJavaService(Object object){return getHttpEmbeddedServer();}
   
   public static String getIsClearUserCode () {
      
      return PacmanPropertiesManager.get_property(c_idParam_is_clearUserCode);
   }

   public static String getIsClearUserCodeJavaService(Object object){return getIsClearUserCode();}
   
   public static String getH2EmbeddedDatabase ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_h2_embedded_database);
   }

   public static String getH2EmbeddedDatabaseJavaService(Object object){return getH2EmbeddedDatabase();}

   public static String getXtoSupName () 
   {
	   return  PacmanPropertiesManager.get_property(c_idParam_server_sql_table_xtopsup_name);
   }

   public static String getXtoSupNameJavaService(Object object){return getXtoSupName();}
   
   public static String getXdMajName () 
   {
	   return PacmanPropertiesManager.get_property(c_idParam_server_sql_table_xdmaj_name);
   }

   public static String getXdMajNameJavaService(Object object){return getXdMajName();} 
   
   public static String getClientJspForceTopBottom() 
   {
      return PacmanPropertiesManager.get_property(c_idParam_client_jsp_topbottom_force);
   }

   public static String getClientJspForceTopBottomJavaService(Object object){return getClientJspForceTopBottom();}
   
   public static String getIsDisplayGeneratorReport () 
   {
      return PacmanPropertiesManager.get_property(c_idParam_is_display_report);
   }
   
   public static String getIsFormatJavaClasses () 
   {
      return PacmanPropertiesManager.get_property(c_idParam_is_formatClasses);
   }

   public static String getUseBatch() 
   {
      return "false";
      //return PacmanPropertiesManager.get_property(c_idParam_use_batch);
   }

   public static String getUseBatchJavaService(Object object){return getUseBatch();}
   
   public static String isClearUserCodeHash() 
   {
      return PacmanPropertiesManager.get_property(c_idParam_is_clearUserCodeHash);
   }

   public static String isClearUserCodeHashJavaService(Object object){return isClearUserCodeHash();}   
   
   public static String getUseIdSqlSuffixForReferences() 
   {
      return PacmanPropertiesManager.get_property(c_idParam_server_sql_idsuffix_enabled);
   }

   public static String getUseIdSqlSuffixForReferencesJavaService(Object object){return getUseIdSqlSuffixForReferences();}
   
   public static String getUseHealthApi() 
   {
      return PacmanPropertiesManager.get_property(c_idParam_ws_health_enabled);
   }

   public static String getUseHealthApiJavaService(Object object){return getUseHealthApi();}

   public static String getXtoSupKey () 
   {
      return  c_idParam_server_sql_table_xtopsup;
   }

   public static String getXtoSupKeyJavaService(Object object){return getXtoSupKey();}
   
   public static String getXdMajKey () 
   {
      return c_idParam_server_sql_table_xdmaj;
   }

   public static String getXdMajKeyJavaService(Object object){return getXdMajKey();} 
}
