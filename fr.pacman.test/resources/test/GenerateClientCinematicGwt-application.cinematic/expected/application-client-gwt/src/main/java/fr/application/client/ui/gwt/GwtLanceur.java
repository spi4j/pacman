/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt;
// Start of user code for imports

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.spi4j.ui.gwt.client.mvp.GWTViewManager;
import fr.spi4j.ui.mvp.MVPUtils;

// End of user code

/**
 * Point d'entrée de l'application GWT.
 * @author safr@n
 */
public class GwtLanceur implements EntryPoint
{

   /**
    * Chargement du module.
    */
   @Override
   public void onModuleLoad ()
   {
      GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler()
      {
         @Override
         public void onUncaughtException (final Throwable p_caught)
         {
            
            // Start of user code Exceptions non interceptees
            GWT.log(p_caught.toString(), p_caught);
            // Create the popup dialog box
            final DialogBox v_dialogBox = new DialogBox();
            v_dialogBox.setText("Erreur inconnue");
            v_dialogBox.setAnimationEnabled(true);
            final Button v_closeButton = new Button("Fermer");
            final VerticalPanel v_dialogVPanel = new VerticalPanel();
            v_dialogVPanel.add(new HTML(p_caught.toString()));
            v_dialogVPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
            v_dialogVPanel.add(v_closeButton);
            v_dialogBox.setWidget(v_dialogVPanel);
            v_dialogBox.center();

            // Add a handler to close the DialogBox
            v_closeButton.addClickHandler(new ClickHandler()
            {
               @Override
               public void onClick (final ClickEvent p_event)
               {
                  v_dialogBox.hide();
               }
            });
            // End of user code
         }
      });

      Window.addWindowClosingHandler(new ClosingHandler()
      {
         @Override
         public void onWindowClosing (final ClosingEvent p_event)
         {
            
            // Start of user code Fermeture de l'application
            p_event.setMessage("Vous allez quitter l'application blanche. Continuer ?");
            // End of user code
         }
      });

      
      // Start of user code Pre initialisation

      // End of user code

      // Instanciation d'un Registre de vues dédié à GWT
      MVPUtils.setViewManager(new GWTViewManager());
      // Association des vues de l'application avec les présenteurs
      MVPUtils.getInstance().getViewManager().setViewsAssociation(new GwtViews());

      // initialisation de l'application
      initApplication();

      
      // Start of user code Post initialisation
      // masquer le div de chargement de la page.
      final RootPanel v_loadingPanel = RootPanel.get("loading");
      v_loadingPanel.setVisible(false);
      // End of user code
   }

   /**
    * Initialisation de l'application GWT.
    */
   public static void initApplication ()
   {
      
      // Start of user code Pre Initialisation de l'application

      // End of user code


      
      // Start of user code Post Initialisation de l'application

      // End of user code
   }

   
   // Start of user code GwtLanceur

   // End of user code
}
