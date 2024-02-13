package fr.pacman.commons.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Attribute;
import org.obeonetwork.dsl.environment.DTO;
import org.obeonetwork.dsl.environment.StructuredType;
import org.obeonetwork.dsl.environment.impl.AttributeImpl;

/**
 * Classe utilitaire pour rechercher des DTO.
 * @author MINARM
 */
public final class DtoFromEntityUtils
{

   /**
    * Constructeur privé pour classe finale.
    */
   private DtoFromEntityUtils ()
   {
      super();
   }

   /**
    * Retourne les attributs du DTO ainsi que ceux potentiellement hérités par une entité.
    * @param p_dto
    *           le DTO
    * @return les attributs du DTO (hérités ou non)
    */
   public static List<Attribute> getAttributes (final DTO p_dto)
   {
      
      if ((p_dto != null) && (p_dto.getAssociatedTypes() != null))
      {
         for (StructuredType v_inheritedType : p_dto.getAssociatedTypes())
         {
            if (v_inheritedType instanceof Entity)
            {
               final Entity v_entity = (Entity) v_inheritedType;
               final EList<org.obeonetwork.dsl.environment.Attribute> v_attributesEntity = v_entity.getAttributes();
               for (org.obeonetwork.dsl.environment.Attribute v_entityAttribute : v_attributesEntity)
               {

                  if (!dtoContainsEntityAttribute(p_dto, v_entityAttribute) && !v_entityAttribute.isIsIdentifier())
                  {
                     @SuppressWarnings("unused")
                     final Attribute v_environmentAttribute = new DtoAttributeIntern(p_dto, v_entityAttribute);
                     // ne pas ajouter l'attribut dans la liste des attributs car cet attribut temporaire a été affecté au DTO juste au dessus
                     // il sera donc ajouté à la fin de cette méthode
                     // v_attributes.add(v_environmentAttribute);
                  }
               }
            }
         }
      }
      // Supprimer les attributs en doublon (peut être dus à un héritage)
      final EList<Attribute> v_attributes = p_dto.getAttributes();
      final Set<String> v_attributesNames = new HashSet<String>();
      final List<Attribute> v_finalAttributes = new ArrayList<Attribute>();
      for (final Attribute v_attribute : v_attributes)
      {
         final String v_attributeName = v_attribute.getName();
         if (!v_attributesNames.contains(v_attributeName))
         {
            v_attributesNames.add(v_attributeName);
            v_finalAttributes.add(v_attribute);
         }
      }
      return v_finalAttributes;
   }

   /**
    * Classe interne de gestion de recopie des attributs d'entité dans DTO
    * @author MINARM
    */
   private static class DtoAttributeIntern extends AttributeImpl
   {

      private final org.obeonetwork.dsl.environment.Attribute _entityAttribute;

      /**
       * Construit un attribut de DTO à partir d'un attribut d'entité.
       * @param p_dto
       *           le DTO qui contiendra l'attribut
       * @param p_entityAttribute
       *           l'attribut d'entité
       */
      public DtoAttributeIntern (final DTO p_dto, final org.obeonetwork.dsl.environment.Attribute p_entityAttribute)
      {
         super();
         _entityAttribute = p_entityAttribute;
         setName(p_entityAttribute.getName());
         setDescription(p_entityAttribute.getDescription());
         setMetadatas(p_entityAttribute.getMetadatas());
         setMultiplicity(p_entityAttribute.getMultiplicity());
         setType(p_entityAttribute.getType());
         setContainingType(p_dto);
         
      }

   }

   /**
    * Retourne true si le DTO contient déjà cet attribut d'entité
    * @param p_dto
    *           le dto
    * @param p_entityAttribute
    *           l'attribut d'entité
    * @return true si le DTO contient déjà cet attribut d'entité, false sinon
    */
   private static boolean dtoContainsEntityAttribute (final DTO p_dto,
            final org.obeonetwork.dsl.environment.Attribute p_entityAttribute)
   {
      boolean v_retour = false;
      for (Attribute v_dtoAttribute : p_dto.getAttributes())
      {
         // si l'attribut courant est un attribut copié
         // et si cet attribut vient de l'attribut d'entité passé en paramètre
         if (v_dtoAttribute instanceof DtoAttributeIntern
                  && ((DtoAttributeIntern) v_dtoAttribute)._entityAttribute == p_entityAttribute)
         {
            v_retour = true;
            break;
         }
      }
      return v_retour;
   }

}
