package fr.pacman.commons.errorgen;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception pour indiquer une erreur de génération.
 */
class ErrorGenerationException extends RuntimeException
{
   /** Pour la sérialisation */
   private static final long serialVersionUID = 1L;

   public static final String c_libErrorGeneration = "[ERROR_GEN] --> ";

   /** L'exception encapsulée */
   private final Throwable _ExceptionWrapped;

   /** Solution(s) potentielle(s) à l'erreur */
   private final String _solutionPotentielle;

   /** La liste d'erreur de génération supplémentaire (cas d'une 'ErrorGenerationException' en chapeau des autres pour affichage dans le "Error Log") */
   private final List<ErrorGenerationException> _lst_ExceptionSupplem = new ArrayList<ErrorGenerationException>();

   /**
    * Constructeur.
    * @param p_ExceptionWrapped
    *           L'exception encapsulée.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    */
   public ErrorGenerationException (final Throwable p_ExceptionWrapped, final String p_solutionPotentielle)
   {
      super(p_ExceptionWrapped.getMessage());
      _ExceptionWrapped = p_ExceptionWrapped;
      _solutionPotentielle = p_solutionPotentielle;
   }

   /**
    * Constructeur.
    * @param p_message
    *           Le message d'erreur.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    */
   public ErrorGenerationException (final String p_message, final String p_solutionPotentielle)
   {
      super(p_message);
      _ExceptionWrapped = null;
      _solutionPotentielle = p_solutionPotentielle;
   }

   /**
    * Obtenir la solution potentielles.
    * @return La chaîne désirée.
    */
   public String get_solutionPotentielle ()
   {
      return _solutionPotentielle;
   }

   /**
    * Permet d'ajouter une erreur de génération.
    * @param p_Exception
    *           L'exception à ajouter.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    */
   public void addException (final Throwable p_Exception, final String p_solutionPotentielle)
   {
      // Mémoriser l'erreur de génération.
      final ErrorGenerationException v_errorGeneration = new ErrorGenerationException(p_Exception,
               p_solutionPotentielle);
      // Obtenir la dernière erreur de génération mémorisée
      final ErrorGenerationException v_lastException = getLastErrorGenerationException();
      // Chaîner les "RootCause"
      v_lastException.initCause(v_errorGeneration);

      // Mémoriser l'erreur de génération.
      _lst_ExceptionSupplem.add(v_errorGeneration);
   }

   /**
    * Obtenir la dernière erreur de génération mémorisée
    * @return L'erreur désirée.
    */
   public ErrorGenerationException getLastErrorGenerationException ()
   {
      ErrorGenerationException v_lastException;
      // Si aucune élément
      if (_lst_ExceptionSupplem.isEmpty() == true)
      {
         v_lastException = this;
      }
      // Si on a au moins 1 élément
      else
      {
         v_lastException = _lst_ExceptionSupplem.get(_lst_ExceptionSupplem.size() - 1);
      }
      return v_lastException;
   }

   /**
    * Obtenir la liste des axception ajoutées.
    * @return La liste désirée.
    * @see #addException(Exception)
    */
   List<ErrorGenerationException> get_tab_ExceptionSupplem ()
   {
      return _lst_ExceptionSupplem;
   }

   @Override
   public String toString ()
   {
      return toStringFmt1() + toStringFmt2();
   }

   /**
    * Obtenir l'erreur sous la forme d'une chaîne de caractères (sans les exceptions encapsulée).
    * @return La chaîne de caractères représente l'instance.
    */
   private String toStringFmt1 ()
   {
      String v_return = c_libErrorGeneration + getMessage();

      // Si l'on a une Exception encapsulée
      if (_ExceptionWrapped != null)
      {
         v_return = v_return + " [Exception encapsulée = " + _ExceptionWrapped.getClass().getName() + " : "
                  + _ExceptionWrapped.getMessage() + "] ";
      }

      // Si on a une solution potentielle
      if (_solutionPotentielle != null)
      {
         v_return = v_return + " --> Essayer de : " + _solutionPotentielle;
      }

      return v_return;
   }

   /**
    * Obtenir l'erreur sous la forme d'une chaîne de caractères avec uniquement les exceptions encapsulées.
    * @return La chaîne de caractères représente l'instance des exceptions encapsulées.
    */
   private String toStringFmt2 ()
   {
      String v_return = "";
      // Si au moins une erreur de génération supplémentaire
      if (_lst_ExceptionSupplem.isEmpty() == false)
      {
         // Parcourir les erreurs supplémentaires
         for (int v_i = 0; v_i < _lst_ExceptionSupplem.size(); v_i++)
         {
            // Ajouter l'erreur supplémentaire courante
            v_return = v_return + "\n" + _lst_ExceptionSupplem.get(v_i).toStringFmt1();
         }
      }
      return v_return;
   }
}
