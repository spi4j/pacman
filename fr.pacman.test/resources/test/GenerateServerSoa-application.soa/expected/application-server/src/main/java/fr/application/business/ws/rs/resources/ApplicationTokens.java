/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.ws.rs.resources;
// Start of user code for imports

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import fr.spi4j.ws.rs.RsRefreshToken;

// End of user code


// Start of user code for path
@Path("/tokens")
// End of user code
public class ApplicationTokens
{
	@GET
	@Path("/refresh")
	@RsRefreshToken(protocol = "apiKey")
	public void refresh() {
		// Ne rien ecrire dans cette méthode technique.
		// Généré par Pacman...
	}
}
