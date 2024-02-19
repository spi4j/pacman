package fr.pacman.soalight.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.dsl.requirement.Category;
import org.obeonetwork.dsl.requirement.Repository;
import org.obeonetwork.dsl.requirement.Requirement;
import org.obeonetwork.dsl.requirement.RequirementPackage;
import org.obeonetwork.dsl.soa.Interface;
import org.obeonetwork.dsl.soa.Operation;
import org.obeonetwork.dsl.soa.Service;

/**
 * Classe utilitaire pour rechercher des références vers les Requirements.
 * 
 * @author MINARM
 */
public class RequirementUtils
{
   /**
    * Récupère les requirements liés à un objet
    * 
    * @param p_object
    *           l'objet source
    * @return la liste des requirements liés à l'objet
    */
   public List<Requirement> getRequirements (final EObject p_object)
   {
      // définition du CrossReferencer et récupération des utilisations de l'objet
      final Collection<Setting> v_settings = new EcoreUtil.UsageCrossReferencer(p_object.eResource().getResourceSet())
      {
         private static final long serialVersionUID = 1L;

         @Override
         protected boolean crossReference (final EObject p_eObject, final EReference p_eReference, final EObject p_referencedObj)
         {
            return p_eReference == RequirementPackage.Literals.REQUIREMENT__REFERENCED_OBJECT && super.crossReference(p_eObject, p_eReference, p_referencedObj);
         }

         @Override
         protected boolean containment (final EObject p_eObject)
         {
            return (p_eObject instanceof Repository) || (p_eObject instanceof Category) || (p_eObject instanceof Requirement);
         }

         @Override
         public Collection<Setting> findUsage (final EObject p_eObject)
         {
            return super.findUsage(p_eObject);
         }
      }
      .findUsage(p_object);
      // Cast des objets récupérés en Requirement
      final List<Requirement> v_requirements = new ArrayList<Requirement>();
      for (final Setting v_setting : v_settings)
      {
         v_requirements.add((Requirement) v_setting.getEObject());
      }
      orderRequirements(v_requirements);
      return v_requirements;
   }

public List<Requirement> getRequirementsJavaService(final EObject p_object){return getRequirements(p_object);}

   /**
    * Retourne la liste des exigences (uniques) liées à un service.
    * 
    * @param p_service
    *           le service
    * @return la liste des exigences (uniques) liées à un service.
    */
   public List<Requirement> getAllRequirementsForService (final Service p_service)
   {
      if (p_service == null)
      {
         return Collections.emptyList();
      }
      final Interface v_interface = p_service.getOwnedInterface();
      if (v_interface == null)
      {
         return Collections.emptyList();
      }
      final Set<Requirement> v_allRequirements = new HashSet<Requirement>();
      final EList<Operation> v_operations = v_interface.getOwnedOperations();
      for (Operation v_op : v_operations)
      {
         v_allRequirements.addAll(getRequirements(v_op));
      }
      final List<Requirement> v_requirementsList = new ArrayList<Requirement>(v_allRequirements);
      orderRequirements(v_requirementsList);
      return v_requirementsList;
   }

public List<Requirement> getAllRequirementsForServiceJavaService(final Service p_service){return getAllRequirementsForService(p_service);}

   /**
    * Tri des exigences par id.
    * 
    * @param p_requirementsList
    *           liste des exigences
    */
   private void orderRequirements (final List<Requirement> p_requirementsList)
   {
      Collections.sort(p_requirementsList, new Comparator<Requirement>()
      {
         @Override
         public int compare (final Requirement p_o1, final Requirement p_o2)
         {
            if (p_o1 == null && p_o2 == null)
            {
               return 0;
            }
            if (p_o1 == null || p_o1.getId() == null)
            {
               return -1;
            }
            if (p_o2 == null || p_o2.getId() == null)
            {
               return 1;
            }
            return p_o1.getId().compareToIgnoreCase(p_o2.getId());
         }
      });
   }

}
