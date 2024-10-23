/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.bookstore;
// Start of user code for imports

import fr.spi4j.business.ApplicationService_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import fr.test.business.api.main.BookDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Définit le contrat de services spécifiques.
 * @author safr@n
 */

// Start of user code a9c64d756071d5bde340c5f2f54001e7
// End of user code
public interface BookService extends ApplicationService_Itf
{


   /**
    * Get the books of interest to be shown on the homepage of the user.
    * @return The books to be showned on the homepage of the user..
	*/
	
	// Start of user code 34d4f45b937142ca3b4b131baa997066

	// End of user code
	
    List<BookDto> getHomePageBooks (final int offset, final int limit); 

	/**
	* Méthode spécifique pour la pagination de l'opération : getHomePageBooks
	* @return le nombre total d'éléments pour l'opération.
	*/
	
	// Start of user code 684833716280d039b4e282cf0cd10323
	
	// End of user code
	int getHomePageBooksTotalCount();
	

   /**
    * Gel all authors.
    * @return authors.
	*/
	
	// Start of user code 9cf7e19cb3fb12addd332fefb84023de

	// End of user code
	
    List<AuthorDto> getAllAuthors (final int offset, final int limit); 

	/**
	* Méthode spécifique pour la pagination de l'opération : getAllAuthors
	* @return le nombre total d'éléments pour l'opération.
	*/
	
	// Start of user code e9dcc6577e45afe45b53a69eb0622c1e
	
	// End of user code
	int getAllAuthorsTotalCount();
	

   /**
    * 
    * @return types.
	*/
	
	// Start of user code 33c5b0ce60b3ef6489f57d4abbc50f5f

	// End of user code
	
    List<String> getAllBookTypes (); 

	

   /**
    * Get books from search criterias.
    * @param authorId
    *           (In) authorId.
    * @param type
    *           (In) type.
    * @param keywords
    *           (In) keywords.
    * @return The books maching the search criterias..
	*/
	
	// Start of user code 4e6d76d0705ec5a6f80cc77f2bab78d6

	// End of user code
	
    List<BookDto> searchBooks (final String authorId, final String type, final List<String> keywords, final int offset, final int limit); 

	/**
	* Méthode spécifique pour la pagination de l'opération : searchBooks
	* @return le nombre total d'éléments pour l'opération.
	*/
	
	// Start of user code b8be620515d9d05142a713ef3033679f
	
	// End of user code
	int searchBooksTotalCount();
	

   /**
    * Get a book by its identifier.
    * @param bookId
    *           (In)(*) The book identifier..
    * @return book.
	*/
	
	// Start of user code ad20751ce975859088e09e187b25710e

	// End of user code
	
    BookDto getBook (final String bookId); 

	

   /**
    * Create a book.
    * @param book
    *           (In)(*) The data structure describing the book to create..
    * @return The created book as it would be returned by the getBook operation..
	*/
	
	// Start of user code 84eabd4398f31a0850cd6c269b5a4e96

	// End of user code
	
    BookDto createBook (final BookDto book); 

	

   /**
    * Updates the existing book whose identifier is given as path param with the data given in the body param. The book identifier that might be present in the data structure passed in the body is ignored.
    * @param bookId
    *           (In)(*) bookId.
    * @param book
    *           (In)(*) book.
    * @return updatedBook.
	*/
	
	// Start of user code 63a3903be11dabf3b930c90dea048ae1

	// End of user code
	
    BookDto updateBook (final String bookId, final BookDto book); 

	

   /**
    * 
    * @param bookId
    *           (In)(*) bookId.
    * @return ok.
	*/
	
	// Start of user code 250915d8299ef8355d378547c3208ab5

	// End of user code
	
     deleteBook (final String bookId); 

	

    
    // Start of user code 38ccead263326c151cfe462b052722c5

    // End of user code
}
