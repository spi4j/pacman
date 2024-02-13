package fr.pacman.entity.jdbc.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenEntity_Test {

	/**
	 * Test de la generation d'une interface d'entite.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genEntity() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/entity/jdbc/", "genEntity_facade", 
				"application.entity", "PersonneEntity");
	}
}
