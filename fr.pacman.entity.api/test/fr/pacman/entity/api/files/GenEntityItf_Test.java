package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenEntityItf_Test
{

   /**
    * Test de la generation d'une interface d'entite.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genEntityItf () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genEntityItf_facade", 
               "application.entity", "PersonneEntity_Itf");
   }
}
