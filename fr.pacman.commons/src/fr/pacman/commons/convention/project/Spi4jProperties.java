package fr.pacman.commons.convention.project;

import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Liste de l'ensemble des proprietes pour une generation PacMan. 
 * Les proprietes presentes ici sont les proprietes generiques de l'application, 
 * celles pour les conventions de nommage sont dans le package '.rule'.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * @author MINARM
 * 
 */
public class Spi4jProperties extends PacmanPropertiesCategory_Abs
{
   private static final String c_idParam_Service_Abs = "framework.type.Service_Abs";

   private static final String c_idParam_Entity_Service_Abs = "framework.type.Entity.Service_Abs";

   private static final String c_idParam_ApplicationService_Itf = "framework.type.ApplicationService_Itf";

   private static final String c_idParam_UserBusiness_Abs = "framework.type.UserBusiness_Abs";

   private static final String c_idParam_Service_Itf = "framework.type.Service_Itf";

   private static final String c_idParam_Entity_Service_Itf = "framework.type.entity.Service_Itf";

   private static final String c_idParam_ServiceReferentiel_Itf = "framework.type.ServiceReferentiel_Itf";

   private static final String c_idParam_AttributesNames_Itf = "framework.type.AttributeNames_Itf";

   private static final String c_idParam_Dto_Itf = "framework.type.Dto_Itf";

   private static final String c_idParam_Mapper_Abs = "framework.type.Mapper_Abs";

   private static final String c_idParam_EntityMapper_Abs = "framework.type.EntityMapper_Abs";

   private static final String c_idParam_Mapper_Itf = "framework.type.Mapper_Itf";

   private static final String c_idParam_EntityMapper_Itf = "framework.type.EntityMapper_Itf";

   private static final String c_idParam_Match_Abs = "framework.type.Match_Abs";

   private static final String c_idParam_Match_Itf = "framework.type.Match_Itf";

   private static final String c_idParam_UserPersistence_Abs = "framework.type.UserPersistence_Abs";

   private static final String c_idParam_Dao_Itf = "framework.type.Dao_Itf";

   private static final String c_idParam_DaoJdbc_Abs = "framework.type.DaoJdbc_Abs";

   private static final String c_idParam_ColumnsNames_Itf = "framework.type.ColumnsNames_Itf";

   private static final String c_idParam_Entity_Itf = "framework.type.Entity_Itf";

   private static final String c_idParam_BeanTester_Abs = "framework.type.BeanTester_Abs";

   private static final String c_idParam_FetchingStrategyTester_Abs = "framework.type.FetchingStrategyTester_Abs";

   private static final String c_idParam_FetchingStrategyEntityTester_Abs = "framework.type.FetchingStrategyEntityTester_Abs";

   private static final String c_idParam_Xto_Itf = "framework.type.Xto_Itf";
   
   private static final String c_idParam_Xto_Rs_Itf = "framework.type.Xto_rs_Itf";

   private static final String c_idParam_Binary = "framework.type.Binary";

   @Override
   protected String get_propertiesFileName ()
   {
      return "framework_spi4j.properties";
   }
   
   @Override
   protected boolean is_defaultFileForAdditionalproperties ()
   {
      return Boolean.FALSE;
   }

