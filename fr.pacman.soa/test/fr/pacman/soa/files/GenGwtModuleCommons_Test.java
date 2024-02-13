package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenGwtModuleCommons_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGwtModuleCommons() throws Throwable {

		PacmanExecutor4Test.doTestGenForXml("fr/pacman/soa/", "genGwtModuleCommons_facade", 
				"application.soa", "ApplicationCommonsGwt", "Application_commons.gwt");
	}
}
