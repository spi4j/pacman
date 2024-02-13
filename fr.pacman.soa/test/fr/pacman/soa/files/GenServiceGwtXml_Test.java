package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceGwtXml_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceGwtXml() throws Throwable {

		PacmanExecutor4Test.doTestGenForXml("fr/pacman/soa/", "genServiceGwtXml_facade", 
				"application.soa", "ApplicationServices", "services_application");
	}
}
