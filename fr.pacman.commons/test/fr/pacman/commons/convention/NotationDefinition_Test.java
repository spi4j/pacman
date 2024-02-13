package fr.pacman.commons.convention;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Classe de test sur les définitions : CamelCase...
 * @author MINARM
 * 
 */
public class NotationDefinition_Test
{

   /**
    * Test en définissant pas de notation particulière.
    */
   @Test
   public void toString_CN0 ()
   {
      final NotationDefinition v_NotationDefinition = new NotationDefinition("");
      // Définir la valeur attendue
      final String v_valAttendue = "";
      // Vérifier
      assertTrue(v_NotationDefinition.toString().equals(v_valAttendue) == true,
               "La valeur de la notation désirée est incorrecte");
   }

   /**
    * Test en définissant pas de notation particulière.
    */
   @Test
   public void toString_CN1 ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeCamelCase()
               .appendNorme(NotationDefinition.normeUpperFirst());
      // Définir la valeur attendue
      final String v_valAttendue = "CamelCase.UpperFirst";
      // Vérifier
      assertTrue(v_NotationDefinition.toString().equals(v_valAttendue) == true,
               "La valeur de la notation désirée est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "CamelCase" (ex : "une valeur" --> "UneValeur").
    */
   @Test
   public void applyNorme_CamelCase ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeCamelCase();
      final String v_valObtenue = v_NotationDefinition.applyNorme("une valeur");
      final String v_valAttendue = "UneValeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "CamelCaseIgnoreFirst" (ex : "une valeur" --> "uneValeur").
    */
   @Test
   public void applyNorme_CamelCaseIgnoreFirst ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeCamelCaseIgnoreFirst();
      final String v_valObtenue = v_NotationDefinition.applyNorme("une valeur");
      final String v_valAttendue = "uneValeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "LanguageC" (ex : "une valeur" --> "une_valeur").
    */
   @Test
   public void applyNorme_LanguageC ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLanguageC();
      final String v_valObtenue = v_NotationDefinition.applyNorme("une valeur");
      final String v_valAttendue = "une_valeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "LowerAll" (ex : "UNe VAleur" --> "une valeur").
    */
   @Test
   public void applyNorme_LowerAll ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLowerAll();
      final String v_valObtenue = v_NotationDefinition.applyNorme("UNe VAleur");
      final String v_valAttendue = "une valeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "LowerFirst" (ex : "UNe VAleur" --> "uNe vAleur").
    */
   @Test
   public void applyNorme_LowerFirst ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLowerFirst();
      final String v_valObtenue = v_NotationDefinition.applyNorme("UNe VAleur");
      final String v_valAttendue = "uNe vAleur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "UpperAll" (ex : "UNe VAleur" --> "UNE VALEUR").
    */
   @Test
   public void applyNorme_UpperAll ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeUpperAll();
      final String v_valObtenue = v_NotationDefinition.applyNorme("UNe VAleur");
      final String v_valAttendue = "UNE VALEUR";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "UpperFirst" (ex : "uNe vaLEur" --> "UNe VaLEur").
    */
   @Test
   public void applyNorme_UpperFirst ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeUpperFirst();
      final String v_valObtenue = v_NotationDefinition.applyNorme("uNe vaLEur");
      final String v_valAttendue = "UNe VaLEur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "LanguageC.LowerFirst" (ex : "UNe VALeur" --> "uNe_VALeur").
    */
   @Test
   public void applyNorme_LanguageC_LowerFirst ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLanguageC()
               .appendNorme(NotationDefinition.normeLowerFirst());
      final String v_valObtenue = v_NotationDefinition.applyNorme("UNe VALeur");
      final String v_valAttendue = "uNe_VALeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "LowerFirst.LanguageC" (ex : "UNe VALeur" --> "uNe_vALeur").
    */
   @Test
   public void applyNorme_LowerFirst_LanguageC ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLowerFirst()
               .appendNorme(NotationDefinition.normeLanguageC());
      final String v_valObtenue = v_NotationDefinition.applyNorme("UNe VALeur");
      final String v_valAttendue = "uNe_vALeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }

   /**
    * Test d'obtention de la valeur résolue "LowerFirst.LanguageC" (ex : "UNe VALeur" --> "uNe_vALeur").
    */
   @Test
   public void applyNorme_LowerAll_UpperFirst_LanguageC ()
   {
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLowerAll()
               .appendNorme(NotationDefinition.normeUpperFirst()).appendNorme(NotationDefinition.normeLanguageC());
      final String v_valObtenue = v_NotationDefinition.applyNorme("UNe VALeur");
      final String v_valAttendue = "Une_Valeur";
      // Vérifier
      assertTrue(v_valAttendue.equals(v_valObtenue) == true, "La valeur obtenue est incorrecte");
   }
}
