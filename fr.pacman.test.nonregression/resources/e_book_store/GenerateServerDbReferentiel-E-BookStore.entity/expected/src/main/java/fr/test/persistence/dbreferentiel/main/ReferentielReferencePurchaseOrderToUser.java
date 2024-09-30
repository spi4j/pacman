/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.test.persistence.dbreferentiel.main;

// Start of user code for imports

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.spi4j.persistence.dao.Operator_Enum;
import fr.spi4j.persistence.dao.TableCriteria;

import fr.spi4j.exception.Spi4jValidationException;

import fr.test.persistence.dbreferentiel.MainReferentiel;
import fr.test.persistence.TestParamPersistence;

import fr.test.persistence.dbreferentiel.ReferentielUtils;

import fr.test.persistence.api.main.PurchaseOrderDao;
import fr.test.persistence.api.main.PurchaseOrderEntity;
import fr.test.persistence.api.main.PurchaseOrderColumnsEnum;
import fr.test.persistence.api.main.UserDao;
import fr.test.persistence.api.main.UserEntity;
import fr.test.persistence.api.main.UserColumnsEnum;

// End of user code

/**
 * Classe de maj des foreign key dans la reference PurchaseOrder vers User.
 * @author safr@n
 */
public final class ReferentielReferencePurchaseOrderToUser 
{

	// CONSTANTES

	/** Le formater des dates pour les noms des fichiers d erreur */
	private static final DateFormat DATEFORMATERR = new SimpleDateFormat( "yyyyMMdd" );

	/** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielReferencePurchaseOrderToUser.class );

	/** Le dao de l'entite PurchaseOrder */
    private static final PurchaseOrderDao PURCHASEORDERDAO = TestParamPersistence.getUserPersistence().getPurchaseOrderDao();

	/** Le dao de l'entite User */
    private static final UserDao USERDAO = TestParamPersistence.getUserPersistence().getUserDao();


	// ATTRIBUTS

    // Attributs ReferentielReferencePurchaseOrderToUser
    // Start of user code 5f0afa0982a4bf76e17ee5aee6643828

    // End of user code


	// METHODES  

    /**
     * Constructeur.
     */
	private ReferentielReferencePurchaseOrderToUser ()
	{
	}

