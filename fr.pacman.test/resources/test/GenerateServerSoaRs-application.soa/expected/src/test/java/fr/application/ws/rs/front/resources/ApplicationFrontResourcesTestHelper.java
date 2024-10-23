/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs.front.resources;
// Start of user code for imports

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import fr.application.ws.rs.front.ApplicationClientFactory;
import fr.application.ws.rs.front.ApplicationFrontConfiguration;
import fr.application.ws.rs.front.ApplicationFrontContextHolder;
import fr.application.ws.rs.front.ApplicationFrontContextHolder.Headers;
import fr.application.ws.rs.front.ApplicationTokensConfiguration;
import fr.application.ws.rs.front.exceptions.ApplicationFrontRsException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import static org.junit.jupiter.api.Assertions.fail;

// End of user code

/**
 * Classe utilitaire pour faciliter l'ensemble des tests JUnit. 
 * Elle permet principalement :
 *  
 *  - De gérer automatiquement la récupération d'un jeton d'authentification si le service est sécurisé.
 *  - De gérer un affichage console pour faciliter le débogage de l'Api avant packaging. 
 * 
 * @author safr@n
 */
public abstract class ApplicationFrontResourcesTestHelper {

	/**
	 * Le logger pour la classe de test.
	 */
	private final static Logger c_testLog = LogManager.getLogger(ApplicationFrontResourcesTestHelper.class);

	/**
	 * Stockage du nom de la classe et de la méthode de test en cours (pas de multithreading).
	 */
	private static String currentTestClassName;
	private static String currentTestMethodName;

	/**
	 * Stockage temporaire du jeton pour l'authentification.
	 */
	private static String token;

	/**
	 * Constructeur privé (classe utilitaire).
	 */
	private ApplicationFrontResourcesTestHelper(){
		//RAS.
	}

	/**
	 * Permet d'enregistrer le nom de la classe de test en cours dans le Helper.
	 * Utilisé pour le logger.
	 *
	 * @param <C> p_currentTestClassName : 
	 */
	protected static <C> void registerClass(final Class<C> p_currentTestClassName) {
	
		ApplicationFrontResourcesTestHelper.currentTestClassName = 
				p_currentTestClassName.getName();
	}

	/**
	 * Permet d'enregistrer le nom de la méthode de test en cours dans le Helper.
	 * Utilisé pour le logger.
	 *
	 * @param p_currentTestMethodName : 
	 */
	protected static void registerMethod(final String p_currentTestMethodName) {
	
		ApplicationFrontResourcesTestHelper.currentTestMethodName = 
				p_currentTestMethodName;
	}

	/**
	 * Positionne le jeton d'authentification dans le Helper.
	 *
	 * @param p_token :
	 *					Le jeton d'authentification à enregistrer temporairement.
	 */
	protected static void set_authToken (final String p_token) {

		ApplicationFrontResourcesTestHelper.token = p_token;
	}

	/**
 	 * Récupération d'un paramètre d'initialisation pour un service.
	 *
 	 * @param <T>
 	 * @param p_globalParam : 
	 *					Le paramètre global (potentiellement commun à plusieurs opérations).
	 *
 	 * @param p_localParam : 
	 *					Le paramètre local (spécifique à l'opération).
	 *
 	 * @return Le paramètre en fonction de la valeur null ou non.
 	 */
	protected static <T> T  set_param(T p_globalParam, 
		T p_localParam) {
		
		if(null != p_localParam) {
			return p_localParam; 
		}
		
		return p_globalParam;
	}

	/**
	 * Récupération du jeton d'authentification pour l'application (hors Passerelle PES).
	 *
	 * @return Le jeton d'authentification.
	 */
	protected static String get_authToken() {

		return token;
	}



