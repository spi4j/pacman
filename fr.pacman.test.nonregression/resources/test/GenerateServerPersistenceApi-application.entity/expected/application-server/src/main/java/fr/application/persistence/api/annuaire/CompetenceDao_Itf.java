/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api.annuaire;
// Start of user code for imports

import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.spi4j.persistence.dao.Dao_Itf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

// End of user code

/**
 * Interface de services spécifiques pour le DAO Competence.
 * @author safr@n
 */
public interface CompetenceDao_Itf extends Dao_Itf<Long, CompetenceEntity_Itf, CompetenceColumns_Enum>
{
   // CONSTANTES

   // Constantes CompetenceDao_Itf
   // Start of user code Constantes CompetenceDao_Itf

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir la liste d'objets de type 'Personne' associés à l'instance de type 'Competence'.
    * @param p_Competence_id
    *           (In)(*) Competence.
    * @return une liste de PersonneEntity_Itf
    */
   List<PersonneEntity_Itf> findListDisposeByCompetence (final Long p_Competence_id);

   // Methodes CompetenceDao_Itf
   // Start of user code Methodes CompetenceDao_Itf

   // End of user code
}
