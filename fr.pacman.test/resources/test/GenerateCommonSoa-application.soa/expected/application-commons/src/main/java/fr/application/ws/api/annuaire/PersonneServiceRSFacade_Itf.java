/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;

// End of user code

/**
 * Definit le contrat de services spécifique pour les services REST.
 * @author safr@n
 */
public interface PersonneServiceRSFacade_Itf
{


   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.
	*/
	
    // Start of user code Annotations MethodefindPersonneByIdFromRest

    // End of user code
   PersonneXto findPersonneByIdFromRest (final Long p_identifiant);

   

}
