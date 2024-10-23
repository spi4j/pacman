/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
import fr.test.business.api.main.PurchaseOrderAttributesEnum;
import fr.test.business.api.main.ShoppingCartDto;
import fr.test.business.api.main.UserDto;
import fr.test.main.StatusEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test pour le DTO 'PurchaseOrder'.
 * @author safr@n
 */

// Start of user code 8d11490eb7e3bd7fbe31724d2cea61c5
// End of user code
public class PurchaseOrderDtoTest extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
      // génération des champs
      final Long id = getRandomLong();
      final String cardNumber =  getRandomString();
      final Integer totalAmount =  getRandomInteger();
      final ShoppingCartDto shoppingCart = new ShoppingCartDto();
      final UserDto user = new UserDto();
      // instanciation et remplissage du dto
      PurchaseOrderDto dto = new PurchaseOrderDto();
      // validation du DTO à plusieurs étapes
      validate(dto);
      dto.setCardNumber(cardNumber);
      validate(dto);
      dto.setTotalAmount(totalAmount);
      validate(dto);
      dto.setShoppingCart(shoppingCart);
      validate(dto);
      dto.setUser(user);
      validate(dto);

      // Appel du constructeur de PurchaseOrderDto; ce dto va être validé	
      dto = new PurchaseOrderDto(id, cardNumber, totalAmount);

      dto.setShoppingCart(shoppingCart);

      dto.setUser(user);

      // test des valeurs
      
      // Start of user code 7db1b67f9311bee084851e988f91c89d
      assertEquals(id, dto.getId(), "Champ id incorrect");
      // End of user code
      assertEquals(cardNumber, dto.getCardNumber(), "Champ cardNumber incorrect");
      assertEquals(totalAmount, dto.getTotalAmount(), "Champ totalAmount incorrect");
      assertEquals(shoppingCart, dto.getShoppingCart(), "Champ shoppingCart_id incorrect");
      assertEquals(user, dto.getUser(), "Champ user_id incorrect");

      
      // Start of user code 8038d34b3c01b4146a122ab622155077

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
   private void unreference (final PurchaseOrderDto dto)
   {
      dto.setShoppingCart(null);
      assertNull(dto.getShoppingCart(), "Champ shoppingCart_id incorrect");
      dto.setUser(null);
      assertNull(dto.getUser(), "Champ user_id incorrect");
   }

   /**
    * Validation du DTO.
    * @param dto
    *           le DTO à  valider
    */
   private void validate (final PurchaseOrderDto dto)
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
      for (final PurchaseOrderAttributesEnum attribute : PurchaseOrderAttributesEnum.values())
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
