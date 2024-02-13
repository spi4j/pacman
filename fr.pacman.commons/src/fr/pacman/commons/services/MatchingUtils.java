package fr.pacman.commons.services;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * Classe utilitaire pour la génération de la couche de matching.
 * 
 * @author MINARM
 */
public final class MatchingUtils
{

   /**
    * Constructeur privé.
    */
   private MatchingUtils ()
   {
      // RAS
   }

   /**
    * Retourne la racine du modèle.
    * 
    * @param p_dto
    *           un dto
    * @return la racine du modèle
    */
   public static EObject getRoot (final EObject p_dto)
   {
      return EcoreUtil.getRootContainer(p_dto);
   }
}