   @Override
   protected PacmanProperty[] initPacmanProperties ()
   {
      return new PacmanProperty[]
      {
               PacmanProperty.newRequired(c_idParam_Service_Itf, "fr.spi4j.business.Service_Itf",
               "L'interface ancetre des services avec CRUD"),

               PacmanProperty.newRequired(c_idParam_Entity_Service_Itf, "fr.spi4j.entity.Service_Itf",
                        "L'interface ancetre des services avec CRUD (Sans la couche Matching)"),

               PacmanProperty.newRequired(c_idParam_Service_Abs, "fr.spi4j.business.Service_Abs",
                        "La classe ancetre des services avec CRUD"),

               PacmanProperty.newRequired(c_idParam_Entity_Service_Abs, "fr.spi4j.entity.Service_Abs",
                        "La classe ancetre des services avec CRUD (Sans la couche Matching)"),

               PacmanProperty.newRequired(c_idParam_ApplicationService_Itf, "fr.spi4j.business.ApplicationService_Itf",
                        "La classe ancetre de tous les services"),

               PacmanProperty.newRequired(c_idParam_UserBusiness_Abs, "fr.spi4j.business.UserBusiness_Abs",
                        "La classe ancetre de la factory des services"),

               PacmanProperty.newRequired(c_idParam_ServiceReferentiel_Itf, "fr.spi4j.business.ServiceReferentiel_Itf",
                        "L'interface marquant un service comme ayant un cache"),

               PacmanProperty.newRequired(c_idParam_AttributesNames_Itf, "fr.spi4j.business.dto.AttributesNames_Itf",
                        "L'interface pour les enumerations des attributs"),

               PacmanProperty.newRequired(c_idParam_Dto_Itf, "fr.spi4j.business.dto.Dto_Itf", 
                        "L'interface des DTOs"),

               PacmanProperty.newRequired(c_idParam_Mapper_Itf, "fr.spi4j.mapper.Mapper_Itf",
                        "L'interface des Mappers (conversion DTO <-> XTO)"),

               PacmanProperty.newRequired(c_idParam_EntityMapper_Itf, "fr.spi4j.mapper.EntityMapper_Itf",
                        "L'interface des Mappers (conversion Entity <-> XTO)"),

               PacmanProperty.newRequired(c_idParam_Mapper_Abs, "fr.spi4j.mapper.Mapper_Abs",
                        "La classe ancetre des Mappers (conversion DTO <-> XTO)"),

               PacmanProperty.newRequired(c_idParam_EntityMapper_Abs, "fr.spi4j.mapper.EntityMapper_Abs",
                        "La classe ancetre des Mappers (conversion Entity <-> XTO)"),

               PacmanProperty.newRequired(c_idParam_Match_Itf, "fr.spi4j.matching.Match_Itf",
                        "L'interface des Match (conversion Entity <-> DTO)"),

               PacmanProperty.newRequired(c_idParam_Match_Abs, "fr.spi4j.matching.Match_Abs",
                        "La classe ancetre des Match (conversion Entity <-> DTO)"),

               PacmanProperty.newRequired(c_idParam_UserPersistence_Abs, "fr.spi4j.persistence.UserPersistence_Abs",
                        "La classe ancetre de la factory des DAOs et Entities"),

               PacmanProperty.newRequired(c_idParam_Dao_Itf, "fr.spi4j.persistence.dao.Dao_Itf", 
                        "L'interface des DAOs"),

               PacmanProperty.newRequired(c_idParam_DaoJdbc_Abs, "fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs",
                        "La classe ancetre des DAO JDBC"),

               PacmanProperty.newRequired(c_idParam_ColumnsNames_Itf, "fr.spi4j.persistence.entity.ColumnsNames_Itf",
                        "L'interface pour les enumerations des colonnes"),

               PacmanProperty.newRequired(c_idParam_Entity_Itf, "fr.spi4j.persistence.entity.Entity_Itf",
                        "L'interface des Entities"),

               PacmanProperty.newRequired(c_idParam_BeanTester_Abs, "fr.spi4j.tua.BeanTester_Abs",
                        "La classe ancetre des testeurs de beans"),

               PacmanProperty.newRequired(c_idParam_FetchingStrategyTester_Abs, "fr.spi4j.tua.FetchingStrategyTester_Abs",
                        "La classe ancetre des testeurs de FetchingStrategy"),

               PacmanProperty.newRequired(c_idParam_FetchingStrategyEntityTester_Abs, "fr.spi4j.tua.FetchingStrategyEntityTester_Abs",
                        "La classe ancetre des testeurs de FetchingStrategy"),

               PacmanProperty.newRequired(c_idParam_Xto_Itf, "fr.spi4j.ws.xto.Xto_Itf", 
                        "L'interface des XTOs"),
               
               PacmanProperty.newRequired(c_idParam_Xto_Rs_Itf, "fr.spi4j.ws.rs.RsXto_Itf", 
                        "L'interface des XTOs pour les services REST"),

               PacmanProperty.newRequired(c_idParam_Binary, "fr.spi4j.persistence.dao.Binary", 
                        "La classe des Binaires (Binary)") };
   }
   
