package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenMapper_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genMapper() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genMapper_facade", "appwhite1-dev.ois", "PersonneMapper");
	}
}
