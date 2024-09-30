/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.servlet;

import jakarta.servlet.ServletConfig;

/**
 * Servlet d'enregistrement des webservices.
 * @author safr@n
 */
public class CXFServlet extends CXFServlet_Abs
{
   private static final long serialVersionUID = 1L;   

   @Override
   public void loadBus (final ServletConfig p_servletConfig)
   {
      super.loadBus(p_servletConfig);
      c_log.info("Deploiement des WebServices");

      // deploiement des WebServices
	 /**
	  * Ajouter le deploiement de services selon l'exemple suivant : 		
      * deployWebService(new MonServiceFacade(), "/MonServiceService");
      */
   }  
}
