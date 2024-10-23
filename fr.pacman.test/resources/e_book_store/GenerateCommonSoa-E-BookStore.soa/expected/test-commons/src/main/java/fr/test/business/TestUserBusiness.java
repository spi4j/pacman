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
import fr.test.business.api.bookstore.BookService;
import fr.test.business.api.bookstore.LogService;
import fr.test.business.api.bookstore.PurchaseOrderService;

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
    * Obtenir la façade de services 'BookService'.
    * @return L'instance désirée.
    */
   public static BookService getBookService ()
   {
      return singleton.getBinding(BookService.class);
   }

   /**
    * Obtenir la façade de services 'PurchaseOrderService'.
    * @return L'instance désirée.
    */
   public static PurchaseOrderService getPurchaseOrderService ()
   {
      return singleton.getBinding(PurchaseOrderService.class);
   }


   /**
    * Obtenir la façade de services 'LogService'.
    * @return L'instance désirée.
    */
   public static LogService getLogService ()
   {
      return singleton.getBinding(LogService.class);
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
      final BookService bookService = wrapService(BookService.class, "fr.test.business.server.bookstore.BookServiceImpl");
      bind(BookService.class, bookService);

      final PurchaseOrderService purchaseOrderService = wrapService(PurchaseOrderService.class, "fr.test.business.server.bookstore.PurchaseOrderServiceImpl");
      bind(PurchaseOrderService.class, purchaseOrderService);

      final LogService logService = wrapService(LogService.class, "fr.test.business.server.bookstore.LogServiceImpl");
      bind(LogService.class, logService);

   }
}
