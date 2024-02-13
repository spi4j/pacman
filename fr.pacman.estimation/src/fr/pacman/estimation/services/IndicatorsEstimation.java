package fr.pacman.estimation.services;

import java.lang.reflect.Method;

/**
 * Contient les différents indicateurs possibles.
 * 
 * @author MINARM
 * @param <TypeRacine>
 *           le type d'objet en entrée de l'estimation (IFile ou EObject)
 */
public class IndicatorsEstimation<TypeRacine>
{

   private TypeRacine _rootModel;

   private Double _complexiteEnPfGdi;

   private Double _complexiteEnPfGdiRapide;

   /**
    * Affecter la complexité en PF.
    * 
    * @param p_complexiteEnPfGdi
    *           La valeur.
    */
   public void set_complexiteEnPfGdi (final Double p_complexiteEnPfGdi)
   {
      _complexiteEnPfGdi = p_complexiteEnPfGdi;
   }

   /**
    * Obtenir la complexité.
    * 
    * @return La valeur.
    */
   public Double get_complexiteEnPfGdi ()
   {
      return _complexiteEnPfGdi;
   }

   /**
    * Affecter la complexité rapide (= nb classes * 7,3) en PF.
    * 
    * @param p_complexiteEnPfGdiRapide
    *           La valeur.
    */
   public void set_complexiteEnPfGdiRapide (final Double p_complexiteEnPfGdiRapide)
   {
      _complexiteEnPfGdiRapide = p_complexiteEnPfGdiRapide;
   }

   /**
    * Obtenir la complexité rapide.
    * 
    * @return La valeur.
    */
   public Double get_complexiteEnPfGdiRapide ()
   {
      return _complexiteEnPfGdiRapide;
   }

   /**
    * Obtenir la racine du modèle sur laquelle on a effectué l'esimation des charges
    * 
    * @return la racine du modèle sur laquelle on a effectué l'esimation des charges
    */
   public TypeRacine get_rootModel ()
   {
      return _rootModel;
   }

   /**
    * Affecter la racine du modèle
    * 
    * @param p_rootModel
    *           la racine du modèle
    */
   public void set_rootModel (final TypeRacine p_rootModel)
   {
      this._rootModel = p_rootModel;
   }

   @Override
   public String toString ()
   {
      String v_nameModel;
      Method v_Method_getName = null;
      try
      {
         v_Method_getName = _rootModel.getClass().getMethod("getName");

         // Si pas de méthode "getName()"
         if (v_Method_getName == null)
         {
            v_nameModel = _rootModel.toString();
         }
         // Si on a une méthode "getName()"
         else
         {
            v_nameModel = (String) v_Method_getName.invoke(_rootModel);
         }
      }
      catch (Exception v_e)
      {
         v_nameModel = _rootModel.toString();
      }

      return
      // Racine utilisée
      "Racine utilisée pour l'estimation : " + v_nameModel + "\n"
      // Complexité macro
      + "Complexité macro : "
      + _complexiteEnPfGdi
      + "\n"
      // Complexité macro rapide
      + "Complexité macro rapide : "
      + _complexiteEnPfGdiRapide;
   }
}
