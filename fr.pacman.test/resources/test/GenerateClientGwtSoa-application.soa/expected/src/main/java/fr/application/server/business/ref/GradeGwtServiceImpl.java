/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.server.business.ref;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import fr.application.ws.api.annuaire.AdresseXto;
import fr.spi4j.ui.gwt.client.services.GwtRemoteService;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public class GradeGwtServiceImpl extends GwtRemoteServiceImpl<Long, GradeXto, GradeDto> implements GradeGwtService
{

   private static final long serialVersionUID = 1L;

   @Override
   protected GradeMapper_Itf getMapper ()
   {
      
      // Start of user code Mapper
      // Le Mapper n'est peut-être pas dans Application, il est peut-être dans un autre domaine qui contient les XTO
      return ApplicationUserMapper.getGradeMapper();
      // End of user code
   }

   @Override
   protected GradeService_Itf getService ()
   {
      
      // Start of user code Service
      return ApplicationUserBusiness.getGradeService();
      // End of user code
   }

   @Override
   protected SerializationPolicy doGetSerializationPolicy (final HttpServletRequest request,
            final String moduleBaseURL, final String strongName)
   {
      // TODO Auto-generated method stub
      return super.doGetSerializationPolicy (request, getServletContext().getInitParameter("moduleBaseURL"), strongName);
   }

   
   // Start of user code GradeService

   // End of user code
}
