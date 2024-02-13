package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenMatchItf_Test {

	/**
	 * Test de la generation de la classe d'interface de matching d'un dto
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genMatchItf() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genMatchItf_facade", 
				"application.soa", "PersonneMatch_Itf");
	}
}
