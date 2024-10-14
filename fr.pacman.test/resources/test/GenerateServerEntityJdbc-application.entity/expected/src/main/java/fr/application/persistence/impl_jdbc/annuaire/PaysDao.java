/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.persistence.api.ApplicationAutoFieldsColumns_Enum;
import fr.application.persistence.api.annuaire.PaysColumns_Enum;
import fr.application.persistence.api.annuaire.PaysDao_Itf;
import fr.application.persistence.api.annuaire.PaysEntity_Itf;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO Pays.
 * @author safr@n
 */
public class PaysDao extends DaoJdbc_Abs<PaysEntity_Itf, PaysColumns_Enum> implements PaysDao_Itf
{
   // CONSTANTES

   // Constantes PaysDao
   // Start of user code Constantes PaysDao

   // End of user code

   // ATTRIBUTS

   // Attributs PaysDao
   // Start of user code Attributs PaysDao

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public PaysDao ()
   {
      super (PaysColumns_Enum.c_tableName, PaysColumns_Enum.values (), ApplicationAutoFieldsColumns_Enum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final PaysEntity_Itf p_Entity)
   {
      final Map<String, Object> v_map_valueByColumnName = new LinkedHashMap<>();
      v_map_valueByColumnName.put(PaysColumns_Enum.pays_id.getLogicalColumnName(), p_Entity.getId());
      v_map_valueByColumnName.put(PaysColumns_Enum.nom.getLogicalColumnName(), p_Entity.get_nom());
      v_map_valueByColumnName.put(PaysColumns_Enum.capitale.getLogicalColumnName(), p_Entity.get_capitale());
      v_map_valueByColumnName.put(PaysColumns_Enum.personnePays_id.getLogicalColumnName(), p_Entity.get_personnePays_id());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName(), p_Entity.get_xdmaj());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName(), p_Entity.get_xtopsup().get_value());
      return v_map_valueByColumnName;
   }

   @Override
   protected PaysEntity_Itf getEntityFromMapValueByLogicalName (final Map<String, Object> p_map_valueByColumnName)
   {
      final PaysEntity_Itf v_entity = new PaysEntity ();
      v_entity.setId((Long) p_map_valueByColumnName.get(PaysColumns_Enum.pays_id.getLogicalColumnName()));
      v_entity.set_nom((String) p_map_valueByColumnName.get(PaysColumns_Enum.nom.getLogicalColumnName ()));
      v_entity.set_capitale((String) p_map_valueByColumnName.get(PaysColumns_Enum.capitale.getLogicalColumnName ()));
      v_entity.set_personnePays_id((Long) p_map_valueByColumnName.get(PaysColumns_Enum.personnePays_id.getLogicalColumnName()));
      v_entity.set_xdmaj((Date) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName()));
      v_entity.set_xtopsup((XtopSup) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName()));
      return v_entity;
   }

   // Methodes PaysDao
   // Start of user code Methodes PaysDao

   // End of user code

}
