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
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation XTO pour le type 'User'.
 * @author safr@n
 */
@XmlRootElement(name = "UserXto", namespace = "http://main.test.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXto implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   // for annotation_id
   // Start of user code 4254d63f8960d40c586ab8ad348942ec
   @XmlElement(namespace = "", name = "id")
   
   private Long _id;
   // End of user code

   /** . */
   // for annotation_email
   // Start of user code 59a37cbb0d7fb31d82d19c59cf21e9f3
   @XmlElement(namespace = "", name = "email")
   
   // End of user code
   private String email;

   /** . */
   // for annotation_password
   // Start of user code 9224b1daf0e9edb85fe1ed775cc29917
   @XmlElement(namespace = "", name = "password")
   
   // End of user code
   private String password;

   /** . */
   // for annotation_name
   // Start of user code 16a98109aebfaf1b73aacd8550bb5191
   @XmlElement(namespace = "", name = "name")
   
   // End of user code
   private String name;

   /** La liste de type 'ShoppingCart' associee a  l'instance de 'User' courante. */
   // for annotation_carts
   // Start of user code 1742ff7df7be48c977a309478d342092
   @XmlElement(namespace = "", name = "carts")
   
   // End of user code
   private List<ShoppingCartXto> carts;

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
    * Obtenir email.
    * @return email.
    */
   public String getEmail ()
   {
      return email;
   }

   /**
    * Affecter email.
    * @param email
    *           (In)(*) email.
    */
   public void setEmail (final String email)
   {
      this.email = email;
   }

   /**
    * Obtenir password.
    * @return password.
    */
   public String getPassword ()
   {
      return password;
   }

   /**
    * Affecter password.
    * @param password
    *           (In)(*) password.
    */
   public void setPassword (final String password)
   {
      this.password = password;
   }

   /**
    * Obtenir name.
    * @return name.
    */
   public String getName ()
   {
      return name;
   }

   /**
    * Affecter name.
    * @param name
    *           (In)(*) name.
    */
   public void setName (final String name)
   {
      this.name = name;
   }

   /**
    * Obtenir la liste de type 'ShoppingCart' associee à  l'instance de 'User' courante.
    * @return La liste desiree.
    */
   public List<ShoppingCartXto> getCarts ()
   {
      return carts;
   }

   /**
    * Affecter les ShoppingCart de user.
    *           (In) carts.
    */
   public void setCarts (final List<ShoppingCartXto> shoppingCart)
   {
      this.carts = shoppingCart;
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getName() + '['  + getId ()  + ", " + getEmail()   + ", " + getPassword()   + ", " + getName()   + ']';

      // End of user code
   }
}
