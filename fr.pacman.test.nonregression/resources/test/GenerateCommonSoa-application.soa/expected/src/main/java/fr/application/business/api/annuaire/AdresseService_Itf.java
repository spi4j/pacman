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
 * Définit le contrat de services spécifiques pour un type 'Adresse'.
 * @author safr@n
 */
// Annotations Service
// Start of user code Annotations Service
// End of user code
public interface AdresseService_Itf extends Service_Itf<Long, AdresseDto>
{


   /**
    * Obtenir la liste d'objets de type 'Adresse' associés à l'instance de type 'Personne'.
    * @param p_personneAdressesId
    *           (In) personne
    * @return une liste de AdresseDto ayant p_personneAdressesId = p_personneAdressesId
    */
	// Annotations Adressesp_personneAdressesId
	// Start of user code Annotations Adressesp_personneAdressesId

	// End of user code
   List<AdresseDto> findListAdressesByPersonne (final Long p_personneAdressesId);

    // Méthodes AdresseService_Itf
    // Start of user code Méthodes AdresseService_Itf

    // End of user code
}
