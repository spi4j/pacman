package fr.pacman.commons.convention.rule;

import fr.pacman.commons.convention.NotationDefinition;
import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Classe des regles de nommage sur les methodes.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * @author MINARM
 */
public class MethodRule extends PacmanPropertiesCategory_Abs
{
   private static final String c_idParam_methodDefault = "methodDefault";

   private static final String c_idParam_methodMultiple = "methodMultiple";

   private static final String c_idParam_methodGet = "methodGet";

   private static final String c_idParam_methodSet = "methodSet";

   private static final String c_idParam_methodReset = "methodReset";

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
               PacmanProperty.newRequired(c_idParam_methodDefault,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]", 
                        
                                 "[" +  NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'une methode par defaut"),
               
               PacmanProperty.newRequired(c_idParam_methodMultiple,  
                        new String[] {  
                                 "[" +  NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]", 
                        
                                 "[" + NotationDefinition.normeLanguageC() + "/]"
                        },
                        "Le nom d'une methode multiple"),
               
               PacmanProperty.newRequired(c_idParam_methodGet,  
                        new String[] {  
                                 "get["  + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeUpperFirst()) + "/]",
                                 
                                 "get_[" + NotationDefinition.normeCamelCaseIgnoreFirst() + "/]"
               },
                        "Le nom d'une methode get"),
              
               PacmanProperty.newRequired(c_idParam_methodSet,  
                        new String[] {  
                                 "set[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeUpperFirst()) + "/]",
                                 
                                 "set_[" + NotationDefinition.normeCamelCaseIgnoreFirst() + "/]"
               },
                        "Le nom d'une methode set"),
               
               PacmanProperty.newRequired(c_idParam_methodReset,  
                        new String[] { 
                                 "reset[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeUpperFirst()) + "/]",
                                 
                                 "reset_[" + NotationDefinition.normeCamelCaseIgnoreFirst()
                                 + "/]"
               },
                        "Le nom d'une methode reset"), };
   }
   
   /**
    * Recuperation des proprietes par le PacmanPropertiesManager.
    */

   /**
    * Appliquer la norme 'methodDefault' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_methodDefault (final String p_value)
   {
      return applyNorme(p_value, c_idParam_methodDefault);
   }

   /**
    * Appliquer la norme 'methodMultiple' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_methodMultiple (final String p_value)
   {
      return applyNorme(p_value, c_idParam_methodMultiple);
   }

   /**
    * Appliquer la norme 'methodGet' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_methodGet (final String p_value)
   {
      return applyNorme(p_value, c_idParam_methodGet);
   }

   /**
    * Appliquer la norme 'methodSet' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_methodSet (final String p_value)
   {
      return applyNorme(p_value, c_idParam_methodSet);
   }

   /**
    * Appliquer la norme 'methodReset' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_methodReset (final String p_value)
   {
      return applyNorme(p_value, c_idParam_methodReset);
   }
}
