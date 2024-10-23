/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.ref;
// Start of user code for imports

import fr.application.business.api.ref.GradeAttributes_Enum;
import fr.application.business.api.ref.GradeDto;
import fr.application.matching.ApplicationMatch_Abs;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.application.persistence.api.ref.GradeColumns_Enum;
import fr.application.persistence.api.ref.GradeDao_Itf;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;

// End of user code

/**
 * Implémentation pour le Matcher (= persistance <-> business) sur le type 'Grade'.
 * @author safr@n
 */
public class GradeMatch extends ApplicationMatch_Abs<Long, GradeDto, GradeEntity_Itf, GradeColumns_Enum> implements GradeMatch_Itf
{
   /**
    * Obtenir le DAO associé au type 'Grade'.
    * @return L'instance désirée.
    */
   @Override
   protected GradeDao_Itf getDao ()
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      return v_userPersistence.getGradeDao ();
   }

   /**
    * Vérification des types d'attributs dans le matching.
    * @param p_id
    *           (In)(*) L'identifiant de grade.
    * @param p_libelle
    *           (In)(*) Le libellé du grade.
    * @param p_trigramme
    *           (In)(*) Le trigramme du grade.
    */
   private void checkGrade (final Class<Long> p_id, final Class<String> p_libelle, final Class<String> p_trigramme)
   {
      checkAttributeTypes (p_id, p_libelle, p_trigramme);
   }

   @Override
   protected GradeEntity_Itf convertDtoToEntity (final GradeDto p_dto)
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      final GradeEntity_Itf v_entity = v_userPersistence.getGradeEntity ();
      // checkGrade(Long.class, String.class, String.class);
      // v_entity.setId(p_dto.getId());
      // v_entity.set_libelle(p_dto.get_libelle ());
      // v_entity.set_trigramme(p_dto.get_trigramme ());

      
      // Start of user code convertDtoToEntity
      checkGrade(Long.class, String.class, String.class);
      v_entity.setId(p_dto.getId ());
      v_entity.set_libelle (p_dto.get_libelle());
      v_entity.set_trigramme (p_dto.get_trigramme());
      v_entity.set_xdmaj(p_dto.get_xdmaj());
      v_entity.set_xtopsup(p_dto.get_xtopsup());
      // End of user code

      return v_entity;
   }

   @Override
   protected GradeDto convertEntityToDto (final GradeEntity_Itf p_entity)
   {
      final GradeDto v_dto = new GradeDto ();
      refreshDtoFromEntity (p_entity, v_dto);
      return v_dto;
   }

   @Override
   protected void refreshDtoFromEntity (final GradeEntity_Itf p_entity, final GradeDto p_dto)
   {
      // checkGrade (Long.class, String.class, String.class);
      // p_dto.setId (p_entity.getId ());
      // p_dto.set_libelle (p_entity.get_libelle ());
      // p_dto.set_trigramme (p_entity.get_trigramme ());

      
      // Start of user code refreshDtoFromEntity
      checkGrade (Long.class, String.class, String.class);
      p_dto.setId (p_entity.getId ());
      p_dto.set_libelle (p_entity.get_libelle ());
      p_dto.set_trigramme (p_entity.get_trigramme ());
      p_dto.set_xdmaj(p_entity.get_xdmaj());
      p_dto.set_xtopsup(p_entity.get_xtopsup());
      // End of user code
   }

   @Override
   public GradeColumns_Enum getColumn (final GradeAttributes_Enum p_attribute)
   {
      if ("id".equals(p_attribute.getName ()))
      {
         return GradeColumns_Enum.grade_id;
      }
      return GradeColumns_Enum.valueOf (p_attribute.getName ());
   }

   
   // Start of user code GradeMatch

   // End of user code
}
