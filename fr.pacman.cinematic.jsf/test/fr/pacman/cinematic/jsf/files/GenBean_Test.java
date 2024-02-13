package fr.pacman.cinematic.jsf.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenBean_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genBean() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/jsf/", "genBean_facade", "appwhite1.ois",
				"ListePersonnesBean");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genBean2() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/jsf/", "genBean_facade", "blanche-model.ois",
				"DetailPersonneBean");
	}
}
