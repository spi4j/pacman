package fr.pacman.entity.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenEntityColumnEnum_Test
{

   /**
    * Test de la generation d'une Enumeration de colonne d'entite.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genEntityColumnEnumPersonne () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genEntityColumnsEnum_facade", 
               "application.entity", "PersonneColumns_Enum");
   }

   /**
    * Test de la generation d'une Enumeration de colonne d'entite.
    * 
    * @throws Throwable
    *            e
    */
   @Test
   public void genEntityColumnEnumAdresse () throws Throwable
   {

      PacmanExecutor4Test.doTestGen("fr/pacman/entity/api/", "genEntityColumnsEnum_facade", 
               "application.entity", "AdresseColumns_Enum");
   }
}
