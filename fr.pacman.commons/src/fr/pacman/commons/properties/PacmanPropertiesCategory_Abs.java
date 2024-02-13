package fr.pacman.commons.properties;

import fr.pacman.commons.convention.NotationResolution;

/**
 * Donne le comportement attendu pour une classe gerant des proprietes Pacman.
 * 
 * @author MINARM
 */
public abstract class PacmanPropertiesCategory_Abs
{
   /**
    * Tableau contenant l'ensemble des proprietes definies.
    */
   private final PacmanProperty[] _tab_pacmanProperties;

   /**
    * Initialise une categorie de proprietes.
    */
   protected PacmanPropertiesCategory_Abs ()
   {
      _tab_pacmanProperties = initPacmanProperties();
      completePacmanProperties();
   }

   /**
    * Mise a jour des elements avec les infos de la categorie.
    */
   private void completePacmanProperties ()
   {
      for (int v_i = 0; v_i < _tab_pacmanProperties.length; v_i++)
      {
         _tab_pacmanProperties[v_i].setPropertyFileName(get_propertiesFileName());
      }
   }

   /**
    * Initialise les proprietes de cette categorie.
    * 
    * @return les proprietes de cette categorie.
    */
   protected abstract PacmanProperty[] initPacmanProperties ();

   /**
    * Recupere le nom du fichier de propriete pour stockage de cette categorie.
    * 
    * @return le nom du fichier de propriete.
    */
   protected abstract String get_propertiesFileName ();
   
   /**
    * Indique si le fichier doit prendre en compte les proprietes additionnelles.
    * 
    * @return true si le fichier recupere les proprietes additionnelles du developpeur.
    */
   protected abstract boolean is_defaultFileForAdditionalproperties();

   /**
    * Obtenir la liste des PacmanProperty decrivant les proprietes attendues par Pacman.
    * 
    * @return La liste desiree.
    */
   protected PacmanProperty[] get_tab_PacmanProperties ()
   {
      return _tab_pacmanProperties;
   }

   /**
    * Appliquer la norme 'packagePersistence' sur la valeur passee en parametre.
    * 
    * @param p_value
    *           (In) La valeur originale (ex : "Imputation de gestion").
    * 
    * @param p_properties
    *           les properties.
    * 
    * @param p_propertyName
    *           la cle de la propriete
    * 
    * @return La valeur respectant la norme.
    */
   protected static String applyNorme (final String p_value, final String p_propertyName)
   {
      final NotationResolution v_notationResolution = new NotationResolution(
               PacmanPropertiesManager.get_property(p_propertyName));
      return v_notationResolution.applyNorme(p_value, PacmanPropertiesManager.get_properties());
   }
}
