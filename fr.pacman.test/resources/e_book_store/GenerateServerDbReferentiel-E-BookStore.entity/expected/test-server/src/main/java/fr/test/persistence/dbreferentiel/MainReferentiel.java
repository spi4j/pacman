/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.dbreferentiel;

// Start of user code for imports

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.spi4j.Parameters;

import fr.test.persistence.TestParamPersistence;

import fr.test.persistence.dbreferentiel.main.ReferentielEntityAuthor;
import fr.test.persistence.dbreferentiel.main.ReferentielEntityBook;
import fr.test.persistence.dbreferentiel.main.ReferentielEntityPurchaseOrder;
import fr.test.persistence.dbreferentiel.main.ReferentielEntityShoopingCartLine;
import fr.test.persistence.dbreferentiel.main.ReferentielEntityShoppingCart;
import fr.test.persistence.dbreferentiel.main.ReferentielEntityUser;

import fr.test.persistence.dbreferentiel.main.ReferentielReferencePurchaseOrderToShoppingCart;
import fr.test.persistence.dbreferentiel.main.ReferentielReferencePurchaseOrderToUser;
import fr.test.persistence.dbreferentiel.main.ReferentielReferenceShoopingCartLineToBook;

// End of user code

/**
 * Permet d'effectuer le remplissage référentiel du Namespace 'main'.
 */
public class MainReferentiel
{
    // CONSTANTES
	
	/** Le repertoire des csv, a recuperer via la variable d'environnement test.dbreferentiel.main.repcsv */
	private static final String REPCSV = Parameters.getParameter( "test.dbreferentiel.main.repcsv", "" );

	/** Le repertoire des logs, a recuperer via a recuperer via la variable d'environnement test.dbreferentiel.replogs  */
	private static final String REPLOGS = Parameters.getParameter( "test.dbreferentiel.main.replogs", "" );

	/** Le logger  */
    private static final Logger LOG = LogManager.getLogger( MainReferentiel.class );

   
   // Start of user code 41e43de6927bebe3fa604701b1381294

   // End of user code


	// ATTRIBUTS

	/** Le flag indiquant si on commite le traitement */
	private static boolean commitTraitement = true;

   
   // Start of user code 2f2d069c0f6781cf75ebbc527627f485

   // End of user code


	// METHODES

    /**
    * Obtenir le répertoire où se trouvent les fichiers à importer
    * @return String repCsv
    */
    public static synchronized String getRepCsv ()
    {
    	return REPCSV;
    }

    /**
    * Obtenir le répertoire où se trouvent les fichiers de log
    * @return String repLogs
    */
    public static synchronized String getRepLogs ()
    {
    	return REPLOGS;
    }

    /**
    * Obtenir la valeur du flag indiquant si on commite le traitement
    * @return boolean repCsv
    */
    public static synchronized boolean getCommitTraitement ()
    {
    	return  commitTraitement;
    }

    /**
    * Set le flag indiquant si on commite le traitement
    * @param boolean commitTraitement
    */
    public static synchronized void setCommitTraitement ( boolean commitTraitementValue )
    {
    	 commitTraitement = commitTraitementValue;
    }

   /**
    * Initialisation du chargement. Debute une transaction et logs d information sur les ressources du programme. 
    */
   public static void initTraitement ()
   {
	  LOG.info( "/******* DEBUT de la transaction du chargement dans MainReferentiel  *******/" );
	  LOG.info( "Nom du repertoire des csv (variable d environment test.main.dbreferentiel.repcsv): " + MainReferentiel.getRepCsv() );
	  LOG.info( "Nom du repertoire de log (variable d environment test.main.dbreferentiel.replogs): " + REPLOGS + "\n");

	  TestParamPersistence.getUserPersistence().begin();	 
	  MainReferentiel.setCommitTraitement(true);
   }

   /**
    * Finalisation du chargement. Commit ou rollback une transaction selon l'absence ou la présence d'erreur dans le chargement 
    */
   public static void finalizeTraitement ()
   {
	  if ( MainReferentiel.getCommitTraitement() )
	  {
       	 TestParamPersistence.getUserPersistence().commit();
	  	 LOG.info( "/******* FIN de la transaction du chargement dans MainReferentiel : COMMIT  *******/" );
	  }
	  else
	  {
		TestParamPersistence.getUserPersistence().rollback();
	  	LOG.info( "/******* FIN de la transaction du chargement dans MainReferentiel : ROLLBACK  *******/" );
		ReferentielUtils.sendErrorMail(
			"Erreur dans le chargement de referentiel", 
			"Le chargement du referentiel main a généré des erreurs; le détail des erreurs est dans la log du programme."
		);

	  }
   }


   /**
    * Executable.
    * @param args
    *           arguments
    * @throws Throwable
    *            exception
    */
   public static synchronized void main ( final String[] args ) throws Throwable
   {

	  /** Debut de la transaction d'import du referentiel */
	  initTraitement();

	  /** Import des entites qui ne sont pas referencees par des relations 0..* ou 1..* */
	  ReferentielEntityPurchaseOrder.chargeEntity(); 
	  ReferentielEntityUser.chargeEntity(); 

	  /** Import des entites qui sont referencees par des relations 0..* ou 1..* */
	  ReferentielEntityAuthor.chargeEntity(); 
	  ReferentielEntityBook.chargeEntity(); 
	  ReferentielEntityShoopingCartLine.chargeEntity(); 
	  ReferentielEntityShoppingCart.chargeEntity(); 

	  /** Import des references 0..1 */
	  ReferentielReferencePurchaseOrderToShoppingCart.majFk(); 
	  ReferentielReferencePurchaseOrderToUser.majFk(); 
	  ReferentielReferenceShoopingCartLineToBook.majFk(); 

      /** Fin de la transaction d'import du referentiel */
	  finalizeTraitement();

   }

   
   // Start of user code 115ff4e1179c74b345087e2f21f16b5e

   // End of user code


}
