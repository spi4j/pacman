package fr.pacman.commons.services;

import java.util.ArrayList;
import java.util.List;

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
public final class EntityFromDtoUtils
{

   /**
    * Constructeur privé pour classe finale.
    */
   private EntityFromDtoUtils ()
   {
      super();
   }

   /**
    * Retourne les attributs du DTO ainsi que ceux potentiellement hérités par une entité.
    * @param p_dto
    *           le DTO
    * @return les attributs du DTO (hérités ou non)
    */
   public static List<Attribute> getAttributes (final Entity p_entity)
   {
      final List<Attribute> v_finalAttributes = new ArrayList<Attribute>();
      if (p_entity.getAssociatedTypes() != null)
      {
         for (StructuredType v_inheritedType : p_entity.getAssociatedTypes())
         {
            if (v_inheritedType instanceof DTO)
            {
               final DTO v_dto = (DTO) v_inheritedType;
               final EList<org.obeonetwork.dsl.environment.Attribute> v_attributesDto = v_dto.getAttributes();
               for (org.obeonetwork.dsl.environment.Attribute v_entityAttribute : v_attributesDto)
               {
                  if (!entityContainsDTOAttribute(p_entity, v_entityAttribute) && !v_entityAttribute.isIsIdentifier())
                  {
                     @SuppressWarnings("unused")
                     final Attribute v_environmentAttribute = new EntityAttributeIntern(p_entity, v_entityAttribute);
                     // ne pas ajouter l'attribut dans la liste des attributs car cet attribut temporaire a été affecté au Entity juste au dessus
                     // il sera donc ajouté à la fin de cette méthode               
                     v_finalAttributes.add(v_environmentAttribute);
                  }
               }
            }
         }
      }
      return v_finalAttributes;
   }

   /**
    * Classe interne de gestion de recopie des attributs d'entité dans DTO
    * @author MINARM
    */
   private static class EntityAttributeIntern extends AttributeImpl
   {

      private final org.obeonetwork.dsl.environment.Attribute _dtoAttribute;

      /**
       * Construit un attribut de Entity à partir d'un attribut de DTO.
       * @param p_entity
       *           le Entity qui contiendra l'attribut
       * @param p_dtoAttribute
       *           l'attribut d'entité
       */
      public EntityAttributeIntern (final Entity p_entity, final org.obeonetwork.dsl.environment.Attribute p_dtoAttribute)
      {
         super();
         _dtoAttribute = p_dtoAttribute;
         setName(p_dtoAttribute.getName());
         setDescription(p_dtoAttribute.getDescription());
         setMetadatas(p_dtoAttribute.getMetadatas());
         setMultiplicity(p_dtoAttribute.getMultiplicity());
         setType(p_dtoAttribute.getType());
         setContainingType(p_entity);
      }

   }

   /**
    * Retourne true si le DTO contient déjà cet attribut d'entité
    * @param p_entity
    *           le dto
    * @param p_entityAttribute
    *           l'attribut d'entité
    * @return true si le DTO contient déjà cet attribut d'entité, false sinon
    */
   private static boolean entityContainsDTOAttribute (final Entity p_entity,
            final org.obeonetwork.dsl.environment.Attribute p_entityAttribute)
   {
      boolean v_retour = false;
      for (Attribute v_entityAttribute : p_entity.getAttributes())
      {
         // si l'attribut courant est un attribut copié
         // et si cet attribut vient de l'attribut d'entité passé en paramètre
         if (v_entityAttribute instanceof EntityAttributeIntern
                  && ((EntityAttributeIntern) v_entityAttribute)._dtoAttribute == p_entityAttribute)
         {
            v_retour = true;
            break;
         }
      }
      return v_retour;
   }

}
