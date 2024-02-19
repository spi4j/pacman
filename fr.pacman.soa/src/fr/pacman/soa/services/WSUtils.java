package fr.pacman.soa.services;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.obeonetwork.dsl.environment.DTO;
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
   public Set<DTO> xtoAGenerer (final List<Operation> p_operations)
   {
      final Set<DTO> v_xtos = new TreeSet<DTO>(new Comparator<DTO>()
      {
         @Override
         public int compare (final DTO p_o1, final DTO p_o2)
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

public Set<DTO> xtoAGenererJavaService(final List<Operation> p_operations){return xtoAGenerer(p_operations);}

   /**
    * Cherche les dépendances d'un type.
    * @param p_dependencies
    *           les dépendances déjà trouvées
    * @param p_type
    *           le type
    */
   private static void findDependencies (final Set<DTO> p_dependencies, final Type p_type)
   {
      // vérifie le type
      if (p_type instanceof DTO)
      {
         // cast
         final DTO v_dto = (DTO) p_type;
         // si la dépendance n'a pas encore été traitée
         if (!p_dependencies.contains(p_type))
         {
            // ajouter la nouvelle dépendance
            p_dependencies.add(v_dto);
            // chercher dans ses références les nouvelles dépendances
            for (final Reference v_ref : v_dto.getReferences())
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
    * Retourne un Set de DTO sans doublons
    * @param p_dtos
    *      la liste de Dtos
    * @return le set de dto à générer
    *
    */
   public static Set<DTO> listeDtos (final List<DTO> p_dtos)
   {
      final Set<DTO> v_dtos = new TreeSet<DTO>(new Comparator<DTO>()
      {
         @Override
            public int compare (final DTO p_o1, final DTO p_o2)
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
      }
      );
      for (final DTO v_dto : p_dtos)
      {
         v_dtos.add(v_dto);
      }
      return v_dtos;
   }

public static Set<DTO> listeDtosJavaService(final List<DTO> p_dtos){return listeDtos(p_dtos);}


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
         if ( !v_services.contains(v_service))
         {
            v_services.add(v_service);
         }            
      }
      return v_services;
   }


}
