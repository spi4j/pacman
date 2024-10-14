/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.spi4j.admin.AdministrationService_Itf;
import fr.spi4j.admin.AdministrationServlet;
import fr.spi4j.persistence.UserPersistence_Abs;

/**
 * Servlet d'administration.
 */
public class ApplicationAdministrationServlet extends AdministrationServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected AdministrationService_Itf findService (final String p_path)
   {
      final AdministrationService_Itf v_service = ApplicationAdministrationService_Enum.get_serviceFromPath (p_path);
      if (v_service != null)
      {
         return v_service;
      }
      return super.findService(p_path);
   }

   @Override
   @SuppressWarnings("rawtypes")
   public void executeService (final AdministrationService_Itf p_service, final Map p_parameters)
   {
      final Logger v_logger = LogManager.getLogger(ApplicationAdministrationServlet.class);
      if (p_service == ApplicationAdministrationService_Enum.CLEAR_CACHES)
      {
         v_logger.info("Demande de purge et rechargement du cache");
         ApplicationCacheManager.clearCaches();
      }
      else
      {
         super.executeService(p_service, p_parameters);
      }
   }

   @Override
   protected List<UserPersistence_Abs> getUserPersistences ()
   {
      return Arrays.asList(null);
   }
}
