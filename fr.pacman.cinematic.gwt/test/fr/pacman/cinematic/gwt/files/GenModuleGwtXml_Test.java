package fr.pacman.cinematic.gwt.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenModuleGwtXml_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genModuleGwtXml() throws Throwable {

		PacmanExecutor4Test.doTestGenForXml("fr/pacman/cinematic/gwt/", "genModuleGwtXml_facade", "appwhite1.ois",
				"Appli1.gwt");
	}
}
