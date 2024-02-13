package fr.pacman.commons.convention;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import fr.pacman.commons.properties.PacmanPropertiesManager;

/**
 * Classe de tests sur la Notation Resolution.
 * @author MINARM
 * 
 */
public class NotationResolution_Test
{

   private static final String c_modelPath4Test = "resources/test";

   /**
    * Test de résolution de nom #1
    */
   @Test
   public void testGetNameResolved_CN0 ()
   {
      // Proposer le répertoire suivant:
      PacmanPropertiesManager.initProperties(c_modelPath4Test);
      // Instancier une 'NotationDefinition'
      final NotationDefinition v_NotationDefinition = NotationDefinition.normeLowerAll()
               .appendNorme(NotationDefinition.normeUpperFirst()).appendNorme(NotationDefinition.normeLanguageC());
      final NotationResolution v_NotationResolution = new NotationResolution("p_[" + v_NotationDefinition + "/]_Abs");
      // Affecter la valeur à résoudre (ex : "adresse pro" --> p_adressePro_Abs)
      final String v_result = v_NotationResolution.applyNorme("adresse pro", PacmanPropertiesManager.get_properties());
      assertTrue("p_Adresse_Pro_Abs".equals(v_result) == true, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Test de résolution de nom #2
    */
   @Test
   public void testGetNameResolvedFromString_CN1 ()
   {
      // Proposer le répertoire suivant:
      PacmanPropertiesManager.initProperties(c_modelPath4Test);
      // Instancier une 'NotationDefinition'
      final NotationDefinition v_NotationDefinition = new NotationDefinition("LowerAll.UpperFirst.LanguageC");
      // Demander la notation
      final NotationResolution v_NotationResolution = new NotationResolution("p_[" + v_NotationDefinition + "/]_Abs");
      // Affecter la valeur à résoudre (ex : "adresse pro" --> p_adressePro_Abs)
      final String v_result = v_NotationResolution.applyNorme("adresse pro", PacmanPropertiesManager.get_properties());
      assertTrue("p_Adresse_Pro_Abs".equals(v_result) == true, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Test de résolution de nom #2
    */
   @Test
   public void testReplaceTagsOfProperties_CN0 ()
   {
      // Proposer le répertoire suivant:
      PacmanPropertiesManager.initProperties(c_modelPath4Test);
      // Instancier une 'NotationDefinition'
      final NotationDefinition v_NotationDefinition = new NotationDefinition("LowerAll.UpperFirst.LanguageC");
      // Demander la notation
      final NotationResolution v_NotationResolution = new NotationResolution(
               "{$package}.p_[" + v_NotationDefinition + "/]_Abs");
      // Affecter la valeur à résoudre (ex : "adresse pro" --> p_adressePro_Abs)
      final String v_result = v_NotationResolution.applyNorme("adresse pro", PacmanPropertiesManager.get_properties());
      assertEquals("fr.appwhite1.p_Adresse_Pro_Abs", v_result, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Test de résolution de nom #2
    */
   @Test
   public void testReadProperties_CN0 ()
   {
      // Proposer le répertoire suivant:
      PacmanPropertiesManager.initProperties(c_modelPath4Test);
      // Instancier une 'NotationDefinition'
      final NotationDefinition v_NotationDefinition = new NotationDefinition("LowerAll.UpperFirst.LanguageC");
      // Demander la notation
      final NotationResolution v_NotationResolution = new NotationResolution(
               "{$package}.p_[" + v_NotationDefinition + "/]_Abs");
      // Affecter la valeur à résoudre (ex : "adresse pro" --> p_adressePro_Abs)
      final String v_result = v_NotationResolution.applyNorme("adresse pro", PacmanPropertiesManager.get_properties());
      assertEquals("fr.appwhite1.p_Adresse_Pro_Abs", v_result, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Remplacement de tag non récursif (cas simple)
    */
   @Test
   public void replaceTagsOfProperties_CN0 ()
   {
      final Properties v_Properties = new Properties();
      // Ajouter 2 propriétés simples
      v_Properties.put("pays", "France");
      v_Properties.put("ville", "Versailles");
      final String v_value = "Nous sommes à {$ville} en {$pays}";
      // Définir la valeur attendue
      final String v_expected = "Nous sommes à Versailles en France";
      // Appeler la méthode à tester
      final String v_result = NotationResolution.replaceTagsOfProperties(v_value, v_Properties);
      // Vérifier
      assertEquals(v_expected, v_result, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Remplacement de tag récursif (cas complexe).
    */
   @Test
   public void replaceTagsOfProperties_CN1 ()
   {
      final Properties v_Properties = getDataPropertiesComplex4Test();
      final String v_value = "Nous sommes à {$adresse}";
      // Définir la valeur attendue
      final String v_expected = "Nous sommes à Versailles - France";
      // Appeler la méthode à tester
      final String v_result = NotationResolution.replaceTagsOfProperties(v_value, v_Properties);
      // Vérifier
      assertEquals(v_expected, v_result, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Appliquer une norme.
    */
   @Test
   public void applyNorme_CN0 ()
   {
      // Obtenir le properties représentant un paramétrage pour le test.
      final Properties v_Properties = getDataPropertiesComplex4Test();
      // Instancier une notationResolution avec le pattern associé à la norme attribut
      final NotationResolution v_NotationResolution = new NotationResolution(v_Properties.getProperty("normeAttribut"));
      // Affecter la valeur trouvée dans le model
      // Obtenir le resultat de l'application de la norme.
      final String v_result = v_NotationResolution.applyNorme("il fait beau", v_Properties);
      // Définir la valeur attendue
      final String v_expected = "debut_IlFaitBeau_fin";
      // Vérifier
      assertEquals(v_expected, v_result, "Le nom resolu ne correspond pas a l'attendu");
   }

   /**
    * Obtenir un jeu de données complexe.
    * @return la jeu de données complexe.
    */
   private Properties getDataPropertiesComplex4Test ()
   {
      final Properties v_Properties = new Properties();
      v_Properties.put("pays", "France");
      v_Properties.put("ville", "Versailles");
      // Ajouter une propriété complexe : une propriété qui en contient une autre
      v_Properties.put("adresse", "{$ville} - {$pays}");
      v_Properties.put("normeAttribut", "debut_[" + NotationDefinition.normeCamelCase() + "/]_fin");
      return v_Properties;
   }
}
