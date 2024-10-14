/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import fr.test.persistence.api.main.ShoppingCartColumnsEnum;
import fr.test.persistence.api.main.ShoppingCartDao;
import fr.test.persistence.api.main.ShoppingCartEntity;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO ShoppingCart.
 * @author safr@n
 */
public class ShoppingCartDaoImpl extends DaoJdbc_Abs<ShoppingCartEntity, ShoppingCartColumnsEnum> implements ShoppingCartDao
{
   // CONSTANTES

   // Constantes ShoppingCartDaoImpl
   // Start of user code 1ab53e8ca217869cbfed4517c4af8b67

   // End of user code

   // ATTRIBUTS

   // Attributs ShoppingCartDaoImpl
   // Start of user code 7d853b03df70a2991a84a57153a5a8b9

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public ShoppingCartDaoImpl ()
   {
      super (ShoppingCartColumnsEnum.TABLENAME, ShoppingCartColumnsEnum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final ShoppingCartEntity entity)
   {
      final Map<String, Object> valueByColumnName = new LinkedHashMap<>();
      valueByColumnName.put(ShoppingCartColumnsEnum.shoppingcart_id.getLogicalColumnName(), entity.getId());
      valueByColumnName.put(ShoppingCartColumnsEnum.status.getLogicalColumnName(), entity.getStatus().getValue());
      valueByColumnName.put(ShoppingCartColumnsEnum.userCarts_id.getLogicalColumnName(), entity.getUserCarts_id());
      return valueByColumnName;
   }

   @Override
   protected ShoppingCartEntity getEntityFromMapValueByLogicalName (final Map<String, Object> valueByColumnName)
   {
      final ShoppingCartEntity entity = new ShoppingCartEntityImpl ();
      entity.setId((Long) valueByColumnName.get(ShoppingCartColumnsEnum.shoppingcart_id.getLogicalColumnName()));
      entity.setStatus( StatusEnum.getEnumByValue ((String)valueByColumnName.get(ShoppingCartColumnsEnum.status.getLogicalColumnName ())));
      entity.setUserCarts_id((Long) valueByColumnName.get(ShoppingCartColumnsEnum.userCarts_id.getLogicalColumnName()));
      return entity;
   }

   // Methodes ShoppingCartDaoImpl
   // Start of user code 5362b2fca713292c0ba63452794706fe

   // End of user code

}
