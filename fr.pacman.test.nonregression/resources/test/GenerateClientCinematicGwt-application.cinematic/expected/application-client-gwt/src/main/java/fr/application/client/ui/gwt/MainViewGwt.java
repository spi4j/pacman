/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
import fr.application.client.ui.LoginPresenter;
import fr.spi4j.ui.graal.UserAction;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;
import fr.spi4j.ui.mvp.View_Itf;
import java.util.Date;

// End of user code

public class MainViewGwt extends ERREUR [ Widget sans nom] implements MainView_Itf
{

   // Declaration btConnexion_ERREUR [ Widget sans nom]
   // Start of user code Declaration btConnexion_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btConnexion;

   // End of user code

   // Declaration btDeconnexion_ERREUR [ Widget sans nom]
   // Start of user code Declaration btDeconnexion_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btDeconnexion;

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
   public MainViewGwt (final MainPresenter p_presenter)
   {
      super(p_presenter);

      // avant initialisation
      // Start of user code avant initialisation
      _content = new VerticalPanel();
      _content.add(new HTML("<h1>Main</h1>"));

      // End of user code

      // initialisation des composants
      // Parametrage btConnexion_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btConnexion_ERREUR [ Widget sans nom]

      _btConnexion = new ERREUR [ Widget sans nom]();
      _content.add(_btConnexion);

      // End of user code

      // Start of user code Listener btConnexion_onClicConnexion
      _btConnexion.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().connexion();
         }
      });

      // Parametrage btDeconnexion_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btDeconnexion_ERREUR [ Widget sans nom]

      _btDeconnexion = new ERREUR [ Widget sans nom]();
      _content.add(_btDeconnexion);

      // End of user code

      // Start of user code Listener btDeconnexion_onClicDeconnexion
      _btDeconnexion.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().deconnexion();
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

   // Getter btConnexion_ERREUR [ Widget sans nom]
   // Start of user code Getter btConnexion_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btConnexion ()
   {
      return _btConnexion;
   }

   // End of user code

   // Getter btDeconnexion_ERREUR [ Widget sans nom]
   // Start of user code Getter btDeconnexion_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btDeconnexion ()
   {
      return _btDeconnexion;
   }

   // End of user code

   // MainViewGwt
   // Start of user code MainViewGwt

   // End of user code

}
