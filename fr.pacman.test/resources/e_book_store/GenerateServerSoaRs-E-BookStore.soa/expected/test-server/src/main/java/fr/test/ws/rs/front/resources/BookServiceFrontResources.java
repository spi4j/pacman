/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports

import fr.test.ws.api.main.AuthorXto;
import fr.test.ws.api.main.BookXto;
import fr.test.ws.rs.front.TestFrontResourcesAbs;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * Ressource frontale (client) pour le service : 'BookService'.
 * La ressource est montée en singleton (pas d'injection @Singleton) mais 
 * une utilisation "forcée" par getInstance afin d'être certain que la classe 
 * sera bien utilisée en tant que singleton dans l'application cible.
 *
 * @author safr@n.
 */
public final class BookServiceFrontResources extends TestFrontResourcesAbs
{

  /**
   * Pattern holder pour le Singleton (lazy-loading).
   */
  private static class Holder {

    public static final BookServiceFrontResources INSTANCE = new BookServiceFrontResources();
  }

  /**
   * Récupération de l'instance pour la classe de ressources.
   *
   * @return L'instance pour le Singleton.
   */
  public static BookServiceFrontResources getInstance() {

    return Holder.INSTANCE ;
  }

  /**
   * Constructeur privé pour la ressource.
   */
  private BookServiceFrontResources(){

    super("/book");
  }


  /**
  * Get the books of interest to be shown on the homepage of the user.
    * @return The books to be showned on the homepage of the user..    * @throws 403 : forbidden.
        */
  public List<BookXto> getHomePageBooks() {

    
    // Start of user code 5ae85e92b53fc219b09cee5732ce20d1

    return target()
      .path("/homepage")
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<BookXto>>() {});

    // End of user code
  }


  /**
  * Gel all authors.
    * @return authors.    */
  public List<AuthorXto> getAllAuthors() {

    
    // Start of user code 01f8ae9496bf6960b8e03f206df36ed2

    return target()
      .path("/allauthors")
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<AuthorXto>>() {});

    // End of user code
  }


  /**
  * 
    * @return types.    */
  public List<String> getAllBookTypes() {

    
    // Start of user code ba29ae7ab9da77f3356350b7c3d7d010

    return target()
      .path("/booktypes")
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<String>>() {});

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
    * @return The books maching the search criterias..    * @throws 400 : badRequest.
        */
  public List<BookXto> searchBooks(final String authorId, final String type, final List<String> keywords) {

    
    // Start of user code 06472fd2021c2aee2d59746b7fb73577

    return target()
      .queryParam("author", authorId)
      .queryParam("type", type)
      .queryParam("keywords", keywords)
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<BookXto>>() {});

    // End of user code
  }


  /**
  * Get a book by its identifier.
    * @param bookId
    *           (In)(*) The book identifier..
    * @return book.    * @throws 404 : notFound.
        */
  public BookXto getBook(final String bookId) {

    
    // Start of user code 0803c1d741c6f84bfa77c59c2518dac6

    return target()
      .path("/{id}")
      .resolveTemplate("bookId", bookId)
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(BookXto.class);

    // End of user code
  }


  /**
  * Create a book.
    * @param book
    *           (In)(*) The data structure describing the book to create..
    * @return The created book as it would be returned by the getBook operation..    * @throws 403 : forbidden.
        */
  public BookXto createBook(final BookXto book) {

    
    // Start of user code 6ee48938bbd3a10e17f553ecdda9c5df

    return target()
      
      .request(MediaType.APPLICATION_JSON)
      .post(Entity.entity(book, MediaType.APPLICATION_JSON))
      .readEntity(BookXto.class);

    // End of user code
  }


  /**
  * Updates the existing book whose identifier is given as path param with the data given in the body param. The book identifier that might be present in the data structure passed in the body is ignored.
    * @param bookId
    *           (In)(*) bookId.
    * @param book
    *           (In)(*) book.
    * @return updatedBook.    * @throws 403 : forbidden.
        */
  public BookXto updateBook(final String bookId, final BookXto book) {

    
    // Start of user code 08c4c657c8493883e4d23b467b685a35

    return target()
      .path("/{id}")
      .resolveTemplate("bookId", bookId)
      .request(MediaType.APPLICATION_JSON)
      .put(Entity.entity(bookId, MediaType.APPLICATION_JSON))
      .readEntity(BookXto.class);

    // End of user code
  }


  /**
  * 
    * @param bookId
    *           (In)(*) bookId.
    * @return ok.    * @throws 403 : forbidden.
        */
  public void deleteBook(final String bookId) {

    
    // Start of user code 565a22159f372e21e079625cab4e6393

     target()
      .path("/{id}")
      .resolveTemplate("bookId", bookId)
      .request(MediaType.APPLICATION_JSON)
      .delete()
      .close();

    // End of user code
  }

}
