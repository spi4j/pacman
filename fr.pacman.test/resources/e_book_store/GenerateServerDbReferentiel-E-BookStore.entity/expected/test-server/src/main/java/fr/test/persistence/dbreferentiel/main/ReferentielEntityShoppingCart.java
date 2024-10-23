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
import fr.test.persistence.api.main.ShoppingCartColumnsEnum;
import fr.test.persistence.api.main.ShoppingCartDao;
import fr.test.persistence.api.main.ShoppingCartEntity;
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
 * Classe de chargement de referentiel pour l entite ShoppingCart.
 * @author safr@n
 */
public final class ReferentielEntityShoppingCart
{

    // CONSTANTES

    /** Le formater des dates lues dans les fichiers csv */
    private static final DateFormat DATEFORMATCSV = new SimpleDateFormat( "dd/MM/yyyy" );

    /** Le logger de la classe */
    private static final Logger LOG = LogManager.getLogger( ReferentielEntityShoppingCart.class );

    /** Le dao de l'entite ShoppingCart */
    private static final ShoppingCartDao SHOPPINGCARTDAO = TestParamPersistence.getUserPersistence().getShoppingCartDao();

    /** Le dao de l'entite User */
    private static final UserDao USERDAO = TestParamPersistence.getUserPersistence().getUserDao();

    
    // Start of user code 33f088be9b90705c66b3aed1ecbbef58

    // End of user code


   // ATTRIBUTS

   
   // Start of user code 14247063a47752c2421a281cb912f706

   // End of user code


   // METHODES

    /**
     * Constructeur.
     */
    private ReferentielEntityShoppingCart ()
    {
    }


   /**
    * Chargement référentiel de l'entité 'ShoppingCart'.
    */
   public static void chargeEntity ()
   {
      /** Initialisations, Log de début de traitement */
      String nomFicCsv = "";
      String nomFicCsvComplet = MainReferentiel.getRepCsv() + nomFicCsv;
      String physicalUnique = "";

      LOG.info( "/** DEBUT Import Referentiel Entity ShoppingCart **/" );
      LOG.info( "Import Referentiel ShoppingCart, nom du fichier: " + nomFicCsv );
      LOG.info( "Import Referentiel ShoppingCart, metadata PHYSICAL_UNIQUE: " + physicalUnique );
      final String fkCritereUser = "" ;
      LOG.info( "Import Referentiel ShoppingCart, Relation de User vers ShoppingCart, nom du fkCritere: " + fkCritereUser );

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
          throw new Spi4jValidationException( "Nom de fichier non renseigné dans la metaData FIC_CSV de ShoppingCart" );
        }
        if ( "".equals ( physicalUnique ) )
        {
          throw new Spi4jValidationException( "Contrainte d'unicité non renseignée dans la metaData PHYSICAL_UNIQUE de ShoppingCart" );
        }

        /** Initialisation  des noms des FK_CRITERE; exception si ils sont mal renseignés */
        ShoppingCartColumnsEnum shoppingCartColumnUser = null;
        UserColumnsEnum userColumnUser = null;
        if ( ( shoppingCartColumnUser == null ) || ( userColumnUser == null ) )
        {
          throw new Spi4jValidationException( "Metadata FK_CRITERE mal renseignée dans la relation de User vers ShoppingCart" );
        }
	    
        /** Ouverture du reader Csv, on ignore la premiere ligne qui correspond à l entete */
        CSVReader csvReader =  new CSVReader (new FileReader( nomFicCsvComplet), ';', '"', 1);
        String [] csvLine;

   	    
   	    // Start of user code 268650573f303b469e84eb2fb9ad4ba3

        // End of user code

