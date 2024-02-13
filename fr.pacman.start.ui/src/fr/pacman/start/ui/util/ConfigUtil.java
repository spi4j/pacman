package fr.pacman.start.ui.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import fr.pacman.commons.services.LoggerUtils;
import fr.pacman.start.ui.Activator;

/**
 * 
 * @author MINARM
 *
 */
public class ConfigUtil {

	public static final String c_PROPERTIES_FILE_EXTENSION = ".properties";

	/**
	 * Constructeur privé pour éviter l'instanciation de la classe.
	 */
	private ConfigUtil() {
		// EMPTY.
	}

	/**
	 * Chargement d'une propriete a partir d'un fichier ".properties". Pour le
	 * starter de Pacman, on ne lance pas d'exception en cas de propriete non
	 * trouvee, on renvoi juste null avec des logs. Dans le cas du starter, toutes
	 * les proprietes sont traitees sous forme de chaines de caracteres.
	 * 
	 * @TODO : Mettre le fichier en memoire afin d'eviter de le charger a plusieurs
	 *       reprises.
	 * 
	 * @param p_file     le fichier de propriete a consulter.
	 * @param p_property l'identifiant de la propriete.
	 * @return
	 */
	public static String loadProperty(final String p_file, final String p_property) {

		final Properties v_properties = new Properties();
		String v_property = null;

		try {

			final Path v_path = new Path(File.separator + p_file + c_PROPERTIES_FILE_EXTENSION);
			final URL v_resourceUrl = FileLocator.find(Platform.getBundle(Activator.c_PLUGIN_ID), v_path, null);
			final InputStream v_inputStream = v_resourceUrl.openStream();
			v_properties.load(v_inputStream);
			v_property = v_properties.getProperty(p_property);

			if (null == v_property)
				LoggerUtils.warn(TextUtil.c_ERR_PROPERTY + " : " + p_property);

		} catch (Exception p_e) {
			LoggerUtils.warn("Erreur de chargement des propriétés : " + p_e.getMessage());
		}
		return v_property;
	}
}
