package fr.pacman.soalight.services;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Reference;
import org.obeonetwork.dsl.environment.Type;
import org.obeonetwork.dsl.soa.Operation;
import org.obeonetwork.dsl.soa.Parameter;
import org.obeonetwork.dsl.soa.Service;

/**
 * Classe utilitaire pour rechercher des DTO.
 * @author MINARM
 */
public class WSUtils
{

   /**
    * Cherche la liste des xto à générer
    * @param p_operations
    *           la liste des opérations de services de l'application
    * @return la liste des xto à générer
    */
   public Set<Entity> xtoAGenerer (final List<Operation> p_operations)
   {
      final Set<Entity> v_xtos = new TreeSet<Entity>(new Comparator<Entity>()
      {
         @Override
         public int compare (final Entity p_o1, final Entity p_o2)
         {
            if (p_o1 == null && p_o2 == null) {
               return 0;
            }
            if (p_o1 == null) {
               return -1;
            }
            if (p_o2 == null) {
               return 1;
            }
            if (p_o1.getName() == null && p_o2.getName() == null) {
               return 0;
            }
            // tri selon le nom du DTO
            return p_o1.getName().compareTo(p_o2.getName());
         }
      });
      for (final Operation v_op : p_operations)
      {
         if (v_op.isPublic())
         {
            for (final Parameter v_param : v_op.getOutput())
            {
               findDependencies(v_xtos, v_param.getType());
            }
            for (final Parameter v_param : v_op.getInput())
            {
               findDependencies(v_xtos, v_param.getType());
            }
         }
      }
      return v_xtos;
   }

public Set<Entity> xtoAGenererJavaService(final List<Operation> p_operations){return xtoAGenerer(p_operations);}

   /**
    * Cherche les dépendances d'un type.
    * @param p_dependencies
    *           les dépendances déjà trouvées
    * @param p_type
    *           le type
    */
   private static void findDependencies (final Set<Entity> p_dependencies, final Type p_type)
   {
      // vérifie le type
      if (p_type instanceof Entity)
      {
         // cast
         final Entity v_entity = (Entity) p_type;
         // si la dépendance n'a pas encore été traitée
         if (!p_dependencies.contains(p_type))
         {
            // ajouter la nouvelle dépendance
            p_dependencies.add(v_entity);
            // chercher dans ses références les nouvelles dépendances
            for (final Reference v_ref : v_entity.getReferences())
            {
               if (v_ref.isNavigable())
               {
                  findDependencies(p_dependencies, v_ref.getReferencedType());
               }
            }
         }
      }
   }


   /**
    * Retourne un Set de Services sans doublons
    * @param p_services
    *      la liste de Services
    * @return le set de service à générer
    *
    */
   public static Set<Service> listeServices (final List<Service> p_services)
   {
      final Set<Service> v_services = new TreeSet<Service>(new Comparator<Service>()
      {
         @Override
            public int compare (final Service p_o1, final Service p_o2)
            {
               if (p_o1 == null && p_o2 == null) {
                  return 0;
               }
               if (p_o1 == null) {
                  return -1;
               }
               if (p_o2 == null) {
                  return 1;
               }
               if (p_o1.getName() == null && p_o2.getName() == null) {
                  return 0;
               }
               // tri selon le nom du Service
               return p_o1.getName().compareTo(p_o2.getName());
            }
      }
      );
      for (final Service v_service : p_services)
      {
         v_services.add(v_service);
      }
      return v_services;
   }

}
