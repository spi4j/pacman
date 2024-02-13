package fr.pacman.commons.services;

import org.obeonetwork.dsl.environment.Enumeration;
import org.obeonetwork.dsl.environment.Literal;

public class EnumerationUtils
{

   private EnumerationUtils ()
   {
      //
   }

   public static String getMaxSqlSizeForEnumeration (final Enumeration p_enum)
   {
      int maxSize = 0;

      for (Literal el : p_enum.getLiterals())
      {
         if (maxSize < el.getName().length())
            maxSize = el.getName().length();
      }
      return Integer.toString(maxSize);
   }
}
