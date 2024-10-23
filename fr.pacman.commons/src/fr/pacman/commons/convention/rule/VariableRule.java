package fr.pacman.commons.convention.rule;

import fr.pacman.commons.convention.NotationDefinition;
import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Classe des regles de nommage sur les variables.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * @author MINARM
 */
public class VariableRule extends PacmanPropertiesCategory_Abs
{

   private static final String c_idParam_variableFinal = "variableFinal";

   private static final String c_idParam_variableDefault = "variableDefault";

   private static final String c_idParam_variableObjectFinal = "variableObjectFinal";

   private static final String c_idParam_variableObjectDefault = "variableObjectDefault";

   private static final String c_idParam_variableMultipleDefault = "variableMultipleDefault";

   private static final String c_idParam_variableMultipleMap = "variableMultipleMap";

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
         PacmanProperty.newRequired(c_idParam_variableFinal,  
                  new String[] {  
                           "[" +  NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                           
                           "v_[" + NotationDefinition.normeCamelCaseIgnoreFirst()
                           + "/]"
                  },
                  "Le nom d'une variable final"),
              
         PacmanProperty.newRequired(c_idParam_variableDefault,  
                  new String[] {  
                           "[" + NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst())  + "/]",
                           
                           "v_[" + NotationDefinition.normeCamelCaseIgnoreFirst()
                           + "/]"
                  },
                  "Le nom d'une variable par defaut"),
              
         PacmanProperty.newRequired(c_idParam_variableMultipleDefault,  
                  new String[] {  
                           "[" + NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst())  + "/]",
                           
                           "v_{$listPrefix}_[" + NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                  },
                  "Le nom d'une variable multiple par defaut"),
              
         PacmanProperty.newRequired(c_idParam_variableMultipleMap,  
                  new String[] {  
                           "[" + NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                           
                           "v_{$mapPrefix}_[" + NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst()) + "/]"
                  },
                  "Le nom d'une variable multiple (map) par defaut"),
               
         PacmanProperty.newRequired(c_idParam_variableObjectFinal,  
                  new String[] {  
                           "[" + NotationDefinition.normeCamelCase()
                           .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                           
                           "v_[" + NotationDefinition.normeCamelCase() + "/]"
                  },
                  "Le nom d'une variable objet final"),
              
         PacmanProperty.newRequired(c_idParam_variableObjectDefault,  
                  new String[] {  
                           "[" + NotationDefinition.normeCamelCase() 
                           .appendNorme(NotationDefinition.normeLowerFirst()) + "/]",
                           
                           "v_[" + NotationDefinition.normeCamelCase() + "/]"
                  },
                  "Le nom d'une variable par objet defaut"), };
   }

   /**
    * Appliquer la norme 'variableFinal' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_variableFinal (final String p_value)
   {
      return applyNorme(p_value, c_idParam_variableFinal);
   }

   /**
    * Appliquer la norme 'variableDefault' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_variableDefault (final String p_value)
   {
      return applyNorme(p_value, c_idParam_variableDefault);
   }

   /**
    * Appliquer la norme 'variableObjectFinal' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_variableObjectFinal (final String p_value)
   {
      return applyNorme(p_value, c_idParam_variableObjectFinal);
   }

   /**
    * Appliquer la norme 'variableObjectDefault' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_variableObjectDefault (final String p_value)
   {
      return applyNorme(p_value, c_idParam_variableObjectDefault);
   }

   /**
    * Appliquer la norme 'variableMultipleDefault' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_variableMultipleDefault (final String p_value)
   {
      return applyNorme(p_value, c_idParam_variableMultipleDefault);
   }

   /**
    * Appliquer la norme 'variableMultipleMap' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_variableMultipleMap (final String p_value)
   {
      return applyNorme(p_value, c_idParam_variableMultipleMap);
   }
}
