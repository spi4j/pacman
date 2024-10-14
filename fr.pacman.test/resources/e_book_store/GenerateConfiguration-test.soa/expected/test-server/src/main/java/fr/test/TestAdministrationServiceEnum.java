/**
 * (C) Copyright Ministere des Armees (France)
 *
 * Apache License 2.0
 */
package fr.test;

import fr.spi4j.admin.AdministrationService_Itf;

/**
 * Services disponibles pour le servlet d'administration.
 */
enum TestAdministrationServiceEnum implements AdministrationService_Itf {

	INDEX("/", "index.html"),

	CLEAR_CACHES("/clearCaches", "clearCaches.html");

	private final String path;

	private final String servletContent;

	/**
	 * Constructeur
	 * 
	 * @param path
	 *            l'url de ce service
	 * @param servletContent
	 *            le chemin de la jsp a afficher a l'utilisateur (les jsp doivent se
	 *            trouver dans src/main/resources/fr/spi4j/admin/)
	 */
	private TestAdministrationServiceEnum(final String path, final String servletContent) {
		this.path = path;
		this.servletContent = servletContent;
	}

	@Override
	public String getServletContentPath() {
		return servletContent;
	}

	/**
	 * Retrouve un service a partir de son url
	 * 
	 * @param path
	 *            l'url cherchee
	 * @return le service trouvee ou null si aucun service n'est defini a cette url
	 */
	public static TestAdministrationServiceEnum getServiceFromPath (final String path) {
		for (final TestAdministrationServiceEnum service : values()) {
			if (service.path.equalsIgnoreCase(path)) {
				return service;
			}
		}
		return null;
	}
}
