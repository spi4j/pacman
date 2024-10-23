/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.test.main.StatusEnum;
import fr.test.persistence.api.main.ShoppingCartEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * Entité du type ShoppingCart.
 * @author safr@n
 */
public class ShoppingCartEntityImpl implements ShoppingCartEntity
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   
   // Start of user code 94f9daa441332b338919dc23d882d6bc

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long shoppingCart_id;

   /** status. */
   private StatusEnum status;


   /** user. */
   private Long userCarts_id;

   
   // Start of user code 6ef11e02f81c9ba16b0d7bfb5939dcd0

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'ShoppingCart'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public ShoppingCartEntityImpl ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return shoppingCart_id;
   }

   @Override
   public void setId (final Long id)
   {
      this.shoppingCart_id = id;
   }

   @Override
   public StatusEnum getStatus ()
   {
      return status;
   }

   @Override
   public void setStatus (final StatusEnum status)
   {
      this.status = status;
   }



   @Override
   public Long getUserCarts_id ()
   {
      return userCarts_id;
   }

   @Override
   public void setUserCarts_id (final Long userCarts_id)
   {
      this.userCarts_id = userCarts_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + shoppingCart_id + ", " + status + ", " + userCarts_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = EntityUtil.checkMandatoryField ("status", status, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException (this, champsInvalides.toArray (new String[champsInvalides.size()]));
      }
   }

   
   // Start of user code b26b20fd08a49d988ea1050dcf388a2b

   // End of user code

}
