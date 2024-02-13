package fr.pacman.validation.services;

import fr.pacman.validation.services.rules.ValidationLevel_Enum;
import fr.pacman.validation.services.rules.implem.EntityDslService;
import fr.pacman.validation.services.rules.implem.GraalDslService;
import fr.pacman.validation.services.rules.implem.RequirementDslService;
import fr.pacman.validation.services.rules.implem.SoaDslService;
import fr.pacman.validation.services.rules.implem.TransverseDslService;

/**
 * Enumeration des regles du referentiel.
 */
public enum DslValidationRuleReferentiel_Enum implements DslValidationRule_Itf
{

   unknownRule("%1$s", null, null, null, ValidationLevel_Enum.Information, false),

   /** Vérification du nombre de paramètres d'entrée */
   WrongParameters("Erreur dans les paramètres d'entrée de la règle de validation %1$s : %2$s", null, null, null, ValidationLevel_Enum.Error, false),

   // Transverse
   // ---------------
   /** Metadonnees */
   Metadata_Check001("Problème avec la métadonnée %1$s : %2$s", null, TransverseDslService.class, null, ValidationLevel_Enum.Error, true),

   // DSL Requirement
   // ---------------
   /** L'identifiant de l'exigence doit etre unique */
   DslRequirement_Check001("L'identifiant de l'exigence doit etre unique : %1$s", null, RequirementDslService.class, Dsl_Enum.DslRequirement,
         ValidationLevel_Enum.Error, true),
   /** L'identifiant de l'exigence est obligatoire */
   DslRequirement_Check002("L'identifiant de l'exigence est obligatoire", null, RequirementDslService.class, Dsl_Enum.DslRequirement,
         ValidationLevel_Enum.Error, true),
   /** La description de l'exigence est obligatoire */
   DslRequirement_Check003("La description de l'exigence est obligatoire", null, RequirementDslService.class, Dsl_Enum.DslRequirement,
         ValidationLevel_Enum.Error, true),
   // Le champ 'Name' ne doit pas dépasser 'c_nbCaracMax4NameRequirement'
   // caractères
   DslRequirement_Check004("L'exigence d'id %1$s a un nom trop long (%2$s > %3$s)", new Object[]
   {ParamExigence4Rule.c_nbCaracMax4NameRequirement }, RequirementDslService.class, Dsl_Enum.DslRequirement, ValidationLevel_Enum.Error, true),

   // DSL Entity
   // ----------
   /** Une seule relation navigable dans la definition de 2 relations opposees */
   DslEntity_Check001("Une seule relation navigable dans la definition de 2 relations opposees", null, EntityDslService.class, Dsl_Enum.DslEntity,
         ValidationLevel_Enum.Error, true),
   /** Deux entités dans le même package ne doivent pas avoir le même nom */
   DslEntity_Check002("Plusieurs entités dans le même package ne doivent pas avoir le même nom", null, EntityDslService.class, Dsl_Enum.DslEntity,
         ValidationLevel_Enum.Error, true),
   /**
    * Deux attributs ou propriétés dans la même entity ne doivent pas avoir le
    * même nom
    */
   DslEntity_Check003("Plusieurs attributs ou propriétés dans la même entity ne doivent pas avoir le même nom", null, EntityDslService.class, Dsl_Enum.DslEntity,
         ValidationLevel_Enum.Error, true),
   /** Les attributs doivent avoir une multiplicité 0..1 ou 1 (pas de liste) */
   DslEntity_Check004("Les attributs doivent avoir une multiplicité 0..1 ou 1 (pas de liste)", null, EntityDslService.class, Dsl_Enum.DslEntity,
         ValidationLevel_Enum.Error, true),
   /** Pas de caractères spéciaux dans les noms */
   DslEntity_Check005("Pas de caractères spéciaux dans les noms", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Error, true),
   /** Eviter les accents et les espaces dans les noms */
   DslEntity_Check006("Eviter les accents et les espaces dans les noms", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning, true),
   /** Eviter les relations 1..* */
   DslEntity_Check007("Eviter les relations 1..*", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning, true),
   /** Eviter les relations isComposite */
   DslEntity_Check008("Eviter les relations isComposite", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning, true),
   /** Description fortement conseillée */
   DslEntity_Check009("Description fortement conseillée", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning, true),
   /** Eviter d'imbriquer les blocs */
   DslEntity_Check010("Eviter d'imbriquer les blocs", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning, true),
   /** Un attribut ne doit pas etre un id */
   DslEntity_Check011("Une entité ne devrait pas avoir d'attribut 'Primary Key'", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning,
         true),
   /** Ne mettre que les types primitifs sauf pour l'email */
   DslEntity_Check012("Un attribut doit être de type primitif (sauf Email)", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Warning, true),
   /** Un bloc doit avoir un nom */
   DslEntity_Check013("Un bloc doit avoir un nom", null, EntityDslService.class, Dsl_Enum.DslEntity, ValidationLevel_Enum.Error, true),

