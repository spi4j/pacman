/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.application.persistence.dbreferentiel.annuaire;
// Start of user code for imports

import com.opencsv.CSVReader;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.api.annuaire.PaysColumns_Enum;
import fr.application.persistence.api.annuaire.PaysDao_Itf;
import fr.application.persistence.api.annuaire.PaysEntity_Itf;
import fr.application.persistence.api.annuaire.PersonneColumns_Enum;
import fr.application.persistence.api.annuaire.PersonneDao_Itf;
import fr.application.persistence.api.annuaire.PersonneEntity_Itf;
import fr.application.persistence.dbreferentiel.AnnuaireReferentiel;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;
import fr.spi4j.persistence.dao.OperatorLogical_Enum;
import fr.spi4j.persistence.dao.Operator_Enum;
import fr.spi4j.persistence.dao.TableCriteria;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// End of user code

/**
 * Classe de chargement de referentiel pour l entite Pays.
 * @author safr@n
 */
public final class ReferentielEntityPays
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat c_dateFormatCsv = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger c_LOG = LogManager.getLogger( ReferentielEntityPays.class );

    /** Le dao de l'entite Pays */
    private static final PaysDao_Itf c_PaysDao = ApplicationParamPersistence.getUserPersistence().getPaysDao();

    /** Le dao de l'entite Personne */
    private static final PersonneDao_Itf c_PersonneDao = ApplicationParamPersistence.getUserPersistence().getPersonneDao();

    
    // Start of user code Constantes ReferentielEntityPays

    // End of user code


   // ATTRIBUTS

   
   // Start of user code Attributs ReferentielEntityPays

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityPays ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'Pays'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String v_nomFicCsv = "";
      String v_nomFicCsvComplet = AnnuaireReferentiel.getRepCsv() + v_nomFicCsv;
      String v_physicalUnique = "";

      c_LOG.info( "/** DEBUT Import Referentiel Entity Pays **/" );
      c_LOG.info( "Import Referentiel Pays, nom du fichier: " + v_nomFicCsv );
      c_LOG.info( "Import Referentiel Pays, metadata PHYSICAL_UNIQUE: " + v_physicalUnique );
      final String v_FkCriterePersonne = "" ;
      c_LOG.info( "Import Referentiel Pays, Relation de Personne vers Pays, nom du fkCritere: " + v_FkCriterePersonne );

      long v_nbLignesTraitees = 0;
      long v_nbLignesFiltrees = 0;
      long v_nbLignesCreate = 0;
      long v_nbLignesUpdate = 0;
      long v_nbLignesDelete = 0;
      long v_nbLignesSansTraitement = 0;
      Date v_today = new Date();

      try
      {

        /** Exception si le nom de fichier ou la contrainte d'unicite ne sont pas renseignés */
        if ( "".equals ( v_nomFicCsv ) )
        {
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de Pays" );
        }
        if ( "".equals ( v_physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de Pays" );
        }

        /** Initialisation  des noms des FK_CRITERE; exception si ils sont mal renseignés */
        PaysColumns_Enum v_PaysColumnPersonne = null;
        PersonneColumns_Enum v_PersonneColumnPersonne = null;
        if ( ( v_PaysColumnPersonne == null ) || ( v_PersonneColumnPersonne == null ) )
        {
          throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de Personne vers Pays" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader v_csvReader =  new CSVReader (new FileReader( v_nomFicCsvComplet), ';', '"', 1);
        String [] v_csvLine;

   	    
   	    // Start of user code PreTraitement ReferentielEntityPays

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( v_csvLine = v_csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  v_cptrCriteria = 0;
          boolean v_suppressionEnregistrement = false;
          boolean v_filterEnregistrement = false;
          String v_xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'Pays', à partir du critere d unicite '' */
          final TableCriteria <PaysColumns_Enum> v_tableCriteriaPays = new TableCriteria <> ("find by criteria en cherchant sur Pays");
          /** La variable entity renseignée par le contenu du fichier  */
          PaysEntity_Itf v_PaysFichier = ApplicationParamPersistence.getUserPersistence().getPaysEntity();


          /** L'attribut nom	*/
          v_PaysFichier.set_nom( v_csvLine[ PaysColumns_Enum.nom.ordinal() - 1 ] );

          /** L'attribut capitale	*/
          v_PaysFichier.set_capitale( v_csvLine[ PaysColumns_Enum.capitale.ordinal() - 1 ] );



          /** La colonne automatique xdmaj */
          v_PaysFichier.set_xdmaj( new Date() );

          /** La colonne automatique xtopsup */
          v_PaysFichier.set_xtopsup( v_xtopsup );



          /** Chargement de la foreign key de 'Pays' vers 'Personne', à partir du fkCritere '' */
          final TableCriteria <PersonneColumns_Enum> v_tableCriteriaPersonne = new TableCriteria <> ("find by criteria en cherchant sur Personne");
          v_tableCriteriaPersonne.addOrderByDesc( PersonneColumns_Enum.dateDeDebut );
          v_tableCriteriaPersonne.addCriteria( v_PersonneColumnPersonne, Operator_Enum.equals, v_csvLine[ v_PaysColumnPersonne.ordinal() - 1 ] );
          final List <PersonneEntity_Itf> v_allPersonne =  c_PersonneDao.findByCriteria( v_tableCriteriaPersonne );
          PersonneEntity_Itf v_entityPersonne = null;
          if ( v_allPersonne.size() > 0)
          {
            v_entityPersonne = v_allPersonne.get( 0 );
          }
          if ( v_entityPersonne != null )
          {
            v_PaysFichier.set_personnePays_id( v_entityPersonne.getId() );
          }


          
          // Start of user code PreSave ReferentielEntityPays

          // End of user code

          /** On recupere les enregistrements en base de l'entite Pays correspondant à la ligne lue */
          final List <PaysEntity_Itf> v_allPays =  c_PaysDao.findByCriteria( v_tableCriteriaPays );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite Pays */
          if (	v_filterEnregistrement )
          {
            v_nbLignesFiltrees ++;
          }
          else
          {
            if ( v_allPays.size() == 0 )
            {
                if ( v_suppressionEnregistrement == false )
                {
                    c_PaysDao.create( v_PaysFichier );
                    v_nbLignesCreate ++;
                }
                else
                {
                    v_nbLignesSansTraitement ++;
                }
            }
            else
            {
                PaysEntity_Itf v_PaysExistant = v_allPays.get(0);
                if ( v_suppressionEnregistrement == true )
                {
                    c_PaysDao.delete( v_PaysExistant );
                    v_nbLignesDelete ++;
                }
                else
                {
                    v_PaysFichier.setId( v_PaysExistant.getId() );
                    c_PaysDao.update( v_PaysFichier );
                    v_nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          v_nbLignesTraitees ++;

          
          // Start of user code PostSave ReferentielEntityPays

          // End of user code

        }
        /** Fermeture du reader Csv */
        v_csvReader.close();
      }
      /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch ( Exception v_exception )
      {
         c_LOG.error ( v_exception );
         AnnuaireReferentiel.setCommitTraitement(false);
      }

      /** Log de fin du traitement */
      c_LOG.info( "Import Referentiel Pays, nombre de ligne(s) traitee(s): " + v_nbLignesTraitees );
      c_LOG.info( "Import Referentiel Pays, nombre de ligne(s) filtree(s): " + v_nbLignesFiltrees );
      c_LOG.info( "Import Referentiel Pays, nombre de ligne(s) create: " + v_nbLignesCreate );
      c_LOG.info( "Import Referentiel Pays, nombre de ligne(s) update: " + v_nbLignesUpdate );
      c_LOG.info( "Import Referentiel Pays, nombre de ligne(s) delete: " + v_nbLignesDelete );
      c_LOG.info( "Import Referentiel Pays, nombre de ligne(s) sans traitement (delete d'un non existant): " + v_nbLignesSansTraitement );
      c_LOG.info( "/** FIN Import Referentiel Entity Pays **/\n" );

      
      // Start of user code PostTraitement ReferentielEntityPays

      // End of user code

   }

   
   // Start of user code Methodes ReferentielEntityPays

   // End of user code


}
