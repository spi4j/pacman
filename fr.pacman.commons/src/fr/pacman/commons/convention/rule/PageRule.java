package fr.pacman.commons.convention.rule;

import fr.pacman.commons.convention.NotationDefinition;
import fr.pacman.commons.properties.PacmanPropertiesCategory_Abs;
import fr.pacman.commons.properties.PacmanProperty;

/**
 * Classe des regles de nommage sur les pages.
 * Placer les proprietes dans l'ordre d'affichage desire dans le fichier.
 * 
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * !!!! PAS DE FORMATTAGE SUR CE FICHIER !!!!!
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * @author MINARM
 */
public class PageRule extends PacmanPropertiesCategory_Abs
{
   
   private static final String c_idParam_pageDefault = "pageDefault";

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
               PacmanProperty.newRequired(c_idParam_pageDefault, 
                        new String[] {
                                 "[" + NotationDefinition.normeTrimLowerAll() + "/]",
                                 
                                 "[" + NotationDefinition.normeTrimLowerAll() + "/]"
               },
               "La norme a appliquer pour une page web par defaut"), };
   }

   /**
    * Appliquer la norme 'pagedefault' sur la valeur passee en parametre.
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * @param p_modelFile
    *           les properties.
    * @return La valeur respectant la norme.
    */
   public static String applyNorme_pageDefault (final String p_value)
   {
      return applyNorme(p_value, c_idParam_pageDefault);
   }

}
