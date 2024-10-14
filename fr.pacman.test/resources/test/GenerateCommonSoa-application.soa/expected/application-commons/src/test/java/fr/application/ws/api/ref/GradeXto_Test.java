/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.ref;
// Start of user code for imports

import fr.application.business.api.ref.GradeAttributes_Enum;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test pour le XTO 'Grade'.
 * @author safr@n
 */
// Annotation for class
// Start of user code Annotation for class
// End of user code
public class GradeXto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testXto ()
   {
      // instanciation du xto
      final GradeXto v_xto = new GradeXto();

      // génération des champs
      final Long v_id = getRandomLong();
      final String v_libelle =  getRandomString();
      final String v_trigramme =  getRandomString();

      // remplissage du Xto
      v_xto.setId(v_id);
      v_xto.set_libelle(v_libelle);
      v_xto.set_trigramme(v_trigramme);

      // test des valeurs
      assertEquals(v_id, v_xto.getId(), "Champ id incorrect");
      assertEquals(v_libelle, v_xto.get_libelle(), "Champ libelle incorrect");
      assertEquals(v_trigramme, v_xto.get_trigramme(), "Champ trigramme incorrect");

      // appel du toString
      assertNotNull(v_xto.toString(), "toString incorrect");

      // déréférencement

   }

}
