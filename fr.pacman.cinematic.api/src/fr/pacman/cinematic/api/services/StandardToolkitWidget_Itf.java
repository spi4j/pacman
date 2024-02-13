package fr.pacman.cinematic.api.services;

/**
 * Interface des énumérations de widgets par Toolkit, définies par défaut dans Spi4J, à implémenter par cible de générateur cinematic. <br />
 * Les méthodes à implémentées sont uniquement là pour provoquer une erreur de compilation si un widget est rajouté dans le toolkit mais pas géré par un générateur.
 * @author MINARM
 */
public interface StandardToolkitWidget_Itf
{

   public static final String c_toolkitName = "Safran-standard";

   /**
    * @return la valeur d'énumération correspondant à la case à cocher
    */
   StandardToolkitWidget_Itf getCheckBox ();

   /**
    * @return la valeur d'énumération correspondant à la liste déroulante
    */
   StandardToolkitWidget_Itf getComboBox ();

   /**
    * @return la valeur d'énumération correspondant au champ de saisie de date
    */
   StandardToolkitWidget_Itf getDateField ();

   /**
    * @return la valeur d'énumération correspondant au champ de saisie de nombre
    */
   StandardToolkitWidget_Itf getDoubleField ();

   /**
    * @return la valeur d'énumération correspondant au champ de saisie d'entier
    */
   StandardToolkitWidget_Itf getIntegerField ();

   /**
    * @return la valeur d'énumération correspondant au champ de saisie de mot de passe
    */
   StandardToolkitWidget_Itf getPasswordField ();

   /**
    * @return la valeur d'énumération correspondant au champ de saisie de texte
    */
   StandardToolkitWidget_Itf getStringField ();

   /**
    * @return la valeur d'énumération correspondant au tableau
    */
   StandardToolkitWidget_Itf getTable ();

   /**
    * @return la valeur d'énumération correspondant à la fenêtre de dialogue
    */
   StandardToolkitWidget_Itf getViewDialog ();

   /**
    * @return la valeur d'énumération correspondant à la fenêtre de l'application
    */
   StandardToolkitWidget_Itf getViewPanel ();

   /**
    * @return la valeur d'énumération correspondant à l'écran principal de l'application
    */
   StandardToolkitWidget_Itf getMainPanel ();

   /**
    * @return la valeur d'énumération correspondant au bouton
    */
   StandardToolkitWidget_Itf getButton ();

}
