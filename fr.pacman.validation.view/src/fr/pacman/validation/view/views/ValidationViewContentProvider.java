/**
 * 
 */
package fr.pacman.validation.view.views;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewSite;

/**
 * @author Arnaud
 * 
 */
public class ValidationViewContentProvider implements IStructuredContentProvider
{

   @Override
   public void dispose ()
   {
      // RAS
   }

   @Override
   public void inputChanged (final Viewer p_viewer, final Object p_oldInput, final Object p_newInput)
   {
      // RAS
   }

   @Override
   public Object[] getElements (final Object p_inputElement)
   {
      if (p_inputElement instanceof IViewSite)
      {
         return new String[]
         {"Aucun model check n'a été lancé." };
      }
      else if (p_inputElement instanceof List)
      {
         return ((List<?>) p_inputElement).toArray();
      }
      return new String[0];
   }
}
