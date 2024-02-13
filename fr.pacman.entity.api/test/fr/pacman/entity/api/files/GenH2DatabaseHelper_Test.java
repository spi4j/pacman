package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenH2DatabaseHelper_Test
{

   /**
    * Test de la generation de la classe d'aide DB
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genH2DatabaseHelper () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genH2DatabaseHelper_facade", 
               "application.entity", "ApplicationH2DatabaseHelper");
   }
}
