package fr.pacman.commons.services;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.obeonetwork.dsl.entity.Entity;

public class ResourceUtils
{

   /**
    * Recuperation du nom de la librairie (en fait le nom du fichier ".entity" ou est stockee l'entite. Comme le nom du fichier est celui de la librairie...)
    * <p>
    * On recherche l'entite dans tous les fichiers de librairie externe. Si elle est trouvee, on retourne le nom du fichier qui la contient. 
    * ATTENTION, pour cette premiere version on ne detecte pas les doublons, il ne faut donc pas qu'une entite ait le meme nom dans deux projets differents !
    * 
    * @param p_entity
    *           : L'entite pour laquelle essayer de recuperer le nom du projet (librairie).
    * @param p_projectName
    *           : Le nom du projet principal (celui en cours).
    * @return
    */
   public static String getLibraryName (final Entity p_entity, final String p_projectName)
   {
      for (Resource v_resource : p_entity.eResource().getResourceSet().getResources())
      {
         String v_lastFragment = v_resource.getURI().lastSegment();
         if (null != v_lastFragment && !v_lastFragment.isEmpty())
            v_lastFragment = v_lastFragment.replace(".entity", "");

         // Par gagner du temps on ne scanne que les librairies.
         if (!v_lastFragment.equals(p_projectName))
         {
            TreeIterator<EObject> v_eObjects = v_resource.getAllContents();
            while (v_eObjects.hasNext())
            {
               EObject v_eObject = v_eObjects.next();
               if (v_eObject instanceof Entity && p_entity.getName().equals(((Entity) v_eObject).getName()))
                  return v_lastFragment;
            }
         }
      }
      return p_projectName;
   }

   /**
    * Verifie si une entite appartient a une librairie externe. Cela est le cas a partir du moment ou 
    * le nom du fichier qui contient l'entite est different de celui du projet en cours.
    * 
    * @param p_entity
    *           : L'entite pour laquelle essayer de recuperer le nom du projet (librairie).
    * @param p_projectName
    *           : Le nom du projet principal (celui en cours).
    * @return
    */
   public static boolean isLibraryEntity (final Entity p_entity, final String p_projectName)
   {
      return !getLibraryName(p_entity, p_projectName).equals(p_projectName);
   }

public static boolean isLibraryEntityJavaService(final Entity p_entity,final String p_projectName){return isLibraryEntity(p_entity,p_projectName);}
}
