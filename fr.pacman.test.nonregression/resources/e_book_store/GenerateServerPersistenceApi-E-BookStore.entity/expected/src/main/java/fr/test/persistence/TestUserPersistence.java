/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
// CHECKSTYLE:OFF
package fr.test.persistence; // NOPMD
// CHECKSTYLE:ON
// Start of user code for imports

import fr.spi4j.persistence.ParamPersistence_Abs;
import fr.spi4j.persistence.UserPersistence_Abs;

// End of user code

/**
 * Implémentation permettant de centraliser les traitements de persistance de l'application.
 * @author safr@n
 */
public final class TestUserPersistence extends UserPersistence_Abs
{

   /**
    * Constructeur.
    * @param paramPersistence
    *           (In)(*) le paramétrage de la persistance
    */
   protected TestUserPersistence (final ParamPersistence_Abs paramPersistence)
   {
      super (paramPersistence);
   }


   // getAuthorEntity
   // Start of user code 0bb5f5560d89c2a1f64beb1da8633614
   /**
    * Obtenir l'entité 'AuthorEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.AuthorEntity getAuthorEntity ()
   {
      return getEntity (fr.test.persistence.api.main.AuthorEntity.class);
   }
   // End of user code

   // getAuthorDao
   // Start of user code 75d97a29789b741f7cdcc9d6d5b59564
   /**
    * Obtenir le DAO 'AuthorDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.AuthorDao getAuthorDao ()
   {
      return getDao (fr.test.persistence.api.main.AuthorEntity.class);
   }
   // End of user code

   // getBookEntity
   // Start of user code cb2e9c7207633245b8ed4f7957c98382
   /**
    * Obtenir l'entité 'BookEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.BookEntity getBookEntity ()
   {
      return getEntity (fr.test.persistence.api.main.BookEntity.class);
   }
   // End of user code

   // getBookDao
   // Start of user code 229167823c438e473246ea72fb5805eb
   /**
    * Obtenir le DAO 'BookDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.BookDao getBookDao ()
   {
      return getDao (fr.test.persistence.api.main.BookEntity.class);
   }
   // End of user code

   // getPurchaseOrderEntity
   // Start of user code b74c4ddb41d0c54736294cb2f16ebb3c
   /**
    * Obtenir l'entité 'PurchaseOrderEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.PurchaseOrderEntity getPurchaseOrderEntity ()
   {
      return getEntity (fr.test.persistence.api.main.PurchaseOrderEntity.class);
   }
   // End of user code

   // getPurchaseOrderDao
   // Start of user code 33de02273c9a5927b2df5dd31dea2216
   /**
    * Obtenir le DAO 'PurchaseOrderDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.PurchaseOrderDao getPurchaseOrderDao ()
   {
      return getDao (fr.test.persistence.api.main.PurchaseOrderEntity.class);
   }
   // End of user code

   // getShoopingCartLineEntity
   // Start of user code 3d1f84e7707c1cfc2f205bb63b63b16e
   /**
    * Obtenir l'entité 'ShoopingCartLineEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoopingCartLineEntity getShoopingCartLineEntity ()
   {
      return getEntity (fr.test.persistence.api.main.ShoopingCartLineEntity.class);
   }
   // End of user code

   // getShoopingCartLineDao
   // Start of user code cdbc463db1d0a8ac2c7537def5ff34c7
   /**
    * Obtenir le DAO 'ShoopingCartLineDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoopingCartLineDao getShoopingCartLineDao ()
   {
      return getDao (fr.test.persistence.api.main.ShoopingCartLineEntity.class);
   }
   // End of user code

   // getShoppingCartEntity
   // Start of user code d773279490f5b3c3a7bd84ee7db55803
   /**
    * Obtenir l'entité 'ShoppingCartEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoppingCartEntity getShoppingCartEntity ()
   {
      return getEntity (fr.test.persistence.api.main.ShoppingCartEntity.class);
   }
   // End of user code

   // getShoppingCartDao
   // Start of user code eb83c0756f0fb97e7cca51d8a45143d6
   /**
    * Obtenir le DAO 'ShoppingCartDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoppingCartDao getShoppingCartDao ()
   {
      return getDao (fr.test.persistence.api.main.ShoppingCartEntity.class);
   }
   // End of user code

   // getUserEntity
   // Start of user code 0759f473c8293b95abc83d97a69cd185
   /**
    * Obtenir l'entité 'UserEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.UserEntity getUserEntity ()
   {
      return getEntity (fr.test.persistence.api.main.UserEntity.class);
   }
   // End of user code

   // getUserDao
   // Start of user code af0976c0fa0874e4e7e34a39cefd3740
   /**
    * Obtenir le DAO 'UserDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.UserDao getUserDao ()
   {
      return getDao (fr.test.persistence.api.main.UserEntity.class);
   }
   // End of user code
}
