/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.bookstore;
// Start of user code for imports

import fr.spi4j.business.ApplicationService_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import fr.test.business.api.main.PurchaseOrderDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Définit le contrat de services spécifiques.
 * @author safr@n
 */
// Annotations Service
// Start of user code a9c64d756071d5bde340c5f2f54001e7
// End of user code
public interface PurchaseOrderService extends ApplicationService_Itf
{


   /**
    * 
    * @param userId
    *           (In)(*) userId.
    * @return purchaseOrders.
	*/
	// Annotations searchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId
	// Start of user code 330af4eb709e07fa523a2bc768b5cbfb

	// End of user code
	
    List<PurchaseOrderDto> searchPurchaseOrder (final String userId); 

	

    // Méthodes PurchaseOrderService
    // Start of user code 35f3d2b4f8ea968835a2743ef2c56318

    // End of user code
}
