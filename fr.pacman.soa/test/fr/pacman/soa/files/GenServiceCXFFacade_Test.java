package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceCXFFacade_Test {

	/**
	 * Test de la generation d'une Façade de Service.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceFacade() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genServiceCXFFacade_facade", 
				"application.soa", "PersonneServiceCXFFacade");
	}
}
