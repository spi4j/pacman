/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.server.bookstore;
// Start of user code for imports

import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import fr.test.business.api.bookstore.LogService;
import fr.test.business.server.bookstore.LogServiceRequirements;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */

// Start of user code 674e50e0c74c5b2ab261ee2db581af68
// End of user code
public class LogServiceImpl implements LogService
{

   // Rappel : les services sont sans état.
   
   // Start of user code 30c346a26df5ef7ff6f1d2298b85c1a5

   // End of user code

   @SuppressWarnings("all")
   private final LogServiceRequirements requirements = new LogServiceRequirements (); // NOPMD

   /**
    * 
    * @param message
    *           (In)(*) message.


    */
   @Override
   public void log (final String message)
   {

      // Appel des exigences en provenance de la modélisation

      
      // Start of user code 4db071299dc05baf236770be7fd6aab7
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	

   
   // Start of user code c6ef9642548f75729bf2383425199c21

   // End of user code
}
