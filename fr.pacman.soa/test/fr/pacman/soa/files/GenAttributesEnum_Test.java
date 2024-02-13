package fr.pacman.soa.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAttributesEnum_Test {

	/**
	 * Test de la generation de l'enumeration d'attributs d'un DTO.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAttributesEnumPersonne() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genAttributesEnum_facade", 
				"application.soa", "PersonneAttributes_Enum");
	}

	/**
	 * Test de la generation de l'enumeration d'attributs d'un DTO.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAttributesEnumAdresse() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genAttributesEnum_facade", 
				"application.soa", "AdresseAttributes_Enum");
	}
}
