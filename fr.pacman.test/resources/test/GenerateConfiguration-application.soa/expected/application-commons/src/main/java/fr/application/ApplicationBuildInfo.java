/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import fr.spi4j.admin.BuildInfo_Abs;

/**
 * Recuperation ou affichage des informations sur le build.<br>
 * (cles : build.number, build.id, build.svnRevision, build.svnUrl,
 * build.projectVersion,
 * etc telles que definies dans le pom.xml)
 */

public class ApplicationBuildInfo extends BuildInfo_Abs
{

   private static final ApplicationBuildInfo c_instance = new ApplicationBuildInfo ();

   private final Map<String, String> _infos;

   /**
    * Constructeur.
   */
    private ApplicationBuildInfo ()
    {
      super();
      _infos = loadInfos();
    }

	/**
    * @return les infos chargees depuis le fichier properties construit par la pic
    */
   private Map<String, String> loadInfos ()
   {
      final InputStream v_input = getClass().getResourceAsStream("/Application-build-info.properties");

	  if (v_input == null)
      {
         return Collections.emptyMap();
      }

      final Properties v_properties = new Properties();
      try
      {
         try
         {
            v_properties.load(v_input);
         }
         finally
         {
			if(null != v_input){
            	v_input.close();
			}
         }
      }
      catch (final IOException v_e)
      {
         // n'est pas cense arrive puisque le stream et donc le fichier
         // existe a cet endroit
         throw new IllegalStateException(v_e);
      }

      final Map<String, String> v_map = new LinkedHashMap<String, String>();
      v_map.put("java.version", System.getProperty("java.version"));
      for (final Entry<Object, Object> v_entry : v_properties.entrySet())
      {
         final String v_key = (String) v_entry.getKey();
         // on ne garde que les proprietes correspondant au pattern souhaite
         // et pas les autres proprietes
         if (v_key.startsWith("build."))
         {
            v_map.put(v_key, (String) v_entry.getValue());
         }
      }
      return Collections.unmodifiableMap(v_map);
   }


	/**
    * @return l'instance du BuildInfo
    */
   public static ApplicationBuildInfo get_instance ()
   {
      return c_instance;
   }

	/**
    * Affiche les informations de build dans le log au niveau info
    * (si le build provient de Maven ou de la PIC).
    */
   public static void logBuildInfo()
   {
      final Logger v_logger = LogManager.getLogger(ApplicationBuildInfo.class);
      for (final Map.Entry<String, String> v_entry : get_instance()._infos.entrySet())
      {
         v_logger.info(v_entry.getKey() + " = " + v_entry.getValue());
      }
   }

   /**
    * @return l'identifiant du build
    */
   public String getBuildId ()
   {
      return _infos.get("build.id");
   }

   /**
    * @return Retourne l'identifiant unique de cette application
    */
   public String getUniqueId ()
   {
      return _infos.get("build.tag");
   }

   @Override
   public String getNomApplication ()
   {
      return _infos.get("build.projectArtifactId");
   }

   @Override
   public String getVersion ()
   {
      return _infos.get("build.projectVersion");
   }

   @Override
   public String getRevision ()
   {
      return _infos.get("build.svnRevision");
   }

   @Override
   public String getUrl ()
   {
      return _infos.get("build.svnUrl");
   }
}
