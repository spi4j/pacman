package fr.pacman.requirementsgrouper.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * Classe de groupement des exigences.
 *
 * @author durandbe
 */
public class RequirementsGrouper
{

   public RequirementsGrouper(List<IFolder> folders, IFolder finalRequirementDest, final Boolean p_creationALaRacine) {
		super();
		this.folders = folders;
		if (p_creationALaRacine) {
			this.finalRequirementDest = new File(finalRequirementDest.getProject().getLocationURI());
		} else {
			this.finalRequirementDest = new File(finalRequirementDest.getLocationURI());
		}
		this.v_tmpDir = new File(new File(finalRequirementDest.getProject().getLocationURI()), c_TMP);
		this.projectName = finalRequirementDest.getProject().getName();
		this.finalRequirementFileName = projectName + c_CONCATENATION_TERM + c_REQUIREMENT_EXT;
	}

/**
    * Logger.
    */
   private static final Logger c_log = Logger.getLogger(RequirementsGrouper.class.getSimpleName());

   /**
    * Les dossier selectionnés.
    */
   private List<IFolder> folders;
   
   /**
    * Le nom du fichier qui sera généré.
    */
   private String finalRequirementFileName = "somme.requirement";

   /**
    * La déstination du requirement concaténé.
    */
   private File finalRequirementDest;

   /**
    * Le répértoire temporaire à la racine du premier projet selectionné.
    */
   private File v_tmpDir;

   /**
    * Le nom du projet du premier dossier selectionné.
    */
   private String projectName;

   /**
    * Le nom du repository dans le fichier final.
    */
   //private static final String c_finalRepositoryName = "COSI";

   /**
    * L'id XMI du repository dans le fichier final;
    */
   private static final String c_finalXmiId = "XMI_ID";

   private static final String c_REQUIREMENT_EXT = ".requirement";

   private static final String c_CONCATENATION_TERM = "_somme";

   private static final String c_TMP = "tmp";

//	private static final Boolean c_creationALaRacine = false;

   /**
    * Méthode principale de groupement des exigences.
    *
    * @param p_finalRequirementFileName
    *           le nom du fichier à générer.
    */
   public void groupRequirements ()
   {
      cleanTmp();
      v_tmpDir.mkdirs();

      // pour chaque dossier, copier les requirement :
      Iterator<IFolder> folderIt = folders.iterator();
		while (folderIt.hasNext()) { 
			IFolder dossier = (IFolder)folderIt.next();
         
         // rechercher les fichiers .requirement dans le dossier en cour
         for (final IFile v_requirementIFile : findRequirementsInProject(dossier))
         {
        	 final File v_requirementFile = new File(v_requirementIFile.getLocationURI());
            c_log.info("copie du fichier " + v_requirementFile.getAbsolutePath());
            // crée un nom de fichier de destination unique à partir du chemin relatif dans le projet 
            File v_destinationFile = new File(v_requirementIFile.getFullPath().toString().replace("/", "_"));
            copyAndEditFile(v_requirementFile, v_tmpDir, v_destinationFile);
         }
      }

      // regroupement des .requirement en un seul fichier
      final File v_finalFile = new File(finalRequirementDest, finalRequirementFileName);
      OutputStream v_os = null;
      try
      {
         v_os = new FileOutputStream(v_finalFile);
         // écriture de l'en-tête
         v_os.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
         v_os.write(("<requirement:Repository xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:requirement=\"http://www.obeonetwork.org/dsl/requirement/1.0\" xmi:id=\"" + c_finalXmiId + "\" name=\"" + projectName + "\">\n").getBytes());

         InputStream v_is = null;
         for (final File v_f : v_tmpDir.listFiles())
         {
            // lecture du contenu d'un fichier requirement et copie dans le fichier global
            try
            {
               v_is = new FileInputStream(v_f);
               final byte[] v_buffer = new byte[1024];
               int v_read;
               while (v_is.available() > 0)
               {
                  v_read = v_is.read(v_buffer);
                  v_os.write(v_buffer, 0, v_read);
               }
            }
            catch (final IOException v_e)
            {
               c_log.severe("Problème lors de la copie du fichier " + v_f.getAbsolutePath() + " : " + v_e.toString());
            }
            finally
            {
               if (v_is != null)
               {
                  try
                  {
                     v_is.close();
                  }
                  catch (final IOException v_e)
                  {
                     c_log.severe(v_e.toString());
                  }
                  v_is = null;
               }
            }
         }

         // écriture de la fin du fichier
         v_os.write("</requirement:Repository>".getBytes());

         c_log.info("Ecriture du fichier " + finalRequirementFileName + " OK");
         cleanTmp();
      }
      catch (final IOException v_e)
      {
         c_log.severe("Impossible de regrouper les fichiers .requirement : " + v_e.getMessage());
      }
      finally
      {
         if (v_os != null)
         {
            try
            {
               v_os.close();
            }
            catch (final IOException v_e)
            {
               c_log.severe(v_e.toString());
            }
         }
      }
   }

