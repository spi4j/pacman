/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test;

import fr.spi4j.admin.BuildInfoServlet;
import fr.spi4j.admin.BuildInfo_Abs;


/**
 * La servlet utilisee pour retourner les informations sur la version deployee de
 * l'application.
 */

public class TestBuildInfoServlet extends BuildInfoServlet
{

   private static final long serialVersionUID = 1L;

   @Override
   protected BuildInfo_Abs getBuildInfo ()
   {
      return TestBuildInfo.getInstance ();
   }

   @Override
   protected String getContent ()
   {
      final StringBuilder stringContent = new StringBuilder (super.getContent());
      stringContent.append ("Identifiant unique : <b>").append (((TestBuildInfo)getBuildInfo ()).getUniqueId ())
               .append("</b>").append("<br>");
      // build.id pour la date et l'heure du build
      stringContent.append ("Build id : <b>").append (((TestBuildInfo) getBuildInfo ()).getBuildId())
               .append("</b>").append ("<br>");
      stringContent.append("Version Java : <b>").append (System.getProperty ("java.version")).append ("</b>")
               .append ("<br>");
      return stringContent.toString ();
   }
}
