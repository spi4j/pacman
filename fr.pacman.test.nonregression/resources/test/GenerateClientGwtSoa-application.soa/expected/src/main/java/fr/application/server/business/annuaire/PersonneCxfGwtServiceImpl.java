/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.server.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Date;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public class PersonneCxfGwtServiceImpl extends SpiRemoteServiceImpl implements PersonneCxfGwtService
{

   private static final long serialVersionUID = 1L;

   /**
    * @return le service réel
    */
   protected PersonneCxfService_Itf getService ()
   {
      return ApplicationUserBusiness.getPersonneCxfService();
   }

   @Override
   protected SerializationPolicy doGetSerializationPolicy (final HttpServletRequest request,
            final String moduleBaseURL, final String strongName)
   {
      // TODO Auto-generated method stub
      return super.doGetSerializationPolicy (request, getServletContext().getInitParameter("moduleBaseURL"), strongName);
   }

   // findPersonneByIdFromCxf_Personne_personne_Long_identifiant
   // Start of user code findPersonneByIdFromCxf_Personne_personne_Long_identifiant

   @Override
   public PersonneXto findPersonneByIdFromCxf (final Long p_identifiant)
   {
      return ApplicationUserMapper.getPersonneMapper ().convertDtoToXto( ApplicationUserBusiness.getPersonneCxfService ().findPersonneByIdFromCxf (p_identifiant));

   }

   // End of user code

   // PersonneCxfService
   // Start of user code PersonneCxfService

   // End of user code
}
