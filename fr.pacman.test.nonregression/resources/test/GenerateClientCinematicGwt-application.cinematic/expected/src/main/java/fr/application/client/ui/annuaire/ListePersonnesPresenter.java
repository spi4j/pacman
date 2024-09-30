/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.client.ui.annuaire;
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

/**
 * Presenter.
 * @author safr@n
 */
// Class definition
// Start of user code Class definition
public class ListePersonnesPresenter extends Presenter_Abs<ListePersonnesView_Itf, Object>
// End of user code
{

   // Constructeur(s)
   // Start of user code Constructeur(s)
   /**
    * Constructeur.
    */
   public ListePersonnesPresenter ()
   {
      super(null);
   }

   /**
    * Constructeur avec présenteur parent.
    * 
    * @param p_parent
    *           le présenteur parent
    */
   public ListePersonnesPresenter (final Presenter_Abs<? extends View_Itf, ?> p_parent)
   {
      super(p_parent);
   }
   // End of user code

   @Override
   public void initView ()
   {
      // Initialisation de la vue
      // Start of user code Initialisation de la vue

      // End of user code
   }

   @Override
   protected String doGenerateTitle ()
   {
      // Titre de l\'écran
      // Start of user code Titre de l'écran
      return "ListePersonnes";
      // End of user code
   }

   /**
    * Gestion de l'événement actualiser.
    */
   // Start of user code Annotation actualiser

   // End of user code
   public void actualiser()
   {
      // Start of user code Pre actualiser

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      // Start of user code actualiser : Action ListerPersonnes
      // TODO ListerPersonnes
      // End of user code

      // Start of user code actualiser : Affichage de ListePersonnes
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

      // Start of user code Post actualiser

      // End of user code
   }

   /**
    * Gestion de l'événement selectGrade.
    */
   // Start of user code Annotation selectGrade

   // End of user code
   public void selectGrade()
   {
      // Start of user code Pre selectGrade

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      // Start of user code selectGrade : Action ListerPersonnes
      // TODO ListerPersonnes
      // End of user code

      // Start of user code selectGrade : Affichage de ListePersonnes
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

      // Start of user code Post selectGrade

      // End of user code
   }

   /**
    * Gestion de l'événement créer.
    */
   // Start of user code Annotation créer

   // End of user code
   public void creer()
   {
      // Start of user code Pre créer
      close();

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      // Start of user code créer : Affichage de DetailPersonne
      // affichage de l'écran DetailPersonne
      DetailPersonnePresenter v_DetailPersonne;
      try {
         // on tente d'instancier un nouvel écran
         v_DetailPersonne = new DetailPersonnePresenter(v_rootPresenter);
         v_rootPresenter.getView().addView(v_DetailPersonne.getView());
      } catch (ViewAlreadyRegisteredException v_e) {
         // si l'écran existe déjà on le récupère dans le gestionnaire de vues
         v_DetailPersonne = getViewManager().getPresenter(DetailPersonnePresenter.class);
         v_rootPresenter.getView().restoreView(v_DetailPersonne.getView());
      }

      // End of user code

      // Start of user code Post créer

      // End of user code
   }

   /**
    * Gestion de l'événement modifier.
    */
   // Start of user code Annotation modifier

   // End of user code
   public void modifier()
   {
      // Start of user code Pre modifier
      close();

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      // Start of user code modifier : Action RecupererPersonne
      // TODO RecupererPersonne
      // End of user code

      // Start of user code modifier : Affichage de DetailPersonne
      // affichage de l'écran DetailPersonne
      DetailPersonnePresenter v_DetailPersonne;
      try {
         // on tente d'instancier un nouvel écran
         v_DetailPersonne = new DetailPersonnePresenter(v_rootPresenter);
         v_rootPresenter.getView().addView(v_DetailPersonne.getView());
      } catch (ViewAlreadyRegisteredException v_e) {
         // si l'écran existe déjà on le récupère dans le gestionnaire de vues
         v_DetailPersonne = getViewManager().getPresenter(DetailPersonnePresenter.class);
         v_rootPresenter.getView().restoreView(v_DetailPersonne.getView());
      }

      // End of user code

      // Start of user code Post modifier

      // End of user code
   }

   /**
    * Gestion de l'événement supprimer.
    */
   // Start of user code Annotation supprimer

   // End of user code
   public void supprimer()
   {
      // Start of user code Pre supprimer

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      // Start of user code supprimer : Action SupprimerPersonne
      // TODO SupprimerPersonne
      // End of user code

      // Start of user code supprimer : Affichage de ListePersonnes
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

      // Start of user code Post supprimer

      // End of user code
   }

   /**
    * Gestion de l'événement visualiser.
    */
   // Start of user code Annotation visualiser

   // End of user code
   public void visualiser()
   {
      // Start of user code Pre visualiser
      close();

      final InvalidPresenter v_rootPresenter = getViewManager().getPresenter(InvalidPresenter.class);

      // End of user code

      // Start of user code visualiser : Action RecupererPersonne
      // TODO RecupererPersonne
      // End of user code

      // Start of user code visualiser : Affichage de DetailPersonne
      // affichage de l'écran DetailPersonne
      DetailPersonnePresenter v_DetailPersonne;
      try {
         // on tente d'instancier un nouvel écran
         v_DetailPersonne = new DetailPersonnePresenter(v_rootPresenter);
         v_rootPresenter.getView().addView(v_DetailPersonne.getView());
      } catch (ViewAlreadyRegisteredException v_e) {
         // si l'écran existe déjà on le récupère dans le gestionnaire de vues
         v_DetailPersonne = getViewManager().getPresenter(DetailPersonnePresenter.class);
         v_rootPresenter.getView().restoreView(v_DetailPersonne.getView());
      }

      // End of user code

      // Start of user code Post visualiser

      // End of user code
   }

   // ListePersonnesPresenter
   // Start of user code ListePersonnesPresenter

   // End of user code

}
