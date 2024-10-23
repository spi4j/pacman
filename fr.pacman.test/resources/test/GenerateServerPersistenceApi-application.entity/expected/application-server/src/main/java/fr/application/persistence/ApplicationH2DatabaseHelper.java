/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence;
// Start of user code for imports

import fr.spi4j.persistence.H2DatabaseHelper;

// End of user code

/**
 * Classe utilitaire dinitialisation de la base de données à partir des scripts générés (pour H2 a priori).
 * @author safr@n
 */
public final class ApplicationH2DatabaseHelper extends H2DatabaseHelper
{

   private static final ApplicationH2DatabaseHelper c_instance = new ApplicationH2DatabaseHelper();

   /**
    * Constructeur.
    */
   private ApplicationH2DatabaseHelper ()
   {
      super(ApplicationParamPersistence.getUserPersistence());
   }

   @Override
   protected void init ()
   {
      // Ajout de scripts
      // Start of user code Ajout de scripts
      // Il est possible d'aller chercher les scripts SQL en fonction de leur emplacement par rapport au code source de cette classe, avec le code suivant :
      // (Il faut utiliser autant de fois getParentFile() qu'il n'existe de répertoires entre la racine des sources et la racine du projet)
      // (Exemple : 2 fois getParentFile() pour des sources dans src/main/java)
      // final String v_sqlPath = new File((new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getParentFile().getParentFile()), "src/main/sql/").getPath();
      // addScript(v_sqlPath + "/create_tables_application_H2.sql");
      // addScript(v_sqlPath + "/init_data_application.sql");

      // on ajoute ../application-server pour que les scripts soient visibles de tous les sous-projets


      // application
      addScript("../application-server/src/main/sql/create_tables_application_H2.sql");
      addScript("../application-server/src/main/sql/init_data_application.sql");

      // End of user code
   }

   /**
    * Méthode d'initialisation de la base de données.
    */
   public static void initializeDatabase ()
   {
      c_instance.initDatabase();
   }

   
   // Start of user code Methodes

   // End of user code

}
