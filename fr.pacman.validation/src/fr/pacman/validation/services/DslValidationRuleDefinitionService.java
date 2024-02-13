package fr.pacman.validation.services;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import fr.pacman.commons.exception.PacmanRuntimeException;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Contient les fonctionnalités pour obtenir requêter sur le referentiel
 * d'exigences de modélisation.
 */
public class DslValidationRuleDefinitionService
{

   /** Le tableau des regles du referentiel */
   private final List<? extends DslValidationRule_Itf> _tab_Rule;

   /**
    * Constructeur par defaut.
    */
   public DslValidationRuleDefinitionService ()
   {
      // Referentiel principal
      this(Arrays.asList(DslValidationRuleReferentiel_Enum.values()));
   }

   /**
    * Constructeur en specifiant le referientiel de regle.
    * 
    * @param p_tab_Rule
    *           Le tableau des exigences de moélisation.
    */
   public DslValidationRuleDefinitionService (final List<? extends DslValidationRule_Itf> p_tab_Rule)
   {
      _tab_Rule = p_tab_Rule;
   }

   /**
    * Ouvrir le service.
    */
   public void openService ()
   {
      // TODO Activer le "readReferentielRequirements()" et le
      // "writeReferentielRequirements()"
      // --> Pour ecrire par defaut si le fichier n'existe pas et le lire sinon
   }

   /**
    * Fermer le service.
    */
   public void closeService ()
   {
      // RAS pour l'instant
      // TODO Activer le "readReferentielRequirements()" et le
      // "writeReferentielRequirements()"
      // --> Pour ecrire par defaut si le fichier n'existe pas et le lire sinon
   }

   /**
    * Permet d'obtenir toutes les regles du DSL specifie avec le status
    * "activee".
    * 
    * @param p_Dsl
    *           Le DSL desire.
    * @return Les regles desirees.
    */
   private List<DslValidationRule_Itf> getAllRulesByDslActivate (final Dsl_Enum p_Dsl)
   {
      final List<DslValidationRule_Itf> v_tab_RuleActivate = new ArrayList<DslValidationRule_Itf>();
      for (final DslValidationRule_Itf v_rule : _tab_Rule)
      {
         // Si règle active et (Si pas de DSL specifie ou DSL courant)
         if (v_rule.is_activateRule() == true && (p_Dsl == null || p_Dsl == v_rule.get_RuleDsl_Enum()))
         {
            // Memoriser la regle
            v_tab_RuleActivate.add(v_rule);
         }
      }
      return v_tab_RuleActivate;
   }

   //
   // Methodes pour les tests
   //

   /**
    * Permet d'activer (ou pas) toutes les regles.
    * 
    * @param p_isActivate
    *           'true' activer toutes les regles.
    */
   void setActivateAllRules (final boolean p_isActivate)
   {
      // Parcourir toutes les regles
      for (final DslValidationRule_Itf v_rule : _tab_Rule)
      {
         // Forcer la regle e l'etat souhaitee
         v_rule.set_activateRule(p_isActivate);
      }
   }

   /**
    * Obtenir le nombre de regles dans le referentiel (activee ou pas).
    * 
    * @return Le nombre de regles total.
    */
   public int getNbRequirements ()
   {
      return _tab_Rule.size();
   }

   /**
    * Permet d'activer (ou pas) toutes les regles.
    * 
    * @param p_idRequirement
    *           L'identifiant de la regle.
    * @param p_isActivate
    *           'true' activer toutes les regles.
    */
   void setActivateRule (final String p_idRequirement, final boolean p_isActivate)
   {
      boolean v_found = false;
      // Parcourir toutes les regles
      for (final DslValidationRule_Itf v_rule : _tab_Rule)
      {
         if (v_rule.get_id().equalsIgnoreCase(p_idRequirement))
         {
            // Forcer la regle a l'etat souhaite
            v_rule.set_activateRule(p_isActivate);
            v_found = true;
            break;
         }
      }
      if (!v_found)
      {
         throw new IllegalArgumentException("Changement d'etat de la regle impossible : identifiant \"" + p_idRequirement + "\" non trouve dans referentiel");
      }
   }

