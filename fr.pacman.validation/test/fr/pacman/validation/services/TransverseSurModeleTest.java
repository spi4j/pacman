package fr.pacman.validation.services;

import org.junit.jupiter.api.Test;

/**
 * Classe de test sur règles de validation transverses.
 * @author MINARM
 */
public class TransverseSurModeleTest extends ModelValidationTester_Abs
{

   /**
    * Test de la génération de vérification de règles de validation.
    * @throws Throwable
    *            e
    */
   @Test
   public void genInvalidateRule_Metadata_Check001 () throws Throwable
   {
      validate("resources-test/model/InvalidModel.ois", DslValidationRuleReferentiel_Enum.Metadata_Check001, 5);
   }

}
