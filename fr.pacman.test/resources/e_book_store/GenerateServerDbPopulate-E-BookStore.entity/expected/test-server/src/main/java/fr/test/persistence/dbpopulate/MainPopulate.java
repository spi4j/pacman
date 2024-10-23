/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.dbpopulate;

// Start of user code for imports

import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;

import fr.test.persistence.TestParamPersistence;
import fr.test.persistence.api.main.AuthorDao;
import fr.test.persistence.api.main.AuthorEntity;
import fr.test.persistence.api.main.BookDao;
import fr.test.persistence.api.main.BookEntity;
import fr.test.persistence.api.main.PurchaseOrderDao;
import fr.test.persistence.api.main.PurchaseOrderEntity;
import fr.test.persistence.api.main.ShoopingCartLineDao;
import fr.test.persistence.api.main.ShoopingCartLineEntity;
import fr.test.persistence.api.main.ShoppingCartDao;
import fr.test.persistence.api.main.ShoppingCartEntity;
import fr.test.persistence.api.main.UserDao;
import fr.test.persistence.api.main.UserEntity;
import fr.test.persistence.api.main.AuthorColumnsEnum;
import fr.test.persistence.api.main.BookColumnsEnum;
import fr.test.persistence.api.main.PurchaseOrderColumnsEnum;
import fr.test.persistence.api.main.ShoopingCartLineColumnsEnum;
import fr.test.persistence.api.main.ShoppingCartColumnsEnum;
import fr.test.persistence.api.main.UserColumnsEnum;
import fr.spi4j.type.XtopSup;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.ParamPersistence_Abs;
import fr.spi4j.persistence.dbpopulate.RepartitionPopulateData;

// End of user code

/**
 * Permet d'effectuer le remplissage du Namespace 'main'.
 */
public class MainPopulate
{

   /**
    * Executable.
    * @param args
    *           arguments
    * @throws Throwable
    *            exception
    */
   public static void main (final String[] args) throws Throwable
   {
      // possibilité de faire un insert en précisant l'id de l'entité
      ParamPersistence_Abs.enableInsertWithId(true);
      TestParamPersistence.getUserPersistence().begin();

      final int nbMaxAuthor = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Author
      final int nbMaxBook = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Book
      final int nbMaxPurchaseOrder = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité PurchaseOrder
      final int nbMaxShoopingCartLine = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité ShoopingCartLine
      final int nbMaxShoppingCart = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité ShoppingCart
      final int nbMaxUser = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité User

      initAuthor(nbMaxAuthor);
      initBook(nbMaxBook);
      initPurchaseOrder(nbMaxPurchaseOrder);
      initShoopingCartLine(nbMaxShoopingCartLine);
      initShoppingCart(nbMaxShoppingCart);
      initUser(nbMaxUser);

      TestParamPersistence.getUserPersistence().commit();
      TestParamPersistence.getUserPersistence().begin();

      final AuthorDao authorDao = TestParamPersistence.getUserPersistence().getAuthorDao();
      final BookDao bookDao = TestParamPersistence.getUserPersistence().getBookDao();
      final PurchaseOrderDao purchaseOrderDao = TestParamPersistence.getUserPersistence().getPurchaseOrderDao();
      final ShoopingCartLineDao shoopingCartLineDao = TestParamPersistence.getUserPersistence().getShoopingCartLineDao();
      final ShoppingCartDao shoppingCartDao = TestParamPersistence.getUserPersistence().getShoppingCartDao();
      final UserDao userDao = TestParamPersistence.getUserPersistence().getUserDao();

      // wrote
      RepartitionPopulateData.repartition_01_0N(
         -1, -1, authorDao, bookDao, BookColumnsEnum.authorWrote_id, false);
      // writtenBy
      RepartitionPopulateData.repartition_01_0N(
         -1, -1, bookDao, authorDao, AuthorColumnsEnum.bookWrittenBy_id, false);
      // shoppingCart
      RepartitionPopulateData.repartition_0N_11(
         -1, -1, shoppingCartDao, purchaseOrderDao, PurchaseOrderColumnsEnum.shoppingCart_id, false);
      // user
      RepartitionPopulateData.repartition_0N_11(
         -1, -1, userDao, purchaseOrderDao, PurchaseOrderColumnsEnum.user_id, false);
      // book
      RepartitionPopulateData.repartition_0N_11(
         -1, -1, bookDao, shoopingCartLineDao, ShoopingCartLineColumnsEnum.book_id, false);
      // lines
      RepartitionPopulateData.repartition_01_0N(
         -1, -1, shoppingCartDao, shoopingCartLineDao, ShoopingCartLineColumnsEnum.shoppingCartLines_id, false);
      // carts
      RepartitionPopulateData.repartition_01_0N(
         -1, -1, userDao, shoppingCartDao, ShoppingCartColumnsEnum.userCarts_id, false);

      TestParamPersistence.getUserPersistence().commit();
   }

