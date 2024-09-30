/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
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
 * Classe de test pour le DTO 'Adresse'.
 * @author safr@n
 */
// Annotation for class
// Start of user code Annotation for class
// End of user code
public class AdresseDto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
      // génération des champs
      final Long v_id = getRandomLong();
      final String v_rue =  getRandomString();
      final String v_ville =  getRandomString();
      final String v_codePostal =  getRandomString(AdresseAttributes_Enum.codePostal.getSize());
      final Long v_personneAdresses = getRandomLong();
      // instanciation et remplissage du dto
      AdresseDto v_dto = new AdresseDto();
      // validation du DTO à plusieurs étapes
      validate(v_dto);
      v_dto.set_rue(v_rue);
      validate(v_dto);
      v_dto.set_ville(v_ville);
      validate(v_dto);
      v_dto.set_codePostal(v_codePostal);
      validate(v_dto);
      v_dto.set_personneAdresses_id(v_personneAdresses);
      validate(v_dto);

      // Appel du constructeur de AdresseDto, avec les dtos référençants valant null, pour valider le constructeur  	
      v_dto = new AdresseDto(v_id, v_rue, v_ville, v_codePostal, (PersonneDto) null);
      // Appel du constructeur de AdresseDto, avec des dtos référençants, pour valider le constructeur  	
      v_dto = new AdresseDto(v_id, v_rue, v_ville, v_codePostal, new PersonneDto());
      // Appel du constructeur de AdresseDto, avec identifiants pour les dtos référençants; ce dto va être validé	
      v_dto = new AdresseDto(v_id, v_rue, v_ville, v_codePostal, v_personneAdresses);

      // test des valeurs
      assertEquals(v_id, v_dto.getId(), "Champ id incorrect");
      assertEquals(v_rue, v_dto.get_rue(), "Champ rue incorrect");
      assertEquals(v_ville, v_dto.get_ville(), "Champ ville incorrect");
      assertEquals(v_codePostal, v_dto.get_codePostal(), "Champ codePostal incorrect");
      assertEquals(v_personneAdresses, v_dto.get_personneAdresses_id(), "Champ adresses_id incorrect");

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
   private void validate (final AdresseDto p_dto)
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
      for (final AdresseAttributes_Enum v_attribute : AdresseAttributes_Enum.values())
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
