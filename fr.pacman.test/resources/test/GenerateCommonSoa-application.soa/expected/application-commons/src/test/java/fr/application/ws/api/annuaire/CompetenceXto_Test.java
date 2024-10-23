/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.api.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.CompetenceAttributes_Enum;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// End of user code

/**
 * Classe de test pour le XTO 'Competence'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class CompetenceXto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @SuppressWarnings("unchecked")
   @Test
   public void testXto ()
   {
      // instanciation du xto
      final CompetenceXto v_xto = new CompetenceXto();

      // génération des champs
      final Long v_id = getRandomLong();
      final String v_libelle =  getRandomString();
      final TypeCompetence_Enum v_typecompetence =  TypeCompetence_Enum.valueOf("TYPECOMP1") ;

      // remplissage du Xto
      v_xto.setId(v_id);
      v_xto.set_libelle(v_libelle);
      v_xto.set_typecompetence(v_typecompetence);

      // test des valeurs
      assertEquals(v_id, v_xto.getId(), "Champ id incorrect");
      assertEquals(v_libelle, v_xto.get_libelle(), "Champ libelle incorrect");
      assertEquals(v_typecompetence, v_xto.get_typecompetence(), "Champ typecompetence incorrect");

      // appel du toString
      assertNotNull(v_xto.toString(), "toString incorrect");

      // déréférencement
      v_xto.setDispose(Collections.EMPTY_LIST);
      assertNotNull(v_xto.get_tab_dispose(), "Champ dispose_id incorrect");

   }

}
