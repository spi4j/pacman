package fr.pacman.cinematic.swing.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenSwingLanceur_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genSwingLanceur() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/swing/", "genSwingLanceur_facade", "appwhite1.ois",
				"SwingLanceur");
	}
}
