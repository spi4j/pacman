/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.impl_server.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.GradesService_Itf;
import fr.application.business.impl_server.annuaire.GradesServiceRequirements;
import fr.spi4j.business.Service_Abs;
import fr.spi4j.exception.Spi4jValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation du contrat de services spécifiques. <br>
 * Pour rappel, les services sont sans état.
 * @author safr@n
 */

// Start of user code annotations service
// End of user code
public class GradesService implements GradesService_Itf
{

   // Rappel : les services sont sans état.
   
   // Start of user code attributs

   // End of user code

   @SuppressWarnings("all")
   private final GradesServiceRequirements _requirements = new GradesServiceRequirements (); // NOPMD

   /**
    * 

    * @return grades.
    */
   @Override
   public List<GradeDto> findAllGrades ()
   {

      // Appel des exigences en provenance de la modélisation

      
      // Start of user code findAllGrades_Grade_grades
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	
   /**
    * 

    * @return grades.
    */
   @Override
   public List<GradeDto> findAllPagedGrades (final int p_offset, final int p_limit)
   {

      // Appel des exigences en provenance de la modélisation

      
      // Start of user code findAllPagedGrades_Grade_grades
      // Appel des exigences
      // TODO Méthode à implémenter
      throw new UnsupportedOperationException ();

      // End of user code
   }

	/**
	* Méthode automatiquement générée pour la pagination de l'opération : findAllPagedGrades
	* @return Le nombre total d'éléments pour l'opération.
	*/
	@Override
	public int findAllPagedGradesTotalCount()
	{
		
		// Start of user code findAllPagedGrades
	
		// TODO Méthode à implémenter
		throw new UnsupportedOperationException ();
	
		// End of user code
	}
	

   
   // Start of user code GradesService

   // End of user code
}
