/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.AdresseAttributes_Enum;
import fr.spi4j.tua.BeanTester_Abs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test pour le XTO 'Adresse'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class AdresseXto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testXto ()
   {
      // instanciation du xto
      final AdresseXto v_xto = new AdresseXto();

      // génération des champs
      final Long v_id = getRandomLong();
      final String v_rue =  getRandomString();
      final String v_ville =  getRandomString();
      final String v_codePostal =  getRandomString(AdresseAttributes_Enum.codePostal.getSize());
      final Long v_personneAdresses = getRandomLong();

      // remplissage du Xto
      v_xto.setId(v_id);
      v_xto.set_rue(v_rue);
      v_xto.set_ville(v_ville);
      v_xto.set_codePostal(v_codePostal);
      v_xto.set_personneAdresses_id(v_personneAdresses);

      // test des valeurs
      assertEquals(v_id, v_xto.getId(), "Champ id incorrect");
      assertEquals(v_rue, v_xto.get_rue(), "Champ rue incorrect");
      assertEquals(v_ville, v_xto.get_ville(), "Champ ville incorrect");
      assertEquals(v_codePostal, v_xto.get_codePostal(), "Champ codePostal incorrect");
      assertEquals(v_personneAdresses, v_xto.get_personneAdresses_id(), "Champ adresses_id incorrect");

      // appel du toString
      assertNotNull(v_xto.toString(), "toString incorrect");

      // déréférencement

   }

}
