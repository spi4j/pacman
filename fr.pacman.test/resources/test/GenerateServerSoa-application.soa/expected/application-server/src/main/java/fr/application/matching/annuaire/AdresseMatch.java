/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.AdresseAttributes_Enum;
import fr.application.business.api.annuaire.AdresseDto;
import fr.application.business.api.annuaire.PersonneDto;
import fr.application.matching.ApplicationMatch_Abs;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.ApplicationUserPersistence;
import fr.application.persistence.api.annuaire.AdresseColumns_Enum;
import fr.application.persistence.api.annuaire.AdresseDao_Itf;
import fr.application.persistence.api.annuaire.AdresseEntity_Itf;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;

// End of user code

/**
 * Implémentation pour le Matcher (= persistance <-> business) sur le type 'Adresse'.
 * @author safr@n
 */
public class AdresseMatch extends ApplicationMatch_Abs<Long, AdresseDto, AdresseEntity_Itf, AdresseColumns_Enum> implements AdresseMatch_Itf
{
   /**
    * Obtenir le DAO associé au type 'Adresse'.
    * @return L'instance désirée.
    */
   @Override
   protected AdresseDao_Itf getDao ()
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      return v_userPersistence.getAdresseDao ();
   }

   /**
    * Vérification des types d'attributs dans le matching.
    * @param p_id
    *           (In)(*) L'identifiant de adresse.
    * @param p_rue
    *           (In)(*) rue.
    * @param p_ville
    *           (In)(*) ville.
    * @param p_codePostal
    *           (In)(*) codePostal.
    * @param p_personneAdresses
    *           (In) personne
    */
   private void checkAdresse (final Class<Long> p_id, final Class<String> p_rue, final Class<String> p_ville, final Class<String> p_codePostal, final Class<PersonneDto> p_personneAdresses)
   {
      checkAttributeTypes (p_id, p_rue, p_ville, p_codePostal, p_personneAdresses);
   }

   @Override
   protected AdresseEntity_Itf convertDtoToEntity (final AdresseDto p_dto)
   {
      final ApplicationUserPersistence v_userPersistence = ApplicationParamPersistence.getUserPersistence ();
      final AdresseEntity_Itf v_entity = v_userPersistence.getAdresseEntity ();
      // checkAdresse(Long.class, String.class, String.class, String.class, PersonneDto.class);
      // v_entity.setId(p_dto.getId());
      // v_entity.set_rue(p_dto.get_rue ());
      // v_entity.set_ville(p_dto.get_ville ());
      // v_entity.set_codePostal(p_dto.get_codePostal ());
      // v_entity.set_personneAdresses_id (p_dto.get_personneAdresses_id ());

      
      // Start of user code convertDtoToEntity
      checkAdresse(Long.class, String.class, String.class, String.class, PersonneDto.class);
      v_entity.setId(p_dto.getId ());
      v_entity.set_rue (p_dto.get_rue());
      v_entity.set_ville (p_dto.get_ville());
      v_entity.set_codePostal (p_dto.get_codePostal());
      v_entity.set_personneAdresses_id (p_dto.get_personneAdresses_id ());
      v_entity.set_xdmaj(p_dto.get_xdmaj());
      v_entity.set_xtopsup(p_dto.get_xtopsup());
      // End of user code

      return v_entity;
   }

   @Override
   protected AdresseDto convertEntityToDto (final AdresseEntity_Itf p_entity)
   {
      final AdresseDto v_dto = new AdresseDto ();
      refreshDtoFromEntity (p_entity, v_dto);
      return v_dto;
   }

   @Override
   protected void refreshDtoFromEntity (final AdresseEntity_Itf p_entity, final AdresseDto p_dto)
   {
      // checkAdresse (Long.class, String.class, String.class, String.class, PersonneDto.class);
      // p_dto.setId (p_entity.getId ());
      // p_dto.set_rue (p_entity.get_rue ());
      // p_dto.set_ville (p_entity.get_ville ());
      // p_dto.set_codePostal (p_entity.get_codePostal ());
      // p_dto.set_personneAdresses_id (p_entity.get_personneAdresses_id ());

      
      // Start of user code refreshDtoFromEntity
      checkAdresse (Long.class, String.class, String.class, String.class, PersonneDto.class);
      p_dto.setId (p_entity.getId ());
      p_dto.set_rue (p_entity.get_rue ());
      p_dto.set_ville (p_entity.get_ville ());
      p_dto.set_codePostal (p_entity.get_codePostal ());
      p_dto.set_personneAdresses_id (p_entity.get_personneAdresses_id ());
      p_dto.set_xdmaj(p_entity.get_xdmaj());
      p_dto.set_xtopsup(p_entity.get_xtopsup());
      // End of user code
   }

   @Override
   public AdresseColumns_Enum getColumn (final AdresseAttributes_Enum p_attribute)
   {
      if ("id".equals(p_attribute.getName ()))
      {
         return AdresseColumns_Enum.adresse_id;
      }
      return AdresseColumns_Enum.valueOf (p_attribute.getName ());
   }

   
   // Start of user code AdresseMatch

   // End of user code
}
