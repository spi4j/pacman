/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import fr.test.main.StatusEnum;
import fr.test.persistence.api.main.UserColumnsEnum;
import fr.test.persistence.api.main.UserDao;
import fr.test.persistence.api.main.UserEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO User.
 * @author safr@n
 */
public class UserDaoImpl extends DaoJdbc_Abs<UserEntity, UserColumnsEnum> implements UserDao
{
   // CONSTANTES

   
   // Start of user code 4de7963bfd040a4dabcd32b9645018fe

   // End of user code

   // ATTRIBUTS

   
   // Start of user code e6d013d5d23adcd3c8b84b4249e744e3

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public UserDaoImpl ()
   {
      super (UserColumnsEnum.TABLENAME, UserColumnsEnum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final UserEntity entity)
   {
      final Map<String, Object> valueByColumnName = new LinkedHashMap<>();
      valueByColumnName.put(UserColumnsEnum.user_id.getLogicalColumnName(), entity.getId());
      valueByColumnName.put(UserColumnsEnum.name.getLogicalColumnName(), entity.getName());
      valueByColumnName.put(UserColumnsEnum.email.getLogicalColumnName(), entity.getEmail());
      valueByColumnName.put(UserColumnsEnum.password.getLogicalColumnName(), entity.getPassword());
      return valueByColumnName;
   }

   @Override
   protected UserEntity getEntityFromMapValueByLogicalName (final Map<String, Object> valueByColumnName)
   {
      final UserEntity entity = new UserEntityImpl ();
      entity.setId((Long) valueByColumnName.get(UserColumnsEnum.user_id.getLogicalColumnName()));
      entity.setName((String) valueByColumnName.get(UserColumnsEnum.name.getLogicalColumnName ()));
      entity.setEmail((String) valueByColumnName.get(UserColumnsEnum.email.getLogicalColumnName ()));
      entity.setPassword((String) valueByColumnName.get(UserColumnsEnum.password.getLogicalColumnName ()));
      return entity;
   }

   
   // Start of user code 5b7872e65d30c791649164d4382093ca

   // End of user code

}