   /**
    * Recuperation des proprietes par le PacmanPropertiesManager.
    */

   /**
    * Retourne le nom de la classe d'apres son nom complet (avec package)
    * @param p_qualifedName
    *           le nom complet de la classe
    * @return le nom simple de la classe (sans package)
    */
   private static String getClassName (final String p_qualifedName)
   {
      if (p_qualifedName.contains("."))
      {
         return p_qualifedName.substring(p_qualifedName.lastIndexOf('.') + 1);
      }
      else
      {
         return p_qualifedName;
      }
   }

   /**
    * Retourne l'import pour Service_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour Service_Abs
    */
   public static String getImportForService_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Service_Abs);
   }

public static String getImportForService_AbsJavaService(Object object){return getImportForService_Abs();}

   /**
    * Retourne l'import pour Service_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour Service_Abs
    */
   public static String getImportForEntityService_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Entity_Service_Abs);
   }

public static String getImportForEntityService_AbsJavaService(Object object){return getImportForEntityService_Abs();}

   /**
    * Retourne le nom de la classe pour Service_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Service_Abs
    */
   public static String getClassNameForService_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Service_Abs));
   }

public static String getClassNameForService_AbsJavaService(Object object){return getClassNameForService_Abs();}

   /**
    * Retourne le nom de la classe pour Service_Abs (Sans la couche Matching)
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Service_Abs
    */
   public static String getClassNameForEntityService_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Entity_Service_Abs));
   }

public static String getClassNameForEntityService_AbsJavaService(Object object){return getClassNameForEntityService_Abs();}

   /**
    * Retourne l'import pour ApplicationService_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour ApplicationService_Itf
    */
   public static String getImportForApplicationService_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_ApplicationService_Itf);
   }

public static String getImportForApplicationService_ItfJavaService(Object object){return getImportForApplicationService_Itf();}

   /**
    * Retourne le nom de la classe pour ApplicationService_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour ApplicationService_Itf
    */
   public static String getClassNameForApplicationService_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_ApplicationService_Itf));
   }

public static String getClassNameForApplicationService_ItfJavaService(Object object){return getClassNameForApplicationService_Itf();}

   /**
    * Retourne l'import pour UserBusiness_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour UserBusiness_Abs
    */
   public static String getImportForUserBusiness_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_UserBusiness_Abs);
   }

public static String getImportForUserBusiness_AbsJavaService(Object object){return getImportForUserBusiness_Abs();}

   /**
    * Retourne le nom de la classe pour UserBusiness_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour UserBusiness_Abs
    */
   public static String getClassNameForUserBusiness_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_UserBusiness_Abs));
   }

public static String getClassNameForUserBusiness_AbsJavaService(Object object){return getClassNameForUserBusiness_Abs();}

   /**
    * Retourne l'import pour Service_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Service_Itf
    */
   public static String getImportForService_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Service_Itf);
   }

public static String getImportForService_ItfJavaService(Object object){return getImportForService_Itf();}

   /**
    * Retourne l'import pour Service_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Service_Itf
    */
   public static String getImportForEntityService_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Entity_Service_Itf);
   }

public static String getImportForEntityService_ItfJavaService(Object object){return getImportForEntityService_Itf();}

   /**
    * Retourne le nom de la classe pour Service_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Service_Itf
    */
   public static String getClassNameForService_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Service_Itf));
   }

public static String getClassNameForService_ItfJavaService(Object object){return getClassNameForService_Itf();}

   /**
    * Retourne le nom de la classe pour Service_Itf (Sans la couche Matching)
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Service_Itf
    */
   public static String getClassNameForEntityService_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Entity_Service_Itf));
   }

