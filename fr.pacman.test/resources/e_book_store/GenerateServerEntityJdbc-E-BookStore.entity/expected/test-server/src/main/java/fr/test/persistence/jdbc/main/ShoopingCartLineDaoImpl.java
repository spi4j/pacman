/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import fr.test.main.StatusEnum;
import fr.test.persistence.api.main.ShoopingCartLineColumnsEnum;
import fr.test.persistence.api.main.ShoopingCartLineDao;
import fr.test.persistence.api.main.ShoopingCartLineEntity;
import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO ShoopingCartLine.
 * @author safr@n
 */
public class ShoopingCartLineDaoImpl extends DaoJdbc_Abs<ShoopingCartLineEntity, ShoopingCartLineColumnsEnum> implements ShoopingCartLineDao
{
   // CONSTANTES

   
   // Start of user code 08eb38a0e5d839f04366e05efba14d54

   // End of user code

   // ATTRIBUTS

   
   // Start of user code 2f46d119efdde7af77dfb223d26e8e40

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public ShoopingCartLineDaoImpl ()
   {
      super (ShoopingCartLineColumnsEnum.TABLENAME, ShoopingCartLineColumnsEnum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final ShoopingCartLineEntity entity)
   {
      final Map<String, Object> valueByColumnName = new LinkedHashMap<>();
      valueByColumnName.put(ShoopingCartLineColumnsEnum.shoopingCartLine_id.getLogicalColumnName(), entity.getId());
      valueByColumnName.put(ShoopingCartLineColumnsEnum.quantity.getLogicalColumnName(), entity.getQuantity());
      valueByColumnName.put(ShoopingCartLineColumnsEnum.book_id.getLogicalColumnName(), entity.getBook_id());
      valueByColumnName.put(ShoopingCartLineColumnsEnum.shoppingCartLines_id.getLogicalColumnName(), entity.getShoppingCartLines_id());
      return valueByColumnName;
   }

   @Override
   protected ShoopingCartLineEntity getEntityFromMapValueByLogicalName (final Map<String, Object> valueByColumnName)
   {
      final ShoopingCartLineEntity entity = new ShoopingCartLineEntityImpl ();
      entity.setId((Long) valueByColumnName.get(ShoopingCartLineColumnsEnum.shoopingCartLine_id.getLogicalColumnName()));
      entity.setQuantity((Integer) valueByColumnName.get(ShoopingCartLineColumnsEnum.quantity.getLogicalColumnName ()));
      entity.setBook_id((Long) valueByColumnName.get(ShoopingCartLineColumnsEnum.book_id.getLogicalColumnName()));
      entity.setShoppingCartLines_id((Long) valueByColumnName.get(ShoopingCartLineColumnsEnum.shoppingCartLines_id.getLogicalColumnName()));
      return entity;
   }

   
   // Start of user code 80d4e348fcb497edb6444c9d6229b467

   // End of user code

}
