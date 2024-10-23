/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.spi4j.business.ApplicationService_Itf;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Définit le contrat de services spécifiques.
 * @author safr@n
 */

// Start of user code Annotations Service
// End of user code
public interface GradesService_Itf extends ApplicationService_Itf
{


   /**
    * 
    * @return grades.
	*/
	
	// Start of user code Annotations findAllGrades_Grade_grades

	// End of user code
	
    List<GradeDto> findAllGrades (); 

	

   /**
    * 
    * @return grades.
	*/
	
	// Start of user code Annotations findAllPagedGrades_Grade_grades

	// End of user code
	
    List<GradeDto> findAllPagedGrades (final int p_offset, final int p_limit); 

	/**
	* Méthode spécifique pour la pagination de l'opération : findAllPagedGrades
	* @return le nombre total d'éléments pour l'opération.
	*/
	
	// Start of user code Annotations MethodefindAllPagedGradestotal count
	
	// End of user code
	int findAllPagedGradesTotalCount();
	

    
    // Start of user code Methodes GradesService_Itf

    // End of user code
}
