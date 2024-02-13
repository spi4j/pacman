package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenGwtModuleBusiness_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGwtModuleBusiness() throws Throwable {

		PacmanExecutor4Test.doTestGenForXml("fr/pacman/soa/", "genGwtModuleBusiness_facade", 
				"application.soa", "ApplicationBusinessGwt", "Application_business.gwt");
	}
}
