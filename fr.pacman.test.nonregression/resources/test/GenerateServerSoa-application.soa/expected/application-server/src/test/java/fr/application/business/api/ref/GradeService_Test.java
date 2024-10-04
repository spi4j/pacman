/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.ref;
// Start of user code for imports

import fr.application.business.ApplicationUserBusiness;
import fr.application.matching.ApplicationUserMatching;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.spi4j.Parameters;
import fr.spi4j.business.dto.DtoUtil;
import fr.spi4j.tua.BeanTester_Abs;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe de test du service 'GradeService_Itf'.
 * @author safr@n
 */
// Annotation for class
// Start of user code Annotation for class
// End of user code
public class GradeService_Test  extends BeanTester_Abs
{ 
   /** Le 'UserPersistence' de l'application. */
   private static ApplicationUserPersistence userPersistence;

   /** Le 'GradeService_Itf' testé. */
   private static GradeService_Itf service;

   /** L'id du 'GradeDto' stocké en base. */
   private static Long crudId;

   /**
    * Définition du crudId.
    * @param p_crudId
    *           le crudId
    */
   public static void setCrudId (final Long p_crudId)
   {
      GradeService_Test.crudId = p_crudId;
   }

   /**
    * Méthode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {  
      Parameters.setH2Database();
      userPersistence = ApplicationParamPersistence.getUserPersistence ();
      service = ApplicationUserBusiness.getGradeService ();

      // set up before class
      // Start of user code set up before class
      // End of user code
   }

   /**
    * Méthode d'initialisation de tests.
    */
   @BeforeEach
   public void setUp ()
   {
	  userPersistence.begin ();

      // set up
      // Start of user code set up
      // End of user code
   }

   /**
    * Test de recherche de toutes les entitys.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testFindAll () throws Throwable
   {
      testCreate ();

      final List<GradeDto> v_all = service.findAll ();
      assertNotNull(DtoUtil.findInCollectionById (v_all, crudId), 
			"Le dto n'a pas été trouvé dans la liste de tous les dto");

      // findAll assertions
      // Start of user code findAll assertions

      // End of user code
   }

   /**
    * Test de création de l'entity.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testCreate () throws Throwable
   {
      final GradeDto v_GradeDto = new GradeDto ();

      // create
      // Start of user code create
      // TODO renseigner données de test
      v_GradeDto.set_libelle ("Str");
      v_GradeDto.set_trigramme ("Str");

      // End of user code

      final GradeDto v_createdGradeDto = service.save (v_GradeDto);

      setCrudId (v_createdGradeDto.getId ());

      assertNotNull (v_createdGradeDto.getId (), "Le dto créé devrait avoir une clé primaire renseignée");

      // create assertions
      // Start of user code create assertions


      // End of user code
   }

   /**
    * Test de recherche par identifiant de l'entity.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testFindById () throws Throwable
   {
      testCreate ();

      final GradeDto v_dto = service.findById (crudId);
      assertNotNull (v_dto, "Le dto devrait exister dans le référentiel");
      assertNotNull (v_dto.getId (), "Le dto créé devrait avoir une clé primaire renseignée");
      assertNotNull (v_dto.toString (), "Le dto créé devrait avoir un toString");

      // findById assertions
      // Start of user code findById assertions

      // End of user code
   }

   /**
    * Test de mise à jour de l'entity.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testUpdate () throws Throwable
   {
      testCreate ();

      final GradeDto v_dto = service.findById (crudId);

      // update
      // Start of user code update

      // End of user code

      final GradeDto v_updatedDto = service.save (v_dto);

      assertNotNull (v_updatedDto.getId (), "Le dto mis à jour devrait avoir une clé primaire renseignée");

      // update assertions
      // Start of user code update assertions

      // End of user code
   }

   /**
    * Test de suppression de l'entity.
    * @throws Throwable
    *            exception
    */
   @Test
   public void testDelete () throws Throwable
   {
      testCreate ();

      final GradeDto v_dto = service.findById (crudId);

      service.delete(v_dto);
      final List<GradeDto> v_all = service.findAll ();
      assertNull (DtoUtil.findInCollectionById (v_all, crudId), 
			"Le dto ne devrait plus exister dans la liste de tous les dto");

      // delete assertions
      // Start of user code delete assertions

      // End of user code
   }

   /**
    * Test de la méthode 'getColumn'.
    */
   @Test
   public void testGetColumn ()
   {
      ApplicationUserMatching.getGradeMatch ().getColumn (GradeAttributes_Enum.id);
      // getColumn
      // Start of user code getColumn
      ApplicationUserMatching.getGradeMatch ().getColumn (GradeAttributes_Enum.libelle);
      // End of user code
   }

   /**
    * Méthode de fin de test : rollback.
    */
   @AfterEach
   public void tearDown ()
   {
      userPersistence.rollback ();

      // tear down
      // Start of user code tear down
      // End of user code
   }

   // specific service test
   // Start of user code specific service test

   // End of user code

}
