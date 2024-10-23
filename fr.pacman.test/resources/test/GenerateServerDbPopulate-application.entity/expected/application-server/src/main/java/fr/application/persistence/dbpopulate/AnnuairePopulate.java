/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.dbpopulate;

// Start of user code for imports

import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;

import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.api.annuaire.CompetenceDao_Itf;
import fr.application.persistence.api.annuaire.CompetenceEntity_Itf;
import fr.application.persistence.api.annuaire.PersonneDao_Itf;
import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.application.persistence.api.annuaire.AdresseDao_Itf;
import fr.application.persistence.api.annuaire.AdresseEntity_Itf;
import fr.application.persistence.api.annuaire.PaysDao_Itf;
import fr.application.persistence.api.annuaire.PaysEntity_Itf;
import fr.application.persistence.api.annuaire.AdresseColumns_Enum;
import fr.application.persistence.api.annuaire.CompetenceColumns_Enum;
import fr.application.persistence.api.ref.GradeColumns_Enum;
import fr.application.persistence.api.ref.GradeDao_Itf;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.application.persistence.api.annuaire.PaysColumns_Enum;
import fr.application.persistence.api.annuaire.PersonneColumns_Enum;
import fr.spi4j.type.XtopSup;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.ParamPersistence_Abs;
import fr.spi4j.persistence.dbpopulate.RepartitionPopulateData;

// End of user code

/**
 * Permet d'effectuer le remplissage du Namespace 'annuaire'.
 */
public class AnnuairePopulate
{

   /**
    * Executable.
    * @param p_args
    *           arguments
    * @throws Throwable
    *            exception
    */
   public static void main (final String[] p_args) throws Throwable
   {
      // possibilité de faire un insert en précisant l'id de l'entité
      ParamPersistence_Abs.enableInsertWithId(true);
      ApplicationParamPersistence.getUserPersistence().begin();

      final int v_nbMaxCompetence = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Competence
      final int v_nbMaxPersonne = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Personne
      final int v_nbMaxAdresse = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Adresse
      final int v_nbMaxPays = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Pays

      initCompetence(v_nbMaxCompetence);
      initPersonne(v_nbMaxPersonne);
      initAdresse(v_nbMaxAdresse);
      initPays(v_nbMaxPays);

      ApplicationParamPersistence.getUserPersistence().commit();
      ApplicationParamPersistence.getUserPersistence().begin();

      final AdresseDao_Itf v_AdresseDao = ApplicationParamPersistence.getUserPersistence().getAdresseDao();
      final CompetenceDao_Itf v_CompetenceDao = ApplicationParamPersistence.getUserPersistence().getCompetenceDao();
      final GradeDao_Itf v_GradeDao = ApplicationParamPersistence.getUserPersistence().getGradeDao();
      final PaysDao_Itf v_PaysDao = ApplicationParamPersistence.getUserPersistence().getPaysDao();
      final PersonneDao_Itf v_PersonneDao = ApplicationParamPersistence.getUserPersistence().getPersonneDao();

      // Dispose
      RepartitionPopulateData.repartition_0N_0N(
         -1, -1, -1, -1, v_CompetenceDao, v_CompetenceDao, CompetenceColumns_Enum.c_tableNameCompetenceDispose, "COMPETENCE_ID", "PERSONNE_ID", false);
      // Grade
      RepartitionPopulateData.repartition_0N_01(
         -1, -1, v_GradeDao, v_PersonneDao, PersonneColumns_Enum.grade_id, false);
      // Adresses
      RepartitionPopulateData.repartition_01_0N(
         -1, -1, v_PersonneDao, v_AdresseDao, AdresseColumns_Enum.personneAdresses_id, false);
      // Pays
      RepartitionPopulateData.repartition_01_0N(
         -1, -1, v_PersonneDao, v_PaysDao, PaysColumns_Enum.personnePays_id, false);
      // ParentDe
      RepartitionPopulateData.repartition_01_1N(
         -1, -1, v_PersonneDao, v_PersonneDao, PersonneColumns_Enum.personneParentDe_id, false);
      // MarieAvec
      RepartitionPopulateData.repartition_0N_01(
         -1, -1, v_PersonneDao, v_PersonneDao, PersonneColumns_Enum.marieAvec_id, false);

      ApplicationParamPersistence.getUserPersistence().commit();
   }

