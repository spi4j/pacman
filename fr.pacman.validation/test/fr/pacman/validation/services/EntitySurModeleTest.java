package fr.pacman.validation.services;

import org.junit.jupiter.api.Test;

/**
 * Classe de test sur règles de validation du métamodèle Requirement.
 * @author MINARM
 */
public class EntitySurModeleTest extends ModelValidationTester_Abs
{

   /**
    * Test de la génération de vérification de règles de validation.
    * @throws Throwable
    *            e
    */
   @Test
   public void genValidateEntity () throws Throwable
   {
      validate("resources-test/model/ValidModel.ois", Dsl_Enum.DslEntity, 0);
   }

   /**
    * Test de la génération de vérification de règles de validation.
    * @throws Throwable
    *            e
    */
   @Test
   public void genInvalidateEntity () throws Throwable
   {
      // Devrait être la somme de toutes les règles pour ce DSL, définies
      // ci-dessous
      validate("resources-test/model/InvalidModel.ois", Dsl_Enum.DslEntity, 17);
   }

   /**
    * Test de la génération de vérification de règles de validation.
    * @throws Throwable
    *            e
    */
   @Test
   public void genInvalidateRule_Entity_Check001 () throws Throwable
   {
      validate("resources-test/model/InvalidModel.ois", DslValidationRuleReferentiel_Enum.DslEntity_Check001, 2);
   }

   /**
    * Test de la génération de vérification de règles de validation.
    * @throws Throwable
    *            e
    */
   @Test
   public void genInvalidateRule_Entity_Check002 () throws Throwable
   {
      validate("resources-test/model/InvalidModel.ois", DslValidationRuleReferentiel_Enum.DslEntity_Check002, 1);
   }

   // TODO Compléter les tests avec les autres règles

}
