/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence;
// Start of user code for imports

import fr.spi4j.persistence.H2DatabaseHelper;

// End of user code

/**
 * Classe utilitaire dinitialisation de la base de données à partir des scripts générés (pour H2 a priori).
 * @author safr@n
 */
public final class TestH2DatabaseHelper extends H2DatabaseHelper
{

   private static final TestH2DatabaseHelper INSTANCE = new TestH2DatabaseHelper();

   /**
    * Constructeur.
    */
   private TestH2DatabaseHelper ()
   {
      super(TestParamPersistence.getUserPersistence());
   }

   @Override
   protected void init ()
   {
      // Ajout de scripts
      // Start of user code 7cd2ae8152e86a5c0a8e49a1454d2124
      // Il est possible d'aller chercher les scripts SQL en fonction de leur emplacement par rapport au code source de cette classe, avec le code suivant :
      // (Il faut utiliser autant de fois getParentFile() qu'il n'existe de répertoires entre la racine des sources et la racine du projet)
      // (Exemple : 2 fois getParentFile() pour des sources dans src/main/java)
      // final String sqlPath = new File((new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile().getParentFile()), "src/main/sql/").getPath();
      // addScript(sqlPath + "/create_tables_test_H2.sql");
      // addScript(sqlPath + "/init_data_test.sql");

      // on ajoute ../test-server pour que les scripts soient visibles de tous les sous-projets


      // test
      addScript("../test-server/src/main/sql/create_tables_test_H2.sql");
      addScript("../test-server/src/main/sql/init_data_test.sql");

      // End of user code
   }

   /**
    * Méthode d'initialisation de la base de données.
    */
   public static void initializeDatabase ()
   {
      INSTANCE.initDatabase();
   }

   
   // Start of user code 06a25426ca87dd3a2b023b85e8e72986

   // End of user code

}
