package fr.pacman.environment.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenPrimitifType_Test {

	/**
	 * Test de generation
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAnnotationRequirement() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/environment/", "genPrimitiType_facade", 
				"application.environment", "Environment");
	}
}
