/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.server.bookstore;
// Start of user code for imports

import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import fr.test.business.api.bookstore.BookService;
import fr.test.business.server.bookstore.BookServiceRequirements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */
// annotations service
// Start of user code 674e50e0c74c5b2ab261ee2db581af68
// End of user code
public class BookServiceImpl implements BookService
{

   // Rappel : les services sont sans état.
   // attributs
   // Start of user code 30c346a26df5ef7ff6f1d2298b85c1a5

   // End of user code

   @SuppressWarnings("all")
   private final BookServiceRequirements requirements = new BookServiceRequirements (); // NOPMD

   /**
    * Get the books of interest to be shown on the homepage of the user.

    * @return The books to be showned on the homepage of the user..
    */
   @Override
   public List<BookDto> getHomePageBooks (final int offset, final int limit)
   {

      // Appel des exigences en provenance de la modélisation

      // getHomePageBooks_Book_books
      // Start of user code 70e7277229fd55769bfd958f30b88b27
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	/**
	* Méthode automatiquement générée pour la pagination de l'opération : getHomePageBooks
	* @return Le nombre total d'éléments pour l'opération.
	*/
	@Override
	public int getHomePageBooksTotalCount()
	{
		// Start of user code e4234eb5ebf1315234c06561f996432b
	
		// TODO Méthode à implémenter
		throw new UnsupportedOperationException ();
	
		// End of user code
	}
	
   /**
    * Gel all authors.

    * @return authors.
    */
   @Override
   public List<AuthorDto> getAllAuthors (final int offset, final int limit)
   {

      // Appel des exigences en provenance de la modélisation

      // getAllAuthors_Author_authors
      // Start of user code d8447cd6587e44626863d8cc2a33eaea
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	/**
	* Méthode automatiquement générée pour la pagination de l'opération : getAllAuthors
	* @return Le nombre total d'éléments pour l'opération.
	*/
	@Override
	public int getAllAuthorsTotalCount()
	{
		// Start of user code 0218cee2ab3536d565ba40c57835278f
	
		// TODO Méthode à implémenter
		throw new UnsupportedOperationException ();
	
		// End of user code
	}
	
   /**
    * 

    * @return types.
    */
   @Override
   public List<String> getAllBookTypes ()
   {

      // Appel des exigences en provenance de la modélisation

      // getAllBookTypes_String_types
      // Start of user code e148393d23ca8a0d145ce8aa1f58aa1a
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	
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
   @Override
   public List<BookDto> searchBooks (final String authorId, final String type, final List<String> keywords, final int offset, final int limit)
   {

      // Appel des exigences en provenance de la modélisation

      // searchBooks_Book_books_String_authorId_String_type_String_keywords
      // Start of user code 5455e4fd1caaac73118a24d6283d4513
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	/**
	* Méthode automatiquement générée pour la pagination de l'opération : searchBooks
	* @return Le nombre total d'éléments pour l'opération.
	*/
	@Override
	public int searchBooksTotalCount()
	{
		// Start of user code 7ca4de4c5c11de32b5d66cf323a8d1e5
	
		// TODO Méthode à implémenter
		throw new UnsupportedOperationException ();
	
		// End of user code
	}
	
   /**
    * Get a book by its identifier.
    * @param bookId
    *           (In)(*) The book identifier..

    * @return book.
    */
   @Override
   public BookDto getBook (final String bookId)
   {

      // Appel des exigences en provenance de la modélisation

      // getBook_Book_book_String_bookId
      // Start of user code 6864d029d9520083cd867f7de9095cfa
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	
   /**
    * Create a book.
    * @param book
    *           (In)(*) The data structure describing the book to create..

    * @return The created book as it would be returned by the getBook operation..
    */
   @Override
   public BookDto createBook (final BookDto book)
   {

      // Appel des exigences en provenance de la modélisation

      // createBook_Book_createdBook_Book_book
      // Start of user code 6c757524adce9c58bd1e7b0b91584233
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	
   /**
    * Updates the existing book whose identifier is given as path param with the data given in the body param. The book identifier that might be present in the data structure passed in the body is ignored.
    * @param bookId
    *           (In)(*) bookId.
    * @param book
    *           (In)(*) book.

    * @return updatedBook.
    */
   @Override
   public BookDto updateBook (final String bookId, final BookDto book)
   {

      // Appel des exigences en provenance de la modélisation

      // updateBook_Book_updatedBook_String_bookId_Book_book
      // Start of user code 1b2a002901cc3d78f4ca1739cf4c2d56
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	
   /**
    * 
    * @param bookId
    *           (In)(*) bookId.

    * @return ok.
    */
   @Override
   public  deleteBook (final String bookId)
   {

      // Appel des exigences en provenance de la modélisation

      // deleteBook_String_bookId
      // Start of user code 54700174cb0ac9ed6ff7844ac923db89
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	

   // BookService
   // Start of user code d9d67b06cf5d2e1e1ea359f4bd470135

   // End of user code
}
