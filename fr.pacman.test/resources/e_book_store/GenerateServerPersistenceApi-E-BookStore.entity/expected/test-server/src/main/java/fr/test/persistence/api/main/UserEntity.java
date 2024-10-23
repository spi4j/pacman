/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;
// Start of user code for imports

import fr.spi4j.persistence.entity.Entity_Itf;
import fr.test.main.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type User.
 * @author safr@n
 */
public interface UserEntity extends Entity_Itf<Long>
{
   // CONSTANTES

   
   // Start of user code 3074c6503da3867bfe07e2d082442cfd

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
    * Obtenir email.
    * @return email.
    */
   String getEmail ();

   /**
    * Affecter email.
    * @param email
    *           (In)(*) email.
    */
   void setEmail (final String email);

   /**
    * Obtenir password.
    * @return password.
    */
   String getPassword ();

   /**
    * Affecter password.
    * @param password
    *           (In)(*) password.
    */
   void setPassword (final String password);


   
   // Start of user code 92bd3a9be871815f37a6989b1a59633b

   // End of user code

}
