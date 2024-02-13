package fr.pacman.validation.services.rules.implem;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.soa.Parameter;

import fr.pacman.validation.services.DslValidationRule;
import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRuleService_Itf;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Les méthodes vérifiant les exigences de modélisation.
 */
public class SoaDslService extends DslService_Abs implements DslValidationRuleService_Itf
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
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check001)
   public List<DslValidationRuleNokBean> checkOnlyOneRelationNavigable_sce (final org.obeonetwork.dsl.environment.Reference p_reference, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      if (p_reference.isNavigable() && p_reference.getOppositeOf() != null && p_reference.getOppositeOf().isNavigable()) {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check001, p_reference));
      }
      return v_objetsInvalides;
   }

//   /**
//    * Vérification de l'exigence : Description fortement conseillée.
//    * 
//    * @param p_dto
//    *           Le point d'entrée dans le Dsl.
//    * @param p_paramRequirement
//    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
//    *           de qqchose)
//    * @return La liste des exigences de modélisation qui ont violées cette
//    *         règle.
//    */
//   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check002)
//   public List<DslValidationRuleNokBean> checkDescriptionDto_sce (final org.obeonetwork.dsl.soa.ServiceDTO p_dto, final Object[] p_paramRequirement)
//   {
//      // la liste des problèmes retournés
//      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
//      if (p_dto.getDescription() == null || p_dto.getDescription().isEmpty())
//      {
//         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check002, p_dto));
//      }
//      final EList<org.obeonetwork.dsl.environment.Property> v_properties = p_dto.getProperties();
//      for (final org.obeonetwork.dsl.environment.Property v_property : v_properties)
//      {
//         if (v_property.getDescription() == null || v_property.getDescription().isEmpty())
//         {
//            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check002, v_property));
//         }
//      }
//      return v_objetsInvalides;
//   }

   /**
    * Vérification de l'exigence : Description fortement conseillée.
    * 
    * @param p_operation
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check002)
   public List<DslValidationRuleNokBean> checkDescriptionOperations_sce (final org.obeonetwork.dsl.soa.Operation p_operation, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();

      if (p_operation.getDescription() == null || p_operation.getDescription().isEmpty())
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check002, p_operation));
      }
      final EList<Parameter> v_parameters = p_operation.getInput();
      v_parameters.addAll(p_operation.getOutput());
      for (final Parameter v_parameter : v_parameters)
      {
         if (v_parameter.getDescription() == null || v_parameter.getDescription().isEmpty())
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslEntity_Check009, v_parameter));
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Pas de service sans Interface
    * 
    * @param p_service
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check006)
   public List<DslValidationRuleNokBean> checkIfServiceWithoutItf_sce (
   // final Object p_entryPointDsl,
         final org.obeonetwork.dsl.soa.Service p_service, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      if (p_service.getOwnedInterface() == null)
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check006, p_service));
      }

      return v_objetsInvalides;
   }

   /**
    * Un parametre d'operation doit avoir Upper = 1 ou Upper = -1
    * 
    * @param p_operation
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check005)
   public List<DslValidationRuleNokBean> checkIfUpperParamIsCorrect_sce (final org.obeonetwork.dsl.soa.Operation p_operation, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final EList<Parameter> v_parameters = p_operation.getInput();
      v_parameters.addAll(p_operation.getOutput());
      for (Parameter v_param : v_parameters)
      { // liste des parametres d'entrée et de sortie
         MultiplicityKind v_multiplicity = v_param.getMultiplicity();
         if (v_multiplicity.getValue()==MultiplicityKind.ONE_STAR)
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check005, v_param));
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Un parametre doit etre de type primitif ou DTO
    * 
    * @param p_operation
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check003)
   public List<DslValidationRuleNokBean> checkIfOpTypeIsPrim_sce (final org.obeonetwork.dsl.soa.Operation p_operation, final Object[] p_paramRequirement)

   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final EList<Parameter> v_parameters = p_operation.getInput();
      v_parameters.addAll(p_operation.getOutput());
      for (Parameter v_param : v_parameters)
      { // lles cas d'erreurs : null, de type entity , de type email
         if (v_param.getType() == null || "Email".equalsIgnoreCase(v_param.getType().getName()) || v_param.getType() instanceof Entity)
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check003, v_param));
         }
      }

      return v_objetsInvalides;
   }

   /**
    * Une operation doit avoir 0 ou 1 parametre de retour
    * 
    * @param p_operation
    *           Le point d'entrée dans le Dsl.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslSoaDto_Check004)
   public List<DslValidationRuleNokBean> checkReturnOp_sce (final org.obeonetwork.dsl.soa.Operation p_operation, final Object[] p_paramRequirement)

   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      if (p_operation.getOutput().size() > 1) // liste des parametres de retour
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslSoaDto_Check004, p_operation));
      }
      return v_objetsInvalides;
   }
}
