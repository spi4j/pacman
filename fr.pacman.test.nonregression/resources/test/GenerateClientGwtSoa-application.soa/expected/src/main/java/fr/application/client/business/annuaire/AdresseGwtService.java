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
@RemoteServiceRelativePath("../application/AdresseService")
public interface AdresseGwtService extends GwtRemoteService<Long, AdresseXto>
{

   /**
    * Obtenir la liste d'objets de type 'Adresse' associés à l'instance de type 'Personne'.
    * @param p_personneAdresses_id
    *           (In) personne
    * @return une liste de AdresseXto ayant _personneAdresses_id = p_personneAdresses_id
    */
   List<AdresseXto> findListAdressesByPersonne (final Long p_personneAdresses_id);

   // AdresseService
   // Start of user code AdresseService

   // End of user code
}
