/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import fr.test.persistence.api.main.AuthorColumnsEnum;
import fr.test.persistence.api.main.AuthorDao;
import fr.test.persistence.api.main.AuthorEntity;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO Author.
 * @author safr@n
 */
public class AuthorDaoImpl extends DaoJdbc_Abs<AuthorEntity, AuthorColumnsEnum> implements AuthorDao
{
   // CONSTANTES

   // Constantes AuthorDaoImpl
   // Start of user code 0b94ecea37aba0d6b6408a0280c51fad

   // End of user code

   // ATTRIBUTS

   // Attributs AuthorDaoImpl
   // Start of user code 8e81091fe68f8cc247fa9b645fb281f8

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public AuthorDaoImpl ()
   {
      super (AuthorColumnsEnum.TABLENAME, AuthorColumnsEnum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final AuthorEntity entity)
   {
      final Map<String, Object> valueByColumnName = new LinkedHashMap<>();
      valueByColumnName.put(AuthorColumnsEnum.author_id.getLogicalColumnName(), entity.getId());
      valueByColumnName.put(AuthorColumnsEnum.name.getLogicalColumnName(), entity.getName());
      valueByColumnName.put(AuthorColumnsEnum.bookWrittenBy_id.getLogicalColumnName(), entity.getBookWrittenBy_id());
      return valueByColumnName;
   }

   @Override
   protected AuthorEntity getEntityFromMapValueByLogicalName (final Map<String, Object> valueByColumnName)
   {
      final AuthorEntity entity = new AuthorEntityImpl ();
      entity.setId((Long) valueByColumnName.get(AuthorColumnsEnum.author_id.getLogicalColumnName()));
      entity.setName((String) valueByColumnName.get(AuthorColumnsEnum.name.getLogicalColumnName ()));
      entity.setBookWrittenBy_id((Long) valueByColumnName.get(AuthorColumnsEnum.bookWrittenBy_id.getLogicalColumnName()));
      return entity;
   }

   // Methodes AuthorDaoImpl
   // Start of user code ceb3490a3ec1c9be7e0521d9416cd0f6

   // End of user code

}
