/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application;

import fr.spi4j.admin.BuildInfoServlet;
import fr.spi4j.admin.BuildInfo_Abs;


/**
 * La servlet utilisee pour retourner les informations sur la version deployee de
 * l'application.
 */

public class ApplicationBuildInfoServlet extends BuildInfoServlet
{

   private static final long serialVersionUID = 1L;

   @Override
   protected BuildInfo_Abs getBuildInfo ()
   {
      return ApplicationBuildInfo.get_instance ();
   }

   @Override
   protected String getContent ()
   {
      final StringBuilder v_stringContent = new StringBuilder (super.getContent());
      v_stringContent.append ("Identifiant unique : <b>").append (((ApplicationBuildInfo)getBuildInfo ()).getUniqueId ())
               .append("</b>").append("<br>");
      // build.id pour la date et l'heure du build
      v_stringContent.append ("Build id : <b>").append (((ApplicationBuildInfo) getBuildInfo ()).getBuildId())
               .append("</b>").append ("<br>");
      v_stringContent.append("Version Java : <b>").append (System.getProperty ("java.version")).append ("</b>")
               .append ("<br>");
      return v_stringContent.toString ();
   }
}
