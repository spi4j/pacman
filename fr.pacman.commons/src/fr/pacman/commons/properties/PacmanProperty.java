package fr.pacman.commons.properties;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Represente un element de propriete pour le parametrage PacMan.
 * 
 * A utiliser pour avoir la liste des proprietes utilisees par PacMan, et generer des fichier de proprietes structures avec les valeurs par defaut contenues dans ces elements.
 * 
 * @author MINARM
 * 
 */
public class PacmanProperty implements Comparable<PacmanProperty>
{
   
   /**
    * Compteur incremente pour l'ordre d'ecriture dans les fichiers.
    */
   private static int _incWriteOrder;

   /**
    * L'identifiant (cle) de la propriete.
    */
   private final String _id;

   /**
    * La/les valeur(s) par defaut fixee(s) pour la propriete.
    */
   private final String[] _defaultValues;

   /**
    * La valeur a utiliser pour la propriete.
    */
   private String _value;

   /**
    * La strategie a appliquer lors de la modification de cette propriete.
    */
   private final PacmanPropertyStrategy_Abs _strategy;

   /**
    * Le statut de reference de la propriete.
    */
   private final PacmanPropertyStatus_Enum _defaultStatus;

   /**
    * Le statut de la propriete.
    */
   private PacmanPropertyStatus_Enum _status;

   /**
    * Le fichier '.properties' pour cette propriete.
    */
   private String _propertyFileName;

   /**
    * L'ordre d'affichage dans le fichier '.properties'.
    */
   private int _order;

   /**
    * Le commentaire a afficher dans le fichier '.properties'.
    */
   private final String _comment;
   
   /**
    * Action necessitant la reecriture du fichier de configuration.
    */
   private PacmanPropertyFileAction_Enum _fileAction;

   /**
    * Constructeur prive.
    * 
    * @param p_id
    *           la cle pour l'element.
    * @param p_defaultValue
    *           la valeur par defaut pour l'element.
    * @param p_comment
    *           le commentaire a afficher dans le fichier '.properties'.
    * @param p_status
    *           le statut pour l'element.
    * @param p_strategy
    *           la strategie (si existe) pour l'element.
    */
   private PacmanProperty (final String p_id, final String[] p_defaultValues, final String p_comment,
            final PacmanPropertyStatus_Enum p_status, final PacmanPropertyStrategy_Abs p_strategy)
   {
      _id = p_id;
      _status = p_status;
      _comment = p_comment;
      _strategy = p_strategy;
      _order = _incWriteOrder;
      _defaultStatus = p_status;
      _value = p_defaultValues[0];
      _defaultValues = p_defaultValues;
      
      //Incrementation du compteur d'ecriture.
      _incWriteOrder++;

      if (null != p_strategy)
         p_strategy.set_refPacmanProperty(this);
   }

   /**
    * Creation d'une propriete obligatoire.
    * 
    * @param p_id
    *           la cle de la propriete.
    * @param p_defaultValue
    *           la valeur par defaut de la propriete.
    * @param p_comment
    *           le commentaire a afficher pour la propriete.
    * @return l'instance de la propriete.
    */
   public static PacmanProperty newRequired (final String p_id, final String p_defaultValue, final String p_comment)
   {
      return new PacmanProperty(p_id, new String[]
      {p_defaultValue }, p_comment, PacmanPropertyStatus_Enum.REQUIRED, null);
   }

   /**
    * Creation d'une propriete obligatoire.
    * 
    * @param p_id
    *           la cle de la propriete.
    * @param p_defaultValues
    *           les valeurs par defaut de la propriete.
    * @param p_comment
    *           le commentaire a afficher pour la propriete.
    * @return l'instance de la propriete.
    */
   public static PacmanProperty newRequired (final String p_id, final String[] p_defaultValues, final String p_comment)
   {
      return new PacmanProperty(p_id, p_defaultValues, p_comment, PacmanPropertyStatus_Enum.REQUIRED, null);
   }

   /**
    * Creation d'une propriete obligatoire avec une strategie.
    * 
    * @param p_id
    *           la cle de la propriete.
    * @param p_defaultValue
    *           la valeur par defaut de la propriete.
    * @param p_comment
    *           le commentaire a afficher pour la propriete.
    * @param p_strategy 
    *           la strategie a appliquer en fonction de la valeur de la propriete. 
    * @return l'instance de la propriete.
    */
   public static PacmanProperty newRequired (final String p_id, final String p_defaultValue, final String p_comment,
            final PacmanPropertyStrategy_Abs p_strategy)
   {
      return new PacmanProperty(p_id, new String[]
      {p_defaultValue }, p_comment, PacmanPropertyStatus_Enum.REQUIRED, p_strategy);
   }

   /**
    * Creation d'une propriete soumise a condition.
    * 
    * @param p_id
    * @param p_defaultValue
    * @param p_comment
    * @return
    */
   public static PacmanProperty newConditional (final String p_id, final String p_defaultValue, final String p_comment)
   {
      return new PacmanProperty(p_id, new String[]
      {p_defaultValue }, p_comment, PacmanPropertyStatus_Enum.CONDITIONAL, null);
   }

