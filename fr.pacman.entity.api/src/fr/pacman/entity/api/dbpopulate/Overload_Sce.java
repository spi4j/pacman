package fr.pacman.entity.api.dbpopulate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Namespace;
import org.obeonetwork.dsl.environment.Reference;
import org.obeonetwork.dsl.environment.StructuredType;
import org.obeonetwork.dsl.environment.Type;

/**
 * Classe de service associée au peuplement de la BdD pour la montée en charge de l'application.
 * 
 * @author MINARM
 */
public class Overload_Sce
{
   /**
    * Traduction de la query Acceleo : b.entities->union(b.entities.references.type->asSet())->sortedBy(name))
    * 
    * @param p_Namespace
    *           Le namespace (= le package) à analyser.
    * @return La liste des entités du namespace et celles référencées par les entités de ce block.
    */
   public List<Entity> getListEntityOfBlockAndReferencedEntities (final Namespace p_Namespace)
   {
      final List<Entity> v_return = new ArrayList<Entity>();

      final Set<Entity> v_SetAllEntities = new HashSet<Entity>();
      // Obtenir les entités du Namespace
      final List<Entity> v_listEntity = new ArrayList<Entity>();
      List<Type> v_types = p_Namespace.getTypes();
      for (Type v_type : v_types)
      {
         if (v_type instanceof Entity)
         {
            v_listEntity.add((Entity)v_type);
         }
      }

      // Parcourir les entités
      for (Entity v_Entity : v_listEntity)
      {
         // Obtenir la liste des références de l'entité courante
         final List<Reference> v_ReferencesEntity = v_Entity.getReferences();
         // Ajouter l'entité courante (seulement si elle a des relations)
         if (v_ReferencesEntity != null && !v_ReferencesEntity.isEmpty())
         {
            v_SetAllEntities.add(v_Entity);
            // Parcourir les références de l'entité
            for (Reference v_Reference : v_ReferencesEntity)
            {
               // Ajouter l'entité référencée dans la liste
               StructuredType v_referencedType2 = v_Reference.getReferencedType();
               if(v_referencedType2 instanceof Entity){
               Entity v_referencedType = (Entity) v_referencedType2;
               v_SetAllEntities.add(v_referencedType);
               }
            }
         }
      }

      // Trier la liste
      v_return.addAll(v_SetAllEntities);
      Collections.sort(v_return, new Comparator<Entity>()
      {
         @Override
         public int compare (final Entity p_Entity1, final Entity p_Entity2)
         {
            int v_return;

            // Les 2 entités existent
            if ((p_Entity1 != null) && (p_Entity2 != null))
            {
               if ((p_Entity1.getName() != null) && (p_Entity2.getName() != null))
               {
                  v_return = p_Entity1.getName().compareTo(p_Entity2.getName());
               }
               else if ((p_Entity1.getName() == null) && (p_Entity2.getName() != null))
               {
                  v_return = -1;
               }
               else if ((p_Entity1.getName() != null) && (p_Entity2.getName() == null))
               {
                  v_return = 1;
               }
               // Les 2 noms d'entités sont 'null'
               else
               {
                  v_return = 0;
               }
            }
            else if ((p_Entity1 == null) && (p_Entity2 != null))
            {
               v_return = -1;
            }
            else if ((p_Entity1 != null) && (p_Entity2 == null))
            {
               v_return = 1;
            }
            // Si les 2 entités sont 'null'
            else
            {
               v_return = 0;
            }

            return v_return;
         }
      });

      return v_return;
   }
}
