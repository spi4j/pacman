package fr.pacman.commons.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.convention.project.Spi4jProperties;
import fr.pacman.commons.convention.rule.AttributeRule;
import fr.pacman.commons.convention.rule.ClassRule;
import fr.pacman.commons.convention.rule.CommonRule;
import fr.pacman.commons.convention.rule.MethodRule;
import fr.pacman.commons.convention.rule.PackageRule;
import fr.pacman.commons.convention.rule.PageRule;
import fr.pacman.commons.convention.rule.ParameterRule;
import fr.pacman.commons.convention.rule.VariableRule;
import fr.pacman.commons.services.LoggerUtils;
import fr.pacman.commons.services.PlugInUtils;

/**
 * @author MINARM
 * 
 * @TODO : gerer proprement les exceptions......
 */
public final class PacmanPropertiesManager
{
   /**
    * 'true' : PacMan en mode Debug
    */
   private static boolean _debug;

   /**
    * 'true' : affiche un message avant de regenerer les fichiers '.properties'.
    */
   private static boolean _displayMessage;

   /**
    * Le singleton (Instance qui ne dure que le temps du generateur !).
    */
   private static PacmanPropertiesManager _instance;

   /**
    * Le nom par defaut du repertoire des fichiers '.properties'.
    */
   private final static String c_dirProperties = "pacman-properties";

   /**
    * Ensemble des proprietes chargees en memoire.
    */
   private final PacmanProperties _pacmanProperties;

   /**
    * Le chemin courant du projet pour les fichiers '.properties'.
    */
   private final String _currentModelPath;

   /**
    * La liste des proprietes pour le model courant (_currentModelPath).
    */
   private static Map<String, PacmanPropertiesCategory_Abs[]> _pacmanReferential = new HashMap<String, PacmanPropertiesCategory_Abs[]>();

   /**
    * Creation de la liste des proprietes utilisables par les generateurs.
    * 
    * @return le referentiel pour le model courant.
    */
   protected static PacmanPropertiesCategory_Abs[] createReferential ()
   {
      return new PacmanPropertiesCategory_Abs[]
      {
           // Proprietes du projet (ex : idAppli)
           new ProjectProperties(), new Spi4jProperties(),

           // Proprietes de nommage du code genere du projet (ex : p_[CamelCase/], pour un parametre)
           new AttributeRule(), new MethodRule(), new ClassRule(), new VariableRule(), new ParameterRule(),
           new PackageRule(), new CommonRule(), new PageRule() };
   }

   /**
    * Constructeur.
    * 
    * @param p_modelPath
    *           le chemin du model courant.
    * @param p_properties
    *           la liste des proprietes a mettre a jour dans le manager.
    * @param p_referential
    *           le referentiel a charger.
    */
   private PacmanPropertiesManager (final String p_modelPath, final Properties p_properties,
            PacmanPropertiesCategory_Abs[] p_referential)
   {
      // On initialise la map des proprietes avec le referentiel.
      _pacmanProperties = new PacmanProperties(p_referential);
      // On met a jour la map des proprietes avec les valeurs lues dans les fichiers.
      _pacmanProperties.setProperties(p_properties);
      // On positionne le chemin pour le fichier de modelisation en cours de traitement.
      _currentModelPath = p_modelPath;
   }

   /**
    * Obtenir l'instance du manager pour le model courant. 
    * Bien que l'utilisation soit mono-thread, on synchronise la recuperation de l'instance. 
    * Ici le manque d'optimisation lie au mot cle 'synchronized' est considere comme sans objet.
    * 
    * @param p_modelPath
    *           Le dossier du modele courant.
    * @param p_properties
    *           Les proprietes a charger pour l'instance.
    * @return _instance L'instance du manager.
    * 
    */
   private static synchronized PacmanPropertiesManager getInstance (final String p_modelPath, Properties p_properties)
   {
      // Message d'ecriture des '.properties'.
      _displayMessage = Boolean.FALSE;

      // On charge les proprietes des '.properties'
      // si aucune injection directe (pacman-start).
      if (null == p_properties)
         p_properties = loadFromPropertiesFiles(p_modelPath);

      // On cree le refentiel pour le model courant (si inexistant dans la map).
      if (null == _pacmanReferential.get(p_modelPath))
         _pacmanReferential.put(p_modelPath, PacmanPropertiesManager.createReferential());

      // Creation de l'instance du manager (on écrase la précédente si elle existe).
      _instance = new PacmanPropertiesManager(p_modelPath, p_properties, 
               _pacmanReferential.get(p_modelPath));

      // Verification des proprietes.
      _instance.checkProperties();

      return _instance;
   }

