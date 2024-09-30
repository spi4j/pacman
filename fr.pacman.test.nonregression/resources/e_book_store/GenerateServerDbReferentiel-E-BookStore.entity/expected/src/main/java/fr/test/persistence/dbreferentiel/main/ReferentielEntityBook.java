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
 * Classe de chargement de referentiel pour l entite Book.
 * @author safr@n
 */
public final class ReferentielEntityBook
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat DATEFORMATCSV = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielEntityBook.class );

    /** Le dao de l'entite Book */
    private static final BookDao BOOKDAO = TestParamPersistence.getUserPersistence().getBookDao();

    /** Le dao de l'entite Author */
    private static final AuthorDao AUTHORDAO = TestParamPersistence.getUserPersistence().getAuthorDao();

    // Constantes ReferentielEntityBook
    // Start of user code f8ac53cb3a6df3ac297ffc98b9b23680

    // End of user code


   // ATTRIBUTS

   // Attributs ReferentielEntityBook
   // Start of user code 354692ea6ecde886a6dba7d83c9ea797

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityBook ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'Book'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String nomFicCsv = "";
      String nomFicCsvComplet = MainReferentiel.getRepCsv() + nomFicCsv;
      String physicalUnique = "";

      LOG.info( "/** DEBUT Import Referentiel Entity Book **/" );
      LOG.info( "Import Referentiel Book, nom du fichier: " + nomFicCsv );
      LOG.info( "Import Referentiel Book, metadata PHYSICAL_UNIQUE: " + physicalUnique );
      final String fkCritereAuthor = "" ;
      LOG.info( "Import Referentiel Book, Relation de Author vers Book, nom du fkCritere: " + fkCritereAuthor );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de Book" );
        }
        if ( "".equals ( physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de Book" );
        }

        /** Initialisation  des noms des FK_CRITERE; exception si ils sont mal renseignés */
        BookColumnsEnum bookColumnAuthor = null;
        AuthorColumnsEnum authorColumnAuthor = null;
        if ( ( bookColumnAuthor == null ) || ( authorColumnAuthor == null ) )
        {
          throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de Author vers Book" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader csvReader =  new CSVReader (new FileReader( nomFicCsvComplet), ';', '"', 1);
        String [] csvLine;

   	    // PreTraitement ReferentielEntityBook
   	    // Start of user code 5d5aa21569d77def3b9ed185d6878760

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( csvLine = csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  cptrCriteria = 0;
          boolean suppressionEnregistrement = false;
          boolean filterEnregistrement = false;
          String xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'Book', à partir du critere d unicite '' */
          final TableCriteria <BookColumnsEnum> tableCriteriaBook = new TableCriteria <> ("find by criteria en cherchant sur Book");
          /** La variable entity renseignée par le contenu du fichier  */
          BookEntity bookFichier = TestParamPersistence.getUserPersistence().getBookEntity();


          /** L'attribut title	*/
          bookFichier.setTitle( csvLine[ BookColumnsEnum.title.ordinal() - 1 ] );

          /** L'attribut price	*/
          bookFichier.setPrice( NUMBERFORMATCSV.parse(csvLine[ BookColumnsEnum.price.ordinal() - 1 ]).intValue() );

          /** L'attribut image	*/
          bookFichier.setImage( csvLine[ BookColumnsEnum.image.ordinal() - 1 ] );

          /** L'attribut type	*/
          bookFichier.setType( csvLine[ BookColumnsEnum.type.ordinal() - 1 ] );



          /** Chargement de la foreign key de 'Book' vers 'Author', à partir du fkCritere '' */
          final TableCriteria <AuthorColumnsEnum> tableCriteriaAuthor = new TableCriteria <> ("find by criteria en cherchant sur Author");
          tableCriteriaAuthor.addOrderByDesc( AuthorColumnsEnum.dateDeDebut );
          tableCriteriaAuthor.addCriteria( authorColumnAuthor, Operator_Enum.equals, csvLine[ bookColumnAuthor.ordinal() - 1 ] );
          final List <AuthorEntity> allAuthor =  AUTHORDAO.findByCriteria( tableCriteriaAuthor );
          AuthorEntity entityAuthor = null;
          if ( allAuthor.size() > 0)
          {
            entityAuthor = allAuthor.get( 0 );
          }
          if ( entityAuthor != null )
          {
            bookFichier.setAuthorWrote_id( entityAuthor.getId() );
          }


          // PreSave ReferentielEntityBook
          // Start of user code e06112bbd746ce5ee85302e47922193f

          // End of user code

          /** On recupere les enregistrements en base de l'entite Book correspondant à la ligne lue */
          final List <BookEntity> allBook =  BOOKDAO.findByCriteria( tableCriteriaBook );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite Book */
          if (	filterEnregistrement )
          {
            nbLignesFiltrees ++;
          }
          else
          {
            if ( allBook.size() == 0 )
            {
                if ( suppressionEnregistrement == false )
                {
                    BOOKDAO.create( bookFichier );
                    nbLignesCreate ++;
                }
                else
                {
                    nbLignesSansTraitement ++;
                }
            }
            else
            {
                BookEntity bookExistant = allBook.get(0);
                if ( suppressionEnregistrement == true )
                {
                    BOOKDAO.delete( bookExistant );
                    nbLignesDelete ++;
                }
                else
                {
                    bookFichier.setId( bookExistant.getId() );
                    BOOKDAO.update( bookFichier );
                    nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          nbLignesTraitees ++;

          // PostSave ReferentielEntityBook
          // Start of user code 2d901d7d1f6b64b4ae55887fe596e660

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
      LOG.info( "Import Referentiel Book, nombre de ligne(s) traitee(s): " + nbLignesTraitees );
      LOG.info( "Import Referentiel Book, nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
      LOG.info( "Import Referentiel Book, nombre de ligne(s) create: " + nbLignesCreate );
      LOG.info( "Import Referentiel Book, nombre de ligne(s) update: " + nbLignesUpdate );
      LOG.info( "Import Referentiel Book, nombre de ligne(s) delete: " + nbLignesDelete );
      LOG.info( "Import Referentiel Book, nombre de ligne(s) sans traitement (delete d'un non existant): " + nbLignesSansTraitement );
      LOG.info( "/** FIN Import Referentiel Entity Book **/\n" );

      // PostTraitement ReferentielEntityBook
      // Start of user code b2cf3e40ea4be8807dee21dc13178690

      // End of user code

   }

   // Methodes ReferentielEntityBook
   // Start of user code 91baa2e7a056faa078b7f2339664fd15

   // End of user code


}
