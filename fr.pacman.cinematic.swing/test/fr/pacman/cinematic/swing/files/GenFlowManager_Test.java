package fr.pacman.cinematic.swing.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenFlowManager_Test {

	/**
	 * Test de la generation de l'interface des eveenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genFlowManager() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genFlowManager_facade", "appwhite1.ois",
				"MainFlowManager");
	}

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genFlowManager2() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genFlowManager_facade", "appwhite1.ois",
				"AnnuaireFlowManager");
	}
}
