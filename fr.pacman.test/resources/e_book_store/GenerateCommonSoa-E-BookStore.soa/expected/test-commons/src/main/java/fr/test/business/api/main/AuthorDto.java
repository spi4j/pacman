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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * DTO 'Author'.
 * @author safr@n
 */
public class AuthorDto implements Dto_Itf<Long>
{
   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // ATTRIBUTS

   /** . */
   private String name;

   /** La liste de type 'Book' associee a  l'instance de 'Author' courante. */
   private List<BookDto> wrote;


   
   // Start of user code 93adb82f11146b1c4cb014432cd81b31

   /** L'identifiant. */
   private Long _id;

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre du dto 'Author'.
    */
   public AuthorDto ()
   {
      super();

      
      // Start of user code 965ae8f837a266a5bbe195669e5490a4

      // End of user code
   }

   /**
    * Constructeur complet du dto 'Author'.
    * @param id
    *           (In)(*) L'identifiant de author.
    * @param name
    *           (In)(*) name.
    */
   public AuthorDto (final Long id, final String name)
   {
      super();
      resetAuthorDto(id, name);
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
    * Obtenir la liste de 'Book' : wrote.
    * @return wrote.
    */
   public List<BookDto> getWrote ()
   {
      return wrote;
   }

   /**
    * Affecter la liste de 'Book' : wrote.
    *           (In) wrote.
    */
   public void setWrote (final List<BookDto> book)
   {
      this.wrote = book;
   }

   /**
    * Recycler le dto 'Author'.
    * @param id
    *           (In)(*) L'identifiant de author.
    * @param name
    *           (In)(*) name.
    */
   public void resetAuthorDto (final Long id, final String name)
   {
      setId(id);
      setName(name);
      wrote = null;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = DtoUtil.checkMandatoryField("name", name, champsInvalides);
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

      return getClass().getSimpleName() + '[' + _id + ", " + getName()   + ']';

      // End of user code
   }

   
   // Start of user code 06a25426ca87dd3a2b023b85e8e72986

   // End of user code
}
