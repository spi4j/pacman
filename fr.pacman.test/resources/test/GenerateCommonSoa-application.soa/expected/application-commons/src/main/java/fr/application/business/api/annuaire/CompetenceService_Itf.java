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
 * Définit le contrat de services spécifiques pour un type 'Competence'.
 * @author safr@n
 */

// Start of user code Annotations Service
// End of user code
public interface CompetenceService_Itf extends Service_Itf<Long, CompetenceDto>
{


	/**
    * Sauvegarde les relations entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    * @param p_tab_dispose
    *           les relations à ajouter
    */
    void saveDispose (final CompetenceDto p_dto, final List<PersonneDto> p_tab_dispose);

   /**
    * Obtenir la liste d'objets de type 'Personne' associés à l'instance de type 'Competence'.
    * @param p_competence_id
    *           (In)(*) Competence.
    * @return une liste de PersonneDto
    */
   List<PersonneDto> findListDisposeByCompetence (final Long p_competence_id);

	/**
    * Supprime les relations Dispose entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    */
    void deleteDispose (final CompetenceDto p_dto) ;

    
    // Start of user code Methodes CompetenceService_Itf

    // End of user code
}
