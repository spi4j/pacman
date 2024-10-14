/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.test.persistence.dbreferentiel.main;
// Start of user code for imports

import com.opencsv.CSVReader;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.DatabaseLineStatus_Enum;
import fr.spi4j.persistence.dao.OperatorLogical_Enum;
import fr.spi4j.persistence.dao.Operator_Enum;
import fr.spi4j.persistence.dao.TableCriteria;
import fr.test.persistence.TestParamPersistence;
import fr.test.persistence.api.main.UserColumnsEnum;
import fr.test.persistence.api.main.UserDao;
import fr.test.persistence.api.main.UserEntity;
import fr.test.persistence.dbreferentiel.MainReferentiel;
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
 * Classe de chargement de referentiel pour l entite User.
 * @author safr@n
 */
public final class ReferentielEntityUser
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat DATEFORMATCSV = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielEntityUser.class );

    /** Le dao de l'entite User */
    private static final UserDao USERDAO = TestParamPersistence.getUserPersistence().getUserDao();

    // Constantes ReferentielEntityUser
    // Start of user code a0bd5cab2c67f511a37276f4be5b901b

    // End of user code


   // ATTRIBUTS

   // Attributs ReferentielEntityUser
   // Start of user code dfdb7c6dafb42910261fe4e4575cb552

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityUser ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'User'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String nomFicCsv = "";
      String nomFicCsvComplet = MainReferentiel.getRepCsv() + nomFicCsv;
      String physicalUnique = "";

      LOG.info( "/** DEBUT Import Referentiel Entity User **/" );
      LOG.info( "Import Referentiel User, nom du fichier: " + nomFicCsv );
      LOG.info( "Import Referentiel User, metadata PHYSICAL_UNIQUE: " + physicalUnique );

      long nbLignesTraitees = 0;
      long nbLignesFiltrees = 0;
      long nbLignesCreate = 0;
      long nbLignesUpdate = 0;
      long nbLignesDelete = 0;
      long nbLignesSansTraitement = 0;
      Date today = new Date();

      try
      {

        /** Exception si le nom de fichier ou la contrainte d'unicite ne sont pas renseignés */
        if ( "".equals ( nomFicCsv ) )
        {
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de User" );
        }
        if ( "".equals ( physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de User" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader csvReader =  new CSVReader (new FileReader( nomFicCsvComplet), ';', '"', 1);
        String [] csvLine;

   	    // PreTraitement ReferentielEntityUser
   	    // Start of user code 49fd7973cbe5f06094a7d2ae0fda5c63

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( csvLine = csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  cptrCriteria = 0;
          boolean suppressionEnregistrement = false;
          boolean filterEnregistrement = false;
          String xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'User', à partir du critere d unicite '' */
          final TableCriteria <UserColumnsEnum> tableCriteriaUser = new TableCriteria <> ("find by criteria en cherchant sur User");
          /** La variable entity renseignée par le contenu du fichier  */
          UserEntity userFichier = TestParamPersistence.getUserPersistence().getUserEntity();


          /** L'attribut name	*/
          userFichier.setName( csvLine[ UserColumnsEnum.name.ordinal() - 1 ] );

          /** L'attribut email	*/
          userFichier.setEmail( csvLine[ UserColumnsEnum.email.ordinal() - 1 ] );

          /** L'attribut password	*/
          userFichier.setPassword( csvLine[ UserColumnsEnum.password.ordinal() - 1 ] );


          // PreSave ReferentielEntityUser
          // Start of user code 309c6817acfeae22105f2c0dba1d13f8

          // End of user code

          /** On recupere les enregistrements en base de l'entite User correspondant à la ligne lue */
          final List <UserEntity> allUser =  USERDAO.findByCriteria( tableCriteriaUser );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite User */
          if (	filterEnregistrement )
          {
            nbLignesFiltrees ++;
          }
          else
          {
            if ( allUser.size() == 0 )
            {
                if ( suppressionEnregistrement == false )
                {
                    USERDAO.create( userFichier );
                    nbLignesCreate ++;
                }
                else
                {
                    nbLignesSansTraitement ++;
                }
            }
            else
            {
                UserEntity userExistant = allUser.get(0);
                if ( suppressionEnregistrement == true )
                {
                    USERDAO.delete( userExistant );
                    nbLignesDelete ++;
                }
                else
                {
                    userFichier.setId( userExistant.getId() );
                    USERDAO.update( userFichier );
                    nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          nbLignesTraitees ++;

          // PostSave ReferentielEntityUser
          // Start of user code da62c2bc68dc5d58ecf734a36e543e42

          // End of user code

        }
        /** Fermeture du reader Csv */
        csvReader.close();
      }
      /** En cas d erreur, on affiche dans la log, et on positionne le flag de transaction à rollback */
      catch ( Exception exception )
      {
         LOG.error ( exception );
         MainReferentiel.setCommitTraitement(false);
      }

      /** Log de fin du traitement */
      LOG.info( "Import Referentiel User, nombre de ligne(s) traitee(s): " + nbLignesTraitees );
      LOG.info( "Import Referentiel User, nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
      LOG.info( "Import Referentiel User, nombre de ligne(s) create: " + nbLignesCreate );
      LOG.info( "Import Referentiel User, nombre de ligne(s) update: " + nbLignesUpdate );
      LOG.info( "Import Referentiel User, nombre de ligne(s) delete: " + nbLignesDelete );
      LOG.info( "Import Referentiel User, nombre de ligne(s) sans traitement (delete d'un non existant): " + nbLignesSansTraitement );
      LOG.info( "/** FIN Import Referentiel Entity User **/\n" );

      // PostTraitement ReferentielEntityUser
      // Start of user code 3d064390fa4b7b04c630cf1a0c28d157

      // End of user code

   }

   // Methodes ReferentielEntityUser
   // Start of user code 059929480359c1ecac64871fe755c5bd

   // End of user code


}