   /**
    * Initialisation de l'entité 'Competence'.
    * @param p_nbMaxCompetence
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initCompetence (final int p_nbMaxCompetence) throws Spi4jValidationException
   {
      final CompetenceEntity_Itf v_CompetenceCour = ApplicationParamPersistence.getUserPersistence().getCompetenceEntity();
      final CompetenceDao_Itf v_CompetenceDao = ApplicationParamPersistence.getUserPersistence().getCompetenceDao();
      v_CompetenceDao.deleteAll();

      for (long v_i = 0; v_i < p_nbMaxCompetence; v_i++)
      {
		 
         // Start of user code for Initialization : Competence
         // TODO renseigner données de test
         // Affecter l'identifiant
         v_CompetenceCour.setId(v_i);
         v_CompetenceCour.set_libelle("s" + v_i);
         v_CompetenceCour.set_typecompetence( TypeCompetence_Enum.valueOf("TYPECOMP1") );
         v_CompetenceCour.set_xdmaj(new Date());
         v_CompetenceCour.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
         // End of user code
         v_CompetenceDao.create(v_CompetenceCour);
      }
   }

   /**
    * Initialisation de l'entité 'Personne'.
    * @param p_nbMaxPersonne
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initPersonne (final int p_nbMaxPersonne) throws Spi4jValidationException
   {
      final PersonneEntity_Itf v_PersonneCour = ApplicationParamPersistence.getUserPersistence().getPersonneEntity();
      final PersonneDao_Itf v_PersonneDao = ApplicationParamPersistence.getUserPersistence().getPersonneDao();
      v_PersonneDao.deleteAll();

      for (long v_i = 0; v_i < p_nbMaxPersonne; v_i++)
      {
		 
         // Start of user code for Initialization : Personne
         // TODO renseigner données de test
         // Affecter l'identifiant
         v_PersonneCour.setId(v_i);
         v_PersonneCour.set_nom("s" + v_i);
         v_PersonneCour.set_prenom("s" + v_i);
         v_PersonneCour.set_civil(false);
         v_PersonneCour.set_dateNaissance(new Date());
         v_PersonneCour.set_salaire((double) v_i);
         v_PersonneCour.set_xdmaj(new Date());
         v_PersonneCour.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
         v_PersonneCour.set_Grade_id(null);
         v_PersonneCour.set_MarieAvec_id(null);
         v_PersonneCour.set_personneParentDe_id(1L);
         // End of user code
         v_PersonneDao.create(v_PersonneCour);
      }
   }

   /**
    * Initialisation de l'entité 'Adresse'.
    * @param p_nbMaxAdresse
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initAdresse (final int p_nbMaxAdresse) throws Spi4jValidationException
   {
      final AdresseEntity_Itf v_AdresseCour = ApplicationParamPersistence.getUserPersistence().getAdresseEntity();
      final AdresseDao_Itf v_AdresseDao = ApplicationParamPersistence.getUserPersistence().getAdresseDao();
      v_AdresseDao.deleteAll();

      for (long v_i = 0; v_i < p_nbMaxAdresse; v_i++)
      {
		 
         // Start of user code for Initialization : Adresse
         // TODO renseigner données de test
         // Affecter l'identifiant
         v_AdresseCour.setId(v_i);
         v_AdresseCour.set_rue("s" + v_i);
         v_AdresseCour.set_ville("s" + v_i);
         v_AdresseCour.set_codePostal("s" + v_i);
         v_AdresseCour.set_xdmaj(new Date());
         v_AdresseCour.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
         v_AdresseCour.set_personneAdresses_id(1L);
         // End of user code
         v_AdresseDao.create(v_AdresseCour);
      }
   }

   /**
    * Initialisation de l'entité 'Pays'.
    * @param p_nbMaxPays
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initPays (final int p_nbMaxPays) throws Spi4jValidationException
   {
      final PaysEntity_Itf v_PaysCour = ApplicationParamPersistence.getUserPersistence().getPaysEntity();
      final PaysDao_Itf v_PaysDao = ApplicationParamPersistence.getUserPersistence().getPaysDao();
      v_PaysDao.deleteAll();

      for (long v_i = 0; v_i < p_nbMaxPays; v_i++)
      {
		 
         // Start of user code for Initialization : Pays
         // TODO renseigner données de test
         // Affecter l'identifiant
         v_PaysCour.setId(v_i);
         v_PaysCour.set_nom("s" + v_i);
         v_PaysCour.set_capitale("s" + v_i);
         v_PaysCour.set_xdmaj(new Date());
         v_PaysCour.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
         v_PaysCour.set_personnePays_id(1L);
         // End of user code
         v_PaysDao.create(v_PaysCour);
      }
   }

}