   /**
    * Creation d'une propriete soumise a condition.
    * 
    * @param p_id
    * @param p_defaultValue
    * @param p_comment
    * @return
    */
   public static PacmanProperty newConditional (final String p_id, final String[] p_defaultValues,
            final String p_comment)
   {
      return new PacmanProperty(p_id, p_defaultValues, p_comment, PacmanPropertyStatus_Enum.CONDITIONAL, null);
   }

   /**
    * Creation d'une propriete optionnelle, pas de controle d'existence sur cette propriete .
    * 
    * @param p_id
    *           la cle de la propriete.
    * @param p_defaultValue
    *           la valeur par defaut de la propriete.
    * @param p_comment
    *           le commentaire a afficher pour la propriete.
    * @return l'instance de la propriete.
    */
   public static PacmanProperty newOptional (final String p_id, final String p_defaultValue, final String p_comment)
   {
      return new PacmanProperty(p_id, new String[]
      {p_defaultValue }, p_comment, PacmanPropertyStatus_Enum.OPTIONAL, null);
   }
   
   /**
    * Creation d'une propriete additionnelle (utilisateur).
    * 
    * @param p_id
    *           la cle de la propriete.
    * @param p_defaultValue
    *           la valeur par defaut de la propriete.
    * @param p_comment
    *           le commentaire a afficher pour la propriete.
    * @return l'instance de la propriete.
    */
   public static PacmanProperty newAdditional (final String p_id, final String p_defaultValue, final String p_comment)
   {
      return new PacmanProperty(p_id, new String[]
      {p_defaultValue }, p_comment, PacmanPropertyStatus_Enum.CONDITIONAL, null);
   }

   /**
    * Creation d'une propriete en memoire uniquement (pas de stockage dans un fichier .properties), pas de controle d'existence sur cette propriete .
    * 
    * @param p_id
    *           la cle de la propriete.
    * @param p_defaultValue
    *           la valeur par defaut de la propriete.
    * @param p_comment
    *           le commentaire a afficher pour la propriete.
    * @return l'instance de la propriete.
    */
   public static PacmanProperty newMemoryOnly (final String p_id, final String p_defaultValue, final String p_comment)
   {
      return new PacmanProperty(p_id, new String[]
      {p_defaultValue }, p_comment, PacmanPropertyStatus_Enum.MEMORY, null);
   }

   /**
    * Creation de la ligne d'ecriture pour l'element.
    */
   @Override
   public String toString ()
   {
      String v_return;

      // Si commentaire.
      if (null != _comment && _comment.trim().length() > 0)
      {
         v_return = "# " + _comment + "\n";
      }
      else
      {
         v_return = "";
      }
      // Constituer la propriete avec id = valeur precedee du commentaire
      return v_return + _id + " = " + _value + "\n";
   }

   /**
    * Met en forme l'element pour un fichier '.properties' ex : #identifiant de l'application idAppli = appwhite1.
    * 
    * @return La propriete" mise en forme pour les properties directement sous le bon format "ISO-8859-1".
    * 
    * @throws UnsupportedEncodingException
    */
   protected byte[] toStringProperties () throws UnsupportedEncodingException
   {
      return toString().getBytes("ISO-8859-1");
   }

   /**
    * Permet de trier les proprietes pour ecriture ".properties".
    */
   @Override
   public int compareTo (PacmanProperty o)
   {
      return (this._order - o._order);
   }

   /**
    * Execute la strategie de l'element.
    * 
    * @param p_pacmanProperties
    *           la liste des proprietes.
    */
   protected void applyStrategy (final Map<String, PacmanProperty> p_pacmanProperties)
   {
      if (has_strategy())
         _strategy.applyStrategy(p_pacmanProperties);
   }

   /**
    * Positionne le statut de l'element.
    * 
    * On ne fait que demander le positionnement d'un statut.
    * 
    * Des regles de gestion sont appliquees.
    * 
    * @param _status
    *           le statut (enumeration) de l'element.
    */
   public void set_status (PacmanPropertyStatus_Enum p_status)
   {
      if (PacmanPropertyStatus_Enum.FILLED == p_status)
      {
         if (PacmanPropertyStatus_Enum.REQUIRED == get_defaultStatus())
            _status = p_status;

         if (PacmanPropertyStatus_Enum.CONDITIONAL == get_defaultStatus())
            _status = PacmanPropertyStatus_Enum.STANDBY;
      }

      if (PacmanPropertyStatus_Enum.REQUIRED == p_status)
      {
         if (PacmanPropertyStatus_Enum.STANDBY == _status)
            _status = PacmanPropertyStatus_Enum.FILLED;

         if (PacmanPropertyStatus_Enum.CONDITIONAL == _status)
            _status = p_status;
      }
   }

   /**
    * Remise a blanc pour le statut courant.
    */
   protected void reset ()
   {
      _status = _defaultStatus;
   }

