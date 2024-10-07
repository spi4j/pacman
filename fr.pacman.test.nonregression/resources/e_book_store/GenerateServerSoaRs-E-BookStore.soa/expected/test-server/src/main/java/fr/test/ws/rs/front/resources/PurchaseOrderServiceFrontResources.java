/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports

import fr.test.ws.api.main.PurchaseOrderXto;
import fr.test.ws.rs.front.TestFrontResourcesAbs;
import jakarta.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Ressource frontale (client) pour le service : 'PurchaseOrderService'.
 * La ressource est montée en singleton (pas d'injection @Singleton) mais 
 * une utilisation "forcée" par getInstance afin d'être certain que la classe 
 * sera bien utilisée en tant que singleton dans l'application cible.
 *
 * @author safr@n.
 */
public final class PurchaseOrderServiceFrontResources extends TestFrontResourcesAbs
{

  /**
   * Pattern holder pour le Singleton (lazy-loading).
   */
  private static class Holder {

    public static final PurchaseOrderServiceFrontResources INSTANCE = new PurchaseOrderServiceFrontResources();
  }

  /**
   * Récupération de l'instance pour la classe de ressources.
   *
   * @return L'instance pour le Singleton.
   */
  public static PurchaseOrderServiceFrontResources getInstance() {

    return Holder.INSTANCE ;
  }

  /**
   * Constructeur privé pour la ressource.
   */
  private PurchaseOrderServiceFrontResources(){

    super("/ebookstore/purchaseorder");
  }


  /**
  * 
    * @param userId
    *           (In)(*) userId.
    * @return purchaseOrders.    */
  public List<PurchaseOrderXto> searchPurchaseOrder(final String userId) {

    // for searchPurchaseOrder_PurchaseOrder_purchaseOrders_String_userId
    // Start of user code 18839889d0f726dfc9d5c779306fc8f2

    return target()
      .queryParam("id", userId)
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<PurchaseOrderXto>>() {});

    // End of user code
  }

}
