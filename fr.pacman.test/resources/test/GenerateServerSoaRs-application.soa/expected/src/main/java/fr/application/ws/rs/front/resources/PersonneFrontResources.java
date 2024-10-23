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
 * Ressource frontale (client) pour le service : 'personne'.
 * La ressource est montée en singleton (pas d'injection @Singleton) mais 
 * une utilisation "forcée" par getInstance afin d'être certain que la classe 
 * sera bien utilisée en tant que singleton dans l'application cible.
 *
 * @author safr@n.
 */
public final class PersonneFrontResources extends ApplicationFrontResources_Abs
{

  /**
   * Pattern holder pour le Singleton (lazy-loading).
   */
  private static class Holder {

    public static final PersonneFrontResources c_instance = new PersonneFrontResources();
  }

  /**
   * Récupération de l'instance pour la classe de ressources.
   *
   * @return L'instance pour le Singleton.
   */
  public static PersonneFrontResources get_instance() {

    return Holder.c_instance ;
  }

  /**
   * Constructeur privé pour la ressource.
   */
  private PersonneFrontResources(){

    super("/personnes");
  }


  /**
  * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
  public PersonneXto findPersonneById(final Long p_identifiant) {

    
    // Start of user code for findPersonneById_Personne_personne_Long_identifiant

    return target()
      
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(PersonneXto.class);

    // End of user code
  }


  /**
  * 
    * @param p_nom
    *           (In)(*) nom.
    * @return personne.    */
  public PersonneXto findPersonneByName(final String p_nom) {

    
    // Start of user code for findPersonneByName_Personne_personne_String_nom

    return target()
      
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(PersonneXto.class);

    // End of user code
  }


  /**
  * 
    * @param p_identifiant
    *           (In)(*) identifiant.
    * @return personne.    */
  public PersonneXto findPersonneByIdFromRest(final Long p_identifiant) {

    
    // Start of user code for findPersonneByIdFromRest_Personne_personne_Long_identifiant

    return target()
      .path("/{id}")
      .resolveTemplate("identifiant", p_identifiant)
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(PersonneXto.class);

    // End of user code
  }

}
