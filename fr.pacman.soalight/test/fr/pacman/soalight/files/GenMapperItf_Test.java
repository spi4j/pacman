package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenMapperItf_Test {

	/**
	 * Test de generation.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genMapperItf() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genMapperItf_facade", "appwhite1-dev.ois",
				"PersonneMapper_Itf");
	}
}
