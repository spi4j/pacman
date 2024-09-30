/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.server.business.annuaire;
// Start of user code for imports

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Interface de services appelables par le client.
 * @author safr@n
 */
public class GradesGwtServiceImpl extends SpiRemoteServiceImpl implements GradesGwtService
{

   private static final long serialVersionUID = 1L;

   /**
    * @return le service réel
    */
   protected GradesService_Itf getService ()
   {
      return ApplicationUserBusiness.getGradesService();
   }

   @Override
   protected SerializationPolicy doGetSerializationPolicy (final HttpServletRequest request,
            final String moduleBaseURL, final String strongName)
   {
      // TODO Auto-generated method stub
      return super.doGetSerializationPolicy (request, getServletContext().getInitParameter("moduleBaseURL"), strongName);
   }

   // findAllGrades_Grade_grades
   // Start of user code findAllGrades_Grade_grades

   @Override
   public List<GradeXto> findAllGrades ()
   {
      return ApplicationUserMapper.getGradeMapper ().convertListDtoToListXto( ApplicationUserBusiness.getGradesService ().findAllGrades ());

   }

   // End of user code

   // findAllPagedGrades_Grade_grades
   // Start of user code findAllPagedGrades_Grade_grades

   @Override
   public List<GradeXto> findAllPagedGrades ()
   {
      return ApplicationUserMapper.getGradeMapper ().convertListDtoToListXto( ApplicationUserBusiness.getGradesService ().findAllPagedGrades ());

   }

   // End of user code

   // GradesService
   // Start of user code GradesService

   // End of user code
}
