/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.resources;
// Start of user code for imports

import fr.application.ws.api.ref.GradeXto;
import fr.application.ws.rs.front.delegates.GradesDelegate;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test JUnit pour la ressource frontale (client) : 'GradesFrontResources_Test'.
 *
 * @author safr@n
 */
public class GradesFrontResources_Test {


	/**
	 * Enregistrement de la classe de test auprès du Helper.
	 * Récupération du token si le service est sécurisé.
	 */
	@BeforeAll
	public static void init(){

		// for init
		// Start of user code for init

		ApplicationFrontResourcesTestHelper.registerClass(
			GradesFrontResources_Test.class);


		// End of user code
	}

   /**
   * 
     * @return grades.    */
	@Test
	@SuppressWarnings("unused")
	public void testFindAllGrades() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			ApplicationFrontResourcesTestHelper.registerMethod("testFindAllGrades");
	
			// for findAllGrades_Grade_grades
			// Start of user code for findAllGrades_Grade_grades

			

			// End of user code

			List<GradeXto> v_entity = GradesDelegate.findAllGrades();

			assertNotNull(v_entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response findAllGrades_Grade_grades
			// Start of user code for response findAllGrades_Grade_grades
			
			ApplicationFrontResourcesTestHelper.displayResponse(v_entity);

			// End of user code

		} catch (ApplicationFrontRsException p_exception) {
			
			ApplicationFrontResourcesTestHelper.displayError(p_exception);
		}
	}

   /**
   * 
     * @return grades.    */
	@Test
	@SuppressWarnings("unused")
	public void testFindAllPagedGrades() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			ApplicationFrontResourcesTestHelper.registerMethod("testFindAllPagedGrades");
	
			// for findAllPagedGrades_Grade_grades
			// Start of user code for findAllPagedGrades_Grade_grades

			

			// End of user code

			List<GradeXto> v_entity = GradesDelegate.findAllPagedGrades();

			assertNotNull(v_entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			// for response findAllPagedGrades_Grade_grades
			// Start of user code for response findAllPagedGrades_Grade_grades
			
			ApplicationFrontResourcesTestHelper.displayResponse(v_entity);

			// End of user code

		} catch (ApplicationFrontRsException p_exception) {
			
			ApplicationFrontResourcesTestHelper.displayError(p_exception);
		}
	}

}

