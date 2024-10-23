/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
@RemoteServiceRelativePath("../application/PersonneCxfService")
public interface PersonneCxfGwtService extends RemoteService
{

   
   // Start of user code findPersonneByIdFromCxf_Personne_personne_Long_identifiant

   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
   PersonneXto findPersonneByIdFromCxf (final Long p_identifiant);

   // End of user code

   
   // Start of user code PersonneCxfService

   // End of user code
}
