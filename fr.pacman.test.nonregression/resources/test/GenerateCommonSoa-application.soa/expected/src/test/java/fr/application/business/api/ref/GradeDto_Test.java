/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.ref;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test pour le DTO 'Grade'.
 * @author safr@n
 */
// Annotation for class
// Start of user code Annotation for class
// End of user code
public class GradeDto_Test extends BeanTester_Abs
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
      final String v_trigramme =  getRandomString();
      // instanciation et remplissage du dto
      GradeDto v_dto = new GradeDto();
      // validation du DTO à plusieurs étapes
      validate(v_dto);
      v_dto.set_libelle(v_libelle);
      validate(v_dto);
      v_dto.set_trigramme(v_trigramme);
      validate(v_dto);

      // Appel du constructeur de GradeDto; ce dto va être validé	
      v_dto = new GradeDto(v_id, v_libelle, v_trigramme);

      // test des valeurs
      assertEquals(v_id, v_dto.getId(), "Champ id incorrect");
      assertEquals(v_libelle, v_dto.get_libelle(), "Champ libelle incorrect");
      assertEquals(v_trigramme, v_dto.get_trigramme(), "Champ trigramme incorrect");

      // Remplissage références du dto
      // Start of user code Remplissage références du dto

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

   }


   /**
    * Validation du DTO.
    * @param p_dto
    *           le DTO à  valider
    */
   private void validate (final GradeDto p_dto)
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
      for (final GradeAttributes_Enum v_attribute : GradeAttributes_Enum.values())
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
