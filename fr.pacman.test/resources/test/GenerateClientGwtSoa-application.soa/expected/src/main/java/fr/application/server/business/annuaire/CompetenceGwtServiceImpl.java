/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.server.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.application.ws.api.annuaire.AdresseXto;
import fr.spi4j.ui.gwt.client.services.GwtRemoteService;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public class CompetenceGwtServiceImpl extends GwtRemoteServiceImpl<Long, CompetenceXto, CompetenceDto> implements CompetenceGwtService
{

   private static final long serialVersionUID = 1L;

   @Override
   protected CompetenceMapper_Itf getMapper ()
   {
      
      // Start of user code Mapper
      // Le Mapper n'est peut-être pas dans Application, il est peut-être dans un autre domaine qui contient les XTO
      return ApplicationUserMapper.getCompetenceMapper();
      // End of user code
   }

   @Override
   protected CompetenceService_Itf getService ()
   {
      
      // Start of user code Service
      return ApplicationUserBusiness.getCompetenceService();
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
   public List<PersonneXto> findListDisposeByCompetence (final Long p_competenceId)
   {
      return ApplicationUserMapper.getPersonneMapper ().convertListDtoToListXto(
               getService ().findListDisposeByCompetence(p_competenceId));
   }

   
   // Start of user code CompetenceService

   // End of user code
}
