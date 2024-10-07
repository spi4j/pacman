/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports

import fr.test.ws.api.main.AuthorXto;
import fr.test.ws.api.main.BookXto;
import fr.test.ws.rs.front.delegates.BookServiceDelegate;
import fr.test.ws.rs.front.exceptions.TestFrontRsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test JUnit pour la ressource frontale (client) : 'BookFrontResourcesTest'.
 *
 * @author safr@n
 */
public class BookFrontResourcesTest {


	// *****************************************************************
	// Liste des paramètres en entrée pour les différents services.
	// Effectuer ici (si besoin) les initialisations communes  
	// pour plusieurs services. !! Attention dans le cas ou plusieurs 
	// paramètres ont le même nom mais un type différent, il sont alors 
	// suffixés (ex param_1, param_2, etc...)
	// *****************************************************************

	// for global input parameters declaration
	// Start of user code 08619449d4a7d8f0eee5227918a18379

	private final String authorId = "S";
	private final BookXto book = new BookXto();
	private final String bookId = "S";
	private final List<String> keywords = new ArrayList<String>();
	private final String type = "S";

	// End of user code

	/**
	 * Enregistrement de la classe de test auprès du Helper.
	 * Récupération du token si le service est sécurisé.
	 */
	@BeforeAll
	public static void init(){

		// for init
		// Start of user code 23427ad5c2cfd5b5c1e5febbaf137394

		TestFrontResourcesTestHelper.registerClass(
			BookFrontResourcesTest.class);


		// End of user code
	}

   /**
   * Get the books of interest to be shown on the homepage of the user.
     * @return The books to be showned on the homepage of the user..    * @throws 403 : forbidden.
         */
	@Test
	@SuppressWarnings("unused")
	public void testGetHomePageBooks() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testGetHomePageBooks");
	
			// for getHomePageBooks_Book_books
			// Start of user code 5ae85e92b53fc219b09cee5732ce20d1

			

			// End of user code

			List<BookXto> entity = BookServiceDelegate.getHomePageBooks();

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response getHomePageBooks_Book_books
			// Start of user code 3b69b49b4cc2ac0fb26bc61b3fec56d5
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * Gel all authors.
     * @return authors.    */
	@Test
	@SuppressWarnings("unused")
	public void testGetAllAuthors() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testGetAllAuthors");
	
			// for getAllAuthors_Author_authors
			// Start of user code 01f8ae9496bf6960b8e03f206df36ed2

			

			// End of user code

			List<AuthorXto> entity = BookServiceDelegate.getAllAuthors();

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response getAllAuthors_Author_authors
			// Start of user code cb230de459ee5ea13b2bb147836790f7
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * 
     * @return types.    */
	@Test
	@SuppressWarnings("unused")
	public void testGetAllBookTypes() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testGetAllBookTypes");
	
			// for getAllBookTypes_String_types
			// Start of user code ba29ae7ab9da77f3356350b7c3d7d010

			

			// End of user code

			List<String> entity = BookServiceDelegate.getAllBookTypes();

			

			// for response getAllBookTypes_String_types
			// Start of user code b500d1d4c3003017fbf0a0cea4ba6b62
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * Get books from search criterias.
     * @param authorId
     *           (In) authorId.
     * @param type
     *           (In) type.
     * @param keywords
     *           (In) keywords.
     * @return The books maching the search criterias..    * @throws 400 : badRequest.
         */
	@Test
	@SuppressWarnings("unused")
	public void testSearchBooks() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testSearchBooks");
	
			// for searchBooks_Book_books_String_authorId_String_type_String_keywords
			// Start of user code 06472fd2021c2aee2d59746b7fb73577

			final String authorId = null;
			final List<String> keywords = null;
			final String type = null;

			// End of user code

			List<BookXto> entity = BookServiceDelegate.searchBooks(TestFrontResourcesTestHelper.setParam(this.authorId, authorId),
			TestFrontResourcesTestHelper.setParam(this.type, type),
			TestFrontResourcesTestHelper.setParam(this.keywords, keywords));

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response searchBooks_Book_books_String_authorId_String_type_String_keywords
			// Start of user code c70ca7d6255229af64be771b710f8597
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * Get a book by its identifier.
     * @param bookId
     *           (In)(*) The book identifier..
     * @return book.    * @throws 404 : notFound.
         */
	@Test
	@SuppressWarnings("unused")
	public void testGetBook() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testGetBook");
	
			// for getBook_Book_book_String_bookId
			// Start of user code 0803c1d741c6f84bfa77c59c2518dac6

			final String bookId = null;

			// End of user code

			BookXto entity = BookServiceDelegate.getBook(TestFrontResourcesTestHelper.setParam(this.bookId, bookId));

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response getBook_Book_book_String_bookId
			// Start of user code 8e4b1c7ebd61a933f8bcbd49d8ea8308
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * Create a book.
     * @param book
     *           (In)(*) The data structure describing the book to create..
     * @return The created book as it would be returned by the getBook operation..    * @throws 403 : forbidden.
         */
	@Test
	@SuppressWarnings("unused")
	public void testCreateBook() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testCreateBook");
	
			// for createBook_Book_createdBook_Book_book
			// Start of user code 6ee48938bbd3a10e17f553ecdda9c5df

			final BookXto book = null;

			// End of user code

			BookXto entity = BookServiceDelegate.createBook(TestFrontResourcesTestHelper.setParam(this.book, book));

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response createBook_Book_createdBook_Book_book
			// Start of user code a5bcdb9682f8905b926422907fecc521
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * Updates the existing book whose identifier is given as path param with the data given in the body param. The book identifier that might be present in the data structure passed in the body is ignored.
     * @param bookId
     *           (In)(*) bookId.
     * @param book
     *           (In)(*) book.
     * @return updatedBook.    * @throws 403 : forbidden.
         */
	@Test
	@SuppressWarnings("unused")
	public void testUpdateBook() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testUpdateBook");
	
			// for updateBook_Book_updatedBook_String_bookId_Book_book
			// Start of user code 08c4c657c8493883e4d23b467b685a35

			final BookXto book = null;
			final String bookId = null;

			// End of user code

			BookXto entity = BookServiceDelegate.updateBook(TestFrontResourcesTestHelper.setParam(this.bookId, bookId),
			TestFrontResourcesTestHelper.setParam(this.book, book));

			assertNotNull(entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response updateBook_Book_updatedBook_String_bookId_Book_book
			// Start of user code e279026e7198b47e5b7dd7e987e43604
			
			TestFrontResourcesTestHelper.displayResponse(entity);

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

   /**
   * 
     * @param bookId
     *           (In)(*) bookId.
     * @return ok.    * @throws 403 : forbidden.
         */
	@Test
	@SuppressWarnings("unused")
	public void testDeleteBook() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			TestFrontResourcesTestHelper.registerMethod("testDeleteBook");
	
			// for deleteBook_String_bookId
			// Start of user code 565a22159f372e21e079625cab4e6393

			final String bookId = null;

			// End of user code

			BookServiceDelegate.deleteBook(TestFrontResourcesTestHelper.setParam(this.bookId, bookId));

			

			// for response deleteBook_String_bookId
			// Start of user code 087aa693c3e6698d66ce2a164a5bbd78
			
			TestFrontResourcesTestHelper.displayResponse("Pas de résultat dans le corps pour cette ressource.");

			// End of user code

		} catch (TestFrontRsException exception) {
			
			TestFrontResourcesTestHelper.displayError(exception);
		}
	}

}

