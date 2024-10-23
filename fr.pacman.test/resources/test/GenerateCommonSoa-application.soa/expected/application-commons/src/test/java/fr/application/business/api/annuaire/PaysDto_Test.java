/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test pour le DTO 'Pays'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class PaysDto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
      // génération des champs
      final Long v_id = getRandomLong();
      final String v_nom =  getRandomString();
      final String v_capitale =  getRandomString();
      final Long v_personnePays = getRandomLong();
      // instanciation et remplissage du dto
      PaysDto v_dto = new PaysDto();
      // validation du DTO à plusieurs étapes
      validate(v_dto);
      v_dto.set_nom(v_nom);
      validate(v_dto);
      v_dto.set_capitale(v_capitale);
      validate(v_dto);
      v_dto.set_personnePays_id(v_personnePays);
      validate(v_dto);

      // Appel du constructeur de PaysDto, avec les dtos référençants valant null, pour valider le constructeur  	
      v_dto = new PaysDto(v_id, v_nom, v_capitale, (PersonneDto) null);
      // Appel du constructeur de PaysDto, avec des dtos référençants, pour valider le constructeur  	
      v_dto = new PaysDto(v_id, v_nom, v_capitale, new PersonneDto());
      // Appel du constructeur de PaysDto, avec identifiants pour les dtos référençants; ce dto va être validé	
      v_dto = new PaysDto(v_id, v_nom, v_capitale, v_personnePays);

      // test des valeurs
      assertEquals(v_id, v_dto.getId(), "Champ id incorrect");
      assertEquals(v_nom, v_dto.get_nom(), "Champ nom incorrect");
      assertEquals(v_capitale, v_dto.get_capitale(), "Champ capitale incorrect");
      assertEquals(v_personnePays, v_dto.get_personnePays_id(), "Champ pays_id incorrect");

      
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

   }


   /**
    * Validation du DTO.
    * @param p_dto
    *           le DTO à  valider
    */
   private void validate (final PaysDto p_dto)
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
      for (final PaysAttributes_Enum v_attribute : PaysAttributes_Enum.values())
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
