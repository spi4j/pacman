package fr.pacman.commons.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenApplicationAppConfig_Test
{

   /**
    * Test de la generation pour l'injection hk2.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genAppliConfig () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/commons/soa/", "genApplicationAppConfig_facade",
    		  "application.soa", "ApplicationAppConfig");
   }
}