   /**
    * Demande l'initialisation du manager.
    * 
    * @param p_modelPath
    *           le chemin du projet model.
    * @param p_properties
    *           la liste des proprietes a injecter directement dans le manager (pacman-start).
    */
   public static void initProperties (final String p_modelPath, final Properties p_properties)
   {
      PacmanPropertiesManager.getInstance(p_modelPath, p_properties);
   }

   /**
    * Demande l'initialisation du manager.
    * 
    * @param p_modelPath
    *           le chemin du projet model.
    */
   public static void initProperties (final String p_modelPath)
   {
      PacmanPropertiesManager.getInstance(p_modelPath, null);
   }

   /**
    * Demande l'initialisation du manager.
    * 
    * @param p_modelPath
    *           le chemin du projet model.
    * @param p_properties
    *           la liste des proprietes a injecter directement dans le manager (pacman-start)
    */
   public static void initProperties (String p_modelPath, final Map<String, String> p_properties)
   {
      Properties v_properties = new Properties();
      v_properties.putAll(p_properties);
      initProperties(p_modelPath, v_properties);
   }

   /**
    * Charge l'ensemble des fichiers '.properties' associes au '_currentModelPath'.
    * 
    * @param p_modelPath
    *           le chemin du modele
    * @return les proprietes trouvees dans ce repertoire.
    */
   private static Properties loadFromPropertiesFiles (final String p_modelPath)
   {
      // Les proprietes qui seront mises dans la Map associee au repertoire courant.
      Properties v_properties = new Properties();

      try
      {
         // Recupere le repertoire ou sont stockes les fichiers '.properties'.
         File v_currentDirectory = getPropertiesDirectory(p_modelPath);

         // Recupere la liste des fichiers '.properties'
         String[] v_lstPropertiesFiles = getPropertyFiles(v_currentDirectory);

         // Chargement des proprietes.
         v_properties = loadProperties(v_lstPropertiesFiles, v_currentDirectory);

      }
      catch (final FileNotFoundException v_err)
      {
         throw new IllegalArgumentException("Problème pour ouvrir un fichier : ", v_err);
      }
      catch (final IOException v_err)
      {
         throw new IllegalArgumentException(
                  "Problème lors de l'écriture d'une propriété : ou de la fermeture du fichier", v_err);
      }
      catch (final IllegalArgumentException v_err)
      {
         throw new IllegalArgumentException("Problème lors du getInstance()" + v_err);
      }
      return v_properties;
   }

   /**
    * Recuperation du repertoire pour le stockage des fichiers '.properties'.
    * 
    * @param p_modelPath
    * @return
    */
   private static File getPropertiesDirectory (final String p_modelPath)
   {
      // Le nombre de dossiers a remonter avant de stopper la recherche.
      final int v_nbMaxIterations = 3;
      int v_iteration = 1;

      // On recupere le repertoire du modele.
      File v_currentDirectory = new File(p_modelPath);

      // On recherche le repertoire des '.properties' dans le repertoire du model.
      String v_pathDirProperty = getSubDirectoryProperties(v_currentDirectory);

      // Tant qu'il n'y a pas de proprietes dans le repertoire
      // on remonte d'un niveau pour un maximum de v_nbMaxIterations.
      while ((v_pathDirProperty == null) && (v_currentDirectory != null) && v_iteration < v_nbMaxIterations)
      {
         v_currentDirectory = v_currentDirectory.getParentFile();
         v_pathDirProperty = getSubDirectoryProperties(v_currentDirectory);
         v_iteration++;
      }

      // On met a jour le repertoire courant (si possible).
      if (null != v_pathDirProperty)
         v_currentDirectory = new File(v_pathDirProperty);

      return v_currentDirectory;
   }

