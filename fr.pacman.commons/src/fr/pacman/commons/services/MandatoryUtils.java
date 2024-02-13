package fr.pacman.commons.services;

import org.eclipse.emf.common.util.EList;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Attribute;
import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.environment.Property;
import org.obeonetwork.dsl.environment.Reference;

/**
 * Classe de services pour vérifier la contrainte obligatoire sur les attributs ou les références.
 * 
 * @author MINARM
 */
public final class MandatoryUtils
{

   /**
    * Constructeur privé pour classe finale.
    */
   private MandatoryUtils ()
   {
      super();
   }

   /**
    * Vérifie qu'une propriété (Entity) est obligatoire.
    * 
    * @param p_property
    *           la propriété
    * @return true si la propriété est obligatoire, false sinon
    */
   public static boolean isMandatoryEntity (final Property p_property)
   {
      if (p_property instanceof Attribute)
      {
         final Attribute v_attribute = (Attribute) p_property;
         final MultiplicityKind v_multiplicity = v_attribute.getMultiplicity();
         return isAttributeMandatory(v_multiplicity);
      }
      else if (p_property instanceof Reference)
      {
         final Reference v_reference = (Reference) p_property;
         final MultiplicityKind v_multiplicity = v_reference.getMultiplicity();
         final Reference v_oppositeReference = v_reference.getOppositeOf();
         final MultiplicityKind v_oppositeMultiplicity;
         if (v_oppositeReference == null)
         {
            v_oppositeMultiplicity = null;
         }
         else
         {
            v_oppositeMultiplicity = v_oppositeReference.getMultiplicity();
         }
         return isReferenceMandatory(v_multiplicity, v_oppositeMultiplicity);
      }
      else
      {
         throw new IllegalArgumentException("Cas non prévu pour p_property=" + p_property);
      }
   }

   /**
    * Vérifie qu'une propriété (SOA) est obligatoire.
    * 
    * @param p_property
    *           la propriété
    * @return true si la propriété est obligatoire, false sinon
    */
   public static boolean isMandatorySoa (final Property p_property)
   {
      if (p_property instanceof Attribute)
      {
         final Attribute v_attribute = (Attribute) p_property;
         final MultiplicityKind v_multiplicity = v_attribute.getMultiplicity();
         return isAttributeMandatory(v_multiplicity);
      }
      else if (p_property instanceof Reference)
      {
         final Reference v_reference = (Reference) p_property;
         final MultiplicityKind v_multiplicity = v_reference.getMultiplicity();
         final Reference v_oppositeReference = v_reference.getOppositeOf();
         final MultiplicityKind v_oppositeMultiplicity;
         if (v_oppositeReference == null)
         {
            v_oppositeMultiplicity = null;
         }
         else
         {
            v_oppositeMultiplicity = v_oppositeReference.getMultiplicity();
         }
         return isReferenceMandatory(v_multiplicity, v_oppositeMultiplicity);
      }
      return false;
   }

   /**
    * Recupere une liste de proprietes concernees par une annotation et verifie si toutes les proprietes sont obligatoires.
    * 
    * @param p_lstAttributes
    * @return
    */
   public static Boolean isMandatoryAnnotationProperties (final Entity p_entity, final String p_lstStProperties)
   {
      String[] v_lstStrProperties = p_lstStProperties.split(",");
      for (String v_strProperty : v_lstStrProperties)
      {
         // La propriete doit absolument se presenter sous la forme XXXX:[ASC|DESC] 
         v_strProperty = v_strProperty.substring(0, v_strProperty.indexOf(":"));
         EList<Property> v_lstProperties = p_entity.getProperties();
         for (Property v_property : v_lstProperties)
            if (v_property.getName().equals(v_strProperty))
               if (!isMandatoryEntity(v_property))
                  return Boolean.FALSE;
      }
      return Boolean.TRUE;
   }

   /**
    * Vérifie qu'un attribut est obligatoire.
    * 
    * @param p_multiplicity
    *           la multiplicité de l'attribut
    * @return true si l'attribut est obligatoire, false sinon
    */
   private static boolean isAttributeMandatory (final MultiplicityKind p_multiplicity)
   {
      // [query isAttributeMandatory(a : Attribute) : Boolean =
      // a.multiplicity = MultiplicityKind::ONE
      // or a.multiplicity = MultiplicityKind::ONE_STAR
      // /]
      return p_multiplicity == MultiplicityKind.ONE_LITERAL || p_multiplicity == MultiplicityKind.ONE_STAR_LITERAL;
   }

   /**
    * Vérifie qu'une référence est obligatoire.
    * 
    * @param p_multiplicity
    *           la multiplicité de la référence
    * @param p_oppositeMultiplicity
    *           la multiplicité de la référence opposée s'il y en a une, false sinon
    * @return true si la référence est obligatoire, false sinon
    */
   private static boolean isReferenceMandatory (final MultiplicityKind p_multiplicity,
            final MultiplicityKind p_oppositeMultiplicity)
   {
      if (p_oppositeMultiplicity == null)
      {
         return p_multiplicity == MultiplicityKind.ONE_LITERAL || p_multiplicity == MultiplicityKind.ONE_STAR_LITERAL;
      }
      else
      {
         return p_multiplicity == MultiplicityKind.ONE_LITERAL || p_oppositeMultiplicity == MultiplicityKind.ONE_LITERAL
                  || p_oppositeMultiplicity == MultiplicityKind.ONE_STAR_LITERAL;
      }
   }

}
