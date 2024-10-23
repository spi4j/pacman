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
import fr.test.persistence.api.main.ShoopingCartLineEntity;
import java.util.List;

// End of user code

/**
 * Entité du type ShoopingCartLine.
 * @author safr@n
 */
public class ShoopingCartLineEntityImpl implements ShoopingCartLineEntity
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   
   // Start of user code 8323d92f6cb10e2adb8763af93a8e6a3

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long shoopingCartLine_id;

   /** quantity. */
   private Integer quantity;


   /** book. */
   private Long book_id;

   /** shoppingCart. */
   private Long shoppingCartLines_id;

   
   // Start of user code 53c8143ec601cd5cff239f9fee44c7d4

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'ShoopingCartLine'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public ShoopingCartLineEntityImpl ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return shoopingCartLine_id;
   }

   @Override
   public void setId (final Long id)
   {
      this.shoopingCartLine_id = id;
   }

   @Override
   public Integer getQuantity ()
   {
      return quantity;
   }

   @Override
   public void setQuantity (final Integer quantity)
   {
      this.quantity = quantity;
   }



   @Override
   public Long getBook_id ()
   {
      return book_id;
   }

   @Override
   public void setBook_id (final Long book_id)
   {
      this.book_id = book_id;
   }

   @Override
   public Long getShoppingCartLines_id ()
   {
      return shoppingCartLines_id;
   }

   @Override
   public void setShoppingCartLines_id (final Long shoppingCartLines_id)
   {
      this.shoppingCartLines_id = shoppingCartLines_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + shoopingCartLine_id + ", " + quantity + ", " + book_id + ", " + shoppingCartLines_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = EntityUtil.checkMandatoryField ("quantity", quantity, champsInvalides);
      champsInvalides = EntityUtil.checkMandatoryField ("book_id", book_id, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException (this, champsInvalides.toArray (new String[champsInvalides.size()]));
      }
   }

   
   // Start of user code 9add4c503635389404061757d81af927

   // End of user code

}