   /**
    * Retourne le sous-repertoire des '.properties' du repertoire passe en parametre.
    * 
    * @param p_repertoire
    *           ou l'on cherche le repertoire des properties
    * @return La liste des path de proprietes du repertoire
    */
   private static String getSubDirectoryProperties (final File p_directory)
   {
      final String[] v_dirFiles = p_directory.list(new FilenameFilter()
      {
         @Override
         public boolean accept (final File p_dir, final String p_name)
         {
            boolean v_isDirectoryProperty = false;

            if (p_name.contains(c_dirProperties) && (p_dir.isDirectory()))
               v_isDirectoryProperty = true;

            return v_isDirectoryProperty;
         }
      });

      if (v_dirFiles.length > 0)
         return p_directory + File.separator + v_dirFiles[0];

      return null;
   }

   /**
    * Retourne la liste des chemins pour les fichiers '.properties' du repertoire passe en parametre.
    * 
    * @param p_repertoire
    *           ou l'on va chercher les fichiers '.properties'.
    * @return La liste des path de fichiers propriete du repertoire
    */
   private static String[] getPropertyFiles (final File p_directory)
   {
      return p_directory.list(new FilenameFilter()
      {
         @Override
         public boolean accept (final File p_dir, final String p_name)
         {
            boolean v_isPropertyFile = false;
            if (p_name.endsWith(".properties"))
            {
               v_isPropertyFile = true;
            }
            return v_isPropertyFile;
         }
      });
   }

   /**
    * Retourne les proprietes chargees a partir des fichiers '.properties'.
    * 
    * A ce niveau on fonctionne avaec un java.util.properties.
    * 
    * @param p_listeDeFichiers
    *           La liste des fichiers de type '.properties'.
    * @param p_repertoire
    *           le repertoire ou sont stockes ces fichiers.
    * @return les proprietes chargees a partir des fichiers.
    * @throws IOException
    *            ioe
    */
   private static Properties loadProperties (final String[] p_listFiles, final File p_directory) throws IOException
   {
      // Commme on charge a partir de fichiers
      // on positionne le booleen d'affichage si proprietes manquantes.
      _displayMessage = Boolean.TRUE;

      // On va construire ce properties qui sera extrait des fichiers
      final Properties v_properties = new Properties();
      FileInputStream v_fis;

      // Pour chaque fichier '.properties'.
      for (final String v_file : p_listFiles)
      {
         final File v_propertyFile = new File(p_directory, v_file);
         final Properties v_tempFile = new Properties();
         final FileInputStream v_fisTemp = new FileInputStream(v_propertyFile);
         v_fis = new FileInputStream(v_propertyFile);

         try
         {
            // On verifie qu'il n'y a pas de doublons.
            v_tempFile.load(v_fisTemp);
            for (final Object v_propertyKey : v_tempFile.keySet())
            {
               if (v_properties.containsKey(v_propertyKey))
                  LoggerUtils.warn("Propriété en doublon dans les fichiers : " + v_propertyKey);
            }
            v_properties.load(v_fis);
         }
         finally
         {
            if(null !=  v_fisTemp) {
               v_fisTemp.close();
            }
            
            if(null != v_fis) {
               v_fis.close();
            }
         }
      }
      return v_properties;
   }

   /**
    * Verification des proprietes non remplies / trouvees dans les fichiers '.properties'.
    */
   private void checkProperties ()
   {
      _pacmanProperties.checkProperties();
   }

