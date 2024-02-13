package fr.pacman.commons.convention;

import java.util.Properties;

/**
 * Classe de résolution d'une convention de nommage.
 * @author MINARM
 */
public class NotationResolution
{

   public static final String c_idPatternStart = "[";

   public static final String c_idPatternEnd = "/]";

   /** Le pattern de resolution (ex : "p_[LowerAll.UpperFirst.LanguageC/]") */
   private String _pattern;

   /**
    * Le contructeur par défaut.
    * @param p_pattern
    *           Le pattern à appliquer.
    */
   public NotationResolution (final String p_pattern)
   {
      _pattern  = p_pattern;
   }

   /**
    * Permet d'obtenir le nom résolu : après application du pattern. Avec remplacement des properties.
    * @param p_value
    *           la valeur.
    * @param p_properties
    *           les properties.
    * @return Le nom résolu.
    */
   public String applyNorme (final String p_value, final Properties p_properties)
   {
      String v_return;
      // Remplacer les éléments fixes (= fichier Properties)
      v_return = replaceTagsOfProperties(_pattern, p_properties);
      // Réaffecter le pattern suite à la résolution du tag.
      _pattern = v_return;
      // Obtenir le prefixe
      final String v_prefixe;
      // Obtenir le suffixe
      final String v_suffixe;

      final NotationDefinition v_NotationDefinition;

      // Obtenir le rang de début de la norme à appliquer
      final int v_rgDeb = _pattern.indexOf(c_idPatternStart);
      // Obtenir le rang de fin de la norme à appliquer
      final int v_rgFin = _pattern.indexOf(c_idPatternEnd);
      // Si on a trouvé le marqueur de début et le marqueur de fin
      if (v_rgDeb >= 0 && v_rgFin >= 0 && v_rgDeb < v_rgFin)
      {
         // Obtenir le nom de la norme à appliquer
         final String v_valueNorme = _pattern.substring(v_rgDeb + c_idPatternStart.length(), v_rgFin);         
         // Instancier un 'NotationDefinition'
         v_NotationDefinition = new NotationDefinition(v_valueNorme);
         // Obtenir le prefixe
         v_prefixe = _pattern.substring(0, v_rgDeb);
         // Obtenir le suffixe
         v_suffixe = _pattern.substring(v_rgFin + c_idPatternEnd.length(), _pattern.length());
      }
      // Si aucun préfixe ni suffixe
      else
      {
         v_prefixe = "";
         v_suffixe = "";
         v_NotationDefinition = new NotationDefinition("");
      }
      // Appliquer la norme
      v_return = v_prefixe + v_NotationDefinition.applyNorme(p_value) + v_suffixe;
      return v_return;
   }

   /**
    * Remplace les références à une autre propriété dans la chaîne à résoudre.
    * @param p_value
    *           la valeur initiale.
    * @param p_properties
    *           les properties.
    * @return La chaine remplacée.
    */
   public static String replaceTagsOfProperties (final String p_value, final Properties p_properties)
   {
      String v_return = p_value;
      // S'il y a au moins un tag a remplacer
      if ((p_value != null) && (p_value.indexOf("{$") >= 0))
      {
         // Parcourir les propriétés connues.
         for (final String v_propertyName : p_properties.stringPropertyNames())
         {
            // Si la propriété existe dans la valeur.
            if (p_value.indexOf(getVarName(v_propertyName)) >= 0)
            {
               v_return = v_return.replace("{$" + v_propertyName + "}",
                        replaceTagsOfProperties(p_properties.getProperty(v_propertyName), p_properties));
            }
         }
      }
      return v_return;
   }

   /**
    * Obtenir le nom de la propriété formaté avec son $.
    * @param p_propertyName
    *           le nom de la propriété à formater.
    * @return le nom de la propriété formaté avec son $.
    */
   private static String getVarName (final String p_propertyName)
   {
      return "{$" + p_propertyName + "}";
   }

   /**
    * Retourne la chaîne à résoudre.
    * @return La chaîne à résoudre.
    */
   @Override
   public String toString ()
   {
      return c_idPatternStart + _pattern + c_idPatternEnd;
   }

}
