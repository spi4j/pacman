/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
import fr.application.client.ui.annuaire.AnnuaireFlowManager;
import fr.application.client.ui.annuaire.ListePersonnesPresenter;
import fr.spi4j.ui.graal.UserAction;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;
import fr.spi4j.ui.mvp.View_Itf;

// End of user code

/**
 * Presenter.
 * @author safr@n
 */

// Start of user code Class definition
public class LoginPresenter extends Presenter_Abs<LoginView_Itf, Object>
// End of user code
{

   
   // Start of user code Constructeur(s)
   /**
    * Constructeur.
    */
   public LoginPresenter ()
   {
      super(null);
   }

   /**
    * Constructeur avec présenteur parent.
    * 
    * @param p_parent
    *           le présenteur parent
    */
   public LoginPresenter (final Presenter_Abs<? extends View_Itf, ?> p_parent)
   {
      super(p_parent);
   }
   // End of user code

   @Override
   public void initView ()
   {
      
      // Start of user code Initialisation de la vue

      // End of user code
   }

   @Override
   protected String doGenerateTitle ()
   {
      
      // Start of user code Titre de l'ecran
      return "Login";
      // End of user code
   }

   /**
    * Gestion de l'événement onClicAnnuler.
    */
   // Start of user code Annotation onClicAnnuler

   // End of user code
   public void onClicAnnuler()
   {
      // Start of user code Pre onClicAnnuler
      close();

      // End of user code

	  
      // Start of user code onClicAnnuler : Affichage de Home

      // End of user code

      // Start of user code Post onClicAnnuler

      // End of user code
   }

   /**
    * Gestion de l'événement onClicConnecter.
    */
   // Start of user code Annotation onClicConnecter

   // End of user code
   public void onClicConnecter()
   {
      // Start of user code Pre onClicConnecter
      close();

      // End of user code

      
	  // Start of user code onClicConnecter : Action Connexion
      // TODO Connexion
      // End of user code

	  
      // Start of user code onClicConnecter : Demarrage du Flow annuaire
      final AnnuaireFlowManager v_AnnuaireFlowManager = new AnnuaireFlowManager(this);
      v_AnnuaireFlowManager.start();
      // End of user code

      // Start of user code Post onClicConnecter

      // End of user code
   }

   // LoginPresenter
   // Start of user code LoginPresenter

   // End of user code

}
