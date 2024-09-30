/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.business.annuaire;
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
@RemoteServiceRelativePath("../application/GradesService")
public interface GradesGwtService extends RemoteService
{

   // findAllGrades_Grade_grades
   // Start of user code findAllGrades_Grade_grades

   /**
    * .
    * @return grades.    */
   List<GradeXto> findAllGrades ();

   // End of user code

   // findAllPagedGrades_Grade_grades
   // Start of user code findAllPagedGrades_Grade_grades

   /**
    * .
    * @return grades.    */
   List<GradeXto> findAllPagedGrades ();

   // End of user code

   // GradesService
   // Start of user code GradesService

   // End of user code
}
