/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.api.ref;
// Start of user code for imports

import fr.application.persistence.api.ApplicationAutoFieldsEntity_Itf;
import fr.application.types.enums.TypeCompetence_Enum;
import java.util.Date;

// End of user code

/**
 * L'interface définissant le contrat de persistance pour le type Grade.
 * @author safr@n
 */
public interface GradeEntity_Itf extends ApplicationAutoFieldsEntity_Itf<Long>
{
   // CONSTANTES

   // Constantes GradeEntity_Itf
   // Start of user code Constantes GradeEntity_Itf

   // End of user code

   // METHODES ABSTRAITES

   /**
    * Obtenir le libellé du grade.
    * @return Le libellé du grade.
    */
   String get_libelle ();

   /**
    * Affecter le libellé du grade.
    * @param p_libelle
    *           (In)(*) Le libellé du grade.
    */
   void set_libelle (final String p_libelle);

   /**
    * Obtenir le trigramme du grade.
    * @return Le trigramme du grade.
    */
   String get_trigramme ();

   /**
    * Affecter le trigramme du grade.
    * @param p_trigramme
    *           (In)(*) Le trigramme du grade.
    */
   void set_trigramme (final String p_trigramme);


   // Methodes GradeEntity_Itf
   // Start of user code Methodes GradeEntity_Itf

   // End of user code

}
