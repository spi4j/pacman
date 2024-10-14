/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.persistence.dbreferentiel;

// Start of user code for imports

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import fr.spi4j.Parameters;

// End of user code

/**
 * Classe d utilitaires pour le chargement des fichiers dans le referentiel
 */
public class ReferentielUtils
{

	// CONSTANTES

	/**  Le logger  */
    private static final Logger LOG = LogManager.getLogger ( ReferentielUtils.class );

    // Constantes ReferentielUtils
    // Start of user code a91407e32ebd31d0cccb024d0008a523

    // End of user code


   // ATTRIBUTS

    // Attributs ReferentielUtils
    // Start of user code c563399958d4f4540586ab6cac200c62

    // End of user code


   // METHODES

   /**
    * Obtenir la multimap <String,String> correspondant à la String filterCriteria passée en parametre
    * @param String filterCriteria
    * @return MultiMap <String,String> mapFilterCriteria
    */
   public static MultiMap <String,String> splitFilterCriteria ( String filterCriteria )
   {
	   /** On declare la multimap qui servira de resultat */
	   MultiMap <String,String> mapFilterCriteria = new MultiValueMap <String,String>();

	   /** On transforme la chaine du type filterCrit1,filterCrit2,...,filterCritn en un tableau de chaine filterCrt, via le separateur ',' */
	   String [] listFilterCriteria = filterCriteria.split (",");

	   /** On parcoure le tableau de chaines de filterCriteria */
	   for (String valueCriteria : listFilterCriteria )
	   {
		   /** On transforme un filter criteria de type champN=valN en un tableau, via le separateur '='*/
		   String [] keyValueFilterCriteria = valueCriteria.split("=");
			
           /** Dans le cas 'champN=', on cree une entree multimap de cle champN, et de valeur chaine vide */
		   if ( ( keyValueFilterCriteria.length == 1 ) && ( valueCriteria.contains("=") ) )  
		   {
			   mapFilterCriteria.put ( keyValueFilterCriteria[0].trim() , "");
		   }
		   
		   /** Dans le cas normal 'champN=valN', on cree une entree multimap de cle champN, et de valeur valN */
		   if ( keyValueFilterCriteria.length == 2 ) 
		   {
			   mapFilterCriteria.put ( keyValueFilterCriteria[0].trim() , keyValueFilterCriteria[1].trim());
		   }
		   
		   /** Dans tous les autres cas (plus que 1 '=' dans le filtercriteria) on est dans un filter criteria erronné, on ne fait rien */		

	   }

	   /** On retourne la multimap générée */
	   return mapFilterCriteria;	   
   }


   /**
    * Ecrire dans un fichier la liste des relations en erreur du traitement en cours
    * @param String entete
    * @param List<String> sourceList
    * @param String fileName
    */
   public static void storeList ( String entete,  List<String> sourceList, String fileName ) 
   {
	   /** On declare la chaine de travail */
       StringBuilder stringBuilder = new StringBuilder();

	   /** On ajoute l'entete a la chaine de travail */       
       stringBuilder.append(entete);
       stringBuilder.append(System.lineSeparator());

       /** Pour tous les elements de la liste, on les ajoute a la chaine de travail */
       for (String listElement : sourceList)
       {
           stringBuilder.append(listElement);
           stringBuilder.append(System.lineSeparator());
       }

       /** On ecrit la chaine de travail dans le fichier */
       String listString = stringBuilder.toString().trim();
       byte[] listBytes = listString.getBytes(StandardCharsets.UTF_8);
	   try
	   {
       		Files.write(Paths.get(fileName), listBytes);
	   }
	   catch ( Exception exception ) 
	   {
	       /** En cas d erreur de traitement, on affiche une ligne d'erreur en log  */		   
    		LOG.error ( "Probleme d'ecriture dans le fichier de detail des lignes en erreur: "+ fileName, exception );
	   }	 
   }

   /**
    * Envoi un mail en cas d erreur dans un traitement de chargement de referentiel
    * @param String subject
    * @param String content
    */
   public static void sendErrorMail ( String subject, String content ) 
   {
		try 
		{
			/** On récupère le serveur d envoi de mail via variable d environnement */
			String mailServer = Parameters.getParameter( "dbreferentiel.mailserver", "" ); 
			
			/** On cree le message */
			Properties prop = System.getProperties();
			prop.put( "mail.smtp.host", mailServer );
			Session session = Session.getDefaultInstance( prop , null );
			Message message = new MimeMessage( session );

			/** On récupère l emetteur de mail via variable d environnement */
			String mailFrom = Parameters.getParameter( "dbreferentiel.mailfrom", "" );
			message.setFrom(new InternetAddress(mailFrom));
			
			/** On récupère le(s) destinataire(s) de mail via variable d environnement */
			String mailTo = Parameters.getParameter( "dbreferentiel.mailto", "" );			
			List <InternetAddress> listInternetAddress = new ArrayList<InternetAddress>();
			for (String dest : Arrays.asList( mailTo.split( ";" ) ) )
			{
				listInternetAddress.add(new InternetAddress( dest ));
			}
			InternetAddress tabInternetAdress[] = new InternetAddress [ listInternetAddress.size() ];
			message.setRecipients( Message.RecipientType.TO,listInternetAddress.toArray( tabInternetAdress ) );
			
			/** On remplit le sujet et le contenu d apres les parametrages de la fonction*/
			message.setSubject( subject );
			message.setText( content );
			
			/** On remplit les autres proprietes techniques du mail */
			message.setHeader( "X-Mailer", "Java" );
			message.setSentDate( new Date() );
			session.setDebug( false );

			/** On indique en logs les informations du message qui va etre envoye */
			LOG.info("sendErrorMail, pre envoi: "
					+"\nRELAIS SMTP= " + mailServer 
					+"\nFROM= " + mailFrom 
					+"\nDEST= " + mailTo
					+"\nSUBJECT= " + subject
					+"\nCONTENT= " + content
					);

			/** On envoit le mail */
			Transport.send( message );
		} 
		catch ( Exception exception ) 
		{
			/** En cas d'erreur, on affiche une erreur en log  */
			LOG.error( exception );
		} 
   }


   // Methodes ReferentielUtils
   // Start of user code 11db85b0230d2756824db4bd87822d99

   // End of user code

}
