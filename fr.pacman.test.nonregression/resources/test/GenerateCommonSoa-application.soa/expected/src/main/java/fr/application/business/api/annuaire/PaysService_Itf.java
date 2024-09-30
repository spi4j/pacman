/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.spi4j.business.Service_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Définit le contrat de services spécifiques pour un type 'Pays'.
 * @author safr@n
 */
// Annotations Service
// Start of user code Annotations Service
// End of user code
public interface PaysService_Itf extends Service_Itf<Long, PaysDto>
{


   /**
    * Obtenir la liste d'objets de type 'Pays' associés à l'instance de type 'Personne'.
    * @param p_personnePaysId
    *           (In) personne
    * @return une liste de PaysDto ayant p_personnePaysId = p_personnePaysId
    */
	// Annotations Paysp_personnePaysId
	// Start of user code Annotations Paysp_personnePaysId

	// End of user code
   List<PaysDto> findListPaysByPersonne (final Long p_personnePaysId);

    // Méthodes PaysService_Itf
    // Start of user code Méthodes PaysService_Itf

    // End of user code
}
