/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.impl_jdbc.annuaire;
// Start of user code for imports

import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.api.ApplicationAutoFieldsColumns_Enum;
import fr.application.persistence.api.annuaire.CompetenceColumns_Enum;
import fr.application.persistence.api.annuaire.CompetenceDao_Itf;
import fr.application.persistence.api.annuaire.CompetenceEntity_Itf;
import fr.application.persistence.api.annuaire.PersonneColumns_Enum;
import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.application.types.enums.TypeCompetence_Enum;
import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO Competence.
 * @author safr@n
 */
public class CompetenceDao extends DaoJdbc_Abs<CompetenceEntity_Itf, CompetenceColumns_Enum> implements CompetenceDao_Itf
{
   // CONSTANTES

   // Constantes CompetenceDao
   // Start of user code Constantes CompetenceDao

   // End of user code

   // ATTRIBUTS

   // Attributs CompetenceDao
   // Start of user code Attributs CompetenceDao

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public CompetenceDao ()
   {
      super (CompetenceColumns_Enum.c_tableName, CompetenceColumns_Enum.values (), ApplicationAutoFieldsColumns_Enum.values ());
   }


   @Override
   public List<PersonneEntity_Itf> findListDisposeByCompetence (final Long p_Competence_id)
   {
      // utilisation de la table de jointure de la relation N-N
      final String v_jointureSql = " inner join " + CompetenceColumns_Enum.c_tableNameCompetenceDispose + " on "
               + PersonneColumns_Enum.c_tableName + "." + PersonneColumns_Enum.personne_id + " = "
               + CompetenceColumns_Enum.c_tableNameCompetenceDispose + "." + PersonneColumns_Enum.personne_id
               + " where " + CompetenceColumns_Enum.c_tableNameCompetenceDispose + "." + CompetenceColumns_Enum.competence_id + " = :competence_id";
      final Map<String, Long> v_params = Collections.singletonMap ("competence_id", p_Competence_id);
      return ApplicationParamPersistence.getUserPersistence().getPersonneDao().findByCriteria (v_jointureSql, v_params);
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final CompetenceEntity_Itf p_Entity)
   {
      final Map<String, Object> v_map_valueByColumnName = new LinkedHashMap<>();
      v_map_valueByColumnName.put(CompetenceColumns_Enum.competence_id.getLogicalColumnName(), p_Entity.getId());
      v_map_valueByColumnName.put(CompetenceColumns_Enum.libelle.getLogicalColumnName(), p_Entity.get_libelle());
      v_map_valueByColumnName.put(CompetenceColumns_Enum.typecompetence.getLogicalColumnName(), p_Entity.get_typecompetence().getValue());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName(), p_Entity.get_xdmaj());
      v_map_valueByColumnName.put(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName(), p_Entity.get_xtopsup().get_value());
      return v_map_valueByColumnName;
   }

   @Override
   protected CompetenceEntity_Itf getEntityFromMapValueByLogicalName (final Map<String, Object> p_map_valueByColumnName)
   {
      final CompetenceEntity_Itf v_entity = new CompetenceEntity ();
      v_entity.setId((Long) p_map_valueByColumnName.get(CompetenceColumns_Enum.competence_id.getLogicalColumnName()));
      v_entity.set_libelle((String) p_map_valueByColumnName.get(CompetenceColumns_Enum.libelle.getLogicalColumnName ()));
      v_entity.set_typecompetence( TypeCompetence_Enum.getEnumByValue ((String)p_map_valueByColumnName.get(CompetenceColumns_Enum.typecompetence.getLogicalColumnName ())));
      v_entity.set_xdmaj((Date) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xdmaj.getLogicalColumnName()));
      v_entity.set_xtopsup((XtopSup) p_map_valueByColumnName.get(ApplicationAutoFieldsColumns_Enum.xtopsup.getLogicalColumnName()));
      return v_entity;
   }

   // Methodes CompetenceDao
   // Start of user code Methodes CompetenceDao

   // End of user code

}
