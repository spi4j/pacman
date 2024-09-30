/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application.ws.rs;

import fr.spi4j.ws.rs.RsTokensConfigurator_Itf;
import fr.spi4j.ws.rs.RsTokensContainer;

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
	 * routage vers le serveur d'authentification / une erreur d'authentification 
     * ou, plus simplement, une erreur de validation.
	 * 
	 * Pour simplement contrôler la présence du paramètre (et non son contenu),
	 * utiliser le joker "*" en valeur de paramètre.
	 */
	@Override
	public void defineRequiredTokens(final RsTokensContainer p_tokensContainer) 
	{
		//RAS.
	}
}
