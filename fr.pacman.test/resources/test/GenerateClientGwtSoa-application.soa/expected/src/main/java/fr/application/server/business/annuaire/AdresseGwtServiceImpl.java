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
public class AdresseGwtServiceImpl extends GwtRemoteServiceImpl<Long, AdresseXto, AdresseDto> implements AdresseGwtService
{

   private static final long serialVersionUID = 1L;

   @Override
   protected AdresseMapper_Itf getMapper ()
   {
      
      // Start of user code Mapper
      // Le Mapper n'est peut-être pas dans Application, il est peut-être dans un autre domaine qui contient les XTO
      return ApplicationUserMapper.getAdresseMapper();
      // End of user code
   }

   @Override
   protected AdresseService_Itf getService ()
   {
      
      // Start of user code Service
      return ApplicationUserBusiness.getAdresseService();
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
   public List<AdresseXto> findListAdressesByPersonne (final Long p_personneAdresses_id)
   {
      return getMapper().convertListDtoToListXto(
               getService ().findListAdressesByPersonne(p_personneAdresses_id));
   }

   
   // Start of user code AdresseService

   // End of user code
}
