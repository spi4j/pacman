/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import fr.test.persistence.api.main.PurchaseOrderColumnsEnum;
import fr.test.persistence.api.main.PurchaseOrderDao;
import fr.test.persistence.api.main.PurchaseOrderEntity;
import fr.test.types.enums.StatusEnum;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO PurchaseOrder.
 * @author safr@n
 */
public class PurchaseOrderDaoImpl extends DaoJdbc_Abs<PurchaseOrderEntity, PurchaseOrderColumnsEnum> implements PurchaseOrderDao
{
   // CONSTANTES

   // Constantes PurchaseOrderDaoImpl
   // Start of user code 7c3942e6a67fc3468c9930a996b60c37

   // End of user code

   // ATTRIBUTS

   // Attributs PurchaseOrderDaoImpl
   // Start of user code 218114cf103e074227053c17cf09018d

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public PurchaseOrderDaoImpl ()
   {
      super (PurchaseOrderColumnsEnum.TABLENAME, PurchaseOrderColumnsEnum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final PurchaseOrderEntity entity)
   {
      final Map<String, Object> valueByColumnName = new LinkedHashMap<>();
      valueByColumnName.put(PurchaseOrderColumnsEnum.purchaseorder_id.getLogicalColumnName(), entity.getId());
      valueByColumnName.put(PurchaseOrderColumnsEnum.cardNumber.getLogicalColumnName(), entity.getCardNumber());
      valueByColumnName.put(PurchaseOrderColumnsEnum.totalAmount.getLogicalColumnName(), entity.getTotalAmount());
      valueByColumnName.put(PurchaseOrderColumnsEnum.shoppingCart_id.getLogicalColumnName(), entity.getShoppingCart_id());
      valueByColumnName.put(PurchaseOrderColumnsEnum.user_id.getLogicalColumnName(), entity.getUser_id());
      return valueByColumnName;
   }

   @Override
   protected PurchaseOrderEntity getEntityFromMapValueByLogicalName (final Map<String, Object> valueByColumnName)
   {
      final PurchaseOrderEntity entity = new PurchaseOrderEntityImpl ();
      entity.setId((Long) valueByColumnName.get(PurchaseOrderColumnsEnum.purchaseorder_id.getLogicalColumnName()));
      entity.setCardNumber((String) valueByColumnName.get(PurchaseOrderColumnsEnum.cardNumber.getLogicalColumnName ()));
      entity.setTotalAmount((Integer) valueByColumnName.get(PurchaseOrderColumnsEnum.totalAmount.getLogicalColumnName ()));
      entity.setShoppingCart_id((Long) valueByColumnName.get(PurchaseOrderColumnsEnum.shoppingCart_id.getLogicalColumnName()));
      entity.setUser_id((Long) valueByColumnName.get(PurchaseOrderColumnsEnum.user_id.getLogicalColumnName()));
      return entity;
   }

   // Methodes PurchaseOrderDaoImpl
   // Start of user code fc1c871fea1049effb5ab332c962d18d

   // End of user code

}