   /**
    * Positionne le nom du fichier '.properties' dans lequel est doit etre stockee la propriete.
    * 
    * @param _propertyFileName
    *           le nom du fichier '.properties' pour la propriete.
    */
   protected void setPropertyFileName (String p_propertyFileName)
   {
      _propertyFileName = p_propertyFileName;
   }

   /**
    * Positionne la valeur a utiliser pour la propriete.
    * Si la propriete est soumise a une strategie alors 
    * on positionne de suite sa valeur dans la strategie 
    * afin de pouvoir correctement gerer le trigger dans le 
    * cas d'un ONCHANGE...
    * 
    * @param _value
    *           la nouvelle valeur pour la propriete.
    */
   public void set_value (final String p_value)
   {
      // Positionne la valeur de la propriete.
      _value = p_value;
      // La propriete est consideree comme renseignee.
      set_status(PacmanPropertyStatus_Enum.FILLED);
      
      // Parametrage de la strategie si besoin.
      if(has_strategy()) 
         get_strategy().updateWithOldRefValue(_value);
   }

   /**
    * Modifie une partie de la valeur (cas des WildCards).
    * 
    * @param p_value
    *           la nouvelle valeur pour la propriete.
    */
   public void update_value (final String p_value)
   {
      _value = p_value;
   }

   /**
    * Modifie la valeur de l'element avec une valeur par defaut.
    * 
    * On force le statut.
    * 
    * @param p_defaultValueIdx
    *           l'index dans le tableau des valeurs par defaut.
    */
   public void set_valueFromIndexedDefaultValue (int p_idx)
   {
      if (p_idx <= _defaultValues.length)
      {
         // Si la valeur est correcte, inutile de la modifier.
         // Par ailleurs si sur strategie ONCHANGE empeche
         // l'ecriture au demarrage du studio.
         if (_value.equals(_defaultValues[p_idx]))
            return;

         _value = _defaultValues[p_idx];
         reset();
      }
   }

   /**
    * Recupere le nom du fichier '.properties' dans lequel est stockee la propriete.
    * 
    * @return _propertyFileName le nom du fichier de propriete
    */
   public String getPropertyFileName ()
   {
      return _propertyFileName;
   }

   /**
    * Obtenir l'ordre d'affichage de la propriete dans le fichier '.properties'.
    * 
    * @return _order l'ordre d'affichage pour la propriete.
    */
   protected int get_order ()
   {
      return _order;
   }

   /**
    * Obtenir le statut par defaut de l'element (il s'agit du statut de reference).
    * 
    * @return _defaultStatus le statut de reference.
    */
   protected PacmanPropertyStatus_Enum get_defaultStatus ()
   {
      return _defaultStatus;
   }

   /**
    * Obtenir la statut courant de la propriete.
    * 
    * @return _status le statut.
    */
   protected PacmanPropertyStatus_Enum get_status ()
   {
      return _status;
   }

   /**
    * Obtenir la valeur de l'element.
    * 
    * @return _value la valeur a utiliser
    */
   public String get_value ()
   {
      return _value;
   }

   /**
    * Obtenir la cle de l'element.
    * 
    * @return _id la cle de l'element.
    */
   public String get_id ()
   {
      return _id;
   }

   /**
    * Obetnir le commentaire pour l'element.
    * 
    * @return _comment le commentaire.
    */
   protected String get_comment ()
   {
      return _comment;
   }

   /**
    * Obtenir le nom du fichier '.properties' pour l'element.
    * 
    * @return _propertyFileName le nom du fichier '.properties'.
    */
   protected String get_propertyFileName ()
   {
      return _propertyFileName;
   }

   /**
    * Les proprietes a ecrire dans les fichiers sont
    * 
    * - toutes les proprietes obligatoires non remplies.
    * 
    * - toutes les proprietes remplies.
    * 
    * @return _writeToFile indicateur pour savoir si la propriete doit etre ecrite.
    */
   protected boolean is_writeToFile ()
   {
      if (PacmanPropertyStatus_Enum.REQUIRED == _status)
         return Boolean.TRUE;

      if (PacmanPropertyStatus_Enum.FILLED == _status)
         return Boolean.TRUE;

      return Boolean.FALSE;
   }

   /**
    * Obtenir la strategie conjointe a appliquer.
    * 
    * @return la strategie (si existe).
    */
   protected PacmanPropertyStrategy_Abs get_strategy ()
   {
      return _strategy;
   }

   /**
    * Raccourci pour savoir si l'element a une strategie.
    * 
    * @return booleen.
    */
   protected boolean has_strategy ()
   {
      return (null != _strategy);
   }

   /**
    * Retourne l'action qui necessite la reecriture du fichier dans lequel est stockee cette propriete.
    * 
    * @return l'action a effectuer pour le fichier de configuration.
    */
   public PacmanPropertyFileAction_Enum get_fileAction ()
   {
      return _fileAction;
   }

   /**
    * Positionne l'action qui necessite la reecriture du fichier dans lequel est stockee cette propriete.
    * 
    * @param _fileAction :
    *              l'action a effectuer pour le fichier de configuration.
    */
   public void set_fileAction (PacmanPropertyFileAction_Enum _fileAction)
   {
      this._fileAction = _fileAction;
   }
}
