/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.CompetenceDto;
import fr.application.business.api.annuaire.CompetenceService_Itf;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.business.impl_server.annuaire.CompetenceServiceRequirements;
import fr.application.matching.ApplicationUserMatching;
import fr.application.matching.annuaire.CompetenceMatch_Itf;
import fr.application.persistence.api.annuaire.CompetenceColumns_Enum;
import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques pour un type 'Competence'. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */
// annotations service
// Start of user code annotations service
// End of user code
public class CompetenceService extends Service_Abs<Long, CompetenceDto, CompetenceColumns_Enum> implements CompetenceService_Itf
{

   // Rappel : les services sont sans état.
   // attributs
   // Start of user code attributs

   // End of user code

   @SuppressWarnings("all")
   private final CompetenceServiceRequirements _requirements = new CompetenceServiceRequirements (); // NOPMD

   /**
    * Obtenir l'instance de matching sur le type 'Competence'.
    * @return L'instance désirée.
    */
   @Override
   protected CompetenceMatch_Itf getMatch ()
   {
      return ApplicationUserMatching.getCompetenceMatch ();
   }


    /**
    * Sauvegarde les relations entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    * @param p_tab_dispose
    *           les relations à ajouter
    */
    @Override
    public void saveDispose (final CompetenceDto p_dto, final List<PersonneDto> p_tab_dispose)
    {
      getMatch ().saveDispose (p_dto,p_tab_dispose);
    }

    /**
    * Obtenir la liste d'objets de type 'Personne' associés à l'instance de type 'PersonneDto'.
    * @param p_p_competence_id
    *           (In)(*) PersonneDto
    * @return une liste de PersonneDto
    */
    @Override
    public List<PersonneDto> findListDisposeByCompetence (final Long p_competence_id)
    {
      return getMatch ().findListDisposeByCompetence (p_competence_id);
    }

    /**
    * Supprime les relations Dispose entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    */
   @Override
   public void deleteDispose (final CompetenceDto p_dto)
   {
      getMatch ().deleteDispose (p_dto);
   }


   // CompetenceService
   // Start of user code CompetenceService

   // End of user code
}
