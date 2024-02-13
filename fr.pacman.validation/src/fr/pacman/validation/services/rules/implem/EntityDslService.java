package fr.pacman.validation.services.rules.implem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.obeonetwork.dsl.environment.Attribute;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Property;
import org.obeonetwork.dsl.environment.Reference;
import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.environment.Type;

import fr.pacman.validation.services.DslValidationRule;
import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRuleService_Itf;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Service Acceleo vérifiant les règles du DSM Requirement.
 */
public class EntityDslService extends DslService_Abs implements DslValidationRuleService_Itf
{
   /**
    * Vérification de l'exigence.
    * 
    * @param p_reference
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check001)
   public List<DslValidationRuleNokBean> checkOnlyOneRelationNavigable_sce (final org.obeonetwork.dsl.environment.Reference p_reference, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      if (p_reference.isNavigable() && p_reference.getOppositeOf() != null && p_reference.getOppositeOf().isNavigable())
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check001, p_reference));
      }
      return v_objetsInvalides;
   }

   /**
    * 
    * @param p_block
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check002)
   public List<DslValidationRuleNokBean> checkEntityNameUnique_sce (final org.obeonetwork.dsl.environment.Namespace p_block, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final Map<String, Integer> v_entitiesMap = new HashMap<String, Integer>();
   Entity   v_entity=null;
      for (final Type v_type : p_block.getTypes())
      {
         if(v_type instanceof Entity){
            v_entity=(Entity)v_type;
         if (v_entitiesMap.containsKey(v_entity.getName()))
         {
            int v_nbOccur = v_entitiesMap.get(v_entity.getName());
            v_nbOccur++;
            v_entitiesMap.put(v_entity.getName(), v_nbOccur);
         }
         else
         {
            v_entitiesMap.put(v_entity.getName(), 1);
         }
      }
      }

      // on vérifie dans la map si une entité a été trouvée plusieurs fois
      for (Entry<String, Integer> v_entry : v_entitiesMap.entrySet())
      {
         if (v_entry.getValue() >= 2)
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check002, p_block));
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Deux attributs ou références dans la même
    * entity ne doivent pas avoir le même nom.
    * 
    * @param p_entity
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check003)
   public List<DslValidationRuleNokBean> checkEntityAttrPropUnique_sce (final org.obeonetwork.dsl.entity.Entity p_entity, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final List<Property> v_allProperties = p_entity.getProperties();
      v_objetsInvalides.addAll(checkIfPropertiesIsUnique(p_entity, v_allProperties));
      return v_objetsInvalides;
   }

   /**
    * Permet de vérifier si l'entité n'a pas de doublon dans ses propriétés.
    * 
    * @param p_entity
    *           L'entité à vérifier.
    * @param p_properties
    *           La liste des propriétés.
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   private List<DslValidationRuleNokBean> checkIfPropertiesIsUnique (final Entity p_entity, final List<Property> p_properties)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final Map<String, Integer> v_entitiesMap = new HashMap<String, Integer>();
      for (final Property v_Property : p_properties)
      {
         if (v_entitiesMap.containsKey(v_Property.getName()))
         {
            int v_nbOccur = v_entitiesMap.get(v_Property.getName());
            v_nbOccur++;
            v_entitiesMap.put(v_Property.getName(), v_nbOccur);
         }
         else
         {
            v_entitiesMap.put(v_Property.getName(), 1);
         }
      }
      for (Entry<String, Integer> v_entry : v_entitiesMap.entrySet())
      {
         if (v_entry.getValue() >= 2)
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check003, p_entity));
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Les attributs doivent avoir une multiplicité
    * 0..1 ou 1.
    * 
    * @param p_attribute
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check004)
   public List<DslValidationRuleNokBean> checkAttributeMultiplicity_sce (final org.obeonetwork.dsl.environment.Attribute p_attribute, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      // on regarde si la multiplicité de l'attribut est bien de type 0..1 ou 1,
      // erreur sinon
      if (p_attribute.getMultiplicity() == MultiplicityKind.ONE_STAR_LITERAL || p_attribute.getMultiplicity() == MultiplicityKind.ZERO_STAR_LITERAL)
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check004, p_attribute));

      }

      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Le Namespace doit avoir un nom.
    * 
    * @param p_block
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check013)
   public List<DslValidationRuleNokBean> checkBlockNameNotEmpty_sce (final org.obeonetwork.dsl.environment.Namespace p_block, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      // Si pas de Namespace ou pas de nom de Namespace
      if ((p_block == null) || (p_block.getName() == null) || ("".equals(p_block.getName()) == true))
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check013, p_block));
      }

      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Pas de caractères spéciaux dans les noms.
    * 
    * @param p_block
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check005)
   public List<DslValidationRuleNokBean> checkValidityName_sce (final org.obeonetwork.dsl.environment.Namespace p_block, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      // TODO Ne fonctionne pas ?

      // Si le Namespace existe et possède un nom
      if (p_block != null && p_block.getName() != null && !p_block.getName().trim().isEmpty())
      {
         // Pattern pour le Regex: faux sur les Strings comportant des
         // caracteres
         // speciaux
         final String v_pattern = "\\p{Punct}";
         if (p_block.getName().matches(v_pattern))
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check005, p_block));
         }
         Entity   v_entity=null;
         for (final Type v_type : p_block.getTypes())
         {
            if(v_type instanceof Entity){
               v_entity=(Entity)v_type;
            if (v_entity.getName().matches(v_pattern))
            {
               v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check005, v_entity));
            }
            final EList<Property> v_properties = v_entity.getProperties();
            for (final Property v_property : v_properties)
            {
               if (v_property.getName().matches(v_pattern))
               {
                  v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check005, v_property));
               }
            }
         }
      }
      }

      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Eviter les accents et les espaces dans les
    * noms.
    * 
    * @param p_block
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check006)
   public List<DslValidationRuleNokBean> checkAccentSpaceName_sce (final org.obeonetwork.dsl.environment.Namespace p_block, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      // Si le Namespace existe et possède un nom
      if (p_block != null && p_block.getName() != null && !p_block.getName().trim().isEmpty())
      {
         // Pattern pour le Regex: faux sur les Strings comportant des
         // caracteres
         // speciaux, accent ou espace
         final String v_pattern = "[a-zA-Z][0-9a-zA-Z]*";
         // verification du nom par rapport au pattern, erreur si il ne
         // correspond
         // pas au pattern
         if (!p_block.getName().matches(v_pattern))
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check006, p_block));
         }
         // parcourt des entités
         Entity v_entity=null;
         for (final Type v_type : p_block.getTypes())
         {
            if(v_type instanceof Entity){
               v_entity=(Entity)v_type;
            if (!v_entity.getName().matches(v_pattern))
            {
               v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check006, v_entity));
            }
            final EList<Property> v_properties = v_entity.getProperties();
            for (final Property v_property : v_properties)
            {
               if (!v_property.getName().matches(v_pattern))
               {
                  v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check006, v_property));
               }
            }
            }
         }
      }

      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Eviter les relations 1..* (non géré, utiliser
    * plutôt 0..*)
    * 
    * @param p_reference
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check007)
   public List<DslValidationRuleNokBean> checkRefMultiplicity_sce (final org.obeonetwork.dsl.environment.Reference p_reference, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      // on regarde si la multiplicité de la reference est bien de type 0..1 ou
      // 1, erreur sinon
      if (p_reference.getMultiplicity() == MultiplicityKind.ONE_STAR_LITERAL)
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check007, p_reference));

      }

      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Eviter les relations isComposite (non géré)
    * 
    * @param p_reference
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check008)
   public List<DslValidationRuleNokBean> checkIfComposite_sce (final org.obeonetwork.dsl.environment.Reference p_reference, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      if (p_reference.isIsComposite())
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check008, p_reference));

      }
      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Pas de sous Namespace.
    * 
    * @param p_block
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check010)
   public List<DslValidationRuleNokBean> checkIfBlockUnique_sce (final org.obeonetwork.dsl.environment.Namespace p_block, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      if (!p_block.getOwnedNamespaces().isEmpty())
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check010, p_block));
      }
      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Pas d'attributs marqué comme id.
    * 
    * @param p_entity
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check011)
   public List<DslValidationRuleNokBean> checkIfAttributeIsId_sce (final org.obeonetwork.dsl.entity.Entity p_entity, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      for (Attribute v_attribute : p_entity.getAttributes())
      {
         if (v_attribute.isIsIdentifier())
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check011, v_attribute));
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Uniquement les types primitifs sauf pour
    * l'Email.
    * 
    * @param p_entity
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */

   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check012)
   public List<DslValidationRuleNokBean> checkIfTypeAttributeIsPrimitif_sce (final org.obeonetwork.dsl.entity.Entity p_entity, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      for (final Attribute v_attribute : p_entity.getAttributes())
      {
         if (v_attribute.getType() == null)
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check012, v_attribute));
         }
         else
         {
            // TODO Le type Email est interdit
            final String v_t = v_attribute.getType().getName();
            if (v_t != null
                  && !(v_t.equalsIgnoreCase("integer") || v_t.equalsIgnoreCase("boolean") || v_t.equalsIgnoreCase("String") || v_t.equalsIgnoreCase("double") || v_t.equalsIgnoreCase("date")
                        || v_t.equalsIgnoreCase("Email") || v_t.equalsIgnoreCase("long") || v_t.equalsIgnoreCase("float")))
            {
               v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check012, v_attribute));
            }
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence : Description fortement conseillée.
    * 
    * @param p_entity
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslEntity_Check009)
   public List<DslValidationRuleNokBean> checkDescription_sce (final org.obeonetwork.dsl.entity.Entity p_entity, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      // si la description de v_entity est vide, warning!
      if (p_entity.getDescription() == null || p_entity.getDescription().isEmpty())
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check009, p_entity));
      }
      final EList<Reference> v_references = p_entity.getReferences();
      for (final Reference v_reference : v_references)
      {
         if (v_reference.getDescription() == null || v_reference.getDescription().isEmpty())
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check009, v_reference));
         }
      }

      // on met tous les attributs d'une entité dans une liste
      final EList<Attribute> v_attributes = p_entity.getAttributes();
      for (final Attribute v_attribute : v_attributes)
      {
         if (v_attribute.getDescription() == null || v_attribute.getDescription().isEmpty())
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check009, v_attribute));
         }
      }
      return v_objetsInvalides;
   }

}
