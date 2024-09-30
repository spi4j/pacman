/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;
// Start of user code for imports

import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.dao.Operator_Enum;
import fr.spi4j.persistence.dao.TableCriteria;
import fr.spi4j.persistence.entity.EntityUtil;
import fr.spi4j.tua.BeanTester_Abs;
import fr.spi4j.type.XtopSup;
import fr.test.persistence.TestParamPersistence;
import fr.test.persistence.TestUserPersistence;
import fr.test.types.enums.StatusEnum;
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
 * Classe de test du dao 'BookDao'.
 * @author safr@n
 */
// Annotation for class
// Start of user code 8d11490eb7e3bd7fbe31724d2cea61c5
// End of user code
public class BookDaoTest extends BeanTester_Abs
{

   /** Le 'UserPersistence' de l'application. */
   private static TestUserPersistence userPersistence;

   /** Le 'BookDao' teste. */
   private static BookDao dao;

   /** L'id du 'BookEntity' stocke en base. */
   private static Long crudId;

   /**
    * Definition du crudId.
    * @param crudId
    *           le crudId
    */
   public static void setCrudId (final Long crudId)
   {
      BookDaoTest.crudId = crudId;
   }

   /**
    * Methode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {
      userPersistence = TestParamPersistence.getUserPersistence();
      dao = userPersistence.getBookDao();

      // set up before class
      // Start of user code ac28938f801815a863d275d7089501cb
      // End of user code
   }

   /**
    * Methode d'initialisation de tests.
    */
   @BeforeEach
   public void setUp ()
   {
      userPersistence.begin();

      // set up
      // Start of user code d5158c215f75d40b3dc3a9efd519b60a
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

      final List<BookEntity> all = dao.findAll();
      assertNotNull(EntityUtil.findInCollectionById(all, crudId), 
			"L'entity n'a pas ete trouvee dans la liste de toutes les entitys");

      // findAll assertions
      // Start of user code 8126df93b16b144cf0a284377f8e50e4

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
      final BookEntity entity = userPersistence.getBookEntity();

      // create
      // Start of user code 76ea0bebb3c22822b4f0dd9c9fd021c5
      // TODO renseigner donnees de test
      entity.setTitle("t");
      entity.setPrice(1);
      entity.setImage("t");
      entity.setType("t");
      entity.setAuthorWrote_id(1L);
      // End of user code

	  dao.create(entity);
	  setCrudId(entity.getId());
	  assertNotNull(entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      // create assertions
      // Start of user code 5d4b33eba9a2ed1283895b43feb20430

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
      final BookEntity entity = userPersistence.getBookEntity();

      // create null on mandatory
      // Start of user code 129c3355781c1f2045ff30413a724b1b
      // TODO renseigner donnees de test
      entity.setTitle(null);
      entity.setPrice(null);
      entity.setImage(null);
      entity.setType(null);
      entity.setAuthorWrote_id(1L);
      // End of user code    

      try{
        dao.create(entity);
        setCrudId(entity.getId());
		assertNull(entity.getId(), "L'entity ne devrait pas avoir été créé");
      }
      catch(Spi4jValidationException p_e){
           assertTrue(Boolean.TRUE);
      }
   }

   /**
    * Test de creation de l'entity avec des valeurs nulles.
    * @throws Throwable
    *            si erreur de creation de l'entity.
    */
   @Test
   public void testCreateWithNull () throws Throwable
   {
      final BookEntity entity = userPersistence.getBookEntity();

      // create with null values
      // Start of user code 1b1105a5a157379169900e4528d4a04c
      // TODO renseigner donnees de test
      entity.setTitle("t");
      entity.setPrice(1);
      entity.setImage("t");
      entity.setType(null);
      entity.setAuthorWrote_id(1L);
      // End of user code

	  dao.create(entity);
	  setCrudId(entity.getId());
	  assertNotNull(entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      // create assertions with null values
      // Start of user code fbe5b301ce0337e834e2f651fc9ee2ad

      // End of user code
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

      final BookEntity entity = dao.findById(crudId);
      assertNotNull(entity, "L'entity devrait exister dans le referentiel");
      assertNotNull(entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");
      assertNotNull(entity.toString(), "L'entity creee devrait avoir un toString");

      // findById assertions
      // Start of user code 4a1cff273c0585c92941311c7a4da044

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

      final BookEntity entityInsert = userPersistence.getBookEntity();

      // findAllFieldInserted create
      // Start of user code 46e820cf08b958fef6b9d21521864929
      // TODO renseigner donnees de test
      entityInsert.setTitle("t");
      entityInsert.setPrice(1);
      entityInsert.setImage("t");
      entityInsert.setType("t");
      entityInsert.setAuthorWrote_id(1L);
      // End of user code

      dao.create(entityInsert);
      setCrudId(entityInsert.getId());
      final BookEntity entityRead = dao.findById(crudId);

      // findAllFieldInserted assertions
      // Start of user code 0af673d2359f791ebb6e5cd9a4bf0f69
      HashCodeBuilder hashCodeBuilderEntityInsert = new HashCodeBuilder();

      hashCodeBuilderEntityInsert.append(entityInsert.getTitle());
      hashCodeBuilderEntityInsert.append(entityInsert.getPrice());
      hashCodeBuilderEntityInsert.append(entityInsert.getImage());
      hashCodeBuilderEntityInsert.append(entityInsert.getType());
      hashCodeBuilderEntityInsert.append(entityInsert.getAuthorWrote_id());

      int  hashCodeEntityInsert = hashCodeBuilderEntityInsert.toHashCode();
      HashCodeBuilder hashCodeBuilderEntityRead = new HashCodeBuilder();

      hashCodeBuilderEntityRead.append(entityRead.getTitle());
      hashCodeBuilderEntityRead.append(entityRead.getPrice());
      hashCodeBuilderEntityRead.append(entityRead.getImage());
      hashCodeBuilderEntityRead.append(entityRead.getType());
      hashCodeBuilderEntityRead.append(entityRead.getAuthorWrote_id());

      int  hashCodeEntityRead = hashCodeBuilderEntityRead.toHashCode();
      EqualsBuilder equalsBuilder = new EqualsBuilder();

      equalsBuilder.append(entityInsert.getTitle(), entityRead.getTitle());
      equalsBuilder.append(entityInsert.getPrice(), entityRead.getPrice());
      equalsBuilder.append(entityInsert.getImage(), entityRead.getImage());
      equalsBuilder.append(entityInsert.getType(), entityRead.getType());
      equalsBuilder.append(entityInsert.getAuthorWrote_id(), entityRead.getAuthorWrote_id());

      assertEquals(hashCodeEntityInsert,hashCodeEntityRead);
      assertTrue(equalsBuilder.isEquals());

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

      final List<BookEntity> entitys = dao.findByColumn(BookColumnsEnum.book_id, crudId);
      assertEquals(1, entitys.size(), "Il ne devrait exister qu'une entity");
      final BookEntity entity = entitys.get(0);
      assertNotNull(entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      // findByColumn assertions
      // Start of user code a0ab12084af498e2779669010048e678

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

      final TableCriteria<BookColumnsEnum> table = new TableCriteria<>(
               "Test 'find by criteria' en cherchant sur l'id");
      table.addCriteria(BookColumnsEnum.book_id, Operator_Enum.equals, crudId);
      table.addOrderByDesc(BookColumnsEnum.book_id);
      final List<BookEntity> entitys = dao.findByCriteria(table);
      assertEquals(1, entitys.size(), "Il ne devrait exister qu'une entity");
      final BookEntity entity = entitys.get(0);
      assertNotNull(entity.getId(), "L'entity creee devrait avoir une cle primaire renseignee");

      // findByCriteria assertions
      // Start of user code f13d2cae116e868a852756539a403284

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

      final BookEntity entity = dao.findById(crudId);

      // update
      // Start of user code 3ac340832f29c11538fbe2d6f75e8bcc

      // End of user code

      dao.update(entity);

      assertNotNull(entity.getId(), "L'entity mise a jour devrait avoir une cle primaire renseignee");

      // update assertions
      // Start of user code 42918cc8d5b612126ea234102c116b19

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

      final BookEntity entity = dao.findById(crudId);

      dao.delete(entity);
      final List<BookEntity> all = dao.findAll();
      assertNull(EntityUtil.findInCollectionById(all, crudId), 
			"L'entity ne devrait plus exister dans la liste de toutes les entitys");

      // delete assertions
      // Start of user code e466b9a2682dac21a5f1f61a381478bf

      // End of user code
   }

   /**
    * Test de l'enumeration des colonnes de l'entity.
    */
   @Test
   public void testColumns ()
   {
      for (final BookColumnsEnum column : BookColumnsEnum.values())
      {
         assertNotNull(column.toString(), "name");
         assertNotNull(column.getLogicalColumnName(), "logicalColumnName");
         assertNotNull(column.getCompletePhysicalName(), "completePhysicalName");
         assertNotNull(column.getSize(), "size");
         assertNotNull(column.getTypeColumn(), "typeColumn");
         assertNotNull(column.isMandatory(), "mandatory");
         assertNotNull(column.isId(), "id");
      }
   }

   /**
    * Test de validation d'une entity.
    */
   @Test
   public void testValidate ()
   {
      // generation des champs
      final String title = getRandomString();
      final Integer price = getRandomInteger();
      final String image = getRandomString();
      final String type = getRandomString();
      final Long authorWrote = getRandomLong();

      final BookEntity entity = userPersistence.getBookEntity();
      validate(entity);
      entity.setTitle(title);
      validate(entity);
      entity.setPrice(price);
      validate(entity);
      entity.setImage(image);
      validate(entity);
      entity.setType(type);
      validate(entity);
      entity.setAuthorWrote_id(authorWrote);
      validate(entity);

      // test validate
      // Start of user code c9f326acc4088b79803774041e895edc

      // End of user code

      // derniere validation avec entity valide
      try
      {
         entity.validate();
      }
      catch (final Spi4jValidationException e)
      {
         fail(e.toString());
      }
   }

   /**
    * Validation de l'entity.
    * @param entity
    *           l'entity a valider
    */
   private void validate (final BookEntity entity)
   {
      try
      {
         entity.validate();
      }
      catch (final Spi4jValidationException e)
      {
         assertTrue(e.getMessage().startsWith("Champ(s)"), "Message incorrect");
      }
   }

   /**
    * Methode de fin de test : rollback.
    */
   @AfterEach
   public void tearDown ()
   {
      userPersistence.rollback();

      // tear down
      // Start of user code b778e9a7588fa49250428a599cf59f97
      // End of user code
   }


   // specific service test
   // Start of user code 83c9cfe79a629107e128f8bc9ca9ba12

   // End of user code

}
