package fr.pacman.soalight.files;

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

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genUserBusiness_facade", "appwhite1-dev.ois",
				"Appwhite2UserBusiness");
	}
}
