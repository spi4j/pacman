package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenFetchingStrategy_Test {

	/**
	 * Test de la generation de la Fetching Strategy d'un Dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genFetchingStrategy() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genFetchingStrategy_facade", 
				"application.soa", "PersonneFetchingStrategy");
	}
}
