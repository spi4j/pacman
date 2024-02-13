package fr.pacman.soalight.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAttributesEnum_Test {

	/**
	 * Test de la generation de l'enumeration d'attributs d'une Entity.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAttributesEnumPersonne() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soalight/", "genAttributesEnum_facade", "appwhite1-dev.ois",
				"PersonneAttributes_Enum");
	}

	/**
	 * Test de la generation de l'enumeration d'attributs d'un DTO.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAttributesEnumAdresse() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genAttributesEnum_facade", "appwhite1-dev.ois",
				"AdresseAttributes_Enum");
	}

	/**
	 * Test de la generation de l'enumeration d'attributs d'un DTO.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAttributesEnumProfilFonction() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/soa/", "genAttributesEnum_facade", "My.soa", "Profilfonction_Enum");
	}
}
