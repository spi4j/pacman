/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
import fr.test.business.api.main.BookDto;
import fr.test.business.api.main.ShoppingCartLineAttributesEnum;
import fr.test.types.enums.StatusEnum;
import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test pour le DTO 'ShoppingCartLine'.
 * @author safr@n
 */
// Annotation for class
// Start of user code 8d11490eb7e3bd7fbe31724d2cea61c5
// End of user code
public class ShoppingCartLineDtoTest extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
      // génération des champs
      final Long id = getRandomLong();
      final Integer quantity =  getRandomInteger();
      final BookDto book = new BookDto();
      // instanciation et remplissage du dto
      ShoppingCartLineDto dto = new ShoppingCartLineDto();
      // validation du DTO à plusieurs étapes
      validate(dto);
      dto.setQuantity(quantity);
      validate(dto);
      dto.setBook(book);
      validate(dto);

      // Appel du constructeur de ShoppingCartLineDto; ce dto va être validé	
      dto = new ShoppingCartLineDto(id, quantity);

      dto.setBook(book);

      // test des valeurs
      // getId
      // Start of user code 7db1b67f9311bee084851e988f91c89d
      assertEquals(id, dto.getId(), "Champ id incorrect");
      // End of user code
      assertEquals(quantity, dto.getQuantity(), "Champ quantity incorrect");
      assertEquals(book, dto.getBook(), "Champ book_id incorrect");

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
   private void unreference (final ShoppingCartLineDto dto)
   {
      dto.setBook(null);
      assertNull(dto.getBook(), "Champ book_id incorrect");
   }

   /**
    * Validation du DTO.
    * @param dto
    *           le DTO à  valider
    */
   private void validate (final ShoppingCartLineDto dto)
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
      for (final ShoppingCartLineAttributesEnum attribute : ShoppingCartLineAttributesEnum.values())
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