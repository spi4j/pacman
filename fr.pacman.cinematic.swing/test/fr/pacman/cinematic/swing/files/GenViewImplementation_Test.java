package fr.pacman.cinematic.swing.files;

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

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genViewImplementation_facade", "appwhite1.ois",
				"MainViewSwing");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewImplementationLogin() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genViewImplementation_facade", "appwhite1.ois",
				"LoginViewSwing");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewImplementationListePersonnes() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genViewImplementation_facade", "appwhite1.ois",
				"ListePersonnesViewSwing");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewImplementationDetailPersonne() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genViewImplementation_facade", "appwhite1.ois",
				"DetailPersonneViewSwing");
	}
}
