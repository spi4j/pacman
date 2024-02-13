package fr.pacman.commons.services;

import org.obeonetwork.dsl.soa.Operation;

public final class CommentsUtils
{

   public static String getRandomUserCodeForOperation (final Operation p_operation, final String p_s)
   {
      StringBuffer v_strBuff = new StringBuffer();

      if (null == p_operation)
         v_strBuff.append("unknow operation");

      if (null != p_operation.getName() && !p_operation.getName().isEmpty())
         v_strBuff.append(p_operation.getName());

      if (null != p_s && !p_s.isEmpty())
         v_strBuff.append("_").append(p_s);

      return v_strBuff.append("_").append(generateRandom(30)).toString();
   }

   private static String generateRandom (int length)
   {
      StringBuffer v_strBuff = new StringBuffer("");
      String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      for (int x = 0; x < length; x++)
      {
         int i = (int) Math.floor(Math.random() * 62);
         v_strBuff.append(chars.charAt(i));
      }
      return v_strBuff.toString();
   }
}