        /** Parcours une à une des lignes du fichier Csv */
        while ( ( csvLine = csvReader.readNext() ) != null )
        {
          /** les variables de la boucle de chargement de fichier */
          int  cptrCriteria = 0;
          boolean suppressionEnregistrement = false;
          boolean filterEnregistrement = false;
          String xtopsup =  DatabaseLineStatus_Enum.deletedForNewReference.get_value();
          /** TableCriteria de recherche de l'entite existante 'ShoppingCart', à partir du critere d unicite '' */
          final TableCriteria <ShoppingCartColumnsEnum> tableCriteriaShoppingCart = new TableCriteria <> ("find by criteria en cherchant sur ShoppingCart");
          /** La variable entity renseignée par le contenu du fichier  */
          ShoppingCartEntity shoppingCartFichier = TestParamPersistence.getUserPersistence().getShoppingCartEntity();


          /** L'attribut status	*/
          shoppingCartFichier.setStatus(  StatusEnum.valueOf("PENDING")  );



          /** Chargement de la foreign key de 'ShoppingCart' vers 'User', à partir du fkCritere '' */
          final TableCriteria <UserColumnsEnum> tableCriteriaUser = new TableCriteria <> ("find by criteria en cherchant sur User");
          tableCriteriaUser.addOrderByDesc( UserColumnsEnum.dateDeDebut );
          tableCriteriaUser.addCriteria( userColumnUser, Operator_Enum.equals, csvLine[ shoppingCartColumnUser.ordinal() - 1 ] );
          final List <UserEntity> allUser =  USERDAO.findByCriteria( tableCriteriaUser );
          UserEntity entityUser = null;
          if ( allUser.size() > 0)
          {
            entityUser = allUser.get( 0 );
          }
          if ( entityUser != null )
          {
            shoppingCartFichier.setUserCarts_id( entityUser.getId() );
          }


          
          // Start of user code 71ff155bcb9d04bc2e7b85b66413d083

          // End of user code

          /** On recupere les enregistrements en base de l'entite ShoppingCart correspondant à la ligne lue */
          final List <ShoppingCartEntity> allShoppingCart =  SHOPPINGCARTDAO.findByCriteria( tableCriteriaShoppingCart );

          /** Enregistrement modification ou suppression selon le code modification et l'existant en base pour l'entite ShoppingCart */
          if (	filterEnregistrement )
          {
            nbLignesFiltrees ++;
          }
          else
          {
            if ( allShoppingCart.size() == 0 )
            {
                if ( suppressionEnregistrement == false )
                {
                    SHOPPINGCARTDAO.create( shoppingCartFichier );
                    nbLignesCreate ++;
                }
                else
                {
                    nbLignesSansTraitement ++;
                }
            }
            else
            {
                ShoppingCartEntity shoppingCartExistant = allShoppingCart.get(0);
                if ( suppressionEnregistrement == true )
                {
                    SHOPPINGCARTDAO.delete( shoppingCartExistant );
                    nbLignesDelete ++;
                }
                else
                {
                    shoppingCartFichier.setId( shoppingCartExistant.getId() );
                    SHOPPINGCARTDAO.update( shoppingCartFichier );
                    nbLignesUpdate ++;
                }
            }
          }
          /** Incrementation du nombre de lignes traitees */
          nbLignesTraitees ++;

          
          // Start of user code ac97af97ba88a52f5c3d3b5a62e8daca

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
      LOG.info( "Import Referentiel ShoppingCart, nombre de ligne(s) traitee(s): " + nbLignesTraitees );
      LOG.info( "Import Referentiel ShoppingCart, nombre de ligne(s) filtree(s): " + nbLignesFiltrees );
      LOG.info( "Import Referentiel ShoppingCart, nombre de ligne(s) create: " + nbLignesCreate );
      LOG.info( "Import Referentiel ShoppingCart, nombre de ligne(s) update: " + nbLignesUpdate );
      LOG.info( "Import Referentiel ShoppingCart, nombre de ligne(s) delete: " + nbLignesDelete );
      LOG.info( "Import Referentiel ShoppingCart, nombre de ligne(s) sans traitement (delete d'un non existant): " + nbLignesSansTraitement );
      LOG.info( "/** FIN Import Referentiel Entity ShoppingCart **/\n" );

      
      // Start of user code 9402bb4bec6749ccc21787e50271d2d3

      // End of user code

   }

   
   // Start of user code 60280f5a4e5afa6ab0692bc935dbfec3

   // End of user code


}
