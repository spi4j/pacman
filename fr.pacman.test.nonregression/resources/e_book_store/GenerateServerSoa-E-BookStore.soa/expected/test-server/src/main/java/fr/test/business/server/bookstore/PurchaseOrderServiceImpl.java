/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.server.bookstore;
// Start of user code for imports

import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import fr.test.business.api.bookstore.PurchaseOrderService;
import fr.test.business.server.bookstore.PurchaseOrderServiceRequirements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */
// annotations service
// Start of user code 674e50e0c74c5b2ab261ee2db581af68
// End of user code
public class PurchaseOrderServiceImpl implements PurchaseOrderService
{

   // Rappel : les services sont sans état.
   // attributs
   // Start of user code 30c346a26df5ef7ff6f1d2298b85c1a5

   // End of user code

   @SuppressWarnings("all")
   private final PurchaseOrderServiceRequirements requirements = new PurchaseOrderServiceRequirements (); // NOPMD

   /**
    * 
    * @param userId
    *           (In)(*) userId.

    * @return purchaseOrders.
    */
   @Override
   public List<PurchaseOrderDto> searchPurchaseOrder (final String userId)
   {

      // Appel des exigences en provenance de la modélisation

      // searchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId
      // Start of user code ea25b02247c5f73f7bfd57b8a856eb30
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	

   // PurchaseOrderService
   // Start of user code 71a10a013cbbbc1bbd7d418b42a64069

   // End of user code
}
