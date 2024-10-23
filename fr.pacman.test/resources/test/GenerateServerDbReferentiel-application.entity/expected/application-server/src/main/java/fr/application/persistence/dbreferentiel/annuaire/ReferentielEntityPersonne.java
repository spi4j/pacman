/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.application.persistence.dbreferentiel.annuaire;
// Start of user code for imports

import com.opencsv.CSVReader;
import fr.application.persistence.ApplicationParamPersistence;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// End of user code

/**
 * Classe de chargement de referentiel pour l entite Personne.
 * @author safr@n
 */
public final class ReferentielEntityPersonne
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat c_dateFormatCsv = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger c_LOG = LogManager.getLogger( ReferentielEntityPersonne.class );

    /** Le dao de l'entite Personne */
    private static final PersonneDao_Itf c_PersonneDao = ApplicationParamPersistence.getUserPersistence().getPersonneDao();

    /** Le dao de l'entite Personne */
    private static final PersonneDao_Itf c_PersonneDao = ApplicationParamPersistence.getUserPersistence().getPersonneDao();

    
    // Start of user code Constantes ReferentielEntityPersonne

    // End of user code


   // ATTRIBUTS

   
   // Start of user code Attributs ReferentielEntityPersonne

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityPersonne ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'Personne'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String v_nomFicCsv = "";
      String v_nomFicCsvComplet = AnnuaireReferentiel.getRepCsv() + v_nomFicCsv;
      String v_physicalUnique = "";

      c_LOG.info( "/** DEBUT Import Referentiel Entity Personne **/" );
      c_LOG.info( "Import Referentiel Personne, nom du fichier: " + v_nomFicCsv );
      c_LOG.info( "Import Referentiel Personne, metadata PHYSICAL_UNIQUE: " + v_physicalUnique );
      final String v_FkCriterePersonne = "" ;
      c_LOG.info( "Import Referentiel Personne, Relation de Personne vers Personne, nom du fkCritere: " + v_FkCriterePersonne );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de Personne" );
        }
        if ( "".equals ( v_physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de Personne" );
        }

        /** Initialisation  des noms des FK_CRITERE; exception si ils sont mal renseignés */
        PersonneColumns_Enum v_PersonneColumnPersonne = null;
        PersonneColumns_Enum v_PersonneColumnPersonne = null;
        if ( ( v_PersonneColumnPersonne == null ) || ( v_PersonneColumnPersonne == null ) )
        {
          throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de Personne vers Personne" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader v_csvReader =  new CSVReader (new FileReader( v_nomFicCsvComplet), ';', '"', 1);
        String [] v_csvLine;

   	    
   	    // Start of user code PreTraitement ReferentielEntityPersonne

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( v_csvLine = v_csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  v_cptrCriteria = 0;
          boolean v_suppressionEnregistrement = false;
          boolean v_filterEnregistrement = false;
          String v_xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'Personne', à partir du critere d unicite '' */
          final TableCriteria <PersonneColumns_Enum> v_tableCriteriaPersonne = new TableCriteria <> ("find by criteria en cherchant sur Personne");
          /** La variable entity renseignée par le contenu du fichier  */
          PersonneEntity_Itf v_PersonneFichier = ApplicationParamPersistence.getUserPersistence().getPersonneEntity();


          /** L'attribut nom	*/
          v_PersonneFichier.set_nom( v_csvLine[ PersonneColumns_Enum.nom.ordinal() - 1 ] );

          /** L'attribut prenom	*/
          v_PersonneFichier.set_prenom( v_csvLine[ PersonneColumns_Enum.prenom.ordinal() - 1 ] );

          /** L'attribut civil	*/
          v_PersonneFichier.set_civil( Boolean.parseBoolean(v_csvLine[ PersonneColumns_Enum.civil.ordinal() - 1 ]) );

          /** L'attribut dateNaissance	*/
          v_PersonneFichier.set_dateNaissance( c_dateFormatCsv.parse(v_csvLine[ PersonneColumns_Enum.dateNaissance.ordinal() - 1 ]) );

          /** L'attribut salaire	*/
          v_PersonneFichier.set_salaire( c_numberFormatCsv.parse(v_csvLine[ PersonneColumns_Enum.salaire.ordinal() - 1 ]).doubleValue() );



          /** La colonne automatique xdmaj */
          v_PersonneFichier.set_xdmaj( new Date() );

          /** La colonne automatique xtopsup */
          v_PersonneFichier.set_xtopsup( v_xtopsup );



          /** Chargement de la foreign key de 'Personne' vers 'Personne', à partir du fkCritere '' */
          final TableCriteria <PersonneColumns_Enum> v_tableCriteriaPersonne = new TableCriteria <> ("find by criteria en cherchant sur Personne");
          v_tableCriteriaPersonne.addOrderByDesc( PersonneColumns_Enum.dateDeDebut );
          v_tableCriteriaPersonne.addCriteria( v_PersonneColumnPersonne, Operator_Enum.equals, v_csvLine[ v_PersonneColumnPersonne.ordinal() - 1 ] );
          final List <PersonneEntity_Itf> v_allPersonne =  c_PersonneDao.findByCriteria( v_tableCriteriaPersonne );
          PersonneEntity_Itf v_entityPersonne = null;
          if ( v_allPersonne.size() > 0)
          {
            v_entityPersonne = v_allPersonne.get( 0 );
          }
          if ( v_entityPersonne != null )
          {
            v_PersonneFichier.set_personneParentDe_id( v_entityPersonne.getId() );
          }


          
          // Start of user code PreSave ReferentielEntityPersonne

          // End of user code

          /** On recupere les enregistrements en base de l'entite Personne correspondant à la ligne lue */
          final List <PersonneEntity_Itf> v_allPersonne =  c_PersonneDao.findByCriteria( v_tableCriteriaPersonne );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite Personne */
          if (	v_filterEnregistrement )
          {
            v_nbLignesFiltrees ++;
          }
          else
          {
            if ( v_allPersonne.size() == 0 )
            {
                if ( v_suppressionEnregistrement == false )
                {
                    c_PersonneDao.create( v_PersonneFichier );
                    v_nbLignesCreate ++;
                }
                else
                {
                    v_nbLignesSansTraitement ++;
                }
            }
            else
            {
                PersonneEntity_Itf v_PersonneExistant = v_allPersonne.get(0);
                if ( v_suppressionEnregistrement == true )
                {
                    c_PersonneDao.delete( v_PersonneExistant );
                    v_nbLignesDelete ++;
                }
                else
                {
                    v_PersonneFichier.setId( v_PersonneExistant.getId() );
                    c_PersonneDao.update( v_PersonneFichier );
                    v_nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          v_nbLignesTraitees ++;

          
          // Start of user code PostSave ReferentielEntityPersonne

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
      c_LOG.info( "Import Referentiel Personne, nombre de ligne(s) traitee(s): " + v_nbLignesTraitees );
      c_LOG.info( "Import Referentiel Personne, nombre de ligne(s) filtree(s): " + v_nbLignesFiltrees );
      c_LOG.info( "Import Referentiel Personne, nombre de ligne(s) create: " + v_nbLignesCreate );
      c_LOG.info( "Import Referentiel Personne, nombre de ligne(s) update: " + v_nbLignesUpdate );
      c_LOG.info( "Import Referentiel Personne, nombre de ligne(s) delete: " + v_nbLignesDelete );
      c_LOG.info( "Import Referentiel Personne, nombre de ligne(s) sans traitement (delete d'un non existant): " + v_nbLignesSansTraitement );
      c_LOG.info( "/** FIN Import Referentiel Entity Personne **/\n" );

      
      // Start of user code PostTraitement ReferentielEntityPersonne

      // End of user code

   }

   
   // Start of user code Methodes ReferentielEntityPersonne

   // End of user code


}
