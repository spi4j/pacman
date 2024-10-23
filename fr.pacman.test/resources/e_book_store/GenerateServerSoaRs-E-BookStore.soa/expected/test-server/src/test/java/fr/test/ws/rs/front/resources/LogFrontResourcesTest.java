/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports

import fr.test.ws.rs.front.delegates.LogServiceDelegate;
import fr.test.ws.rs.front.exceptions.TestFrontRsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test JUnit pour la ressource frontale (client) : 'LogFrontResourcesTest'.
 *
 * @author safr@n
 */
public class LogFrontResourcesTest {


	/**
	 * Enregistrement de la classe de test auprès du Helper.
	 * Récupération du token si le service est sécurisé.
	 */
	@BeforeAll
	public static void init(){

		
		// Start of user code 23427ad5c2cfd5b5c1e5febbaf137394

		TestFrontResourcesTestHelper.registerClass(
			LogFrontResourcesTest.class);


		// End of user code
	}

   /**
   * 
     * @param message
     *           (In)(*) message.
     */
	@Test
	@SuppressWarnings("unused")
	public void testLog() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testLog");
	
			
			// Start of user code 1b95a23c179d6dc2763e5fdccd2212a4

			final String message = "S";

			// End of user code

			LogServiceDelegate.log(message);

			

			
			// Start of user code 048ebe4cf891182cd6767aa758625964
			
			TestFrontResourcesTestHelper.displayResponse("Pas de résultat dans le corps pour cette ressource.");

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

}

