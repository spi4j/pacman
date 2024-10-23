/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.business.api.annuaire.CompetenceAttributes_Enum;
import fr.application.business.api.annuaire.PersonneDto;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test pour le DTO 'Competence'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class CompetenceDto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
      // génération des champs
      final Long v_id = getRandomLong();
      final String v_libelle =  getRandomString();
      final TypeCompetence_Enum v_typecompetence =  TypeCompetence_Enum.valueOf("TYPECOMP1") ;
      // instanciation et remplissage du dto
      CompetenceDto v_dto = new CompetenceDto();
      // validation du DTO à plusieurs étapes
      validate(v_dto);
      v_dto.set_libelle(v_libelle);
      validate(v_dto);
      v_dto.set_typecompetence(v_typecompetence);
      validate(v_dto);

      // Appel du constructeur de CompetenceDto; ce dto va être validé	
      v_dto = new CompetenceDto(v_id, v_libelle, v_typecompetence);

      // test des valeurs
      assertEquals(v_id, v_dto.getId(), "Champ id incorrect");
      assertEquals(v_libelle, v_dto.get_libelle(), "Champ libelle incorrect");
      assertEquals(v_typecompetence, v_dto.get_typecompetence(), "Champ typecompetence incorrect");

      
      // Start of user code Remplissage references du dto

      // End of user code

      // appel des champs calculés

      // appel du toString
      assertNotNull(v_dto.toString(), "toString incorrect");

      // dernière validation avec DTO valide
      try
      {
         v_dto.validate();
      }
      catch (final Spi4jValidationException v_e)
      {
         fail(v_e.toString());
      }

      // déréferencement
      unreference(v_dto);
   }


   /**
    * Déréférencement des relations du DTO.
    * @param p_dto
    *           le DTO
    */
   @SuppressWarnings("unchecked")
   private void unreference (final CompetenceDto p_dto)
   {
      p_dto.setDispose(Collections.EMPTY_LIST);
      assertNotNull(p_dto.get_tab_dispose(), "Champ dispose_id incorrect");
   }

   /**
    * Validation du DTO.
    * @param p_dto
    *           le DTO à  valider
    */
   private void validate (final CompetenceDto p_dto)
   {
      try
      {
         p_dto.validate();
      }
      catch (final Spi4jValidationException v_e)
      {
         assertTrue(v_e.getMessage().startsWith("Champ(s)"), "Message incorrect");
      }
   }

   /**
    * Test de l'énumération des attributs du DTO.
    */
   @Test
   public void testAttributes ()
   {
      for (final CompetenceAttributes_Enum v_attribute : CompetenceAttributes_Enum.values())
      {
         assertNotNull(v_attribute.toString(), "name");
         assertNotNull(v_attribute.getName(), "name");
         assertNotNull(v_attribute.getDescription(), "description");
         assertNotNull(v_attribute.getSize(), "size");
         assertNotNull(v_attribute.getType(), "type");
         assertNotNull(v_attribute.isMandatory(), "mandatory");
         // mise en cache
         assertNotNull(v_attribute.getGetterMethod(), "getter");
         // lecture du cache
         assertNotNull(v_attribute.getGetterMethod(), "getter");
         // mise en cache (si existant : champ non calculé)
         final java.lang.reflect.Method v_setterMethod1 = v_attribute.getSetterMethod();
         // lecture du cache (si existant : champ non calculé)
         final java.lang.reflect.Method v_setterMethod2 = v_attribute.getSetterMethod();
         // vérification que les 2 valeurs sont identiques
         assertEquals(v_setterMethod1, v_setterMethod2, "setter");
      }
   }
}
