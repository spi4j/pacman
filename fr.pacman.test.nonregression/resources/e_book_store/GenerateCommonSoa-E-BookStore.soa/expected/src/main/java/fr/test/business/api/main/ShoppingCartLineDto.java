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
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'ShoppingCartLine'.
 * @author safr@n
 */
public class ShoppingCartLineDto implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** . */
   private Integer quantity;

   /** L'instance de 'Invalid' associee à  l'instance de 'ShoppingCartLine' courante. */
   private BookDto book;


   // Attributs
   // Start of user code 93adb82f11146b1c4cb014432cd81b31

   /** L'identifiant. */
   private Long _id;

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre du dto 'ShoppingCartLine'.
    */
   public ShoppingCartLineDto ()
   {
      super();

      // Constructeur
      // Start of user code 965ae8f837a266a5bbe195669e5490a4

      // End of user code
   }

   /**
    * Constructeur complet du dto 'ShoppingCartLine'.
    * @param id
    *           (In)(*) L'identifiant de shoppingCartLine.
    * @param quantity
    *           (In)(*) quantity.
    */
   public ShoppingCartLineDto (final Long id, final Integer quantity)
   {
      super();
      resetShoppingCartLineDto(id, quantity);
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
    * Obtenir book.
    * @return book.
    */
   public BookDto getBook ()
   {
      return book;
   }

   /**
    * Affecter book.
    * @param book
    *           (In)(*) book.
    */
   public void setBook (final BookDto book)
   {
      this.book = book;
   }

   /**
    * Recycler le dto 'ShoppingCartLine'.
    * @param id
    *           (In)(*) L'identifiant de shoppingCartLine.
    * @param quantity
    *           (In)(*) quantity.
    */
   public void resetShoppingCartLineDto (final Long id, final Integer quantity)
   {
      setId(id);
      setQuantity(quantity);
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = DtoUtil.checkMandatoryField("quantity", quantity, champsInvalides);
      champsInvalides = DtoUtil.checkMandatoryField("book", book, champsInvalides);
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

      return getClass().getSimpleName() + '[' + _id + ", " + getQuantity()   + ']';

      // End of user code
   }

   // Méthodes
   // Start of user code 2987547e053c107ed8efc536be3584ed

   // End of user code

}
