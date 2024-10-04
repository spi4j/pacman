/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs;
// Start of user code for imports

import fr.spi4j.ws.rs.RsAuthProtocol_Enum;
import fr.spi4j.ws.rs.RsSigningAlgo_Enum;
import fr.spi4j.ws.rs.RsSigningKeysLoad_Enum;
import fr.spi4j.ws.rs.RsToken;
import fr.spi4j.ws.rs.RsTokensConfigurator_Itf;
import fr.spi4j.ws.rs.RsTokensContainer;

// End of user code
/**
 * Paramétrage des différents jetons utilisés par l'application.
 * @author safr@n.
 */
public class ApplicationTokensConfig implements RsTokensConfigurator_Itf {

	/**
	 * Insérer dans le conteneur de jetons la liste des différents jetons à faire
	 * automatiquement contrôler par le filtre.
	 * 
	 * Toute erreur de validation sur un des paramètres définis dans cette méthode
	 * entrainera la non validation du jeton et, selon le protocole utilisé un
	 * routage vers le serveur d'authentification.
	 * 
	 * De nombreuses propriétés sont stockées dans le fichier de configuration
	 * "safranTokens.properties" mais le filtre ne va pas de lui-même remplir
	 * automatiquement les différentes propriétés pour le(s) jeton(s) à partir de ce
	 * fichier. Par défaut c'est donc le builder du jeton qui s'occupe de ce
	 * chargement avec les différentes clés présentes dans le fichier.
	 * 
	 * L'intérêt de cette indirection supplémentaire est de pouvoir se passer
	 * complétement du fichier de configuration ou uniquement de certaines clés du
	 * fichier selon la volontée du développeur, et de rentrer directement les
	 * valeurs finales par l'intermédiaire du builder.
	 * 
	 * Pour simplement contrôler la présence du paramètre (et non son contenu),
	 * utiliser le joker "*" en valeur de paramètre.
	 */
	@Override
	public void defineRequiredTokens(final RsTokensContainer p_tokensContainer) 
	{
		// define required tokens
		// Start of user code define required tokens

		// End of user code

	}
}
