package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenDaoItf_Test
{

   /**
    * Test de la generation d'une interface d'entite.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genDaoItf () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genDaoItf_facade", "application.entity", "PersonneDao_Itf");
   }
}
