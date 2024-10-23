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
public interface PersonneCxfGwtServiceAsync
{

   
   // Start of user code findPersonneByIdFromCxf_Personne_personne_Long_identifiant

   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @param callback
    *           (Out) le callback pour retourner personne.
    */
   void findPersonneByIdFromCxf (final Long p_identifiant, final AsyncCallback<PersonneXto> callback);

   // End of user code

   
   // Start of user code PersonneCxfGwtServiceAsync

   // End of user code
}
