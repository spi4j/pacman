/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test;

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

public class TestBuildInfo extends BuildInfo_Abs
{

   private static final TestBuildInfo INSTANCE = new TestBuildInfo ();

   private final Map<String, String> infos;

   /**
    * Constructeur.
   */
    private TestBuildInfo ()
    {
      super();
      this.infos = loadInfos();
    }

	/**
    * @return les infos chargees depuis le fichier properties construit par la pic
    */
   private Map<String, String> loadInfos ()
   {
      final InputStream input = getClass().getResourceAsStream("/Test-build-info.properties");

	  if (input == null)
      {
         return Collections.emptyMap();
      }

      final Properties properties = new Properties();
      try
      {
         try
         {
            properties.load(input);
         }
         finally
         {
			if(null != input){
            	input.close();
			}
         }
      }
      catch (final IOException e)
      {
         // n'est pas cense arrive puisque le stream et donc le fichier
         // existe a cet endroit
         throw new IllegalStateException(e);
      }

      final Map<String, String> map = new LinkedHashMap<String, String>();
      map.put("java.version", System.getProperty("java.version"));
      for (final Entry<Object, Object> entry : properties.entrySet())
      {
         final String key = (String) entry.getKey();
         // on ne garde que les proprietes correspondant au pattern souhaite
         // et pas les autres proprietes
         if (key.startsWith("build."))
         {
            map.put(key, (String) entry.getValue());
         }
      }
      return Collections.unmodifiableMap(map);
   }


	/**
    * @return l'instance du BuildInfo
    */
   public static TestBuildInfo getInstance ()
   {
      return INSTANCE;
   }

	/**
    * Affiche les informations de build dans le log au niveau info
    * (si le build provient de Maven ou de la PIC).
    */
   public static void logBuildInfo()
   {
      final Logger logger = LogManager.getLogger(TestBuildInfo.class);
      for (final Map.Entry<String, String> entry : getInstance().infos.entrySet())
      {
         logger.info(entry.getKey() + " = " + entry.getValue());
      }
   }

   /**
    * @return l'identifiant du build
    */
   public String getBuildId ()
   {
      return this.infos.get("build.id");
   }

   /**
    * @return Retourne l'identifiant unique de cette application
    */
   public String getUniqueId ()
   {
      return this.infos.get("build.tag");
   }

   @Override
   public String getNomApplication ()
   {
      return this.infos.get("build.projectArtifactId");
   }

   @Override
   public String getVersion ()
   {
      return this.infos.get("build.projectVersion");
   }

   @Override
   public String getRevision ()
   {
      return this.infos.get("build.svnRevision");
   }

   @Override
   public String getUrl ()
   {
      return infos.get("build.svnUrl");
   }
}
