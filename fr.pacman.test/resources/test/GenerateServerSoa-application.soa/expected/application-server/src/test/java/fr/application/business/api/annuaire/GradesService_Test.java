/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.business.ApplicationUserBusiness;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.spi4j.Parameters;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test du service 'GradesService_Itf'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class GradesService_Test  extends BeanTester_Abs
{ 
   /** Le 'UserPersistence' de l'application. */
   private static ApplicationUserPersistence userPersistence;

   /** Le 'GradesService_Itf' testé. */
   private static GradesService_Itf service;

   /**
    * Méthode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {  
      Parameters.setH2Database();
      userPersistence = ApplicationParamPersistence.getUserPersistence ();
      service = ApplicationUserBusiness.getGradesService ();

      
      // Start of user code set up before class
      // End of user code
   }

   /**
    * Méthode d'initialisation de tests.
    */
   @BeforeEach
   public void setUp ()
   {
	  userPersistence.begin ();

      
      // Start of user code set up
      // End of user code
   }

   /**
    * Test de l'opération 'findAllGrades'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testFindAllGrades_Grade_grades () throws Throwable
   {
      
      // Start of user code findAllGrades_Grade_grades
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'findAllPagedGrades'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testFindAllPagedGrades_Grade_grades () throws Throwable
   {
      
      // Start of user code findAllPagedGrades_Grade_grades
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Méthode de fin de test : rollback.
    */
   @AfterEach
   public void tearDown ()
   {
      userPersistence.rollback ();

      
      // Start of user code tear down
      // End of user code
   }

   
   // Start of user code specific service test

   // End of user code

}
