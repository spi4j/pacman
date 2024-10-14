/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.delegates;
// Start of user code for imports

import fr.application.ws.api.annuaire.PersonneXto;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import fr.application.ws.rs.front.resources.PersonneCxfFrontResources;
import jakarta.ws.rs.ProcessingException;
import java.util.Date;

// End of user code

/**
* Facade pour la ressource : personneCxf.
*
* @author safr@n.
*/
public final class PersonneCxfDelegate {


  	/**
  	* 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
  	public static PersonneXto findPersonneByIdFromCxf(final Long p_identifiant) {
		
		try {
			return PersonneCxfFrontResources.get_instance()
                .findPersonneByIdFromCxf(p_identifiant);

		} catch(ProcessingException p_exception) {
			
			throw ApplicationFrontRsException.cast(p_exception);
		}
	}
}
