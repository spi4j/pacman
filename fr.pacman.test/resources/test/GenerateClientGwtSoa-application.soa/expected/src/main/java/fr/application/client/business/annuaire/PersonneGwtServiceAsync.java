/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.application.ws.api.annuaire.PersonneXto;
import fr.spi4j.ui.gwt.client.services.GwtRemoteService;
import java.util.Date;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public interface PersonneGwtServiceAsync extends GwtRemoteServiceAsync<Long, PersonneXto>
{

   /**
    * Obtenir la liste d'objets de type 'Personne' associés à l'instance de type 'Personne'.
    * @param p_personneParentDe_id
    *           (In)(*) personne
    * @param callback
    *           le callback pour retourner une liste de PersonneXto ayant _personneParentDe_id = p_personneParentDe_id
    */
   void findListParentDeByPersonne (final Long p_personneParentDe_id, final AsyncCallback<List<PersonneXto>> callback);

   // findPersonneById_Personne_personne_Long_identifiant
   // Start of user code findPersonneById_Personne_personne_Long_identifiant

   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @param callback
    *           (Out) le callback pour retourner personne.
    */
   void findPersonneById (final Long p_identifiant, final AsyncCallback<PersonneXto> callback);

   // End of user code

   // findPersonneByName_Personne_personne_String_nom
   // Start of user code findPersonneByName_Personne_personne_String_nom

   /**
    * .
    * @param p_nom
    *           (In)(*) nom.
    * @param callback
    *           (Out) le callback pour retourner personne.
    */
   void findPersonneByName (final String p_nom, final AsyncCallback<PersonneXto> callback);

   // End of user code

   // findPersonneByIdFromRest_Personne_personne_Long_identifiant
   // Start of user code findPersonneByIdFromRest_Personne_personne_Long_identifiant

   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @param callback
    *           (Out) le callback pour retourner personne.
    */
   void findPersonneByIdFromRest (final Long p_identifiant, final AsyncCallback<PersonneXto> callback);

   // End of user code

   // PersonneGwtServiceAsync
   // Start of user code PersonneGwtServiceAsync

   // End of user code
}
