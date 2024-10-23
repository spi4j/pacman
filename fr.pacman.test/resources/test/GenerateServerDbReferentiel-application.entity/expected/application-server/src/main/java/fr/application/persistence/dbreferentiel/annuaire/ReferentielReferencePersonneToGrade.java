/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.application.persistence.dbreferentiel.annuaire;

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

import fr.application.persistence.dbreferentiel.AnnuaireReferentiel;
import fr.application.persistence.ApplicationParamPersistence;

import fr.application.persistence.dbreferentiel.ReferentielUtils;

import fr.application.persistence.api.annuaire.PersonneDao_Itf;
import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.application.persistence.api.ref.PersonneColumns_Enum;
import fr.application.persistence.api.ref.GradeDao_Itf;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.application.persistence.api.ref.GradeColumns_Enum;

// End of user code

/**
 * Classe de maj des foreign key dans la reference Personne vers Grade.
 * @author safr@n
 */
public final class ReferentielReferencePersonneToGrade 
{

	// CONSTANTES

	/** Le formater des dates pour les noms des fichiers d erreur */
	private static final DateFormat c_dateFormatErr = new SimpleDateFormat( "yyyyMMdd" );

	/** Le logger de la classe */
    private static final Logger c_LOG = LogManager.getLogger( ReferentielReferencePersonneToGrade.class );

	/** Le dao de l'entite Personne */
    private static final PersonneDao_Itf c_PersonneDao = ApplicationParamPersistence.getUserPersistence().getPersonneDao();

	/** Le dao de l'entite Grade */
    private static final GradeDao_Itf c_GradeDao = ApplicationParamPersistence.getUserPersistence().getGradeDao();


	// ATTRIBUTS

    
    // Start of user code Attributs ReferentielReferencePersonneToGrade

    // End of user code


	// METHODES  

    /**
     * Constructeur.
     */
	private ReferentielReferencePersonneToGrade ()
	{
	}

