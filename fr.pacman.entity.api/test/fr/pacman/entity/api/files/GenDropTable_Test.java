package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenDropTable_Test
{

   /**
    * Test de la generation du drop table sql.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genDropTable () throws Throwable
   {

      PacmanExecutor4Test.doTestGenForSql("fr/pacman/entity/api/", "genDropTable_facade", "application.entity", 
               "ApplicationDropTablesH2", "drop_tables_application_H2");
   }
}
