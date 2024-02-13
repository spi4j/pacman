package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenPermissionsItf_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genPermissionsItf() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genPermissionsItf_facade", "appwhite1-dev.ois",
				"Appwhite2Permissions_Itf");

	}
}
