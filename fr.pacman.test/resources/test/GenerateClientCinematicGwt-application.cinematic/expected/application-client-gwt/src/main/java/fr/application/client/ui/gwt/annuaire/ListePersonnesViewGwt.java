/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt.annuaire;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
import fr.application.client.ui.annuaire.DetailPersonnePresenter;
import fr.application.client.ui.annuaire.ListePersonnesPresenter;
import fr.spi4j.ui.graal.UserAction;
import fr.spi4j.ui.mvp.Presenter_Abs;
import fr.spi4j.ui.mvp.ViewAlreadyRegisteredException;
import fr.spi4j.ui.mvp.View_Itf;
import java.util.Date;

// End of user code

public class ListePersonnesViewGwt extends ERREUR [ Widget sans nom] implements ListePersonnesView_Itf
{

   // Declaration cbGrade_ERREUR [ Widget sans nom]
   // Start of user code Declaration cbGrade_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _cbGrade;

   // End of user code

   // Declaration table_ERREUR [ Widget sans nom]
   // Start of user code Declaration table_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _table;

   // End of user code

   // Declaration btActualiser_ERREUR [ Widget sans nom]
   // Start of user code Declaration btActualiser_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btActualiser;

   // End of user code

   // Declaration btCreer_ERREUR [ Widget sans nom]
   // Start of user code Declaration btCreer_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btCreer;

   // End of user code

   // Declaration btModifier_ERREUR [ Widget sans nom]
   // Start of user code Declaration btModifier_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btModifier;

   // End of user code

   // Declaration btVisualiser_ERREUR [ Widget sans nom]
   // Start of user code Declaration btVisualiser_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btVisualiser;

   // End of user code

   // Declaration btSupprimer_ERREUR [ Widget sans nom]
   // Start of user code Declaration btSupprimer_ERREUR [ Widget sans nom]

   private final ERREUR [ Widget sans nom] _btSupprimer;

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
   public ListePersonnesViewGwt (final ListePersonnesPresenter p_presenter)
   {
      super(p_presenter);

      // avant initialisation
      // Start of user code avant initialisation
      _content = new VerticalPanel();
      _content.add(new HTML("<h1>ListePersonnes</h1>"));

      // End of user code

      // initialisation des composants
      // Parametrage cbGrade_ERREUR [ Widget sans nom]
      // Start of user code Parametrage cbGrade_ERREUR [ Widget sans nom]

      _cbGrade = new ERREUR [ Widget sans nom]();
      _content.add(_cbGrade);

      // End of user code

      // Start of user code Listener cbGrade_onSelectGrade
      _cbGrade.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().selectGrade();
         }
      });

      // Parametrage table_ERREUR [ Widget sans nom]
      // Start of user code Parametrage table_ERREUR [ Widget sans nom]

      _table = new ERREUR [ Widget sans nom]();
      _content.add(_table);

      // End of user code

      // Parametrage btActualiser_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btActualiser_ERREUR [ Widget sans nom]

      _btActualiser = new ERREUR [ Widget sans nom]();
      _content.add(_btActualiser);

      // End of user code

      // Start of user code Listener btActualiser_onClicActualiser
      _btActualiser.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().actualiser();
         }
      });

      // Parametrage btCreer_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btCreer_ERREUR [ Widget sans nom]

      _btCreer = new ERREUR [ Widget sans nom]();
      _content.add(_btCreer);

      // End of user code

      // Start of user code Listener btCreer_onClicCreer
      _btCreer.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().creer();
         }
      });

      // Parametrage btModifier_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btModifier_ERREUR [ Widget sans nom]

      _btModifier = new ERREUR [ Widget sans nom]();
      _content.add(_btModifier);

      // End of user code

      // Start of user code Listener btModifier_onClicModifier
      _btModifier.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().modifier();
         }
      });

      // Parametrage btVisualiser_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btVisualiser_ERREUR [ Widget sans nom]

      _btVisualiser = new ERREUR [ Widget sans nom]();
      _content.add(_btVisualiser);

      // End of user code

      // Start of user code Listener btVisualiser_onClicVisualiser
      _btVisualiser.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().visualiser();
         }
      });

      // Parametrage btSupprimer_ERREUR [ Widget sans nom]
      // Start of user code Parametrage btSupprimer_ERREUR [ Widget sans nom]

      _btSupprimer = new ERREUR [ Widget sans nom]();
      _content.add(_btSupprimer);

      // End of user code

      // Start of user code Listener btSupprimer_onClicSupprimer
      _btSupprimer.addXXXHandler(new XXXHandler()
      {
         @Override
         public void onXXX (final XXXEvent p_event)
         // End of user code
         {
            getPresenter().supprimer();
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

   // Getter cbGrade_ERREUR [ Widget sans nom]
   // Start of user code Getter cbGrade_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_cbGrade ()
   {
      return _cbGrade;
   }

   // End of user code

   // Getter table_ERREUR [ Widget sans nom]
   // Start of user code Getter table_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_table ()
   {
      return _table;
   }

   // End of user code

   // Getter btActualiser_ERREUR [ Widget sans nom]
   // Start of user code Getter btActualiser_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btActualiser ()
   {
      return _btActualiser;
   }

   // End of user code

   // Getter btCreer_ERREUR [ Widget sans nom]
   // Start of user code Getter btCreer_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btCreer ()
   {
      return _btCreer;
   }

   // End of user code

   // Getter btModifier_ERREUR [ Widget sans nom]
   // Start of user code Getter btModifier_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btModifier ()
   {
      return _btModifier;
   }

   // End of user code

   // Getter btVisualiser_ERREUR [ Widget sans nom]
   // Start of user code Getter btVisualiser_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btVisualiser ()
   {
      return _btVisualiser;
   }

   // End of user code

   // Getter btSupprimer_ERREUR [ Widget sans nom]
   // Start of user code Getter btSupprimer_ERREUR [ Widget sans nom]

   @Override
   public ERREUR [ Widget sans nom] get_btSupprimer ()
   {
      return _btSupprimer;
   }

   // End of user code

   // ListePersonnesViewGwt
   // Start of user code ListePersonnesViewGwt

   // End of user code

}
