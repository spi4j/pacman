/*******************************************************************************
 * Copyright (c) 2008, 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.validation.ui.popup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.validation.services.ValidationService;
import fr.pacman.validation.ui.Activator;
import fr.pacman.validation.ui.common.GenerateValidation;
import fr.pacman.validation.view.views.ValidationView;

/**
 * Entity code generation.
 */
public class AcceleoValidationAction extends ActionDelegate implements
         IActionDelegate
{

   /**
    * Selected model files.
    */
   protected List<IFile> _files;

   /**
    * La vue (si souhaitée par le plugin).
    */
   protected IViewPart _view;

   /**
    * {@inheritDoc}
    * 
    * @see org.eclipse.ui.actions.ActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
    * @generated
    */
   @Override
   @SuppressWarnings("unchecked")
   public void selectionChanged (
      final IAction p_action,
      final ISelection p_selection)
   {
      if (p_selection instanceof IStructuredSelection)
      {
         _files = ((IStructuredSelection) p_selection).toList();
      }
   }

   /**
    * {@inheritDoc}
    * 
    * @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
    * @generated
    */
   @Override
   public void run (final IAction p_action)
   {
      if (_files != null)
      {
         // Gestion de la vue
         try
         {
            PlatformUI
                     .getWorkbench()
                     .getActiveWorkbenchWindow()
                     .getActivePage()
                     .showView(ValidationView.c_id);
         }
         catch (PartInitException v_e)
         {
            final IStatus v_status =
                     new Status(
                              IStatus.ERROR,
                              getPluginId(),
                              v_e.getMessage(),
                              v_e);
            getLogger().log(v_status);
         }

         _view =
                  PlatformUI
                           .getWorkbench()
                           .getActiveWorkbenchWindow()
                           .getActivePage()
                           .findView(ValidationView.c_id);

         final IRunnableWithProgress v_operation = new IRunnableWithProgress()
         {
            @Override
            public void run (final IProgressMonitor p_monitor)
            {
               // vider le cache des propriétés
               PacmanPropertiesManager.clearProperties();
               for (final IFile v_model : _files)
               {
                  // on charge le modèle
                  final URI v_modelURI =
                           URI.createPlatformResourceURI(v_model
                                    .getFullPath()
                                    .toString(), true);
                  final GenerateValidation v_generator = new GenerateValidation(v_modelURI);
                  // on utilise pas de SafranGenerator car on n'exécute pas d'Acceleo derrière
                  // final SafranGenerator v_generator = getSafranGenerator(v_modelURI,
                  // v_target.getLocation().toFile(), getArguments());
                  try
                  {
                     v_generator.doGenerate(p_monitor);
                     final IStatus v_status;
                     if (ValidationService.getProblems().isEmpty())
                     {
                        v_status =
                                 new Status(
                                          Status.OK,
                                          Activator.c_PLUGIN_ID,
                                          v_model.getName()
                                                   + " : Modèle valide");
                     }
                     else
                     {
                        v_status =
                                 new Status(
                                          Status.WARNING,
                                          Activator.c_PLUGIN_ID,
                                          v_model.getName()
                                                   + " : "
                                                   + ValidationService
                                                            .getProblems()
                                                            .size()
                                                   + " problème(s)");
                     }
                     getLogger().log(v_status);
                     updateView(v_model.getProject());
                  }
                  catch (final IOException v_e)
                  {
                     final IStatus v_status =
                              new Status(
                                       IStatus.ERROR,
                                       getPluginId(),
                                       v_e.getMessage(),
                                       v_e);
                     getLogger().log(v_status);
                  }
               }
            }
         };
         try
         {
            PlatformUI
                     .getWorkbench()
                     .getProgressService()
                     .run(true, true, v_operation);
         }
         catch (final InvocationTargetException v_e)
         {
            final IStatus v_status =
                     new Status(
                              IStatus.ERROR,
                              getPluginId(),
                              v_e.getMessage(),
                              v_e);
            getLogger().log(v_status);
         }
         catch (final InterruptedException v_e)
         {
            final IStatus v_status =
                     new Status(
                              IStatus.ERROR,
                              getPluginId(),
                              v_e.getMessage(),
                              v_e);
            getLogger().log(v_status);
         }
      }
   }

   /**
    * Computes the arguments of the generator.
    * 
    * @return the arguments
    * @generated
    */
   protected List<? extends Object> getArguments ()
   {
      return new ArrayList<String>();
   }

   /**
    * Mise à jour de la vue des problèmes de validation de modèle.
    * 
    * @param p_project
    *           le projet courant
    */
   private void updateView (final IProject p_project)
   {
      ((ValidationView) _view).setModelProject(p_project);
      ((ValidationView) _view).updateTable();
   }

   /**
    * @return l'id du plugin
    */
   protected String getPluginId ()
   {
      return Activator.c_PLUGIN_ID;
   }

   /**
    * @return le logger du plugin
    */
   protected ILog getLogger ()
   {
      return Activator.getDefault().getLog();
   }

}
