/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.resources;
// Start of user code for imports

import fr.application.ws.api.annuaire.PersonneXto;
import fr.application.ws.rs.front.delegates.PersonneCxfDelegate;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test JUnit pour la ressource frontale (client) : 'PersonneCxfFrontResources_Test'.
 *
 * @author safr@n
 */
public class PersonneCxfFrontResources_Test {


	/**
	 * Enregistrement de la classe de test auprès du Helper.
	 * Récupération du token si le service est sécurisé.
	 */
	@BeforeAll
	public static void init(){

		
		// Start of user code for init

		ApplicationFrontResourcesTestHelper.registerClass(
			PersonneCxfFrontResources_Test.class);


		// End of user code
	}

   /**
   * 
     * @param p_identifiant
     *           (In)(*) identifiant.
     * @return personne.    */
	@Test
	@SuppressWarnings("unused")
	public void testFindPersonneByIdFromCxf() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			ApplicationFrontResourcesTestHelper.registerMethod("testFindPersonneByIdFromCxf");
	
			
			// Start of user code for findPersonneByIdFromCxf_Personne_personne_Long_identifiant

			final Long v_Identifiant = 1L;

			// End of user code

			PersonneXto v_entity = PersonneCxfDelegate.findPersonneByIdFromCxf(v_Identifiant);

			assertNotNull(v_entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			
			// Start of user code for response findPersonneByIdFromCxf_Personne_personne_Long_identifiant
			
			ApplicationFrontResourcesTestHelper.displayResponse(v_entity);

			// End of user code

		} catch (ApplicationFrontRsException p_exception) {
			
			ApplicationFrontResourcesTestHelper.displayError(p_exception);
		}
	}

}

