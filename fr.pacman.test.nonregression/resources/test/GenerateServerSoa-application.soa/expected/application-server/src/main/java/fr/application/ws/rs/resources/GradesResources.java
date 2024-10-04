/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.resources;
// Start of user code for imports

import fr.application.ws.api.annuaire.GradesServiceRSFacade_Itf;
import fr.spi4j.ws.rs.RsConstants;
import fr.spi4j.ws.rs.RsMediaType;
import fr.spi4j.ws.rs.RsPaginatorHelper;
import fr.spi4j.ws.rs.RsResponseHelper;
import fr.spi4j.ws.rs.RsStatusType;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

// End of user code

/**
 * Ressource pour le service : grades.
 * 
 * @author safr@n.
 */
@Path("/0.0.1/grades")
public class GradesResources
{
   @Context
   UriInfo _uriInfo;

   /**
    * Récupération du singleton pour la facade du service.
    */
   @Inject
   GradesServiceRSFacade_Itf _serviceFacade;


    /**
    * 
    * @return grades.
	*/
   @GET
   @Produces(RsMediaType.c_application_json_utf8)
   public Response findAllGrades(){

		// findAllGrades_Grade_grades
		// Start of user code findAllGrades_Grade_grades

		return RsResponseHelper.responseForEntity(_serviceFacade.findAllGrades()
		, RsStatusType.create(200, "OK"));

		// End of user code
   }
    /**
    * 
	* @param p_pageToken : 
	*			(In)(*) Jeton de pagination.
    * @return grades.
	*/
   @GET
   @Path("/paged")
   @Produces(RsMediaType.c_application_json_utf8)
   public Response findAllPagedGrades(@QueryParam("pageToken") final String p_pageToken){

		// findAllPagedGrades_Grade_grades
		// Start of user code findAllPagedGrades_Grade_grades

		final RsPaginatorHelper v_paginator = new RsPaginatorHelper(_uriInfo, RsConstants.c_list_default_page_count, p_pageToken)
		.completeWithTotalCount(_serviceFacade.findAllPagedGradesTotalCount());
		
		return RsResponseHelper.responseForEntity(v_paginator.completeWithPagedList(
		_serviceFacade.findAllPagedGrades(v_paginator.get_pageOffset(), v_paginator.get_pageSize()))
		, RsStatusType.create(200, "OK"));

		// End of user code
   }
}
