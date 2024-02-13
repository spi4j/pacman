package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenGwtUserBusiness_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGwtUserBusiness() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genGwtUserBusiness_facade", "appwhite1-dev.ois",
				"Appwhite2GwtUserBusiness");
	}
}
