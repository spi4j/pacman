package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenUserMatching_Test {

	/**
	 * Test de la generation de la classe User Matching de l'application.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genUserMatching() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genUserMatching_facade", 
				"application.soa", "ApplicationUserMatching");
	}
}
