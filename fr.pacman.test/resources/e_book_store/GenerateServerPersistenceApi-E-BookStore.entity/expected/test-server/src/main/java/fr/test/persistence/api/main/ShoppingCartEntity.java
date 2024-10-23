/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;
// Start of user code for imports

import fr.spi4j.persistence.entity.Entity_Itf;
import fr.test.main.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type ShoppingCart.
 * @author safr@n
 */
public interface ShoppingCartEntity extends Entity_Itf<Long>
{
   // CONSTANTES

   
   // Start of user code 97038ac49c93afae9b0fd3cc62c432e6

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir status.
    * @return status.
    */
   StatusEnum getStatus ();

   /**
    * Affecter status.
    * @param status
    *           (In)(*) status.
    */
   void setStatus (final StatusEnum status);

   /**
    * Obtenir user.
    * @return user.
    */
   Long getUserCarts_id ();

   /**
    * Affecter user.
    * @param userCarts_id
    *           (In) user.
    */
   void setUserCarts_id (final Long userCarts_id);



   
   // Start of user code f6a38946f921d0920ceab7302b06b708

   // End of user code

}
