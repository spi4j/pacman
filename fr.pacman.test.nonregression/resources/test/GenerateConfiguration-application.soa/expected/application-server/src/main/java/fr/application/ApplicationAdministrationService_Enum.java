/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.application;

import fr.spi4j.admin.AdministrationService_Itf;

/**
 * Services disponibles pour le servlet d'administration.
 */
enum ApplicationAdministrationService_Enum implements AdministrationService_Itf {

	INDEX("/", "index.html"),

	CLEAR_CACHES("/clearCaches", "clearCaches.html");

	private final String _path;

	private final String _servletContent;

	/**
	 * Constructeur
	 * 
	 * @param p_path
	 *            l'url de ce service
	 * @param p_servletContent
	 *            le chemin de la jsp a afficher a l'utilisateur (les jsp doivent se
	 *            trouver dans src/main/resources/fr/spi4j/admin/)
	 */
	private ApplicationAdministrationService_Enum(final String p_path, final String p_servletContent) {
		_path = p_path;
		_servletContent = p_servletContent;
	}

	@Override
	public String getServletContentPath() {
		return _servletContent;
	}

	/**
	 * Retrouve un service a partir de son url
	 * 
	 * @param p_path
	 *            l'url cherchee
	 * @return le service trouvee ou null si aucun service n'est defini a cette url
	 */
	public static ApplicationAdministrationService_Enum get_serviceFromPath (final String p_path) {
		for (final ApplicationAdministrationService_Enum v_service : values()) {
			if (v_service._path.equalsIgnoreCase(p_path)) {
				return v_service;
			}
		}
		return null;
	}
}
