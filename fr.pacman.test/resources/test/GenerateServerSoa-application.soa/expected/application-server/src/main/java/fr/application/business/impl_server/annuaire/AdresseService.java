/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.AdresseDto;
import fr.application.business.api.annuaire.AdresseService_Itf;
import fr.application.business.impl_server.annuaire.AdresseServiceRequirements;
import fr.application.matching.ApplicationUserMatching;
import fr.application.matching.annuaire.AdresseMatch_Itf;
import fr.application.persistence.api.annuaire.AdresseColumns_Enum;
import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques pour un type 'Adresse'. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */
// annotations service
// Start of user code annotations service
// End of user code
public class AdresseService extends Service_Abs<Long, AdresseDto, AdresseColumns_Enum> implements AdresseService_Itf
{

   // Rappel : les services sont sans état.
   // attributs
   // Start of user code attributs

   // End of user code

   @SuppressWarnings("all")
   private final AdresseServiceRequirements _requirements = new AdresseServiceRequirements (); // NOPMD

   /**
    * Obtenir l'instance de matching sur le type 'Adresse'.
    * @return L'instance désirée.
    */
   @Override
   protected AdresseMatch_Itf getMatch ()
   {
      return ApplicationUserMatching.getAdresseMatch ();
   }


   @Override
   public List<AdresseDto> findListAdressesByPersonne (final Long p_personneAdressesId)
   {
      return findByColumn (AdresseColumns_Enum.personneAdresses_id, p_personneAdressesId);
   }


   // AdresseService
   // Start of user code AdresseService

   // End of user code
}
