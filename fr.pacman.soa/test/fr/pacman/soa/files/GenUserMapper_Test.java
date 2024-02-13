package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenUserMapper_Test {

	/**
	 * Test de la generation de la classe User Mapper de l'application.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genUserMapper() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genUserMapper_facade", 
				"application.soa", "ApplicationUserMapper");
	}
}
