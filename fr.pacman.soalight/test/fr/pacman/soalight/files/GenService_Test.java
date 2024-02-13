package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenService_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genService() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genService_facade", "appwhite1-dev.ois",
				"PersonneService");
	}
}
