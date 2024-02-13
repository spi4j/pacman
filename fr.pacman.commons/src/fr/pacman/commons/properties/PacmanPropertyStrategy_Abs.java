package fr.pacman.commons.properties;

import java.util.Map;

/**
 * Une strategie associee a un element permet de declancher des actions a reception de cet element.
 * 
 * Elle est lancee selon une frequence d'activation (trigger).
 * 
 * ONSTART : La strategie est lancee uniquement au premier set.
 * 
 * ONCHANGE : La strategie est lancee uniquement sur changement.
 * 
 * ONCHAIN : La strategie est lancee uniquement sur chainage.
 * 
 * ALWAYS : La strategie est lancee a chaque set.
 * 
 * @author MINARM
 */
public abstract class PacmanPropertyStrategy_Abs
{
   /**
    * La propriete de reference (qui a declanche la strategie).
    */
   private PacmanProperty _refPacmanProperty;

   /**
    * Ancienne valeur de la propriete de reference.
    */
   private String _refOldValue;

   /**
    * Permet de savoir si la strategie a ete activee au moins une fois.
    */
   private boolean _triggered = Boolean.FALSE;

   /**
    * Verifie si il faut lancer la strategie pour cette propriete. Pour l'instant ONCHAIN n'est pas encore implemente...
    * 
    * @return oui / non.
    */
   private boolean canTriggerStrategy ()
   {
      if (PacmanPropertyTrigger_Enum.ALWAYS == get_strategyTrigger())
         return Boolean.TRUE;

      if ((PacmanPropertyTrigger_Enum.ONSTART == get_strategyTrigger()
               || PacmanPropertyTrigger_Enum.ONCREATE_CHANGE == get_strategyTrigger()) && !_triggered)
         return Boolean.TRUE;

      if ((PacmanPropertyTrigger_Enum.ONCHANGE == get_strategyTrigger()
               || PacmanPropertyTrigger_Enum.ONCREATE_CHANGE == get_strategyTrigger())
               && !_refPacmanProperty.get_value().equals(_refOldValue))
         return Boolean.TRUE;

      return Boolean.FALSE;
   }

   /**
    * Met a jour la valeur de la propriete de reference a partir des '.properties'.
    * 
    * Demande le lancement eventuel de la strategie.
    * 
    * @param p_properties
    *           la liste des proprietes chargees.
    */
   void applyStrategy (final Map<String, PacmanProperty> p_pacmanProperties)
   {
      // Verifie si on peux appliquer la strategie.
      if (canTriggerStrategy())
      {
         // On applique la strategie liee (si besoin).
         applyChainedStrategy(p_pacmanProperties);

         // On applique la strategie.
         doStrategy(p_pacmanProperties);

         // On positionne les variables pour
         // le bon fonctionnement de la strategie.
         _refOldValue = _refPacmanProperty.get_value();
         _triggered = Boolean.TRUE;
      }
   }

   /**
    * Dans le cas ou la strategie est basee sur un trigger de type 
    * ONCHANGE, on initialise de suite la valeur au premier demarrage 
    * du generateur afin justement de pouvoir comparer correctement les 
    * valeurs. UNIQUEMENT SI LA VALEUR EST NULLE (donc _triggered = false)!
    * 
    * @param p_value
    *           : la valeur de la propriete.
    */
   void updateWithOldRefValue (final String p_value)
   {
      if (PacmanPropertyTrigger_Enum.ONCHANGE == get_strategyTrigger() && !_triggered)
      {
         _refOldValue = p_value;
         _triggered = Boolean.TRUE;
      }
   }

   /**
    * Ce cas devrait etre rare mais il est possible de chainer les strategies.
    * 
    * @param p_pacmanProperties
    *           la liste des proprietes.
    * @return
    */
   private void applyChainedStrategy (final Map<String, PacmanProperty> p_pacmanProperties)
   {
      if (null == getChainedStrategy())
         return;

      // On recupere la propriete demandee.
      PacmanProperty v_pacmanProperty = p_pacmanProperties.get(getChainedStrategy());

      // On applique directement la strategie (bypass de la frequence initiale).
      v_pacmanProperty.get_strategy().doStrategy(p_pacmanProperties);
   }

   /**
    * Strategie a appliquer pour la propriete de reference.
    * 
    * @param p_properties
    *           la liste des proprietes chargees.
    */
   protected abstract void doStrategy (final Map<String, PacmanProperty> p_pacmanProperties);

   /**
    * Permet d'appliquer la strategie de mise a jour pour l'element.
    * 
    * @param p_pacmanProperty
    *           la propriete a mettre a jour.
    */
   protected abstract void updateProperty (final PacmanProperty p_pacmanProperty);

   /**
    * Recupere la frequence d'activation de la strategie.
    * 
    * @return la demande d'activation pour la strategie.
    */
   protected abstract PacmanPropertyTrigger_Enum get_strategyTrigger ();

   /**
    * Obtenir la cle de la propriete qui declanche la strategie.
    * 
    * @return _refId la cle pour l'element maitre (reference).
    */
   protected String get_refId ()
   {
      return _refPacmanProperty.get_id();
   }

   /**
    * Obtenir la valeur de la propriete qui declanche la strategie.
    * 
    * @return _refValue la valeur pour l'element maitre (reference).
    */
   protected String get_refValue ()
   {
      return _refPacmanProperty.get_value();
   }

   /**
    * Positionne la propriete pour la mettre a disposition dans la methode doStrategy.
    * 
    * @param _refPacmanProperty
    *           la propriete de reference.
    */
   protected void set_refPacmanProperty (PacmanProperty _refPacmanProperty)
   {
      this._refPacmanProperty = _refPacmanProperty;
   }

   /**
    * Obtenir la cle de l'element pour appliquer sa strategie.
    * 
    * Pour mettre en place une strategie chainee :
    * 
    * implementer cette methode dans la strategie de l'element de reference.
    * 
    * @return la cle de l'element.
    */
   protected String getChainedStrategy ()
   {
      return null;
   }
}
