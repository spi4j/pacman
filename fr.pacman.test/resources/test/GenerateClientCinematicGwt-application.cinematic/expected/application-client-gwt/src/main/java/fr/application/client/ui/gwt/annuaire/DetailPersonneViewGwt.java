/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt.annuaire;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
import fr.application.client.ui.annuaire.ListePersonnesPresenter;
import fr.spi4j.ui.graal.UserAction;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;
import fr.spi4j.ui.mvp.View_Itf;

// End of user code

public class DetailPersonneViewGwt extends ERREUR [ Widget sans nom] implements DetailPersonneView_Itf
{

   
   // Start of user code Declaration btAnnuler_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btAnnuler;

   // End of user code

   
   // Start of user code Declaration btEnregistrer_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btEnregistrer;

   // End of user code

   
   // Start of user code Attributs
   private final VerticalPanel _content;

   // End of user code
   /**
    * Constructeur de la vue.
    * @param p_presenter
    *           le présenteur de la vue
    */
   public DetailPersonneViewGwt (final DetailPersonnePresenter p_presenter)
   {
      super(p_presenter);

      
      // Start of user code avant initialisation
      _content = new VerticalPanel();
      _content.add(new HTML("<h1>DetailPersonne</h1>"));

      // End of user code

      // initialisation des composants
      
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
            getPresenter().annuler();
         }
      });

      
      // Start of user code Parametrage btEnregistrer_ERREUR [ Widget sans nom]

      _btEnregistrer = new ERREUR [ Widget sans nom]();
      _content.add(_btEnregistrer);

      // End of user code

	  
      // Start of user code Listener btEnregistrer_onClicEnregistrer
      _btEnregistrer.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().enregistrer();
         }
      });

      
      // Start of user code apres initialisation
      add(_content);


      // End of user code
   }

   @Override
   public void addView (final View_Itf p_view)
   {
      
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
      
      // Start of user code restoreView

      // End of user code
   }

   @Override
   public void removeView (final View_Itf p_view)
   {
      
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

   
   // Start of user code Getter btAnnuler_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btAnnuler ()
   {
      return _btAnnuler;
   }

   // End of user code

   
   // Start of user code Getter btEnregistrer_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btEnregistrer ()
   {
      return _btEnregistrer;
   }

   // End of user code

   
   // Start of user code DetailPersonneViewGwt

   // End of user code

}
