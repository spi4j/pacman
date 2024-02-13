package fr.pacman.validation.services.rules;

/**
 * Permet de definir les valeurs posibles pour un niveau de regle.
 */
public enum ValidationLevel_Enum
{
   // Niveau erreur
   Error(1, "Erreur"),
   // Niveau avertissement
   Warning(2, "Avertissement"),
   // Niveau info
   Information(3, "Information");
   // Remarque : Pour desactiver une regle, cf. "DslValidationRuleBean"

   private final int _value;

   private final String _title;

   /**
    * Constructeur.
    * 
    * @param p_value
    *           la valeur entière du niveau
    * @param p_title
    *           le libellé du niveau
    */
   private ValidationLevel_Enum (final int p_value, final String p_title)
   {
      _value = p_value;
      _title = p_title;
   }

   /**
    * @return la valeur du niveau
    */
   public int getValue ()
   {
      return _value;
   }

   /**
    * @return le libellé du niveau pour affichage
    */
   public String getTitle ()
   {
      return _title;
   }

   @Override
   public String toString ()
   {
      return getTitle();
   }

   /**
    * Retrouve un niveau selon sa valeur. Lance une
    * {@link IllegalArgumentException} si la valeur du niveau est inconnue.
    * 
    * @param p_value
    *           la valeur du niveau
    * @return le niveau correspondant à cette valeur
    */
   public static ValidationLevel_Enum findLevel (final int p_value)
   {
      for (final ValidationLevel_Enum v_niveau : values())
      {
         if (v_niveau._value == p_value)
         {
            return v_niveau;
         }
      }
      throw new IllegalArgumentException("Niveau inconnu (valeurs possibles : 1, 2, 3) : " + p_value);
   }

   /**
    * Retrouve un niveau selon son texte. Lance une
    * {@link IllegalArgumentException} si la valeur du niveau est inconnue.
    * 
    * @param p_value
    *           la valeur du niveau
    * @return le niveau correspondant à cette valeur
    */
   public static ValidationLevel_Enum findLevel (final String p_value)
   {
      for (final ValidationLevel_Enum v_niveau : values())
      {
         if (v_niveau._title.equalsIgnoreCase(p_value))
         {
            return v_niveau;
         }
      }
      throw new IllegalArgumentException("Niveau inconnu (valeurs possibles : Erreur, Avertissement, Information) : " + p_value);
   }

}