   // DSL GRAAL
   // -------------
   /** regle pour un diagramme de tache */
   DslGraal_Check001("Un nom de tache doit être unique dans un use case  ", null, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDeTaches, ValidationLevel_Enum.Error, true),
   /** regle pour les plans d'actions*/
   DslGraal_Check002("Chaque plan d'action doit avoir un etat initial", null, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDePlanActions, ValidationLevel_Enum.Error, true),
   /** concernant une boucle dans un plan d'action (etat initial)*/
   DslGraal_Check003("Une loop doit avoir un etat initial", null, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDePlanActions, ValidationLevel_Enum.Error, true),
   /** concernant une boucle dans un plan d'action (etat final)*/
   DslGraal_Check004("Une loop doit avoir un etat final", null, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDePlanActions, ValidationLevel_Enum.Error, true),
   /** il faut un User, par exemple si uniquement appli action, le plan d'action est inadapte*/
   DslGraal_Check005("Il faut un UserAction, par exemple si uniquement AppliAction, le plan d'action est inadapte", null, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDePlanActions, ValidationLevel_Enum.Warning, true),
   /** Chaque plan d'action doit avoir un etat final*/
   DslGraal_Check006("Chaque plan d'action doit avoir un etat final", null, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDePlanActions, ValidationLevel_Enum.Warning, true),
   /** Il est recommande de ne pas depasse 2 niveaux dans unne boucle*/
   DslGraal_Check007("Il est recommande de ne pas depasser %1$d niveaux dans une boucle", new Object[]{2}, GraalDslService.class, Dsl_Enum.DslGraalDiagrammeDePlanActions, ValidationLevel_Enum.Warning, true),

   // DSL SOA - DTO
   // -------------
   /** Une seule relation navigable dans la definition de 2 relations opposees */
   DslSoaDto_Check001("Une seule relation navigable dans la definition de 2 relations opposees", null, SoaDslService.class, Dsl_Enum.DslSoaDto,
         ValidationLevel_Enum.Error, true),
   /** Description fortement conseillée */
   DslSoaDto_Check002("Description fortement conseillée", null, SoaDslService.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Warning, true),
   /** Uniquement types primitifs ou DTO */
   DslSoaDto_Check003("Le parametre doit etre de type primitif ou DTO", null, SoaDslService.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Error, true),
   /** Une operation doit avoir 0 ou 1 parametre de retour */
   DslSoaDto_Check004("L'operation doit avoir 0 ou 1 parametre de retour", null, SoaDslService.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Error, true),
   /** Le parametre d'une operation doit avoir Upper = 1 ou -1 (=*) */
   DslSoaDto_Check005("Le parametre d'une operation doit avoir Upper = 1 ou -1 (=*)", null, SoaDslService.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Error, true),
   /** Pas de service sans Interface */
   DslSoaDto_Check006("Le Service doit avoir une interface", null, SoaDslService.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Error, true),
   /** Pas de caracteres speciaux dans les noms */
   DslSoaDto_Check100("Pas de caracteres speciaux dans les noms", null, SoaDslService.class, Dsl_Enum.DslSoaDto, ValidationLevel_Enum.Information, false);

   /** Identifiant de la regle */
   private String _id;

   /** Le libelle de la regle */
   private String _libRule;

   /** Des paramètres éventuels de l'exigence (ex : nb max attributs) */
   private Object[] _tab_paramRule;

   /** La classe dans laquelle l'exigence est implémentée */
   private Class<? extends DslValidationRuleService_Itf> _ClassImplem;

   /** Le DSL sur lequel la regle s'applique */
   private Dsl_Enum _RuleDsl_Enum;

   /** Niveau de la regle (error, warning, info) */
   private ValidationLevel_Enum _RuleLevel;

   /** Regle activee (ou pas) */
   private boolean _activateRule;

   /**
    * Constructeur interne de l'enumeration.
    * @param p_libRequirement
    *           Le libelle de la regle.
    * @param p_paramRequirements
    *           Un paramètre éventuel de l'exigence (ex : nb max attributs).
    * @param p_ClassImplem
    *           La classe dans laquelle l'exigence est implémentée.
    * @param p_RequirementDsl_Enum
    *           Le DSL sur lequel la regle s'applique.
    * @param p_RequirementLevel
    *           Le niveau de la regle (erreur, avertissement ou info).
    * @param p_activateRequirement
    *           'true' si la regle est activee.
    */
   private DslValidationRuleReferentiel_Enum (final String p_libRequirement, final Object[] p_paramRequirements, final Class<? extends DslValidationRuleService_Itf> p_ClassImplem,
         final Dsl_Enum p_RequirementDsl_Enum, final ValidationLevel_Enum p_RequirementLevel, final boolean p_activateRequirement)
   {
      _libRule = p_libRequirement;
      _ClassImplem = p_ClassImplem;
      _tab_paramRule = p_paramRequirements;
      _RuleDsl_Enum = p_RequirementDsl_Enum;
      _RuleLevel = p_RequirementLevel;
      _activateRule = p_activateRequirement;
   }

   @Override
   public String get_id ()
   {
      return name();
   }

   @Override
   public void set_id (final String p_id)
   {
      // Referentiel : on ne modifie pas les id
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
      return super.toString() + "\n_id = " + _id + "\n_libRule = " + _libRule + "\n_tab_paramRule = " + _tab_paramRule + "\n_ClassImplem = " + _ClassImplem + "\n_RuleLevel = " + _RuleLevel
            + "\n_RuleDsl_Enum = " + _RuleDsl_Enum + "\n_activateRule = " + _activateRule;
   }

   /**
    * Définition des constantes pour les paramètres supplémentaires des méthodes
    * d'implémentation. Rappel : la signature d'une méthode d'implémentation est
    * la suivante "EObject p_entryPointDsl, Object[] p_paramsRequirement", par
    * exemple :
    * 
    * <code>
    * -@DslValidationRule(DslValidationRuleReferentiel_Enum.DslRequirement_Check001) public void checkIdentUnique_sce (EObject p_entryPointDsl, Object[] p_paramsRequirement) { ... }
    * </code>
    */
   private static final class ParamExigence4Rule
   {
      /** Le nombre maximum de caractères pour le champ 'Name' d'une Requirement */
      public static final Integer c_nbCaracMax4NameRequirement = 100;

      /**
       * Constructeur privé.
       */
      private ParamExigence4Rule ()
      {
         super();
      }
   }
}
