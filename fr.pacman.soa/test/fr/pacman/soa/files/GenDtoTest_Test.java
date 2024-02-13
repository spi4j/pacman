package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenDtoTest_Test {

	/**
	 * Test de la generation d'une classe de test de Dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genDtoTest() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genDtoTest_facade", 
				"application.soa", "PersonneDto_Test");
	}
}
