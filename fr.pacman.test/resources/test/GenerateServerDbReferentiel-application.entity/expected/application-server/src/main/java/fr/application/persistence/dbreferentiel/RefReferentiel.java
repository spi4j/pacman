/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.dbreferentiel;

// Start of user code for imports

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.spi4j.Parameters;

import fr.application.persistence.ApplicationParamPersistence;

import fr.application.persistence.dbreferentiel.ref.ReferentielEntityGrade;


// End of user code

/**
 * Permet d'effectuer le remplissage référentiel du Namespace 'ref'.
 */
public class RefReferentiel
{
    // CONSTANTES
	
	/** Le repertoire des csv, a recuperer via la variable d'environnement application.dbreferentiel.ref.repcsv */
	private static final String c_repCsv = Parameters.getParameter( "application.dbreferentiel.ref.repcsv", "" );

	/** Le repertoire des logs, a recuperer via a recuperer via la variable d'environnement application.dbreferentiel.replogs  */
	private static final String c_repLogs = Parameters.getParameter( "application.dbreferentiel.ref.replogs", "" );

	/** Le logger  */
    private static final Logger c_LOG = LogManager.getLogger( RefReferentiel.class );

   
   // Start of user code Constantes RefReferentiel

   // End of user code


	// ATTRIBUTS

	/** Le flag indiquant si on commite le traitement */
	private static boolean commitTraitement = true;

   
   // Start of user code Attributs RefReferentiel

   // End of user code


	// METHODES

    /**
    * Obtenir le répertoire où se trouvent les fichiers à importer
    * @return String p_repCsv
    */
    public static synchronized String getRepCsv ()
    {
    	return c_repCsv;
    }

    /**
    * Obtenir le répertoire où se trouvent les fichiers de log
    * @return String p_repLogs
    */
    public static synchronized String getRepLogs ()
    {
    	return c_repLogs;
    }

    /**
    * Obtenir la valeur du flag indiquant si on commite le traitement
    * @return boolean p_repCsv
    */
    public static synchronized boolean getCommitTraitement ()
    {
    	return  commitTraitement;
    }

    /**
    * Set le flag indiquant si on commite le traitement
    * @param boolean p_commitTraitement
    */
    public static synchronized void setCommitTraitement ( boolean p_commitTraitementValue )
    {
    	 commitTraitement = p_commitTraitementValue;
    }

   /**
    * Initialisation du chargement. Debute une transaction et logs d information sur les ressources du programme. 
    */
   public static void initTraitement ()
   {
	  c_LOG.info( "/******* DEBUT de la transaction du chargement dans RefReferentiel  *******/" );
	  c_LOG.info( "Nom du repertoire des csv (variable d environment application.ref.dbreferentiel.repcsv): " + RefReferentiel.getRepCsv() );
	  c_LOG.info( "Nom du repertoire de log (variable d environment application.ref.dbreferentiel.replogs): " + c_repLogs + "\n");

	  ApplicationParamPersistence.getUserPersistence().begin();	 
	  RefReferentiel.setCommitTraitement(true);
   }

   /**
    * Finalisation du chargement. Commit ou rollback une transaction selon l'absence ou la présence d'erreur dans le chargement 
    */
   public static void finalizeTraitement ()
   {
	  if ( RefReferentiel.getCommitTraitement() )
	  {
       	 ApplicationParamPersistence.getUserPersistence().commit();
	  	 c_LOG.info( "/******* FIN de la transaction du chargement dans RefReferentiel : COMMIT  *******/" );
	  }
	  else
	  {
		ApplicationParamPersistence.getUserPersistence().rollback();
	  	c_LOG.info( "/******* FIN de la transaction du chargement dans RefReferentiel : ROLLBACK  *******/" );
		ReferentielUtils.sendErrorMail(
			"Erreur dans le chargement de referentiel", 
			"Le chargement du referentiel ref a généré des erreurs; le détail des erreurs est dans la log du programme."
		);

	  }
   }


   /**
    * Executable.
    * @param p_args
    *           arguments
    * @throws Throwable
    *            exception
    */
   public static synchronized void main ( final String[] p_args ) throws Throwable
   {

	  /** Debut de la transaction d'import du referentiel */
	  initTraitement();

	  /** Import des entites qui ne sont pas referencees par des relations 0..* ou 1..* */
	  ReferentielEntityGrade.chargeEntity(); 

	  /** Import des entites qui sont referencees par des relations 0..* ou 1..* */

	  /** Import des references 0..1 */

      /** Fin de la transaction d'import du referentiel */
	  finalizeTraitement();

   }

   
   // Start of user code Methodes RefReferentiel

   // End of user code


}
