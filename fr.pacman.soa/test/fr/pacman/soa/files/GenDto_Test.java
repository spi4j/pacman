package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenDto_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genDto() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genDto_facade", 
				"application.soa", "PersonneDto");
	}
}
