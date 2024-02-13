package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenInitData_Test
{

   /**
    * Test de la generation des scripts sql d'initialisation de donnees.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genInitData () throws Throwable
   {

      PacmanExecutor4Test.doTestGenForSql("fr/pacman/entity/api/", "genInitData_facade", "application.entity", 
               "ApplicationInitData", "init_data_application");
   }
}
