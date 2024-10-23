/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api.annuaire;
// Start of user code for imports

import fr.application.annuaire.TypeCompetence_Enum;
import fr.application.persistence.api.ApplicationAutoFieldsEntity_Itf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type Competence.
 * @author safr@n
 */
public interface CompetenceEntity_Itf extends ApplicationAutoFieldsEntity_Itf<Long>
{
   // CONSTANTES

   
   // Start of user code Constantes CompetenceEntity_Itf

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir le libellé de la compétence.
    * @return Le libellé de la compétence.
    */
   String get_libelle ();

   /**
    * Affecter le libellé de la compétence.
    * @param p_libelle
    *           (In)(*) Le libellé de la compétence.
    */
   void set_libelle (final String p_libelle);

   /**
    * Obtenir typecompetence.
    * @return typecompetence.
    */
   TypeCompetence_Enum get_typecompetence ();

   /**
    * Affecter typecompetence.
    * @param p_typecompetence
    *           (In)(*) typecompetence.
    */
   void set_typecompetence (final TypeCompetence_Enum p_typecompetence);


   
   // Start of user code Methodes CompetenceEntity_Itf

   // End of user code

}
