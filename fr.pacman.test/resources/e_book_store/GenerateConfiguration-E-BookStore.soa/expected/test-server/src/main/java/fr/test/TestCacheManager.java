/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test;

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
public class TestCacheManager implements ServletContextListener
{

   private static final Logger LOG = LogManager.getLogger(TestCacheManager.class);

   /**
    * Heure de la purge quotidienne des caches.
    */
   private static final int CLEARCACHESHOUR = 0;

   /**
    * Le job pour la purge des caches.
    */
   private transient SpiJob_Abs jobPurgeCache;

   @Override
   public void contextInitialized (final ServletContextEvent sce)
   {
      jobPurgeCache = new SpiJob_Abs("Purge des caches")
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
         protected void doOnException (final Throwable ex)
         {
            LOG.warn(ex.toString(), ex);
         }

         @Override
         protected UserPersistence_Abs getUserPersistence ()
         {
            return null;
         }
      };
      // on purge les caches de service tous les jours a l'heure souhaitee
      jobPurgeCache.scheduleDaily(CLEARCACHESHOUR);

   }

   @Override
   public void contextDestroyed (final ServletContextEvent sce)
   {
      if (jobPurgeCache != null)
      {
         // Eviter les fuites memoires.
         jobPurgeCache.cancel();
      }
      ServiceCacheProxy.shutdown();
   }

   public static void clearCaches ()
   {

      ServiceCacheProxy.clearCaches();
      LOG.info("Caches purges");

	  // for reloading caches
	  // Start of user code 2a8c3b09df4c65db0fab193d280792c5

      // End of user code

      LOG.info("Caches recharges");
   }
}