   /**
    * Initialisation de l'entité 'Author'.
    * @param nbMaxAuthor
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initAuthor (final int nbMaxAuthor) throws Spi4jValidationException
   {
      final AuthorEntity authorCour = TestParamPersistence.getUserPersistence().getAuthorEntity();
      final AuthorDao authorDao = TestParamPersistence.getUserPersistence().getAuthorDao();
      authorDao.deleteAll();

      for (long i = 0; i < nbMaxAuthor; i++)
      {
		 
         // Start of user code 3600f1f7127353a68af8516bcd0030c8
         // TODO renseigner données de test
         // Affecter l'identifiant
         authorCour.setId(i);
         authorCour.setName("s" + i);
         authorCour.setBookWrittenBy_id(1L);
         // End of user code
         authorDao.create(authorCour);
      }
   }

   /**
    * Initialisation de l'entité 'Book'.
    * @param nbMaxBook
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initBook (final int nbMaxBook) throws Spi4jValidationException
   {
      final BookEntity bookCour = TestParamPersistence.getUserPersistence().getBookEntity();
      final BookDao bookDao = TestParamPersistence.getUserPersistence().getBookDao();
      bookDao.deleteAll();

      for (long i = 0; i < nbMaxBook; i++)
      {
		 
         // Start of user code c29cb526ffce0e7e021aa9f4908df1ff
         // TODO renseigner données de test
         // Affecter l'identifiant
         bookCour.setId(i);
         bookCour.setTitle("s" + i);
         bookCour.setPrice((int) i);
         bookCour.setImage("s" + i);
         bookCour.setType("s" + i);
         bookCour.setAuthorWrote_id(1L);
         // End of user code
         bookDao.create(bookCour);
      }
   }

   /**
    * Initialisation de l'entité 'PurchaseOrder'.
    * @param nbMaxPurchaseOrder
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initPurchaseOrder (final int nbMaxPurchaseOrder) throws Spi4jValidationException
   {
      final PurchaseOrderEntity purchaseOrderCour = TestParamPersistence.getUserPersistence().getPurchaseOrderEntity();
      final PurchaseOrderDao purchaseOrderDao = TestParamPersistence.getUserPersistence().getPurchaseOrderDao();
      purchaseOrderDao.deleteAll();

      for (long i = 0; i < nbMaxPurchaseOrder; i++)
      {
		 
         // Start of user code e2af0c9054ad6ecd3de16ec3fd89f654
         // TODO renseigner données de test
         // Affecter l'identifiant
         purchaseOrderCour.setId(i);
         purchaseOrderCour.setCardNumber("s" + i);
         purchaseOrderCour.setTotalAmount((int) i);
         purchaseOrderCour.setShoppingCart_id(null);
         purchaseOrderCour.setUser_id(null);
         // End of user code
         purchaseOrderDao.create(purchaseOrderCour);
      }
   }

   /**
    * Initialisation de l'entité 'ShoopingCartLine'.
    * @param nbMaxShoopingCartLine
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initShoopingCartLine (final int nbMaxShoopingCartLine) throws Spi4jValidationException
   {
      final ShoopingCartLineEntity shoopingCartLineCour = TestParamPersistence.getUserPersistence().getShoopingCartLineEntity();
      final ShoopingCartLineDao shoopingCartLineDao = TestParamPersistence.getUserPersistence().getShoopingCartLineDao();
      shoopingCartLineDao.deleteAll();

      for (long i = 0; i < nbMaxShoopingCartLine; i++)
      {
		 
         // Start of user code cdd24024ef75c2dc7789c2df2fd6fb47
         // TODO renseigner données de test
         // Affecter l'identifiant
         shoopingCartLineCour.setId(i);
         shoopingCartLineCour.setQuantity((int) i);
         shoopingCartLineCour.setBook_id(null);
         shoopingCartLineCour.setShoppingCartLines_id(1L);
         // End of user code
         shoopingCartLineDao.create(shoopingCartLineCour);
      }
   }

   /**
    * Initialisation de l'entité 'ShoppingCart'.
    * @param nbMaxShoppingCart
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initShoppingCart (final int nbMaxShoppingCart) throws Spi4jValidationException
   {
      final ShoppingCartEntity shoppingCartCour = TestParamPersistence.getUserPersistence().getShoppingCartEntity();
      final ShoppingCartDao shoppingCartDao = TestParamPersistence.getUserPersistence().getShoppingCartDao();
      shoppingCartDao.deleteAll();

      for (long i = 0; i < nbMaxShoppingCart; i++)
      {
		 
         // Start of user code ff36e121bc6b2c961c843f5e29be204f
         // TODO renseigner données de test
         // Affecter l'identifiant
         shoppingCartCour.setId(i);
         shoppingCartCour.setStatus( StatusEnum.valueOf("PENDING") );
         shoppingCartCour.setUserCarts_id(1L);
         // End of user code
         shoppingCartDao.create(shoppingCartCour);
      }
   }

   /**
    * Initialisation de l'entité 'User'.
    * @param nbMaxUser
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initUser (final int nbMaxUser) throws Spi4jValidationException
   {
      final UserEntity userCour = TestParamPersistence.getUserPersistence().getUserEntity();
      final UserDao userDao = TestParamPersistence.getUserPersistence().getUserDao();
      userDao.deleteAll();

      for (long i = 0; i < nbMaxUser; i++)
      {
		 
         // Start of user code 12a5078a94a9b57bfeb79ef336ed2916
         // TODO renseigner données de test
         // Affecter l'identifiant
         userCour.setId(i);
         userCour.setName("s" + i);
         userCour.setEmail("s" + i);
         userCour.setPassword("s" + i);
         // End of user code
         userDao.create(userCour);
      }
   }

}
