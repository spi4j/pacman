package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenMapperItf_Test {

	/**
	 * Test de la generation de la classe d'interface mapper d'un dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genMapperItf() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genMapperItf_facade", 
				"application.soa", "PersonneMapper_Itf");
	}
}
