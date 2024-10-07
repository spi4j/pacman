/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.delegates;
// Start of user code for imports

import fr.application.ws.api.annuaire.PersonneXto;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import fr.application.ws.rs.front.resources.PersonneFrontResources;
import jakarta.ws.rs.ProcessingException;
import java.util.Date;

// End of user code

/**
* Facade pour la ressource : personne.
*
* @author safr@n.
*/
public final class PersonneDelegate {


  	/**
  	* 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
  	public static PersonneXto findPersonneById(final Long p_identifiant) {
		
		try {
			return PersonneFrontResources.get_instance()
                .findPersonneById(p_identifiant);

		} catch(ProcessingException p_exception) {
			
			throw ApplicationFrontRsException.cast(p_exception);
		}
	}
  	/**
  	* 
    * @param p_nom
    *           (In)(*) nom.
    * @return personne.    */
  	public static PersonneXto findPersonneByName(final String p_nom) {
		
		try {
			return PersonneFrontResources.get_instance()
                .findPersonneByName(p_nom);

		} catch(ProcessingException p_exception) {
			
			throw ApplicationFrontRsException.cast(p_exception);
		}
	}
  	/**
  	* 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
  	public static PersonneXto findPersonneByIdFromRest(final Long p_identifiant) {
		
		try {
			return PersonneFrontResources.get_instance()
                .findPersonneByIdFromRest(p_identifiant);

		} catch(ProcessingException p_exception) {
			
			throw ApplicationFrontRsException.cast(p_exception);
		}
	}
}
