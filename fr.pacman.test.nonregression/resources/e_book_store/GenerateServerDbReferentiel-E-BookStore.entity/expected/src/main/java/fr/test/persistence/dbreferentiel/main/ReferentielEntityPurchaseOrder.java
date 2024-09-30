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
import fr.test.persistence.api.main.PurchaseOrderColumnsEnum;
import fr.test.persistence.api.main.PurchaseOrderDao;
import fr.test.persistence.api.main.PurchaseOrderEntity;
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
 * Classe de chargement de referentiel pour l entite PurchaseOrder.
 * @author safr@n
 */
public final class ReferentielEntityPurchaseOrder
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat DATEFORMATCSV = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielEntityPurchaseOrder.class );

    /** Le dao de l'entite PurchaseOrder */
    private static final PurchaseOrderDao PURCHASEORDERDAO = TestParamPersistence.getUserPersistence().getPurchaseOrderDao();

    // Constantes ReferentielEntityPurchaseOrder
    // Start of user code 1dc230cb0aa8973cfa065d67d612af1f

    // End of user code


   // ATTRIBUTS

   // Attributs ReferentielEntityPurchaseOrder
   // Start of user code 3bbf55da350545600daf2ce0849fca44

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityPurchaseOrder ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'PurchaseOrder'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String nomFicCsv = "";
      String nomFicCsvComplet = MainReferentiel.getRepCsv() + nomFicCsv;
      String physicalUnique = "";

      LOG.info( "/** DEBUT Import Referentiel Entity PurchaseOrder **/" );
      LOG.info( "Import Referentiel PurchaseOrder, nom du fichier: " + nomFicCsv );
      LOG.info( "Import Referentiel PurchaseOrder, metadata PHYSICAL_UNIQUE: " + physicalUnique );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de PurchaseOrder" );
        }
        if ( "".equals ( physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de PurchaseOrder" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader csvReader =  new CSVReader (new FileReader( nomFicCsvComplet), ';', '"', 1);
        String [] csvLine;

   	    // PreTraitement ReferentielEntityPurchaseOrder
   	    // Start of user code 3013ddd0b158b06fdee8ccb17d867ea8

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( csvLine = csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  cptrCriteria = 0;
          boolean suppressionEnregistrement = false;
          boolean filterEnregistrement = false;
          String xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'PurchaseOrder', à partir du critere d unicite '' */
          final TableCriteria <PurchaseOrderColumnsEnum> tableCriteriaPurchaseOrder = new TableCriteria <> ("find by criteria en cherchant sur PurchaseOrder");
          /** La variable entity renseignée par le contenu du fichier  */
          PurchaseOrderEntity purchaseOrderFichier = TestParamPersistence.getUserPersistence().getPurchaseOrderEntity();


          /** L'attribut cardNumber	*/
          purchaseOrderFichier.setCardNumber( csvLine[ PurchaseOrderColumnsEnum.cardNumber.ordinal() - 1 ] );

          /** L'attribut totalAmount	*/
          purchaseOrderFichier.setTotalAmount( NUMBERFORMATCSV.parse(csvLine[ PurchaseOrderColumnsEnum.totalAmount.ordinal() - 1 ]).intValue() );


          // PreSave ReferentielEntityPurchaseOrder
          // Start of user code 741bf4fbd1e9fa4312373fcd5a198cb3

          // End of user code

          /** On recupere les enregistrements en base de l'entite PurchaseOrder correspondant à la ligne lue */
          final List <PurchaseOrderEntity> allPurchaseOrder =  PURCHASEORDERDAO.findByCriteria( tableCriteriaPurchaseOrder );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite PurchaseOrder */
          if (	filterEnregistrement )
          {
            nbLignesFiltrees ++;
          }
          else
          {
            if ( allPurchaseOrder.size() == 0 )
            {
                if ( suppressionEnregistrement == false )
                {
                    PURCHASEORDERDAO.create( purchaseOrderFichier );
                    nbLignesCreate ++;
                }
                else
                {
                    nbLignesSansTraitement ++;
                }
            }
            else
            {
                PurchaseOrderEntity purchaseOrderExistant = allPurchaseOrder.get(0);
                if ( suppressionEnregistrement == true )
                {
                    PURCHASEORDERDAO.delete( purchaseOrderExistant );
                    nbLignesDelete ++;
                }
                else
                {
                    purchaseOrderFichier.setId( purchaseOrderExistant.getId() );
                    PURCHASEORDERDAO.update( purchaseOrderFichier );
                    nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          nbLignesTraitees ++;

          // PostSave ReferentielEntityPurchaseOrder
          // Start of user code 9b66b7fcf4f9d293deb148c7e41763e4

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
      LOG.info( "Import Referentiel PurchaseOrder, nombre de ligne(s) traitee(s): " + nbLignesTraitees );
      LOG.info( "Import Referentiel PurchaseOrder, nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
      LOG.info( "Import Referentiel PurchaseOrder, nombre de ligne(s) create: " + nbLignesCreate );
      LOG.info( "Import Referentiel PurchaseOrder, nombre de ligne(s) update: " + nbLignesUpdate );
      LOG.info( "Import Referentiel PurchaseOrder, nombre de ligne(s) delete: " + nbLignesDelete );
      LOG.info( "Import Referentiel PurchaseOrder, nombre de ligne(s) sans traitement (delete d'un non existant): " + nbLignesSansTraitement );
      LOG.info( "/** FIN Import Referentiel Entity PurchaseOrder **/\n" );

      // PostTraitement ReferentielEntityPurchaseOrder
      // Start of user code 1369adcdd08c3b6d3b5837e499287391

      // End of user code

   }

   // Methodes ReferentielEntityPurchaseOrder
   // Start of user code 2c448264c9cfd8296c6706f4d5a70578

   // End of user code


}
