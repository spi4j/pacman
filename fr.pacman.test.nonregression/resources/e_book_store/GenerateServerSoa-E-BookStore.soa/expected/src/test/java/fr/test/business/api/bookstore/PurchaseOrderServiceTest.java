/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.bookstore;
// Start of user code for imports

import fr.spi4j.Parameters;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.tua.BeanTester_Abs;
import fr.test.business.TestUserBusiness;
import fr.test.business.api.main.PurchaseOrderAttributesEnum;
import fr.test.business.api.main.PurchaseOrderDto;
import fr.test.persistence.TestUserPersistence;
import fr.test.types.enums.StatusEnum;
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
 * Classe de test du service 'PurchaseOrderService'.
 * @author safr@n
 */
// Annotation for class
// Start of user code 8d11490eb7e3bd7fbe31724d2cea61c5
// End of user code
public class PurchaseOrderServiceTest  extends BeanTester_Abs
{ 

   /** Le 'PurchaseOrderService' testé. */
   private static PurchaseOrderService service;

   /**
    * Méthode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {  
      service = TestUserBusiness.getPurchaseOrderService ();

      // set up before class
      // Start of user code ac28938f801815a863d275d7089501cb
      // End of user code
   }

   /**
    * Méthode d'initialisation de tests.
    */
   @BeforeEach
   public void setUp ()
   {

      // set up
      // Start of user code d5158c215f75d40b3dc3a9efd519b60a
      // End of user code
   }

   /**
    * Test de l'opération 'searchPurchaseOrder'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testSearchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId () throws Throwable
   {
      // searchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId
      // Start of user code ea25b02247c5f73f7bfd57b8a856eb30
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

      // tear down
      // Start of user code b778e9a7588fa49250428a599cf59f97
      // End of user code
   }

   // specific service test
   // Start of user code 83c9cfe79a629107e128f8bc9ca9ba12

   // End of user code

}
