package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenDaoTest_Test
{

   /**
    * Test de la generation d'une classe de test de Dao
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genDaoTest () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genDaoTest_facade", "application.entity", "PersonneDao_Test");
   }
}
