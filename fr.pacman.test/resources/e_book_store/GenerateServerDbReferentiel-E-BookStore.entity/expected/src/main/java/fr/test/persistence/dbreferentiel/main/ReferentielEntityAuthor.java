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
import fr.test.persistence.api.main.AuthorColumnsEnum;
import fr.test.persistence.api.main.AuthorDao;
import fr.test.persistence.api.main.AuthorEntity;
import fr.test.persistence.api.main.BookColumnsEnum;
import fr.test.persistence.api.main.BookDao;
import fr.test.persistence.api.main.BookEntity;
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
 * Classe de chargement de referentiel pour l entite Author.
 * @author safr@n
 */
public final class ReferentielEntityAuthor
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat DATEFORMATCSV = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielEntityAuthor.class );

    /** Le dao de l'entite Author */
    private static final AuthorDao AUTHORDAO = TestParamPersistence.getUserPersistence().getAuthorDao();

    /** Le dao de l'entite Book */
    private static final BookDao BOOKDAO = TestParamPersistence.getUserPersistence().getBookDao();

    // Constantes ReferentielEntityAuthor
    // Start of user code 31388d7c82a3066c4ee6e8c6b8e33690

    // End of user code


   // ATTRIBUTS

   // Attributs ReferentielEntityAuthor
   // Start of user code 64f1972313cfcdb21491c626ae25be26

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityAuthor ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'Author'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String nomFicCsv = "";
      String nomFicCsvComplet = MainReferentiel.getRepCsv() + nomFicCsv;
      String physicalUnique = "";

      LOG.info( "/** DEBUT Import Referentiel Entity Author **/" );
      LOG.info( "Import Referentiel Author, nom du fichier: " + nomFicCsv );
      LOG.info( "Import Referentiel Author, metadata PHYSICAL_UNIQUE: " + physicalUnique );
      final String fkCritereBook = "" ;
      LOG.info( "Import Referentiel Author, Relation de Book vers Author, nom du fkCritere: " + fkCritereBook );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de Author" );
        }
        if ( "".equals ( physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de Author" );
        }

        /** Initialisation  des noms des FK_CRITERE; exception si ils sont mal renseignés */
        AuthorColumnsEnum authorColumnBook = null;
        BookColumnsEnum bookColumnBook = null;
        if ( ( authorColumnBook == null ) || ( bookColumnBook == null ) )
        {
          throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de Book vers Author" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader csvReader =  new CSVReader (new FileReader( nomFicCsvComplet), ';', '"', 1);
        String [] csvLine;

   	    // PreTraitement ReferentielEntityAuthor
   	    // Start of user code bbf94a9b03cc71ba25987d3474570249

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( csvLine = csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  cptrCriteria = 0;
          boolean suppressionEnregistrement = false;
          boolean filterEnregistrement = false;
          String xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'Author', à partir du critere d unicite '' */
          final TableCriteria <AuthorColumnsEnum> tableCriteriaAuthor = new TableCriteria <> ("find by criteria en cherchant sur Author");
          /** La variable entity renseignée par le contenu du fichier  */
          AuthorEntity authorFichier = TestParamPersistence.getUserPersistence().getAuthorEntity();


          /** L'attribut name	*/
          authorFichier.setName( csvLine[ AuthorColumnsEnum.name.ordinal() - 1 ] );



          /** Chargement de la foreign key de 'Author' vers 'Book', à partir du fkCritere '' */
          final TableCriteria <BookColumnsEnum> tableCriteriaBook = new TableCriteria <> ("find by criteria en cherchant sur Book");
          tableCriteriaBook.addOrderByDesc( BookColumnsEnum.dateDeDebut );
          tableCriteriaBook.addCriteria( bookColumnBook, Operator_Enum.equals, csvLine[ authorColumnBook.ordinal() - 1 ] );
          final List <BookEntity> allBook =  BOOKDAO.findByCriteria( tableCriteriaBook );
          BookEntity entityBook = null;
          if ( allBook.size() > 0)
          {
            entityBook = allBook.get( 0 );
          }
          if ( entityBook != null )
          {
            authorFichier.setBookWrittenBy_id( entityBook.getId() );
          }


          // PreSave ReferentielEntityAuthor
          // Start of user code b2b989d1a72f98fd49145e6d9740a7d3

          // End of user code

          /** On recupere les enregistrements en base de l'entite Author correspondant à la ligne lue */
          final List <AuthorEntity> allAuthor =  AUTHORDAO.findByCriteria( tableCriteriaAuthor );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite Author */
          if (	filterEnregistrement )
          {
            nbLignesFiltrees ++;
          }
          else
          {
            if ( allAuthor.size() == 0 )
            {
                if ( suppressionEnregistrement == false )
                {
                    AUTHORDAO.create( authorFichier );
                    nbLignesCreate ++;
                }
                else
                {
                    nbLignesSansTraitement ++;
                }
            }
            else
            {
                AuthorEntity authorExistant = allAuthor.get(0);
                if ( suppressionEnregistrement == true )
                {
                    AUTHORDAO.delete( authorExistant );
                    nbLignesDelete ++;
                }
                else
                {
                    authorFichier.setId( authorExistant.getId() );
                    AUTHORDAO.update( authorFichier );
                    nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          nbLignesTraitees ++;

          // PostSave ReferentielEntityAuthor
          // Start of user code 27783775c939cb193443bf00c49eda1d

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
      LOG.info( "Import Referentiel Author, nombre de ligne(s) traitee(s): " + nbLignesTraitees );
      LOG.info( "Import Referentiel Author, nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
      LOG.info( "Import Referentiel Author, nombre de ligne(s) create: " + nbLignesCreate );
      LOG.info( "Import Referentiel Author, nombre de ligne(s) update: " + nbLignesUpdate );
      LOG.info( "Import Referentiel Author, nombre de ligne(s) delete: " + nbLignesDelete );
      LOG.info( "Import Referentiel Author, nombre de ligne(s) sans traitement (delete d'un non existant): " + nbLignesSansTraitement );
      LOG.info( "/** FIN Import Referentiel Entity Author **/\n" );

      // PostTraitement ReferentielEntityAuthor
      // Start of user code 7eaa4c1b6b5869a5cd6627aa7c6a5dfe

      // End of user code

   }

   // Methodes ReferentielEntityAuthor
   // Start of user code d30ec0fb5a30d5ac8ab650083e05b9d8

   // End of user code


}
