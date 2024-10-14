/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test.ws.rs.front.resources;
// Start of user code for imports



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
public abstract class TestFrontResourcesTestHelper {

	/**
	 * Le logger pour la classe de test.
	 */
	private final static Logger TESTLOG = LogManager.getLogger(TestFrontResourcesTestHelper.class);

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
	private TestFrontResourcesTestHelper(){
		//RAS.
	}

	/**
	 * Permet d'enregistrer le nom de la classe de test en cours dans le Helper.
	 * Utilisé pour le logger.
	 *
	 * @param <C> currentTestClassName : 
	 */
	protected static <C> void registerClass(final Class<C> currentTestClassName) {
	
		TestFrontResourcesTestHelper.currentTestClassName = 
				currentTestClassName.getName();
	}

	/**
	 * Permet d'enregistrer le nom de la méthode de test en cours dans le Helper.
	 * Utilisé pour le logger.
	 *
	 * @param currentTestMethodName : 
	 */
	protected static void registerMethod(final String currentTestMethodName) {
	
		TestFrontResourcesTestHelper.currentTestMethodName = 
				currentTestMethodName;
	}

	/**
	 * Positionne le jeton d'authentification dans le Helper.
	 *
	 * @param token :
	 *					Le jeton d'authentification à enregistrer temporairement.
	 */
	protected static void setAuthToken (final String token) {

		TestFrontResourcesTestHelper.token = token;
	}

	/**
 	 * Récupération d'un paramètre d'initialisation pour un service.
	 *
 	 * @param <T>
 	 * @param globalParam : 
	 *					Le paramètre global (potentiellement commun à plusieurs opérations).
	 *
 	 * @param localParam : 
	 *					Le paramètre local (spécifique à l'opération).
	 *
 	 * @return Le paramètre en fonction de la valeur null ou non.
 	 */
	protected static <T> T  setParam(T globalParam, 
		T localParam) {
		
		if(null != localParam) {
			return localParam; 
		}
		
		return globalParam;
	}

	/**
	 * Récupération du jeton d'authentification pour l'application (hors Passerelle PES).
	 *
	 * @return Le jeton d'authentification.
	 */
	protected static String getAuthToken() {

		return token;
	}



