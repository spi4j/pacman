package fr.pacman.entity.api.sql;

import org.obeonetwork.dsl.entity.Entity;

public class ConstraintUtils
{

   private static int _oldCpt = 0;

   private static int _currentCpt = 1;

   private static Entity _oldEntity;

   private static _ActionEnum _oldAction;

   private enum _ActionEnum
   {
      FK, UN, IDX, CK
   }

   private ConstraintUtils ()
   {
      // RAS.
   }

   /**
    * Visiblement avec Acceleo toute query doit retourner quelque chose ?
    * 
    * @return null
    */
   public static String resetCounterForConstraints (final Boolean p_fullReset)
   {

      if (p_fullReset)
      {
         _oldAction = null;
         _oldEntity = null;
      }
      _currentCpt = 1;
      _oldCpt = 0;
      return null;
   }

   public static int getNextCounterForFK (final Entity p_e, final int p_nbToIncrement)
   {
      return getNextCounterForConstraints(_ActionEnum.FK, p_e, p_nbToIncrement);
   }

   public static int getNextCounterForUN (final Entity p_e, final int p_nbToIncrement)
   {
      return getNextCounterForConstraints(_ActionEnum.IDX, p_e, p_nbToIncrement);
   }

   public static int getNextCounterForIDX (final Entity p_e, final int p_nbToIncrement)
   {
      return getNextCounterForConstraints(_ActionEnum.IDX, p_e, p_nbToIncrement);
   }

   public static int getNextCounterForCK (final Entity p_e)
   {
      return getNextCounterForConstraints(_ActionEnum.CK, p_e, 1);
   }

   /**
    * Gestion des compteurs pour la generation des contraintes.
    * 
    * @param p_e
    * @param p_nbRef
    * @return
    */
   private static int getNextCounterForConstraints (final _ActionEnum p_action, final Entity p_e, final int p_nbToIncrement)
   {

      if (_oldAction != p_action)
      {
         resetCounterForConstraints(Boolean.TRUE);
         _oldAction = p_action;
      }

      if (_oldEntity != p_e)
      {
         _oldEntity = p_e;
         resetCounterForConstraints(Boolean.FALSE);
      }

      _oldCpt = _currentCpt;
      _currentCpt += p_nbToIncrement;
      return _oldCpt;
   }

   /**
    * Recupere la liste des colonnes pour la creation d'une contrainte d'unicite 
    * sur une table et renvoi le nombre de colonnes pour pouvoir creer le 
    * bon compteur dans la generation de la contrainte.
    * 
    * @param p_lstColumns
    * @return
    */
   public static int getNbColumnsInUniqueIndex (final String p_lstColumns)
   {
      return p_lstColumns.split(",").length;
   }
}
