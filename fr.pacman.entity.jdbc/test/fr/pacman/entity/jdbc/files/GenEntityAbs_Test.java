package fr.pacman.entity.jdbc.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenEntityAbs_Test {

	/**
	 * Test de la generation d'une interface d'entite.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAppliEntityAbs() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/entity/jdbc/", "genEntityAbs_facade", 
				"application.entity", "Entity_Abs");
	}
}
