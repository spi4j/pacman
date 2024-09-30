/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.ref;
// Start of user code for imports

import fr.application.persistence.api.ApplicationAutoFieldsColumns_Enum;
import fr.application.persistence.api.ref.GradeColumns_Enum;
import fr.application.persistence.api.ref.GradeDao_Itf;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO Grade.
 * @author safr@n
 */
public class GradeDao extends DaoJdbc_Abs<GradeEntity_Itf, GradeColumns_Enum> implements GradeDao_Itf
{
   // CONSTANTES

   // Constantes GradeDao
   // Start of user code Constantes GradeDao

   // End of user code

   // ATTRIBUTS

   // Attributs GradeDao
   // Start of user code Attributs GradeDao

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public GradeDao ()
   {
      super (GradeColumns_Enum.c_tableName, GradeColumns_Enum.values (), ApplicationAutoFieldsColumns_Enum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final GradeEntity_Itf p_Entity)
   {
      final Map<String, Object> v_map_valueByColumnName = new LinkedHashMap<>();
      v_map_valueByColumnName.put(GradeColumns_Enum.grade_id.getLogicalColumnName(), p_Entity.getId());
      v_map_valueByColumnName.put(GradeColumns_Enum.libelle.getLogicalColumnName(), p_Entity.get_libelle());
      v_map_valueByColumnName.put(GradeColumns_Enum.trigramme.getLogicalColumnName(), p_Entity.get_trigramme());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName(), p_Entity.get_xdmaj());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName(), p_Entity.get_xtopsup().get_value());
      return v_map_valueByColumnName;
   }

   @Override
   protected GradeEntity_Itf getEntityFromMapValueByLogicalName (final Map<String, Object> p_map_valueByColumnName)
   {
      final GradeEntity_Itf v_entity = new GradeEntity ();
      v_entity.setId((Long) p_map_valueByColumnName.get(GradeColumns_Enum.grade_id.getLogicalColumnName()));
      v_entity.set_libelle((String) p_map_valueByColumnName.get(GradeColumns_Enum.libelle.getLogicalColumnName ()));
      v_entity.set_trigramme((String) p_map_valueByColumnName.get(GradeColumns_Enum.trigramme.getLogicalColumnName ()));
      v_entity.set_xdmaj((Date) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName()));
      v_entity.set_xtopsup((XtopSup) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName()));
      return v_entity;
   }

   // Methodes GradeDao
   // Start of user code Methodes GradeDao

   // End of user code

}
