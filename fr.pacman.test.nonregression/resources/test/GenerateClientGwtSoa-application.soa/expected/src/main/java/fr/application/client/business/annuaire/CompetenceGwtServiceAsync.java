/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.application.ws.api.annuaire.AdresseXto;
import fr.spi4j.ui.gwt.client.services.GwtRemoteService;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public interface CompetenceGwtServiceAsync extends GwtRemoteServiceAsync<Long, CompetenceXto>
{

   /**
    * Obtenir la liste d'objets de type 'Personne' associés à l'instance de type 'Competence'.
    * @param p_competence_id
    *           (In)(*) Competence.
    * @param callback
    *           le callback pour retourner une liste de PersonneXto
    */
   void findListDisposeByCompetence (final Long p_competence_id, final AsyncCallback<List<PersonneXto>> callback);

   // CompetenceGwtServiceAsync
   // Start of user code CompetenceGwtServiceAsync

   // End of user code
}
