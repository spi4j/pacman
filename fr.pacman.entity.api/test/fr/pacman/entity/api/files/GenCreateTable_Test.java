package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenCreateTable_Test
{

   /**
    * Test de la generation du script sql de creation de table.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genCreateTable () throws Throwable
   {

      PacmanExecutor4Test.doTestGenForSql("fr/pacman/entity/api/", "genCreateTable_facade", 
               "application.entity", "ApplicationCreateTablesH2", "create_tables_application_H2");
   }
}
