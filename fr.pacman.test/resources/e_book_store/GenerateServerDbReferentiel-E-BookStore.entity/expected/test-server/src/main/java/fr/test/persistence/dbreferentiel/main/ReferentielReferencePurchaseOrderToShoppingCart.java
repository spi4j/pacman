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
import fr.test.persistence.api.main.ShoppingCartDao;
import fr.test.persistence.api.main.ShoppingCartEntity;
import fr.test.persistence.api.main.ShoppingCartColumnsEnum;

// End of user code

/**
 * Classe de maj des foreign key dans la reference PurchaseOrder vers ShoppingCart.
 * @author safr@n
 */
public final class ReferentielReferencePurchaseOrderToShoppingCart 
{

	// CONSTANTES

	/** Le formater des dates pour les noms des fichiers d erreur */
	private static final DateFormat DATEFORMATERR = new SimpleDateFormat( "yyyyMMdd" );

	/** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielReferencePurchaseOrderToShoppingCart.class );

	/** Le dao de l'entite PurchaseOrder */
    private static final PurchaseOrderDao PURCHASEORDERDAO = TestParamPersistence.getUserPersistence().getPurchaseOrderDao();

	/** Le dao de l'entite ShoppingCart */
    private static final ShoppingCartDao SHOPPINGCARTDAO = TestParamPersistence.getUserPersistence().getShoppingCartDao();


	// ATTRIBUTS

    
    // Start of user code e4446812afe5d6d71060b8dec489b67f

    // End of user code


	// METHODES  

    /**
     * Constructeur.
     */
	private ReferentielReferencePurchaseOrderToShoppingCart ()
	{
	}

   /**
    * Maj des relations de PurchaseOrder vers  ShoppingCart.
    */
   public static void majFk ()
   {
	  /** Initialisations, Log de début de traitement */	
 	  long  nbLignesTraitees = 0;
	  long  nbLignesFiltrees = 0;
 	  long  nbLignesUpdates = 0;
 	  long  nbLignesCleVide = 0;
 	  long  nbLignesCleNonTrouvee = 0;
	  ShoppingCartColumnsEnum shoppingCartColumnPurchaseOrder = null;
	  PurchaseOrderColumnsEnum purchaseOrderColumnPurchaseOrder = null;
	  List <String> listeCleNonTrouvee = new ArrayList <String> ();
	  String nomColFkCritere = "";
      Date today = new Date();
	  String nomFicErreurFk = DATEFORMATERR.format(today) + "_referentielReferencePurchaseOrderToShoppingCart.key.err";

	  LOG.info( "/** DEBUT Import Referentiel Relation shoppingCart, update de PurchaseOrder depuis ShoppingCart **/" );
	  LOG.info( "Import Relation de PurchaseOrder depuis ShoppingCart, nom du fkCritere: " + nomColFkCritere );

   	  
   	  // Start of user code 8a694a662a2e9e7d3e39fc1f1661e550

   	  // End of user code

	  try
      {	
		/** Traitement uniquement si le Fk Critere est correctement renseigné */
	  	if ( ( shoppingCartColumnPurchaseOrder != null ) && ( purchaseOrderColumnPurchaseOrder  != null ) )
		{
		  /** On parcourt les PurchaseOrder pour les mettre à jour */
		  final List <PurchaseOrderEntity> allEntitiesToUpdate = PURCHASEORDERDAO.findAll();
          for ( PurchaseOrderEntity entityToUpdate : allEntitiesToUpdate )
		  {
	      	final TableCriteria <ShoppingCartColumnsEnum> tableCriteriaShoppingCart = new TableCriteria <> ( "find by criteria en cherchant sur ShoppingCart" );
    	    /** on applique un order by date desc sur le table criteria pour que la premiere ligne lue soit la plus recente */
			tableCriteriaShoppingCart.addOrderByDesc( ShoppingCartColumnsEnum.dateDeDebut );

			/** on positionne la foreign keys selon l'attribut correspondant au fkcritere*/ 				
			String valueCode = null;

			/**Ajout du critere de recherche sur le champ fkcriteria avant le find byCriteria */
       		tableCriteriaShoppingCart.addCriteria( shoppingCartColumnPurchaseOrder, Operator_Enum.equals, valueCode );


   		  	
   		  	// Start of user code 617e100357c784946337c53b177feeb4

   		  	// End of user code

          	/** Maj de la foreign key de PurchaseOrder vers  ShoppingCart */
			if ( !"".equals(valueCode) )
			{

	        		final List <ShoppingCartEntity> listFkShoppingCart =  SHOPPINGCARTDAO.findByCriteria( tableCriteriaShoppingCart );		  	
					/** Si on trouve au mois une ligne dans l'entite referencee, on update la fk avec l'id de la l'enregistrement trouve*/
					if ( listFkShoppingCart.size() > 0) 
					{
        				entityToUpdate.setShoppingCart_id( listFkShoppingCart.get(0).getId() );
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

   		    
   		    // Start of user code a80c3dbe25fee96eeec26693a92bef39

   		    // End of user code

 		  }
	    }
        else
        {
		  /** Envoi d'une exception si le fkCritere est mal renseigné */	
		  throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de PurchaseOrder vers  ShoppingCart" );
	    }
	  }
	  /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch (Exception  exception)
      {
		LOG.error ( exception );
		MainReferentiel.setCommitTraitement(false);	  
	  }

      /** Affichage des count en fin de traitement*/
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis ShoppingCart,  nombre de relations traitees: " + nbLignesTraitees );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis ShoppingCart,  nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis ShoppingCart,  nombre de relations updatees: " + nbLignesUpdates );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis ShoppingCart,  nombre de relations cle vide: " + nbLignesCleVide );
	  LOG.info( "Import Relation PurchaseOrder, update de PurchaseOrder depuis ShoppingCart,  nombre de relations cle non trouvee: " + nbLignesCleNonTrouvee );

	  /** Remplissage du fichier des reference non trouvees, affichage dans la log */	
	  if ( listeCleNonTrouvee.size() > 0 )
      {
		 String enteteFichierRejet = "Colonne ; Entite PurchaseOrder";
		 ReferentielUtils.storeList ( enteteFichierRejet ,  listeCleNonTrouvee, MainReferentiel.getRepLogs() + nomFicErreurFk );
		 LOG.error ( "Présence de reference cle non trouvee; Détail dans le fichier " + MainReferentiel.getRepLogs() + nomFicErreurFk );
		 MainReferentiel.setCommitTraitement(false);
	  }		

	  /** Log de fin du traitement */
	  LOG.info( "/** FIN Import Referentiel Relation PurchaseOrder, update de PurchaseOrder depuis ShoppingCart  **/\n" );

   	  
   	  // Start of user code c285181d92446217811bf3cd7decb7ac

   	  // End of user code

   }

   
   // Start of user code Methodes ReferentielReferencePurchaseOrderToShoppingCart

   // End of user code

}
