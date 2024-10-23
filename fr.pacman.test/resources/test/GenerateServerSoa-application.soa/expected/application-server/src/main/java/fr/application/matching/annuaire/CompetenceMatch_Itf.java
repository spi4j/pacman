/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.CompetenceAttributes_Enum;
import fr.application.business.api.annuaire.CompetenceDto;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.persistence.api.annuaire.CompetenceColumns_Enum;
import fr.application.persistence.api.annuaire.CompetenceEntity_Itf;
import fr.spi4j.matching.Match_Itf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * L'interface définissant le contrat pour le Matcher (= persistance <-> business) sur le type 'Competence'.
 * @author safr@n
 */
public interface CompetenceMatch_Itf extends Match_Itf<Long, CompetenceDto, CompetenceEntity_Itf, CompetenceColumns_Enum>
{

   /**
    * Pour un matching 1 pour 1, retourne la colonne Entity associée à l'attribut DTO.
    * @param p_attribute
    *           l'attribut DTO
    * @return la colonne Entity
    */
   CompetenceColumns_Enum getColumn (final CompetenceAttributes_Enum p_attribute);

	
	/**
    * Sauvegarde les relations entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    * @param p_tab_personne
    *           les relations à ajouter
    */
    void saveDispose (final CompetenceDto p_dto, final List<PersonneDto> p_tab_personne);
   

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
    void deleteDispose (final CompetenceDto p_dto);

   
   // Start of user code CompetenceMatch_Itf

   // End of user code
}
