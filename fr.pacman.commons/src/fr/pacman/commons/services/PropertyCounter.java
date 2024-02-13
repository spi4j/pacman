package fr.pacman.commons.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Compteur de propriétés.
 * @author MINARM
 */
public class PropertyCounter
{

   private final Map<String, Integer> _counters = new HashMap<String, Integer>();

   /**
    * Incremente une propriete.
    * @param p_property la propriete a incrementer
    * @return la nouvelle valeur pour cette propriete
    */
   public int incrementPropertyCount (final String p_property)
   {
      if (_counters.containsKey(p_property))
      {
         _counters.put(p_property, _counters.get(p_property) + 1);
      }
      else
      {
         _counters.put(p_property, 1);
      }
      return _counters.get(p_property).intValue();
   }

   /**
    * (Re)initialise le compteur pour une propriete.
    * @param p_property la propriete a (re)initialiser
    */
   public void initPropertyCount (final String p_property)
   {
      _counters.put(p_property, 0);
   }

}
