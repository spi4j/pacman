/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package  fr.test.persistence.dbreferentiel.main;
// Start of user code for imports

import com.opencsv.CSVReader;
import fr.spi4j.exception.Spi4jValidationException;
import fr.spi4j.persistence.dao.OperatorLogical_Enum;
import fr.spi4j.persistence.dao.Operator_Enum;
import fr.spi4j.persistence.dao.TableCriteria;
import fr.test.persistence.TestParamPersistence;
import fr.test.persistence.api.main.ShoopingCartLineColumnsEnum;
import fr.test.persistence.api.main.ShoopingCartLineDao;
import fr.test.persistence.api.main.ShoopingCartLineEntity;
import fr.test.persistence.api.main.ShoppingCartColumnsEnum;
import fr.test.persistence.api.main.ShoppingCartDao;
import fr.test.persistence.api.main.ShoppingCartEntity;
import fr.test.persistence.dbreferentiel.MainReferentiel;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// End of user code

/**
 * Classe de chargement de referentiel pour l entite ShoopingCartLine.
 * @author safr@n
 */
public final class ReferentielEntityShoopingCartLine
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat DATEFORMATCSV = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielEntityShoopingCartLine.class );

    /** Le dao de l'entite ShoopingCartLine */
    private static final ShoopingCartLineDao SHOOPINGCARTLINEDAO = TestParamPersistence.getUserPersistence().getShoopingCartLineDao();

    /** Le dao de l'entite ShoppingCart */
    private static final ShoppingCartDao SHOPPINGCARTDAO = TestParamPersistence.getUserPersistence().getShoppingCartDao();

    
    // Start of user code c232634fef899dd4f15f93f9da79f18f

    // End of user code


   // ATTRIBUTS

   
   // Start of user code 72fb1aa87fa7911a61194be9eb75997a

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityShoopingCartLine ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'ShoopingCartLine'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String nomFicCsv = "";
      String nomFicCsvComplet = MainReferentiel.getRepCsv() + nomFicCsv;
      String physicalUnique = "";

      LOG.info( "/** DEBUT Import Referentiel Entity ShoopingCartLine **/" );
      LOG.info( "Import Referentiel ShoopingCartLine, nom du fichier: " + nomFicCsv );
      LOG.info( "Import Referentiel ShoopingCartLine, metadata PHYSICAL_UNIQUE: " + physicalUnique );
      final String fkCritereShoppingCart = "" ;
      LOG.info( "Import Referentiel ShoopingCartLine, Relation de ShoppingCart vers ShoopingCartLine, nom du fkCritere: " + fkCritereShoppingCart );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de ShoopingCartLine" );
        }
        if ( "".equals ( physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de ShoopingCartLine" );
        }

        /** Initialisation  des noms des FK_CRITERE; exception si ils sont mal renseignés */
        ShoopingCartLineColumnsEnum shoopingCartLineColumnShoppingCart = null;
        ShoppingCartColumnsEnum shoppingCartColumnShoppingCart = null;
        if ( ( shoopingCartLineColumnShoppingCart == null ) || ( shoppingCartColumnShoppingCart == null ) )
        {
          throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de ShoppingCart vers ShoopingCartLine" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader csvReader =  new CSVReader (new FileReader( nomFicCsvComplet), ';', '"', 1);
        String [] csvLine;

   	    
   	    // Start of user code b9fa1db065ad4f2b641316a1f03192bf

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( csvLine = csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  cptrCriteria = 0;
          boolean suppressionEnregistrement = false;
          boolean filterEnregistrement = false;
          String xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'ShoopingCartLine', à partir du critere d unicite '' */
          final TableCriteria <ShoopingCartLineColumnsEnum> tableCriteriaShoopingCartLine = new TableCriteria <> ("find by criteria en cherchant sur ShoopingCartLine");
          /** La variable entity renseignée par le contenu du fichier  */
          ShoopingCartLineEntity shoopingCartLineFichier = TestParamPersistence.getUserPersistence().getShoopingCartLineEntity();


          /** L'attribut quantity	*/
          shoopingCartLineFichier.setQuantity( NUMBERFORMATCSV.parse(csvLine[ ShoopingCartLineColumnsEnum.quantity.ordinal() - 1 ]).intValue() );



          /** Chargement de la foreign key de 'ShoopingCartLine' vers 'ShoppingCart', à partir du fkCritere '' */
          final TableCriteria <ShoppingCartColumnsEnum> tableCriteriaShoppingCart = new TableCriteria <> ("find by criteria en cherchant sur ShoppingCart");
          tableCriteriaShoppingCart.addOrderByDesc( ShoppingCartColumnsEnum.dateDeDebut );
          tableCriteriaShoppingCart.addCriteria( shoppingCartColumnShoppingCart, Operator_Enum.equals, csvLine[ shoopingCartLineColumnShoppingCart.ordinal() - 1 ] );
          final List <ShoppingCartEntity> allShoppingCart =  SHOPPINGCARTDAO.findByCriteria( tableCriteriaShoppingCart );
          ShoppingCartEntity entityShoppingCart = null;
          if ( allShoppingCart.size() > 0)
          {
            entityShoppingCart = allShoppingCart.get( 0 );
          }
          if ( entityShoppingCart != null )
          {
            shoopingCartLineFichier.setShoppingCartLines_id( entityShoppingCart.getId() );
          }


          
          // Start of user code c0b66520506864980fa288be2c4d936e

          // End of user code

          /** On recupere les enregistrements en base de l'entite ShoopingCartLine correspondant à la ligne lue */
          final List <ShoopingCartLineEntity> allShoopingCartLine =  SHOOPINGCARTLINEDAO.findByCriteria( tableCriteriaShoopingCartLine );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite ShoopingCartLine */
          if (	filterEnregistrement )
          {
            nbLignesFiltrees ++;
          }
          else
          {
            if ( allShoopingCartLine.size() == 0 )
            {
                if ( suppressionEnregistrement == false )
                {
                    SHOOPINGCARTLINEDAO.create( shoopingCartLineFichier );
                    nbLignesCreate ++;
                }
                else
                {
                    nbLignesSansTraitement ++;
                }
            }
            else
            {
                ShoopingCartLineEntity shoopingCartLineExistant = allShoopingCartLine.get(0);
                if ( suppressionEnregistrement == true )
                {
                    SHOOPINGCARTLINEDAO.delete( shoopingCartLineExistant );
                    nbLignesDelete ++;
                }
                else
                {
                    shoopingCartLineFichier.setId( shoopingCartLineExistant.getId() );
                    SHOOPINGCARTLINEDAO.update( shoopingCartLineFichier );
                    nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          nbLignesTraitees ++;

          
          // Start of user code 17ac3d9087ebe39408d5a14fe56e9b0c

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
      LOG.info( "Import Referentiel ShoopingCartLine, nombre de ligne(s) traitee(s): " + nbLignesTraitees );
      LOG.info( "Import Referentiel ShoopingCartLine, nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
      LOG.info( "Import Referentiel ShoopingCartLine, nombre de ligne(s) create: " + nbLignesCreate );
      LOG.info( "Import Referentiel ShoopingCartLine, nombre de ligne(s) update: " + nbLignesUpdate );
      LOG.info( "Import Referentiel ShoopingCartLine, nombre de ligne(s) delete: " + nbLignesDelete );
      LOG.info( "Import Referentiel ShoopingCartLine, nombre de ligne(s) sans traitement (delete d'un non existant): " + nbLignesSansTraitement );
      LOG.info( "/** FIN Import Referentiel Entity ShoopingCartLine **/\n" );

      
      // Start of user code ac4b46393ea4da31be31e4088bf30dd0

      // End of user code

   }

   
   // Start of user code b36cccc1bc3c25a13721d7697e9423bc

   // End of user code


}