	/**
	 * Valeur binaire aléatoire pour les tests.
	 *
	 * @return An array of bytes.
	 */
	protected static byte[] get_randomBytes(final String p_s) {
	    int len = p_s.length();
	    byte[] v_data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        v_data[i / 2] = (byte) ((Character.digit(p_s.charAt(i), 16) << 4)
	              + Character.digit(p_s.charAt(i+1), 16));
	    }
	    return v_data;
	}

	/**
	 * Ecriture de la pile des erreurs dans une chaine de caractères.
	 *
	 * @param p_exception :
     * @return La pile convertie en chaine de caractères.
	 */
	protected static String stackTraceToString (final Exception p_exception) {

		final StringWriter v_sw = new StringWriter();
		final PrintWriter v_pw = new PrintWriter(v_sw);
		p_exception.printStackTrace(v_pw);
		return v_sw.toString();
	}

	/**
	 * Affiche de manière intelligible l'exception dans la console pour aider au debogage.
	 *
	 * @param p_exception : 
	 * 						L'exception à afficher dans la console.		
	 */
	protected static void displayError(final ApplicationFrontRsException p_exception)  {

		// Création d'une chaine de caractères lisible pour l'utilisateur.
		final StringBuilder v_strBuilder = new StringBuilder();
		v_strBuilder.append(get_prettyOperationPrinting(currentTestMethodName));
		v_strBuilder.append(get_prettyHeadersPrinting(ApplicationFrontContextHolder.get_headers()));
		v_strBuilder.append(get_prettyErrorPrinting(p_exception));

		// Affichage de la chaine de caractères sur la console.
		c_testLog.error(v_strBuilder.toString());

		fail("Message : " + p_exception.getMessage());
	}

	/**
	 * Affiche de manière intelligible l'exception dans la console pour aider au debogage.
	 *
	 * @param p_exception : 
	 * 						L'exception à afficher dans la console.	
	 */
	private static String get_prettyErrorPrinting(final ApplicationFrontRsException p_exception)  {
	
		
		// Start of user code for pretty error printing

		final StringBuilder v_strBuilder = new StringBuilder();
		
		v_strBuilder.append("\n\nCode retour  : ").append(p_exception.get_statusCode());
		v_strBuilder.append(" - ").append(p_exception.getMessage()).append("\nURI          : ");
		v_strBuilder.append(p_exception.get_calledUri()).append("\nInformations : ");
		v_strBuilder.append(p_exception.get_additionalInfo()).append("\n\n");
		
		return v_strBuilder.toString();
	
		// End of user code
	}

	/**
	 * Affiche de manière intelligible le retour de la ressource demandée.
	 * On sérialise à nouveau l'objet Java avec la librairie GSON pour 
	 * bénéficier de la récursivité totale sur la grappe d'objets.
	 * 
	 * @param p_entity : 
	 * 			L'objet à afficher sur la console.
	 *
	 * @param p_operation : 
	 * 			Le nom de l'opération (la méthode) testée pour affichage sur la console.
	 */
	protected static void displayResponse(final Object p_entity) {
		
		
		// Start of user code for display response

		// Parsing de la grappe d'objets sur l'entité retournée.
		final Gson v_gsonParser = new GsonBuilder()
				.generateNonExecutableJson()
				.serializeSpecialFloatingPointValues()
				.enableComplexMapKeySerialization()
				.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE_WITH_SPACES)
				.setLongSerializationPolicy(LongSerializationPolicy.STRING)
				.setDateFormat("dd-MM-yyyy'T'HH:mm:ssXXX")
				.setPrettyPrinting()
				.serializeNulls()
				.create(); 

		// Création d'une chaine de caractères lisible pour l'utilisateur.
		final StringBuilder v_strBuilder = new StringBuilder();
		v_strBuilder.append(get_prettyOperationPrinting(currentTestMethodName));
		v_strBuilder.append(get_prettyHeadersPrinting(ApplicationFrontContextHolder.get_headers()));
		v_strBuilder.append(get_prettyEntityPrinting(v_gsonParser.toJson(p_entity)));
		
		// Ecriture de la chaine complète sur la console.
		c_testLog.info(v_strBuilder.toString());

		// End of user code
	}

	/**
	 * Affiche de manière intelligible des en-têtes pour la sortie 
	 * console dans le cadre du débogage.
	 * 
	 * @param p_headers : 
	 * 				Les en-têtes à afficher.
	 * @return La chaine de caractère formatée pour affichage dans la console.
	 */
	private static String get_prettyHeadersPrinting(final Headers p_headers) {
		
		
		// Start of user code for pretty headers printing

		if(ApplicationFrontConfiguration.isDebugDisplayHeaders()){
			// Création d'une chaine de caractères lisible pour l'utilisateur.
			final StringBuilder v_strBuilder = new StringBuilder();	
			
			// Affichage des en-têtes pour le contexte de la requête.
			v_strBuilder.append("\nEn-têtes : REQUEST   *************************************************");
			for (Entry<String, List<Object>> v_entry : p_headers
				.get_requestHeaders().entrySet() ){
				prettyHeadersPrinting(v_strBuilder, v_entry);
			}

			// Cas spécifique du serveur injoignable, aucun élément de réponse en provenance du serveur.
			if(null == p_headers.get_responseHeaders()) {
				v_strBuilder.append("\n**********************************************************************");
				return v_strBuilder.toString();
			}
			
			// Affichage des en-têtes pour le contexte de la réponse.
			v_strBuilder.append("\nEn-têtes : RESPONSE   *************************************************");
			for (Entry<String, List<String>> v_entry : p_headers
				.get_responseHeaders().entrySet() ){
				prettyHeadersPrinting(v_strBuilder, v_entry);
			}
			v_strBuilder.append("\n**********************************************************************");
			return v_strBuilder.toString();
		}
		return "";
	
		// End of user code
	}

	/**
	* Formattage des informations contenues dans l'en-tête.
	*
	* @param p_strBuilder : 
	*					Le builder pour l'aggrégation des données issues de l'en-tête.
	* @param p_entry :
	*					Les informations issues de l'en-tête.
	* @return Le builder.
	*/
	private static StringBuilder prettyHeadersPrinting(final StringBuilder p_strBuilder, 
			final Entry<String, ?> p_entry) {
	
		
		// Start of user code for pretty headers printing builder

		p_strBuilder.append("\n           ");
		p_strBuilder.append(p_entry.getKey());
		p_strBuilder.append(" : ");
		p_strBuilder.append(p_entry.getValue());

		return p_strBuilder;

		// End of user code
	}

	/**
	 * Formattage des informations sur la méthode en cours de test.
	 *
	 * @return Une chaine de caractères pour l'affichage de la méthode testée.
	 */
	private static String get_prettyOperationPrinting(final String p_operation) {
		
		
		// Start of user code for pretty operation printing

		final StringBuilder v_strBuilder = new StringBuilder();
		v_strBuilder.append("\n\n");
		v_strBuilder.append("**********************************************************************\n");
		v_strBuilder.append("Classe   : ").append(currentTestClassName).append(".\n");
		v_strBuilder.append("Méthode  : ").append(p_operation).append(".");
		
		if(!ApplicationFrontConfiguration.isDebugDisplayHeaders()) {
			v_strBuilder.append("\n**********************************************************************");
		}

		return v_strBuilder.toString();

		// End of user code
	}

	/**
	 * La sérialisation GJSON effectue la majorité du travail mais dans 
	 * le cadre de l'affichage, on effectue quelques opérations supplémentaires.
	 *  
	 * @param p_jsonSerialization : 
	 * 				L'objet sérialisé Json par la librairie GJSON.
	 * 
	 * @return Une chaine de caractère formatée pour un affichage sur une console (ou dans un fichier).
	 */
	private static String get_prettyEntityPrinting(String p_jsonSerialization) {
		
		Matcher v_matcher = Pattern.compile("\"(.*)\":").matcher(p_jsonSerialization);
		
		if (ApplicationFrontConfiguration.isDebugDisplayAttrUpper()) {
			
			StringBuffer v_strBuffer = new StringBuffer();
			while (v_matcher.find()) {
				v_matcher.appendReplacement(v_strBuffer, 
					v_matcher.group().toUpperCase());
			}		
			v_matcher.appendTail(v_strBuffer);
			p_jsonSerialization = v_strBuffer.toString();
		}
		 
		
		// Start of user code for pretty entity printing

		return p_jsonSerialization
				.trim()
				.replace(")]}'\n{", "\n\r")
				.replaceAll("\"(.*)\":", "$1 :")
				.replaceAll("\n( *)\\},\n *\\{", newRecord())
				.replaceAll("(\\[ *\\n)( *)\\{", firstRecord())
				.replaceAll("[\\{\\}]", "")
				.replaceAll(",(\n)", "$1")
				.replaceAll("(null),", "$1")
				.replaceAll("\n *\n", "\n")
				.replaceAll("(\n *)_", "$1") 		//Si norme SAFRAN.
				.replaceAll("<!NLE>|</!NLE>","\n") 	//Espacement pour les listes.
				.replaceAll("\\}$", "");

		// End of user code
	}
	
	/**
	 * Affiche une ligne spécifique pour afficher un nouvel élément dans une liste.
	 * 
	 * @return La ligne pour afficher le nouvel élément dans la liste.
	 */
	private static String newRecord() {
		
		
		// Start of user code for new record

		StringBuilder v_strBuilder = new StringBuilder();
		v_strBuilder.append("<!NLE>\n$1  -----------------------------------------------\n");
		v_strBuilder.append("$1  ---------- NOUVEL ELEMENT DE LISTE ------------\n");
		v_strBuilder.append("$1  -----------------------------------------------</!NLE>");
		return v_strBuilder.toString();

		// End of user code
	}

	/**
	 * Affiche une ligne spécifique pour afficher le premier élément dans une liste.
	 * 
	 * @return La ligne pour afficher le premier élément dans la liste.
	 */
	private static String firstRecord() {
		
		// Start of user code for new record

		StringBuilder v_strBuilder = new StringBuilder();
		v_strBuilder.append("$1").append("<!NLE>$2  -----------------------------------------------\n");
		v_strBuilder.append("$2  ---------- PREMIER ELEMENT DE LISTE -----------\n");
		v_strBuilder.append("$2  -----------------------------------------------</!NLE>");
		return v_strBuilder.toString();

		// End of user code
	}
}
