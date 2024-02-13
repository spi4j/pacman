package fr.pacman.cinematic.jsf.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenViewImplementation_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewImplementationMain() throws Throwable {

		PacmanExecutor4Test.doTestGenForXhtml("fr/pacman/cinematic/jsf/", "genViewImplementation_facade",
				"/cinematic/blanche-model.ois", "ListePersonnes");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewImplementationLogin() throws Throwable {

		PacmanExecutor4Test.doTestGenForXhtml("fr/pacman/cinematic/swing/", "genViewImplementation_facade",
				"/cinematic/blanche-model.ois", "DetailPersonne");
	}
}
