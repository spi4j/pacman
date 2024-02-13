package fr.pacman.soalight.services;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.soa.Service;


/**
 * Classe utilitaire pour la génération de la couche de matching.
 * 
 * @author MINARM
 */
public final class ServiceUtils
{

   /**
    * Constructeur privé.
    */
//   private ServiceUtils ()
//   {
//      // RAS
//   }

   /**
    * Retourne la racine du modèle.
    * 
    * @param p_dto
    *           un dto
    * @return la racine du modèle
    */
   public static EObject getRoot (final EObject p_dto)
   {
      return EcoreUtil.getRootContainer(p_dto);
   }

   /**
    * Retourne vrai s'il existe  un service de même nom  lié à une entité
    * 
    * @param p_service
    * @param p_name
    * @return La liste des entités du namespace et celles référencées par les entités de ce block.
    */
   public  Boolean  isEntityService (final Service p_service, final String p_name)
   {

       URI v_uri = getRoot(p_service).eResource().getURI();
       final Resource v_rse = getEntities(v_uri);
       TreeIterator<EObject> v_treeIterator = v_rse.getAllContents();
       while (v_treeIterator.hasNext()) {
           final EObject v_eObject = v_treeIterator.next();
           if (v_eObject instanceof Entity) {
              if (p_name.equalsIgnoreCase(( (Entity) v_eObject).getName()))
                 return true;
           }
       }

       return false;
   }


 /**
    * Recherche des fichiers .entity du modèle.
    * 
    * @return les fichiers entity dans le même dossier que le modéle utilisé pourla génération
    */
   private Resource getEntities (URI p_uri)
   {

      // We get the folder containing the model
      Resource v_entitiesModel = new ResourceImpl();
      final IWorkspace v_wksp = ResourcesPlugin.getWorkspace();
      final IWorkspaceRoot v_root = v_wksp.getRoot();
      final IFile v_modelFile = v_root.getFile(new Path(p_uri.toPlatformString(true)));
      String v_fullPath = v_modelFile.getFullPath().toString();
      String[] v_fullPathSplit = v_fullPath.split("/");
      IContainer v_container = v_modelFile.getParent();
      while (!v_container.getName().equals(v_fullPathSplit[1])) {
          v_container = v_container.getParent();
      }
      // Now we look at the children to list the ".requirement" files
      String v_req = null;
      try
      {
          IResource[] v_siblings = v_container.members();
          for (final IResource v_sibling : v_siblings)
          {
              v_req = recupEntityModel(v_sibling);
              if (v_req != null)
                 break;
          }
      }catch (final CoreException v_e)
      {
          // do nothing
          v_e.printStackTrace();
      }
      if (v_req != null){
   	     final ResourceSet v_resourceSet = new ResourceSetImpl();
         final URI v_entitesURI = URI.createPlatformResourceURI(v_req, true);
    	 v_entitiesModel = v_resourceSet.getResource(v_entitesURI, true);
      }

      return v_entitiesModel;
   }

	/**
	 * 
	 * @param p_resource
	 * @return
	 */
    private String  recupEntityModel (final IResource p_resource)
    {
        if (p_resource.getType() == IResource.FILE)
        {
            if ("entity".equals(p_resource.getFileExtension())){
               return p_resource.getFullPath().toString();
            }
            if ("ois".equals(p_resource.getFileExtension())){
            	return p_resource.getFullPath().toString();
            }
        }
		else if (p_resource.getType() == IResource.FOLDER){
            try
            {
            	IResource[] v_siblings = ((IContainer) p_resource).members();
                for (final IResource v_sibling : v_siblings)
                {
                    String v_entityModel = recupEntityModel(v_sibling);
                    if (v_entityModel != null)
                    {
                        return v_entityModel;
                    }
                }
            }
            catch (final CoreException v_e)
            {
                // do nothing
                return null;
            }
        }

        return null;
    }
}

