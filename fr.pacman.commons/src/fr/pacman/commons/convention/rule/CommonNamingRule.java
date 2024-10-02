package fr.pacman.commons.convention.rule;

import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Classe commune pour le nommage.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * @author MINARM
 */
public class CommonNamingRule extends PacmanPropertiesCategory_Abs
{

   private static final String c_idParam_listPrefix = "listPrefix";

   private static final String c_idParam_mapPrefix = "mapPrefix";

   private static final String c_idParam_affectation_prefix = "affectationPrefix";

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
               
         PacmanProperty.newRequired(c_idParam_listPrefix, 
                  new String[] { 
                           "", 
                           
                           "tab"
                  }, 
                  "Le prefixe des listes"),

         PacmanProperty.newRequired(c_idParam_mapPrefix, 
                  new String[] {
                           "", 
                           
                           "map"
                  }, 
                  "Le prefixe des map"),
         
         PacmanProperty.newRequired(c_idParam_affectation_prefix, 
                  new String[] {
                           "this.", 
                           
                             ""
                  },
                  "Prefix des attributs lors d'une affectation (this. ou rien)")
         
      };
   }
   
   public static String getListPrefix()
   {
      return PacmanPropertiesManager.get_property(c_idParam_listPrefix);
   }

   public static String getListPrefixJavaService(Object object){return getListPrefix();}
   
   public static String getMapPrefix ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_mapPrefix);
   }

   public static String getMapPrefixJavaService(Object object){return getMapPrefix();}
   
   public static String getAffectationPrefix ()
   {
      return PacmanPropertiesManager.get_property(c_idParam_affectation_prefix);
   }

   public static String getAffectationPrefixJavaService(Object object){return getAffectationPrefix();}
}