   /**
    * Maj des relations de Personne vers  Grade.
    */
   public static void majFk ()
   {
	  /** Initialisations, Log de début de traitement */	
 	  long  v_nbLignesTraitees = 0;
	  long  v_nbLignesFiltrees = 0;
 	  long  v_nbLignesUpdates = 0;
 	  long  v_nbLignesCleVide = 0;
 	  long  v_nbLignesCleNonTrouvee = 0;
	  GradeColumns_Enum v_GradeColumnPersonne = null;
	  PersonneColumns_Enum v_PersonneColumnPersonne = null;
	  List <String> v_listeCleNonTrouvee = new ArrayList <String> ();
	  String v_nomColFkCritere = "";
      Date v_today = new Date();
	  String v_nomFicErreurFk = c_dateFormatErr.format(v_today) + "_referentielReferencePersonneToGrade.key.err";

	  c_LOG.info( "/** DEBUT Import Referentiel Relation Grade, update de Personne depuis Grade **/" );
	  c_LOG.info( "Import Relation de Personne depuis Grade, nom du fkCritere: " + v_nomColFkCritere );

   	  
   	  // Start of user code PreTraitement ReferentielReferencePersonneToGrade

   	  // End of user code

	  try
      {	
		/** Traitement uniquement si le Fk Critere est correctement renseigné */
	  	if ( ( v_GradeColumnPersonne != null ) && ( v_PersonneColumnPersonne  != null ) )
		{
		  /** On parcourt les Personne pour les mettre à jour */
		  final List <PersonneEntity_Itf> v_allEntitiesToUpdate = c_PersonneDao.findAll();
          for ( PersonneEntity_Itf v_entityToUpdate : v_allEntitiesToUpdate )
		  {
	      	final TableCriteria <GradeColumns_Enum> v_tableCriteriaGrade = new TableCriteria <> ( "find by criteria en cherchant sur Grade" );
    	    /** on applique un order by date desc sur le table criteria pour que la premiere ligne lue soit la plus recente */
			v_tableCriteriaGrade.addOrderByDesc( GradeColumns_Enum.dateDeDebut );

			/** on positionne la foreign keys selon l'attribut correspondant au fkcritere*/ 				
			String v_valueCode = null;

			/**Ajout du critere de recherche sur le champ fkcriteria avant le find byCriteria */
       		v_tableCriteriaGrade.addCriteria( v_GradeColumnPersonne, Operator_Enum.equals, v_valueCode );


   		  	
   		  	// Start of user code PreUpdate ReferentielReferencePersonneToGrade

   		  	// End of user code

          	/** Maj de la foreign key de Personne vers  Grade */
			if ( !"".equals(v_valueCode) )
			{

	        		final List <GradeEntity_Itf> v_listFkGrade =  c_GradeDao.findByCriteria( v_tableCriteriaGrade );		  	
					/** Si on trouve au mois une ligne dans l'entite referencee, on update la fk avec l'id de la l'enregistrement trouve*/
					if ( v_listFkGrade.size() > 0) 
					{
        				v_entityToUpdate.set_Grade_id( v_listFkGrade.get(0).getId() );
						c_PersonneDao.update( v_entityToUpdate);
						v_nbLignesUpdates ++;
		  			}
					/** Si on ne trouve pas de ligne dans l'entite referencee, on ajoute la ligne dans la liste de cles non trouvees*/
					else
					{
						v_listeCleNonTrouvee.add( v_valueCode + "; " + v_entityToUpdate );
						v_nbLignesCleNonTrouvee ++;
					}
			}
			else
			{
				v_nbLignesCleVide ++;
			}
		    v_nbLignesTraitees ++;

   		    
   		    // Start of user code PostUpdate ReferentielReferencePersonneToGrade

   		    // End of user code

 		  }
	    }
        else
        {
		  /** Envoi d'une exception si le fkCritere est mal renseigné */	
		  throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de Personne vers  Grade" );
	    }
	  }
	  /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch (Exception  v_exception)
      {
		c_LOG.error ( v_exception );
		AnnuaireReferentiel.setCommitTraitement(false);	  
	  }

      /** Affichage des count en fin de traitement*/
	  c_LOG.info( "Import Relation Personne, update de Personne depuis Grade,  nombre de relations traitees: " + v_nbLignesTraitees );
	  c_LOG.info( "Import Relation Personne, update de Personne depuis Grade,  nombre de ligne(s) filtree(s): " + v_nbLignesFiltrees );
	  c_LOG.info( "Import Relation Personne, update de Personne depuis Grade,  nombre de relations updatees: " + v_nbLignesUpdates );
	  c_LOG.info( "Import Relation Personne, update de Personne depuis Grade,  nombre de relations cle vide: " + v_nbLignesCleVide );
	  c_LOG.info( "Import Relation Personne, update de Personne depuis Grade,  nombre de relations cle non trouvee: " + v_nbLignesCleNonTrouvee );

	  /** Remplissage du fichier des reference non trouvees, affichage dans la log */	
	  if ( v_listeCleNonTrouvee.size() > 0 )
      {
		 String v_enteteFichierRejet = "Colonne ; Entite Personne";
		 ReferentielUtils.storeList ( v_enteteFichierRejet ,  v_listeCleNonTrouvee, AnnuaireReferentiel.getRepLogs() + v_nomFicErreurFk );
		 c_LOG.error ( "Présence de reference cle non trouvee; Détail dans le fichier " + AnnuaireReferentiel.getRepLogs() + v_nomFicErreurFk );
		 AnnuaireReferentiel.setCommitTraitement(false);
	  }		

	  /** Log de fin du traitement */
	  c_LOG.info( "/** FIN Import Referentiel Relation Personne, update de Personne depuis Grade  **/\n" );

   	  
   	  // Start of user code PostTraitement ReferentielReferencePersonneToGrade

   	  // End of user code

   }

   
   // Start of user code Methodes ReferentielReferencePersonneToGrade

   // End of user code

}