   /**
    * Maj des relations de PurchaseOrder vers  User.
    */
   public static void majFk ()
   {
	  /** Initialisations, Log de début de traitement */	
 	  long  nbLignesTraitees = 0;
	  long  nbLignesFiltrees = 0;
 	  long  nbLignesUpdates = 0;
 	  long  nbLignesCleVide = 0;
 	  long  nbLignesCleNonTrouvee = 0;
	  UserColumnsEnum userColumnPurchaseOrder = null;
	  PurchaseOrderColumnsEnum purchaseOrderColumnPurchaseOrder = null;
	  List <String> listeCleNonTrouvee = new ArrayList <String> ();
	  String nomColFkCritere = "";
      Date today = new Date();
	  String nomFicErreurFk = DATEFORMATERR.format(today) + "_referentielReferencePurchaseOrderToUser.key.err";

	  LOG.info( "/** DEBUT Import Referentiel Relation user, update de PurchaseOrder depuis User **/" );
	  LOG.info( "Import Relation de PurchaseOrder depuis User, nom du fkCritere: " + nomColFkCritere );

   	  // PreTraitement ReferentielReferencePurchaseOrderToUser
   	  // Start of user code f2f66cf9c88aa4d6f03e8ba2027f5fef

   	  // End of user code

	  try
      {	
		/** Traitement uniquement si le Fk Critere est correctement renseigné */
	  	if ( ( userColumnPurchaseOrder != null ) && ( purchaseOrderColumnPurchaseOrder  != null ) )
		{
		  /** On parcourt les PurchaseOrder pour les mettre à jour */
		  final List <PurchaseOrderEntity> allEntitiesToUpdate = PURCHASEORDERDAO.findAll();
          for ( PurchaseOrderEntity entityToUpdate : allEntitiesToUpdate )
		  {
	      	final TableCriteria <UserColumnsEnum> tableCriteriaUser = new TableCriteria <> ( "find by criteria en cherchant sur User" );
    	    /** on applique un order by date desc sur le table criteria pour que la premiere ligne lue soit la plus recente */
			tableCriteriaUser.addOrderByDesc( UserColumnsEnum.dateDeDebut );

			/** on positionne la foreign keys selon l'attribut correspondant au fkcritere*/ 				
			String valueCode = null;

			/**Ajout du critere de recherche sur le champ fkcriteria avant le find byCriteria */
       		tableCriteriaUser.addCriteria( userColumnPurchaseOrder, Operator_Enum.equals, valueCode );


   		  	// PreUpdate ReferentielReferencePurchaseOrderToUser
   		  	// Start of user code 2f2da0bb6c0f941b31ef0746bbf5910f

   		  	// End of user code

          	/** Maj de la foreign key de PurchaseOrder vers  User */
			if ( !"".equals(valueCode) )
			{

	        		final List <UserEntity> listFkUser =  USERDAO.findByCriteria( tableCriteriaUser );		  	
					/** Si on trouve au mois une ligne dans l'entite referencee, on update la fk avec l'id de la l'enregistrement trouve*/
					if ( listFkUser.size() > 0) 
					{
        				entityToUpdate.setUser_id( listFkUser.get(0).getId() );
						PURCHASEORDERDAO.update( entityToUpdate);
						nbLignesUpdates ++;
		  			}
					/** Si on ne trouve pas de ligne dans l'entite referencee, on ajoute la ligne dans la liste de cles non trouvees*/
					else
					{
						listeCleNonTrouvee.add( valueCode + "; " + entityToUpdate );
						nbLignesCleNonTrouvee ++;
					}
			}
			else
			{
				nbLignesCleVide ++;
			}
		    nbLignesTraitees ++;

   		    // PostUpdate ReferentielReferencePurchaseOrderToUser
   		    // Start of user code 62d2491d38f575ec766c0bbac7d00ab0

   		    // End of user code

 		  }
	    }
        else
        {
		  /** Envoi d'une exception si le fkCritere est mal renseigné */	
		  throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de PurchaseOrder vers  User" );
	    }
	  }
	  /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch (Exception  exception)
      {
		LOG.error ( exception );
		MainReferentiel.setCommitTraitement(false);	  
	  }

      /** Affichage des count en fin de traitement*/
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis User,  nombre de relations traitees: " + nbLignesTraitees );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis User,  nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis User,  nombre de relations updatees: " + nbLignesUpdates );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis User,  nombre de relations cle vide: " + nbLignesCleVide );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis User,  nombre de relations cle non trouvee: " + nbLignesCleNonTrouvee );

	  /** Remplissage du fichier des reference non trouvees, affichage dans la log */	
	  if ( listeCleNonTrouvee.size() > 0 )
      {
		 String enteteFichierRejet = "Colonne ; Entite PurchaseOrder";
		 ReferentielUtils.storeList ( enteteFichierRejet ,  listeCleNonTrouvee, MainReferentiel.getRepLogs() + nomFicErreurFk );
		 LOG.error ( "Présence de reference cle non trouvee; Détail dans le fichier " + MainReferentiel.getRepLogs() + nomFicErreurFk );
		 MainReferentiel.setCommitTraitement(false);
	  }		

	  /** Log de fin du traitement */
	  LOG.info( "/** FIN Import Referentiel Relation PurchaseOrder, update de PurchaseOrder depuis User  **/\n" );

   	  // PostTraitement ReferentielReferencePurchaseOrderToUser
   	  // Start of user code d285253dd31775bb17a4ccc8db68a9bd

   	  // End of user code

   }

   // Start of user code Methodes ReferentielReferencePurchaseOrderToUser

   // End of user code

}
