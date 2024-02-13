package fr.pacman.commons.services;

import java.util.logging.Logger;

import org.eclipse.core.runtime.Status;

import fr.pacman.commons.Activator;
import fr.pacman.commons.convention.ApplyNorme;
import fr.pacman.commons.properties.PacmanProperty;

public class LoggerUtils
{

   /**
    * Affiche un message d'avertissement (dans l'error log).
    * @param p_message
    *           le message d'avertissement
    */
   public static void warn (final String p_message)
   {
      if (Activator.getDefault() != null)
      {
         Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.c_PLUGIN_ID, p_message));
      }
      else
      {
         Logger.getLogger(ApplyNorme.class.getName()).warning(p_message);
      }
   }

   /**
    * Affiche un message informatif (dans la console).
    * @param p_message
    *           le message d'avertissement
    */
   public static void info (final String p_message)
   {
      if (Activator.getDefault() != null)
      {
         Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.c_PLUGIN_ID, p_message));
      }
      else
      {
         Logger.getLogger(ApplyNorme.class.getName()).info(p_message);
      }
   }

   /**
    * 
    * @param p_property
    */
   public static void logProperty (final PacmanProperty p_property)
   {
      info(p_property.get_id() + " = " + p_property.get_value());
   }
}
