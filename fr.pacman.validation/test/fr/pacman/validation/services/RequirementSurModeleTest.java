package fr.pacman.validation.services;

import org.junit.jupiter.api.Test;

/**
 * Classe de test sur règles de validation du métamodèle Requirement.
 * @author MINARM
 */
public class RequirementSurModeleTest extends ModelValidationTester_Abs
{

   /**
    * Test de la génération de vérification de règles de validation.
    * @throws Throwable
    *            e
    */
   @Test
   public void genInvalidateRule_Requirement_Check002 () throws Throwable
   {
      validate("resources-test/model/Invalid.requirement", DslValidationRuleReferentiel_Enum.DslRequirement_Check001, 1);
   }

   // TODO Compléter les tests avec les autres règles

}
