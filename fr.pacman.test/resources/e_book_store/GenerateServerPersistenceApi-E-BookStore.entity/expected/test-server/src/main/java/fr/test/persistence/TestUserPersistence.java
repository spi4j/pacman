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


   /**
    * Obtenir l'entité 'AuthorEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.AuthorEntity getAuthorEntity ()
   {
      return getEntity (fr.test.persistence.api.main.AuthorEntity.class);
   }


   /**
    * Obtenir le DAO 'AuthorDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.AuthorDao getAuthorDao ()
   {
      return getDao (fr.test.persistence.api.main.AuthorEntity.class);
   }


   /**
    * Obtenir l'entité 'BookEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.BookEntity getBookEntity ()
   {
      return getEntity (fr.test.persistence.api.main.BookEntity.class);
   }


   /**
    * Obtenir le DAO 'BookDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.BookDao getBookDao ()
   {
      return getDao (fr.test.persistence.api.main.BookEntity.class);
   }


   /**
    * Obtenir l'entité 'PurchaseOrderEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.PurchaseOrderEntity getPurchaseOrderEntity ()
   {
      return getEntity (fr.test.persistence.api.main.PurchaseOrderEntity.class);
   }


   /**
    * Obtenir le DAO 'PurchaseOrderDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.PurchaseOrderDao getPurchaseOrderDao ()
   {
      return getDao (fr.test.persistence.api.main.PurchaseOrderEntity.class);
   }


   /**
    * Obtenir l'entité 'ShoopingCartLineEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoopingCartLineEntity getShoopingCartLineEntity ()
   {
      return getEntity (fr.test.persistence.api.main.ShoopingCartLineEntity.class);
   }


   /**
    * Obtenir le DAO 'ShoopingCartLineDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoopingCartLineDao getShoopingCartLineDao ()
   {
      return getDao (fr.test.persistence.api.main.ShoopingCartLineEntity.class);
   }


   /**
    * Obtenir l'entité 'ShoppingCartEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoppingCartEntity getShoppingCartEntity ()
   {
      return getEntity (fr.test.persistence.api.main.ShoppingCartEntity.class);
   }


   /**
    * Obtenir le DAO 'ShoppingCartDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.ShoppingCartDao getShoppingCartDao ()
   {
      return getDao (fr.test.persistence.api.main.ShoppingCartEntity.class);
   }


   /**
    * Obtenir l'entité 'UserEntity'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.UserEntity getUserEntity ()
   {
      return getEntity (fr.test.persistence.api.main.UserEntity.class);
   }


   /**
    * Obtenir le DAO 'UserDao'.
    * @return L'instance désirée.
    */
   public fr.test.persistence.api.main.UserDao getUserDao ()
   {
      return getDao (fr.test.persistence.api.main.UserEntity.class);
   }

}
