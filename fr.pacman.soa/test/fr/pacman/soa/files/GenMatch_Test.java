package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenMatch_Test {

	/**
	 * Test de la generation de la classe de matching d'un dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genMatch() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genMatch_facade", 
				"application.soa", "PersonneMatch");
	}
}
