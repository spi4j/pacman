package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

public class GenGradesResources_Test {

	/**
	 * Test de la generation du test de la Fetching Strategy d'un Dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGradesResources() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genGradesResources_facade", "application.soa",
				"GradesResources");
	}
}
