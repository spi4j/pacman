/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
import fr.test.business.api.main.ShoppingCartDto;
import fr.test.business.api.main.UserAttributesEnum;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test pour le DTO 'User'.
 * @author safr@n
 */
// Annotation for class
// Start of user code 8d11490eb7e3bd7fbe31724d2cea61c5
// End of user code
public class UserDtoTest extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
      // génération des champs
      final Long id = getRandomLong();
      final String email =  getRandomString();
      final String password =  getRandomString();
      final String name =  getRandomString();
      // instanciation et remplissage du dto
      UserDto dto = new UserDto();
      // validation du DTO à plusieurs étapes
      validate(dto);
      dto.setEmail(email);
      validate(dto);
      dto.setPassword(password);
      validate(dto);
      dto.setName(name);
      validate(dto);

      // Appel du constructeur de UserDto; ce dto va être validé	
      dto = new UserDto(id, email, password, name);

      // test des valeurs
      // getId
      // Start of user code 7db1b67f9311bee084851e988f91c89d
      assertEquals(id, dto.getId(), "Champ id incorrect");
      // End of user code
      assertEquals(email, dto.getEmail(), "Champ email incorrect");
      assertEquals(password, dto.getPassword(), "Champ password incorrect");
      assertEquals(name, dto.getName(), "Champ name incorrect");

      // Remplissage références du dto
      // Start of user code acd8fa0455735d0a30c2fa5ea8e46174

      // End of user code

      // appel des champs calculés

      // appel du toString
      assertNotNull(dto.toString(), "toString incorrect");

      // dernière validation avec DTO valide
      try
      {
         dto.validate();
      }
      catch (final Spi4jValidationException e)
      {
         fail(e.toString());
      }

      // déréferencement
      unreference(dto);
   }


   /**
    * Déréférencement des relations du DTO.
    * @param dto
    *           le DTO
    */
   @SuppressWarnings("unchecked")
   private void unreference (final UserDto dto)
   {
      dto.setCarts(Collections.EMPTY_LIST);
      assertNotNull(dto.getCarts(), "Champ carts_id incorrect");
   }

   /**
    * Validation du DTO.
    * @param dto
    *           le DTO à  valider
    */
   private void validate (final UserDto dto)
   {
      try
      {
         dto.validate();
      }
      catch (final Spi4jValidationException e)
      {
         assertTrue(e.getMessage().startsWith("Champ(s)"), "Message incorrect");
      }
   }

   /**
    * Test de l'énumération des attributs du DTO.
    */
   @Test
   public void testAttributes ()
   {
      for (final UserAttributesEnum attribute : UserAttributesEnum.values())
      {
         assertNotNull(attribute.toString(), "name");
         assertNotNull(attribute.getName(), "name");
         assertNotNull(attribute.getDescription(), "description");
         assertNotNull(attribute.getSize(), "size");
         assertNotNull(attribute.getType(), "type");
         assertNotNull(attribute.isMandatory(), "mandatory");
         // mise en cache
         assertNotNull(attribute.getGetterMethod(), "getter");
         // lecture du cache
         assertNotNull(attribute.getGetterMethod(), "getter");
         // mise en cache (si existant : champ non calculé)
         final java.lang.reflect.Method setterMethod1 = attribute.getSetterMethod();
         // lecture du cache (si existant : champ non calculé)
         final java.lang.reflect.Method setterMethod2 = attribute.getSetterMethod();
         // vérification que les 2 valeurs sont identiques
         assertEquals(setterMethod1, setterMethod2, "setter");
      }
   }
}
