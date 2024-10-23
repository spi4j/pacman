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
import fr.application.persistence.api.ref.GradeDao_Itf;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.spi4j.type.XtopSup;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.ParamPersistence_Abs;
import fr.spi4j.persistence.dbpopulate.RepartitionPopulateData;

// End of user code

/**
 * Permet d'effectuer le remplissage du Namespace 'ref'.
 */
public class RefPopulate
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

      final int v_nbMaxGrade = -1; // TODO Renseigner l'annotation VOL_NB_ROWS sur l'entité Grade

      initGrade(v_nbMaxGrade);

      ApplicationParamPersistence.getUserPersistence().commit();
      ApplicationParamPersistence.getUserPersistence().begin();



      ApplicationParamPersistence.getUserPersistence().commit();
   }

   /**
    * Initialisation de l'entité 'Grade'.
    * @param p_nbMaxGrade
    *           le nombre d'instances à initialiser
    * @throws Spi4jValidationException
    *            erreur de validation
    */
   public static void initGrade (final int p_nbMaxGrade) throws Spi4jValidationException
   {
      final GradeEntity_Itf v_GradeCour = ApplicationParamPersistence.getUserPersistence().getGradeEntity();
      final GradeDao_Itf v_GradeDao = ApplicationParamPersistence.getUserPersistence().getGradeDao();
      v_GradeDao.deleteAll();

      for (long v_i = 0; v_i < p_nbMaxGrade; v_i++)
      {
		 
         // Start of user code for Initialization : Grade
         // TODO renseigner données de test
         // Affecter l'identifiant
         v_GradeCour.setId(v_i);
         v_GradeCour.set_libelle("s" + v_i);
         v_GradeCour.set_trigramme("s" + v_i);
         v_GradeCour.set_xdmaj(new Date());
         v_GradeCour.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.active));
         // End of user code
         v_GradeDao.create(v_GradeCour);
      }
   }

}
