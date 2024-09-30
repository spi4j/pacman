/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports

import fr.test.ws.api.main.PurchaseOrderXto;
import fr.test.ws.rs.front.delegates.PurchaseOrderServiceDelegate;
import fr.test.ws.rs.front.exceptions.TestFrontRsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test JUnit pour la ressource frontale (client) : 'PurchaseOrderFrontResourcesTest'.
 *
 * @author safr@n
 */
public class PurchaseOrderFrontResourcesTest {


	/**
	 * Enregistrement de la classe de test auprès du Helper.
	 * Récupération du token si le service est sécurisé.
	 */
	@BeforeAll
	public static void init(){

		// for init
		// Start of user code 23427ad5c2cfd5b5c1e5febbaf137394

		TestFrontResourcesTestHelper.registerClass(
			PurchaseOrderFrontResourcesTest.class);


		// End of user code
	}

   /**
   * 
     * @param userId
     *           (In)(*) userId.
     * @return purchaseOrders.    */
	@Test
	@SuppressWarnings("unused")
	public void testSearchPurchaseOrder() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testSearchPurchaseOrder");
	
			// for searchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId
			// Start of user code 18839889d0f726dfc9d5c779306fc8f2

			final String userId = "S";

			// End of user code

			List<PurchaseOrderXto> entity = PurchaseOrderServiceDelegate.searchPurchaseOrder(userId);

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response searchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId
			// Start of user code 8a7c85717fec510b6e7a9421a6f7376f
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

}

