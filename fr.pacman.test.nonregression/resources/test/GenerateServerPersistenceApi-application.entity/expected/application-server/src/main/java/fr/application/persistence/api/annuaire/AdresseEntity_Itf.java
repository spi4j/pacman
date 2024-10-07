/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api.annuaire;
// Start of user code for imports

import fr.application.persistence.api.ApplicationAutoFieldsEntity_Itf;
import fr.application.types.enums.TypeCompetence_Enum;
import java.util.Date;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type Adresse.
 * @author safr@n
 */
public interface AdresseEntity_Itf extends ApplicationAutoFieldsEntity_Itf<Long>
{
   // CONSTANTES

   // Constantes AdresseEntity_Itf
   // Start of user code Constantes AdresseEntity_Itf

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir rue.
    * @return rue.
    */
   String get_rue ();

   /**
    * Affecter rue.
    * @param p_rue
    *           (In)(*) rue.
    */
   void set_rue (final String p_rue);

   /**
    * Obtenir ville.
    * @return ville.
    */
   String get_ville ();

   /**
    * Affecter ville.
    * @param p_ville
    *           (In)(*) ville.
    */
   void set_ville (final String p_ville);

   /**
    * Obtenir codePostal.
    * @return codePostal.
    */
   String get_codePostal ();

   /**
    * Affecter codePostal.
    * @param p_codePostal
    *           (In)(*) codePostal.
    */
   void set_codePostal (final String p_codePostal);

   /**
    * Obtenir personne.
    * @return personne.
    */
   Long get_personneAdresses_id ();

   /**
    * Affecter personne.
    * @param p_personneAdresses_id
    *           (In) personne.
    */
   void set_personneAdresses_id (final Long p_personneAdresses_id);



   // Methodes AdresseEntity_Itf
   // Start of user code Methodes AdresseEntity_Itf

   // End of user code

}
