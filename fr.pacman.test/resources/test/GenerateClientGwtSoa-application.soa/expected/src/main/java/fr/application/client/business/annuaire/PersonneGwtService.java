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

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
@RemoteServiceRelativePath("../application/PersonneService")
public interface PersonneGwtService extends GwtRemoteService<Long, PersonneXto>
{

   /**
    * Obtenir la liste d'objets de type 'Personne' associés à l'instance de type 'Personne'.
    * @param p_personneParentDe_id
    *           (In)(*) personne
    * @return une liste de PersonneXto ayant _personneParentDe_id = p_personneParentDe_id
    */
   List<PersonneXto> findListParentDeByPersonne (final Long p_personneParentDe_id);

   
   // Start of user code findPersonneById_Personne_personne_Long_identifiant

   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
   PersonneXto findPersonneById (final Long p_identifiant);

   // End of user code

   
   // Start of user code findPersonneByName_Personne_personne_String_nom

   /**
    * .
    * @param p_nom
    *           (In)(*) nom.
    * @return personne.    */
   PersonneXto findPersonneByName (final String p_nom);

   // End of user code

   
   // Start of user code findPersonneByIdFromRest_Personne_personne_Long_identifiant

   /**
    * .
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
   PersonneXto findPersonneByIdFromRest (final Long p_identifiant);

   // End of user code

   
   // Start of user code PersonneService

   // End of user code
}
