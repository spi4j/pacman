/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.resources;
// Start of user code for imports

import fr.application.ws.api.annuaire.PersonneXto;
import fr.application.ws.rs.front.ApplicationFrontResources_Abs;
import jakarta.ws.rs.core.*;

// End of user code

/**
 * Ressource frontale (client) pour le service : 'personneCxf'.
 * La ressource est montée en singleton (pas d'injection @Singleton) mais 
 * une utilisation "forcée" par getInstance afin d'être certain que la classe 
 * sera bien utilisée en tant que singleton dans l'application cible.
 *
 * @author safr@n.
 */
public final class PersonneCxfFrontResources extends ApplicationFrontResources_Abs
{

  /**
   * Pattern holder pour le Singleton (lazy-loading).
   */
  private static class Holder {

    public static final PersonneCxfFrontResources c_instance = new PersonneCxfFrontResources();
  }

  /**
   * Récupération de l'instance pour la classe de ressources.
   *
   * @return L'instance pour le Singleton.
   */
  public static PersonneCxfFrontResources get_instance() {

    return Holder.c_instance ;
  }

  /**
   * Constructeur privé pour la ressource.
   */
  private PersonneCxfFrontResources(){

    super("/");
  }


  /**
  * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
  public PersonneXto findPersonneByIdFromCxf(final Long p_identifiant) {

    
    // Start of user code for findPersonneByIdFromCxf_Personne_personne_Long_identifiant

    return target()
      
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(PersonneXto.class);

    // End of user code
  }

}
