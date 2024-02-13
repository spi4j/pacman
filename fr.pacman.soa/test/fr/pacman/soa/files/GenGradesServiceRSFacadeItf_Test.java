package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

public class GenGradesServiceRSFacadeItf_Test {

	/**
	 * Test de la generation d'une interface façade de service Rest.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceRSFacade() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genGradesServiceRSFacadeItf_facade", 
				"application.soa", "GradesServiceRSFacade_Itf");
	}
}