public static String getClassNameForEntityService_ItfJavaService(Object object){return getClassNameForEntityService_Itf();}

   /**
    * Retourne l'import pour ServiceReferentiel_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour ServiceReferentiel_Itf
    */
   public static String getImportForServiceReferentiel_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_ServiceReferentiel_Itf);
   }

public static String getImportForServiceReferentiel_ItfJavaService(Object object){return getImportForServiceReferentiel_Itf();}

   /**
    * Retourne le nom de la classe pour ServiceReferentiel_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour ServiceReferentiel_Itf
    */
   public static String getClassNameForServiceReferentiel_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_ServiceReferentiel_Itf));
   }

public static String getClassNameForServiceReferentiel_ItfJavaService(Object object){return getClassNameForServiceReferentiel_Itf();}

   /**
    * Retourne l'import pour AttributeNames_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour AttributeNames_Itf
    */
   public static String getImportForAttributesNames_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_AttributesNames_Itf);
   }

public static String getImportForAttributesNames_ItfJavaService(Object object){return getImportForAttributesNames_Itf();}

   /**
    * Retourne le nom de la classe pour AttributeNames_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour AttributeNames_Itf
    */
   public static String getClassNameForAttributesNames_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_AttributesNames_Itf));
   }

public static String getClassNameForAttributesNames_ItfJavaService(Object object){return getClassNameForAttributesNames_Itf();}

   /**
    * Retourne l'import pour Dto_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Dto_Itf
    */
   public static String getImportForDto_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Dto_Itf);
   }

public static String getImportForDto_ItfJavaService(Object object){return getImportForDto_Itf();}

   /**
    * Retourne le nom de la classe pour Dto_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Dto_Itf
    */
   public static String getClassNameForDto_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Dto_Itf));
   }

public static String getClassNameForDto_ItfJavaService(Object object){return getClassNameForDto_Itf();}

   /**
    * Retourne l'import pour Mapper_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour Mapper_Abs
    */
   public static String getImportForMapper_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Mapper_Abs);
   }

public static String getImportForMapper_AbsJavaService(Object object){return getImportForMapper_Abs();}

   /**
    * Retourne l'import pour EntityMapper_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour EntityMapper_Abs
    */
   public static String getImportForEntityMapper_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_EntityMapper_Abs);
   }

   /**
    * Retourne le nom de la classe pour Mapper_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Mapper_Abs
    */
   public static String getClassNameForMapper_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Mapper_Abs));
   }

public static String getClassNameForMapper_AbsJavaService(Object object){return getClassNameForMapper_Abs();}

   /**
    * Retourne le nom de la classe pour EntityMapper_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour EntityMapper_Abs
    */
   public static String getClassNameForEntityMapper_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_EntityMapper_Abs));
   }

public static String getClassNameForEntityMapper_AbsJavaService(Object object){return getClassNameForEntityMapper_Abs();}

   /**
    * Retourne l'import pour Mapper_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Mapper_Itf
    */
   public static String getImportForMapper_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Mapper_Itf);
   }

public static String getImportForMapper_ItfJavaService(Object object){return getImportForMapper_Itf();}

   /**
    * Retourne l'import pour EntityMapper_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour EntityMapper_Itf
    */
   public static String getImportForEntityMapper_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_EntityMapper_Itf);
   }

public static String getImportForEntityMapper_ItfJavaService(Object object){return getImportForEntityMapper_Itf();}

   /**
    * Retourne le nom de la classe pour Mapper_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Mapper_Itf
    */
   public static String getClassNameForMapper_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Mapper_Itf));
   }

public static String getClassNameForMapper_ItfJavaService(Object object){return getClassNameForMapper_Itf();}

   /**
    * Retourne le nom de la classe pour EntityMapper_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour EntityMapper_Itf
    */
   public static String getClassNameForEntityMapper_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_EntityMapper_Itf));
   }

