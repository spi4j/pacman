package fr.pacman.validation.services.rules.implem;

import fr.pacman.commons.exception.PacmanRuntimeException;

/**
 * Méthodes communes aux services de validation.
 * @author MINARM
 */
public abstract class DslService_Abs
{

   /**
    * Retourne un paramètre comme un entier.
    * @param p_param
    *           le paramètre de la règle
    * @return le paramètre entier
    */
   public int getIntegerParam (final Object p_param)
   {
      try
      {
         return Integer.parseInt(p_param.toString());
      }
      catch (final NumberFormatException v_e)
      {
         throw new PacmanRuntimeException("Impossible de récupérer le paramètre comme un Integer : " + p_param.toString(), v_e);
      }
   }

}
