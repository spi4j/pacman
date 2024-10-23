/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.annuaire;
// Start of user code for imports

import fr.application.client.ui.annuaire.ListePersonnesPresenter;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.SpiFlowManager_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;

// End of user code

/**
 * Flow annuaire.
 * @author safr@n
 */
public class AnnuaireFlowManager extends SpiFlowManager_Abs
{

   
   // Start of user code Attributs AnnuaireFlowManager

   // End of user code

   /**
    * Constructeur du flow.
    * @param p_previousPresenter
    *           le présenteur en amont de ce flow
    */
   public AnnuaireFlowManager (final Presenter_Abs<?, ?> p_previousPresenter)
   {
      super(p_previousPresenter);
   }

   @Override
   public void onStart ()
   {
      
      // Start of user code Pre onStart

      // End of user code

      
	  // Start of user code onStart : Action ListerPersonnes
      // TODO ListerPersonnes
      // End of user code

	  
      // Start of user code onStart : Affichage de ListePersonnes
      // affichage de l'écran ListePersonnes
      ListePersonnesPresenter v_ListePersonnes;
      try {
         // on tente d'instancier un nouvel écran
         v_ListePersonnes = new ListePersonnesPresenter(v_rootPresenter);
         v_rootPresenter.getView().addView(v_ListePersonnes.getView());
      } catch (ViewAlreadyRegisteredException v_e) {
         // si l'écran existe déjà on le récupère dans le gestionnaire de vues
         v_ListePersonnes = getViewManager().getPresenter(ListePersonnesPresenter.class);
         v_rootPresenter.getView().restoreView(v_ListePersonnes.getView());
      }

      // End of user code


      
      // Start of user code Post onStart

      // End of user code

   }

   
   // Start of user code Méthodes AnnuaireFlowManager

   // End of user code

}
