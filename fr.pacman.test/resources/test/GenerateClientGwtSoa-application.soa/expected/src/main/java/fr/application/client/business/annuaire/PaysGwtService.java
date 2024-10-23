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
@RemoteServiceRelativePath("../application/PaysService")
public interface PaysGwtService extends GwtRemoteService<Long, PaysXto>
{

   /**
    * Obtenir la liste d'objets de type 'Pays' associés à l'instance de type 'Personne'.
    * @param p_personnePays_id
    *           (In) personne
    * @return une liste de PaysXto ayant _personnePays_id = p_personnePays_id
    */
   List<PaysXto> findListPaysByPersonne (final Long p_personnePays_id);

   
   // Start of user code PaysService

   // End of user code
}
