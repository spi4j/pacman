package fr.pacman.validation.services.rules.implem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.obeonetwork.graal.AbstractTask;
import org.obeonetwork.graal.Activity;
import org.obeonetwork.graal.FinalNode;
import org.obeonetwork.graal.InitialNode;
import org.obeonetwork.graal.Loop;
import org.obeonetwork.graal.Node;
import org.obeonetwork.graal.UserAction;

import fr.pacman.validation.services.DslValidationRule;
import fr.pacman.validation.services.DslValidationRuleReferentiel_Enum;
import fr.pacman.validation.services.DslValidationRuleService_Itf;
import fr.pacman.validation.services.DslValidationRule_Itf;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Vérification de règles sur Graal.
 * @author MINARM
 */
public class GraalDslService extends DslService_Abs implements DslValidationRuleService_Itf
{

   /**
    * Règle 1 : un nom de tache doit etre unique dans un use case
    * @param p_System
    *           un use case
    * @param p_paramRequirement
    *           paramètres
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check001)
   public List<DslValidationRuleNokBean> checkEntityNameUnique (final org.obeonetwork.graal.System p_System, final Object[] p_paramRequirement)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final Map<String, Integer> v_entitiesMap = new HashMap<String, Integer>();
      for (AbstractTask v_attribute : p_System.getTasks())
      {
         if (v_entitiesMap.containsKey(v_attribute.getName()))
         {
            int v_nbOccur = v_entitiesMap.get(v_attribute.getName());
            v_nbOccur++;
            v_entitiesMap.put(v_attribute.getName(), v_nbOccur);
         }
         else
         {
            v_entitiesMap.put(v_attribute.getName(), 1);
         }
      }
      // on vérifie dans la map si un meme nom de tache a été trouvée plusieurs
      // fois
      for (Entry<String, Integer> v_entry : v_entitiesMap.entrySet())
      {
         if (v_entry.getValue() >= 2)
         {
            v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslGraal_Check001, p_System));
         }
      }
      return v_objetsInvalides;
   }

   /**
    * Règle 2 : Chaque plan d'action doit avoir un etat initial
    * @param p_task
    *           le plan d'action
    * @param p_paramRequirement
    *           paramètres
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check002)
   public List<DslValidationRuleNokBean> checkEtatInitialPlanAction (final org.obeonetwork.graal.Task p_task, final Object[] p_paramRequirement)
   {
      return checkEtatInitialActivity(p_task, DslValidationRuleReferentiel_Enum.DslGraal_Check002);
   }

   /**
    * Regle 3 : un loop doit avoir un etat initial
    * @param p_loop
    *           la loop
    * @param p_parametres
    *           paramètres
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check003)
   public List<DslValidationRuleNokBean> checkEtatInitialLoop (final org.obeonetwork.graal.Loop p_loop, final Object[] p_parametres)
   {
      return checkEtatInitialActivity(p_loop, DslValidationRuleReferentiel_Enum.DslGraal_Check003);
   }

   /**
    * Règle 2 / 3 : une loop ou un use case doit avoir un état initial
    * @param p_activity
    *           la loop ou le use case
    * @param p_rule
    *           la règle à vérifier
    * @return liste d'erreurs
    */
   private List<DslValidationRuleNokBean> checkEtatInitialActivity (final Activity p_activity, final DslValidationRule_Itf p_rule)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      boolean v_found = false;
      for (Node v_node : p_activity.getNodes())
      {
         if (v_node instanceof InitialNode)
         {
            v_found = true;
            break;
         }
      }
      if (!(v_found))
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(p_rule, p_activity));
      }
      return v_objetsInvalides;
   }

   /**
    * Regle 4 : un loop doit avoir un etat final.
    * @param p_loop
    *           la loop
    * @param p_parametres
    *           paramètres
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check004)
   public List<DslValidationRuleNokBean> checkEtatFinalLoop (final org.obeonetwork.graal.Loop p_loop, final Object[] p_parametres)
   {
      return checkEtatFinalActivity(p_loop, DslValidationRuleReferentiel_Enum.DslGraal_Check004);
   }

   /**
    * Regle 5 : Il faut un UserAction, par exemple si uniquement AppliAction, le
    * plan d'action est inadapte.
    * @param p_task
    *           le plan d'action
    * @param p_parametres
    *           paramètres
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check005)
   public List<DslValidationRuleNokBean> checkUserActionPlanAction_sce5 (final org.obeonetwork.graal.Task p_task, final Object[] p_parametres)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      boolean v_found = false;
      for (Node v_node : p_task.getNodes())
      {
         if (v_node instanceof UserAction)
         {
            v_found = true;
            break;
         }
      }
      if (!(v_found))
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslGraal_Check005, p_task));
      }

      return v_objetsInvalides;
   }

   /**
    * Regle 6 : chaque plan d'action doit avoir un etat final
    * @param p_task
    *           le plan d'action
    * @param p_parametres
    *           paramètres
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check006)
   public List<DslValidationRuleNokBean> checkEtatFinalPlanAction (final org.obeonetwork.graal.Task p_task, final Object[] p_parametres)
   {
      return checkEtatFinalActivity(p_task, DslValidationRuleReferentiel_Enum.DslGraal_Check006);
   }

   /**
    * Règle 4 / 6 : une loop ou un use case doit avoir un état final
    * @param p_activity
    *           la loop ou le use case
    * @param p_rule
    *           la règle à vérifier
    * @return liste d'erreurs
    */
   private List<DslValidationRuleNokBean> checkEtatFinalActivity (final Activity p_activity, final DslValidationRule_Itf p_rule)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      boolean v_found = false;
      for (Node v_node : p_activity.getNodes())
      {
         if (v_node instanceof FinalNode)
         {
            v_found = true;
            break;
         }
      }
      if (!(v_found))
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(p_rule, p_activity));
      }
      return v_objetsInvalides;
   }

   /**
    * Règle 7 : Il est recommande de ne pas depasse plusieurs niveaux dans une
    * boucle
    * @param p_task
    *           task
    * @param p_parametres
    *           paramètres (le premier est le nombre de niveaux à ne pas
    *           dépasser)
    * @return liste d'erreurs
    */
   @DslValidationRule(DslValidationRuleReferentiel_Enum.DslGraal_Check007)
   public List<DslValidationRuleNokBean> checkImbricationLoops (final org.obeonetwork.graal.Task p_task, final Object[] p_parametres)
   {
      final List<DslValidationRuleNokBean> v_objetsInvalides = new ArrayList<DslValidationRuleNokBean>();
      final List<Loop> v_errorLoops = new ArrayList<Loop>();

      // 1 paramètre attendu
      if (p_parametres.length != 1)
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.WrongParameters, p_task, DslValidationRuleReferentiel_Enum.DslGraal_Check007.get_id(), "1 paramètre attendu : le nombre d'imbrications de boucles toléré"));
         return v_objetsInvalides;
      }

      // Le premier paramètre est le nombre max de loops à ne pas dépasser
      final int v_maxLoops = Integer.valueOf(p_parametres[0].toString());

      for (Activity v_activite : p_task.getSubActivities())
      {
         if (v_activite instanceof Loop)
         {
            v_errorLoops.addAll(getLoops((Loop) v_activite, 1, v_maxLoops));
         }
      }
      for (final Loop v_childLoop : v_errorLoops)
      {
         v_objetsInvalides.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.DslGraal_Check007, v_childLoop, v_maxLoops));
      }
      return v_objetsInvalides;
   }

   /**
    * Retourne les sous-boucles en erreurs
    * @param p_loop
    *           loop parente
    * @param p_degreParente
    *           numéro d'imbrication courant
    * @param p_maxLoops
    *           le nombre max d'imbrication à ne pas dépasser
    * @return la liste des loops en erreur
    */
   private List<Loop> getLoops (final Loop p_loop, final int p_degreParente, final int p_maxLoops)
   {
      final List<Loop> v_errorLoops = new ArrayList<Loop>();
      final List<Loop> v_ChildLoops = new ArrayList<Loop>();
      for (Activity v_activite : p_loop.getSubActivities())
      {
         if (v_activite instanceof Loop)
         {
            v_ChildLoops.add((Loop) v_activite);
         }
      }
      if (!v_ChildLoops.isEmpty())
      {
         if (p_degreParente >= p_maxLoops)
         {
            v_errorLoops.addAll(v_ChildLoops);
         }
         for (Loop v_childLoop : v_ChildLoops)
         {
            v_errorLoops.addAll(getLoops(v_childLoop, p_degreParente + 1, p_maxLoops));
         }
      }
      return v_errorLoops;
   }
}
