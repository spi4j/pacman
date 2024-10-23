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
import fr.test.ws.api.main.BookXto;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation XTO pour le type 'ShoppingCartLine'.
 * @author safr@n
 */
@XmlRootElement(name = "ShoppingCartLineXto", namespace = "http://main.test.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoppingCartLineXto implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   
   // Start of user code 4254d63f8960d40c586ab8ad348942ec
   @XmlElement(namespace = "", name = "id")
   
   private Long _id;
   // End of user code

   /** . */
   
   // Start of user code aa7b2bcb5d9c3a847d28ef6fca2aee31
   @XmlElement(namespace = "", name = "quantity")
   
   // End of user code
   private Integer quantity;


   /** L'instance de 'Book' associee a  l'instance de 'ShoppingCartLine' courante. */
   
   // Start of user code 1c83b0ad84d0138c268e004989dd8678
   @XmlElement(namespace = "", name = "book")
   
   // End of user code
   private BookXto book;

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
    * Obtenir quantity.
    * @return quantity.
    */
   public Integer getQuantity ()
   {
      return quantity;
   }

   /**
    * Affecter quantity.
    * @param quantity
    *           (In)(*) quantity.
    */
   public void setQuantity (final Integer quantity)
   {
      this.quantity = quantity;
   }

   /**
    * Obtenir l'instance de 'Book' associee a  l'instance de 'ShoppingCartLine' courante.
    * @return L'instance desiree.
    */
   public BookXto getBook ()
   {
      return book;
   }

   /**
    * Affecter book de shoppingCartLine.
    * @param book
    *           (In)(*) book.
    */
   public void setBook (final BookXto book)
   {
      this.book = book;
   }

   @Override
   public String toString ()
   {
      
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getName() + '['  + getId ()  + ", " + getQuantity()   + ']';

      // End of user code
   }
}
