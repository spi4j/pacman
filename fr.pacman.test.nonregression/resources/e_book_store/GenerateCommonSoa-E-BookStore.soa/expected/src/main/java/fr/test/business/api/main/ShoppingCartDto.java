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
 * DTO 'ShoppingCart'.
 * @author safr@n
 */
public class ShoppingCartDto implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** . */
   private StatusEnum status;

   /** La liste de type 'ShoppingCartLine' associee a  l'instance de 'ShoppingCart' courante. */
   private List<ShoppingCartLineDto> lines;


   // Attributs
   // Start of user code 93adb82f11146b1c4cb014432cd81b31

   /** L'identifiant. */
   private Long _id;

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre du dto 'ShoppingCart'.
    */
   public ShoppingCartDto ()
   {
      super();

      // Constructeur
      // Start of user code 965ae8f837a266a5bbe195669e5490a4

      // End of user code
   }

   /**
    * Constructeur complet du dto 'ShoppingCart'.
    * @param id
    *           (In)(*) L'identifiant de shoppingCart.
    * @param status
    *           (In)(*) status.
    */
   public ShoppingCartDto (final Long id, final StatusEnum status)
   {
      super();
      resetShoppingCartDto(id, status);
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
    * Obtenir status.
    * @return status.
    */
   public StatusEnum getStatus ()
   {
      return status;
   }

   /**
    * Affecter status.
    * @param status
    *           (In)(*) status.
    */
   public void setStatus (final StatusEnum status)
   {
      this.status = status;
   }

   /**
    * Obtenir la liste de 'ShoppingCartLine' : lines.
    * @return lines.
    */
   public List<ShoppingCartLineDto> getLines ()
   {
      return lines;
   }

   /**
    * Affecter la liste de 'ShoppingCartLine' : lines.
    *           (In) lines.
    */
   public void setLines (final List<ShoppingCartLineDto> shoppingCartLine)
   {
      this.lines = shoppingCartLine;
   }

   /**
    * Recycler le dto 'ShoppingCart'.
    * @param id
    *           (In)(*) L'identifiant de shoppingCart.
    * @param status
    *           (In)(*) status.
    */
   public void resetShoppingCartDto (final Long id, final StatusEnum status)
   {
      setId(id);
      setStatus(status);
      lines = null;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = DtoUtil.checkMandatoryField("status", status, champsInvalides);
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

      return getClass().getSimpleName() + '[' + _id + ", " + getStatus()   + ']';

      // End of user code
   }

   // Méthodes
   // Start of user code 2987547e053c107ed8efc536be3584ed

   // End of user code

}
