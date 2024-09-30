/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;
// Start of user code for imports

import fr.spi4j.persistence.entity.Entity_Itf;
import fr.test.types.enums.StatusEnum;
import java.util.Date;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type ShoopingCartLine.
 * @author safr@n
 */
public interface ShoopingCartLineEntity extends Entity_Itf<Long>
{
   // CONSTANTES

   // Constantes ShoopingCartLineEntity
   // Start of user code 17957a9ade0c729ed9453ada29fa5397

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir quantity.
    * @return quantity.
    */
   Integer getQuantity ();

   /**
    * Affecter quantity.
    * @param quantity
    *           (In)(*) quantity.
    */
   void setQuantity (final Integer quantity);

   /**
    * Obtenir book.
    * @return book.
    */
   Long getBook_id ();

   /**
    * Affecter book.
    * @param book_id
    *           (In)(*) book.
    */
   void setBook_id (final Long book_id);

   /**
    * Obtenir shoppingCart.
    * @return shoppingCart.
    */
   Long getShoppingCartLines_id ();

   /**
    * Affecter shoppingCart.
    * @param shoppingCartLines_id
    *           (In) shoppingCart.
    */
   void setShoppingCartLines_id (final Long shoppingCartLines_id);



   // Methodes ShoopingCartLineEntity
   // Start of user code abaf448d6da914fda5916fedbf415a5b

   // End of user code

}
