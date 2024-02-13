package fr.pacman.commons.errorgen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Classes de tests unitaires sur 'ErrorMessage'.
 */
public class ErrorGeneration_Test
{

   /**
    * Initialisation des erreurs.
    */
   @BeforeAll
   public void setUp ()
   {
      ErrorGeneration.clear();
   }

   /**
    * Cas nominal de la méthode "doIfThrowErrorGenerationException()".
    */
   @Test
   public void doIfThrowErrorGenerationException_CN ()
   {
      ErrorGeneration.doIfThrowErrorGenerationException();
   }

   /**
    * Cas d'exception No1 de la méthode "doIfThrowErrorGenerationException()".
    */
   @Test
   public void doIfThrowErrorGenerationException_CE1 ()
   {
      boolean v_errorGenerated = false;
      final String v_messageFmt1 = ErrorGeneration.printMessageFmt(
               new IllegalArgumentException("Message d'erreur de génération No1 pour les tests unitaires"),
               "Pas de solution No1 :-(");
      final String v_messageFmt2 = ErrorGeneration.printMessageFmt(
               "Message d'erreur de génération No2 pour les tests unitaires", "Pas de solution No2 :-(");
      final String v_messageFmt3 = ErrorGeneration.printMessageFmt(
               new IllegalArgumentException("Message d'erreur de génération No3 pour les tests unitaires"),
               "Pas de solution No3 :-(");

      try
      {
         ErrorGeneration.doIfThrowErrorGenerationException();
      }
      catch (ErrorGenerationException v_e)
      {
         // Vérifier
         assertTrue(v_messageFmt1.equals(ErrorGenerationException.c_libErrorGeneration
                  + "Message d'erreur de génération No1 pour les tests unitaires ==> Pas de solution No1 :-(") == true,
                  "Le message est incorrect");
         assertTrue(v_messageFmt2.equals(ErrorGenerationException.c_libErrorGeneration
                  + "Message d'erreur de génération No2 pour les tests unitaires ==> Pas de solution No2 :-(") == true,
                  "Le message est incorrect");
         assertTrue(v_messageFmt3.equals(ErrorGenerationException.c_libErrorGeneration
                  + "Message d'erreur de génération No3 pour les tests unitaires ==> Pas de solution No3 :-(") == true,
                  "Le message est incorrect");
         assertTrue(v_e.getMessage().startsWith("Il y a des erreurs de génération") == true,
                  "La fin de la chaîne est incorrecte");

         v_errorGenerated = true;
      }

      // Cette assertion permet d'utiliser Junit 5 avec Java 7 (pas de lambda).
      assertTrue(v_errorGenerated == true, "Une erreur ErrorGenerationException aurait due être générée.");
   }
}
