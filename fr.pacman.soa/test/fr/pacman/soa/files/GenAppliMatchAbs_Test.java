package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAppliMatchAbs_Test {

	/**
	 * Test de la generation du Match abs de l'application.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAppliMatchAbs() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genAppliMatchAbs_facade", 
				"application.soa", "ApplicationMatch_Abs");
	}
}