   /**
    * Construit et ecrit un fichier '.properties', avec les attributs / valeurs par defaut.
    * 
    * @param p_directory
    *           le repertoire ou ecrire le fichier.
    * @return le Properties.
    * @throws IOException
    *            ioe
    */
   private static void writeProperties (final File p_directory, final String p_fileName,
            final List<PacmanProperty> p_properties) throws IOException
   {
      FileOutputStream v_fos = null;
      try
      {
         String v_fileName = p_fileName;

         v_fos = new FileOutputStream(p_directory.getPath() + File.separator + v_fileName, false);
         Collections.sort(p_properties);

         for (PacmanProperty v_property : p_properties)
         {
            // Ecrire la propriete courante.
            if (PacmanPropertyStatus_Enum.MEMORY != v_property.get_defaultStatus())
               v_fos.write(v_property.toStringProperties());
         }
      }
      catch (IOException e)
      {
         LoggerUtils.warn(e.getMessage());
         throw e;
      }
      finally
      {
         try
         {
            if (null != v_fos)
               v_fos.close();
         }
         catch (IOException e)
         {
            // RAS.
         }
      }
   }

   /**
    * La seule et unique reference etant les proprietes presentes dans les classes filles de 'PacmanPropertiesCategory_Abs', 
    * on va ecrire les proprietes dans des fichiers '.properties' a partir des instances de 'PacmanProperty' qui ont ete mises a jour.
    */
   public void writePropertiesFile () throws IOException
   {
      // Initialisation d'une liste de proprietes additionnelles (si existent).
      List<PacmanProperty> v_additionalProperties = new ArrayList<>();
               
      // Prend l'ensemble des categories enregistrees.
      for (PacmanPropertiesCategory_Abs v_pacmanPropertiesCategory : _pacmanReferential.get(_currentModelPath))
      {
         List<PacmanProperty> v_propertiesToFile = new ArrayList<PacmanProperty>();
         String v_propertiesFileName = v_pacmanPropertiesCategory.get_propertiesFileName();
         for (Entry<String, PacmanProperty> v_entry : get_pacmanProperties().entrySet())
         {
            if (v_propertiesFileName.equals(v_entry.getValue().getPropertyFileName())
                     && v_entry.getValue().is_writeToFile())
               v_propertiesToFile.add(v_entry.getValue());
            
            // Verifie s'il s'agit d'une propriete additionnelle.
            else if (v_entry.getValue().get_propertyFileName().isEmpty())
               v_additionalProperties.add(v_entry.getValue());
         }
         
         // Si fichier maitre, on ajoute les eventuelles proprietes additionnelles.
         if(v_pacmanPropertiesCategory.is_defaultFileForAdditionalproperties())
            v_propertiesToFile.addAll(v_additionalProperties);
         
         // Recuperation du chemin pour le stockage des fichiers de propriete 
         // a partir du chemin du fichier de modelisation qui a ete utilise.
         File v_propertiesDirpath = getPropertiesDirectory(_currentModelPath);

         // Ecriture du fichier de propriete dans le bon repertoire.
         PacmanPropertiesManager.writeProperties(new File(v_propertiesDirpath.getPath()),
                  v_propertiesFileName, v_propertiesToFile);
      }
   }

   /**
    * Verifie a la sortie du generateur, si on doit reecrire les fichiers '.properties'.
    */
   public static void exit ()
   {
      try
      {
         // Recuperation de la liste eventuelle des proprietes qui necessitent la reecriture des fichiers.
         List<PacmanProperty> v_writeProperties = _instance._pacmanProperties.get_writeProperties();
         
         // On alerte le developpeur que les fichiers vont etre modifies.
         if (null != v_writeProperties && !v_writeProperties.isEmpty())
         {
            // Si demande d'affichage du message (hors starter).
            if (_displayMessage) 
            {
               int v_cpt = 0;
               // Construction de la liste des proprietes a afficher.
               StringBuilder v_builder = new StringBuilder();
               
               // Ne pas passer par une lambda.........
               for (PacmanProperty v_property : v_writeProperties)
               {
                  v_builder.append(v_property.get_propertyFileName())
                  .append(" : ")
                  .append(v_property.get_id())
                  .append(" -> ")
                  .append(v_property.get_fileAction().get_textToDisplay())
                  .append("\n");   
                  v_cpt++;
                  
                  // On affiche max 25 lignes.
                  if(25 == v_cpt) {
                     v_builder.append("....\n[");
                     v_builder.append(v_writeProperties.size());
                     v_builder.append(" propriétés impactées ...]");
                     break;
                  }
               }
               // Affichage du message.
               PlugInUtils.displayError("Pacman : Gestionnaire de propriétés",
                        "Des propriétés sont manquantes, les fichiers de propriétés vont être regénerés : \n\n"  
                        + v_builder.toString());
            }
            // Ecriture des fichiers de configuration.
            _instance.writePropertiesFile();
         }
      }
      catch (IOException e)
      {
         PlugInUtils.displayError("Pacman : Gestionnaire de propriétés",
                  "Impossible de créer les fichiers de propriété : " + e.getMessage());
      }
   }

