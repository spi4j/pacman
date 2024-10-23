package fr.pacman.commons.convention.rule;

import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Classe des regles de nommage sur les packages.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * @author MINARM
 */
public class PackageRule extends PacmanPropertiesCategory_Abs
{
   private static final String c_idParam_packagePersistence = "packagePersistence";

   private static final String c_idParam_packageImplemJdbc = "packageImplemJdbc";

   private static final String c_idParam_packageApi = "packageApi";

   private static final String c_idParam_packageOverload = "packageOverload";

   private static final String c_idParam_packageReferentiel = "packageReferentiel";

   private static final String c_idParam_packageImplemServer = "packageImplemServer";

   private static final String c_idParam_packageMatching = "packageMatching";

   private static final String c_idParam_packageBusiness = "packageBusiness";

   private static final String c_idParam_packageRequirement = "packageRequirement";

   private static final String c_idParam_packagePgeSwing = "packagePgeSwing";

   private static final String c_idParam_packagePgeGwt = "packagePgeGwt";
   
   private static final String c_idParam_packagePgeJsf = "packagePgeJsf";

   @Override
   protected String get_propertiesFileName ()
   {
      return "nommage.properties";
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
               PacmanProperty.newRequired(c_idParam_packagePersistence, 
                        new String[] {  
                                 "persistence",
                                 
                                 "persistence"
                        }, 
                        "Le nom du package de persistence"),

               PacmanProperty.newRequired(c_idParam_packageImplemJdbc, 
                        new String[] {  
                                 "jdbc",
                                 
                                 "impl_jdbc"
                        }, 
                        "Le nom du package d'implementation jdbc"),

               PacmanProperty.newRequired(c_idParam_packageApi, 
                        new String[] {  
                                 "api",  
                                 
                                 "api"
                        }, 
                        "Le nom du package d'api"),

               PacmanProperty.newRequired(c_idParam_packageOverload, 
                        new String[] {  
                                 "dbpopulate",  
                                 
                                 "dbpopulate"
                        },
                        "Le nom du package pour la montee en charge"),

               PacmanProperty.newRequired(c_idParam_packageReferentiel, 
                        new String[] {  
                                 "dbreferentiel",     
                                 
                                 "dbreferentiel"
                        },
                        "Le nom du package pour l import de referentiel"),

               PacmanProperty.newRequired(c_idParam_packageImplemServer, 
                        new String[] {  
                                 "server",     
                                 
                                 "impl_server"
                        },
                        "Le nom du package d'implementation server"),

               PacmanProperty.newRequired(c_idParam_packageMatching, 
                        new String[] {  
                                 "matching",   
                                 
                                 "matching"
                        }, 
                        "Le nom du package de matching"),

               PacmanProperty.newRequired(c_idParam_packageBusiness, 
                        new String[] {  
                                 "business",    
                                 
                                 "business"
                        }, 
                        "Le nom du package du business"),

               PacmanProperty.newRequired(c_idParam_packageRequirement, 
                        new String[] {  
                                 "requirement",    
                                 
                                 "requirement"
                        }, 
                        "Le nom du package de requirement"),

               PacmanProperty.newRequired(c_idParam_packagePgeSwing, 
                        new String[] {  
                                 "client.ui.swing.widgets",  
                                 
                                 "client.ui.swing.widgets"
                        },
                        "Le nom du package pour les composants PGE Swing"),

               PacmanProperty.newRequired(c_idParam_packagePgeGwt, 
                        new String[] {  
                                 "client.ui.gwt.widgets",
                                 
                                 "client.ui.gwt.widgets"
                        },
                        "Le nom du package pour les composants PGE GWT"), };
   }

   /**
    * Appliquer la norme 'packagePersistence' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packagePersistence (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packagePersistence);
   }

   /**
    * Appliquer la norme 'packageImplemJdbc' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packageImplemJdbc (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packageImplemJdbc);
   }

   /**
    * Appliquer la norme 'packageApi' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packageApi (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packageApi);
   }

   /**
    * Appliquer la norme 'packageImplemServer' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packageImplemServer (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packageImplemServer);
   }

   /**
    * Appliquer la norme 'packageMatching' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packageMatching (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packageMatching);
   }

   /**
    * Appliquer la norme 'packageBusiness' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packageBusiness (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packageBusiness);
   }

   /**
    * Appliquer la norme 'packageRequirement' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_properties
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_packageRequirement (final String p_value)
   {
      return applyNorme(p_value, c_idParam_packageRequirement);
   }
   
   /**
    * Bien que la classe soit nommee "PackageRule" 
    * elle est aussi utilisee pour recuperer 
    * directement les noms de package.
    */
   
   public static String getPackagePersistence ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packagePersistence);
   }

   public static String getPackagePersistenceJavaService(Object object){return getPackagePersistence();}
   
   public static String getPackageImplemJdbc ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageImplemJdbc);
   }

   public static String getPackageImplemJdbcJavaService(Object object){return getPackageImplemJdbc();}
   
   public static String getPackageApi ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageApi);
   }

   public static String getPackageApiJavaService(Object object){return getPackageApi();}
   
   public static String getPackageOverload ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageOverload);
   }

   public static String getPackageOverloadJavaService(Object object){return getPackageOverload();}
   
   public static String getPackageReferentiel ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageReferentiel);
   }

   public static String getPackageReferentielJavaService(Object object){return getPackageReferentiel();}
   
   public static String getPackageImplemServer ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageImplemServer);
   }

   public static String getPackageImplemServerJavaService(Object object){return getPackageImplemServer();}
   
   public static String getPackagePgeJsf ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packagePgeJsf);
   }

   public static String getPackagePgeJsfJavaService(Object object){return getPackagePgeJsf();}
   
   public static String getPackageMatching ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageMatching);
   }

   public static String getPackageMatchingJavaService(Object object){return getPackageMatching();}
   
   public static String getPackageBusiness ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageBusiness);
   }

   public static String getPackageBusinessJavaService(Object object){return getPackageBusiness();}
   
   public static String getPackageRequirement ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packageRequirement);
   }

   public static String getPackageRequirementJavaService(Object object){return getPackageRequirement();}
   
   public static String getPackagePgeSwing ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packagePgeSwing);
   }

   public static String getPackagePgeSwingJavaService(Object object){return getPackagePgeSwing();}
   
   public static String getPackagePgeGwt ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_packagePgeGwt);
   }

   public static String getPackagePgeGwtJavaService(Object object){return getPackagePgeGwt();}
}
