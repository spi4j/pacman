/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.jdbc.main;
// Start of user code for imports

import fr.spi4j.persistence.dao.jdbc.DaoJdbc_Abs;
import fr.test.persistence.api.main.BookColumnsEnum;
import fr.test.persistence.api.main.BookDao;
import fr.test.persistence.api.main.BookEntity;
import fr.test.types.enums.StatusEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// End of user code

/**
 * Implémentation JDBC du DAO Book.
 * @author safr@n
 */
public class BookDaoImpl extends DaoJdbc_Abs<BookEntity, BookColumnsEnum> implements BookDao
{
   // CONSTANTES

   // Constantes BookDaoImpl
   // Start of user code bfd66d3a61afb489a8fcb8667c19d0cf

   // End of user code

   // ATTRIBUTS

   // Attributs BookDaoImpl
   // Start of user code b436f1e6c1a90a295a7b5f491b07e9a8

   // End of user code

   // METHODES

   /**
    * Constructeur par défaut.
    */
   public BookDaoImpl ()
   {
      super (BookColumnsEnum.TABLENAME, BookColumnsEnum.values ());
   }

   @Override
   protected Map<String, Object> getMapValueByLogicalNameFromEntity (final BookEntity entity)
   {
      final Map<String, Object> valueByColumnName = new LinkedHashMap<>();
      valueByColumnName.put(BookColumnsEnum.book_id.getLogicalColumnName(), entity.getId());
      valueByColumnName.put(BookColumnsEnum.title.getLogicalColumnName(), entity.getTitle());
      valueByColumnName.put(BookColumnsEnum.price.getLogicalColumnName(), entity.getPrice());
      valueByColumnName.put(BookColumnsEnum.image.getLogicalColumnName(), entity.getImage());
      valueByColumnName.put(BookColumnsEnum.type.getLogicalColumnName(), entity.getType());
      valueByColumnName.put(BookColumnsEnum.authorWrote_id.getLogicalColumnName(), entity.getAuthorWrote_id());
      return valueByColumnName;
   }

   @Override
   protected BookEntity getEntityFromMapValueByLogicalName (final Map<String, Object> valueByColumnName)
   {
      final BookEntity entity = new BookEntityImpl ();
      entity.setId((Long) valueByColumnName.get(BookColumnsEnum.book_id.getLogicalColumnName()));
      entity.setTitle((String) valueByColumnName.get(BookColumnsEnum.title.getLogicalColumnName ()));
      entity.setPrice((Integer) valueByColumnName.get(BookColumnsEnum.price.getLogicalColumnName ()));
      entity.setImage((String) valueByColumnName.get(BookColumnsEnum.image.getLogicalColumnName ()));
      entity.setType((String) valueByColumnName.get(BookColumnsEnum.type.getLogicalColumnName ()));
      entity.setAuthorWrote_id((Long) valueByColumnName.get(BookColumnsEnum.authorWrote_id.getLogicalColumnName()));
      return entity;
   }

   // Methodes BookDaoImpl
   // Start of user code d3a65ee8603d397906dc26c86b73f801

   // End of user code

}
