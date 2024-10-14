/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports

import fr.test.ws.rs.front.TestFrontResourcesAbs;
import jakarta.ws.rs.core.*;
import java.util.Date;

// End of user code

/**
 * Ressource frontale (client) pour le service : 'LogService'.
 * La ressource est montée en singleton (pas d'injection @Singleton) mais 
 * une utilisation "forcée" par getInstance afin d'être certain que la classe 
 * sera bien utilisée en tant que singleton dans l'application cible.
 *
 * @author safr@n.
 */
public final class LogServiceFrontResources extends TestFrontResourcesAbs
{

  /**
   * Pattern holder pour le Singleton (lazy-loading).
   */
  private static class Holder {

    public static final LogServiceFrontResources INSTANCE = new LogServiceFrontResources();
  }

  /**
   * Récupération de l'instance pour la classe de ressources.
   *
   * @return L'instance pour le Singleton.
   */
  public static LogServiceFrontResources getInstance() {

    return Holder.INSTANCE ;
  }

  /**
   * Constructeur privé pour la ressource.
   */
  private LogServiceFrontResources(){

    super("/ebookstore/");
  }


  /**
  * 
    * @param message
    *           (In)(*) message.
    */
  public void log(final String message) {

    // for log_String_message
    // Start of user code 1b95a23c179d6dc2763e5fdccd2212a4

     target()
      
      .request(MediaType.APPLICATION_JSON)
      .get()
      .close();

    // End of user code
  }

}
