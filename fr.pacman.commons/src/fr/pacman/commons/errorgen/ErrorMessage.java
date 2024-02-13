package fr.pacman.commons.errorgen;

import java.util.ArrayList;
import java.util.List;

import fr.pacman.commons.properties.PacmanPropertiesManager;


/**
 * Représente un message d'erreur.
 */
public class ErrorMessage
{
   /** Exception de génération "chapeau" */
   private static ErrorGenerationException rootException = init();

   /**
    * Constructeur.
    * @param p_Exception
    *           L'exception.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    */
   ErrorMessage (final Throwable p_Exception, final String p_solutionPotentielle)
   {
      super();
      // Mémoriser l'erreur de génération
      rootException.addException(p_Exception, p_solutionPotentielle);
   }

   /**
    * Constructeur.
    * @param p_message
    *           Le message d'erreur.
    * @param p_solutionPotentielle
    *           Le message indiquant la (ou les) solution(s) potentielle(s).
    */
   ErrorMessage (final String p_message, final String p_solutionPotentielle)
   {
      this(new RuntimeException(p_message), p_solutionPotentielle);
   }

   /**
    * Permet de constituer l'exception encapsulant toutes les erreurs de génération de l'exécution.
    * @return La 'ErrorGenerationException' racine.
    */
   static ErrorGenerationException getRootErrorGenerationException ()
   {
      return rootException;
   }

   /**
    * Remise à zéro des exceptions encapsulées.
    * @return l'exception racine
    */
   static ErrorGenerationException init ()
   {
      // Nouvelle instance à cause de la RootCause que l'on ne peut pas réinitialiser
      rootException = new ErrorGenerationException("Il y a des erreurs de génération",
               "regarder les erreurs dans les fichiers générés et corriger les lignes avec les balises \""
                        + ErrorGenerationException.c_libErrorGeneration + "\"");
      return rootException;
   }

   @Override
   public String toString ()
   {
      String v_return = ErrorGenerationException.c_libErrorGeneration;

      // Message
      // -------

      // Si mode DEBUG
      if (PacmanPropertiesManager.isDebug() == true)
      {
         // Formater le message avec StackTrace utile (jusqu'au dernier package PacMan)
         v_return = v_return
                  + toStringStackTraceUsefull(getClass(), getStackTraceAsList(rootException.getLastErrorGenerationException()));
      }
      // Si mode normal
      else
      {
         // Formater le message sans StackTrace
         v_return = v_return + rootException.getLastErrorGenerationException().getMessage();
      }

      // Solution potentielle
      // --------------------

      // Si pas de solution potentielle
      if (rootException.getLastErrorGenerationException().get_solutionPotentielle() == null)
      {
         v_return = v_return + " ==> ???";
      }
      // Si on a une solution potentielle
      else
      {
         v_return = v_return + " ==> " + rootException.getLastErrorGenerationException().get_solutionPotentielle();
      }

      return v_return;
   }

   /**
    * Permet de convertir une Exception Java en une liste de 'String'.
    * 
    * @param p_Exception
    *           L'exception à convertir.
    * @return La liste de 'String' représentant l'erreur.
    */
   private static List<String> getStackTraceAsList (final Exception p_Exception)
   {
      final List<String> v_getStackTraceAsList = new ArrayList<String>();
      // Obtenir la pile d'appel
      final StackTraceElement[] v_tabStackTraceElement = p_Exception.getStackTrace();
      // Ajouter le message de l'erreur
      v_getStackTraceAsList.add("Exception mise en forme dans getStackTraceAsList()");
      v_getStackTraceAsList.add("--> " + p_Exception.toString());
      // Parcourir la pile d'appel
      for (int v_i = 0; v_i < v_tabStackTraceElement.length; v_i++)
      {
         // Ajouter l'élément
         v_getStackTraceAsList.add(v_tabStackTraceElement[v_i].toString());
      }

      return v_getStackTraceAsList;
   }

   /**
    * Permet de couper la pile d'appels.
    * @param p_ClassAppelante
    *           La classe appelante.
    * @param p_lstStackTrace
    *           La liste contenant les lignes de la pile d'appels.
    * @return La chaîne formatée sur 1 ligne avec la pile d'appels utiles. Par exemple : [Exception mise en forme dans getStackTraceAsList(), --> java.lang.IllegalArgumentException: Message d'erreur de génération pour les tests unitaires, fr.pacman.commons.errorgen.ErrorMessage_Test.toString_CN(ErrorMessage_Test.java:14), sun.reflect.NativeMethodAccessorImpl.invoke0(...
    */
   private static String toStringStackTraceUsefull (final Class<?> p_ClassAppelante, final List<String> p_lstStackTrace)
   {
      String v_return = "";
      String v_callCour;
      // Par exemple : "fr.pacman.commons.errorgen"
      final String v_packageActuel = p_ClassAppelante.getPackage().getName();

      int v_nbLignesPackagePacMan = 0;
      boolean v_isSortir = false;
      // Parcourir la pile d'appels
      for (int v_i = 0; ((v_isSortir == false) && (v_i < p_lstStackTrace.size())); v_i++)
      {
         v_return = v_return + "\t ";
         // Obtenir l'appel courant de la pile d'appels
         v_callCour = p_lstStackTrace.get(v_i);
         // Si on a un package PacMan (ex : "fr.pacman.commons.errorgen.ErrorMessage_Test.toString_CN(ErrorMessage_Test.java:14)")
         if (v_callCour.indexOf(v_packageActuel) >= 0)
         {
            // Une ligne de plus
            v_nbLignesPackagePacMan++;
            v_return = v_return + v_callCour;
         }
         // Si ce n'est pas un package PacMan
         else
         {
            // Si déjà au moins une ligne PacMan rencontrée
            if (v_nbLignesPackagePacMan > 0)
            {
               v_isSortir = true;
               v_return = v_return + v_callCour + "...";
            }
            else
            {
               v_return = v_return + v_callCour;
            }
         }
      }

      return v_return;
   }
}
