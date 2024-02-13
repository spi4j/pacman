package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenMapper_Test {

	/**
	 * Test de la generation de la classe mapper d'un dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genMapper() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genMapper_facade", 
				"application.soa", "PersonneMapper");
	}
}
