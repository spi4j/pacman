package fr.pacman.commons.errorgen;

/**
 * Permet de centraliser la gestion d'erreur de génération afin qu'elle remonte dans le code généré.
 * 
 * <code>
 *    String v_return;
 *    ...
 *    v_return = ErrorGeneration.addMessageFmt(UneClasse.class, "Description de l'erreur")
 * </code>
 * @author gareler
 */
public abstract class ErrorGeneration
{
   /**
    * Permet d'obtenir le message formaté sous la forme d'une 'String' et de l'écrire dans la vue "Error Logs". <br>
    * <code>
    * try {
    *    ...
    * } catch (Exception v_e) {
    *    ErrorGeneration.printMessageFmt(v_e, "Contacter la MOE")
    * }
    * </code>
    * @param p_Exception
    *           L'erreur à faire remontée.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    * @return L'erreur formatée.
    */
   public static String printMessageFmt (final Throwable p_Exception, final String p_solutionPotentielle)
   {
      final ErrorMessage v_MessageErreur = new ErrorMessage(p_Exception, p_solutionPotentielle);
      return v_MessageErreur.toString();
   }

   /**
    * Permet d'obtenir le message formaté sous la forme d'une 'String' et de l'écrire dans la vue "Error Logs". <br>
    * <code>
    * ErrorGeneration.addMessageFmt("Cas non prévu pour p_param1=" + p_param1, "soit ce cas est une erreur dans la modélisation, soit il faut enrichir les scripts pour gérer ce cas")
    * </code>
    * 
    * @param p_message
    *           Le message d'erreur.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    * @return L'erreur formatée.
    */
   public static String printMessageFmt (final String p_message, final String p_solutionPotentielle)
   {
      final ErrorMessage v_MessageErreur = new ErrorMessage(p_message, p_solutionPotentielle);
      return v_MessageErreur.toString();
   }

   /**
    * Permet de lancer une "ErrorGenerationException" des erreurs de génération survenues. (et donc un affichage dans la vue "Error Log" d'Eclipse).
    */
   public static void doIfThrowErrorGenerationException ()
   {
      // Obtenir l'erreur racine encapsulant toutes les autres erreurs de génération
      final ErrorGenerationException v_rootException = ErrorMessage.getRootErrorGenerationException();

      // Si on a une exception
      if (v_rootException.get_tab_ExceptionSupplem().isEmpty() == false)
      {
         throw v_rootException;
      }
   }

   /**
    * Remise à zéro des exceptions encapsulées.
    */
   public static void clear ()
   {
      ErrorMessage.init();
   }

}
