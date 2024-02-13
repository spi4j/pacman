package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceGwtXml_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceGwtXml() throws Throwable {

		PacmanExecutor4Test.doTestGenForXml("fr/pacman/soalight/", "genServiceGwtXml_facade", "appwhite1-dev.ois",
				"Appwhiet2Services");
	}
}
