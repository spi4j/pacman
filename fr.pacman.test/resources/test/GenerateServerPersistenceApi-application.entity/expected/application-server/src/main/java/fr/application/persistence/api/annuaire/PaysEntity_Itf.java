/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.api.ApplicationAutoFieldsEntity_Itf;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type Pays.
 * @author safr@n
 */
public interface PaysEntity_Itf extends ApplicationAutoFieldsEntity_Itf<Long>
{
   // CONSTANTES

   
   // Start of user code Constantes PaysEntity_Itf

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir nom.
    * @return nom.
    */
   String get_nom ();

   /**
    * Affecter nom.
    * @param p_nom
    *           (In)(*) nom.
    */
   void set_nom (final String p_nom);

   /**
    * Obtenir capitale.
    * @return capitale.
    */
   String get_capitale ();

   /**
    * Affecter capitale.
    * @param p_capitale
    *           (In)(*) capitale.
    */
   void set_capitale (final String p_capitale);

   /**
    * Obtenir personne.
    * @return personne.
    */
   Long get_personnePays_id ();

   /**
    * Affecter personne.
    * @param p_personnePays_id
    *           (In) personne.
    */
   void set_personnePays_id (final Long p_personnePays_id);



   
   // Start of user code Methodes PaysEntity_Itf

   // End of user code

}
