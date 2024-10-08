/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.bookstore;
// Start of user code for imports

import fr.spi4j.business.ApplicationService_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Définit le contrat de services spécifiques.
 * @author safr@n
 */
// Annotations Service
// Start of user code a9c64d756071d5bde340c5f2f54001e7
// End of user code
public interface LogService extends ApplicationService_Itf
{


   /**
    * 
    * @param message
    *           (In)(*) message.

	*/
	// Annotations log_String_message
	// Start of user code 7cbc0d29b997b684d5ace434853f55fe

	// End of user code
	
    void log (final String message); 

	

    // Méthodes LogService
    // Start of user code 9fbf6205d8d1f2d4d0353d6a67215f40

    // End of user code
}
