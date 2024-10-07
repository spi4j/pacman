/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.delegates;
// Start of user code for imports

import fr.application.ws.api.ref.GradeXto;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import fr.application.ws.rs.front.resources.GradesFrontResources;
import jakarta.ws.rs.ProcessingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
* Facade pour la ressource : grades.
*
* @author safr@n.
*/
public final class GradesDelegate {


  	/**
  	* 
    * @return grades.    */
  	public static List<GradeXto> findAllGrades() {
		
		try {
			return GradesFrontResources.get_instance()
                .findAllGrades();

		} catch(ProcessingException p_exception) {
			
			throw ApplicationFrontRsException.cast(p_exception);
		}
	}
  	/**
  	* 
    * @return grades.    */
  	public static List<GradeXto> findAllPagedGrades() {
		
		try {
			return GradesFrontResources.get_instance()
                .findAllPagedGrades();

		} catch(ProcessingException p_exception) {
			
			throw ApplicationFrontRsException.cast(p_exception);
		}
	}
}
