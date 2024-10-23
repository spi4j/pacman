/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;
// Start of user code for imports

import fr.spi4j.persistence.entity.Entity_Itf;
import fr.test.main.StatusEnum;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type PurchaseOrder.
 * @author safr@n
 */
public interface PurchaseOrderEntity extends Entity_Itf<Long>
{
   // CONSTANTES

   
   // Start of user code fb2b5d4d79ae8fe942cc20b5d4117df8

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir cardNumber.
    * @return cardNumber.
    */
   String getCardNumber ();

   /**
    * Affecter cardNumber.
    * @param cardNumber
    *           (In)(*) cardNumber.
    */
   void setCardNumber (final String cardNumber);

   /**
    * Obtenir totalAmount.
    * @return totalAmount.
    */
   Integer getTotalAmount ();

   /**
    * Affecter totalAmount.
    * @param totalAmount
    *           (In)(*) totalAmount.
    */
   void setTotalAmount (final Integer totalAmount);

   /**
    * Obtenir shoppingCart.
    * @return shoppingCart.
    */
   Long getShoppingCart_id ();

   /**
    * Affecter shoppingCart.
    * @param shoppingCart_id
    *           (In)(*) shoppingCart.
    */
   void setShoppingCart_id (final Long shoppingCart_id);

   /**
    * Obtenir user.
    * @return user.
    */
   Long getUser_id ();

   /**
    * Affecter user.
    * @param user_id
    *           (In)(*) user.
    */
   void setUser_id (final Long user_id);


   
   // Start of user code 27cf7f8a6fef8405f7bd58dc99821662

   // End of user code

}
