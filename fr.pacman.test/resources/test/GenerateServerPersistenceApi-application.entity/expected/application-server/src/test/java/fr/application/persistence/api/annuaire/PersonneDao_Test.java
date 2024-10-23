/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.dao.Operator_Enum;
import fr.spi4j.persistence.dao.TableCriteria;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.spi4j.tua.BeanTester_Abs;
import fr.spi4j.type.XtopSup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test du dao 'PersonneDao_Itf'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class PersonneDao_Test extends BeanTester_Abs
{

   /** Le 'UserPersistence' de l'application. */
   private static ApplicationUserPersistence userPersistence;

   /** Le 'PersonneDao_Itf' teste. */
   private static PersonneDao_Itf dao;

   /** L'id du 'PersonneEntity_Itf' stocke en base. */
   private static Long crudId;

   /**
    * Definition du crudId.
    * @param p_crudId
    *           le crudId
    */
   public static void setCrudId (final Long p_crudId)
   {
      PersonneDao_Test.crudId = p_crudId;
   }

   /**
    * Methode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {
      userPersistence = ApplicationParamPersistence.getUserPersistence();
      dao = userPersistence.getPersonneDao();

      
      // Start of user code set up before class
      // End of user code
   }

   /**
    * Methode d'initialisation de tests.
    */
   @BeforeEach
   public void setUp ()
   {
      userPersistence.begin();

      
      // Start of user code set up
      // End of user code
   }

   /**
    * Test de recherche de toutes les entitys.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testFindAll () throws Throwable
   {
      testCreate();

      final List<PersonneEntity_Itf> v_all = dao.findAll();
      assertNotNull(EntityUtil.findInCollectionById(v_all, crudId), 
			"L'entity n'a pas ete trouvee dans la liste de toutes les entitys");

      
      // Start of user code findAll assertions

      // End of user code
   }

   /**
    * Test de creation de l'entity.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testCreate () throws Throwable
   {
      final PersonneEntity_Itf v_entity = userPersistence.getPersonneEntity();

      
      // Start of user code create
      // TODO renseigner donnees de test
      v_entity.set_nom("t");
      v_entity.set_prenom("t");
      v_entity.set_civil(false);
      v_entity.set_dateNaissance(new Date());
      v_entity.set_salaire(1D);
      v_entity.set_xdmaj(new Date());
      v_entity.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
      v_entity.set_Grade_id(1L);
      v_entity.set_MarieAvec_id(1L);
      v_entity.set_personneParentDe_id(1L);
      // End of user code

	  dao.create(v_entity);
	  setCrudId(v_entity.getId());
	  assertNotNull(v_entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      
      // Start of user code create assertions

      // End of user code
   }

   /**
    * Test de (non) creation de l'entity avec 
    * des valeurs nulles sur des champs obligatoires.
    * @throws Throwable
    *            si erreur .
    */
   @Test
   public void testCreateWithNullOnMandatoryFields () throws Throwable
   {
      final PersonneEntity_Itf v_entity = userPersistence.getPersonneEntity();

      
      // Start of user code create null on mandatory
      // TODO renseigner donnees de test
      v_entity.set_nom(null);
      v_entity.set_prenom(null);
      v_entity.set_civil(null);
      v_entity.set_dateNaissance(null);
      v_entity.set_salaire(null);
      v_entity.set_xdmaj(new Date());
      v_entity.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
      v_entity.set_Grade_id(1L);
      v_entity.set_MarieAvec_id(1L);
      v_entity.set_personneParentDe_id(1L);
      // End of user code    

      try{
        dao.create(v_entity);
        setCrudId(v_entity.getId());
		assertNull(v_entity.getId(), "L'entity ne devrait pas avoir été créé");
      }
      catch(Spi4jValidationException p_e){
           assertTrue(Boolean.TRUE);
      }
   }

   /**
    * Test de recherche par identifiant de l'entity.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testFindById () throws Throwable
   {
      testCreate();

      final PersonneEntity_Itf v_entity = dao.findById(crudId);
      assertNotNull(v_entity, "L'entity devrait exister dans le referentiel");
      assertNotNull(v_entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");
      assertNotNull(v_entity.toString(), "L'entity creee devrait avoir un toString");

      
      // Start of user code findById assertions

      // End of user code
   }

   /**
    * Test d egalite entre les champs de l entite pre et post insertion.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testAllFieldInserted () throws Throwable
   {

      final PersonneEntity_Itf v_entityInsert = userPersistence.getPersonneEntity();

      
      // Start of user code findAllFieldInserted create
      // TODO renseigner donnees de test
      v_entityInsert.set_nom("t");
      v_entityInsert.set_prenom("t");
      v_entityInsert.set_civil(false);
      v_entityInsert.set_dateNaissance(new Date());
      v_entityInsert.set_salaire(1D);
      v_entityInsert.set_xdmaj(new Date());
      v_entityInsert.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
      v_entityInsert.set_Grade_id(1L);
      v_entityInsert.set_MarieAvec_id(1L);
      v_entityInsert.set_personneParentDe_id(1L);
      // End of user code

      dao.create(v_entityInsert);
      setCrudId(v_entityInsert.getId());
      final PersonneEntity_Itf v_entityRead = dao.findById(crudId);

      
      // Start of user code findAllFieldInserted assertions
      HashCodeBuilder v_hashCodeBuilderEntityInsert = new HashCodeBuilder();

      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_nom());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_prenom());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_civil());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_dateNaissance());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_salaire());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_xdmaj());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_xtopsup().get_value());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_Grade_id());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_MarieAvec_id());
      v_hashCodeBuilderEntityInsert.append(v_entityInsert.get_personneParentDe_id());

      int  v_hashCodeEntityInsert = v_hashCodeBuilderEntityInsert.toHashCode();
      HashCodeBuilder v_hashCodeBuilderEntityRead = new HashCodeBuilder();

      v_hashCodeBuilderEntityRead.append(v_entityRead.get_nom());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_prenom());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_civil());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_dateNaissance());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_salaire());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_xdmaj());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_xtopsup().get_value());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_Grade_id());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_MarieAvec_id());
      v_hashCodeBuilderEntityRead.append(v_entityRead.get_personneParentDe_id());

      int  v_hashCodeEntityRead = v_hashCodeBuilderEntityRead.toHashCode();
      EqualsBuilder v_equalsBuilder = new EqualsBuilder();

      v_equalsBuilder.append(v_entityInsert.get_nom(), v_entityRead.get_nom());
      v_equalsBuilder.append(v_entityInsert.get_prenom(), v_entityRead.get_prenom());
      v_equalsBuilder.append(v_entityInsert.get_civil(), v_entityRead.get_civil());
      v_equalsBuilder.append(v_entityInsert.get_dateNaissance(), v_entityRead.get_dateNaissance());
      v_equalsBuilder.append(v_entityInsert.get_salaire(), v_entityRead.get_salaire());
      v_equalsBuilder.append(v_entityInsert.get_xdmaj(), v_entityRead.get_xdmaj());
      v_equalsBuilder.append(v_entityInsert.get_xtopsup().get_value(), v_entityRead.get_xtopsup().get_value());
      v_equalsBuilder.append(v_entityInsert.get_Grade_id(), v_entityRead.get_Grade_id());
      v_equalsBuilder.append(v_entityInsert.get_MarieAvec_id(), v_entityRead.get_MarieAvec_id());
      v_equalsBuilder.append(v_entityInsert.get_personneParentDe_id(), v_entityRead.get_personneParentDe_id());

      assertEquals(v_hashCodeEntityInsert,v_hashCodeEntityRead);
      assertTrue(v_equalsBuilder.isEquals());

      // End of user code
   }

   /**
    * Test de recherche par colonne.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testFindByColumn () throws Throwable
   {
      testCreate();

      final List<PersonneEntity_Itf> v_entitys = dao.findByColumn(PersonneColumns_Enum.personne_id, crudId);
      assertEquals(1, v_entitys.size(), "Il ne devrait exister qu'une entity");
      final PersonneEntity_Itf v_entity = v_entitys.get(0);
      assertNotNull(v_entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      
      // Start of user code findByColumn assertions

      // End of user code
   }

   /**
    * Test de recherche par critere.
    * @throws Throwable
    *            si erreur de recherche de l'entity.
    */
   @Test
   public void testFindByCriteria () throws Throwable
   {
      testCreate();

      final TableCriteria<PersonneColumns_Enum> v_table = new TableCriteria<>(
               "Test 'find by criteria' en cherchant sur l'id");
      v_table.addCriteria(PersonneColumns_Enum.personne_id, Operator_Enum.equals, crudId);
      v_table.addOrderByDesc(PersonneColumns_Enum.personne_id);
      final List<PersonneEntity_Itf> v_entitys = dao.findByCriteria(v_table);
      assertEquals(1, v_entitys.size(), "Il ne devrait exister qu'une entity");
      final PersonneEntity_Itf v_entity = v_entitys.get(0);
      assertNotNull(v_entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      
      // Start of user code findByCriteria assertions

      // End of user code
   }

   /**
    * Test de mise a jour de l'entity.
    * @throws Throwable
    *            si erreur de mise a jour de l'entity.
    */
   @Test
   public void testUpdate () throws Throwable
   {
      testCreate();

      final PersonneEntity_Itf v_entity = dao.findById(crudId);

      
      // Start of user code update

      // End of user code

      dao.update(v_entity);

      assertNotNull(v_entity.getId(), "L'entity mise a jour devrait avoir une cle primaire renseignee");

      
      // Start of user code update assertions

      // End of user code
   }

   /**
    * Test de suppression de l'entity.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testDelete () throws Throwable
   {
      testCreate();

      final PersonneEntity_Itf v_entity = dao.findById(crudId);

      dao.delete(v_entity);
      final List<PersonneEntity_Itf> v_all = dao.findAll();
      assertNull(EntityUtil.findInCollectionById(v_all, crudId), 
			"L'entity ne devrait plus exister dans la liste de toutes les entitys");

      
      // Start of user code delete assertions

      // End of user code
   }

   /**
    * Test de l'enumeration des colonnes de l'entity.
    */
   @Test
   public void testColumns ()
   {
      for (final PersonneColumns_Enum v_column : PersonneColumns_Enum.values())
      {
         assertNotNull(v_column.toString(), "name");
         assertNotNull(v_column.getLogicalColumnName(), "logicalColumnName");
         assertNotNull(v_column.getCompletePhysicalName(), "completePhysicalName");
         assertNotNull(v_column.getSize(), "size");
         assertNotNull(v_column.getTypeColumn(), "typeColumn");
         assertNotNull(v_column.isMandatory(), "mandatory");
         assertNotNull(v_column.isId(), "id");
      }
   }

   /**
    * Test de validation d'une entity.
    */
   @Test
   public void testValidate ()
   {
      // generation des champs
      final Date v_xdmaj = getRandomDate();
      final XtopSup v_xtopsup = getRandomXtopSup();
      final String v_nom = getRandomString(PersonneColumns_Enum.nom.getSize());
      final String v_prenom = getRandomString();
      final Boolean v_civil = getRandomBoolean();
      final Date v_dateNaissance = getRandomDate();
      final Double v_salaire = getRandomDouble();
      final Long v_Grade_id = getRandomLong();
      final Long v_MarieAvec_id = getRandomLong();
      final Long v_personneParentDe = getRandomLong();

      final PersonneEntity_Itf v_entity = userPersistence.getPersonneEntity();
      validate(v_entity);
      v_entity.set_xdmaj(v_xdmaj);
      validate(v_entity);
      v_entity.set_xtopsup(v_xtopsup);
      validate(v_entity);
      v_entity.set_nom(v_nom);
      validate(v_entity);
      v_entity.set_prenom(v_prenom);
      validate(v_entity);
      v_entity.set_civil(v_civil);
      validate(v_entity);
      v_entity.set_dateNaissance(v_dateNaissance);
      validate(v_entity);
      v_entity.set_salaire(v_salaire);
      validate(v_entity);
      v_entity.set_Grade_id(v_Grade_id);
      validate(v_entity);
      v_entity.set_MarieAvec_id(v_MarieAvec_id);
      validate(v_entity);
      v_entity.set_personneParentDe_id(v_personneParentDe);
      validate(v_entity);

      
      // Start of user code test validate

      // End of user code

      // derniere validation avec entity valide
      try
      {
         v_entity.validate();
      }
      catch (final Spi4jValidationException v_e)
      {
         fail(v_e.toString());
      }
   }

   /**
    * Validation de l'entity.
    * @param p_entity
    *           l'entity a valider
    */
   private void validate (final PersonneEntity_Itf p_entity)
   {
      try
      {
         p_entity.validate();
      }
      catch (final Spi4jValidationException v_e)
      {
         assertTrue(v_e.getMessage().startsWith("Champ(s)"), "Message incorrect");
      }
   }

   /**
    * Methode de fin de test : rollback.
    */
   @AfterEach
   public void tearDown ()
   {
      userPersistence.rollback();

      
      // Start of user code tear down
      // End of user code
   }


   
   // Start of user code specific service test

   // End of user code

}
