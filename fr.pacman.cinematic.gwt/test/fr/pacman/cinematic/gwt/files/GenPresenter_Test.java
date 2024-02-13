package fr.pacman.cinematic.gwt.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenPresenter_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genPresenterMain() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/gwt", "genPresenter_facade", "appwhite1.ois",
				"MainPresenter");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genPresenterLogin() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/gwt/", "genPresenter_facade", "appwhite1.ois",
				"LoginPresenter");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genPresenterDetailPersonne() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/gwt/", "genPresenter_facade", "appwhite1.ois",
				"DetailPersonnePresenter");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genPresenterListePersonnes() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/gwt", "genPresenter_facade", "appwhite1.ois",
				"ListePersonnesPresenter");
	}
}
