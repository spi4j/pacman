/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
// CHECKSTYLE:OFF
package fr.application.persistence; // NOPMD
// CHECKSTYLE:ON
// Start of user code for imports

import fr.spi4j.persistence.ParamPersistence_Abs;
import fr.spi4j.persistence.UserPersistence_Abs;

// End of user code

/**
 * Implémentation permettant de centraliser les traitements de persistance de l'application.
 * @author safr@n
 */
public final class ApplicationUserPersistence extends UserPersistence_Abs
{

   /**
    * Constructeur.
    * @param p_ParamPersistence
    *           (In)(*) le paramétrage de la persistance
    */
   protected ApplicationUserPersistence (final ParamPersistence_Abs p_ParamPersistence)
   {
      super (p_ParamPersistence);
   }


   // getGradeEntity
   // Start of user code getGradeEntity
   /**
    * Obtenir l'entité 'GradeEntity_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.ref.GradeEntity_Itf getGradeEntity ()
   {
      return getEntity (fr.application.persistence.api.ref.GradeEntity_Itf.class);
   }
   // End of user code

   // getGradeDao
   // Start of user code getGradeDao
   /**
    * Obtenir le DAO 'GradeDao_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.ref.GradeDao_Itf getGradeDao ()
   {
      return getDao (fr.application.persistence.api.ref.GradeEntity_Itf.class);
   }
   // End of user code

   // getCompetenceEntity
   // Start of user code getCompetenceEntity
   /**
    * Obtenir l'entité 'CompetenceEntity_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.CompetenceEntity_Itf getCompetenceEntity ()
   {
      return getEntity (fr.application.persistence.api.annuaire.CompetenceEntity_Itf.class);
   }
   // End of user code

   // getCompetenceDao
   // Start of user code getCompetenceDao
   /**
    * Obtenir le DAO 'CompetenceDao_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.CompetenceDao_Itf getCompetenceDao ()
   {
      return getDao (fr.application.persistence.api.annuaire.CompetenceEntity_Itf.class);
   }
   // End of user code

   // getPersonneEntity
   // Start of user code getPersonneEntity
   /**
    * Obtenir l'entité 'PersonneEntity_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.PersonneEntity_Itf getPersonneEntity ()
   {
      return getEntity (fr.application.persistence.api.annuaire.PersonneEntity_Itf.class);
   }
   // End of user code

   // getPersonneDao
   // Start of user code getPersonneDao
   /**
    * Obtenir le DAO 'PersonneDao_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.PersonneDao_Itf getPersonneDao ()
   {
      return getDao (fr.application.persistence.api.annuaire.PersonneEntity_Itf.class);
   }
   // End of user code

   // getAdresseEntity
   // Start of user code getAdresseEntity
   /**
    * Obtenir l'entité 'AdresseEntity_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.AdresseEntity_Itf getAdresseEntity ()
   {
      return getEntity (fr.application.persistence.api.annuaire.AdresseEntity_Itf.class);
   }
   // End of user code

   // getAdresseDao
   // Start of user code getAdresseDao
   /**
    * Obtenir le DAO 'AdresseDao_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.AdresseDao_Itf getAdresseDao ()
   {
      return getDao (fr.application.persistence.api.annuaire.AdresseEntity_Itf.class);
   }
   // End of user code

   // getPaysEntity
   // Start of user code getPaysEntity
   /**
    * Obtenir l'entité 'PaysEntity_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.PaysEntity_Itf getPaysEntity ()
   {
      return getEntity (fr.application.persistence.api.annuaire.PaysEntity_Itf.class);
   }
   // End of user code

   // getPaysDao
   // Start of user code getPaysDao
   /**
    * Obtenir le DAO 'PaysDao_Itf'.
    * @return L'instance désirée.
    */
   public fr.application.persistence.api.annuaire.PaysDao_Itf getPaysDao ()
   {
      return getDao (fr.application.persistence.api.annuaire.PaysEntity_Itf.class);
   }
   // End of user code
}
