package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAppliEntityItf_Test
{

   /**
    * Test de la generation d'une interface d'entité.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genAppliEntityItf () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genAppliEntityItf_facade", "application.entity", "ApplicationAutoFieldsEntity_Itf");
   }
}
