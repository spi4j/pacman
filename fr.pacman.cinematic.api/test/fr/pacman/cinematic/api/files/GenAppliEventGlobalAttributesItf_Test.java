package fr.pacman.cinematic.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAppliEventGlobalAttributesItf_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAppliEventGlobalAttributesItf() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/api/", "genAppliEventGlobalAttributesItf_facade",
				"appwhite1.ois", "Appli1EventGlobalAttributes_Itf");
	}
}
