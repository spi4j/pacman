package fr.pacman.commons.services;

import java.util.UUID;

/**
 * Classe utilitaire de génération d'UUID.
 * 
 * @author MINARM
 */
public final class UuidUtils
{
   /**
    * Constructeur privé pour classe finale.
    */
   private UuidUtils ()
   {
      super();
   }

   /**
    * Génère un UUID à partir d'un nom.
    * 
    * @param p_s
    *           le nom
    * @return l'UUID du nom
    */
   public static String name (final String p_s)
   {
      return UUID.nameUUIDFromBytes(p_s.getBytes()).toString();
   }

   /**
    * @return un UUID aléatoire
    */
   public static String unique (Object o)
   {
      return UUID.randomUUID().toString();
   }

}
