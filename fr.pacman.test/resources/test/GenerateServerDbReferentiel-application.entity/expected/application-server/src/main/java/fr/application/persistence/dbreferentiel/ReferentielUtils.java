/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.persistence.dbreferentiel;

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
    private static final Logger c_LOG = LogManager.getLogger ( ReferentielUtils.class );

    
    // Start of user code Constantes ReferentielUtils

    // End of user code


   // ATTRIBUTS

    
    // Start of user code Attributs ReferentielUtils

    // End of user code


   // METHODES

   /**
    * Obtenir la multimap <String,String> correspondant à la String p_filterCriteria passée en parametre
    * @param String p_filterCriteria
    * @return MultiMap <String,String> v_mapFilterCriteria
    */
   public static MultiMap <String,String> splitFilterCriteria ( String p_filterCriteria )
   {
	   /** On declare la multimap qui servira de resultat */
	   MultiMap <String,String> v_mapFilterCriteria = new MultiValueMap <String,String>();

	   /** On transforme la chaine du type filterCrit1,filterCrit2,...,filterCritn en un tableau de chaine filterCrt, via le separateur ',' */
	   String [] v_listFilterCriteria = p_filterCriteria.split (",");

	   /** On parcoure le tableau de chaines de filterCriteria */
	   for (String v_valueCriteria : v_listFilterCriteria )
	   {
		   /** On transforme un filter criteria de type champN=valN en un tableau, via le separateur '='*/
		   String [] v_keyValueFilterCriteria = v_valueCriteria.split("=");
			
           /** Dans le cas 'champN=', on cree une entree multimap de cle champN, et de valeur chaine vide */
		   if ( ( v_keyValueFilterCriteria.length == 1 ) && ( v_valueCriteria.contains("=") ) )  
		   {
			   v_mapFilterCriteria.put ( v_keyValueFilterCriteria[0].trim() , "");
		   }
		   
		   /** Dans le cas normal 'champN=valN', on cree une entree multimap de cle champN, et de valeur valN */
		   if ( v_keyValueFilterCriteria.length == 2 ) 
		   {
			   v_mapFilterCriteria.put ( v_keyValueFilterCriteria[0].trim() , v_keyValueFilterCriteria[1].trim());
		   }
		   
		   /** Dans tous les autres cas (plus que 1 '=' dans le filtercriteria) on est dans un filter criteria erronné, on ne fait rien */		

	   }

	   /** On retourne la multimap générée */
	   return v_mapFilterCriteria;	   
   }


   /**
    * Ecrire dans un fichier la liste des relations en erreur du traitement en cours
    * @param String p_entete
    * @param List<String> p_sourceList
    * @param String p_fileName
    */
   public static void storeList ( String p_entete,  List<String> p_sourceList, String p_fileName ) 
   {
	   /** On declare la chaine de travail */
       StringBuilder v_stringBuilder = new StringBuilder();

	   /** On ajoute l'entete a la chaine de travail */       
       v_stringBuilder.append(p_entete);
       v_stringBuilder.append(System.lineSeparator());

       /** Pour tous les elements de la liste, on les ajoute a la chaine de travail */
       for (String p_listElement : p_sourceList)
       {
           v_stringBuilder.append(p_listElement);
           v_stringBuilder.append(System.lineSeparator());
       }

       /** On ecrit la chaine de travail dans le fichier */
       String p_listString = v_stringBuilder.toString().trim();
       byte[] p_listBytes = p_listString.getBytes(StandardCharsets.UTF_8);
	   try
	   {
       		Files.write(Paths.get(p_fileName), p_listBytes);
	   }
	   catch ( Exception v_exception ) 
	   {
	       /** En cas d erreur de traitement, on affiche une ligne d'erreur en log  */		   
    		c_LOG.error ( "Probleme d'ecriture dans le fichier de detail des lignes en erreur: "+ p_fileName, v_exception );
	   }	 
   }

   /**
    * Envoi un mail en cas d erreur dans un traitement de chargement de referentiel
    * @param String p_subject
    * @param String p_content
    */
   public static void sendErrorMail ( String p_subject, String p_content ) 
   {
		try 
		{
			/** On récupère le serveur d envoi de mail via variable d environnement */
			String v_mailServer = Parameters.getParameter( "dbreferentiel.mailserver", "" ); 
			
			/** On cree le message */
			Properties v_prop = System.getProperties();
			v_prop.put( "mail.smtp.host", v_mailServer );
			Session v_session = Session.getDefaultInstance( v_prop , null );
			Message v_message = new MimeMessage( v_session );

			/** On récupère l emetteur de mail via variable d environnement */
			String v_mailFrom = Parameters.getParameter( "dbreferentiel.mailfrom", "" );
			v_message.setFrom(new InternetAddress(v_mailFrom));
			
			/** On récupère le(s) destinataire(s) de mail via variable d environnement */
			String v_mailTo = Parameters.getParameter( "dbreferentiel.mailto", "" );			
			List <InternetAddress> v_listInternetAddress = new ArrayList<InternetAddress>();
			for (String v_dest : Arrays.asList( v_mailTo.split( ";" ) ) )
			{
				v_listInternetAddress.add(new InternetAddress( v_dest ));
			}
			InternetAddress v_tabInternetAdress[] = new InternetAddress [ v_listInternetAddress.size() ];
			v_message.setRecipients( Message.RecipientType.TO,v_listInternetAddress.toArray( v_tabInternetAdress ) );
			
			/** On remplit le sujet et le contenu d apres les parametrages de la fonction*/
			v_message.setSubject( p_subject );
			v_message.setText( p_content );
			
			/** On remplit les autres proprietes techniques du mail */
			v_message.setHeader( "X-Mailer", "Java" );
			v_message.setSentDate( new Date() );
			v_session.setDebug( false );

			/** On indique en logs les informations du message qui va etre envoye */
			c_LOG.info("sendErrorMail, pre envoi: "
					+"\nRELAIS SMTP= " + v_mailServer 
					+"\nFROM= " + v_mailFrom 
					+"\nDEST= " + v_mailTo
					+"\nSUBJECT= " + p_subject
					+"\nCONTENT= " + p_content
					);

			/** On envoit le mail */
			Transport.send( v_message );
		} 
		catch ( Exception v_exception ) 
		{
			/** En cas d'erreur, on affiche une erreur en log  */
			c_LOG.error( v_exception );
		} 
   }


   
   // Start of user code Methodes ReferentielUtils

   // End of user code

}
