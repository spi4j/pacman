package fr.pacman.cinematic.gwt.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenGwtViews_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGwtViews() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/gwt/", "genGwtViews_facade", "appwhite1.ois", "GwtViews");
	}
}
