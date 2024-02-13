package fr.pacman.cinematic.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenViewInterface_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewInterfaceLogin() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/api/", "genViewInterface_facade", "appwhite1.ois",
				"LoginView_Itf");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewInterfaceMain() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/api/", "genViewInterface_facade", "appwhite1.ois",
				"MainView_Itf");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewInterfaceDetailPersonne() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/api/", "genViewInterface_facade", "appwhite1.ois",
				"DetailPersonneView_Itf");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genViewInterfaceListePersonne() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/api/", "genViewInterface_facade", "appwhite1.ois",
				"ListePersonnesView_Itf");
	}
}
