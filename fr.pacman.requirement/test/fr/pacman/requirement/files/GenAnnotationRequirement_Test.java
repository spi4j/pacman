package fr.pacman.requirement.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAnnotationRequirement_Test
{
   /**
    * Test de la generation d'une interface d'entite.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genAnnotationRequirement () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/requirement/", "genAnnotationRequirement_facade", 
               "application.requirement", "Requirement");
   }
}
