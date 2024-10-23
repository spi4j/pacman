/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs;
// Start of user code for imports

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.ext.Provider;


import fr.spi4j.ws.rs.RsLogger;
import fr.spi4j.ws.rs.RsFilter_Abs;
import fr.spi4j.ws.rs.RsFilterConfigurator;
import fr.spi4j.ws.rs.RsAuthTokenXtoWrapper;
import fr.spi4j.ws.rs.RsTokensConfigurator_Itf;
import fr.spi4j.ws.rs.RsXto_Itf;
import io.jsonwebtoken.Claims;

// End of user code

/**
 * Filtre pour l'ensemble des resources de l'application. Ce filtre permet entre
 * autres, de gérer les en-têtes nécessaires pour la passerelle du Ministère des
 * Armées et de gérer la sécurisation des API (quel que soit le protocol
 * utilisé).
 * 
 * IMPORTANT : Ne jamais mettre d'annotation @PreMatching sur ce filtre. Ce
 * filtre nécessite la présence du matching afin de détecter la sécurisation
 * éventuelle d'une opération. Par ailleurs il s'occupe aussi du rajout
 * automatique des en-têtes du MINARM si besoin, et d'opérations de post appel
 * pour la ressource.
 *
 * @author safr@n.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApplicationGenericFilter extends RsFilter_Abs
{

	/**
	 * Récupération des clés pour les paramètres d'en-tête dans le cadre de la
	 * pagination. Par souci de simplification aucune Map n'est utilisée, mais cela
	 * implique de transmettre les éléments dans le bon ordre. Cet ordre est donc le
	 * suivant :
	 * 
	 * @apiNote Le nombre total d'éléments pour la requête.
	 * @apiNote Le nombre total de pages pour la requête.
	 * @apiNote Le nombre d'élements pour la page en cours.
	 * @apiNote Le numéro de la page courante.
	 * 
	 * @return Le tableau avec l'ensemble des éléments.
	 */
	@Override
	protected String[] initPaginatorHeaderKeys() {
		
		return new String[] {"Resource-Count","Page-Count",
			"Current-Page-Size","Current-Page"};
	}

	/**
	 * Récupération de la configuration pour le filtre de l'application.
	 *
	 * Il est possible de positionner l'application derrière une passerelle d'API.
	 * Par défaut le filtre est configuré pour fonctionner en mode standalone.
	 * Modifier l'énumération selon le besoin dans le fichier de configuration.
	 * 
	 * Il est possible de définir un préfixe pour l'ensemble des fichiers de
	 * configuration. Par défaut, le préfixe est le nom de l'application. A modifier
	 * selon la convenance.
	 * 
	 * @return Le chemin pour le(s) fichier(s) de configuration.
	 */
 	@Override
   	protected RsFilterConfigurator initFilterConfig ()
   	{
		
		// Start of user code init filter config

		return new RsFilterConfigurator("APPLICATION").set_debugMode();

		// End of user code
	}

	/**
	 * Récupération du configurateur pour l'ensemble des jetons utilisés par
	 * l'application. Le configurateur est transmis au framework spi4j pour
	 * traitement automatique des jetons.
	 * 
	 * @return Le configurateur de jetons.
	 */
	@Override
	protected RsTokensConfigurator_Itf initTokensConfig() 
	{
		
		// Start of user code init tokens config
		
		return new ApplicationTokensConfig();

		// End of user code
	}

	/**
	 * Appliquer ici le code pour la récupération de l'ensemble de informations qui
	 * ont été transférées par le biais des jetons (quelle que soit leur nature).
	 * Cette méthode est appelée uniquement une fois le(s) jeton(s) vérifié(s) et
	 * validé(s). Cette méthode est appelée a chaque traitement de jeton, utiliser
	 * l'identifiant interne du jeton pour différencier les traitements.
	 * 
	 * Dans le cas d'un jeton de rafraîchissement, si ce dernier a été falsifié,
	 * lancer une exception 'Runtime' de type RsUnexpectedException (par
	 * exemple...).
	 *
	 * @param p_spi4jId : Identifiant interne (spi4j) du jeton.
	 * @param p_claims : L'ensemble des informations stockées dans le jeton.
	 */
	@Override
	protected void provideClaimsToApplication(final String p_spi4jId, final Claims p_claims)
	{
      	
      	// Start of user code provide claims application

 		// End of user code
	}

	/**
	 * Appliquer ici le code nécessaire pour formaliser et renvoyer l'objet Json de
	 * retour pour le jeton d'accès qui a été regénéré.
	 * 
	 * A utiliser uniquement dans le cadre d'une sécurité de type API KEY !
	 *
	 * @return Le nouveau jeton d'accès.
	 */
	@Override
	protected RsXto_Itf buildRefreshedTokenResponse(RsAuthTokenXtoWrapper p_tokenWrapper) {
		
	    
	    // Start of user code build refreshed token reponse

		return null;

 		// End of user code	
	}
	
	/**
	 * Appliquer ici du code spécifique pour l'applicatif si besoin.
	 * Cette méthode est appelée avant l'execution de la ressource.
	 *
	 * @param : p_requestCtx :
	 * 					Le contexte de la requête.
	 */
	@Override
	protected void beforeFilter (final ContainerRequestContext p_requestCtx)
	{
		
		// Start of user code before filter

 		// End of user code
	}

	/**
	 * Appliquer ici du code spécifique pour l'applicatif si besoin.
	 * Cette méthode est appelée après l'execution de la ressource.
	 *
	 * @param p_responseCtx :
	 * 					Le contexte de la réponse.
	 */
	@Override
	protected void afterFilter (final ContainerResponseContext p_responseCtx)
	{
      	
      	// Start of user code after filter

 		// End of user code
	}

	/**
	 * Appliquer ici toutes les opérations à effectuer en cas d'erreur lors de
	 * l'initialisation du filtre.
	 * 
	 * @param p_exception : L'exception initiale ayant entrainé l'echec de
	 *                    l'initialisation du filtre.
	 */
	@Override
	protected void onInitializationFailure(Exception p_exception) 
	{
      	
      	// Start of user code on initialization failure

		RsLogger.get_log().fatal(p_exception, p_exception.getCause());

 		// End of user code
	}
}
