/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PaysAttributes_Enum;
import fr.application.business.api.annuaire.PaysDto;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.matching.ApplicationMatch_Abs;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.application.persistence.api.annuaire.PaysColumns_Enum;
import fr.application.persistence.api.annuaire.PaysDao_Itf;
import fr.application.persistence.api.annuaire.PaysEntity_Itf;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;
import java.util.Date;

// End of user code

/**
 * Implémentation pour le Matcher (= persistance <-> business) sur le type 'Pays'.
 * @author safr@n
 */
public class PaysMatch extends ApplicationMatch_Abs<Long, PaysDto, PaysEntity_Itf, PaysColumns_Enum> implements PaysMatch_Itf
{
   /**
    * Obtenir le DAO associé au type 'Pays'.
    * @return L'instance désirée.
    */
   @Override
   protected PaysDao_Itf getDao ()
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      return v_userPersistence.getPaysDao ();
   }

   /**
    * Vérification des types d'attributs dans le matching.
    * @param p_id
    *           (In)(*) L'identifiant de pays.
    * @param p_nom
    *           (In)(*) nom.
    * @param p_capitale
    *           (In)(*) capitale.
    * @param p_personnePays
    *           (In) personne
    */
   private void checkPays (final Class<Long> p_id, final Class<String> p_nom, final Class<String> p_capitale, final Class<PersonneDto> p_personnePays)
   {
      checkAttributeTypes (p_id, p_nom, p_capitale, p_personnePays);
   }

   @Override
   protected PaysEntity_Itf convertDtoToEntity (final PaysDto p_dto)
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      final PaysEntity_Itf v_entity = v_userPersistence.getPaysEntity ();
      // checkPays(Long.class, String.class, String.class, PersonneDto.class);
      // v_entity.setId(p_dto.getId());
      // v_entity.set_nom(p_dto.get_nom ());
      // v_entity.set_capitale(p_dto.get_capitale ());
      // v_entity.set_personnePays_id (p_dto.get_personnePays_id ());

      // convertDtoToEntity
      // Start of user code convertDtoToEntity
      checkPays(Long.class, String.class, String.class, PersonneDto.class);
      v_entity.setId(p_dto.getId ());
      v_entity.set_nom (p_dto.get_nom());
      v_entity.set_capitale (p_dto.get_capitale());
      v_entity.set_personnePays_id (p_dto.get_personnePays_id ());
      v_entity.set_xdmaj(p_dto.get_xdmaj());
      v_entity.set_xtopsup(p_dto.get_xtopsup());
      // End of user code

      return v_entity;
   }

   @Override
   protected PaysDto convertEntityToDto (final PaysEntity_Itf p_entity)
   {
      final PaysDto v_dto = new PaysDto ();
      refreshDtoFromEntity (p_entity, v_dto);
      return v_dto;
   }

   @Override
   protected void refreshDtoFromEntity (final PaysEntity_Itf p_entity, final PaysDto p_dto)
   {
      // checkPays (Long.class, String.class, String.class, PersonneDto.class);
      // p_dto.setId (p_entity.getId ());
      // p_dto.set_nom (p_entity.get_nom ());
      // p_dto.set_capitale (p_entity.get_capitale ());
      // p_dto.set_personnePays_id (p_entity.get_personnePays_id ());

      // refreshDtoFromEntity
      // Start of user code refreshDtoFromEntity
      checkPays (Long.class, String.class, String.class, PersonneDto.class);
      p_dto.setId (p_entity.getId ());
      p_dto.set_nom (p_entity.get_nom ());
      p_dto.set_capitale (p_entity.get_capitale ());
      p_dto.set_personnePays_id (p_entity.get_personnePays_id ());
      p_dto.set_xdmaj(p_entity.get_xdmaj());
      p_dto.set_xtopsup(p_entity.get_xtopsup());
      // End of user code
   }

   @Override
   public PaysColumns_Enum getColumn (final PaysAttributes_Enum p_attribute)
   {
      if ("id".equals(p_attribute.getName ()))
      {
         return PaysColumns_Enum.pays_id;
      }
      return PaysColumns_Enum.valueOf (p_attribute.getName ());
   }

   // PaysMatch
   // Start of user code PaysMatch

   // End of user code
}
