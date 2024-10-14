/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.business; // NOPMD

// Start of user code for imports

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.shared.GWT;


fr.application.client.business.annuaire.GradesGwtService
fr.application.client.business.annuaire.GradesGwtServiceAsync
fr.application.client.business.annuaire.PersonneCxfGwtService
fr.application.client.business.annuaire.PersonneCxfGwtServiceAsync

import fr.spi4j.ui.gwt.client.services.GwtUserBusiness_Abs;

// End of user code

/**
 * Classe permettant de centraliser les factories business de l'application.
 * @author safr@n
 */
public class ApplicationGwtUserBusiness extends GwtUserBusiness_Abs
{

   /** Singleton. */
   private static ApplicationGwtUserBusiness singleton = new ApplicationGwtUserBusiness();

   /**
    * Constructeur privé.
    */
   protected ApplicationGwtUserBusiness ()
   {
      super();
   }



   /**
    * Obtenir la façade de services 'GradesService'.
    * @return L'instance désirée.
    */
   public static GradesGwtServiceAsync getGradesService ()
   {
      return (GradesGwtServiceAsync) singleton.getBinding(GradesGwtService.class);
   }

   /**
    * Obtenir la façade de services 'PersonneCxfService'.
    * @return L'instance désirée.
    */
   public static PersonneCxfGwtServiceAsync getPersonneCxfService ()
   {
      return (PersonneCxfGwtServiceAsync) singleton.getBinding(PersonneCxfGwtService.class);
   }

   /**
    * @return le singleton de cette factory
    */
   public static ApplicationGwtUserBusiness getSingleton ()
   {
      return singleton;
   }

   // CHECKSTYLE:OFF
   @Override
   // CHECKSTYLE:ON
   public void initBindings ()
   {

      bind(GradesGwtService.class, GWT.create(GradesGwtService.class));
      bind(PersonneCxfGwtService.class, GWT.create(PersonneCxfGwtService.class));
   }

   // Methodes
   // Start of user code Methodes

   /**
    * Affecte un singleton dans le cas des tests d'intégration, qui renverra directement l'instance du service (sans passer par GWT).
    * @param p_singleton
    *           le singleton pour les tests d'intégration
    */
   public static void setSingleton (final ApplicationGwtUserBusiness p_singleton)
   {
      ApplicationGwtUserBusiness.singleton = p_singleton;
   }

   // End of user code

}
