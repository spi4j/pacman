/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.spi4j.business.ApplicationService_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Définit le contrat de services spécifiques.
 * @author safr@n
 */
// Annotations Service
// Start of user code Annotations Service
// End of user code
public interface PersonneCxfService_Itf extends ApplicationService_Itf
{


   /**
    * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.
	*/
	// Annotations findPersonneByIdFromCxf_Personne_personne_Long_identifiant
	// Start of user code Annotations findPersonneByIdFromCxf_Personne_personne_Long_identifiant

	// End of user code
	
    PersonneDto findPersonneByIdFromCxf (final Long p_identifiant); 

	

    // Méthodes PersonneCxfService_Itf
    // Start of user code Méthodes PersonneCxfService_Itf

    // End of user code
}
