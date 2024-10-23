/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs;
// Start of user code for imports

import jakarta.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import fr.spi4j.ws.rs.RsDefaultContextResolver;

// End of user code

/**
 * Enregistrement du "context resolver" pour les services REST.
 * @author safr@n.
 */
@Provider
public class ApplicationContextResolver extends RsDefaultContextResolver
{
   /**
    * Constructeur.
    * @throws Exception
    */
   public ApplicationContextResolver  () throws Exception
   {
      // Par defaut, on demande à Jackson de ne pas prendre en compte
      // les annotations JAXB afin de pouvoir bénéficier du même objet XTO,
      // à la fois dans le cadre des services SOAP et REST. Les annotations
      // JAXB sont donc utilisées uniquement dans le cadre de SOAP et les
      // annotations JSON dans le cadre des services REST ce qui permet de
      // traiter le même objet différemment selon qu'il soit utilisé en SOAP
      // ou REST.

      
      // Start of user code ApplicationContextResolver

      super(new JacksonAnnotationIntrospector());

      // End of user code
   }
}
