/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.bookstore;
// Start of user code for imports

import fr.spi4j.Parameters;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.tua.BeanTester_Abs;
import fr.test.business.TestUserBusiness;
import fr.test.business.api.main.BookAttributesEnum;
import fr.test.business.api.main.BookDto;
import fr.test.persistence.TestUserPersistence;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test du service 'BookService'.
 * @author safr@n
 */
// Annotation for class
// Start of user code 8d11490eb7e3bd7fbe31724d2cea61c5
// End of user code
public class BookServiceTest  extends BeanTester_Abs
{ 

   /** Le 'BookService' testé. */
   private static BookService service;

   /**
    * Méthode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {  
      service = TestUserBusiness.getBookService ();

      // set up before class
      // Start of user code ac28938f801815a863d275d7089501cb
      // End of user code
   }

   /**
    * Méthode d'initialisation de tests.
    */
   @BeforeEach
   public void setUp ()
   {

      // set up
      // Start of user code d5158c215f75d40b3dc3a9efd519b60a
      // End of user code
   }

   /**
    * Test de l'opération 'getHomePageBooks'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testGetHomePageBooks_Book_books () throws Throwable
   {
      // getHomePageBooks_Book_books
      // Start of user code 70e7277229fd55769bfd958f30b88b27
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'getAllAuthors'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testGetAllAuthors_Author_authors () throws Throwable
   {
      // getAllAuthors_Author_authors
      // Start of user code d8447cd6587e44626863d8cc2a33eaea
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'getAllBookTypes'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testGetAllBookTypes_String_types () throws Throwable
   {
      // getAllBookTypes_String_types
      // Start of user code e148393d23ca8a0d145ce8aa1f58aa1a
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'searchBooks'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testSearchBooks_Book_books_String_authorId_String_type_String_keywords () throws Throwable
   {
      // searchBooks_Book_books_String_authorId_String_type_String_keywords
      // Start of user code 5455e4fd1caaac73118a24d6283d4513
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'getBook'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testGetBook_Book_book_String_bookId () throws Throwable
   {
      // getBook_Book_book_String_bookId
      // Start of user code 6864d029d9520083cd867f7de9095cfa
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'createBook'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testCreateBook_Book_createdBook_Book_book () throws Throwable
   {
      // createBook_Book_createdBook_Book_book
      // Start of user code 6c757524adce9c58bd1e7b0b91584233
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'updateBook'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testUpdateBook_Book_updatedBook_String_bookId_Book_book () throws Throwable
   {
      // updateBook_Book_updatedBook_String_bookId_Book_book
      // Start of user code 1b2a002901cc3d78f4ca1739cf4c2d56
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Test de l'opération 'deleteBook'.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testDeleteBook_String_bookId () throws Throwable
   {
      // deleteBook_String_bookId
      // Start of user code 54700174cb0ac9ed6ff7844ac923db89
      // TODO : A Implémenter
      fail ("Test non implémenté");
      // End of user code
   }

   /**
    * Méthode de fin de test : rollback.
    */
   @AfterEach
   public void tearDown ()
   {

      // tear down
      // Start of user code b778e9a7588fa49250428a599cf59f97
      // End of user code
   }

   // specific service test
   // Start of user code 83c9cfe79a629107e128f8bc9ca9ba12

   // End of user code

}
