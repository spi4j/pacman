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

import fr.application.persistence.dbreferentiel.annuaire.ReferentielEntityCompetence;
import fr.application.persistence.dbreferentiel.annuaire.ReferentielEntityPersonne;
import fr.application.persistence.dbreferentiel.annuaire.ReferentielEntityAdresse;
import fr.application.persistence.dbreferentiel.annuaire.ReferentielEntityPays;

import fr.application.persistence.dbreferentiel.annuaire.ReferentielReferencePersonneToGrade;
import fr.application.persistence.dbreferentiel.annuaire.ReferentielReferencePersonneToPersonne;

// End of user code

/**
 * Permet d'effectuer le remplissage référentiel du Namespace 'annuaire'.
 */
public class AnnuaireReferentiel
{
    // CONSTANTES
	
	/** Le repertoire des csv, a recuperer via la variable d'environnement application.dbreferentiel.annuaire.repcsv */
	private static final String c_repCsv = Parameters.getParameter( "application.dbreferentiel.annuaire.repcsv", "" );

	/** Le repertoire des logs, a recuperer via a recuperer via la variable d'environnement application.dbreferentiel.replogs  */
	private static final String c_repLogs = Parameters.getParameter( "application.dbreferentiel.annuaire.replogs", "" );

	/** Le logger  */
    private static final Logger c_LOG = LogManager.getLogger( AnnuaireReferentiel.class );

   
   // Start of user code Constantes AnnuaireReferentiel

   // End of user code


	// ATTRIBUTS

	/** Le flag indiquant si on commite le traitement */
	private static boolean commitTraitement = true;

   
   // Start of user code Attributs AnnuaireReferentiel

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
	  c_LOG.info( "/******* DEBUT de la transaction du chargement dans AnnuaireReferentiel  *******/" );
	  c_LOG.info( "Nom du repertoire des csv (variable d environment application.annuaire.dbreferentiel.repcsv): " + AnnuaireReferentiel.getRepCsv() );
	  c_LOG.info( "Nom du repertoire de log (variable d environment application.annuaire.dbreferentiel.replogs): " + c_repLogs + "\n");

	  ApplicationParamPersistence.getUserPersistence().begin();	 
	  AnnuaireReferentiel.setCommitTraitement(true);
   }

   /**
    * Finalisation du chargement. Commit ou rollback une transaction selon l'absence ou la présence d'erreur dans le chargement 
    */
   public static void finalizeTraitement ()
   {
	  if ( AnnuaireReferentiel.getCommitTraitement() )
	  {
       	 ApplicationParamPersistence.getUserPersistence().commit();
	  	 c_LOG.info( "/******* FIN de la transaction du chargement dans AnnuaireReferentiel : COMMIT  *******/" );
	  }
	  else
	  {
		ApplicationParamPersistence.getUserPersistence().rollback();
	  	c_LOG.info( "/******* FIN de la transaction du chargement dans AnnuaireReferentiel : ROLLBACK  *******/" );
		ReferentielUtils.sendErrorMail(
			"Erreur dans le chargement de referentiel", 
			"Le chargement du referentiel annuaire a généré des erreurs; le détail des erreurs est dans la log du programme."
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
	  ReferentielEntityCompetence.chargeEntity(); 

	  /** Import des entites qui sont referencees par des relations 0..* ou 1..* */
	  ReferentielEntityPersonne.chargeEntity(); 
	  ReferentielEntityAdresse.chargeEntity(); 
	  ReferentielEntityPays.chargeEntity(); 

	  /** Import des references 0..1 */
	  ReferentielReferencePersonneToGrade.majFk(); 
	  ReferentielReferencePersonneToPersonne.majFk(); 

      /** Fin de la transaction d'import du referentiel */
	  finalizeTraitement();

   }

   
   // Start of user code Methodes AnnuaireReferentiel

   // End of user code


}
