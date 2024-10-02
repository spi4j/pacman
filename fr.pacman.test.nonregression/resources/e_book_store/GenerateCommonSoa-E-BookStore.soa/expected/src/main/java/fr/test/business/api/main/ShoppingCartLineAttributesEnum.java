/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.business.api.main;
// Start of user code for imports

;

import fr.spi4j.business.dto.AttributesNames_Itf;
import fr.spi4j.business.dto.DtoAttributeHelper;
import fr.test.business.api.main.BookDto;
import fr.test.types.enums.StatusEnum;
import java.lang.reflect.Method;
import java.util.Date;

// End of user code

/**
 * L'énumération définissant les informations de chaque attribut pour le type 'ShoppingCartLine'.
 * @author safr@n
 */
public enum ShoppingCartLineAttributesEnum implements AttributesNames_Itf
{
   // Enumeration Attributs
   // Start of user code 2a41b154093266d124969adf9885873d

   // End of user code

   /** id. */
   id("id", "id", Long.class, true, -1),
   /** quantity. */
   quantity("quantity", "", Integer.class, true, -1),
   /** book. */
   book("book", "", BookDto.class, true, -1);

   /** Le nom de l'attribut. */
   private final String name;

   /** La description de l'attribut. */
   private final String description;

   /** Le type associé à l'attribut. */
   private final Class<?> type;

   /** Est-ce que la saisie de la valeur est obligatoire pour cet attribut ? */
   private final boolean mandatory;

   /** La taille de l'attribut. */
   private final int size;

   /** La méthode du getter. */
   private transient Method getterMethod;

   /** La méthode du setter. */
   private transient Method setterMethod;

   /**
    * Constructeur.
    * @param name
    *           (In)(*) Le nom de l'attribut.
    * @param description
    *           (In)(*) La description de l'attribut.
    * @param classType
    *           (In)(*) Le type de l'attribut.
    * @param mandatory
    *           (In)(*) Est-ce que la saisie de la valeur est obligatoire pour cette colonne?
    * @param size
    *           (In)(*) La taille de la colonne
    */
   private ShoppingCartLineAttributesEnum (final String name, final String description, final Class<?> classType, final boolean mandatory, final int size)
   {
      this.name = name;
      this.description = description;
      this.type = classType;
      this.mandatory = mandatory;
      this.size = size;
   }


   @Override
   public String getName ()
   {
      return name;
   }

   @Override
   public String getDescription ()
   {
      return description;
   }

   @Override
   public Class<?> getType ()
   {
      return type;
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
   public Method getGetterMethod ()
   {
      if (getterMethod == null)
      {
         getterMethod = DtoAttributeHelper.getInstance().getGetterMethodForAttribute(ShoppingCartLineDto.class, getName());
      }
      return getterMethod;
   }

   @Override
   public Method getSetterMethod ()
   {
      if (setterMethod == null)
      {
         setterMethod = DtoAttributeHelper.getInstance().getSetterMethodForAttribute(ShoppingCartLineDto.class, getName(), getType());
      }
      return setterMethod;
   }

   @Override
   public String toString ()
   {
      return description;
   }
}