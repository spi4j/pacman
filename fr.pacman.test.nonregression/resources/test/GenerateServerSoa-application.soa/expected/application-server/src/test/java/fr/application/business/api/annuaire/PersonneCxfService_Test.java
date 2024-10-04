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
 * Classe de test du service 'PersonneCxfService_Itf'.
 * @author safr@n
 */
// Annotation for class
// Start of user code Annotation for class
// End of user code
public class PersonneCxfService_Test  extends BeanTester_Abs
{ 
   /** Le 'UserPersistence' de l'application. */
   private static ApplicationUserPersistence userPersistence;

   /** Le 'PersonneCxfService_Itf' testé. */
   private static PersonneCxfService_Itf service;

   /**
    * Méthode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {  
      Parameters.setH2Database();
      userPersistence = ApplicationParamPersistence.getUserPersistence ();
      service = ApplicationUserBusiness.getPersonneCxfService ();

      // set up before class
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

      // set up
      // Start of user code set up
      // End of user code
   }

   /**
    * Test de l'opération 'findPersonneByIdFromCxf'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testFindPersonneByIdFromCxf_Personne_personne_Long_identifiant () throws Throwable
   {
      // findPersonneByIdFromCxf_Personne_personne_Long_identifiant
      // Start of user code findPersonneByIdFromCxf_Personne_personne_Long_identifiant
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

      // tear down
      // Start of user code tear down
      // End of user code
   }

   // specific service test
   // Start of user code specific service test

   // End of user code

}
