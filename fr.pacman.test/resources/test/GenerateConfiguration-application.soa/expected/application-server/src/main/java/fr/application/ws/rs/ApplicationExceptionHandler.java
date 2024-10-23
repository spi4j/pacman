/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs;
// Start of user code for imports

import jakarta.ws.rs.ext.Provider;

import fr.spi4j.ws.rs.exception.RsExceptionHandler_Abs;

// End of user code

/**
 * Mappeur d'exceptions pour les services REST.
 * @author safr@n.
 */
@Provider
public class ApplicationExceptionHandler extends RsExceptionHandler_Abs
{

	/**
	 * Permet d'obtenir l'exception initiale avant qu'elle soit traitée par le
	 * framework SPI4J.
	 *
	 * @param p_exception : L'exception initiale.
	 */
	@Override
	protected void handleException (final Exception p_exception)
	{
		
		// Start of user code ApplicationExceptionHandler

		// End of user code
	}

	/**
	 * Permet de définir un mapping entre l'exception initiale et une exception de
	 * type RS (exception enrichie).
	 *
	 * @param p_exception : L'exception initiale.
	 * @return L'exception de type RS (exception enrichie pour pouvoir être
	 *         retournée par la ressource).
	 */
	@Override
	protected Exception convertToSpi4jRestException (final Exception p_exception)
	{
      	
      	// Start of user code convertToSpi4jRestException

      	// End of user code

      	return p_exception;
	}
}
