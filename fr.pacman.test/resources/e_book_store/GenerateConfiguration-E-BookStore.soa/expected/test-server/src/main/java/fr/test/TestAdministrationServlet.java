/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test;

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
public class TestAdministrationServlet extends AdministrationServlet
{
   private static final long serialVersionUID = 1L;

   @Override
   protected AdministrationService_Itf findService (final String path)
   {
      final AdministrationService_Itf service = TestAdministrationServiceEnum.getServiceFromPath (path);
      if (service != null)
      {
         return service;
      }
      return super.findService(path);
   }

   @Override
   @SuppressWarnings("rawtypes")
   public void executeService (final AdministrationService_Itf service, final Map parameters)
   {
      final Logger logger = LogManager.getLogger(TestAdministrationServlet.class);
      if (service == TestAdministrationServiceEnum.CLEAR_CACHES)
      {
         logger.info("Demande de purge et rechargement du cache");
         TestCacheManager.clearCaches();
      }
      else
      {
         super.executeService(service, parameters);
      }
   }

   @Override
   protected List<UserPersistence_Abs> getUserPersistences ()
   {
      return Arrays.asList(null);
   }
}
