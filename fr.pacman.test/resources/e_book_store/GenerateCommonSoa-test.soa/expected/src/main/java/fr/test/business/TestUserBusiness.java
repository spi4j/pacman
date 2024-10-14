/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
// CHECKSTYLE:OFF
package fr.test.business; // NOPMD
// CHECKSTYLE:ON
// Start of user code for imports

import fr.spi4j.business.UserBusiness_Abs;
import fr.spi4j.exception.Spi4jRuntimeException;
import fr.spi4j.persistence.UserPersistence_Abs;
import java.util.Date;

// End of user code

/**
 * Classe permettant de centraliser les factories business de l'application.
 * @author safr@n
 */
public final class TestUserBusiness extends UserBusiness_Abs
{

   /** Singleton. */
   private static TestUserBusiness singleton = new TestUserBusiness();

   /**
    * Constructeur privé.
    */
   private TestUserBusiness ()
   {
      super();
   }


   /**
    * @return le singleton de cette factory
    */
   public static TestUserBusiness getSingleton ()
   {
      return singleton;
   }

   @Override
   protected UserPersistence_Abs getUserPersistence ()
   {
	  	// Pas de persistance pour cette application.
		// Cette méthode n'a aucunne raison d'être appelée par spi4j.
	  	return null;
   }

   // CHECKSTYLE:OFF
   @Override
   // CHECKSTYLE:ON
   public void initBindings () // NOPMD
   {
      // dans cette factory de la partie commune entre un client et le serveur
      // on ne référence pas les classes d'implémentations "serveur" des services
      // pour ne pas avoir d'erreur de compilation (et pour ne pas avoir ClassNotFoundException à l'exécution)
   }
}
