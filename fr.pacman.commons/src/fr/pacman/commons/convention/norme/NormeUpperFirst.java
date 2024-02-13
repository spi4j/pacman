package fr.pacman.commons.convention.norme;

import java.util.StringTokenizer;

/**
 * Norme UpperFirst.
 * @author MINARM
 */
public class NormeUpperFirst extends Norme_Abs
{

   /**
    * Appliquer la norme "UpperFirst" (ex : "uNe vaLEur" --> "UNe VaLEur").
    * @param p_value
    *           La valeur d'origine.
    * @return Une instance prenant en compte cette notation.
    */
   @Override
   public String applyNorme (final String p_value)
   {
      String v_return = "";
      final StringTokenizer v_st = new StringTokenizer(p_value, " ");
      String v_valueCour;
      int v_nbMots = 0;
      // Parcourir les mots (ex : "une valeur")
      while (v_st.hasMoreTokens() == true)
      {
         // Obtenir le nom de notation courante
         v_valueCour = (String) v_st.nextElement();
         // Si 1er mot
         if (v_nbMots == 0)
         {
            v_return = v_valueCour.toUpperCase().charAt(0) + v_valueCour.substring(1, v_valueCour.length());
         }
         // Si au moins un mot de lu
         else
         {
            // Mettre en "UpperFirst"
            v_return += " " + v_valueCour.toUpperCase().charAt(0) + v_valueCour.substring(1, v_valueCour.length());
         }
         v_nbMots++;
      }
      return v_return;
   }
}
