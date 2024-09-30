/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.ref;
// Start of user code for imports

import fr.application.business.api.ref.GradeAttributes_Enum;
import fr.application.business.api.ref.GradeDto;
import fr.application.persistence.api.ref.GradeColumns_Enum;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.spi4j.matching.Match_Itf;
import java.util.Date;

// End of user code

/**
 * L'interface définissant le contrat pour le Matcher (= persistance <-> business) sur le type 'Grade'.
 * @author safr@n
 */
public interface GradeMatch_Itf extends Match_Itf<Long, GradeDto, GradeEntity_Itf, GradeColumns_Enum>
{

   /**
    * Pour un matching 1 pour 1, retourne la colonne Entity associée à l'attribut DTO.
    * @param p_attribute
    *           l'attribut DTO
    * @return la colonne Entity
    */
   GradeColumns_Enum getColumn (final GradeAttributes_Enum p_attribute);

   // GradeMatch_Itf
   // Start of user code GradeMatch_Itf

   // End of user code
}
