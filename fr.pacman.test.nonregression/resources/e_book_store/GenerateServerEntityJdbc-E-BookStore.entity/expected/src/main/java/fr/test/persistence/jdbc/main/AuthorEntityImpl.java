/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.test.persistence.api.main.AuthorEntity;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Entité du type Author.
 * @author safr@n
 */
public class AuthorEntityImpl implements AuthorEntity
{
   // CONSTANTES

   /**
    * SerialUid.
    */
   private static final long serialVersionUID = -1;

   // Constantes AuthorEntityImpl
   // Start of user code 8b9b7863ab4315e9e7da51d57aacb62f

   // End of user code

   // ATTRIBUTS

   /** Id. */
   private Long author_id;

   /** name. */
   private String name;


   /** book. */
   private Long bookWrittenBy_id;

   // Attributs AuthorEntityImpl
   // Start of user code d4bd424be6100467faf9701e37d333e0

   // End of user code

   // METHODES

   /**
    * Constructeur sans paramètre de l'entité 'Author'. <br>
    * Attention à ne pas appeler ce constructeur directement, mais à bien passer par UserPersistence.
    */
   public AuthorEntityImpl ()
   {
      super ();
   }

   @Override
   public Long getId ()
   {
      return author_id;
   }

   @Override
   public void setId (final Long id)
   {
      this.author_id = id;
   }

   @Override
   public String getName ()
   {
      return name;
   }

   @Override
   public void setName (final String name)
   {
      this.name = name;
   }



   @Override
   public Long getBookWrittenBy_id ()
   {
      return bookWrittenBy_id;
   }

   @Override
   public void setBookWrittenBy_id (final Long bookWrittenBy_id)
   {
      this.bookWrittenBy_id = bookWrittenBy_id;
   }

   @Override
   public String toString ()
   {
      return super.toString () + " : " + author_id + ", " + name + ", " + bookWrittenBy_id;
   }

   @Override
   public void validate () throws Spi4jValidationException
   {
      List<String> champsInvalides = null;
      champsInvalides = EntityUtil.checkMandatoryField ("name", name, champsInvalides);
      // si la liste des champs invalides n'est pas nulle, elle ne peut pas être vide à cet endroit
      if (champsInvalides != null)
      {
         throw new Spi4jValidationException (this, champsInvalides.toArray (new String[champsInvalides.size()]));
      }
   }

   // Methodes AuthorEntityImpl
   // Start of user code aba035c58c3bb7bfb2fe800b95873273

   // End of user code

}
