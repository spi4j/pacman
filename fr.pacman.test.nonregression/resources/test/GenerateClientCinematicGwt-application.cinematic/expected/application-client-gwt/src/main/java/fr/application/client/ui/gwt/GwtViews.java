/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt; // NOPMD
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
import java.util.Date;

// End of user code

/**
 * Association des vues pour GWT.
 * @author safr@n
 */
public class GwtViews extends ViewsAssociation
{

   // CHECKSTYLE:OFF
   @SuppressWarnings("unchecked")
   @Override
   // CHECKSTYLE:ON
   public <TypeView extends View_Itf> TypeView getViewForPresenter (final Presenter_Abs<TypeView, ?> p_presenter)
   {
      if (p_presenter == null)
      {
         throw new Spi4jRuntimeException("Le paramètre presenter ne peut pas être null",
               "Précisez un présenteur non null dont vous cherchez la vue");
      }
      else if (p_presenter instanceof DetailPersonnePresenter)
      {
         return (TypeView) new DetailPersonneViewGwt((DetailPersonnePresenter) p_presenter);
      }
      else if (p_presenter instanceof ListePersonnesPresenter)
      {
         return (TypeView) new ListePersonnesViewGwt((ListePersonnesPresenter) p_presenter);
      }
      else if (p_presenter instanceof LoginPresenter)
      {
         return (TypeView) new LoginViewGwt((LoginPresenter) p_presenter);
      }
      else if (p_presenter instanceof MainPresenter)
      {
         return (TypeView) new MainViewGwt((MainPresenter) p_presenter);
      }
      // Vues supplémentaires
      // Start of user code Vues supplémentaires

      // End of user code

      throw new Spi4jRuntimeException("Vue inconnue pour le presenteur : " + p_presenter.getClass().getName(),
               "Ajouter une association dans GWTViews");
   }

   @Override
   public Presenter_Abs<? extends View_Itf, ?> getPresenterForAnnotatedView (final String p_userView)
   {
      throw new Spi4jRuntimeException(
               "Cette méthode devrait être utilisée uniquement lors des tests JBehave, hors cette classe n'est utilisée qu'en production",
               "Ne recherchez pas un présenteur selon son annotation @UserView dans vos écrans");
   }

   // GwtViews
   // Start of user code GwtViews

   // End of user code

}
