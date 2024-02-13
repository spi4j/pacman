package fr.pacman.commons.properties;

public enum PacmanPropertyFileAction_Enum
{
   ADD("Ajout"), MODIFY("Modification"), REMOVE("Suppression");

   private final String _textToDisplay;

   /**
    * Constructeur.
    */
   PacmanPropertyFileAction_Enum (final String p_textToDisplay)
   {

      _textToDisplay = p_textToDisplay;
   }

   
   public String get_textToDisplay ()
   {
      return _textToDisplay;
   }
}
