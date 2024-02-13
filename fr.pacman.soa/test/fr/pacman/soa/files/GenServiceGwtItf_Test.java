package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceGwtItf_Test {

	/**
	 * Test de la generation d'une interface de service pour gwt.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceGwtImpl() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genServiceGwtItf_facade", 
				"application.soa", "PersonneGwtService");
	}
}
