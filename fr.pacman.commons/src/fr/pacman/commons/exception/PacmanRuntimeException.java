package fr.pacman.commons.exception;

/**
 * Erreur Runtime pour Pacman.
 * @author MINARM
 */
public class PacmanRuntimeException extends RuntimeException
{

   private static final long serialVersionUID = 1L;

   /**
    * Constructeur avec cause.
    * @param p_cause
    *           la cause
    */
   public PacmanRuntimeException (final Throwable p_cause)
   {
      super(p_cause);
   }

   /**
    * Constructeur avec message et cause.
    * @param p_message
    *           le message
    * @param p_cause
    *           la cause
    */
   public PacmanRuntimeException (final String p_message, final Throwable p_cause)
   {
      super(p_message, p_cause);
   }

}
