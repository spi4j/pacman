package fr.pacman.commons.convention.norme;

/**
 * Définition d'une norme au sens large.
 */
public abstract class Norme_Abs
{

   /**
    * Appliquer la norme à la valeur spécifiée.
    * @param p_value
    *           La valeur à résoudre.
    * @return La valeur résolue (norme appliquée).
    */
   abstract public String applyNorme (String p_value);
}
