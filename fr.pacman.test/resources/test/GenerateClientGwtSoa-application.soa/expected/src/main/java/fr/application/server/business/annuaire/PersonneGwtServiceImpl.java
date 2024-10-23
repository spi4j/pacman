/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.server.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.application.ws.api.annuaire.PersonneXto;
import fr.spi4j.ui.gwt.client.services.GwtRemoteService;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public class PersonneGwtServiceImpl extends GwtRemoteServiceImpl<Long, PersonneXto, PersonneDto> implements PersonneGwtService
{

   private static final long serialVersionUID = 1L;

   @Override
   protected PersonneMapper_Itf getMapper ()
   {
      
      // Start of user code Mapper
      // Le Mapper n'est peut-être pas dans Application, il est peut-être dans un autre domaine qui contient les XTO
      return ApplicationUserMapper.getPersonneMapper();
      // End of user code
   }

   @Override
   protected PersonneService_Itf getService ()
   {
      
      // Start of user code Service
      return ApplicationUserBusiness.getPersonneService();
      // End of user code
   }

   @Override
   protected SerializationPolicy doGetSerializationPolicy (final HttpServletRequest request,
            final String moduleBaseURL, final String strongName)
   {
      // TODO Auto-generated method stub
      return super.doGetSerializationPolicy (request, getServletContext().getInitParameter("moduleBaseURL"), strongName);
   }

   @Override
   public List<PersonneXto> findListParentDeByPersonne (final Long p_personneParentDe_id)
   {
      return getMapper().convertListDtoToListXto(
               getService ().findListParentDeByPersonne(p_personneParentDe_id));
   }

   
   // Start of user code findPersonneById_Personne_personne_Long_identifiant

   @Override
   public PersonneXto findPersonneById (final Long p_identifiant)
   {
      return ApplicationUserMapper.getPersonneMapper ().convertDtoToXto( ApplicationUserBusiness.getPersonneService ().findPersonneById (p_identifiant));

   }

   // End of user code

   
   // Start of user code findPersonneByName_Personne_personne_String_nom

   @Override
   public PersonneXto findPersonneByName (final String p_nom)
   {
      return ApplicationUserMapper.getPersonneMapper ().convertDtoToXto( ApplicationUserBusiness.getPersonneService ().findPersonneByName (p_nom));

   }

   // End of user code

   
   // Start of user code findPersonneByIdFromRest_Personne_personne_Long_identifiant

   @Override
   public PersonneXto findPersonneByIdFromRest (final Long p_identifiant)
   {
      return ApplicationUserMapper.getPersonneMapper ().convertDtoToXto( ApplicationUserBusiness.getPersonneService ().findPersonneByIdFromRest (p_identifiant));

   }

   // End of user code

   
   // Start of user code PersonneService

   // End of user code
}
