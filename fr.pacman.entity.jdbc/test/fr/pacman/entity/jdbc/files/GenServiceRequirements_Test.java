package fr.pacman.entity.jdbc.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceRequirements_Test {

	/**
	 * Test de la generation d'une Interface de Service avec Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceRequirements() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/entity/jdbc/", "genServiceRequirements_facade", 
				"application.entity", "PersonneServiceRequirements");
	}
}
