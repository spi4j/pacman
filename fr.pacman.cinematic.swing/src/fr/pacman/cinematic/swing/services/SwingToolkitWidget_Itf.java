package fr.pacman.cinematic.swing.services;

import fr.pacman.cinematic.api.services.ToolkitWidget_Itf;

/**
 * Interface des énumérations de widgets par Toolkit.
 * @author MINARM
 */
public interface SwingToolkitWidget_Itf extends ToolkitWidget_Itf
{

   /**
    * @return la classe d'implémentation du widget
    */
   String getImplementationClass ();

   /**
    * @return le package de l'implémentation du widget
    */
   String getImplementationPackage ();

   /**
    * @return le préfixe de la variable du widget
    */
   String getVariablePrefixe ();

}