	/**
	 * Valeur binaire aléatoire pour les tests.
	 *
	 * @return An array of bytes.
	 */
	protected static byte[] getRandomBytes(final String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	              + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

	/**
	 * Ecriture de la pile des erreurs dans une chaine de caractères.
	 *
	 * @param exception :
     * @return La pile convertie en chaine de caractères.
	 */
	protected static String stackTraceToString (final Exception exception) {

		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * Affiche de manière intelligible l'exception dans la console pour aider au debogage.
	 *
	 * @param exception : 
	 * 						L'exception à afficher dans la console.		
	 */
	protected static void displayError(final TestFrontRsException exception)  {

		// Création d'une chaine de caractères lisible pour l'utilisateur.
		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(getPrettyOperationPrinting(currentTestMethodName));
		strBuilder.append(getPrettyHeadersPrinting(TestFrontContextHolder.getHeaders()));
		strBuilder.append(getPrettyErrorPrinting(exception));

		// Affichage de la chaine de caractères sur la console.
		TESTLOG.error(strBuilder.toString());

		fail("Message : " + exception.getMessage());
	}

	/**
	 * Affiche de manière intelligible l'exception dans la console pour aider au debogage.
	 *
	 * @param exception : 
	 * 						L'exception à afficher dans la console.	
	 */
	private static String getPrettyErrorPrinting(final TestFrontRsException exception)  {
	
		// for pretty error printing
		// Start of user code a5eb451c1fd531fdfc988f82a537db83

		final StringBuilder strBuilder = new StringBuilder();
		
		strBuilder.append("\n\nCode retour  : ").append(exception.getStatusCode());
		strBuilder.append(" - ").append(exception.getMessage()).append("\nURI          : ");
		strBuilder.append(exception.getCalledUri()).append("\nInformations : ");
		strBuilder.append(exception.getAdditionalInfo()).append("\n\n");
		
		return strBuilder.toString();
	
		// End of user code
	}

	/**
	 * Affiche de manière intelligible le retour de la ressource demandée.
	 * On sérialise à nouveau l'objet Java avec la librairie GSON pour 
	 * bénéficier de la récursivité totale sur la grappe d'objets.
	 * 
	 * @param entity : 
	 * 			L'objet à afficher sur la console.
	 *
	 * @param operation : 
	 * 			Le nom de l'opération (la méthode) testée pour affichage sur la console.
	 */
	protected static void displayResponse(final Object entity) {
		
		// for display response
		// Start of user code 321e903ed6f27d8ada10954d673166b2

		// Parsing de la grappe d'objets sur l'entité retournée.
		final Gson gsonParser = new GsonBuilder()
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
		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(getPrettyOperationPrinting(currentTestMethodName));
		strBuilder.append(getPrettyHeadersPrinting(TestFrontContextHolder.getHeaders()));
		strBuilder.append(getPrettyEntityPrinting(gsonParser.toJson(entity)));
		
		// Ecriture de la chaine complète sur la console.
		TESTLOG.info(strBuilder.toString());

		// End of user code
	}

	/**
	 * Affiche de manière intelligible des en-têtes pour la sortie 
	 * console dans le cadre du débogage.
	 * 
	 * @param headers : 
	 * 				Les en-têtes à afficher.
	 * @return La chaine de caractère formatée pour affichage dans la console.
	 */
	private static String getPrettyHeadersPrinting(final Headers headers) {
		
		// for pretty headers printing
		// Start of user code 5483a5d85eaba8f8574572b1518af968

		if(TestFrontConfiguration.isDebugDisplayHeaders()){
			// Création d'une chaine de caractères lisible pour l'utilisateur.
			final StringBuilder strBuilder = new StringBuilder();	
			
			// Affichage des en-têtes pour le contexte de la requête.
			strBuilder.append("\nEn-têtes : REQUEST   *************************************************");
			for (Entry<String, List<Object>> entry : headers
				.getRequestHeaders().entrySet() ){
				prettyHeadersPrinting(strBuilder, entry);
			}

			// Cas spécifique du serveur injoignable, aucun élément de réponse en provenance du serveur.
			if(null == headers.getResponseHeaders()) {
				strBuilder.append("\n**********************************************************************");
				return strBuilder.toString();
			}
			
			// Affichage des en-têtes pour le contexte de la réponse.
			strBuilder.append("\nEn-têtes : RESPONSE   *************************************************");
			for (Entry<String, List<String>> entry : headers
				.getResponseHeaders().entrySet() ){
				prettyHeadersPrinting(strBuilder, entry);
			}
			strBuilder.append("\n**********************************************************************");
			return strBuilder.toString();
		}
		return "";
	
		// End of user code
	}

	/**
	* Formattage des informations contenues dans l'en-tête.
	*
	* @param strBuilder : 
	*					Le builder pour l'aggrégation des données issues de l'en-tête.
	* @param entry :
	*					Les informations issues de l'en-tête.
	* @return Le builder.
	*/
	private static StringBuilder prettyHeadersPrinting(final StringBuilder strBuilder, 
			final Entry<String, ?> entry) {
	
		// for pretty headers printing builder
		// Start of user code ff05592c516f13d6fa84ce61c7744174

		strBuilder.append("\n           ");
		strBuilder.append(entry.getKey());
		strBuilder.append(" : ");
		strBuilder.append(entry.getValue());

		return strBuilder;

		// End of user code
	}

	/**
	 * Formattage des informations sur la méthode en cours de test.
	 *
	 * @return Une chaine de caractères pour l'affichage de la méthode testée.
	 */
	private static String getPrettyOperationPrinting(final String operation) {
		
		// for pretty operation printing
		// Start of user code 1017ff3bf1ef506215d39a27e4096552

		final StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("\n\n");
		strBuilder.append("**********************************************************************\n");
		strBuilder.append("Classe   : ").append(currentTestClassName).append(".\n");
		strBuilder.append("Méthode  : ").append(operation).append(".");
		
		if(!TestFrontConfiguration.isDebugDisplayHeaders()) {
			strBuilder.append("\n**********************************************************************");
		}

		return strBuilder.toString();

		// End of user code
	}

	/**
	 * La sérialisation GJSON effectue la majorité du travail mais dans 
	 * le cadre de l'affichage, on effectue quelques opérations supplémentaires.
	 *  
	 * @param jsonSerialization : 
	 * 				L'objet sérialisé Json par la librairie GJSON.
	 * 
	 * @return Une chaine de caractère formatée pour un affichage sur une console (ou dans un fichier).
	 */
	private static String getPrettyEntityPrinting(String jsonSerialization) {
		
		Matcher matcher = Pattern.compile("\"(.*)\":").matcher(jsonSerialization);
		
		if (TestFrontConfiguration.isDebugDisplayAttrUpper()) {
			
			StringBuffer strBuffer = new StringBuffer();
			while (matcher.find()) {
				matcher.appendReplacement(strBuffer, 
					matcher.group().toUpperCase());
			}		
			matcher.appendTail(strBuffer);
			jsonSerialization = strBuffer.toString();
		}
		 
		// for pretty entity printing
		// Start of user code 44e054c8405261345b0f5717d98fc663

		return jsonSerialization
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
		
		// for new record
		// Start of user code fb502e183823bc486278fee3d0942f80

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<!NLE>\n$1  -----------------------------------------------\n");
		strBuilder.append("$1  ---------- NOUVEL ELEMENT DE LISTE ------------\n");
		strBuilder.append("$1  -----------------------------------------------</!NLE>");
		return strBuilder.toString();

		// End of user code
	}

	/**
	 * Affiche une ligne spécifique pour afficher le premier élément dans une liste.
	 * 
	 * @return La ligne pour afficher le premier élément dans la liste.
	 */
	private static String firstRecord() {
		
		// Start of user code for new record

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("$1").append("<!NLE>$2  -----------------------------------------------\n");
		strBuilder.append("$2  ---------- PREMIER ELEMENT DE LISTE -----------\n");
		strBuilder.append("$2  -----------------------------------------------</!NLE>");
		return strBuilder.toString();

		// End of user code
	}
}
