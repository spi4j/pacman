/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.main;
// Start of user code for imports

import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.business.dto.Dto_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import fr.test.business.TestUserBusiness;
import fr.test.main.StatusEnum;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'PurchaseOrder'.
 * @author safr@n
 */
public class PurchaseOrderDto implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** . */
   private String cardNumber;

   /** . */
   private Integer totalAmount;

   /** L'instance de 'ShoppingCart' associee à  l'instance de 'PurchaseOrder' courante. */
   private ShoppingCartDto shoppingCart;

   /** L'instance de 'User' associee à  l'instance de 'PurchaseOrder' courante. */
   private UserDto user;


   
   // Start of user code 93adb82f11146b1c4cb014432cd81b31

   /** L'identifiant. */
   private Long _id;

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre du dto 'PurchaseOrder'.
    */
   public PurchaseOrderDto ()
   {
      super();

      
      // Start of user code 965ae8f837a266a5bbe195669e5490a4

      // End of user code
   }

   /**
    * Constructeur complet du dto 'PurchaseOrder'.
    * @param id
    *           (In)(*) L'identifiant de purchaseOrder.
    * @param cardNumber
    *           (In)(*) cardNumber.
    * @param totalAmount
    *           (In)(*) totalAmount.
    */
   public PurchaseOrderDto (final Long id, final String cardNumber, final Integer totalAmount)
   {
      super();
      resetPurchaseOrderDto(id, cardNumber, totalAmount);
   }

   @Override
   public Long getId ()
   {
      
      // Start of user code 7db1b67f9311bee084851e988f91c89d

      return _id;

      // End of user code
   }

   @Override
   public void setId (final Long id)
   {
      
      // Start of user code 0a8d0673bf66104005f2022f591e0538

      this._id = id;

      // End of user code
   }

   /**
    * Obtenir cardNumber.
    * @return cardNumber.
    */
   public String getCardNumber ()
   {
      return cardNumber;
   }

   /**
    * Affecter cardNumber.
    * @param cardNumber
    *           (In)(*) cardNumber.
    */
   public void setCardNumber (final String cardNumber)
   {
      this.cardNumber = cardNumber;
   }

   /**
    * Obtenir totalAmount.
    * @return totalAmount.
    */
   public Integer getTotalAmount ()
   {
      return totalAmount;
   }

   /**
    * Affecter totalAmount.
    * @param totalAmount
    *           (In)(*) totalAmount.
    */
   public void setTotalAmount (final Integer totalAmount)
   {
      this.totalAmount = totalAmount;
   }

   /**
    * Obtenir shoppingCart.
    * @return shoppingCart.
    */
   public ShoppingCartDto getShoppingCart ()
   {
      return shoppingCart;
   }

   /**
    * Affecter shoppingCart.
    * @param shoppingCart
    *           (In)(*) shoppingCart.
    */
   public void setShoppingCart (final ShoppingCartDto shoppingCart)
   {
      this.shoppingCart = shoppingCart;
   }

   /**
    * Obtenir user.
    * @return user.
    */
   public UserDto getUser ()
   {
      return user;
   }

   /**
    * Affecter user.
    * @param user
    *           (In)(*) user.
    */
   public void setUser (final UserDto user)
   {
      this.user = user;
   }

   /**
    * Recycler le dto 'PurchaseOrder'.
    * @param id
    *           (In)(*) L'identifiant de purchaseOrder.
    * @param cardNumber
    *           (In)(*) cardNumber.
    * @param totalAmount
    *           (In)(*) totalAmount.
    */
   public void resetPurchaseOrderDto (final Long id, final String cardNumber, final Integer totalAmount)
   {
      setId(id);
      setCardNumber(cardNumber);
      setTotalAmount(totalAmount);
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = DtoUtil.checkMandatoryField("cardNumber", cardNumber, champsInvalides);
      champsInvalides = DtoUtil.checkMandatoryField("totalAmount", totalAmount, champsInvalides);
      champsInvalides = DtoUtil.checkMandatoryField("shoppingCart", shoppingCart, champsInvalides);
      champsInvalides = DtoUtil.checkMandatoryField("user", user, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException(this, champsInvalides.toArray(new String[champsInvalides.size()]));
      }
   }

   @Override
   public String toString ()
   {
      
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getSimpleName() + '[' + _id + ", " + getCardNumber() + ", " + getTotalAmount()   + ']';

      // End of user code
   }

   
   // Start of user code 06a25426ca87dd3a2b023b85e8e72986

   // End of user code
}
