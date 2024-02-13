package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceGwtImpl_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceGwtImpl() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genServiceGwtImpl_facade", 
				"application.soa", "PersonneGwtServiceImpl");
	}
}
