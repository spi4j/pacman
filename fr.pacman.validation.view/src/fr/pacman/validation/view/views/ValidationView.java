package fr.pacman.validation.view.views;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import fr.pacman.validation.services.ValidationService;
import fr.pacman.validation.services.rules.DslValidationRuleNokBean;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class ValidationView extends ViewPart
{

   /**
    * The ID of the view as specified by the extension.
    */
   public static final String c_id = "fr.pacman.validation.view.views.ModelCheckerView";

   private IProject _modelProject;

   private TableViewer _viewer;
   private Action _actionShowModel;
   private Action _actionShowRepresentation;
   private Action _doubleClickAction;

   /**
    * The content provider class is responsible for providing objects to the
    * view. It can wrap existing objects in adapters or simply return objects
    * as-is. These objects may be sensitive to the current input of the view, or
    * ignore it and always show the same content (like Task List, for example).
    */
   class NameSorter extends ViewerSorter
   {
   }

   /**
    * The constructor.
    */
   public ValidationView ()
   {
   }

   /**
    * This is a callback that will allow us to create the viewer and initialize
    * it.
    * 
    * @param p_parent
    *           parent
    */
   public void createPartControl (final Composite p_parent)
   {
      _viewer = new TableViewer(p_parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
      _viewer.setContentProvider(new ValidationViewContentProvider());
      _viewer.setLabelProvider(new ValidationViewLabelProvider());
      _viewer.setSorter(new NameSorter());
      _viewer.setInput(getViewSite());

      // Create the help context id for the viewer's control
      PlatformUI.getWorkbench().getHelpSystem().setHelp(_viewer.getControl(), "fr.pacman.validation.view.viewer");
      makeActions();
      hookContextMenu();
      hookDoubleClickAction();
   }

   /**
    * Gestion du menu contextuel.
    */
   private void hookContextMenu ()
   {
      final MenuManager v_menuMgr = new MenuManager("#PopupMenu");
      v_menuMgr.setRemoveAllWhenShown(true);
      v_menuMgr.addMenuListener(new IMenuListener()
      {
         public void menuAboutToShow (final IMenuManager p_manager)
         {
            ValidationView.this.fillContextMenu(p_manager);
         }
      });
      final Menu v_menu = v_menuMgr.createContextMenu(_viewer.getControl());
      _viewer.getControl().setMenu(v_menu);
      getSite().registerContextMenu(v_menuMgr, _viewer);
   }

   /**
    * Remplit le menu contextuel.
    * 
    * @param p_manager
    *           le gestionnaire de menu
    */
   private void fillContextMenu (final IMenuManager p_manager)
   {
      p_manager.add(_actionShowModel);
      p_manager.add(_actionShowRepresentation);
      // Other plug-ins can contribute there actions here
      p_manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
   }

   //
   // private void fillLocalToolBar(final IToolBarManager manager) {
   // manager.add(actionShowModel);
   // manager.add(actionShowRepresentation);
   // }

   /**
    * Créé les actions.
    */
   private void makeActions ()
   {
      _actionShowModel = new Action()
      {
         public void run ()
         {
            // showMessage("Action 1 executed");
            showModel();
         }
      };
      _actionShowModel.setText("Voir modèle");
      _actionShowModel.setToolTipText("Voir ce problème dans la vue arborescente du modèle");
      _actionShowModel.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

      _actionShowRepresentation = new Action()
      {
         public void run ()
         {
            // showMessage("Action 2 executed");
            showRepresentation();
         }
      };
      _actionShowRepresentation.setText("Voir représentation");
      _actionShowRepresentation.setToolTipText("Voir ce problème dans la vue graphique du modèle");
      _actionShowRepresentation.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
      _doubleClickAction = new Action()
      {
         public void run ()
         {
            // _LGR_begin
            showModel();
            // _LGR_end
         }
      };
   }

   /**
    * Affiche les objets sélectionnés dans la vue arborescente.
    */
   private void showModel ()
   {
      for (final DslValidationRuleNokBean v_info : getSelectedElements())
      {
         final EObject v_obj = v_info.get_entryPointDsl();

         try
         {
            org.eclipse.emf.edit.ui.util.EditUIUtil.openEditor(v_obj);
            final EditorPart v_activeEditor = (EditorPart) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            final IViewerProvider v_ongletEditor = (IViewerProvider) v_activeEditor;
            if ((TreeViewer) v_ongletEditor.getViewer() != null)
            {
               searchObjectInTree(v_obj, v_ongletEditor);
            }
         } catch (final Exception v_e)
         {
            // Impossible de trouver l'éditeur par défaut pour cet objet
            v_e.printStackTrace();
         }
      }
   }

   /**
    * Recherche dans un arbre un objet pour le sélectionner
    * 
    * @param p_obj
    *           l'objet cherché
    * @param p_ongletEditor
    *           l'onglet ouvert
    */
   private void searchObjectInTree (final EObject p_obj, final IViewerProvider p_ongletEditor)
   {
      // ouvrir tous les noeuds de l'arbre
      ((TreeViewer) p_ongletEditor.getViewer()).expandAll();
      // ((TreeViewer) ongletEditor.getViewer()).collapseAll();

      TreeItem v_itemTrouve = null;
      for (final TreeItem v_treeItem : ((TreeViewer) p_ongletEditor.getViewer()).getTree().getItems())
      {
         v_itemTrouve = searchObjectInTree(v_treeItem, p_obj, p_ongletEditor);
         if (v_itemTrouve != null)
         {
            break;
         }
      }

      if (v_itemTrouve != null)
      {
         TreeItem v_parent = v_itemTrouve.getParentItem();
         while (v_parent != null)
         {
            v_parent.setExpanded(true);
            v_parent = v_parent.getParentItem();
         }
         // ouvre l'arbre au niveau de l'objet trouvé
         ((TreeViewer) p_ongletEditor.getViewer()).expandToLevel(v_itemTrouve.getData(), 1);
         // La méthode suivante n'est pas définie à un niveau suffisament haut, donc on l'appelle par reflect
         // p_ongletEditor.setSelectionToViewer(v_collection);
         final Collection<Object> v_collection = Collections.singleton(v_itemTrouve.getData());
         Method v_setSelectionToViewerMethod;
         try
         {
            v_setSelectionToViewerMethod = p_ongletEditor.getClass().getMethod("setSelectionToViewer", Collection.class);
         } catch (Exception v_e)
         {
            throw new RuntimeException("Impossible de trouver la méthode setSelectionToViewer sur l'éditeur", v_e);
         }
         try
         {
            v_setSelectionToViewerMethod.invoke(p_ongletEditor, v_collection);
         } catch (Exception v_e)
         {
            throw new RuntimeException("Erreur lors de l'exécution de la méthode setSelectionToViewer sur l'éditeur", v_e);
         }
      }

   }

   /**
    * Recherche dans un arbre un objet pour le sélectionner
    * 
    * @param p_item
    *           l'item courant de l'arbre
    * @param p_obj
    *           l'objet cherché
    * @param p_ongletEditor
    *           l'onglet ouvert
    * @return l'objet trouvé dans l'arbre
    */
   private TreeItem searchObjectInTree (final TreeItem p_item, final EObject p_obj, final IViewerProvider p_ongletEditor)
   {
      TreeItem v_itemTrouve = null;
      if (p_item.getData() instanceof EObject)
      {
         // TODO plantage si le modèle vient d'être modifié
         final String v_idObjetPotentiel = ((EObject) (p_item.getData())).eResource().getURIFragment((EObject) (p_item.getData()));
         final String v_idObjetCherche = p_obj.eResource().getURIFragment(p_obj);
         if (v_idObjetCherche != null && v_idObjetCherche.equals(v_idObjetPotentiel))
         {
            v_itemTrouve = p_item;
         }
      }
      if (v_itemTrouve == null)
      {
         for (final TreeItem v_i : p_item.getItems())
         {
            v_itemTrouve = searchObjectInTree(v_i, p_obj, p_ongletEditor);
            if (v_itemTrouve != null)
            {
               break;
            }
         }
      }
      return v_itemTrouve;
   }

   /**
    * Affiche les éléments sélectionnés dans leur diagramme.
    */
   private void showRepresentation ()
   {
      for (final DslValidationRuleNokBean v_info : getSelectedElements())
      {
         final EObject v_obj = v_info.get_entryPointDsl();
         boolean v_isRepresented = false;

         // recherche dans les vues (diagrammes) :
         // FIXME Chercher dans tous les airds disponibles dans le projet
         // TODO Quid de CDO ?
         final URI v_sessionResourceURI = URI.createPlatformResourceURI("/" + _modelProject.getName() + "/representations.aird", true);
         final Session v_createdSession = SessionManager.INSTANCE.getSession(v_sessionResourceURI,new NullProgressMonitor());
         v_createdSession.open(new NullProgressMonitor());
         EObject v_objInSession = null;
         for (final Resource v_r : v_createdSession.getSemanticResources())
         {
            v_objInSession = v_r.getEObject(v_obj.eResource().getURIFragment(v_obj));
            if (v_objInSession != null)
            {
               break;
            }
         }
         if (v_objInSession != null)
         {
            final Collection<EObject> v_graphicalElts = new EObjectQuery(v_objInSession).getInverseReferences(ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET);
            Option<DDiagram> v_diag = null;
            IEditorPart v_dialectEditor = null;
            final List<DRepresentationElement> v_l = new ArrayList<DRepresentationElement>();
            v_isRepresented = false;
            for (final EObject v_grEl : v_graphicalElts)
            {
               if (new EObjectQuery(v_grEl).getParentDiagram() != v_diag)
               {
                  v_diag = new EObjectQuery(v_grEl).getParentDiagram();
                  v_dialectEditor = DialectUIManager.INSTANCE.openEditor(v_createdSession, v_diag.get(), new NullProgressMonitor());
                  v_l.clear();
               }
               if (v_grEl instanceof DRepresentationElement)
               {
                  v_l.add((DRepresentationElement) v_grEl);
                  DialectUIManager.INSTANCE.setSelection((DialectEditor) v_dialectEditor, v_l);
                  v_isRepresented = true;
               }
            }
            if (!v_isRepresented)
            {
               showMessage("Aucun diagramme ne contient une représentation de cet élément (Fichier AIRD inexistant ?).");
            }
         }
         else
         {
            showMessage("Aucun diagramme ne contient une représentation de cet élément (Fichier AIRD inexistant ?).");
            // createdSession.close(); // ferme les diagrammes
         }
      }
   }

   /**
    * @return les éléments sélectionnés
    */
   @SuppressWarnings("unchecked")
   private List<DslValidationRuleNokBean> getSelectedElements ()
   {
      List<DslValidationRuleNokBean> v_elements;
      final ISelection v_selection = _viewer.getSelection();
      if (v_selection == null || v_selection.isEmpty())
      {
         v_elements = new ArrayList<DslValidationRuleNokBean>();
      }
      else
      {
         v_elements = ((IStructuredSelection) v_selection).toList();
      }

      return v_elements;
   }

   // _LGR_

   /**
    * Action sur le double clic.
    */
   private void hookDoubleClickAction ()
   {
      _viewer.addDoubleClickListener(new IDoubleClickListener()
      {
         public void doubleClick (final DoubleClickEvent p_event)
         {
            _doubleClickAction.run();
         }
      });
   }

   /**
    * Affiche un message d'information.
    * 
    * @param p_message
    *           le message
    */
   private void showMessage (final String p_message)
   {
      MessageDialog.openInformation(_viewer.getControl().getShell(), "Validation View", p_message);
   }

   /**
    * Passing the focus request to the viewer's control.
    */
   public void setFocus ()
   {
      _viewer.getControl().setFocus();
   }

   /**
    * Met à jour la table dans la vue.
    */
   public void updateTable ()
   {
      Display.getDefault().asyncExec(new Runnable()
      {
         public void run ()
         {
            _viewer.setInput(ValidationService.getProblems());
            _viewer.refresh();
            ValidationService.getProblems().clear();
         }
      });
   }

   /**
    * Affecte le projet du modèle
    * 
    * @param p_project
    *           le projet du modèle
    */
   public void setModelProject (final IProject p_project)
   {
      _modelProject = p_project;
   }
}