package fr.pacman.requirement.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenRequirementEnum_Test
{

   /**
    * Test de la generation d'une interface d'entite.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genRequirementEnum () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/requirement/", "genRequirementEnum_facade", 
               "application.requirement", "Requirement_Enum");
   }
}
