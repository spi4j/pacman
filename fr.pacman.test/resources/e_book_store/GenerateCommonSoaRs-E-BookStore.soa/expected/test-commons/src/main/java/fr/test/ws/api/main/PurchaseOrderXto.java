/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.api.main;
// Start of user code for imports

import fr.spi4j.ws.rs.RsXto_Itf;
import fr.spi4j.ws.xto.Xto_Itf;
import fr.test.Binary;
import fr.test.ws.api.main.ShoppingCartXto;
import fr.test.ws.api.main.UserXto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation XTO pour le type 'PurchaseOrder'.
 * @author safr@n
 */
@XmlRootElement(name = "PurchaseOrderXto", namespace = "http://main.test.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrderXto implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   
   // Start of user code 4254d63f8960d40c586ab8ad348942ec
   @XmlElement(namespace = "", name = "id")
   
   private Long _id;
   // End of user code

   /** . */
   
   // Start of user code 3a68ba0d0267f117ec55a51c826d68a2
   @XmlElement(namespace = "", name = "cardNumber")
   
   // End of user code
   private String cardNumber;

   /** . */
   
   // Start of user code 47c657b990d01260fc3537a21b8bd246
   @XmlElement(namespace = "", name = "totalAmount")
   
   // End of user code
   private Integer totalAmount;


   /** L'instance de 'ShoppingCart' associee a  l'instance de 'PurchaseOrder' courante. */
   
   // Start of user code 4db0172483f3fd927dc657610a29bd6c
   @XmlElement(namespace = "", name = "shoppingCart")
   
   // End of user code
   private ShoppingCartXto shoppingCart;


   /** L'instance de 'User' associee a  l'instance de 'PurchaseOrder' courante. */
   
   // Start of user code 1977d459394e7358e92050aac4f87508
   @XmlElement(namespace = "", name = "user")
   
   // End of user code
   private UserXto user;

   // METHODES

   @Override
   public Long getId ()
   {
      return _id;
   }

   @Override
   public void setId (final Long id)
   {
      this._id = id;
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
    * Obtenir l'instance de 'ShoppingCart' associee a  l'instance de 'PurchaseOrder' courante.
    * @return L'instance desiree.
    */
   public ShoppingCartXto getShoppingCart ()
   {
      return shoppingCart;
   }

   /**
    * Affecter shoppingCart de purchaseOrder.
    * @param shoppingCart
    *           (In)(*) shoppingCart.
    */
   public void setShoppingCart (final ShoppingCartXto shoppingCart)
   {
      this.shoppingCart = shoppingCart;
   }

   /**
    * Obtenir l'instance de 'User' associee a  l'instance de 'PurchaseOrder' courante.
    * @return L'instance desiree.
    */
   public UserXto getUser ()
   {
      return user;
   }

   /**
    * Affecter user de purchaseOrder.
    * @param user
    *           (In)(*) user.
    */
   public void setUser (final UserXto user)
   {
      this.user = user;
   }

   @Override
   public String toString ()
   {
      
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getName() + '['  + getId ()  + ", " + getCardNumber()   + ", " + getTotalAmount()   + ']';

      // End of user code
   }
}
