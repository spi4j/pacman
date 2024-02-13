package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenGwtModuleCommons_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGwtModuleCommons() throws Throwable {

		PacmanExecutor4Test.doTestGenForXml("fr/pacman/soalight/", "genGwtModuleCommons_facade", "appwhite1-dev.ois",
				"Appwhite2CommonsGwt");
	}
}
