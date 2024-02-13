package fr.pacman.validation.services.rules;

import fr.pacman.validation.services.DslValidationRule_Itf;
import fr.pacman.validation.services.Dsl_Enum;
import fr.pacman.validation.services.DslValidationRuleService_Itf;

/**
 * Permet de memoriser les informations concernant la definition d'une regle.
 */
public class DslValidationRuleBean implements DslValidationRule_Itf
{

   private static final long serialVersionUID = 1L;

   /** Identifiant de la regle */
   private String _id;

   /** Le libelle de la regle */
   private String _libRule;

   /** Un paramètre éventuel de l'exigence (ex : nb max attributs) */
   private Object[] _tab_paramRule;

   /** Un parametre e la regle (optionnel) */
   private Class<? extends DslValidationRuleService_Itf> _ClassImplem;

   /** Le DSM sur lequel la regle s'applique */
   private Dsl_Enum _RuleDsl_Enum;

   /** Niveau de la regle (error, warning, info) */
   private ValidationLevel_Enum _RuleLevel;

   /** Regle activee (ou pas) */
   private boolean _activateRule;

   /**
    * Constructeur.
    * 
    * @param p_id
    *           L'identifiant de la regle.
    * @param p_libRule
    *           Le libelle de la regle.
    * @param p_tab_paramRule
    *           Le paramètre (éventuel) de l'éxigence (ex : nb caractères max).
    * @param p_ClassImplem
    *           La classe implémentant cette règle.
    * @param p_RuleDsl_Enum
    *           Le DSL sur lequel la regle s'applique.
    * @param p_RuleLevel
    *           Le niveau de la regle (erreur, avertissement ou info).
    * @param p_activateRequirement
    *           'true' si la regle est activee.
    */
   public DslValidationRuleBean (final String p_id, final String p_libRule, final Object[] p_tab_paramRule, final Class<? extends DslValidationRuleService_Itf> p_ClassImplem,
         final Dsl_Enum p_RuleDsl_Enum, final ValidationLevel_Enum p_RuleLevel, final boolean p_activateRequirement)
   {
      super();
      _id = p_id;
      _libRule = p_libRule;
      _tab_paramRule = p_tab_paramRule;
      _ClassImplem = p_ClassImplem;
      _RuleDsl_Enum = p_RuleDsl_Enum;
      _RuleLevel = p_RuleLevel;
      _activateRule = p_activateRequirement;
   }

   @Override
   public String get_id ()
   {
      return _id;
   }

   @Override
   public void set_id (final String p_id)
   {
      this._id = p_id;
   }

   @Override
   public String get_libRule ()
   {
      return _libRule;
   }

   @Override
   public void set_libRule (final String p_libRule)
   {
      this._libRule = p_libRule;
   }

   @Override
   public Object[] get_tab_paramRule ()
   {
      return _tab_paramRule;
   }

   @Override
   public void set_tab_paramRule (final Object[] p_tab_paramRule)
   {
      _tab_paramRule = p_tab_paramRule;
   }

   @Override
   public Class<? extends DslValidationRuleService_Itf> get_ClassImplem ()
   {
      return _ClassImplem;
   }

   @Override
   public void set_ClassImplem (final Class<? extends DslValidationRuleService_Itf> p_ClassImplem)
   {
      this._ClassImplem = p_ClassImplem;
   }

   @Override
   public ValidationLevel_Enum get_RuleLevel ()
   {
      return _RuleLevel;
   }

   @Override
   public void set_RuleLevel (final ValidationLevel_Enum p_RuleLevel)
   {
      this._RuleLevel = p_RuleLevel;
   }

   @Override
   public Dsl_Enum get_RuleDsl_Enum ()
   {
      return _RuleDsl_Enum;
   }

   @Override
   public void set_RuleDsl_Enum (final Dsl_Enum p_RuleDsl_Enum)
   {
      this._RuleDsl_Enum = p_RuleDsl_Enum;
   }

   @Override
   public boolean is_activateRule ()
   {
      return _activateRule;
   }

   @Override
   public void set_activateRule (final boolean p_activateRule)
   {
      this._activateRule = p_activateRule;
   }

   @Override
   public String toString ()
   {
      return super.toString() + "\n_id = " + _id + "\n_libRule = " + _libRule + "\n_ClassImplem = " + _ClassImplem + "\n_RuleLevel = " + _RuleLevel + "\n_RuleDsl_Enum = " + _RuleDsl_Enum
            + "\n_activateRule = " + _activateRule;
   }
}
