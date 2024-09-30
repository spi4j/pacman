/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.CompetenceAttributes_Enum;
import fr.application.business.api.annuaire.CompetenceDto;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.matching.ApplicationMatch_Abs;
import fr.application.matching.ApplicationUserMatching;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.application.persistence.api.annuaire.CompetenceColumns_Enum;
import fr.application.persistence.api.annuaire.CompetenceDao_Itf;
import fr.application.persistence.api.annuaire.CompetenceEntity_Itf;
import fr.application.persistence.api.annuaire.PersonneColumns_Enum;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;
import fr.spi4j.type.XtopSup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// End of user code

/**
 * Implémentation pour le Matcher (= persistance <-> business) sur le type 'Competence'.
 * @author safr@n
 */
public class CompetenceMatch extends ApplicationMatch_Abs<Long, CompetenceDto, CompetenceEntity_Itf, CompetenceColumns_Enum> implements CompetenceMatch_Itf
{
   /**
    * Obtenir le DAO associé au type 'Competence'.
    * @return L'instance désirée.
    */
   @Override
   protected CompetenceDao_Itf getDao ()
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      return v_userPersistence.getCompetenceDao ();
   }

   /**
    * Vérification des types d'attributs dans le matching.
    * @param p_id
    *           (In)(*) L'identifiant de competence.
    * @param p_libelle
    *           (In)(*) Le libellé de la compétence.
    * @param p_typecompetence
    *           (In)(*) typecompetence.
    */
   private void checkCompetence (final Class<Long> p_id, final Class<String> p_libelle, final Class<TypeCompetence_Enum> p_typecompetence)
   {
      checkAttributeTypes (p_id, p_libelle, p_typecompetence);
   }

   @Override
   protected CompetenceEntity_Itf convertDtoToEntity (final CompetenceDto p_dto)
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      final CompetenceEntity_Itf v_entity = v_userPersistence.getCompetenceEntity ();
      // checkCompetence(Long.class, String.class, TypeCompetence_Enum.class);
      // v_entity.setId(p_dto.getId());
      // v_entity.set_libelle(p_dto.get_libelle ());
      // v_entity.set_typecompetence(p_dto.get_typecompetence ());

      // convertDtoToEntity
      // Start of user code convertDtoToEntity
      checkCompetence(Long.class, String.class, TypeCompetence_Enum.class);
      v_entity.setId(p_dto.getId ());
      v_entity.set_libelle (p_dto.get_libelle());
      v_entity.set_typecompetence (p_dto.get_typecompetence());
      v_entity.set_xdmaj(p_dto.get_xdmaj());
      v_entity.set_xtopsup(p_dto.get_xtopsup());
      // End of user code

      return v_entity;
   }

   @Override
   protected CompetenceDto convertEntityToDto (final CompetenceEntity_Itf p_entity)
   {
      final CompetenceDto v_dto = new CompetenceDto ();
      refreshDtoFromEntity (p_entity, v_dto);
      return v_dto;
   }

   @Override
   protected void refreshDtoFromEntity (final CompetenceEntity_Itf p_entity, final CompetenceDto p_dto)
   {
      // checkCompetence (Long.class, String.class, TypeCompetence_Enum.class);
      // p_dto.setId (p_entity.getId ());
      // p_dto.set_libelle (p_entity.get_libelle ());
      // p_dto.set_typecompetence (p_entity.get_typecompetence ());

      // refreshDtoFromEntity
      // Start of user code refreshDtoFromEntity
      checkCompetence (Long.class, String.class, TypeCompetence_Enum.class);
      p_dto.setId (p_entity.getId ());
      p_dto.set_libelle (p_entity.get_libelle ());
      p_dto.set_typecompetence (p_entity.get_typecompetence ());
      p_dto.set_xdmaj(p_entity.get_xdmaj());
      p_dto.set_xtopsup(p_entity.get_xtopsup());
      // End of user code
   }

   @Override
   public CompetenceColumns_Enum getColumn (final CompetenceAttributes_Enum p_attribute)
   {
      if ("id".equals(p_attribute.getName ()))
      {
         return CompetenceColumns_Enum.competence_id;
      }
      return CompetenceColumns_Enum.valueOf (p_attribute.getName ());
   }

   @Override
   public void create (final CompetenceDto p_dto) throws Spi4jValidationException
   {
      final List<PersonneDto> v_tab_dispose = p_dto.get_tab_dispose ();
      super.create (p_dto);
      saveDispose (p_dto, v_tab_dispose);
   }

   @Override
   public void update (final CompetenceDto p_dto) throws Spi4jValidationException
   {
      final List<PersonneDto> v_tab_dispose = p_dto.get_tab_dispose ();
      super.update (p_dto);
      // sauvegarde des Personne dans la relation Dispose
      saveDispose (p_dto, v_tab_dispose);
   }

   @Override
   public void delete (final CompetenceDto p_dto) throws Spi4jValidationException
   {
      // suppression des Personne dans la relation Dispose
      final List<PersonneDto> v_Dispose = p_dto.get_tab_dispose();
      for(PersonneDto v_PersonneDto : v_Dispose){ 
          v_PersonneDto.set_xtopsup(new XtopSup(DatabaseLineStatus_Enum.deletedForAll));
      }
      saveDispose (p_dto, v_Dispose);
      super.delete (p_dto);
   }

	/**
    * Sauvegarde les relations entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    * @param p_tab_personne
    *           les relations à ajouter
    */
   @Override	
   public void saveDispose (final CompetenceDto p_dto, final List<PersonneDto> p_tab_dispose)
   {
      if (p_tab_dispose != null)
      {
		 // suppression des relations existantes 
         deleteDispose (p_dto);
         // construction de la requête
         final String v_queryDisposeInsert = "insert into " + CompetenceColumns_Enum.c_tableNameCompetenceDispose
          + "(" + CompetenceColumns_Enum.competence_id + ", " + PersonneColumns_Enum.personne_id + ", " + ApplicationAutoFieldsColumns_Enum.xtopsup
           + ", " + ApplicationAutoFieldsColumns_Enum.xdmaj + ")" + "values (:competence_id, :personne_id, :xtopsup, :xdmaj)"; 
         // initialisation des paramètres de la requête
         final Map<String, Object> v_params = new HashMap<> ();
         v_params.put ("competence_id", p_dto.getId ());
	     v_params.put ("xdmaj", new Date());
         for (final PersonneDto v_dtoFk : p_tab_dispose)
         {
            v_params.put ("personne_id", v_dtoFk.getId ());
			v_params.put ("xtopsup", v_dtoFk.get_xtopsup().get_value()); 
            // insertion en base
            getDao().executeUpdate (v_queryDisposeInsert, v_params);
         }
         // mise à jour du dto
         p_dto.setDispose(p_tab_dispose);
      }
   }
	
	/**
    * Obtenir la liste d'objets de type 'PersonneDto' associés à l'instance de type 'Personne'.
    * @param p_competence_id
    *           (In)(*) Competence.
    * @return une liste de PersonneDto
    */
 	@Override
   public List<PersonneDto> findListDisposeByCompetence (final Long p_competence_id)
   {
      return ApplicationUserMatching.getPersonneMatch ().convertListEntityToListDto (
               getDao ().findListDisposeByCompetence (p_competence_id));
   }

   /**
    * Supprime les relations entre 'Competence' et 'Personne' pour ce dto.
    * @param p_dto
    *           le dto
    */
   @Override
   public void deleteDispose (final CompetenceDto p_dto)
   {
      // construction de la requête
      final String v_queryDisposeDelete = "delete from " + CompetenceColumns_Enum.c_tableNameCompetenceDispose + " where " + CompetenceColumns_Enum.competence_id + " = :competence_id";
      // initialisation des paramètres de la requête
      final Map<String, Long> v_params = Collections.singletonMap ("competence_id", p_dto.getId ());
      // insertion en base
      getDao().executeUpdate (v_queryDisposeDelete, v_params);
   }

   // CompetenceMatch
   // Start of user code CompetenceMatch

   // End of user code
}
