/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
import fr.application.client.ui.annuaire.AnnuaireFlowManager;
import fr.application.client.ui.annuaire.ListePersonnesPresenter;
import fr.spi4j.ui.graal.UserAction;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;
import fr.spi4j.ui.mvp.View_Itf;
import java.util.Date;

// End of user code

public class LoginViewGwt extends ERREUR [ Widget sans nom] implements LoginView_Itf
{

   // Declaration txtLogin_ERREUR [ Widget sans nom]
   // Start of user code Declaration txtLogin_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _txtLogin;

   // End of user code

   // Declaration txtPassword_ERREUR [ Widget sans nom]
   // Start of user code Declaration txtPassword_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _txtPassword;

   // End of user code

   // Declaration btConnecter_ERREUR [ Widget sans nom]
   // Start of user code Declaration btConnecter_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btConnecter;

   // End of user code

   // Declaration btAnnuler_ERREUR [ Widget sans nom]
   // Start of user code Declaration btAnnuler_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btAnnuler;

   // End of user code

   // Attributs
   // Start of user code Attributs
   private final VerticalPanel _content;

   // End of user code
   /**
    * Constructeur de la vue.
    * @param p_presenter
    *           le présenteur de la vue
    */
   public LoginViewGwt (final LoginPresenter p_presenter)
   {
      super(p_presenter);

      // avant initialisation
      // Start of user code avant initialisation
      _content = new VerticalPanel();
      _content.add(new HTML("<h1>Login</h1>"));

      // End of user code

      // initialisation des composants
      // Parametrage txtLogin_ERREUR [ Widget sans nom]
      // Start of user code Parametrage txtLogin_ERREUR [ Widget sans nom]

      _txtLogin = new ERREUR [ Widget sans nom]();
      _content.add(_txtLogin);

      // End of user code

      // Parametrage txtPassword_ERREUR [ Widget sans nom]
      // Start of user code Parametrage txtPassword_ERREUR [ Widget sans nom]

      _txtPassword = new ERREUR [ Widget sans nom]();
      _content.add(_txtPassword);

      // End of user code

      // Parametrage btConnecter_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btConnecter_ERREUR [ Widget sans nom]

      _btConnecter = new ERREUR [ Widget sans nom]();
      _content.add(_btConnecter);

      // End of user code

      // Start of user code Listener btConnecter_onClicConnecter
      _btConnecter.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().onClicConnecter();
         }
      });

      // Parametrage btAnnuler_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btAnnuler_ERREUR [ Widget sans nom]

      _btAnnuler = new ERREUR [ Widget sans nom]();
      _content.add(_btAnnuler);

      // End of user code

      // Start of user code Listener btAnnuler_onClicAnnuler
      _btAnnuler.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().onClicAnnuler();
         }
      });

      // après initialisation
      // Start of user code après initialisation
      add(_content);


      // End of user code
   }

   @Override
   public void addView (final View_Itf p_view)
   {
      // addView
      // Start of user code addView
      if (p_view instanceof IsWidget)
      {
         if (p_view instanceof DialogBox)
         {
            ((DialogBox) p_view).center();
         }
         else
         {
            _content.add((IsWidget) p_view);
         }
      }
      else
      {
         Window.alert("Vue inconnue : " + p_view.getClass().getName());
      }
      // End of user code
   }

   @Override
   public void restoreView (final View_Itf p_view)
   {
      // restoreView
      // Start of user code restoreView

      // End of user code
   }

   @Override
   public void removeView (final View_Itf p_view)
   {
      // removeView
      // Start of user code removeView
      if (p_view instanceof IsWidget)
      {
         if (p_view instanceof DialogBox)
         {
            ((DialogBox) p_view).hide();
         }
         else
         {
            _content.remove((IsWidget) p_view);
         }
      }
      else
      {
         Window.alert("Vue inconnue : " + p_view.getClass().getName());
      }
      // End of user code
   }

   // Getter txtLogin_ERREUR [ Widget sans nom]
   // Start of user code Getter txtLogin_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_txtLogin ()
   {
      return _txtLogin;
   }

   // End of user code

   // Getter txtPassword_ERREUR [ Widget sans nom]
   // Start of user code Getter txtPassword_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_txtPassword ()
   {
      return _txtPassword;
   }

   // End of user code

   // Getter btConnecter_ERREUR [ Widget sans nom]
   // Start of user code Getter btConnecter_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btConnecter ()
   {
      return _btConnecter;
   }

   // End of user code

   // Getter btAnnuler_ERREUR [ Widget sans nom]
   // Start of user code Getter btAnnuler_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btAnnuler ()
   {
      return _btAnnuler;
   }

   // End of user code

   // LoginViewGwt
   // Start of user code LoginViewGwt

   // End of user code

}
