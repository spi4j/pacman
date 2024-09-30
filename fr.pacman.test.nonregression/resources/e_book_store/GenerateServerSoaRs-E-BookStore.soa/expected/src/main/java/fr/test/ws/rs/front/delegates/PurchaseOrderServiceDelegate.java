/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.delegates;
// Start of user code for imports

import fr.test.ws.api.main.PurchaseOrderXto;
import fr.test.ws.rs.front.exceptions.TestFrontRsException;
import fr.test.ws.rs.front.resources.PurchaseOrderServiceFrontResources;
import jakarta.ws.rs.ProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
* Facade pour la ressource : PurchaseOrderService.
*
* @author safr@n.
*/
public final class PurchaseOrderServiceDelegate {


  	/**
  	* 
    * @param userId
    *           (In)(*) userId.
    * @return purchaseOrders.    */
  	public static List<PurchaseOrderXto> searchPurchaseOrder(final String userId) {
		
		try {
			return PurchaseOrderServiceFrontResources.getInstance()
                .searchPurchaseOrder(userId);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
}