public static String getClassNameForEntityMapper_ItfJavaService(Object object){return getClassNameForEntityMapper_Itf();}

   /**
    * Retourne l'import pour Match_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour Match_Abs
    */
   public static String getImportForMatch_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Match_Abs);
   }

public static String getImportForMatch_AbsJavaService(Object object){return getImportForMatch_Abs();}

   /**
    * Retourne le nom de la classe pour Match_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Match_Abs
    */
   public static String getClassNameForMatch_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Match_Abs));
   }

public static String getClassNameForMatch_AbsJavaService(Object object){return getClassNameForMatch_Abs();}

   /**
    * Retourne l'import pour Match_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Match_Itf
    */
   public static String getImportForMatch_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Match_Itf);
   }

public static String getImportForMatch_ItfJavaService(Object object){return getImportForMatch_Itf();}

   /**
    * Retourne le nom de la classe pour Match_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Match_Itf
    */
   public static String getClassNameForMatch_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Match_Itf));
   }

public static String getClassNameForMatch_ItfJavaService(Object object){return getClassNameForMatch_Itf();}

   /**
    * Retourne l'import pour UserPersistence_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour UserPersistence_Abs
    */
   public static String getImportForUserPersistence_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_UserPersistence_Abs);
   }

public static String getImportForUserPersistence_AbsJavaService(Object object){return getImportForUserPersistence_Abs();}

   /**
    * Retourne le nom de la classe pour UserPersistence_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour UserPersistence_Abs
    */
   public static String getClassNameForUserPersistence_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_UserPersistence_Abs));
   }

public static String getClassNameForUserPersistence_AbsJavaService(Object object){return getClassNameForUserPersistence_Abs();}

   /**
    * Retourne l'import pour Dao_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Dao_Itf
    */
   public static String getImportForDao_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Dao_Itf);
   }

public static String getImportForDao_ItfJavaService(Object object){return getImportForDao_Itf();}

   /**
    * Retourne le nom de la classe pour Dao_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Dao_Itf
    */
   public static String getClassNameForDao_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Dao_Itf));
   }

public static String getClassNameForDao_ItfJavaService(Object object){return getClassNameForDao_Itf();}

   /**
    * Retourne l'import pour DaoJdbc_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour DaoJdbc_Abs
    */
   public static String getImportForDaoJdbc_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_DaoJdbc_Abs);
   }

public static String getImportForDaoJdbc_AbsJavaService(Object object){return getImportForDaoJdbc_Abs();}

   /**
    * Retourne le nom de la classe pour DaoJdbc_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour DaoJdbc_Abs
    */
   public static String getClassNameForDaoJdbc_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_DaoJdbc_Abs));
   }

public static String getClassNameForDaoJdbc_AbsJavaService(Object object){return getClassNameForDaoJdbc_Abs();}

   /**
    * Retourne l'import pour ColumnsNames_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour ColumnsNames_Itf
    */
   public static String getImportForColumnsNames_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_ColumnsNames_Itf);
   }

public static String getImportForColumnsNames_ItfJavaService(Object object){return getImportForColumnsNames_Itf();}

   /**
    * Retourne le nom de la classe pour ColumnsNames_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour ColumnsNames_Itf
    */
   public static String getClassNameForColumnsNames_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_ColumnsNames_Itf));
   }

public static String getClassNameForColumnsNames_ItfJavaService(Object object){return getClassNameForColumnsNames_Itf();}

   /**
    * Retourne l'import pour Entity_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Entity_Itf
    */
   public static String getImportForEntity_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Entity_Itf);
   }

public static String getImportForEntity_ItfJavaService(Object object){return getImportForEntity_Itf();}

   /**
    * Retourne le nom de la classe pour Entity_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Entity_Itf
    */
   public static String getClassNameForEntity_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Entity_Itf));
   }

