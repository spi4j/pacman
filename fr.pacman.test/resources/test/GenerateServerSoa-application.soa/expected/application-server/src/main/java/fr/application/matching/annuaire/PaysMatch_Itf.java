/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.PaysAttributes_Enum;
import fr.application.business.api.annuaire.PaysDto;
import fr.application.persistence.api.annuaire.PaysColumns_Enum;
import fr.application.persistence.api.annuaire.PaysEntity_Itf;
import fr.spi4j.matching.Match_Itf;

// End of user code

/**
 * L'interface définissant le contrat pour le Matcher (= persistance <-> business) sur le type 'Pays'.
 * @author safr@n
 */
public interface PaysMatch_Itf extends Match_Itf<Long, PaysDto, PaysEntity_Itf, PaysColumns_Enum>
{

   /**
    * Pour un matching 1 pour 1, retourne la colonne Entity associée à l'attribut DTO.
    * @param p_attribute
    *           l'attribut DTO
    * @return la colonne Entity
    */
   PaysColumns_Enum getColumn (final PaysAttributes_Enum p_attribute);

   
   // Start of user code PaysMatch_Itf

   // End of user code
}
