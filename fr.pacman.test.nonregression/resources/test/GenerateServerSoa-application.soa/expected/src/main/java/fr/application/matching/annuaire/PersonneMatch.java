/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PersonneAttributes_Enum;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.business.api.ref.GradeDto;
import fr.application.matching.ApplicationMatch_Abs;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.application.persistence.api.annuaire.PersonneColumns_Enum;
import fr.application.persistence.api.annuaire.PersonneDao_Itf;
import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Implémentation pour le Matcher (= persistance <-> business) sur le type 'Personne'.
 * @author safr@n
 */
public class PersonneMatch extends ApplicationMatch_Abs<Long, PersonneDto, PersonneEntity_Itf, PersonneColumns_Enum> implements PersonneMatch_Itf
{
   /**
    * Obtenir le DAO associé au type 'Personne'.
    * @return L'instance désirée.
    */
   @Override
   protected PersonneDao_Itf getDao ()
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      return v_userPersistence.getPersonneDao ();
   }

   /**
    * Vérification des types d'attributs dans le matching.
    * @param p_id
    *           (In)(*) L'identifiant de personne.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_prenom
    *           (In)(*) prenom.
    * @param p_civil
    *           (In)(*) civil.
    * @param p_dateNaissance
    *           (In)(*) dateNaissance.
    * @param p_salaire
    *           (In)(*) salaire.
    * @param p_grade
    *           (In) Grade.
    * @param p_marieAvec
    *           (In) MarieAvec.
    * @param p_personneParentDe
    *           (In)(*) personne
    */
   private void checkPersonne (final Class<Long> p_id, final Class<String> p_nom, final Class<String> p_prenom, final Class<Boolean> p_civil, final Class<Date> p_dateNaissance, final Class<Double> p_salaire, final Class<GradeDto> p_grade, final Class<PersonneDto> p_marieAvec, final Class<PersonneDto> p_personneParentDe)
   {
      checkAttributeTypes (p_id, p_nom, p_prenom, p_civil, p_dateNaissance, p_salaire, p_grade, p_marieAvec, p_personneParentDe);
   }

   @Override
   protected PersonneEntity_Itf convertDtoToEntity (final PersonneDto p_dto)
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      final PersonneEntity_Itf v_entity = v_userPersistence.getPersonneEntity ();
      // checkPersonne(Long.class, String.class, String.class, Boolean.class, Date.class, Double.class, GradeDto.class, PersonneDto.class, PersonneDto.class);
      // v_entity.setId(p_dto.getId());
      // v_entity.set_nom(p_dto.get_nom ());
      // v_entity.set_prenom(p_dto.get_prenom ());
      // v_entity.set_civil(p_dto.get_civil ());
      // v_entity.set_dateNaissance(p_dto.get_dateNaissance ());
      // v_entity.set_salaire(p_dto.get_salaire ());
      // v_entity.set_Grade_id (p_dto.get_grade_id ());
      // v_entity.set_MarieAvec_id (p_dto.get_marieAvec_id ());
      // v_entity.set_personneParentDe_id (p_dto.get_personneParentDe_id ());

      // convertDtoToEntity
      // Start of user code convertDtoToEntity
      checkPersonne(Long.class, String.class, String.class, Boolean.class, Date.class, Double.class, GradeDto.class, PersonneDto.class, PersonneDto.class);
      v_entity.setId(p_dto.getId ());
      v_entity.set_nom (p_dto.get_nom());
      v_entity.set_prenom (p_dto.get_prenom());
      v_entity.set_civil (p_dto.get_civil());
      v_entity.set_dateNaissance (p_dto.get_dateNaissance());
      v_entity.set_salaire (p_dto.get_salaire());
      v_entity.set_Grade_id (p_dto.get_grade_id ());
      v_entity.set_MarieAvec_id (p_dto.get_marieAvec_id ());
      v_entity.set_personneParentDe_id (p_dto.get_personneParentDe_id ());
      v_entity.set_xdmaj(p_dto.get_xdmaj());
      v_entity.set_xtopsup(p_dto.get_xtopsup());
      // End of user code

      return v_entity;
   }

   @Override
   protected PersonneDto convertEntityToDto (final PersonneEntity_Itf p_entity)
   {
      final PersonneDto v_dto = new PersonneDto ();
      refreshDtoFromEntity (p_entity, v_dto);
      return v_dto;
   }

   @Override
   protected void refreshDtoFromEntity (final PersonneEntity_Itf p_entity, final PersonneDto p_dto)
   {
      // checkPersonne (Long.class, String.class, String.class, Boolean.class, Date.class, Double.class, GradeDto.class, PersonneDto.class, PersonneDto.class);
      // p_dto.setId (p_entity.getId ());
      // p_dto.set_nom (p_entity.get_nom ());
      // p_dto.set_prenom (p_entity.get_prenom ());
      // p_dto.set_civil (p_entity.get_civil ());
      // p_dto.set_dateNaissance (p_entity.get_dateNaissance ());
      // p_dto.set_salaire (p_entity.get_salaire ());
      // p_dto.set_grade_id (p_entity.get_Grade_id ());
      // p_dto.set_marieAvec_id (p_entity.get_MarieAvec_id ());
      // p_dto.set_personneParentDe_id (p_entity.get_personneParentDe_id ());

      // refreshDtoFromEntity
      // Start of user code refreshDtoFromEntity
      checkPersonne (Long.class, String.class, String.class, Boolean.class, Date.class, Double.class, GradeDto.class, PersonneDto.class, PersonneDto.class);
      p_dto.setId (p_entity.getId ());
      p_dto.set_nom (p_entity.get_nom ());
      p_dto.set_prenom (p_entity.get_prenom ());
      p_dto.set_civil (p_entity.get_civil ());
      p_dto.set_dateNaissance (p_entity.get_dateNaissance ());
      p_dto.set_salaire (p_entity.get_salaire ());
      p_dto.set_grade_id (p_entity.get_Grade_id ());
      p_dto.set_marieAvec_id (p_entity.get_MarieAvec_id ());
      p_dto.set_personneParentDe_id (p_entity.get_personneParentDe_id ());
      p_dto.set_xdmaj(p_entity.get_xdmaj());
      p_dto.set_xtopsup(p_entity.get_xtopsup());
      // End of user code
   }

   @Override
   public PersonneColumns_Enum getColumn (final PersonneAttributes_Enum p_attribute)
   {
      if ("id".equals(p_attribute.getName ()))
      {
         return PersonneColumns_Enum.personne_id;
      }
      return PersonneColumns_Enum.valueOf (p_attribute.getName ());
   }

   // PersonneMatch
   // Start of user code PersonneMatch

   // End of user code
}
