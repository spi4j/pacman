/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PaysDto;
import fr.application.business.api.annuaire.PaysService_Itf;
import fr.application.business.impl_server.annuaire.PaysServiceRequirements;
import fr.application.matching.ApplicationUserMatching;
import fr.application.matching.annuaire.PaysMatch_Itf;
import fr.application.persistence.api.annuaire.PaysColumns_Enum;
import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques pour un type 'Pays'. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */

// Start of user code annotations service
// End of user code
public class PaysService extends Service_Abs<Long, PaysDto, PaysColumns_Enum> implements PaysService_Itf
{

   // Rappel : les services sont sans état.
   
   // Start of user code attributs

   // End of user code

   @SuppressWarnings("all")
   private final PaysServiceRequirements _requirements = new PaysServiceRequirements (); // NOPMD

   /**
    * Obtenir l'instance de matching sur le type 'Pays'.
    * @return L'instance désirée.
    */
   @Override
   protected PaysMatch_Itf getMatch ()
   {
      return ApplicationUserMatching.getPaysMatch ();
   }


   @Override
   public List<PaysDto> findListPaysByPersonne (final Long p_personnePaysId)
   {
      return findByColumn (PaysColumns_Enum.personnePays_id, p_personnePaysId);
   }


   
   // Start of user code PaysService

   // End of user code
}
