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

import fr.test.persistence.api.main.ShoopingCartLineDao;
import fr.test.persistence.api.main.ShoopingCartLineEntity;
import fr.test.persistence.api.main.ShoopingCartLineColumnsEnum;
import fr.test.persistence.api.main.BookDao;
import fr.test.persistence.api.main.BookEntity;
import fr.test.persistence.api.main.BookColumnsEnum;

// End of user code

/**
 * Classe de maj des foreign key dans la reference ShoopingCartLine vers Book.
 * @author safr@n
 */
public final class ReferentielReferenceShoopingCartLineToBook 
{

	// CONSTANTES

	/** Le formater des dates pour les noms des fichiers d erreur */
	private static final DateFormat DATEFORMATERR = new SimpleDateFormat( "yyyyMMdd" );

	/** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielReferenceShoopingCartLineToBook.class );

	/** Le dao de l'entite ShoopingCartLine */
    private static final ShoopingCartLineDao SHOOPINGCARTLINEDAO = TestParamPersistence.getUserPersistence().getShoopingCartLineDao();

	/** Le dao de l'entite Book */
    private static final BookDao BOOKDAO = TestParamPersistence.getUserPersistence().getBookDao();


	// ATTRIBUTS

    
    // Start of user code c6ab79160692535e5c2c2d2063ac3ab5

    // End of user code


	// METHODES  

    /**
     * Constructeur.
     */
	private ReferentielReferenceShoopingCartLineToBook ()
	{
	}

   /**
    * Maj des relations de ShoopingCartLine vers  Book.
    */
   public static void majFk ()
   {
	  /** Initialisations, Log de début de traitement */	
 	  long  nbLignesTraitees = 0;
	  long  nbLignesFiltrees = 0;
 	  long  nbLignesUpdates = 0;
 	  long  nbLignesCleVide = 0;
 	  long  nbLignesCleNonTrouvee = 0;
	  BookColumnsEnum bookColumnShoopingCartLine = null;
	  ShoopingCartLineColumnsEnum shoopingCartLineColumnShoopingCartLine = null;
	  List <String> listeCleNonTrouvee = new ArrayList <String> ();
	  String nomColFkCritere = "";
      Date today = new Date();
	  String nomFicErreurFk = DATEFORMATERR.format(today) + "_referentielReferenceShoopingCartLineToBook.key.err";

	  LOG.info( "/** DEBUT Import Referentiel Relation book, update de ShoopingCartLine depuis Book **/" );
	  LOG.info( "Import Relation de ShoopingCartLine depuis Book, nom du fkCritere: " + nomColFkCritere );

   	  
   	  // Start of user code 3408ffba3210bceb29263b3c661df7fc

   	  // End of user code

	  try
      {	
		/** Traitement uniquement si le Fk Critere est correctement renseigné */
	  	if ( ( bookColumnShoopingCartLine != null ) && ( shoopingCartLineColumnShoopingCartLine  != null ) )
		{
		  /** On parcourt les ShoopingCartLine pour les mettre à jour */
		  final List <ShoopingCartLineEntity> allEntitiesToUpdate = SHOOPINGCARTLINEDAO.findAll();
          for ( ShoopingCartLineEntity entityToUpdate : allEntitiesToUpdate )
		  {
	      	final TableCriteria <BookColumnsEnum> tableCriteriaBook = new TableCriteria <> ( "find by criteria en cherchant sur Book" );
    	    /** on applique un order by date desc sur le table criteria pour que la premiere ligne lue soit la plus recente */
			tableCriteriaBook.addOrderByDesc( BookColumnsEnum.dateDeDebut );

			/** on positionne la foreign keys selon l'attribut correspondant au fkcritere*/ 				
			String valueCode = null;

			/**Ajout du critere de recherche sur le champ fkcriteria avant le find byCriteria */
       		tableCriteriaBook.addCriteria( bookColumnShoopingCartLine, Operator_Enum.equals, valueCode );


   		  	
   		  	// Start of user code ac3064e85a5f86f569c2e46d1fcaf921

   		  	// End of user code

          	/** Maj de la foreign key de ShoopingCartLine vers  Book */
			if ( !"".equals(valueCode) )
			{

	        		final List <BookEntity> listFkBook =  BOOKDAO.findByCriteria( tableCriteriaBook );		  	
					/** Si on trouve au mois une ligne dans l'entite referencee, on update la fk avec l'id de la l'enregistrement trouve*/
					if ( listFkBook.size() > 0) 
					{
        				entityToUpdate.setBook_id( listFkBook.get(0).getId() );
						SHOOPINGCARTLINEDAO.update( entityToUpdate);
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

   		    
   		    // Start of user code 97ddc0ab357ead6a29b803b8340d817b

   		    // End of user code

 		  }
	    }
        else
        {
		  /** Envoi d'une exception si le fkCritere est mal renseigné */	
		  throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de ShoopingCartLine vers  Book" );
	    }
	  }
	  /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch (Exception  exception)
      {
		LOG.error ( exception );
		MainReferentiel.setCommitTraitement(false);	  
	  }

      /** Affichage des count en fin de traitement*/
	  LOG.info( "Import Relation ShoopingCartLine, update de ShoopingCartLine depuis Book,  nombre de relations traitees: " + nbLignesTraitees );
	  LOG.info( "Import Relation ShoopingCartLine, update de ShoopingCartLine depuis Book,  nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
	  LOG.info( "Import Relation ShoopingCartLine, update de ShoopingCartLine depuis Book,  nombre de relations updatees: " + nbLignesUpdates );
	  LOG.info( "Import Relation ShoopingCartLine, update de ShoopingCartLine depuis Book,  nombre de relations cle vide: " + nbLignesCleVide );
	  LOG.info( "Import Relation ShoopingCartLine, update de ShoopingCartLine depuis Book,  nombre de relations cle non trouvee: " + nbLignesCleNonTrouvee );

	  /** Remplissage du fichier des reference non trouvees, affichage dans la log */	
	  if ( listeCleNonTrouvee.size() > 0 )
      {
		 String enteteFichierRejet = "Colonne ; Entite ShoopingCartLine";
		 ReferentielUtils.storeList ( enteteFichierRejet ,  listeCleNonTrouvee, MainReferentiel.getRepLogs() + nomFicErreurFk );
		 LOG.error ( "Présence de reference cle non trouvee; Détail dans le fichier " + MainReferentiel.getRepLogs() + nomFicErreurFk );
		 MainReferentiel.setCommitTraitement(false);
	  }		

	  /** Log de fin du traitement */
	  LOG.info( "/** FIN Import Referentiel Relation ShoopingCartLine, update de ShoopingCartLine depuis Book  **/\n" );

   	  
   	  // Start of user code 0565d2c433f3fc4523869af49fbfb0e4

   	  // End of user code

   }

   
   // Start of user code Methodes ReferentielReferenceShoopingCartLineToBook

   // End of user code

}
