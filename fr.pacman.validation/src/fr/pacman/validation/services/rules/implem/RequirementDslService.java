package fr.pacman.validation.services.rules.implem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.obeonetwork.dsl.requirement.Requirement;

import fr.pacman.validation.services.DslValidationRule;
import fr.pacman.validation.services.DslValidationRuleDefinitionService;
import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRuleService_Itf;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Service Acceleo vérifiant les règles du DSM Requirement.
 */
public class RequirementDslService extends DslService_Abs implements DslValidationRuleService_Itf
{

   /**
    * @param p_repository
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirements
    *           Inutilisé pour cette règle - mettre 'null'.
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslRequirement_Check001)
   public List<DslValidationRuleNokBean> checkIdUnique_sce (final org.obeonetwork.dsl.requirement.Repository p_repository, final Object[] p_paramRequirements)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final List<Requirement> v_requirements = DslValidationRuleDefinitionService.findAll(p_repository, Requirement.class);

      final Map<String, Integer> v_requirementMap = new HashMap<String, Integer>();
      for (final Requirement v_requirement : v_requirements)
      {
         // Si l'id de l'exigence existe déjà
         if (v_requirementMap.containsKey(v_requirement.getId()))
         {
            // int v_nbOccur = v_requirementMap.get(v_requirement.getId());
            // v_nbOccur++;
            // v_requirementMap.put(v_requirement.getId(), v_nbOccur++);
            // Ajouter une violation de la règle
            // "L'identifiant de l'exigence doit etre unique"
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslRequirement_Check001, v_requirement, v_requirement.getId()));
         }
         else
         {
            v_requirementMap.put(v_requirement.getId(), 1);
         }
      }
      // for (Entry<String, Integer> v_entry : v_requirementMap.entrySet()) {
      // if (v_entry.getValue() >= 2) {
      // v_objetsInvalides.add(new DslValidationRuleNokBean(
      // DslValidationRuleReferentiel_Enum.DslEntity_Check001,
      // v_requirement,
      // "Plusieurs exigences avec le même id existent: "
      // + v_entry.getKey()
      // + " ; "
      // + v_entry.getValue()
      // + " fois)"));
      // }
      // }

      return v_objetsInvalides;
   }

   /**
    * Vérification de l'exigence.
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslRequirement_Check002)
   public List<DslValidationRuleNokBean> checkIdentOblig_sce (final org.obeonetwork.dsl.requirement.Repository p_entryPointDsl, final Object p_paramRequirement)
   {
      // TODO Méthode non implémentée
      return Collections.emptyList();
   }

   /**
    * Vérification de l'exigence.
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslRequirement_Check003)
   public List<DslValidationRuleNokBean> checkFieldDescriptionOblig_sce (final org.obeonetwork.dsl.requirement.Repository p_entryPointDsl, final Object p_paramRequirement)
   {
      // TODO Méthode non implémentée
      return Collections.emptyList();
   }

   /**
    * Vérification de l'exigence.
    * @param p_entryPointDsl
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirements
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslRequirement_Check004)
   public List<DslValidationRuleNokBean> checkFieldNameSize_sce (final org.obeonetwork.dsl.requirement.Repository p_entryPointDsl, final Object[] p_paramRequirements)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final int v_maxLength = getIntegerParam(p_paramRequirements[0]);
      // Caster
      final org.obeonetwork.dsl.requirement.Repository v_Repository = p_entryPointDsl;
      final List<Requirement> v_requirements = DslValidationRuleDefinitionService.findAll(v_Repository, Requirement.class);

      for (final Requirement v_requirement : v_requirements)
      {
         if (v_requirement.getName().length() > v_maxLength)
         {
            // la règle n'est pas respectée pour cet objet Requirement
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslRequirement_Check004, v_requirement, v_requirement.getId(), v_requirement.getName().length(),
                  v_maxLength));
         }
      }

      return v_objetsInvalides;
   }

}
