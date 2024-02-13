package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenUserMapper_Test {

	/**
	 * Test de la generation de la classe User Mapper de l'application.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genUserMapper() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genUserMapper_facade", "appwhite1-dev.ois",
				"Appwhite2UserMapper");
	}
}
