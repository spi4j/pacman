package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenUserBusiness_Test {

	/**
	 * Test de la generation de la classe User Business de l'application.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genUserBusiness() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genUserBusiness_facade", 
				"application.soa", "ApplicationUserBusiness");
	}
}
