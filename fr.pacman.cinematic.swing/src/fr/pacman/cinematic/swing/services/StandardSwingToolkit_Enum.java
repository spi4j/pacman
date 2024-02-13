package fr.pacman.cinematic.swing.services;

import fr.pacman.cinematic.api.services.StandardToolkitWidget_Itf;

/**
 * Enumeration des widgets connus du toolkit Standard.
 * @author MINARM
 */
public enum StandardSwingToolkit_Enum implements StandardToolkitWidget_Itf, SwingToolkitWidget_Itf
{

   /**
    * 
    */
   CHECK_BOX("CheckBox", "SpiCheckBox", "fr.spi4j.ui.swing.fields", "ck"),
   /**
    * 
    */
   COMBO_BOX("ComboBox", "SpiComboBox<T>", "fr.spi4j.ui.swing.fields", "cb"),
   /**
    * 
    */
   DATE_FIELD("DateField", "SpiDateField", "fr.spi4j.ui.swing.fields", "dt"),
   /**
    * 
    */
   DOUBLE_FIELD("DoubleField", "SpiDoubleField", "fr.spi4j.ui.swing.fields", "dbl"),
   /**
    * 
    */
   INTEGER_FIELD("IntegerField", "SpiIntegerField", "fr.spi4j.ui.swing.fields", ""),
   /**
    * 
    */
   PASSWORD_FIELD("PasswordField", "SpiPasswordField", "fr.spi4j.ui.swing.fields", "pwd"),
   /**
    * 
    */
   STRING_FIELD("StringField", "SpiStringField", "fr.spi4j.ui.swing.fields", "txt"),
   /**
    * 
    */
   TABLE("Table", "SpiTable<T>", "fr.spi4j.ui.swing.table", "tbl"),
   /**
    * 
    */
   VIEW_DIALOG("ViewDialog", "SpiViewDialog<T>", "fr.spi4j.ui.swing.fields", "dlg"),
   /**
    * 
    */
   VIEW_PANEL("ViewPanel", "SpiViewPanel<T>", "fr.spi4j.ui.swing.fields", "pnl"),
   /**
    * 
    */
   MAIN_PANEL("MainPanel", "SpiViewPanel<T>", "fr.spi4j.ui.swing.fields", ""),
   /**
    * 
    */
   BOUTON("Button", "SpiButton", "fr.spi4j.ui.swing", "bt");

   private String _name;

   private String _implementationClass;

   private String _implementationPackage;

   private String _variablePrefixe;

   /**
    * Constructeur.
    * @param p_name
    *           le nom du widget
    * @param p_implementationClass
    *           la classe d'implémentation du widget
    * @param p_implementationPackage
    *           le package de l'implémentation du widget
    * @param p_variablePrefixe
    *           le préfixe de la variable du widget
    */
   private StandardSwingToolkit_Enum (final String p_name, final String p_implementationClass,
            final String p_implementationPackage, final String p_variablePrefixe)
   {
      _name = p_name;
      _implementationClass = p_implementationClass;
      _implementationPackage = p_implementationPackage;
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
   public String getVariablePrefixe ()
   {
      return _variablePrefixe;
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
