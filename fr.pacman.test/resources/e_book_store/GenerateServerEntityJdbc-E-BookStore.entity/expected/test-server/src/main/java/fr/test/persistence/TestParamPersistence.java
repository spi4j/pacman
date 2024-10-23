/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence; // NOPMD
// Start of user code for imports

import java.io.File;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.spi4j.Parameters;
import fr.spi4j.exception.Spi4jRuntimeException;
import fr.spi4j.persistence.ElemResourceManager;
import fr.spi4j.persistence.ParamPersistence_Abs;
import fr.spi4j.persistence.dao.jdbc.DefaultJdbcDao;
import fr.spi4j.persistence.resource.DefaultResourcePhysical;
import fr.spi4j.persistence.resource.ResourcePhysical_Abs;
import fr.spi4j.persistence.resource.ResourceType_Enum;
import fr.spi4j.persistence.resource.jdbc.NonXAJndiResourcePhysical;
import fr.test.TestParamAppli;

// End of user code

/**
 * Implémentation permettant de centraliser le paramétrage de persistance de l'application.
 * @author safr@n
 */
public final class TestParamPersistence extends ParamPersistence_Abs implements TestParamAppli
{
   private static final Logger LOG = LogManager.getLogger(TestParamPersistence.class);

   
   // Start of user code 4d57fb9b72281c3af79b765975ffa597

   /** Chemin de la base H2 (test ou base embarquee). */
   private static final String CHEMINBASEH2 = "./h2/Test";

   // End of user code

   /** Propriété positionnée à "true" (par maven-surefire-plugin) lorsque l'application est lancée en mode h2. */
   private boolean h2Mode = Boolean.FALSE;

   /**
    * Le gestionnaire de connexions comprenant des ResourcePhysique et renvoyant le ResourceManager correspondant au mode de fonctionnement de l'appli.
    */
   private final ElemResourceManager elemResourceManager;

   /**
    * Constructeur.
    */
   public TestParamPersistence ()
   {
      super(IDAPPLI);

      
      // Start of user code 965ae8f837a266a5bbe195669e5490a4

	  // Positionne l'indicateur à "true" si l'application est lancée en mode h2.
      this.h2Mode = checkIfH2Mode();

      // La ressource physique SGBD.
      this.elemResourceManager = new ElemResourceManager(IDAPPLI, createResourcePhysical());


      // End of user code
   }

	/**
	 * Initialise l'indicateur de mode de lancement pour l'application. Soit on part
	 * sur une base H2 dans le cadre des tests unitaires ou d'une base embarquée,
	 * soit on se connecte sur une base externe. Dans le cas d'une base embarquée, 
	 * on force directement l'indicateur et on se contente de le retourner. 
	 */
   private boolean checkIfH2Mode() {
	
      boolean h2Mode;
      try {

         final String withH2Bool = Parameters.getParameter(Parameters.c_h2, Boolean.toString(Boolean.FALSE));
         h2Mode = withH2Bool.equalsIgnoreCase(Boolean.toString(Boolean.TRUE));

	  } catch (final IllegalStateException v_e) {

         h2Mode = Boolean.FALSE;
      }
	  return h2Mode;
   }

   /**
    * Initialiser les instances du paramétrage de la couche persistance.
    */
   private static synchronized void initInstance ()
   {
      TestParamPersistence paramPersistence = (TestParamPersistence) getParamPersistence(IDAPPLI);
      // Si pas d'instance
      if (paramPersistence == null)
      {
         // Instancier 'ParamPersistenceApp'
         paramPersistence = new TestParamPersistence();
         // Mémoriser l'instance 'ParamPersistence'
         setParamPersistence(IDAPPLI, paramPersistence);

         // Instancier un 'TestUserPersistence'
         final TestUserPersistence userPersistence = new TestUserPersistence(paramPersistence);
         // Mémoriser l'instance 'UserPersistence'
         setUserPersistence(IDAPPLI, userPersistence);

         // Initialiser les éléments du paramétrage
         paramPersistence.initElemParamPersistence();
      }
   }

   /**
    * Permet d'obtenir le 'UserPersistence' de l'application.
    * @return Une instance de 'TestUserPersistence'
    */
   public static TestUserPersistence getUserPersistence ()
   {
      return UserPersistenceStaticHolder.USERPERSISTENCE;
   }

   /**
    * Design pattern "Static Holder": Classe pour initialiser au besoin (c'est-à-dire à la première demande)<br>
    * le userPersistence de l'application sans nécessiter d'ajouter "synchronized" sur la méthode static getUserPersistence().<br>
    * Ajouter "synchronized" pourrait devenir une contention car la méthode est static et est appelée très souvent dans l'application.<br>
    * Le Static Holder permet d'initialiser l'attribut en étant automatiquement synchronisé par l'initialisation de la classe dans le ClassLoader.<br>
    */
   private static final class UserPersistenceStaticHolder
   {
      /** Le 'UserPersistence' de l'application. */
      private static final TestUserPersistence USERPERSISTENCE;

      static
      {
         // Initialiser la couche de persistance
         initInstance();
         // Obtenir le 'UserPersistence' de l'application
         USERPERSISTENCE = (TestUserPersistence) getUserPersistence(IDAPPLI);
         // finalise l'initialisation du ParamPersistence
         ((TestParamPersistence) getParamPersistence(IDAPPLI)).afterInit();
      }

      /**
       * Constructeur.
       */
      private UserPersistenceStaticHolder ()
      {
         super();
      }
   }

