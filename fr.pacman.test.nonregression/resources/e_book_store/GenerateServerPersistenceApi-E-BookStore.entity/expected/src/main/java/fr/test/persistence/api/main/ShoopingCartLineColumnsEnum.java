/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.api.main;

import fr.spi4j.persistence.entity.ColumnsNames_Itf;
import java.util.Date;

/**
 * L'énumeration définissant les informations de chaque colonne pour le type ShoopingCartLine.
 * @author safr@n
 */
public enum ShoopingCartLineColumnsEnum implements ColumnsNames_Itf
{
   /** shoopingcartline_id. */
   shoopingcartline_id("shoopingcartline_id", "SHOOPINGCARTLINE_ID", Long.class, true, -1, true),
   /** quantity. */
   quantity("quantity", "QUANTITY", Integer.class, true, -1, false),
   /** book_id. */
   book_id("book_id", "BOOK_ID", Long.class, true, -1, false),
   /** shoppingCartLines_id. */
   shoppingCartLines_id("shoppingCartLines_id", "SHOPPINGCART_ID", Long.class, false, -1, false);

   /**
    * Le nom physique de la table.
    */
   public static final String TABLENAME = "SHOOPINGCARTLINE";

   /** Le nom logique de la colonne. */
   private final String logicalColumnName;

   /** Le nom physique de la colonne. */
   private final String physicalColumnName;

   /** Le type associe a la colonne. */
   private final Class<?> typeColumn;

   /** Est-ce que la saisie de la valeur est obligatoire pour cette colonne ? */
   private final boolean mandatory;

   /** La taille de la colonne. */
   private final int size;

   /** Est-ce que la colonne est la cle primaire ? */
   private final boolean id;

   /**
    * Constructeur permettant de spécifier le type de la colonne.
    * @param logicalColumnName
    *           (In)(*) Le nom logique de la colonne.
    * @param physicalColumnName
    *           (In)(*) Le nom physique de la colonne.
    * @param classType
    *           (In)(*) Le type de la colonne.
    * @param mandatory
    *           (In)(*) Est-ce que la saisie de la valeur est obligatoire pour cette colonne?
    * @param size
    *           (In)(*) La taille de la colonne
    * @param id
    *           (In)(*) Est-ce que la colonne est la clé primaire?
    */
   private ShoopingCartLineColumnsEnum (final String logicalColumnName, final String physicalColumnName,
            final Class<?> classType, final boolean mandatory, final int size, final boolean id)
   {
      this.logicalColumnName = logicalColumnName;
      this.physicalColumnName = physicalColumnName;
      this.typeColumn = classType;
      this.mandatory = mandatory;
      this.size = size;
      this.id = id;
   }

   @Override
   public String getLogicalColumnName ()
   {
      return logicalColumnName;
   }

   @Override
   public String getPhysicalColumnName ()
   {
      return physicalColumnName;
   }

   @Override
   public boolean isMandatory ()
   {
      return mandatory;
   }

   @Override
   public int getSize ()
   {
      return size;
   }

   @Override
   public boolean isId ()
   {
      return id;
   }

   @Override
   public Class<?> getTypeColumn ()
   {
      return typeColumn;
   }

   @Override
   public String toString ()
   {
      return physicalColumnName;
   }

   @Override
   public String getTableName ()
   {
      return TABLENAME;
   }

   @Override
   public String getCompletePhysicalName ()
   {
      return getTableName() + '.' + getPhysicalColumnName ();
   }
}
