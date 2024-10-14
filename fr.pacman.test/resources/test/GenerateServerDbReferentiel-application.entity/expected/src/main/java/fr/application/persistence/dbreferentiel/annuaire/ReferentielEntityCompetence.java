/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.application.persistence.dbreferentiel.annuaire;
// Start of user code for imports

import fr.application.persistence.api.annuaire.PersonneEntity_Itf;

import com.opencsv.CSVReader;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.api.annuaire.CompetenceColumns_Enum;
import fr.application.persistence.api.annuaire.CompetenceDao_Itf;
import fr.application.persistence.api.annuaire.CompetenceEntity_Itf;
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
 * Classe de chargement de referentiel pour l entite Competence.
 * @author safr@n
 */
public final class ReferentielEntityCompetence
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat c_dateFormatCsv = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger c_LOG = LogManager.getLogger( ReferentielEntityCompetence.class );

    /** Le dao de l'entite Competence */
    private static final CompetenceDao_Itf c_CompetenceDao = ApplicationParamPersistence.getUserPersistence().getCompetenceDao();

    // Constantes ReferentielEntityCompetence
    // Start of user code Constantes ReferentielEntityCompetence

    // End of user code


   // ATTRIBUTS

   // Attributs ReferentielEntityCompetence
   // Start of user code Attributs ReferentielEntityCompetence

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityCompetence ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'Competence'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String v_nomFicCsv = "";
      String v_nomFicCsvComplet = AnnuaireReferentiel.getRepCsv() + v_nomFicCsv;
      String v_physicalUnique = "";

      c_LOG.info( "/** DEBUT Import Referentiel Entity Competence **/" );
      c_LOG.info( "Import Referentiel Competence, nom du fichier: " + v_nomFicCsv );
      c_LOG.info( "Import Referentiel Competence, metadata PHYSICAL_UNIQUE: " + v_physicalUnique );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de Competence" );
        }
        if ( "".equals ( v_physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de Competence" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader v_csvReader =  new CSVReader (new FileReader( v_nomFicCsvComplet), ';', '"', 1);
        String [] v_csvLine;

   	    // PreTraitement ReferentielEntityCompetence
   	    // Start of user code PreTraitement ReferentielEntityCompetence

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( v_csvLine = v_csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  v_cptrCriteria = 0;
          boolean v_suppressionEnregistrement = false;
          boolean v_filterEnregistrement = false;
          String v_xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'Competence', à partir du critere d unicite '' */
          final TableCriteria <CompetenceColumns_Enum> v_tableCriteriaCompetence = new TableCriteria <> ("find by criteria en cherchant sur Competence");
          /** La variable entity renseignée par le contenu du fichier  */
          CompetenceEntity_Itf v_CompetenceFichier = ApplicationParamPersistence.getUserPersistence().getCompetenceEntity();


          /** L'attribut libelle	*/
          v_CompetenceFichier.set_libelle( v_csvLine[ CompetenceColumns_Enum.libelle.ordinal() - 1 ] );

          /** L'attribut typecompetence	*/
          v_CompetenceFichier.set_typecompetence(  TypeCompetence_Enum.valueOf("TYPECOMP1")  );



          /** La colonne automatique xdmaj */
          v_CompetenceFichier.set_xdmaj( new Date() );

          /** La colonne automatique xtopsup */
          v_CompetenceFichier.set_xtopsup( v_xtopsup );


          // PreSave ReferentielEntityCompetence
          // Start of user code PreSave ReferentielEntityCompetence

          // End of user code

          /** On recupere les enregistrements en base de l'entite Competence correspondant à la ligne lue */
          final List <CompetenceEntity_Itf> v_allCompetence =  c_CompetenceDao.findByCriteria( v_tableCriteriaCompetence );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite Competence */
          if (	v_filterEnregistrement )
          {
            v_nbLignesFiltrees ++;
          }
          else
          {
            if ( v_allCompetence.size() == 0 )
            {
                if ( v_suppressionEnregistrement == false )
                {
                    c_CompetenceDao.create( v_CompetenceFichier );
                    v_nbLignesCreate ++;
                }
                else
                {
                    v_nbLignesSansTraitement ++;
                }
            }
            else
            {
                CompetenceEntity_Itf v_CompetenceExistant = v_allCompetence.get(0);
                if ( v_suppressionEnregistrement == true )
                {
                    c_CompetenceDao.delete( v_CompetenceExistant );
                    v_nbLignesDelete ++;
                }
                else
                {
                    v_CompetenceFichier.setId( v_CompetenceExistant.getId() );
                    c_CompetenceDao.update( v_CompetenceFichier );
                    v_nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          v_nbLignesTraitees ++;

          // PostSave ReferentielEntityCompetence
          // Start of user code PostSave ReferentielEntityCompetence

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
      c_LOG.info( "Import Referentiel Competence, nombre de ligne(s) traitee(s): " + v_nbLignesTraitees );
      c_LOG.info( "Import Referentiel Competence, nombre de ligne(s) filtree(s): " + v_nbLignesFiltrees );
      c_LOG.info( "Import Referentiel Competence, nombre de ligne(s) create: " + v_nbLignesCreate );
      c_LOG.info( "Import Referentiel Competence, nombre de ligne(s) update: " + v_nbLignesUpdate );
      c_LOG.info( "Import Referentiel Competence, nombre de ligne(s) delete: " + v_nbLignesDelete );
      c_LOG.info( "Import Referentiel Competence, nombre de ligne(s) sans traitement (delete d'un non existant): " + v_nbLignesSansTraitement );
      c_LOG.info( "/** FIN Import Referentiel Entity Competence **/\n" );

      // PostTraitement ReferentielEntityCompetence
      // Start of user code PostTraitement ReferentielEntityCompetence

      // End of user code

   }

   // Methodes ReferentielEntityCompetence
   // Start of user code Methodes ReferentielEntityCompetence

   // End of user code


}
