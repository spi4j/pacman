package fr.pacman.validation.services.rules.implem;

import java.util.ArrayList;
import java.util.List;

import org.obeonetwork.dsl.environment.Annotation;

import fr.pacman.commons.services.Annotation_Enum;
import fr.pacman.validation.services.DslValidationRule;
import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRuleService_Itf;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Service Acceleo vérifiant les règles des DSL transverses.
 */
public class TransverseDslService extends DslService_Abs implements DslValidationRuleService_Itf
{

   /**
    * Vérification de l'exigence : Metadonnees
    * 
    * @param p_annotation
    *           Le point d'entrée dans le Dsl : une annotation.
    * @param p_paramRequirement
    *           Le paramètre (éventuel) de l'exigence de modélisation (ex : max
    *           de qqchose)
    * @return La liste des exigences de modélisation qui ont violées cette
    *         règle.
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.Metadata_Check001)
   public List<DslValidationRuleNokBean> checkMetadatas_sce (final Annotation p_annotation, final Object[] p_paramRequirement)
   {
      // la liste des problèmes retournés
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      if (p_annotation.getTitle() != null)
      {
         final Annotation_Enum v_a = Annotation_Enum.findByName(p_annotation.getTitle());
         if (v_a == null)
         { // metadonnée inconnue
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.Metadata_Check001, p_annotation, p_annotation.getTitle(), "Métadonnée inconnue"));
         }
         else
         { // metadonnee qui ne colle pas
            if (!(Annotation_Enum.findClass(p_annotation.eContainer().eContainer().eClass().getName(), p_annotation.getTitle())))
            {
               v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.Metadata_Check001, p_annotation, p_annotation.getTitle(), "Ne peut pas être positionnée sur "
                     + p_annotation.eContainer().eContainer().getClass().getName()));
            }
            else
            {
               // contenu obligatoire
               if (v_a.hasBody() && p_annotation.getBody() == null)
               {
                  v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.Metadata_Check001, p_annotation, p_annotation.getTitle(), "Contenu attendu"));
               }
            }
         }

      }
      return v_objetsInvalides;
   }
}
