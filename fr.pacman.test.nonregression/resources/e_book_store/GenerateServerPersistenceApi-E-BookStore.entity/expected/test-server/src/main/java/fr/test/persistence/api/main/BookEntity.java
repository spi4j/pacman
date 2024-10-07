/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;
// Start of user code for imports

import fr.spi4j.persistence.entity.Entity_Itf;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type Book.
 * @author safr@n
 */
public interface BookEntity extends Entity_Itf<Long>
{
   // CONSTANTES

   // Constantes BookEntity
   // Start of user code af845690844d6b08e3a35b0d656aa97b

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir title.
    * @return title.
    */
   String getTitle ();

   /**
    * Affecter title.
    * @param title
    *           (In)(*) title.
    */
   void setTitle (final String title);

   /**
    * Obtenir price.
    * @return price.
    */
   Integer getPrice ();

   /**
    * Affecter price.
    * @param price
    *           (In)(*) price.
    */
   void setPrice (final Integer price);

   /**
    * Obtenir image.
    * @return image.
    */
   String getImage ();

   /**
    * Affecter image.
    * @param image
    *           (In)(*) image.
    */
   void setImage (final String image);

   /**
    * Obtenir type.
    * @return type.
    */
   String getType ();

   /**
    * Affecter type.
    * @param type
    *           (In) type.
    */
   void setType (final String type);

   /**
    * Obtenir author.
    * @return author.
    */
   Long getAuthorWrote_id ();

   /**
    * Affecter author.
    * @param authorWrote_id
    *           (In) author.
    */
   void setAuthorWrote_id (final Long authorWrote_id);



   // Methodes BookEntity
   // Start of user code 1776bfa3dd743eef6bbdcbba0e7f3a36

   // End of user code

}
