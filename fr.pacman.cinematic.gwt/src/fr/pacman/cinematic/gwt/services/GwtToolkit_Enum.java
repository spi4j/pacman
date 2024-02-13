package fr.pacman.cinematic.gwt.services;

import fr.pacman.cinematic.api.services.InterfaceToolkitWidget_Itf;

/**
 * Enumeration des widgets connus du toolkit GWT.
 * @author MINARM
 */
public enum GwtToolkit_Enum implements GwtToolkitWidget_Itf, InterfaceToolkitWidget_Itf
{

   // Rajouter ici les composants spécifiques à GWT, issus du toolkit GWT
   NONE("", "", "", "", "", "");

   public static final String c_toolkitName = "GWT";

   private String _name;

   private String _implementationClass;

   private String _implementationPackage;

   private String _interfaceName;

   private String _interfacePackage;

   private String _variablePrefixe;

   /**
    * Constructeur.
    * @param p_name
    *           le nom du widget
    * @param p_implementationClass
    *           la classe d'implémentation du widget
    * @param p_implementationPackage
    *           le package de l'implémentation du widget
    * @param p_interfaceName
    *           l'interface du widget
    * @param p_interfacePackage
    *           le package de l'implémentation du widget
    * @param p_variablePrefixe
    *           le préfixe de la variable du widget
    */
   private GwtToolkit_Enum (final String p_name, final String p_implementationClass,
            final String p_implementationPackage, final String p_interfaceName, final String p_interfacePackage,
            final String p_variablePrefixe)
   {
      _name = p_name;
      _implementationClass = p_implementationClass;
      _implementationPackage = p_implementationPackage;
      _interfaceName = p_interfaceName;
      _interfacePackage = p_interfacePackage;
      _variablePrefixe = p_variablePrefixe;
   }

   @Override
   public String getName ()
   {
      return _name;
   }

   @Override
   public String getImplementationClass ()
   {
      return _implementationClass;
   }

   @Override
   public String getImplementationPackage ()
   {
      return _implementationPackage;
   }

   @Override
   public String getInterfaceName ()
   {
      return _interfaceName;
   }

   @Override
   public String getInterfacePackage ()
   {
      return _interfacePackage;
   }

   @Override
   public String getVariablePrefixe ()
   {
      return _variablePrefixe;
   }

   @Override
   public String getToolkitName ()
   {
      return c_toolkitName;
   }

}
