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
 * L'interface définissant le contrat de persistance pour le type Author.
 * @author safr@n
 */
public interface AuthorEntity extends Entity_Itf<Long>
{
   // CONSTANTES

   // Constantes AuthorEntity
   // Start of user code 1986c53f3818b4f7c1db7984994eda82

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir name.
    * @return name.
    */
   String getName ();

   /**
    * Affecter name.
    * @param name
    *           (In)(*) name.
    */
   void setName (final String name);

   /**
    * Obtenir book.
    * @return book.
    */
   Long getBookWrittenBy_id ();

   /**
    * Affecter book.
    * @param bookWrittenBy_id
    *           (In) book.
    */
   void setBookWrittenBy_id (final Long bookWrittenBy_id);



   // Methodes AuthorEntity
   // Start of user code 47caeee15e4569ce33279a4c76d6288b

   // End of user code

}
