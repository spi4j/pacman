package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

public class GenGradesServiceRSFacade_Test {

	/**
	 * Test de la generation d'une façade de service Rest.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceRSFacade() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genGradesServiceRSFacade_facade", 
				"application.soa", "GradesServiceRSFacade");
	}
}
