package fr.pacman.entity.jdbc.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenServiceGwtAsync_Test {

	/**
	 * Test de la generation d'un Dto.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genServiceGwtAsync() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/entity/jdbc/", "genServiceGwtAsync_facade", 
				"application.entity", "PersonneGwtServiceAsync");
	}
}
