/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.gwt;
// Start of user code for imports

import fr.spi4j.ui.gwt.client.services.DefaultAsyncCallback;

// End of user code

/**
 * Callback par défaut avec gestion d'erreur en affichant une popup.
 * 
 * @param <T>
 *           Type de données attendues en retour
 * @author safr@n
 */
public class ApplicationAsyncCallback<T> extends DefaultAsyncCallback<T>
{

   /**
    * Constructeur par défaut.
    */
   public ApplicationAsyncCallback ()
   {
      super();
   }

   /**
    * Constructeur définissant si une popup de chargement s'affiche ou non.
    * 
    * @param p_loadingEnabled
    *           true si une popup de chargement est affichée, false sinon.
    */
   public ApplicationAsyncCallback (final boolean p_loadingEnabled)
   {
      super(p_loadingEnabled);
   }

   /**
    * Constructeur définissant le texte du chargement.
    * 
    * @param p_loadingMessage
    *           le message affiché lors du chargement
    */
   public ApplicationAsyncCallback (final String p_loadingMessage)
   {
      super(p_loadingMessage);
   }

   @Override
   public Throwable buildException (final String p_className, final String p_errorMessageContent)
   {
      if (p_className == null)
      {
         return null;
      }
      
      // Start of user code Propagation des erreurs
      // if (p_className.equals(MonException.class.getName()))
      // {
      // return new MonException(p_errorMessageContent);
      // }
      // End of user code
      return super.buildException(p_className, p_errorMessageContent);
   }

   @Override
   protected void doOnError (final Throwable p_caught)
   {
      
      // Start of user code Gestion des erreurs
      // if (p_caught instanceof MonException)
      // {
      // ... traitement spécifique ...
      // }
      // else
      // {
      // super.doOnError(p_caught);
      // }
      // End of user code
   }

   
   // Start of user code ApplicationAsyncCallback

   // End of user code
}
