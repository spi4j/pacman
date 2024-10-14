/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.servlet;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.xml.ws.Endpoint;

import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Servlet d'enregistrement des webservices.
 * @author safr@n
 */
public class CXFServlet_Abs extends CXFNonSpringServlet
{
   private static final long serialVersionUID = 1L;   

   protected transient List<Endpoint> _endPoints;

   protected static final Logger c_log = LogManager.getLogger(CXFServlet_Abs.class.getName());
   
   @Override
   protected void loadBus (final ServletConfig p_servletConfig)
   {
      super.loadBus(p_servletConfig);     
      BusFactory.setDefaultBus(getBus());

      _endPoints = new ArrayList<>();
   }

   /**
    * Deploiement d'un WebService.
    * @param p_implementation
    *           l'implementation du WebService
    * @param p_adresse
    *           l'adresse de publication du WebService
    */
   protected void deployWebService (final Object p_implementation, final String p_adresse)
   {
      final Endpoint v_endPoint = Endpoint.create(p_implementation);
      v_endPoint.publish(p_adresse);
      _endPoints.add(v_endPoint);
   }

   @Override
   public void destroy ()
   {
      super.destroy();
      // stop necessaire pour arreter le thread qui n'est d'ailleurs pas un daemon
      // (sinon fuite memoire et en plus Tomcat ne terminerait jamais un shutdown)
      c_log.info("Arret des WebServices");
      for (final Endpoint v_endPoint : _endPoints)
      {
         v_endPoint.stop();
      }
      final boolean v_wait = true;
      getBus().shutdown(v_wait);
   }
}
