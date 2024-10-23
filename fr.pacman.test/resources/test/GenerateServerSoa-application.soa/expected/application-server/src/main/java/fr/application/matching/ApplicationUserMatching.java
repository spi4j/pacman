/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching; // NOPMD
// Start of user code for imports

import fr.application.matching.annuaire.AdresseMatch;
import fr.application.matching.annuaire.AdresseMatch_Itf;
import fr.application.matching.annuaire.CompetenceMatch;
import fr.application.matching.annuaire.CompetenceMatch_Itf;
import fr.application.matching.annuaire.PaysMatch;
import fr.application.matching.annuaire.PaysMatch_Itf;
import fr.application.matching.annuaire.PersonneMatch;
import fr.application.matching.annuaire.PersonneMatch_Itf;
import fr.application.matching.ref.GradeMatch;
import fr.application.matching.ref.GradeMatch_Itf;

// End of user code

/**
 * Factory permettant de récupérer les instances de classes de matching.
 * @author safr@n
*/
public final class ApplicationUserMatching
{
   /**
    * Constructeur privé.
    */
   private ApplicationUserMatching ()
   {
      super();
   }
   /**
    * Obtenir la façade de services 'AdresseMatch_Itf'.
    * @return L'instance désirée.
    */
   public static AdresseMatch_Itf getAdresseMatch ()
   {
      return new AdresseMatch();
   }

   /**
    * Obtenir la façade de services 'CompetenceMatch_Itf'.
    * @return L'instance désirée.
    */
   public static CompetenceMatch_Itf getCompetenceMatch ()
   {
      return new CompetenceMatch();
   }

   /**
    * Obtenir la façade de services 'GradeMatch_Itf'.
    * @return L'instance désirée.
    */
   public static GradeMatch_Itf getGradeMatch ()
   {
      return new GradeMatch();
   }

   /**
    * Obtenir la façade de services 'PaysMatch_Itf'.
    * @return L'instance désirée.
    */
   public static PaysMatch_Itf getPaysMatch ()
   {
      return new PaysMatch();
   }

   /**
    * Obtenir la façade de services 'PersonneMatch_Itf'.
    * @return L'instance désirée.
    */
   public static PersonneMatch_Itf getPersonneMatch ()
   {
      return new PersonneMatch();
   }


}
