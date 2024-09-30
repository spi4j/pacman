package fr.application.ws.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.ApplicationUserBusiness;
import fr.application.mapper.ApplicationUserMapper;
import fr.application.ws.api.annuaire.PersonneServiceRSFacade_Itf;
import fr.application.ws.api.annuaire.PersonneXto;
import fr.spi4j.ws.rs.exception.RsNoResultException;
import fr.spi4j.ws.rs.exception.Spi4jValidationException;
import java.util.Date;
import org.jvnet.hk2.annotations.Service;

// End of user code

/**
 * Implémentation de la façade du service REST pour le service : personne.
 * @author safr@n
 */
@Service
public class PersonneServiceRSFacade implements PersonneServiceRSFacade_Itf
{
	// attributs
	// Start of user code attributs

    // End of user code

	/**
	* Constructeur.
	*/
	public PersonneServiceRSFacade(){
		super();
	}


    /**
    * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne. 
	*/
   @Override
   public PersonneXto findPersonneByIdFromRest (final Long p_identifiant)
   {
		// for findPersonneByIdFromRest_Personne_personne_Long_identifiant
		// Start of user code for findPersonneByIdFromRest_Personne_personne_Long_identifiant
		
		
		return ApplicationUserMapper.getPersonneMapper ()
			.convertDtoToXto( ApplicationUserBusiness.getPersonneService ()
			.findPersonneByIdFromRest (p_identifiant));
		
		// End of user code
   }

   
}
