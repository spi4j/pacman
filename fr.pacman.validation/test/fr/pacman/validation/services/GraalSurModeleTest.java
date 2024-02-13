package fr.pacman.validation.services;

import org.junit.jupiter.api.Test;

/**
 * Tests macro sur les diagrammes GRAAL.
 * @author MINARM
 */
public class GraalSurModeleTest extends ModelValidationTester_Abs
{
   /**
    * Tests de validation de règles sur le diagramme de tâches.
    * @throws Throwable
    *            Si une erreur survient.
    */
   @Test
   public void genValidateGraalDiagrammeDeTaches () throws Throwable
   {
      validate("resources-test/model/InvalidModel.graal", Dsl_Enum.DslGraalDiagrammeDeTaches, 2);

   }

   /**
    * Tests de validation de règles sur le diagramme de plan d'actions.
    * @throws Throwable
    *            Si une erreur survient.
    */
   @Test
   public void genValidateGraalDiagrammeDePlanActions () throws Throwable
   {
      validate("resources-test/model/InvalidModel.graal", Dsl_Enum.DslGraalDiagrammeDePlanActions, 29);
   }

   /**
    * Tests de validation de règles sur le diagramme de plan d'actions.
    * @throws Throwable
    *            Si une erreur survient.
    */
   @Test
   public void genValidateGraalFile () throws Throwable
   {
      validate("resources-test/model/InvalidModel.graal", Dsl_Enum.DslGraalDiagrammeDePlanActions, "resources-test/files-expected/InvalidModelGraal");
   }
}
