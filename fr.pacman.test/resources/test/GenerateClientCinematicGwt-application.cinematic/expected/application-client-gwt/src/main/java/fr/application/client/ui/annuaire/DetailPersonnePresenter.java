/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.annuaire;
// Start of user code for imports

import fr.application.client.ui.InvalidPresenter;
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
public class DetailPersonnePresenter extends Presenter_Abs<DetailPersonneView_Itf, Object>
// End of user code
{

   
   // Start of user code Constructeur(s)
   /**
    * Constructeur.
    */
   public DetailPersonnePresenter ()
   {
      super(null);
   }

   /**
    * Constructeur avec présenteur parent.
    * 
    * @param p_parent
    *           le présenteur parent
    */
   public DetailPersonnePresenter (final Presenter_Abs<? extends View_Itf, ?> p_parent)
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
      return "DetailPersonne";
      // End of user code
   }

   /**
    * Gestion de l'événement annuler.
    */
   // Start of user code Annotation annuler

   // End of user code
   public void annuler()
   {
      // Start of user code Pre annuler
      close();

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      
	  // Start of user code annuler : Action ListerPersonnes
      // TODO ListerPersonnes
      // End of user code

	  
      // Start of user code annuler : Affichage de ListePersonnes
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

      // Start of user code Post annuler

      // End of user code
   }

   /**
    * Gestion de l'événement enregistrer.
    */
   // Start of user code Annotation enregistrer

   // End of user code
   public void enregistrer()
   {
      // Start of user code Pre enregistrer
      close();

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      
	  // Start of user code enregistrer : Action Enregistrer
      // TODO Enregistrer
      // End of user code

      
	  // Start of user code enregistrer : Action ListerPersonnes
      // TODO ListerPersonnes
      // End of user code

	  
      // Start of user code enregistrer : Affichage de ListePersonnes
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

      // Start of user code Post enregistrer

      // End of user code
   }

   // DetailPersonnePresenter
   // Start of user code DetailPersonnePresenter

   // End of user code

}
