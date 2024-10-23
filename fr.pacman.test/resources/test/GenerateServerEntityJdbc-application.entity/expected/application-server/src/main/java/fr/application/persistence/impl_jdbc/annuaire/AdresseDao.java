/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.api.ApplicationAutoFieldsColumns_Enum;
import fr.application.persistence.api.annuaire.AdresseColumns_Enum;
import fr.application.persistence.api.annuaire.AdresseDao_Itf;
import fr.application.persistence.api.annuaire.AdresseEntity_Itf;
import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO Adresse.
 * @author safr@n
 */
public class AdresseDao extends DaoJdbc_Abs<AdresseEntity_Itf, AdresseColumns_Enum> implements AdresseDao_Itf
{
   // CONSTANTES

   
   // Start of user code Constantes AdresseDao

   // End of user code

   // ATTRIBUTS

   
   // Start of user code Attributs AdresseDao

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public AdresseDao ()
   {
      super (AdresseColumns_Enum.c_tableName, AdresseColumns_Enum.values (), ApplicationAutoFieldsColumns_Enum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final AdresseEntity_Itf p_Entity)
   {
      final Map<String, Object> v_map_valueByColumnName = new LinkedHashMap<>();
      v_map_valueByColumnName.put(AdresseColumns_Enum.adresse_id.getLogicalColumnName(), p_Entity.getId());
      v_map_valueByColumnName.put(AdresseColumns_Enum.rue.getLogicalColumnName(), p_Entity.get_rue());
      v_map_valueByColumnName.put(AdresseColumns_Enum.ville.getLogicalColumnName(), p_Entity.get_ville());
      v_map_valueByColumnName.put(AdresseColumns_Enum.codePostal.getLogicalColumnName(), p_Entity.get_codePostal());
      v_map_valueByColumnName.put(AdresseColumns_Enum.personneAdresses_id.getLogicalColumnName(), p_Entity.get_personneAdresses_id());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName(), p_Entity.get_xdmaj());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName(), p_Entity.get_xtopsup().get_value());
      return v_map_valueByColumnName;
   }

   @Override
   protected AdresseEntity_Itf getEntityFromMapValueByLogicalName (final Map<String, Object> p_map_valueByColumnName)
   {
      final AdresseEntity_Itf v_entity = new AdresseEntity ();
      v_entity.setId((Long) p_map_valueByColumnName.get(AdresseColumns_Enum.adresse_id.getLogicalColumnName()));
      v_entity.set_rue((String) p_map_valueByColumnName.get(AdresseColumns_Enum.rue.getLogicalColumnName ()));
      v_entity.set_ville((String) p_map_valueByColumnName.get(AdresseColumns_Enum.ville.getLogicalColumnName ()));
      v_entity.set_codePostal((String) p_map_valueByColumnName.get(AdresseColumns_Enum.codePostal.getLogicalColumnName ()));
      v_entity.set_personneAdresses_id((Long) p_map_valueByColumnName.get(AdresseColumns_Enum.personneAdresses_id.getLogicalColumnName()));
      v_entity.set_xdmaj((Date) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName()));
      v_entity.set_xtopsup((XtopSup) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName()));
      return v_entity;
   }

   
   // Start of user code Methodes AdresseDao

   // End of user code

}