   @Override
   protected void afterInit ()
   {
      
      // Start of user code 34626b7fbc31c16e0e8cded94e95b6ed

	  // Uniquement si application en mode h2.
	  if(this.h2Mode) {

      	// suppression de l'ancienne base si elle existe encore
      	final File dbFile = new File(CHEMINBASEH2 + ".mv.db").getAbsoluteFile();
      	if (dbFile.exists())
      	{
        	LOG.info("Suppression de la base existante : " + dbFile.getName());
         	if (!dbFile.delete())
         	{
            	LOG.warn("La base existante n'a pas pu être supprimée : " + dbFile.getName());
         	}
      	}
      	try
      	{
			LOG.info("Création de la base (version usine) : " + dbFile.getName());
         	TestH2DatabaseHelper.initializeDatabase();
      	}
      	catch (final Throwable t)
      	{
        	LOG.error("Impossible d'initialiser la base de données, "
                  + "vérifier les scripts d'initialisation de la base de données", t);
      	}
	  }
      // End of user code
   }

   /**
    * Paramétrage de la persistance.
    */
   // CHECKSTYLE:OFF
   @Override
   // CHECKSTYLE:ON
   protected void initElemParamPersistence () // NOPMD
   {
      // Ajout de différentes ressources au ElemResourceManager
      
      // Start of user code 1ad971cb5720d2d0846078e44c24b3d1
      // elemResourceManager.addResourcePhysical(LOCALRESOURCEPHYSICAL, fr.spi4j.persistence.Mode_Enum.nomade);
      // End of user code

      
      // Start of user code 175619c8f7fd0c27037175a557ea69ad
      // Ajouter le paramétrage pour le DAO par défaut
      setDefaultDao(DefaultJdbcDao.class, elemResourceManager);
      // End of user code

      
      // Start of user code 1be3cd2dba4f74efe385666fab8ce1b7
      // Ajouter le paramétrage pour l'entité "fr.test.persistence.api.main.AuthorEntity"
      addElemParamPersistence(fr.test.persistence.api.main.AuthorEntity.class, new ElemParamPersistence(
               fr.test.persistence.jdbc.main.AuthorEntityImpl.class,
               fr.test.persistence.jdbc.main.AuthorDaoImpl.class, elemResourceManager));
      // End of user code
      
      // Start of user code 00b180ba2ab8e21db665f298c146615f
      // Ajouter le paramétrage pour l'entité "fr.test.persistence.api.main.BookEntity"
      addElemParamPersistence(fr.test.persistence.api.main.BookEntity.class, new ElemParamPersistence(
               fr.test.persistence.jdbc.main.BookEntityImpl.class,
               fr.test.persistence.jdbc.main.BookDaoImpl.class, elemResourceManager));
      // End of user code
      
      // Start of user code d01e2ff2b4a26fd526e1489030c4e581
      // Ajouter le paramétrage pour l'entité "fr.test.persistence.api.main.PurchaseOrderEntity"
      addElemParamPersistence(fr.test.persistence.api.main.PurchaseOrderEntity.class, new ElemParamPersistence(
               fr.test.persistence.jdbc.main.PurchaseOrderEntityImpl.class,
               fr.test.persistence.jdbc.main.PurchaseOrderDaoImpl.class, elemResourceManager));
      // End of user code
      
      // Start of user code cd0980fc8a2e2fb5fcc846be9aef767d
      // Ajouter le paramétrage pour l'entité "fr.test.persistence.api.main.ShoopingCartLineEntity"
      addElemParamPersistence(fr.test.persistence.api.main.ShoopingCartLineEntity.class, new ElemParamPersistence(
               fr.test.persistence.jdbc.main.ShoopingCartLineEntityImpl.class,
               fr.test.persistence.jdbc.main.ShoopingCartLineDaoImpl.class, elemResourceManager));
      // End of user code
      
      // Start of user code 7eb5511db668919a0c987882fe9415f6
      // Ajouter le paramétrage pour l'entité "fr.test.persistence.api.main.ShoppingCartEntity"
      addElemParamPersistence(fr.test.persistence.api.main.ShoppingCartEntity.class, new ElemParamPersistence(
               fr.test.persistence.jdbc.main.ShoppingCartEntityImpl.class,
               fr.test.persistence.jdbc.main.ShoppingCartDaoImpl.class, elemResourceManager));
      // End of user code
      
      // Start of user code 7c5860662c563bf4ff72eb19e14446af
      // Ajouter le paramétrage pour l'entité "fr.test.persistence.api.main.UserEntity"
      addElemParamPersistence(fr.test.persistence.api.main.UserEntity.class, new ElemParamPersistence(
               fr.test.persistence.jdbc.main.UserEntityImpl.class,
               fr.test.persistence.jdbc.main.UserDaoImpl.class, elemResourceManager));
      // End of user code
   }

   /**
    * Création de la ressource physique.
    * @return ResourcePhysical_Abs
    */
   private ResourcePhysical_Abs createResourcePhysical ()
   {

	  
	  // Start of user code 10235059f9a015181b43503484b5fd3b

	  final ResourcePhysical_Abs resourcePhysical;
     
      // On regarde si on démarre en mode base h2 embarque ou sur une base externe.
	  if(h2Mode){

			// Chargement de la base H2.
			LOG.info("Démarrage du serveur/application sur base H2.");
			resourcePhysical = new DefaultResourcePhysical("jdbc:h2:" + CHEMINBASEH2, "", "",
                  ResourceType_Enum.ressourceH2NonXA);
	  } else {

			// Chargement de la ressource.
			LOG.info("Démarrage de l'application avec la couche de persistence...");
			resourcePhysical = new DefaultResourcePhysical("jdbc:", "", "", ResourceType_Enum.ressourceDbcpNonXA);
	  }
      return resourcePhysical;
	  // End of user code
   }


   
   // Start of user code 06a25426ca87dd3a2b023b85e8e72986

   // End of user code
}