   /**
    * Utilise pour recuperer une propriete simple (ex : packagePersistence)
    * 
    * @param p_propertyName
    *           Le nom de la propriete a recuperer.
    * @return La valeur de la propriete.
    */
   private String get_pacmanProperty (final String p_propertyName)
   {
      final PacmanProperty v_property = _pacmanProperties.get_pacmanProperty(p_propertyName);

      if (null == v_property)
      {
         LoggerUtils.warn("La propriété : " + p_propertyName + " n'a pas été trouvée dans les fichiers");
         return null;
      }
      
      // Affiche la propriete et sa valeur dans la console.
      if(isDebug()) LoggerUtils.logProperty(v_property);
      
      return v_property.get_value().toString();

      // return NotationResolution.replaceTagsOfProperties(v_propertyObj.toString(), getAllProperties());
   }

   /**
    * Obtenir Les proprietes du modele courant liees a la normalisation.
    * 
    * @return les proprietes liees a la normalisation.
    * 
    */
   private Properties get_normeProperties ()
   {
      return _instance._pacmanProperties.get_normeProperties();
   }

   /**
    * Vide le cache des proprietes pour lancer une nouvelle generation qui ira lire les proprietes.
    */
   public static void clearProperties ()
   {
      _instance = null;
   }

   /**
    * Obtenir une propriete.
    * 
    * @param p_propertyName
    *           le nom de la propriete recherchee ex : packagePersistence
    * 
    * @return La valeur de la propriete.
    * 
    * @toto gerer le null sur l'instance.
    */
   public static String get_property (final String p_propertyName)
   {
      return PacmanPropertiesManager._instance.get_pacmanProperty(p_propertyName);
   }

   /**
    * LES FONCTIONS PASSE-PLAT - DEBUT.
    */

   /**
    * Obtenir une propriete simple (pour l'instant cette fonction sert de passe-plat). Conserve pour historique (pour l'instant)
    * 
    * @param p_propertyName
    *           le nom de la propriete recherchee ex : packagePersistence
    * @param p_modelFile
    *           Le folder du model contenant l'objet sur lequel on travail.
    * @return La valeur de la propriété.
    */
   public static String get_property (final String p_propertyName, String p_folder)
   {
      return get_property(p_propertyName);
   }

   /**
    * Obtenir toutes les proprietes pour la normalisation.
    */
   public static Properties get_properties ()
   {
      return PacmanPropertiesManager._instance.get_normeProperties();
   }

   /**
    * LES FONCTIONS PASSE-PLAT - FIN.
    */

   /**
    * Permet de savoir si PacMan est en mode Debug.
    * @return 'true' si mode Debug.
    */
   public static boolean isDebug ()
   {
      return _debug;
   }

   /**
    * Permet d'affecter le mode Debug de PacMan.
    * 
    * @param p_isDebug
    *           'true' passer en mode Debug.
    */
   public static void setDebug (final boolean p_isDebug)
   {
      _debug = p_isDebug;
   }

   /**
    * Permet de connaitre le nom du repertoire où se trouvent les fichiers '.properties'.
    * 
    * @return le nom du repertoire des fichiers '.properties'.
    */
   public static String getDirProperties ()
   {
      return c_dirProperties;
   }

public static String getDirPropertiesJavaService(Object object){return getDirProperties();}

   /**
    * Obtenir la liste complete des proprietes au format Pacman.
    * 
    * @return _properties la liste complete des proprietes sous format pacman.
    */
   public Map<String, PacmanProperty> get_pacmanProperties ()
   {
      return _pacmanProperties.get_pacmanProperties();
   }
}
