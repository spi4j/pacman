/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.AdresseDto;
import fr.application.business.api.annuaire.CompetenceDto;
import fr.application.business.api.annuaire.PaysDto;
import fr.application.business.api.annuaire.PersonneAttributes_Enum;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.business.api.ref.GradeDto;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.tua.BeanTester_Abs;
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
 * Classe de test pour le DTO 'Personne'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class PersonneDto_Test extends BeanTester_Abs
{

   /**
    * Test des attributs.
    */
   @Test
   public void testDto ()
   {
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
      // instanciation et remplissage du dto
      PersonneDto v_dto = new PersonneDto();
      // validation du DTO à plusieurs étapes
      validate(v_dto);
      v_dto.set_nom(v_nom);
      validate(v_dto);
      v_dto.set_prenom(v_prenom);
      validate(v_dto);
      v_dto.set_civil(v_civil);
      validate(v_dto);
      v_dto.set_dateNaissance(v_dateNaissance);
      validate(v_dto);
      v_dto.set_salaire(v_salaire);
      validate(v_dto);
      v_dto.set_grade_id(v_grade);
      validate(v_dto);
      v_dto.set_marieAvec_id(v_marieAvec);
      validate(v_dto);
      v_dto.set_personneParentDe_id(v_personneParentDe);
      validate(v_dto);

      // Appel du constructeur de PersonneDto, avec des dtos référençants, pour valider le constructeur  	
      v_dto = new PersonneDto(v_id, v_nom, v_prenom, v_civil, v_dateNaissance, v_salaire, (GradeDto) null, (PersonneDto) null, new PersonneDto());
      // Appel du constructeur de PersonneDto, avec identifiants pour les dtos référençants; ce dto va être validé	
      v_dto = new PersonneDto(v_id, v_nom, v_prenom, v_civil, v_dateNaissance, v_salaire, v_grade, v_marieAvec, v_personneParentDe);

      // test des valeurs
      assertEquals(v_id, v_dto.getId(), "Champ id incorrect");
      assertEquals(v_nom, v_dto.get_nom(), "Champ nom incorrect");
      assertEquals(v_prenom, v_dto.get_prenom(), "Champ prenom incorrect");
      assertEquals(v_civil, v_dto.get_civil(), "Champ civil incorrect");
      assertEquals(v_dateNaissance, v_dto.get_dateNaissance(), "Champ dateNaissance incorrect");
      assertEquals(v_salaire, v_dto.get_salaire(), "Champ salaire incorrect");
      assertEquals(v_grade, v_dto.get_grade_id(), "Champ grade_id incorrect");
      assertEquals(v_marieAvec, v_dto.get_marieAvec_id(), "Champ marieAvec_id incorrect");
      assertEquals(v_personneParentDe, v_dto.get_personneParentDe_id(), "Champ parentDe_id incorrect");

      
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
   private void unreference (final PersonneDto p_dto)
   {
      p_dto.set_grade(null);
      assertNull(p_dto.get_grade(), "Champ grade_id incorrect");
      p_dto.set_marieAvec(null);
      assertNull(p_dto.get_marieAvec(), "Champ marieAvec_id incorrect");
      p_dto.setAdresses(Collections.EMPTY_LIST);
      assertNotNull(p_dto.get_tab_adresses(), "Champ adresses_id incorrect");
      p_dto.setParentDe(Collections.EMPTY_LIST);
      assertNotNull(p_dto.get_tab_parentDe(), "Champ parentDe_id incorrect");
      p_dto.setPays(Collections.EMPTY_LIST);
      assertNotNull(p_dto.get_tab_pays(), "Champ pays_id incorrect");
   }

   /**
    * Validation du DTO.
    * @param p_dto
    *           le DTO à  valider
    */
   private void validate (final PersonneDto p_dto)
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
      for (final PersonneAttributes_Enum v_attribute : PersonneAttributes_Enum.values())
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
