/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.spi4j.SpiJob_Abs;
import fr.spi4j.business.ServiceCacheProxy;
import fr.spi4j.persistence.UserPersistence_Abs;

/**
 * Classe permettant de demarrer le job de purge des caches lors de l'initialisation de la webapp.
 * @author safr@n
 */
public class ApplicationCacheManager implements ServletContextListener
{

   private static final Logger c_log = LogManager.getLogger(ApplicationCacheManager.class);

   /**
    * Heure de la purge quotidienne des caches.
    */
   private static final int c_clearCachesHour = 0;

   /**
    * Le job pour la purge des caches.
    */
   private transient SpiJob_Abs _jobPurgeCache;

   @Override
   public void contextInitialized (final ServletContextEvent p_sce)
   {
      _jobPurgeCache = new SpiJob_Abs("Purge des caches")
      {
         @Override
         protected void run () throws Throwable
         {
            clearCaches();
         }

         @Override
         protected boolean hasTransaction ()
         {
            return false;
         }

         @Override
         protected void doOnException (final Throwable p_ex)
         {
            c_log.warn(p_ex.toString(), p_ex);
         }

         @Override
         protected UserPersistence_Abs getUserPersistence ()
         {
            return null;
         }
      };
      // on purge les caches de service tous les jours a l'heure souhaitee
      _jobPurgeCache.scheduleDaily(c_clearCachesHour);

   }

   @Override
   public void contextDestroyed (final ServletContextEvent p_sce)
   {
      if (_jobPurgeCache != null)
      {
         // Eviter les fuites memoires.
         _jobPurgeCache.cancel();
      }
      ServiceCacheProxy.shutdown();
   }

   public static void clearCaches ()
   {

      ServiceCacheProxy.clearCaches();
      c_log.info("Caches purges");

	  // for reloading caches
	  // Start of user code for reloading caches

      // End of user code

      c_log.info("Caches recharges");
   }
}
