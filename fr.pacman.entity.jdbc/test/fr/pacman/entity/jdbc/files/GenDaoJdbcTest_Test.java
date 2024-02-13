package fr.pacman.entity.jdbc.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenDaoJdbcTest_Test {

	/**
	 * Test de la generation d'une interface d'entite.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genDaoJdbcTest() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/entity/jdbc/", "genDaoJdbcTest_facade", 
				"application.entity", "PersonneDaoJdbc_Test");
	}
}
