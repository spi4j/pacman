/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.ws.rs.resources;
// Start of user code for imports

import fr.application.ws.api.annuaire.PersonneServiceRSFacade_Itf;
import fr.spi4j.ws.rs.RsMediaType;
import fr.spi4j.ws.rs.RsResponseHelper;
import fr.spi4j.ws.rs.RsStatusType;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

// End of user code

/**
 * Ressource pour le service : personne.
 * 
 * @author safr@n.
 */
@Path("/personnes")
public class PersonneResources
{
   @Context
   UriInfo _uriInfo;

   /**
    * Récupération du singleton pour la facade du service.
    */
   @Inject
   PersonneServiceRSFacade_Itf _serviceFacade;


    /**
    * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.
	*/
   @GET
   @Path("/{id}")
   @Produces(RsMediaType.c_application_json_utf8)
   public Response findPersonneByIdFromRest(@PathParam("id") final Long p_identifiant){

		
		// Start of user code findPersonneByIdFromRest_Personne_personne_Long_identifiant

		return RsResponseHelper.responseForEntity(_serviceFacade.findPersonneByIdFromRest(p_identifiant));

		// End of user code
   }
}
