/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui;
// Start of user code for imports

import fr.application.client.ui.MainPresenter;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.SpiFlowManager_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;
import java.util.Date;

// End of user code

/**
 * Flow main.
 * @author safr@n
 */
public class MainFlowManager extends SpiFlowManager_Abs
{

   // Attributs MainFlowManager
   // Start of user code Attributs MainFlowManager

   // End of user code

   /**
    * Constructeur du flow.
    * @param p_previousPresenter
    *           le présenteur en amont de ce flow
    */
   public MainFlowManager (final Presenter_Abs<?, ?> p_previousPresenter)
   {
      super(p_previousPresenter);
   }

   @Override
   public void onStart ()
   {
      // Pre onStart
      // Start of user code Pre onStart

      // End of user code

      // Start of user code onStart : Affichage de MainView
      // affichage de l'écran Main
      MainPresenter v_Main;
      try {
         // on tente d'instancier un nouvel écran
         v_Main = new MainPresenter(v_rootPresenter);
         v_rootPresenter.getView().addView(v_Main.getView());
      } catch (ViewAlreadyRegisteredException v_e) {
         // si l'écran existe déjà on le récupère dans le gestionnaire de vues
         v_Main = getViewManager().getPresenter(MainPresenter.class);
         v_rootPresenter.getView().restoreView(v_Main.getView());
      }

      // End of user code


      // Post onStart
      // Start of user code Post onStart

      // End of user code

   }

   // Méthodes MainFlowManager
   // Start of user code Méthodes MainFlowManager

   // End of user code

}