public static String getClassNameForEntity_ItfJavaService(Object object){return getClassNameForEntity_Itf();}

   /**
    * Retourne l'import pour BeanTester_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour BeanTester_Abs
    */
   public static String getImportForBeanTester_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_BeanTester_Abs);
   }

public static String getImportForBeanTester_AbsJavaService(Object object){return getImportForBeanTester_Abs();}

   /**
    * Retourne le nom de la classe pour BeanTester_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour BeanTester_Abs
    */
   public static String getClassNameForBeanTester_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_BeanTester_Abs));
   }

public static String getClassNameForBeanTester_AbsJavaService(Object object){return getClassNameForBeanTester_Abs();}

   /**
    * Retourne l'import pour FetchingStrategyTester_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour FetchingStrategyTester_Abs
    */
   public static String getImportForFetchingStrategyTester_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_FetchingStrategyTester_Abs);
   }

public static String getImportForFetchingStrategyTester_AbsJavaService(Object object){return getImportForFetchingStrategyTester_Abs();}

   /**
    * Retourne l'import pour FetchingStrategyTester_Abs
    * @param p_modelFile
    *           le modele
    * @return l'import pour FetchingStrategyTester_Abs
    */
   public static String getImportForFetchingStrategyEntityTester_Abs ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_FetchingStrategyEntityTester_Abs);
   }

public static String getImportForFetchingStrategyEntityTester_AbsJavaService(Object object){return getImportForFetchingStrategyEntityTester_Abs();}

   /**
    * Retourne le nom de la classe pour FetchingStrategyTester_Abs
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour FetchingStrategyTester_Abs
    */
   public static String getClassNameForFetchingStrategyTester_Abs ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_FetchingStrategyTester_Abs));
   }

public static String getClassNameForFetchingStrategyTester_AbsJavaService(Object object){return getClassNameForFetchingStrategyTester_Abs();}

   /*
    * Retourne le nom de la classe pour FetchingStrategyEntityTester_Abs
    * 
    * @param p_modelFile le modele
    * 
    * @return le nom de la classe pour FetchingStrategyEntityTester_Abs
    */
   public static String getClassNameForFetchingStrategyEntityTester_Abs ()
   {
      return getClassName(
               PacmanPropertiesManager.get_property(c_idParam_FetchingStrategyEntityTester_Abs));
   }

public static String getClassNameForFetchingStrategyEntityTester_AbsJavaService(Object object){return getClassNameForFetchingStrategyEntityTester_Abs();}

   /**
    * Retourne l'import pour Xto_Itf
    * @param p_modelFile
    *           le modele
    * @return l'import pour Xto_Itf
    */
   public static String getImportForXto_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Xto_Itf);
   }

public static String getImportForXto_ItfJavaService(Object object){return getImportForXto_Itf();}
   
   /**
    * Retourne l'import pour Xto_Itf pour RS.
    * @param p_modelFile
    *           le modele
    * @return l'import pour Xto_Itf
    */
   public static String getImportForRsXto_Itf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Xto_Rs_Itf);
   }

public static String getImportForRsXto_ItfJavaService(Object object){return getImportForRsXto_Itf();}

   /**
    * Retourne le nom de la classe pour Xto_Itf
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Xto_Itf
    */
   public static String getClassNameForXto_Itf ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Xto_Itf));
   }

public static String getClassNameForXto_ItfJavaService(Object object){return getClassNameForXto_Itf();}

   /**
    * Retourne l'import pour Binary
    * @param p_modelFile
    *           le modele
    * @return l'import pour Binary
    */
   public static String getImportForBinary ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_Binary);
   }

public static String getImportForBinaryJavaService(Object object){return getImportForBinary();}

   /**
    * Retourne le nom de la classe pour Binary
    * @param p_modelFile
    *           le modele
    * @return le nom de la classe pour Binary
    */
   public static String getClassNameForBinary ()
   {
      return getClassName(PacmanPropertiesManager.get_property(c_idParam_Binary));
   }

public static String getClassNameForBinaryJavaService(Object object){return getClassNameForBinary();}
}
