package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceTest_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceTest() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genServiceTest_facade", "appwhite1-dev.ois",
				"PersonneService_Test");
	}
}
