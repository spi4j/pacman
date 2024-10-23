/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PaysAttributes_Enum;
import fr.spi4j.tua.BeanTester_Abs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test pour le XTO 'Pays'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class PaysXto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testXto ()
   {
      // instanciation du xto
      final PaysXto v_xto = new PaysXto();

      // génération des champs
      final Long v_id = getRandomLong();
      final String v_nom =  getRandomString();
      final String v_capitale =  getRandomString();
      final Long v_personnePays = getRandomLong();

      // remplissage du Xto
      v_xto.setId(v_id);
      v_xto.set_nom(v_nom);
      v_xto.set_capitale(v_capitale);
      v_xto.set_personnePays_id(v_personnePays);

      // test des valeurs
      assertEquals(v_id, v_xto.getId(), "Champ id incorrect");
      assertEquals(v_nom, v_xto.get_nom(), "Champ nom incorrect");
      assertEquals(v_capitale, v_xto.get_capitale(), "Champ capitale incorrect");
      assertEquals(v_personnePays, v_xto.get_personnePays_id(), "Champ pays_id incorrect");

      // appel du toString
      assertNotNull(v_xto.toString(), "toString incorrect");

      // déréférencement

   }

}
