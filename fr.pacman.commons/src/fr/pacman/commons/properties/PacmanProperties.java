package fr.pacman.commons.properties;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * Cette classe permet de stocker des proprietes 'enrichies'.
 * 
 * Il s'agit d'un conteneur pour l'ensemble des proprietes.
 * 
 * @author MINARM
 *
 */
public class PacmanProperties
{
   /**
    * Stocke l'ensemble des proprietes sous la forme de 'PacmanProperties'.
    */
   private final Map<String, PacmanProperty> _pacmanProperties;

   /**
    * Stocke les proprietes dediees a la normalisation sous forme de 'properties'.
    */
   private Properties _normeProperties;

   /**
    * Liste des proprietes qui demandent la reecriture des fichiers.
    */
   private List<PacmanProperty> _writeProperties;

   
   /**
    * Constructeur.
    * 
    * @param p_categories
    *           la liste des categories enregistrees.
    */
   protected PacmanProperties (final PacmanPropertiesCategory_Abs[] p_categories)
   {
      _pacmanProperties = new Hashtable<String, PacmanProperty>();
      initPacmanProperties(p_categories);
   }

   /**
    * Initialise les proprietes a partir des categories enregistrees.
    * 
    * @param p_categories
    *           la liste des categories enregistrees.
    */
   private void initPacmanProperties (final PacmanPropertiesCategory_Abs[] p_categories)
   {
      for (PacmanPropertiesCategory_Abs pacmanPropertiesCategory : p_categories)
      {
         for (PacmanProperty v_pacmanProperty : pacmanPropertiesCategory.get_tab_PacmanProperties())
         {
            // Remise a blanc des elements.
            v_pacmanProperty.reset();

            // On insere l'element dans la map de travail.
            _pacmanProperties.put(v_pacmanProperty.get_id(), v_pacmanProperty);
         }
      }
   }

   /**
    * Mise a jour des valeurs par defaut. Il peut aussi y avoir des proprietes rajoutees 
    * par le developpeur dans le fichier ".properties" et qui ne peuvent pas etre connues 
    * en avance. Dans ce cas on les enregistre dans la liste. Pour l'instant les proprietes 
    * additionnelles ne peuvent etre remontees au referentiel, elles sont donc perdues si 
    * supprimees du fichier (tout au moins au niveau des valeurs).
    * 
    * Les strategies sont toujours appliquees en dernier.
    * 
    * @param p_props
    *           la liste des proprietes recuperees des fichiers '.properties'.
    * @return
    */
   protected boolean setProperties (final Properties p_props)
   {
      // Initialisation de la liste des strategies.
      List<PacmanProperty> v_strategies = new ArrayList<PacmanProperty>();

      // Verification rapide sur le parametre d'entree
      if (null == p_props || p_props.size() == 0)
         return Boolean.FALSE;

      // Liste l'ensemble des proprietes lues a partir des fichiers.
      for (String v_key : p_props.stringPropertyNames())
      {         
         // On recupere la propriete Pacman dans la copie du referentiel.
         PacmanProperty v_pacmanProperty = _pacmanProperties.get(v_key);

         // Si propriete trouvee dans le referentiel, on met a jour 
         // le referentiel avec la valeur qui a ete recuperee.
         if (null != v_pacmanProperty)
         {             
            // Ajout dans la liste des strategies a appliquer (si besoin).
            if (v_pacmanProperty.has_strategy())
               v_strategies.add(v_pacmanProperty);

            // On met a jour la propriete avec la valeur recuperee.
            v_pacmanProperty.set_value((String) p_props.getProperty(v_key));
         }
         // Sinon, on rajoute la propriete dans la liste.
         else
         {   
            setAdditionalUserProperty(v_key, p_props.getProperty(v_key));
         }
      }

      // On applique les strategies (toujours en dernier).
      for (PacmanProperty pacmanProperty : v_strategies)
         pacmanProperty.applyStrategy(_pacmanProperties);

      return Boolean.TRUE;
   }

