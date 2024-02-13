package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceItf_Test {

	/**
	 * Test de la generation d'une Interface de Service avec Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceItf() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genServiceItf_facade", 
				"application.soa", "PersonneService_Itf");
	}
}
