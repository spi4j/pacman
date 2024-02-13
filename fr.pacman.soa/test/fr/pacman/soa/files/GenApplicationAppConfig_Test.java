package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenApplicationAppConfig_Test {

	/**
	 * Test de la generation du binding REST (injection HK2).
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genApplicationAppConfig() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genApplicationAppConfig_facade", 
				"application.soa", "ApplicationAppConfig");
	}
}
