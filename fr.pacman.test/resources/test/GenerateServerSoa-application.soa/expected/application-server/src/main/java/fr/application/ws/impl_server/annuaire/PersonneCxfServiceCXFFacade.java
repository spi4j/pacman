/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.ApplicationUserBusiness;
import fr.application.mapper.ApplicationUserMapper;
import fr.application.ws.api.annuaire.PersonneCxfServiceCXFFacade_Itf;
import fr.application.ws.api.annuaire.PersonneXto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

// End of user code


/**
 * Implémentation de la façade du web service.
 * @author safr@n
 */
@WebService(serviceName = "PersonneCxfService", portName = "PersonneCxfServicePort", targetNamespace = "http://annuaire.api.ws.application.fr/")
public class PersonneCxfServiceCXFFacade implements PersonneCxfServiceCXFFacade_Itf
{

	
	// Start of user code attributs

    // End of user code
    /**
    * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.
	*/
   @WebMethod
   @Override
   public PersonneXto findPersonneByIdFromCxf (final Long p_identifiant)
   {
	
	// Start of user code findPersonneByIdFromCxf_Personne_personne_Long_identifiant

      return ApplicationUserMapper.getPersonneMapper ().convertDtoToXto( ApplicationUserBusiness.getPersonneCxfService ().findPersonneByIdFromCxf (p_identifiant));

	// End of user code
   }

   
   // Start of user code PersonneCxfServiceCXFFacade

   // End of user code
}
