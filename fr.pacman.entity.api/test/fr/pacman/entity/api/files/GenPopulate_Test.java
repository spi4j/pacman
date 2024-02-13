package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenPopulate_Test
{

   /**
    * Test de la generation code de montee en charge.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genPopulate () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genPopulate_facade", 
               "application.entity", "AnnuairePopulate");
   }
}
