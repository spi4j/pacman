/**
 * 
 */
package fr.pacman.validation.view.views;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * @author Arnaud
 * 
 */
public class ValidationViewLabelProvider implements ITableLabelProvider
{

   @Override
   public void addListener (final ILabelProviderListener p_listener)
   {
      // RAS
   }

   @Override
   public void dispose ()
   {
      // RAS
   }

   @Override
   public boolean isLabelProperty (final Object p_element, final String p_property)
   {
      return false;
   }

   @Override
   public void removeListener (final ILabelProviderListener p_listener)
   {
      // RAS
   }

   @Override
   public Image getColumnImage (final Object p_element, final int p_columnIndex)
   {
      if (p_element instanceof DslValidationRuleNokBean)
      {
         final DslValidationRuleNokBean v_info = (DslValidationRuleNokBean) p_element;
         switch (v_info.getLevel())
         {
         case Error:
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
         case Warning:
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
         case Information:
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
         default:
            return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
         }
      }
      else
      {
         return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_CLEAR_DISABLED);
      }
   }

   @Override
   public String getColumnText (final Object p_element, final int p_columnIndex)
   {
      if (p_element instanceof DslValidationRuleNokBean)
      {
         final DslValidationRuleNokBean v_info = (DslValidationRuleNokBean) p_element;
         String v_l = " " + v_info.getLevel().toString() + " sur un élément de type : ";
         v_l = v_l + v_info.get_entryPointDsl().eClass().getName() + ".  ";
         v_l = v_l + v_info.get_messageNok() + ".  (Nom de l'élément: ";
         final EStructuralFeature v_nameFeature = v_info.get_entryPointDsl().eClass().getEStructuralFeature("name");
         if (v_nameFeature != null)
         { // pourquoi ne descendent-ils pas de ENamedElement ?
            v_l = v_l + v_info.get_entryPointDsl().eGet(v_nameFeature) + " )";
         }
         else
         {
            v_l = v_l + "pas de nom disponible sur ce type d'élément )";
         }

         return v_l;
      }
      else
      {
         return "Aucun model check n'a été lancé.";
      }
   }
}
