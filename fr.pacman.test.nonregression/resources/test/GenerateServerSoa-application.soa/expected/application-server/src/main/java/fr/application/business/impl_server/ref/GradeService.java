/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.impl_server.ref;
// Start of user code for imports

import fr.application.business.api.ref.GradeDto;
import fr.application.business.api.ref.GradeService_Itf;
import fr.application.business.impl_server.ref.GradeServiceRequirements;
import fr.application.matching.ApplicationUserMatching;
import fr.application.matching.ref.GradeMatch_Itf;
import fr.application.persistence.api.ref.GradeColumns_Enum;
import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques pour un type 'Grade'. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */
// annotations service
// Start of user code annotations service
// End of user code
public class GradeService extends Service_Abs<Long, GradeDto, GradeColumns_Enum> implements GradeService_Itf
{

   // Rappel : les services sont sans état.
   // attributs
   // Start of user code attributs

   // End of user code

   @SuppressWarnings("all")
   private final GradeServiceRequirements _requirements = new GradeServiceRequirements (); // NOPMD

   /**
    * Obtenir l'instance de matching sur le type 'Grade'.
    * @return L'instance désirée.
    */
   @Override
   protected GradeMatch_Itf getMatch ()
   {
      return ApplicationUserMatching.getGradeMatch ();
   }



   // GradeService
   // Start of user code GradeService

   // End of user code
}
