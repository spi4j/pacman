/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.application.persistence.dbreferentiel.ref;
// Start of user code for imports

import com.opencsv.CSVReader;
import fr.application.persistence.ApplicationParamPersistence;
import fr.application.persistence.api.ref.GradeColumns_Enum;
import fr.application.persistence.api.ref.GradeDao_Itf;
import fr.application.persistence.api.ref.GradeEntity_Itf;
import fr.application.persistence.dbreferentiel.RefReferentiel;
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
 * Classe de chargement de referentiel pour l entite Grade.
 * @author safr@n
 */
public final class ReferentielEntityGrade
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat c_dateFormatCsv = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger c_LOG = LogManager.getLogger( ReferentielEntityGrade.class );

    /** Le dao de l'entite Grade */
    private static final GradeDao_Itf c_GradeDao = ApplicationParamPersistence.getUserPersistence().getGradeDao();

    // Constantes ReferentielEntityGrade
    // Start of user code Constantes ReferentielEntityGrade

    // End of user code


   // ATTRIBUTS

   // Attributs ReferentielEntityGrade
   // Start of user code Attributs ReferentielEntityGrade

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityGrade ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'Grade'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String v_nomFicCsv = "";
      String v_nomFicCsvComplet = RefReferentiel.getRepCsv() + v_nomFicCsv;
      String v_physicalUnique = "";

      c_LOG.info( "/** DEBUT Import Referentiel Entity Grade **/" );
      c_LOG.info( "Import Referentiel Grade, nom du fichier: " + v_nomFicCsv );
      c_LOG.info( "Import Referentiel Grade, metadata PHYSICAL_UNIQUE: " + v_physicalUnique );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de Grade" );
        }
        if ( "".equals ( v_physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de Grade" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader v_csvReader =  new CSVReader (new FileReader( v_nomFicCsvComplet), ';', '"', 1);
        String [] v_csvLine;

   	    // PreTraitement ReferentielEntityGrade
   	    // Start of user code PreTraitement ReferentielEntityGrade

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( v_csvLine = v_csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  v_cptrCriteria = 0;
          boolean v_suppressionEnregistrement = false;
          boolean v_filterEnregistrement = false;
          String v_xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'Grade', à partir du critere d unicite '' */
          final TableCriteria <GradeColumns_Enum> v_tableCriteriaGrade = new TableCriteria <> ("find by criteria en cherchant sur Grade");
          /** La variable entity renseignée par le contenu du fichier  */
          GradeEntity_Itf v_GradeFichier = ApplicationParamPersistence.getUserPersistence().getGradeEntity();


          /** L'attribut libelle	*/
          v_GradeFichier.set_libelle( v_csvLine[ GradeColumns_Enum.libelle.ordinal() - 1 ] );

          /** L'attribut trigramme	*/
          v_GradeFichier.set_trigramme( v_csvLine[ GradeColumns_Enum.trigramme.ordinal() - 1 ] );



          /** La colonne automatique xdmaj */
          v_GradeFichier.set_xdmaj( new Date() );

          /** La colonne automatique xtopsup */
          v_GradeFichier.set_xtopsup( v_xtopsup );


          // PreSave ReferentielEntityGrade
          // Start of user code PreSave ReferentielEntityGrade

          // End of user code

          /** On recupere les enregistrements en base de l'entite Grade correspondant à la ligne lue */
          final List <GradeEntity_Itf> v_allGrade =  c_GradeDao.findByCriteria( v_tableCriteriaGrade );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite Grade */
          if (	v_filterEnregistrement )
          {
            v_nbLignesFiltrees ++;
          }
          else
          {
            if ( v_allGrade.size() == 0 )
            {
                if ( v_suppressionEnregistrement == false )
                {
                    c_GradeDao.create( v_GradeFichier );
                    v_nbLignesCreate ++;
                }
                else
                {
                    v_nbLignesSansTraitement ++;
                }
            }
            else
            {
                GradeEntity_Itf v_GradeExistant = v_allGrade.get(0);
                if ( v_suppressionEnregistrement == true )
                {
                    c_GradeDao.delete( v_GradeExistant );
                    v_nbLignesDelete ++;
                }
                else
                {
                    v_GradeFichier.setId( v_GradeExistant.getId() );
                    c_GradeDao.update( v_GradeFichier );
                    v_nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          v_nbLignesTraitees ++;

          // PostSave ReferentielEntityGrade
          // Start of user code PostSave ReferentielEntityGrade

          // End of user code

        }
        /** Fermeture du reader Csv */
        v_csvReader.close();
      }
      /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch ( Exception v_exception )
      {
         c_LOG.error ( v_exception );
         RefReferentiel.setCommitTraitement(false);
      }

      /** Log de fin du traitement */
      c_LOG.info( "Import Referentiel Grade, nombre de ligne(s) traitee(s): " + v_nbLignesTraitees );
      c_LOG.info( "Import Referentiel Grade, nombre de ligne(s) filtree(s): " + v_nbLignesFiltrees );
      c_LOG.info( "Import Referentiel Grade, nombre de ligne(s) create: " + v_nbLignesCreate );
      c_LOG.info( "Import Referentiel Grade, nombre de ligne(s) update: " + v_nbLignesUpdate );
      c_LOG.info( "Import Referentiel Grade, nombre de ligne(s) delete: " + v_nbLignesDelete );
      c_LOG.info( "Import Referentiel Grade, nombre de ligne(s) sans traitement (delete d'un non existant): " + v_nbLignesSansTraitement );
      c_LOG.info( "/** FIN Import Referentiel Entity Grade **/\n" );

      // PostTraitement ReferentielEntityGrade
      // Start of user code PostTraitement ReferentielEntityGrade

      // End of user code

   }

   // Methodes ReferentielEntityGrade
   // Start of user code Methodes ReferentielEntityGrade

   // End of user code


}
