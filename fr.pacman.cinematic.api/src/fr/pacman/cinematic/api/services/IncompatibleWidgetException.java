package fr.pacman.cinematic.api.services;

/**
 * Exception si le widget est incompatible pour la génération.
 * @author MINARM
 */
public class IncompatibleWidgetException extends Exception
{

   private static final long serialVersionUID = 1L;

   /**
    * Constructeur.
    * @param p_message
    *           le message d'erreur
    */
   public IncompatibleWidgetException (final String p_message)
   {
      super(p_message);
   }

}
