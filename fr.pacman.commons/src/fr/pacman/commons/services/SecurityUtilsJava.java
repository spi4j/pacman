package fr.pacman.commons.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Status;
import org.obeonetwork.dsl.environment.ObeoDSMObject;
import org.obeonetwork.dsl.soa.Operation;

import fr.pacman.commons.Activator;

/**
 * Service utilitaire dédié au framework de sécurité.
 * 
 * @author MINARM
 */
public class SecurityUtilsJava
{

   /**
    * Vérifie si une Entity est l'Entity correspondant a l'utilisateur.
    * 
    * @param p_entity
    *           le Entity
    * @return true si l'Entity correspond à l'utilisateur
    */
   public boolean entityIsUser (final ObeoDSMObject p_entity)
   {
      return AnnotationUtils.annotationExists(p_entity, "USER", false);
   }

public boolean entityIsUserJavaService(final ObeoDSMObject p_entity){return entityIsUser(p_entity);}

   /**
    * Vérifie si un DTO est le DTO correspondant a l'utilisateur.
    * 
    * @param p_dto
    *           le DTO
    * @return true si le DTO correspond à l'utilisateur
    */
   public boolean dtoIsUser (final ObeoDSMObject p_dto)
   {
      return AnnotationUtils.annotationExists(p_dto, "USER", false);
   }

public boolean dtoIsUserJavaService(final ObeoDSMObject p_dto){return dtoIsUser(p_dto);}
   
   /**
    * Retourne une liste de permissions sur une opération, stockées dans une
    * métadonnées.
    * 
    * @param p_operation
    *           l'opération sur laquelle on cherche les permissions
    * @param p_annotation
    *           le nom de la métadonnée qui contient les permissions
    * @return la liste des permissions
    */
   public List<String> getPermissionsListOfOperation (final Operation p_operation, final String p_annotation)
   {
      final List<String> v_list = new ArrayList<String>();

      final String v_body = AnnotationUtils.getAnnotationBody(p_operation, p_annotation);
      
      // on definit les caracteres de ponctuation (espaces, tabulations, retours chariots, ...)
      final String v_punct = "[\\s\\p{Punct}&&[^_]]";

      // split sur les caractères de ponctuation
      final String[] v_split = v_body.trim().split(v_punct+"+");

      for (final String v_splitElement : v_split)
      {
         // supprimer caractères de ponctuation
        v_list.add(v_splitElement.trim().replaceAll(v_punct, ""));
      }
      return v_list;
   }
   
// Sauvegarde de la version soalight ? quelle est la meilleure ?
   
//   public List<String> getPermissionsListOfOperation (final Operation p_operation, final String p_annotation)
//   {
//      final List<String> v_list = new ArrayList<String>();
//
//      final String v_body = AnnotationUtils.getAnnotationBody(p_operation, p_annotation);
//      // split sur caractères invisibles (espaces, tabulations, retours
//      // chariots, ...)
//      final String[] v_split = v_body.trim().split("[\\s\\p{Punct}&&[^_]]+");
//      for (final String v_splitElement : v_split)
//      {
//         // supprimer caractères de ponctuation
//         v_list.add(v_splitElement.trim().replaceAll("\\p{Punct}", ""));
//      }
//      return v_list;
//   }

   /**
    * Récupère le set de toutes les permissions connues dans le modèle.
    * 
    * @param p_operations
    *           la liste des opérations du modèle
    * @return le set de toutes les permissions connues dans le modèle
    */
   public Set<String> getSetOfAllKnownPermissions (final List<Operation> p_operations)
   {
      final Set<String> v_permissions = new HashSet<String>();
      for (final Operation v_operation : p_operations)
      {
         try
         {
            if (AnnotationUtils.annotationExists(v_operation, "PERMISSIONS", false))
            {
               v_permissions.addAll(getPermissionsListOfOperation(v_operation, "PERMISSIONS"));
            }
         }
         catch (final Exception v_e)
         {
            Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.c_PLUGIN_ID, "Erreur dans les permissions pour l'opération " + v_operation.getName() + " : " + v_e.toString()));
         }
         try
         {
            if (AnnotationUtils.annotationExists(v_operation, "PERMISSIONS_OR", false))
            {
               v_permissions.addAll(getPermissionsListOfOperation(v_operation, "PERMISSIONS_OR"));
            }
         }
         catch (final Exception v_e)
         {
            Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.c_PLUGIN_ID, "Erreur dans les permissions pour l'opération " + v_operation.getName() + " : " + v_e.toString()));
         }
         try
         {
            if (AnnotationUtils.annotationExists(v_operation, "PERMISSIONS_AND", false))
            {
               v_permissions.addAll(getPermissionsListOfOperation(v_operation, "PERMISSIONS_AND"));
            }
         }
         catch (final Exception v_e)
         {
            Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.c_PLUGIN_ID, "Erreur dans les permissions pour l'opération " + v_operation.getName() + " : " + v_e.toString()));
         }
      }
      return v_permissions;
   }

}
