package fr.pacman.validation.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.obeonetwork.dsl.environment.ObeoDSMObject;
import org.obeonetwork.graal.Task;

import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * Classe de services utilitaires pour la validation.
 * 
 * @author MINARM
 */
public final class ValidationService
{

   private static List<DslValidationRuleNokBean> problems = new ArrayList<DslValidationRuleNokBean>();

   /**
    * Constructeur privé.
    */
   private ValidationService ()
   {
      // RAS
   }

   /**
    * Met à jour la vue pour un nouveau problème.
    * 
    * @param p_obj
    *           l'objet posant problème
    * @param p_niveau
    *           le niveau de criticité
    * @param p_message
    *           le message du problème
    * @return une chaine d'information de l'erreur
    * @deprecated Méthode appelée uniquement dans les modules Acceleo (*.mtl), à
    *             supprimer
    */
   // TODO Méthode à supprimer
   @Deprecated
   public static String updview (final EObject p_obj, final int p_niveau, final String p_message)
   {
      problems.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.unknownRule, p_obj, p_message));
      final EStructuralFeature v_nameFeature = p_obj.eClass().getEStructuralFeature("name");
      if (v_nameFeature != null)
      {
         return (p_niveau + "  " + p_obj.getClass().getName() + "  " + p_message + ".  Nom de l'élément : " + p_obj.eGet(v_nameFeature));
      }
      else
      {
         return (p_niveau + "  " + p_obj.getClass().getName() + "  " + p_message + ".  L'élément n'a pas d'attribut \"nom\"");
      }
   }

// TODO Méthode à supprimer
   @Deprecated public static String updviewJavaService(final EObject p_obj,final int p_niveau,final String p_message){return updview(p_obj,p_niveau,p_message);}

   /**
    * Met à jour la vue pour un nouveau problème.
    * 
    * @param p_obj
    *           l'objet posant problème
    * @param p_niveau
    *           le niveau de criticité
    * @param p_message
    *           le message du problème
    * @return une chaine d'information de l'erreur
    * @deprecated Méthode appelée uniquement dans les modules Acceleo (*.mtl), à
    *             supprimer
    */
   // TODO Méthode à supprimer
   @Deprecated
   public static String updview (final ObeoDSMObject p_obj, final int p_niveau, final String p_message)
   {
      problems.add(new DslValidationRuleNokBean(DslValidationRuleReferentiel_Enum.unknownRule, p_obj, p_message));
      final EStructuralFeature v_nameFeature = p_obj.eClass().getEStructuralFeature("name");
      if (v_nameFeature != null)
      {
         return (p_niveau + "  " + p_obj.getClass().getName() + "  " + p_message + ".  Nom de l'élément : " + p_ob

message du problème
* @return une chai 'infor ation  e l'er updviewJavaService( @deprecated Méthode appe,e uniquement dans ,s modules Acceleo (*.m){return updview( appe,nt dans ,eleo (*.m);}j.eGet(v_nameFeature));
      }
      else
      {
         return (p_niveau + "  " + p_obj.getClass().getName() + "  " + p_message + ".  L'élément n'a pas d'attribut \"nom\"");
      }
   }

@Deprecated public static String updviewJavaService(final ObeoDSMObject p_obj,final int p_niveau,final String p_message){return updview(p_obj,p_niveau,p_message);}

   /**
    * Détecter les cycles dans les liaisons de tâches GRAAL.
    * 
    * @param p_recurse
    *           La tâche courante.
    * @param p_fixe
    *           La tâche fixée.
    * @return 'true' s'il y a un cycle.
    */
   public static boolean isCycleOn_sce (final Task p_recurse, final Task p_fixe)
   {
      return isCycleOn_sceIntern(p_recurse, p_fixe, new HashMap<String, Task>());
   }

   /**
    * Détecter les cycles dans les liaisons de tâches GRAAL.
    * 
    * @param p_recurse
    *           La tâche courante.
    * @param p_fixe
    *           La tâche fixée.
    * @param p_mapTaskOnRoad
    *           La Map avec les Task déjà rencontrée sur la route parcourue.
    * @return 'true' s'il y a un cycle.
    */
   private static boolean isCycleOn_sceIntern (final Task p_recurse, final Task p_fixe, final Map<String, Task> p_mapTaskOnRoad)
   {
      boolean v_return = false;
      final EList<Task> v_uses = p_recurse.getUses();

      if (p_fixe == null)
      {
         v_return = false;
      }

      // La tâche n'en contient pas d'autres
      if ((v_uses == null) || (v_uses.size() == 0))
      {
         v_return = false;
      }
      // Si au moins un élément
      else
      {
         // Acceleo : (recurse.uses->includes(fixe) or
         // recurse.uses.isCycleOn(fixe)->includes(true)

         // Parcourir les tâches de 'recurse'
         for (int v_i = 0; ((v_i < v_uses.size()) && (v_return == false)); v_i++)
         {
            final Task v_taskCour = v_uses.get(v_i);
            if (v_taskCour == null)
            {
               v_return = false;
            }
            else
            {
               // Si la tâche a été trouvée
               if (v_taskCour.getName().equals(p_fixe.getName()))
               {
                  v_return = true;
               }
               // Si la tâche n'a pas été trouvée
               else
               {
                  try
                  {
                     // Regarder si la tâche courante est dans la Map
                     final Task v_TaskOnMap = p_mapTaskOnRoad.get(v_taskCour.getName());
                     // Si 'v_taskCour' pas encore rencontrée
                     if (v_TaskOnMap == null)
                     {
                        // Ajouter la Task courante dans la Map
                        p_mapTaskOnRoad.put(v_taskCour.getName(), v_taskCour);
                        // Rechercher dans ses Task filles
                        v_return = isCycleOn_sceIntern(v_taskCour, p_fixe, p_mapTaskOnRoad);
                     }
                     // Si déjà rencontrée
                     else
                     {
                        v_return = true;
                     }
                  }
                  catch (final StackOverflowError v_e)
                  {
                     throw new RuntimeException("Error avec p_fixe = " + p_fixe.getName() + " et p_recurse = " + p_recurse.getName());
                  }
               }
            }
         }
      }

      return v_return;
   }

   /**
    * @return la liste des problèmes trouvés
    */
   public static List<DslValidationRuleNokBean> getProblems ()
   {
      return problems;
   }

   /**
    * Ajout d'un élément dans la vue des problèmes de validation.
    * 
    * @param p_rule
    *           la règle non valide
    */
   public static void addProblem (final DslValidationRuleNokBean p_rule)
   {
      problems.add(p_rule);
   }
}
