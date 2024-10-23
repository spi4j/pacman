/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PersonneAttributes_Enum;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

// End of user code

/**
 * Classe de test pour le XTO 'Personne'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class PersonneXto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @SuppressWarnings("unchecked")
   @Test
   public void testXto ()
   {
      // instanciation du xto
      final PersonneXto v_xto = new PersonneXto();

      // génération des champs
      final Long v_id = getRandomLong();
      final String v_nom =  getRandomString(PersonneAttributes_Enum.nom.getSize());
      final String v_prenom =  getRandomString();
      final Boolean v_civil =  getRandomBoolean();
      final Date v_dateNaissance =  getRandomDate();
      final Double v_salaire =  getRandomDouble();
      final Long v_grade = getRandomLong();
      final Long v_marieAvec = getRandomLong();
      final Long v_personneParentDe = getRandomLong();

      // remplissage du Xto
      v_xto.setId(v_id);
      v_xto.set_nom(v_nom);
      v_xto.set_prenom(v_prenom);
      v_xto.set_civil(v_civil);
      v_xto.set_dateNaissance(v_dateNaissance);
      v_xto.set_salaire(v_salaire);
      v_xto.set_grade_id(v_grade);
      v_xto.set_marieAvec_id(v_marieAvec);
      v_xto.set_personneParentDe_id(v_personneParentDe);

      // test des valeurs
      assertEquals(v_id, v_xto.getId(), "Champ id incorrect");
      assertEquals(v_nom, v_xto.get_nom(), "Champ nom incorrect");
      assertEquals(v_prenom, v_xto.get_prenom(), "Champ prenom incorrect");
      assertEquals(v_civil, v_xto.get_civil(), "Champ civil incorrect");
      assertEquals(v_dateNaissance, v_xto.get_dateNaissance(), "Champ dateNaissance incorrect");
      assertEquals(v_salaire, v_xto.get_salaire(), "Champ salaire incorrect");
      assertEquals(v_grade, v_xto.get_grade_id(), "Champ grade_id incorrect");
      assertEquals(v_marieAvec, v_xto.get_marieAvec_id(), "Champ marieAvec_id incorrect");
      assertEquals(v_personneParentDe, v_xto.get_personneParentDe_id(), "Champ parentDe_id incorrect");

      // appel du toString
      assertNotNull(v_xto.toString(), "toString incorrect");

      // déréférencement
      v_xto.set_grade(null);
      assertNull(v_xto.get_grade(), "Champ grade_id incorrect");
      v_xto.set_marieAvec(null);
      assertNull(v_xto.get_marieAvec(), "Champ marieAvec_id incorrect");
      v_xto.setAdresses(Collections.EMPTY_LIST);
      assertNotNull(v_xto.get_tab_adresses(), "Champ adresses_id incorrect");
      v_xto.setParentDe(Collections.EMPTY_LIST);
      assertNotNull(v_xto.get_tab_parentDe(), "Champ parentDe_id incorrect");
      v_xto.setPays(Collections.EMPTY_LIST);
      assertNotNull(v_xto.get_tab_pays(), "Champ pays_id incorrect");

   }

}
