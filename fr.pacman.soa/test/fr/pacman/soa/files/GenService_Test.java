package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenService_Test {

	/**
	 * Test de la generation d'un Service avec Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genService() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genService_facade", 
				"application.soa", "PersonneService");
	}
}
