/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.delegates;
// Start of user code for imports

import fr.test.ws.api.main.AuthorXto;
import fr.test.ws.api.main.BookXto;
import fr.test.ws.rs.front.exceptions.TestFrontRsException;
import fr.test.ws.rs.front.resources.BookServiceFrontResources;
import jakarta.ws.rs.ProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
* Facade pour la ressource : BookService.
*
* @author safr@n.
*/
public final class BookServiceDelegate {


  	/**
  	* Get the books of interest to be shown on the homepage of the user.
    * @return The books to be showned on the homepage of the user..    * @throws 403 : forbidden.
        */
  	public static List<BookXto> getHomePageBooks() {
		
		try {
			return BookServiceFrontResources.getInstance()
                .getHomePageBooks();

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
  	/**
  	* Gel all authors.
    * @return authors.    */
  	public static List<AuthorXto> getAllAuthors() {
		
		try {
			return BookServiceFrontResources.getInstance()
                .getAllAuthors();

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
  	/**
  	* 
    * @return types.    */
  	public static List<String> getAllBookTypes() {
		
		try {
			return BookServiceFrontResources.getInstance()
                .getAllBookTypes();

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
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
  	public static List<BookXto> searchBooks(final String authorId, final String type, final List<String> keywords) {
		
		try {
			return BookServiceFrontResources.getInstance()
                .searchBooks(authorId, type, keywords);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
  	/**
  	* Get a book by its identifier.
    * @param bookId
    *           (In)(*) The book identifier..
    * @return book.    * @throws 404 : notFound.
        */
  	public static BookXto getBook(final String bookId) {
		
		try {
			return BookServiceFrontResources.getInstance()
                .getBook(bookId);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
  	/**
  	* Create a book.
    * @param book
    *           (In)(*) The data structure describing the book to create..
    * @return The created book as it would be returned by the getBook operation..    * @throws 403 : forbidden.
        */
  	public static BookXto createBook(final BookXto book) {
		
		try {
			return BookServiceFrontResources.getInstance()
                .createBook(book);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
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
  	public static BookXto updateBook(final String bookId, final BookXto book) {
		
		try {
			return BookServiceFrontResources.getInstance()
                .updateBook(bookId, book);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
  	/**
  	* 
    * @param bookId
    *           (In)(*) bookId.
    * @return ok.    * @throws 403 : forbidden.
        */
  	public static void deleteBook(final String bookId) {
		
		try {
			 BookServiceFrontResources.getInstance()
                .deleteBook(bookId);

		} catch(ProcessingException exception) {
			
			throw TestFrontRsException.cast(exception);
		}
	}
}