   /**
    * Obtenir les instances representant les methodes implementant la règle.
    * 
    * @param p_RequirementDsm
    *           L'identifiant de l'exigence DSM.
    * @return L'instance desiree.
    */
   List<Method> findMethodsForRule (final DslValidationRule_Itf p_RequirementDsm)
   {
      final List<Method> v_return = new ArrayList<Method>();

      // Obtenir toutes les classe annotations de la classe d'implementation de
      // l'exigence DSM specifiee
      final Method[] v_tab_Method = p_RequirementDsm.get_ClassImplem().getDeclaredMethods();

      // Parcourir toutes les annotations
      for (final Method v_MethodCour : v_tab_Method)
      {
         // Obtenir l'annotation 'DslValidationRule' (si elle existe)
         final Annotation[] v_tab_Annotation = v_MethodCour.getAnnotations();
         // Parcourir les annotations de la methode courante
         for (final Annotation v_AnnotationCour : v_tab_Annotation)
         {
            // A ce niveau, nous avons sur la methode courante
            // par exemple :
            // @DslValidationRule(DslValidationRuleReferentiel_Enum.DsmRequirement_Check001)

            try
            {
               // Obtenir la methode "value()" de l'annotation
               final Method v_Method_value = v_AnnotationCour.getClass().getMethod("value");
               // Obtenir la "value" - par exemple :
               // DslValidationRuleReferentiel_Enum.DsmRequirement_Check001
               final Object v_result = v_Method_value.invoke(v_AnnotationCour);
               // Si le retour de "value()" est un 'DslValidationRule_Itf'
               if (v_result instanceof DslValidationRule_Itf)
               {
                  // Object --> DslValidationRule_Itf
                  final DslValidationRule_Itf v_RequirementDsm = (DslValidationRule_Itf) v_result;
                  // Si c'est la valeur d'enumeration desiree
                  if (p_RequirementDsm.get_id().equals(v_RequirementDsm.get_id()) == true)
                  {
                     v_return.add(v_MethodCour);
                  }
               }
            }
            catch (final Exception v_e)
            {
               throw new PacmanRuntimeException(v_e);
            }
         }
      }

      // Si exigence non trouvee
      if (v_return.isEmpty())
      {
         throw new IllegalStateException("La methode annotee pour la règle \"" + p_RequirementDsm.get_id() + "\" n'a pas ete trouvee dans la classe " + p_RequirementDsm.get_ClassImplem());
      }

      return v_return;
   }

   /**
    * Permet d'executer toutes les methodes d'implementation du Dsm
    * representation par son point d'entree.
    * 
    * @param p_Dsl
    *           Le DSL concerné.
    * @param p_entryPoint
    *           Le point d'entree du modèle.
    * @return La liste des exigences non satisfaites (NOK).
    */
   public List<DslValidationRuleNokBean> runRulesOfDsl (final Dsl_Enum p_Dsl, final EObject p_entryPoint)
   {
      return runRulesOfDsl(p_Dsl, p_entryPoint, null);
   }

