package fr.pacman.validation.services.rules;

import org.eclipse.emf.ecore.EObject;

import fr.pacman.validation.services.DslValidationRule_Itf;

/**
 * Contient les données des exigences de modélisation non satisfaite (NOK).
 */
public class DslValidationRuleNokBean
{
   /** L'exigence de modélisation non satisfaite */
   private final DslValidationRule_Itf _ruleNok;

   /** Le point d'entrée dans la modélisation */
   private final EObject _entryPointDsm;

   private final Object[] _messageParams;

   /**
    * Le constructeur max.
    * 
    * @param p_RequirementNok
    *           L'exigence de modélisation non satisfaite.
    * @param p_entryPointDsm
    *           Le point d'entrée dans la modélisation.
    * @param p_messageParams
    *           Les paramètres du message à afficher.
    */
   public DslValidationRuleNokBean (final DslValidationRule_Itf p_RequirementNok, final EObject p_entryPointDsm, final Object... p_messageParams)
   {
      super();
      this._ruleNok = p_RequirementNok;
      this._entryPointDsm = p_entryPointDsm;
      this._messageParams = p_messageParams;
   }

   /**
    * Obtenir l'exigence Nok.
    * @return L'exigence Nok.
    */
   public DslValidationRule_Itf get_RequirementNok ()
   {
      return _ruleNok;
   }

   /**
    * Obtenir le point d'entrée du Dsm.
    * @return L'instance désirée.
    */
   public EObject get_entryPointDsl ()
   {
      return _entryPointDsm;
   }

   /**
    * Le message d'erreur à afficher dans le "Log".
    * @return Le message.
    */
   public String get_messageNok ()
   {
      return String.format(_ruleNok.get_libRule(), _messageParams);
   }

   @Override
   public String toString ()
   {
      return get_messageNok();
   }

   /**
    * @return le niveau de la règle
    */
   public ValidationLevel_Enum getLevel ()
   {
      return _ruleNok.get_RuleLevel();
   }
}
