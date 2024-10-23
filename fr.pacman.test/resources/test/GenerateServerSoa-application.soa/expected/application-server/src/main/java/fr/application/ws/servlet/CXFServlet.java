/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.servlet;
// Start of user code for imports

import fr.application.ws.impl_server.annuaire.PersonneCxfServiceCXFFacade;
import jakarta.servlet.ServletConfig;

// End of user code

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
      c_log.info("Déploiement des WebServices");
      // Déploiement des WebServices
	 /**
	  * Ajouter le deploiement de services selon l'exemple suivant :
      * deployWebService(new MonServiceFacade(), "/MonServiceService");
      */
	
	// Start of user code PersonneCxfService

	  // Déploiement du Webservice  PersonneCxfService
	  c_log.info("Déploiement du WebService : PersonneCxfService");
	  deployWebService(new PersonneCxfServiceCXFFacade(), "/PersonneCxfService");

	// End of user code

   }
}

