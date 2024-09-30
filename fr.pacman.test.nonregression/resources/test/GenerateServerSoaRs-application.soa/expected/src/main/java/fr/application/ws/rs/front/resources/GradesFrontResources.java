/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.resources;
// Start of user code for imports

import fr.application.ws.api.ref.GradeXto;
import fr.application.ws.rs.front.ApplicationFrontResources_Abs;
import jakarta.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Ressource frontale (client) pour le service : 'grades'.
 * La ressource est montée en singleton (pas d'injection @Singleton) mais 
 * une utilisation "forcée" par getInstance afin d'être certain que la classe 
 * sera bien utilisée en tant que singleton dans l'application cible.
 *
 * @author safr@n.
 */
public final class GradesFrontResources extends ApplicationFrontResources_Abs
{

  /**
   * Pattern holder pour le Singleton (lazy-loading).
   */
  private static class Holder {

    public static final GradesFrontResources c_instance = new GradesFrontResources();
  }

  /**
   * Récupération de l'instance pour la classe de ressources.
   *
   * @return L'instance pour le Singleton.
   */
  public static GradesFrontResources get_instance() {

    return Holder.c_instance ;
  }

  /**
   * Constructeur privé pour la ressource.
   */
  private GradesFrontResources(){

    super("/0.0.1/grades");
  }


  /**
  * 
    * @return grades.    */
  public List<GradeXto> findAllGrades() {

    // for findAllGrades_Grade_grades
    // Start of user code for findAllGrades_Grade_grades

    return target()
      
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<GradeXto>>() {});

    // End of user code
  }


  /**
  * 
    * @return grades.    */
  public List<GradeXto> findAllPagedGrades() {

    // for findAllPagedGrades_Grade_grades
    // Start of user code for findAllPagedGrades_Grade_grades

    return target()
      .path("/paged")
      .request(MediaType.APPLICATION_JSON)
      .get()
      .readEntity(new GenericType<List<GradeXto>>() {});

    // End of user code
  }

}
