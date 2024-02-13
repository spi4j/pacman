package fr.pacman.commons.convention.rule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.pacman.commons.convention.ApplyNorme;

/**
 * Classe de test des règles sur les Classes.
 * @author MINARM
 *
 */
public class ClassRule_Test
{

   /**
    * Test pour la norme d'une 'classAbstrac'.
    */
   @Test
   public void applyNorme_classAbstract_CN1 ()
   {
      // Obtenir la valeur en appliquant la norme pour une 'classAbstract'
      final String v_valueActual = ApplyNorme.norme_classAbstract("Imputation de gestion");
      // Définir la valeur attendue
      final String v_valueExpected = "ImputationDeGestion_Abs";
      // Vérifier
      assertEquals(v_valueExpected, v_valueActual, "La valeur de la norme attendue est incorrecte");
   }

   /**
    * Test pour la norme d'une 'classInterface'.
    */
   @Test
   public void applyNorme_classInterface_CN1 ()
   {
      // Obtenir la valeur en appliquant la norme pour une 'classInterface'
      final String v_valueActual = ApplyNorme.norme_classInterface("Imputation de gestion");
      // Définir la valeur attendue
      final String v_valueExpected = "ImputationDeGestion_Itf";
      // Vérifier
      assertEquals(v_valueExpected, v_valueActual, "La valeur de la norme attendue est incorrecte");
   }

   /**
    * Test pour la norme d'une 'classImplem'.
    */
   @Test
   public void applyNorme_classImplem_CN1 ()
   {
      // Obtenir la valeur en appliquant la norme pour une 'classImplem'
      final String v_valueActual = ApplyNorme.norme_classImplem("Imputation de gestion");
      // Définir la valeur attendue
      final String v_valueExpected = "ImputationDeGestion";
      // Vérifier
      assertEquals(v_valueExpected, v_valueActual, "La valeur de la norme attendue est incorrecte");
   }

   /**
    * Test pour la norme d'une 'classEnum'.
    */
   @Test
   public void applyNorme_classEnum_CN1 ()
   {
      // Obtenir la valeur en appliquant la norme pour une 'classEnum'
      final String v_valueActual = ApplyNorme.norme_classEnum("Imputation de gestion");
      // Définir la valeur attendue
      final String v_valueExpected = "ImputationDeGestion_Enum";
      // Vérifier
      assertEquals(v_valueExpected, v_valueActual, "La valeur de la norme attendue est incorrecte");
   }

}
