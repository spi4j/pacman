package fr.pacman.cinematic.api.services;

/**
 * Enumeration des widgets connus du toolkit Standard.
 * @author MINARM
 */
public enum StandardInterfaceToolkit_Enum implements StandardToolkitWidget_Itf, InterfaceToolkitWidget_Itf
{

   /**
    * 
    */
   CHECK_BOX("CheckBox", "HasBoolean_Itf", "fr.spi4j.ui"),
   /**
    * 
    */
   COMBO_BOX("ComboBox", "HasSelection_Itf<T>", "fr.spi4j.ui"),
   /**
    * 
    */
   DATE_FIELD("DateField", "HasDate_Itf", "fr.spi4j.ui"),
   /**
    * 
    */
   DOUBLE_FIELD("DoubleField", "HasDouble_Itf", "fr.spi4j.ui"),
   /**
    * 
    */
   INTEGER_FIELD("IntegerField", "HasInteger_Itf", "fr.spi4j.ui"),
   /**
    * 
    */
   PASSWORD_FIELD("PasswordField", "HasString_Itf", "fr.spi4j.ui"),
   /**
    * 
    */
   STRING_FIELD("StringField", "HasString_Itf", "fr.spi4j.ui"),
   /**
    * 
    */
   TABLE("Table", "HasSelection_Itf<T>", "fr.spi4j.ui"),
   /**
    * 
    */
   VIEW_DIALOG("ViewDialog", "View_Itf", "fr.spi4j.ui.mvp"),
   /**
    * 
    */
   VIEW_PANEL("ViewPanel", "View_Itf", "fr.spi4j.ui.mvp"),
   /**
    * 
    */
   MAIN_PANEL("MainPanel", "View_Itf", "fr.spi4j.ui.mvp"),
   /**
    * 
    */
   BOUTON("Button", "HasString_Itf", "fr.spi4j.ui");

   private String _name;

   private String _interfaceName;

   private String _interfacePackage;

   /**
    * Constructeur.
    * @param p_name
    *           le nom du widget
    * @param p_interfaceName
    *           l'interface du widget
    * @param p_interfacePackage
    *           le package de l'implémentation du widget
    */
   private StandardInterfaceToolkit_Enum (final String p_name, final String p_interfaceName,
            final String p_interfacePackage)
   {
      _name = p_name;
      _interfaceName = p_interfaceName;
      _interfacePackage = p_interfacePackage;
   }

   @Override
   public String getName ()
   {
      return _name;
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
   public StandardToolkitWidget_Itf getCheckBox ()
   {
      return CHECK_BOX;
   }

   @Override
   public StandardToolkitWidget_Itf getComboBox ()
   {
      return COMBO_BOX;
   }

   @Override
   public StandardToolkitWidget_Itf getDateField ()
   {
      return DATE_FIELD;
   }

   @Override
   public StandardToolkitWidget_Itf getDoubleField ()
   {
      return DOUBLE_FIELD;
   }

   @Override
   public StandardToolkitWidget_Itf getIntegerField ()
   {
      return INTEGER_FIELD;
   }

   @Override
   public StandardToolkitWidget_Itf getPasswordField ()
   {
      return PASSWORD_FIELD;
   }

   @Override
   public StandardToolkitWidget_Itf getStringField ()
   {
      return STRING_FIELD;
   }

   @Override
   public StandardToolkitWidget_Itf getTable ()
   {
      return TABLE;
   }

   @Override
   public StandardToolkitWidget_Itf getViewDialog ()
   {
      return VIEW_DIALOG;
   }

   @Override
   public StandardToolkitWidget_Itf getViewPanel ()
   {
      return VIEW_PANEL;
   }

   @Override
   public StandardToolkitWidget_Itf getMainPanel ()
   {
      return MAIN_PANEL;
   }

   @Override
   public StandardToolkitWidget_Itf getButton ()
   {
      return BOUTON;
   }

   @Override
   public String getToolkitName ()
   {
      return c_toolkitName;
   }

}
