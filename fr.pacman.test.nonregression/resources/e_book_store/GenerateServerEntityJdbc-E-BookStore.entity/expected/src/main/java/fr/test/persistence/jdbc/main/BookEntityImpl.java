/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.test.persistence.api.main.BookEntity;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type Book.
 * @author safr@n
 */
public class BookEntityImpl implements BookEntity
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes BookEntityImpl
   // Start of user code 744f045480239333e683362a519a0eb8

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long book_id;

   /** title. */
   private String title;

   /** price. */
   private Integer price;

   /** image. */
   private String image;

   /** type. */
   private String type;


   /** author. */
   private Long authorWrote_id;

   // Attributs BookEntityImpl
   // Start of user code 52a60b6b530edb161158d45f7c4acf5b

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Book'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public BookEntityImpl ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return book_id;
   }

   @Override
   public void setId (final Long id)
   {
      this.book_id = id;
   }

   @Override
   public String getTitle ()
   {
      return title;
   }

   @Override
   public void setTitle (final String title)
   {
      this.title = title;
   }

   @Override
   public Integer getPrice ()
   {
      return price;
   }

   @Override
   public void setPrice (final Integer price)
   {
      this.price = price;
   }

   @Override
   public String getImage ()
   {
      return image;
   }

   @Override
   public void setImage (final String image)
   {
      this.image = image;
   }

   @Override
   public String getType ()
   {
      return type;
   }

   @Override
   public void setType (final String type)
   {
      this.type = type;
   }



   @Override
   public Long getAuthorWrote_id ()
   {
      return authorWrote_id;
   }

   @Override
   public void setAuthorWrote_id (final Long authorWrote_id)
   {
      this.authorWrote_id = authorWrote_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + book_id + ", " + title + ", " + price + ", " + image + ", " + type + ", " + authorWrote_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = EntityUtil.checkMandatoryField ("title", title, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("price", price, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("image", image, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException (this, champsInvalides.toArray (new String[champsInvalides.size()]));
      }
   }

   // Methodes BookEntityImpl
   // Start of user code 440ee440796a3d663d5b2b68555e2932

   // End of user code

}
