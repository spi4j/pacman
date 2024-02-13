package fr.pacman.cinematic.api.files;

import org.junit.jupiter.api.Test;

import fr.pacman.test.PacmanExecutor4Test;

/**
 * 
 * @author MINARM
 * 
 */
public class GenAppliEventTypeEnum_Test {

	/**
	 * Test de la generation de l'interface des evenements.
	 * 
	 * @throws Throwable e
	 */
	@Test
	public void genAppliEventTypeEnum() throws Throwable {

		PacmanExecutor4Test.doTestGen("fr/pacman/cinematic/api/", "genAppliEventTypeEnum_facade", "appwhite1.ois",
				"Appli1EventType_Enum");
	}
}
