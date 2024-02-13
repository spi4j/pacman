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
package fr.pacman.estimation.ui.popup;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.estimation.JavaEstimationGenerator;
import fr.pacman.estimation.services.IndicatorsEstimation;
import fr.pacman.estimation.services.complexite_pf.NotReliableException;
import fr.pacman.estimation.services.complexite_pf.UnsupportedModelException;
import fr.pacman.estimation.ui.Activator;

/**
 * Entity code generation.
 */
public class AcceleoEstimationAction extends ActionDelegate implements IActionDelegate
{

   /**
    * Selected model files.
    */
   protected List<Object> _selectedNodes;

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
   public void selectionChanged (final IAction p_action, final ISelection p_selection)
   {
      if (p_selection instanceof IStructuredSelection)
      {
         final IStructuredSelection v_sel = (IStructuredSelection) p_selection;
         _selectedNodes = v_sel.toList();
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
      // Si au moins un fichier de modélisation sélectionné
      if (_selectedNodes != null)
      {
         final IRunnableWithProgress v_operation = new IRunnableWithProgress()
         {
            @Override
            public void run (final IProgressMonitor p_monitor)
            {
               // vider le cache des propriétés
               PacmanPropertiesManager.clearProperties();
               URI v_modelURI = null;
               // Parcourir les noeuds sélectionnés
               for (final Object v_NodeCour : _selectedNodes)
               {
                  try
                  {
                     final JavaEstimationGenerator v_generator;
                     // Si le neoud est un IFile
                     if (v_NodeCour instanceof IFile)
                     {
                        IFile v_Node = (IFile) v_NodeCour;
                        // on charge le modèle
                        v_modelURI = URI.createPlatformResourceURI(v_Node.getFullPath().toString(), true);
                        v_generator = new JavaEstimationGenerator(v_modelURI);
                        final IndicatorsEstimation<IFile> v_indicators = v_generator.computeIndicatorsEstimation(v_Node);
                        // Si estimation réalisée
                        info(v_indicators.toString());
                     }
                     // Si le neoud est un EObject
                     else if (v_NodeCour instanceof EObject)
                     {
                        EObject v_Node = (EObject) v_NodeCour;
                        v_generator = new JavaEstimationGenerator(v_Node);
                        final IndicatorsEstimation<EObject> v_indicators = v_generator.computeIndicatorsEstimation(v_Node);
                        // Si estimation réalisée
                        info(v_indicators.toString());
                     }
                     // Cas non prévu
                     else
                     {
                        warning("Cas non prévu avec le noeud : " + v_NodeCour);
                        v_generator = null;
                     }

                  }
                  catch (NotReliableException v_e)
                  {
                     // Si estimation réalisée mais pas fiable
                     error(v_e);
                  }
                  catch (UnsupportedModelException v_e)
                  {
                     // Si estimation non gérée pour ce modèle
                     error(v_e);
                  }
                  catch (final IOException v_e)
                  {
                     final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
                     getLogger().log(v_status);
                  }

               }
            } // FIN méthode "run()"
         };
         try
         {
            PlatformUI.getWorkbench().getProgressService().run(true, true, v_operation);
         }
         catch (final InvocationTargetException v_e)
         {
            final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
            getLogger().log(v_status);
         }
         catch (final InterruptedException v_e)
         {
            final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), v_e.getMessage(), v_e);
            getLogger().log(v_status);
         }
      }
   }

   /**
    * Affichage d'un message (popup + error log)
    * 
    * @param p_message
    *           le message à afficher
    */
   private void info (final String p_message)
   {
      /// Afficher une Popup
      Display.getDefault().asyncExec(new Runnable()
      {
         @Override
         public void run ()
         {
            MessageDialog.openInformation(null, "Pacman Estimation", p_message);
         }
      });
      final IStatus v_status = new Status(IStatus.INFO, getPluginId(), p_message);
      getLogger().log(v_status);
   }

   /**
    * Affichage d'un message d'avertissement (error log)
    * 
    * @param p_message
    *           le message à afficher
    */
   private void warning (final String p_message)
   {
      final IStatus v_status = new Status(IStatus.INFO, getPluginId(), p_message);
      getLogger().log(v_status);
   }

   /**
    * Affichage d'une erreur (popup + error log).
    * 
    * @param p_e
    *           l'exception
    */
   private void error (final Exception p_e)
   {
      /// Afficher une Popup
      Display.getDefault().asyncExec(new Runnable()
      {
         @Override
         public void run ()
         {
            MessageDialog.openError(null, "Pacman Estimation", p_e.getMessage());
         }
      });

      final IStatus v_status = new Status(IStatus.ERROR, getPluginId(), p_e.getMessage(), p_e);
      getLogger().log(v_status);
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
