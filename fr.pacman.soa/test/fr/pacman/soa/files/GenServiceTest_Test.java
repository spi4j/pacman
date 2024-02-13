package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceTest_Test {

	/**
	 * Test de la generation d'un Test de Service avec Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceTest() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genServiceTest_facade", 
				"application.soa", "PersonneService_Test");
	}
}
