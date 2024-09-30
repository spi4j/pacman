/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.matching.annuaire;
// Start of user code for imports

import fr.application.business.api.annuaire.AdresseAttributes_Enum;
import fr.application.business.api.annuaire.AdresseDto;
import fr.application.persistence.api.annuaire.AdresseColumns_Enum;
import fr.application.persistence.api.annuaire.AdresseEntity_Itf;
import fr.spi4j.matching.Match_Itf;
import java.util.Date;

// End of user code

/**
 * L'interface définissant le contrat pour le Matcher (= persistance <-> business) sur le type 'Adresse'.
 * @author safr@n
 */
public interface AdresseMatch_Itf extends Match_Itf<Long, AdresseDto, AdresseEntity_Itf, AdresseColumns_Enum>
{

   /**
    * Pour un matching 1 pour 1, retourne la colonne Entity associée à l'attribut DTO.
    * @param p_attribute
    *           l'attribut DTO
    * @return la colonne Entity
    */
   AdresseColumns_Enum getColumn (final AdresseAttributes_Enum p_attribute);

   // AdresseMatch_Itf
   // Start of user code AdresseMatch_Itf

   // End of user code
}
