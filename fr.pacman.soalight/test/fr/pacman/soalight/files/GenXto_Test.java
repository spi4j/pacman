package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenXto_Test {

	/**
	 * Test de la generation d'un Xto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genXto() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genXto_facade", "appwhite1-dev.ois", "PersonneXto");
	}
}
