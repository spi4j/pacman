/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.business.api.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
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
 * Classe de test du service 'CompetenceService_Itf'.
 * @author safr@n
 */

// Start of user code Annotation for class
// End of user code
public class CompetenceService_Test  extends BeanTester_Abs
{ 
   /** Le 'UserPersistence' de l'application. */
   private static ApplicationUserPersistence userPersistence;

   /** Le 'CompetenceService_Itf' testé. */
   private static CompetenceService_Itf service;

   /** L'id du 'CompetenceDto' stocké en base. */
   private static Long crudId;

   /**
    * Définition du crudId.
    * @param p_crudId
    *           le crudId
    */
   public static void setCrudId (final Long p_crudId)
   {
      CompetenceService_Test.crudId = p_crudId;
   }

   /**
    * Méthode d'initialisation de la classe de tests.
    */
   @BeforeAll
   public static void setUpBeforeClass ()
   {  
      Parameters.setH2Database();
      userPersistence = ApplicationParamPersistence.getUserPersistence ();
      service = ApplicationUserBusiness.getCompetenceService ();

      
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

      final List<CompetenceDto> v_all = service.findAll ();
      assertNotNull(DtoUtil.findInCollectionById (v_all, crudId), 
			"Le dto n'a pas été trouvé dans la liste de tous les dto");

      
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
      final CompetenceDto v_CompetenceDto = new CompetenceDto ();

      
      // Start of user code create
      // TODO renseigner données de test
      v_CompetenceDto.set_libelle ("Str");
      v_CompetenceDto.set_typecompetence ( TypeCompetence_Enum.valueOf ("TYPECOMP1") );

      /* ajout d un dto pour la référence Dispose */
      final PersonneService_Itf  v_PersonneServiceDispose;
      v_PersonneServiceDispose = ApplicationUserBusiness.getPersonneService ();
      final PersonneDto v_PersonneDtoDispose = new PersonneDto ();
      v_PersonneDtoDispose.set_nom ("Str");
      v_PersonneDtoDispose.set_prenom ("Str");
      v_PersonneDtoDispose.set_civil (false);
      v_PersonneDtoDispose.set_dateNaissance (new Date());
      v_PersonneDtoDispose.set_salaire (2D);
      final PersonneDto v_createdPersonneDtoDispose = v_PersonneServiceDispose.save (v_PersonneDtoDispose);
      final List <PersonneDto> v_listPersonneDtoDispose = new ArrayList <PersonneDto> ();
      v_listPersonneDtoDispose.add(v_createdPersonneDtoDispose);
      v_CompetenceDto.setDispose(v_listPersonneDtoDispose);

      // End of user code

      final CompetenceDto v_createdCompetenceDto = service.save (v_CompetenceDto);

      setCrudId (v_createdCompetenceDto.getId ());

      assertNotNull (v_createdCompetenceDto.getId (), "Le dto créé devrait avoir une clé primaire renseignée");

      
      // Start of user code create assertions


      /* test de la bonne insertion de la référence Dispose */
      final List <PersonneDto> v_listPersonneDtoDisposeRead = service.findListDisposeByCompetence (crudId);
      assertTrue(!v_listPersonneDtoDisposeRead.isEmpty(), "La relation Dispose a bien été créée");

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

      final CompetenceDto v_dto = service.findById (crudId);
      assertNotNull (v_dto, "Le dto devrait exister dans le référentiel");
      assertNotNull (v_dto.getId (), "Le dto créé devrait avoir une clé primaire renseignée");
      assertNotNull (v_dto.toString (), "Le dto créé devrait avoir un toString");

      
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

      final CompetenceDto v_dto = service.findById (crudId);

      
      // Start of user code update

      // End of user code

      final CompetenceDto v_updatedDto = service.save (v_dto);

      assertNotNull (v_updatedDto.getId (), "Le dto mis à jour devrait avoir une clé primaire renseignée");

      
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

      final CompetenceDto v_dto = service.findById (crudId);

      service.delete(v_dto);
      final List<CompetenceDto> v_all = service.findAll ();
      assertNull (DtoUtil.findInCollectionById (v_all, crudId), 
			"Le dto ne devrait plus exister dans la liste de tous les dto");

      
      // Start of user code delete assertions

      // End of user code
   }

   /**
    * Test de la méthode 'getColumn'.
    */
   @Test
   public void testGetColumn ()
   {
      ApplicationUserMatching.getCompetenceMatch ().getColumn (CompetenceAttributes_Enum.id);
      
      // Start of user code getColumn
      ApplicationUserMatching.getCompetenceMatch ().getColumn (CompetenceAttributes_Enum.libelle);
      // End of user code
   }

   /**
    * Méthode de fin de test : rollback.
    */
   @AfterEach
   public void tearDown ()
   {
      userPersistence.rollback ();

      
      // Start of user code tear down
      // End of user code
   }

   
   // Start of user code specific service test

   // End of user code

}
