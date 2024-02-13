package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenGwtUserBusiness_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genGwtUserBusiness() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genGwtUserBusiness_facade", 
				"application.soa", "ApplicationGwtUserBusiness");
	}
}
