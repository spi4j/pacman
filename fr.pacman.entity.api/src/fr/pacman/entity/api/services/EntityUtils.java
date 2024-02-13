package fr.pacman.entity.api.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.obeonetwork.dsl.environment.Reference;

/**
 * Classe utilitaire pour les  Entity.
 * @author MINARM
 */
public final class EntityUtils
{

   /**
    * Constructeur privé pour classe finale.
    */
   private EntityUtils ()
   {
      super();
   }

   /**
    * Retourne la liste de reference sans doublon
    * @param p_references
    *           le liste de Reference
    * @return le liste de Reference sans doublon
    */
   public static Set<Reference> getReferences (final Set<Reference> p_references)
   {
      final Map<String,Reference> v_references = new HashMap<String,Reference>();
      final Set<Reference> v_finalReferences = new HashSet<Reference>();
      if (!p_references.isEmpty())
      {
         final Iterator<Reference> v_iterator =  p_references.iterator();
         while (v_iterator.hasNext())
         {
            Reference v_reference = v_iterator.next();
            if (!v_references.containsKey(v_reference.getContainingType().getName())){
               v_references.put(v_reference.getContainingType().getName(), v_reference);
            }
         }
         v_finalReferences.addAll(v_references.values());
      }
      return v_finalReferences;
   }

   
}
