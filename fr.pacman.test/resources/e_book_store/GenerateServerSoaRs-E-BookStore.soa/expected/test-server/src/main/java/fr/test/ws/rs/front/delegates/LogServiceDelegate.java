/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.delegates;
// Start of user code for imports

import fr.test.ws.rs.front.exceptions.TestFrontRsException;
import fr.test.ws.rs.front.resources.LogServiceFrontResources;
import jakarta.ws.rs.ProcessingException;

// End of user code

/**
* Facade pour la ressource : LogService.
*
* @author safr@n.
*/
public final class LogServiceDelegate {


  	/**
  	* 
    * @param message
    *           (In)(*) message.
    */
  	public static void log(final String message) {
		
		try {
			 LogServiceFrontResources.getInstance()
                .log(message);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
}
