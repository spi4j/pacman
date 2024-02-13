package fr.pacman.cinematic.api.services;

/**
 * Interface des énumérations de Widget sur lesquels on peut récupérer une interface.
 * @author MINARM
 */
public interface InterfaceToolkitWidget_Itf extends ToolkitWidget_Itf
{

   /**
    * @return l'interface du widget
    */
   String getInterfaceName ();

   /**
    * @return le package de l'interface du widget
    */
   String getInterfacePackage ();

}
