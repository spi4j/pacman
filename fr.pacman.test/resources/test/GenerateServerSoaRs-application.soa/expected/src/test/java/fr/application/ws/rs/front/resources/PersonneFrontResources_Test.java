/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.resources;
// Start of user code for imports

import fr.application.ws.api.annuaire.PersonneXto;
import fr.application.ws.rs.front.delegates.PersonneDelegate;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test JUnit pour la ressource frontale (client) : 'PersonneFrontResources_Test'.
 *
 * @author safr@n
 */
public class PersonneFrontResources_Test {


	/**
	 * Enregistrement de la classe de test auprès du Helper.
	 * Récupération du token si le service est sécurisé.
	 */
	@BeforeAll
	public static void init(){

		
		// Start of user code for init

		ApplicationFrontResourcesTestHelper.registerClass(
			PersonneFrontResources_Test.class);


		// End of user code
	}

   /**
   * 
     * @param p_identifiant
     *           (In)(*) identifiant.
     * @return personne.    */
	@Test
	@SuppressWarnings("unused")
	public void testFindPersonneById() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			ApplicationFrontResourcesTestHelper.registerMethod("testFindPersonneById");
	
			
			// Start of user code for findPersonneById_Personne_personne_Long_identifiant

			final Long v_Identifiant = 1L;

			// End of user code

			PersonneXto v_entity = PersonneDelegate.findPersonneById(v_Identifiant);

			assertNotNull(v_entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			
			// Start of user code for response findPersonneById_Personne_personne_Long_identifiant
			
			ApplicationFrontResourcesTestHelper.displayResponse(v_entity);

			// End of user code

		} catch (ApplicationFrontRsException p_exception) {
			
			ApplicationFrontResourcesTestHelper.displayError(p_exception);
		}
	}

   /**
   * 
     * @param p_nom
     *           (In)(*) nom.
     * @return personne.    */
	@Test
	@SuppressWarnings("unused")
	public void testFindPersonneByName() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			ApplicationFrontResourcesTestHelper.registerMethod("testFindPersonneByName");
	
			
			// Start of user code for findPersonneByName_Personne_personne_String_nom

			final String v_Nom = "S";

			// End of user code

			PersonneXto v_entity = PersonneDelegate.findPersonneByName(v_Nom);

			assertNotNull(v_entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			
			// Start of user code for response findPersonneByName_Personne_personne_String_nom
			
			ApplicationFrontResourcesTestHelper.displayResponse(v_entity);

			// End of user code

		} catch (ApplicationFrontRsException p_exception) {
			
			ApplicationFrontResourcesTestHelper.displayError(p_exception);
		}
	}

   /**
   * 
     * @param p_identifiant
     *           (In)(*) identifiant.
     * @return personne.    */
	@Test
	@SuppressWarnings("unused")
	public void testFindPersonneByIdFromRest() {

		try {

			// Enregistrement du nom de la méthode pour la sortie console.
			ApplicationFrontResourcesTestHelper.registerMethod("testFindPersonneByIdFromRest");
	
			
			// Start of user code for findPersonneByIdFromRest_Personne_personne_Long_identifiant

			final Long v_Identifiant = 1L;

			// End of user code

			PersonneXto v_entity = PersonneDelegate.findPersonneByIdFromRest(v_Identifiant);

			assertNotNull(v_entity, "L'entité retournée est nulle, "
					+ "vérifier le filtre et / ou l'intercepteur.");

			
			// Start of user code for response findPersonneByIdFromRest_Personne_personne_Long_identifiant
			
			ApplicationFrontResourcesTestHelper.displayResponse(v_entity);

			// End of user code

		} catch (ApplicationFrontRsException p_exception) {
			
			ApplicationFrontResourcesTestHelper.displayError(p_exception);
		}
	}

}

