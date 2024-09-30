/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.test.persistence.api.main.PurchaseOrderEntity;
import fr.test.types.enums.StatusEnum;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type PurchaseOrder.
 * @author safr@n
 */
public class PurchaseOrderEntityImpl implements PurchaseOrderEntity
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes PurchaseOrderEntityImpl
   // Start of user code b6cff2a974bf202b083d7ce5a27cbf88

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long purchaseorder_id;

   /** cardNumber. */
   private String cardNumber;

   /** totalAmount. */
   private Integer totalAmount;


   /** shoppingCart. */
   private Long shoppingCart_id;

   /** user. */
   private Long user_id;

   // Attributs PurchaseOrderEntityImpl
   // Start of user code 376f10f48076fe79633a1f41a4859fd0

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'PurchaseOrder'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public PurchaseOrderEntityImpl ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return purchaseorder_id;
   }

   @Override
   public void setId (final Long id)
   {
      this.purchaseorder_id = id;
   }

   @Override
   public String getCardNumber ()
   {
      return cardNumber;
   }

   @Override
   public void setCardNumber (final String cardNumber)
   {
      this.cardNumber = cardNumber;
   }

   @Override
   public Integer getTotalAmount ()
   {
      return totalAmount;
   }

   @Override
   public void setTotalAmount (final Integer totalAmount)
   {
      this.totalAmount = totalAmount;
   }



   @Override
   public Long getShoppingCart_id ()
   {
      return shoppingCart_id;
   }

   @Override
   public void setShoppingCart_id (final Long shoppingCart_id)
   {
      this.shoppingCart_id = shoppingCart_id;
   }

   @Override
   public Long getUser_id ()
   {
      return user_id;
   }

   @Override
   public void setUser_id (final Long user_id)
   {
      this.user_id = user_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + purchaseorder_id + ", " + cardNumber + ", " + totalAmount + ", " + shoppingCart_id + ", " + user_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = EntityUtil.checkMandatoryField ("cardNumber", cardNumber, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("totalAmount", totalAmount, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("shoppingCart_id", shoppingCart_id, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("user_id", user_id, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException (this, champsInvalides.toArray (new String[champsInvalides.size()]));
      }
   }

   // Methodes PurchaseOrderEntityImpl
   // Start of user code 0c2ffc54dc081cc6e8e77ad295770c95

   // End of user code

}
