package fr.pacman.validation.services;

import fr.pacman.validation.services.rules.ValidationLevel_Enum;

/**
 * Les donnees d'une regle de validation d'un DSL.
 */
public interface DslValidationRule_Itf extends java.io.Serializable
{
   /**
    * L'identifiant.
    * @return L'id.
    */
   abstract public String get_id ();

   /**
    * Affecter l'identifiant.
    * @param p_id
    *           L'identifiant.
    */
   abstract public void set_id (String p_id);

   /**
    * Obtenir le libellé d'exigence.
    * @return Le libellé d'exigence.
    */
   abstract public String get_libRule ();

   /**
    * Affecter le libellé de l'exigence.
    * @param p_libRequirement
    *           Le libellé de l'exigence.
    */
   abstract public void set_libRule (String p_libRequirement);

   /**
    * Obtenir le paramètres (éventuel) de la règle (ex : max de qqchose).
    * @return Les paramètres.
    */
   abstract public Object[] get_tab_paramRule ();

   /**
    * Affecter les paramètres (éventuel) de la règle (ex : max de qqchose).
    * @param p_tab_paramRule
    *           Les paramètres.
    */
   abstract public void set_tab_paramRule (Object[] p_tab_paramRule);

   /**
    * Obtenir La classe contenant la méthode de vérification annotée.
    * @return La classe désirée.
    */
   abstract public Class<? extends DslValidationRuleService_Itf> get_ClassImplem ();

   /**
    * Affectée la classe contenant la méthode de vérification annotée.
    * @param p_ClassImplem
    *           La classe concernée.
    */
   abstract public void set_ClassImplem (Class<? extends DslValidationRuleService_Itf> p_ClassImplem);

   /**
    * Obtenir le niveau de la règle de validation : erreur, avertissement, ...
    * @return Le niveau.
    */
   abstract public ValidationLevel_Enum get_RuleLevel ();

   /**
    * Affecter le niveau de la règle de validation : erreur, avertissement, ...
    * @param p_RequirementLevel
    *           Le niveau.
    */
   abstract public void set_RuleLevel (ValidationLevel_Enum p_RequirementLevel);

   /**
    * Obtenir le DSM sur lequel porte l'exigence de modélisation.
    * @return Le DSM.
    */
   abstract public Dsl_Enum get_RuleDsl_Enum ();

   /**
    * Affecter le DSM sur lequel porte l'exigence de modélisation.
    * @param p_RequirementDsm_Enum
    *           Le DSM.
    */
   abstract public void set_RuleDsl_Enum (Dsl_Enum p_RequirementDsm_Enum);

   /**
    * Affecter l'activation (ou pas) de l'exigence de modélisation.
    * @return 'true' si activée.
    */
   abstract public boolean is_activateRule ();

   /**
    * Affecter l'activation (ou pas) de l'exigence de modélisation.
    * @param p_activateRequirement
    *           'true' si activée.
    */
   abstract public void set_activateRule (boolean p_activateRequirement);
}
