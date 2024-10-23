/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PersonneCxfService_Itf;
import fr.application.business.impl_server.annuaire.PersonneCxfServiceRequirements;
import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */

// Start of user code annotations service
// End of user code
public class PersonneCxfService implements PersonneCxfService_Itf
{

   // Rappel : les services sont sans état.
   
   // Start of user code attributs

   // End of user code

   @SuppressWarnings("all")
   private final PersonneCxfServiceRequirements _requirements = new PersonneCxfServiceRequirements (); // NOPMD

   /**
    * 
    * @param p_identifiant
    *           (In)(*) identifiant.

    * @return personne.
    */
   @Override
   public PersonneDto findPersonneByIdFromCxf (final Long p_identifiant)
   {

      // Appel des exigences en provenance de la modélisation

      
      // Start of user code findPersonneByIdFromCxf_Personne_personne_Long_identifiant
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	

   
   // Start of user code PersonneCxfService

   // End of user code
}