   /**
    *   nettoyage du dossier temporaire
    */
   private void cleanTmp() {
	if (v_tmpDir.exists())
      {
         for (final File v_f : v_tmpDir.listFiles())
         {
            v_f.delete();
         }
         v_tmpDir.delete();
      }
   }

   /**
    * Recherche rÃ©cursive des fichiers .requirement dans un dossier
    *
    * @param p_projectDir
    *           le dossier de recherche
    * @return la liste des fichiers .requirement dans ce dossier et ses sous-dossiers rÃ©cursivement
    */
   protected List<IFile> findRequirementsInProject (final IFolder p_projectDir)
   {
      final List<IFile> v_requirementsFile = new ArrayList<IFile>();
      // parcourt les dossiers et fichiers du dossier courant
      try {
		for (final IResource v_currentFile : p_projectDir.members())
		  {
		     // si c'est un dossier
		     if (v_currentFile.getType() == IResource.FOLDER)
		     {
		        // chercher les .requirement dans ce sous-dossier
		        v_requirementsFile.addAll(findRequirementsInProject((IFolder)v_currentFile));
		     }
		     // si c'est un fichier .requirement
		     else if (v_currentFile.getType() == IResource.FILE && v_currentFile.getName().endsWith(c_REQUIREMENT_EXT))
		     {
		        // l'ajouter dans la liste Ã  retourner
		        v_requirementsFile.add((IFile)v_currentFile);
		     }
		  }
	} catch (CoreException e) {
		c_log.warning("CoreException pour " + p_projectDir.getLocationURI().getPath());
		e.printStackTrace();
	}

      // retourner la liste des fichiers .requirement
      return v_requirementsFile;
   }

   /**
    * Copie un fichier dans un dossier
    *
    * @param p_file
    *           le fichier Ã  copier
    * @param p_toDir
    *           le dossier destination
    */
   protected void copyAndEditFile (final File p_file, final File p_toDir, final File p_toFile)
   {
      final File v_toFile = new File(p_toDir, p_toFile.getName());
      InputStream v_is = null;
      OutputStream v_os = null;
      try
      {
         // lecture du fichier source
         v_is = new FileInputStream(p_file);

         // utilisation d'un buffer de 1024 octets
         final byte[] v_buffer = new byte[1024];
         int v_read;
         final StringBuilder v_strBuilder = new StringBuilder();
         while (v_is.available() > 0)
         {
            v_read = v_is.read(v_buffer);
            v_strBuilder.append(new String(v_buffer, 0, v_read));
         }

         // modification du contenu du fichier avant réécriture
         String v_strContent = v_strBuilder.toString();
         v_strContent = updateFileContent(v_strContent);

         // écriture du fichier
         v_os = new FileOutputStream(v_toFile);
         v_os.write(v_strContent.getBytes());
      }
      catch (final IOException v_e)
      {
         c_log.severe("Problème lors de la copie de " + p_file.getAbsolutePath() + " vers " + p_toDir.getAbsolutePath());
      }
      finally
      {
         // tentative de fermeture des flux de lecture / Ã©criture
         if (v_is != null)
         {
            try
            {
               v_is.close();
            }
            catch (final IOException v_e)
            {
               c_log.severe(v_e.toString());
            }
         }
         if (v_os != null)
         {
            try
            {
               v_os.close();
            }
            catch (final IOException v_e)
            {
               c_log.severe(v_e.toString());
            }
         }
      }
   }

   /**
    * Traite le contenu du fichier, pour supprimer ou modifier certaines parties
    *
    * @param p_content
    *           le contenu original du fichier
    * @return ce qui devra Ãªtre exportÃ© dans le fichier final
    */
   protected String updateFileContent (final String p_content)
   {
      String v_strContent = p_content.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");

      // hypothÃ¨se ou le repository d'un .requirement devient une main categorie
      // strContent = strContent.replace("<requirement:Repository xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:requirement=\"http://www.obeonetwork.org/dsl/requirement/1.0\"", "<mainCategories");
      // strContent = strContent.replace("</requirement:Repository>", "</mainCategories>");
      // strContent = strContent.replace("<mainCategories", "<subCategories");
      // strContent = strContent.replace("</mainCategories>", "</subCategories>");

      // hypothÃ¨se ou une main categorie d'un .requirement reste une main categorie dans le .requirement global
      v_strContent = v_strContent.substring(v_strContent.indexOf('<', v_strContent.indexOf("<requirement:Repository") + 1));
      v_strContent = v_strContent.replace("</requirement:Repository>", "");

      // suppression des referencedObject
      v_strContent = v_strContent.replaceAll("<referencedObject href=\".*\"/>", "");

      return v_strContent;
   }

}
