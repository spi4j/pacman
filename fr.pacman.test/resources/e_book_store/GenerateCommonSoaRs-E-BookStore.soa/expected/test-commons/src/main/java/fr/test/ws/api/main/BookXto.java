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
import fr.test.ws.api.main.AuthorXto;
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
 * Implémentation XTO pour le type 'Book'.
 * @author safr@n
 */
@XmlRootElement(name = "BookXto", namespace = "http://main.test.fr/xto")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookXto implements Xto_Itf<Long>, RsXto_Itf
{

   private static final long serialVersionUID = 1L;

   // ATTRIBUTS

   /** L'identifiant. */
   
   // Start of user code 4254d63f8960d40c586ab8ad348942ec
   @XmlElement(namespace = "", name = "id")
   
   private Long _id;
   // End of user code

   /** . */
   
   // Start of user code e9be8f5f7e9098ecf8eda1a195b88f50
   @XmlElement(namespace = "", name = "title")
   
   // End of user code
   private String title;

   /** . */
   
   // Start of user code 503176d7862c02696a85b7b006e3d603
   @XmlElement(namespace = "", name = "price")
   
   // End of user code
   private Integer price;

   /** . */
   
   // Start of user code 7338688242718785ccc3f7091fe717b7
   @XmlElement(namespace = "", name = "image")
   
   // End of user code
   private String image;

   /** . */
   
   // Start of user code 6ac7042eeb1e81a55ccc983f3f578614
   @XmlElement(namespace = "", name = "type")
   
   // End of user code
   private String type;

   /** La liste de type 'Author' associee a  l'instance de 'Book' courante. */
   
   // Start of user code 972f6c9bfcc631c3f50904fa0ae19a74
   @XmlElement(namespace = "", name = "writtenBy")
   
   // End of user code
   private List<AuthorXto> writtenBy;

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
    * Obtenir title.
    * @return title.
    */
   public String getTitle ()
   {
      return title;
   }

   /**
    * Affecter title.
    * @param title
    *           (In)(*) title.
    */
   public void setTitle (final String title)
   {
      this.title = title;
   }

   /**
    * Obtenir price.
    * @return price.
    */
   public Integer getPrice ()
   {
      return price;
   }

   /**
    * Affecter price.
    * @param price
    *           (In)(*) price.
    */
   public void setPrice (final Integer price)
   {
      this.price = price;
   }

   /**
    * Obtenir image.
    * @return image.
    */
   public String getImage ()
   {
      return image;
   }

   /**
    * Affecter image.
    * @param image
    *           (In) image.
    */
   public void setImage (final String image)
   {
      this.image = image;
   }

   /**
    * Obtenir type.
    * @return type.
    */
   public String getType ()
   {
      return type;
   }

   /**
    * Affecter type.
    * @param type
    *           (In) type.
    */
   public void setType (final String type)
   {
      this.type = type;
   }

   /**
    * Obtenir la liste de type 'Author' associee à  l'instance de 'Book' courante.
    * @return La liste desiree.
    */
   public List<AuthorXto> getWrittenBy ()
   {
      return writtenBy;
   }

   /**
    * Affecter les Author de book.
    *           (In) writtenBy.
    */
   public void setWrittenBy (final List<AuthorXto> author)
   {
      this.writtenBy = author;
   }

   @Override
   public String toString ()
   {
      
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getName() + '['  + getId ()  + ", " + getTitle()   + ", " + getPrice()   + ", " + getImage()   + ", " + getType()   + ']';

      // End of user code
   }
}