   /**
    * Ajoute une propriete additionnelle dans le referentiel.
    * 
    * @param p_key
    * @param p_value
    */
   protected void setAdditionalUserProperty (final String p_key, final String p_value)
   {
      // Creation d'une nouvelle propriete.
      PacmanProperty v_newProperty = PacmanProperty.newAdditional(p_key, p_value,
               "Champ additionnel, specifique utilisateur.");
      
      // Pas de valeur, la propriete doit etre ecrite dans le fichier maitre.
      v_newProperty.setPropertyFileName("");
      
      // Ajout de la propriete dans la liste (on ne remonte pas jusqu'au 
      // referentiel pour ne pas creer d'adherence).
      _pacmanProperties.put(p_key, v_newProperty);
   }

   /**
    * Verifie l'ensemble des proprietes et demande la reecriture si besoin.
    * Pour l'instant on ne traite pas encore la modification (a voir..).
    */
   protected void checkProperties ()
   {
      for (Entry<String, PacmanProperty> v_entry : _pacmanProperties.entrySet())
      {
         // Proprietes obligatoires non presentes.
         if (v_entry.getValue().get_status() == PacmanPropertyStatus_Enum.REQUIRED)
         {
            addToWriteProperties(v_entry, PacmanPropertyFileAction_Enum.ADD);
         }

         // Proprietes conditionnelles plus utilisees.
         if (v_entry.getValue().get_status() == PacmanPropertyStatus_Enum.STANDBY)
         {
            addToWriteProperties(v_entry, PacmanPropertyFileAction_Enum.REMOVE);
         }
      }
   }
   
   /**
    * Ajoute la propriete a ecrire dans la liste des proprietes qui necessitent la reecriture des fichiers.
    * 
    * @param p_entry : 
    *          la propriete qui necessite la reecriture des fichiers de propriete.
    *         
    * @param p_fileAction :
    *          action a effectuer dans le fichier de configuration.
    */
   private void addToWriteProperties(final Entry<String, PacmanProperty> p_entry, final PacmanPropertyFileAction_Enum p_fileAction) 
   {   
      // Initialise la liste si besoin. 
      if(null == _writeProperties) 
      {
         _writeProperties = new ArrayList<>();
      }
      
      // Renseigne l'action qui necessite la reecriture du fichier.
      p_entry.getValue().set_fileAction(p_fileAction);
      // Ajoute la propriete a la liste.
      _writeProperties.add(p_entry.getValue());
   }

   /**
    * Obtenir la propriete a partir de sa cle.
    * 
    * @param p_key
    *           la cle de la propriete a recuperer.
    * 
    * @return la propriete au format Pacman.
    * 
    * @TODO : gerer les nulls
    */
   protected PacmanProperty get_pacmanProperty (final String p_key)
   {
      return ((PacmanProperty) _pacmanProperties.get(p_key));
   }

   /**
    * Obtenir l'ensemble des proprietes sous format Pacman.
    * 
    * @return la liste des proprietes (format Pacman).
    */
   protected Map<String, PacmanProperty> get_pacmanProperties ()
   {
      return _pacmanProperties;
   }

   /**
    * Obtenir l'ensemble des proprietes liees a la norme.
    * 
    * @return la liste des proprietes.
    */
   protected Properties get_normeProperties ()
   {
      if (null == _normeProperties)
      {
         _normeProperties = new Properties();
         for (Entry<String, PacmanProperty> v_entry : _pacmanProperties.entrySet())
            _normeProperties.put(v_entry.getKey(), v_entry.getValue().get_value());
      }
      return _normeProperties;
   }

   /**
    * Retourne au manager de propriete le liste eventuelle des proprietes 
    * qui necessitent la reecriture des fichiers.
    * 
    * @return la liste des proprietes qui necessitent l reecriture des fichiers.
    */
   protected List<PacmanProperty> get_writeProperties()
   {
      return _writeProperties;
   }
}