   /**
    * Permet d'executer toutes les methodes d'implementation du Dsm
    * representation par son point d'entree.
    * 
    * @param p_Dsl
    *           Le DSL concerné.
    * @param p_entryPoint
    *           Le point d'entree du modèle.
    * @param p_rules
    *           les règles à vérifier pour ce métamodèle (peut être null, par
    *           défaut toutes)
    * @return La liste des exigences non satisfaites (NOK).
    */
   @SuppressWarnings("unchecked")
   public List<DslValidationRuleNokBean> runRulesOfDsl (final Dsl_Enum p_Dsl, final EObject p_entryPoint, final DslValidationRule_Itf[] p_rules)
   {
      final List<DslValidationRuleNokBean> v_return = new ArrayList<DslValidationRuleNokBean>();

      final List<DslValidationRule_Itf> v_tabRules;
      // Si aucune règle n'a été précisée lors de l'exécution
      if (p_rules == null || p_rules.length == 0)
      {
         // Obtenir toutes les regles a l'etat 'activee' pour le Dsl courant
         v_tabRules = getAllRulesByDslActivate(p_Dsl);
      }
      else
      {
         v_tabRules = Arrays.asList(p_rules);
      }

      // Parcourir les exigences de modelisation
      for (final DslValidationRule_Itf v_rule : v_tabRules)
      {
         // System.out.println("Règle : " + v_rule.get_id() + " --> " +
         // v_rule.get_libRule());
         // Trouver les instances de 'Method' pour l'exigence de modelisation
         // courante
         final List<Method> v_Methods = findMethodsForRule(v_rule);
         for (final Method v_Method : v_Methods)
         {
            // System.out.println("\tMéthode trouvée : " +
            // v_Method.getDeclaringClass().getName() + '.' +
            // v_Method.getName());
            // Obtenir une instance de la classe d'implementation de l'exigence
            // DSM
            try
            {
               final DslValidationRuleService_Itf v_instanceImplem = v_rule.get_ClassImplem().newInstance();
               // Obtenir le paramètre de l'exigence
               final Object[] v_paramsRule = v_rule.get_tab_paramRule();
               // Execution de la methode verifiant la règle de validation
               // Rq : v_instanceImplem."v_Method.getName()"(v_entryPointDsm,
               // v_paramRequirement)
               // Récupération du paramètre du point d'entrée pour l'exécution
               // de cette règle
               final Class<? extends EObject> v_parameterType = (Class<? extends EObject>) v_Method.getParameterTypes()[0];
               // Le paramètre doit être compatible avec le méta modèle testé et ce n'est pas un élément de "dsl.environment" (ex : "dsl.environment.Annotation")
               if (v_rule.get_RuleDsl_Enum() != null && (v_parameterType.getPackage().equals(v_rule.get_RuleDsl_Enum().getPackage()) == false) && (v_parameterType.getPackage().toString().indexOf("dsl.environment") == 0))
               {
                  throw new IllegalArgumentException("Package incompatible (= " + v_parameterType.getPackage() + ") entre le DSL et le paramètre d'entrée de l'opération (= " + v_rule.get_RuleDsl_Enum().getPackage() + ") : " + v_Method);
               }
               // System.out.println("\tRecherche de " +
               // v_parameterType.getName() + " dans " +
               // p_entryPoint.getClass().getName());
               // TODO Optimisation : mise en cache
               final List<? extends EObject> v_elements = findAll(p_entryPoint, v_parameterType);
               for (final EObject v_object : v_elements)
               {
                  // System.out.println("\t\tExécution règle sur " + v_object);
                  final List<DslValidationRuleNokBean> v_listExigenceNok = (List<DslValidationRuleNokBean>) v_Method.invoke(v_instanceImplem, v_object, v_paramsRule);

                  // Si l'exigence est NOK
                  if ((v_listExigenceNok != null) && (v_listExigenceNok.isEmpty() == false))
                  {
                     // Instancier la donnée contenant les infos sur l'exigence
                     // de modélisation violée
                     v_return.addAll(v_listExigenceNok);
                  }
                  // Si l'exigence est OK
                  // else
                  // {
                  // // RAS
                  // }
               }
            }
            catch (final Exception v_e)
            {
               throw new PacmanRuntimeException(v_e);
            }
         }
      }

      return v_return;
   }

   // /**
   // * Permet de lire le referentiel de regles (sans les desactivees).
   // */
   // public void readReferentielRequirements ()
   // {
   // TODO Activer le "readReferentielRequirements()" et le
   // "writeReferentielRequirements()"
   // --> Pour ecrire par defaut si le fichier n'existe pas et le lire sinon
   // // TODO Methode non implementee
   // throw new UnsupportedOperationException("Methode non implementee");
   // }

   // /**
   // * Permet de d'ecrire le referentiel de regles (sans les desactivees).
   // */
   // public void writeReferentielRequirements ()
   // {
   // TODO Activer le "readReferentielRequirements()" et le
   // "writeReferentielRequirements()"
   // --> Pour ecrire par defaut si le fichier n'existe pas et le lire sinon
   // // TODO Methode non implementee
   // throw new UnsupportedOperationException("Methode non implementee");
   // }

   /**
    * Cherche tous les objets d'un type donné dans un arbre
    * 
    * @param p_Root
    *           la racine de l'arbre
    * @param p_class
    *           le type d'objet cherché
    * @param <Type>
    *           Le type d'object cherché.
    * @return les objets trouvés
    */
   @SuppressWarnings("unchecked")
   public static <Type extends EObject> List<Type> findAll (final EObject p_Root, final Class<Type> p_class)
   {
      final List<Type> v_retour = new ArrayList<Type>();
      // Si c'est le type cherché
      if (p_class.isInstance(p_Root))
      {
         // Ajouter l'instance
         v_retour.add((Type) p_Root);
      }
      final EList<EObject> v_arbre = p_Root.eContents();
      for (EObject v_obj : v_arbre)
      {
         // Parcourir récursivement
         v_retour.addAll(findAll(v_obj, p_class));
      }
      return v_retour;
   }

}
