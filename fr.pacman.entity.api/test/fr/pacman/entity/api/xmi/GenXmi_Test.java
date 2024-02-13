package fr.pacman.entity.api.xmi;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenXmi_Test
{

   /**
    * Test de la génération du fichier xmi pour PowerAmc.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genXmi () throws Throwable
   {

      PacmanExecutor4Test.doTestGenForXml("fr/pacman/entity/api/", "genXmi_facade", 
               "application.entity", "Annuaire", "annuaire");
   }
}
