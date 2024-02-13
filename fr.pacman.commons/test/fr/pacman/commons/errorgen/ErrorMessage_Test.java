package fr.pacman.commons.errorgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.pacman.commons.properties.PacmanPropertiesManager;

/**
 * Classes de tests unitaires sur 'ErrorMessage'.
 */
public class ErrorMessage_Test
{

   /**
    * Cas nominal de la méthode "toString()" en mode PacMan Debug.
    */
   @Test
   public void toString_CN1 ()
   {
      // Forcer le mode DEBUG de PacMan pour les tests unitaires
      PacmanPropertiesManager.setDebug(true);
      final ErrorMessage v_ErrorMessage = new ErrorMessage(
               new IllegalArgumentException("Message d'erreur de génération pour les tests unitaires"),
               "Pas de solution :-)");
      // Obtenir le message sur une ligne
      final String v_msgSurUneLigne = v_ErrorMessage.toString();
      // Vérifier
      final String v_messageAttendu = "[ERROR_GEN] -->   Exception mise en forme dans getStackTraceAsList()  --> [ERROR_GEN] --> Message d'erreur de génération pour les tests unitaires [Exception encapsulée = java.lang.IllegalArgumentException : Message d'erreur de génération pour les tests unitaires]  --> Essayer de : Pas de solution :-)  fr.pacman.commons.errorgen.ErrorGenerationException.addException(ErrorGenerationException.java:72)  fr.pacman.commons.errorgen.ErrorMessage.<init>(ErrorMessage.java:27)  fr.pacman.commons.errorgen.ErrorMessage_Test.toString_CN1(ErrorMessage_Test.java:23)  sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)... ==> Pas de solution :-)";
      assertEquals(v_messageAttendu, v_msgSurUneLigne.replace('\t', ' '));
   }

   /**
    * Cas nominal de la méthode "toString()" en mode PacMan normal (non Debug).
    */
   @Test
   public void toString_CN2 ()
   {
      // Forcer le mode NON DEBUG de PacMan pour les tests unitaires
      PacmanPropertiesManager.setDebug(false);
      final ErrorMessage v_ErrorMessage = new ErrorMessage(
               new IllegalArgumentException("Message d'erreur de génération pour les tests unitaires"),
               "Pas de solution :-)");
      // Obtenir le message sur une ligne
      final String v_msgSurUneLigne = v_ErrorMessage.toString();
      // Vérifier
      assertTrue(v_msgSurUneLigne.startsWith(
               "[ERROR_GEN] --> Message d'erreur de génération pour les tests unitaires ==> Pas de solution :-)") == true,
               "La fin de la chaîne est incorrecte");
   }
}
