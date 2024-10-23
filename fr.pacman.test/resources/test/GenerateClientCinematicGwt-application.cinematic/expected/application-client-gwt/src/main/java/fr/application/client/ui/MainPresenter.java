/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
import fr.application.client.ui.LoginPresenter;
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
public class MainPresenter extends Presenter_Abs<MainView_Itf, Object>
// End of user code
{

   
   // Start of user code Constructeur(s)
   /**
    * Constructeur.
    */
   public MainPresenter ()
   {
      super(null);
   }

   /**
    * Constructeur avec présenteur parent.
    * 
    * @param p_parent
    *           le présenteur parent
    */
   public MainPresenter (final Presenter_Abs<? extends View_Itf, ?> p_parent)
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
      return "Main";
      // End of user code
   }

   /**
    * Gestion de l'événement connexion.
    */
   // Start of user code Annotation connexion

   // End of user code
   public void connexion()
   {
      // Start of user code Pre connexion
      close();

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

	  
      // Start of user code connexion : Affichage de Login
      // affichage de l'écran Login
      LoginPresenter v_Login;
      try {
         // on tente d'instancier un nouvel écran
         v_Login = new LoginPresenter(v_rootPresenter);
         v_rootPresenter.getView().addView(v_Login.getView());
      } catch (ViewAlreadyRegisteredException v_e) {
         // si l'écran existe déjà on le récupère dans le gestionnaire de vues
         v_Login = getViewManager().getPresenter(LoginPresenter.class);
         v_rootPresenter.getView().restoreView(v_Login.getView());
      }

      // End of user code

      // Start of user code Post connexion

      // End of user code
   }

   /**
    * Gestion de l'événement déconnexion.
    */
   // Start of user code Annotation déconnexion

   // End of user code
   public void deconnexion()
   {
      // Start of user code Pre déconnexion
      close();

      // End of user code

	  
      // Start of user code déconnexion : Affichage de Home

      // End of user code

      // Start of user code Post déconnexion

      // End of user code
   }

   // MainPresenter
   // Start of user code MainPresenter

   // End of user code

}
