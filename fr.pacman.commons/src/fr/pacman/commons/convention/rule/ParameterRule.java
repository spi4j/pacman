package fr.pacman.commons.convention.rule;

import fr.pacman.commons.convention.NotationDefinition;
import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Classe des regles de nommage sur les parametres.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 *
 * @author MINARM
 */
public class ParameterRule extends PacmanPropertiesCategory_Abs
{

   private static final String c_idParam_parameterMethodClassicIn = "parameterMethodClassicIn";

   private static final String c_idParam_parameterMethodMultiple = "parameterMethodMultiple";

   private static final String c_idParam_parameterMethodMultipleMap = "parameterMethodMultipleMap";

   private static final String c_idParam_parameterMethodObjectIn = "parameterMethodObjectIn";

   private static final String c_idParam_parameterMethodClassicOut = "parameterMethodClassicOut";

   private static final String c_idParam_parameterMethodObjectOut = "parameterMethodObjectOut";

   private static final String c_idParam_parameterMethodClassicInOut = "parameterMethodClassicInOut";

   private static final String c_idParam_parameterMethodObjectInOut = "parameterMethodObjectInOut";

   private static final String c_idParam_parameterMethodReset = "parameterMethodReset";

   private static final String c_idParam_parameterMethodSetter = "parameterMethodSetter";

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
               PacmanProperty.newRequired(c_idParam_parameterMethodClassicIn, 
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'un parametre IN classic"),
               
               PacmanProperty.newRequired(c_idParam_parameterMethodMultiple,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_{$listPrefix}_["+ NotationDefinition.normeCamelCase()
                                  .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'un parametre Multiple"),
               
               PacmanProperty.newRequired(c_idParam_parameterMethodMultipleMap,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_{$mapPrefix}_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'un parametre Multiple"),
               
               PacmanProperty.newRequired(c_idParam_parameterMethodObjectIn,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeUpperFirst()) + "/]"
                        },
                        "Le nom d'un parametre IN objet"),
              
               PacmanProperty.newRequired(c_idParam_parameterMethodClassicOut,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                         },
                        "Le nom d'un parametre OUT classique"),
              
               PacmanProperty.newRequired(c_idParam_parameterMethodObjectOut,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeUpperFirst()) + "/]"
                        },
                        "Le nom d'un parametre OUT objet"),
              
               PacmanProperty.newRequired(c_idParam_parameterMethodClassicInOut,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'un parametre IN/OUT classique"),
               
               PacmanProperty.newRequired(c_idParam_parameterMethodObjectInOut,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeUpperFirst()) + "/]"
                        },
                        "Le nom d'un parametre IN/OUT objet"),
              
               PacmanProperty.newRequired(c_idParam_parameterMethodReset,  
                        new String[] { 
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'un parametre du methode reset"),
               
               PacmanProperty.newRequired(c_idParam_parameterMethodSetter,  
                        new String[] {  
                                 "[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                                 
                                 "p_[" + NotationDefinition.normeCamelCase()
                                 .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                        },
                        "Le nom d'un parametre d'un setter") };
   }

   /**
    * Appliquer la norme 'parameterMethodClassicIn' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodClassicIn (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodClassicIn);
   }

   /**
    * Appliquer la norme 'parameterMethodMultiple' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodMultiple (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodMultiple);
   }

   /**
    * Appliquer la norme 'parameterMethodMultipleMap' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodMultipleMap (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodMultipleMap);
   }

   /**
    * Appliquer la norme 'parameterMethodObjectIn' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodObjectIn (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodObjectIn);
   }

   /**
    * Appliquer la norme 'parameterMethodClassicOut' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodClassicOut (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodClassicOut);
   }

   /**
    * Appliquer la norme 'parameterMethodObjectOut' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodObjectOut (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodObjectOut);
   }

   /**
    * Appliquer la norme 'parameterMethodClassicInOut' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodClassicInOut (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodClassicInOut);
   }

   /**
    * Appliquer la norme 'parameterMethodClassicInOut' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodObjectInOut (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodObjectInOut);
   }

   /**
    * Appliquer la norme 'parameterMethodReset' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "montant reel").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_parameterMethodReset (final String p_value)
   {
      return applyNorme(p_value, c_idParam_parameterMethodReset);
   }
}
