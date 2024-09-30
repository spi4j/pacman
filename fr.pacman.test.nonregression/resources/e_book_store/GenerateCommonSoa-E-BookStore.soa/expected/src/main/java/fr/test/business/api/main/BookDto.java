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
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'Book'.
 * @author safr@n
 */
public class BookDto implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** . */
   private String title;

   /** . */
   private Integer price;

   /** . */
   private String image;

   /** . */
   private String type;

   /** La liste de type 'Author' associee a  l'instance de 'Book' courante. */
   private List<AuthorDto> writtenBy;


   // Attributs
   // Start of user code 93adb82f11146b1c4cb014432cd81b31

   /** L'identifiant. */
   private Long _id;

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Book'.
    */
   public BookDto ()
   {
      super();

      // Constructeur
      // Start of user code 965ae8f837a266a5bbe195669e5490a4

      // End of user code
   }

   /**
    * Constructeur complet du dto 'Book'.
    * @param id
    *           (In)(*) L'identifiant de book.
    * @param title
    *           (In)(*) title.
    * @param price
    *           (In)(*) price.
    * @param image
    *           (In) image.
    * @param type
    *           (In) type.
    */
   public BookDto (final Long id, final String title, final Integer price, final String image, final String type)
   {
      super();
      resetBookDto(id, title, price, image, type);
   }

   @Override
   public Long getId ()
   {
      // getId
      // Start of user code 7db1b67f9311bee084851e988f91c89d

      return _id;

      // End of user code
   }

   @Override
   public void setId (final Long id)
   {
      // setId
      // Start of user code 0a8d0673bf66104005f2022f591e0538

      this._id = id;

      // End of user code
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
    * Obtenir la liste de 'Author' : writtenBy.
    * @return writtenBy.
    */
   public List<AuthorDto> getWrittenBy ()
   {
      return writtenBy;
   }

   /**
    * Affecter la liste de 'Author' : writtenBy.
    *           (In) writtenBy.
    */
   public void setWrittenBy (final List<AuthorDto> author)
   {
      this.writtenBy = author;
   }

   /**
    * Recycler le dto 'Book'.
    * @param id
    *           (In)(*) L'identifiant de book.
    * @param title
    *           (In)(*) title.
    * @param price
    *           (In)(*) price.
    * @param image
    *           (In) image.
    * @param type
    *           (In) type.
    */
   public void resetBookDto (final Long id, final String title, final Integer price, final String image, final String type)
   {
      setId(id);
      setTitle(title);
      setPrice(price);
      setImage(image);
      setType(type);
      writtenBy = null;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = DtoUtil.checkMandatoryField("title", title, champsInvalides);
      champsInvalides = DtoUtil.checkMandatoryField("price", price, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException(this, champsInvalides.toArray(new String[champsInvalides.size()]));
      }
   }

   @Override
   public String toString ()
   {
      // toString
      // Start of user code c424b4614133c28bf2c3dee0d0b54f6d

      return getClass().getSimpleName() + '[' + _id + ", " + getTitle() + ", " + getPrice() + ", " + getImage() + ", " + getType()   + ']';

      // End of user code
   }

   // Méthodes
   // Start of user code 2987547e053c107ed8efc536be3584ed

   // End of user code

}
