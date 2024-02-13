package fr.pacman.start.ui.util;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.widgets.Text;

public class FormUtil {

	private static final String c_PATTERN_NUMERIC = "[0-9[\b]]";
	private static final String c_PATTERN_PROJECT_KEY = "[a-zA-Z0-9[\b\\_]]";
	private static final String c_PATTERN_PACKAGE_KEY = "[a-z0-9[\b\\.\\_]]";
	private static final String c_PATTERN_SQL_OPTION_KEY = "[a-zA-Z0-9[\b\\_]]";
	private static final String c_PATTERN_SQL_SCHEMA_KEY = "[a-zA-Z0-9[\b\\.]]";
	private static final String c_PATTERN_SQL_PREFIX = "^$|^[a-zA-Z0-9]+(\\_){1}";
	private static final String c_PATTERN_SQL_SCHEMA = "^$|^[a-zA-Z0-9]+(\\.){1}";
	private static final String c_PATTERN_LIBRARY_KEY = "[a-zA-Z0-9[\b\\-\\_\\.]]";
	private static final String c_PATTERN_PACKAGE_NAME = "^[a-z]{2,3}(\\.([a-z0-9\\_]){1,}){1,}";
	private static final String c_PATTERN_LIBRARY_VERSION_KEY = "[a-zA-Z0-9[\b\\-\\.]]";

	/**
	 * Constructeur prive pour eviter l'instanciation de la classe.
	 */
	private FormUtil() {
		// EMPTY.
	}

	/**
	 * Verification de la validite du caractere saisi pour le nommage d'un projet.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForProjectName(final Character p_c) {
		return Pattern.matches(c_PATTERN_PROJECT_KEY, p_c.toString());
	}

	/**
	 * Verification de la validite du caractere saisi pour le nommage d'une
	 * librairie.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForLibraryName(final Character p_c) {
		return Pattern.matches(c_PATTERN_LIBRARY_KEY, p_c.toString());
	}

	/**
	 * Verification de la validite du caractere saisi pour le nommage d'un package.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForPackageName(final Character p_c) {
		return Pattern.matches(c_PATTERN_PACKAGE_KEY, p_c.toString());
	}

	/**
	 * Verification de la validite du caractere saisi pour un préfixe.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForPrefix(final Character p_c) {
		return Pattern.matches(c_PATTERN_SQL_OPTION_KEY, p_c.toString());
	}
	
	/**
	 * Verification de la validite du caractere saisi pour un schéma.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForSchema(final Character p_c) {
		return Pattern.matches(c_PATTERN_SQL_SCHEMA_KEY, p_c.toString());
	}
	
	/**
	 * Verification de la validite de la chaine saisie pour un préfixe.
	 * 
	 * @param p_s
	 * @return
	 */
	public static boolean checkValueForPrefix(final String p_s) {
		return Pattern.matches(c_PATTERN_SQL_PREFIX, p_s);
	}
	
	/**
	 * Verification de la validite de la chaine saisie pour un schéma.
	 * 
	 * @param p_s
	 * @return
	 */
	public static boolean checkValueForSchema(final String p_s) {
		return Pattern.matches(c_PATTERN_SQL_SCHEMA, p_s);
	}

	/**
	 * Verification de la validite de la chaine saisie pour le nommage d'un package.
	 * 
	 * @param p_s
	 * @return
	 */
	public static boolean checkValueForPackageName(final String p_s) {
		return Pattern.matches(c_PATTERN_PACKAGE_NAME, p_s);
	}

	/**
	 * Verification de la validite du caractere saisi pour un numero de version.
	 * 
	 * @return
	 */
	public static boolean checkKeyForLibraryVersion(final Character p_c) {
		return Pattern.matches(c_PATTERN_LIBRARY_VERSION_KEY, p_c.toString());
	}

	/**
	 * Verification de la validite du caractere saisi une valeur numerique.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForNumericValue(final Character p_c) {
		return Pattern.matches(c_PATTERN_NUMERIC, p_c.toString());
	}
	
	/**
	 * Verification de la validite du caractere saisi pour le nommage du tablespace
	 * des contraintes.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForSqlTableSpace(final Character p_c) {
		return Pattern.matches(c_PATTERN_SQL_OPTION_KEY, p_c.toString());
	}
	
	/**
	 * Verification de la validite du caractere saisi pour le nom d'une colonne SQL.
	 * 
	 * @param p_c
	 * @return
	 */
	public static boolean checkKeyForSqlColumnName(final Character p_c) {
		return Pattern.matches(c_PATTERN_SQL_OPTION_KEY, p_c.toString());
	}
	
	/**
	 * Vérifie que les deux version Oracles ne peuvent pas être cochées en même temps.
	 * 
	 * @param _bdNames
	 * @return
	 */
	public static boolean checkForOracleVersion (final List<String> _bdNames) {
		if(_bdNames.contains(PacmanConfig.c_ORACLE) && _bdNames.contains(PacmanConfig.c_ORACLE_32))
			return Boolean.FALSE;
		
		return Boolean.TRUE;
	}

	/**
	 * Construction automatique d'un nom de package a partir du nom de projet.
	 * 
	 * @param p_packageName
	 * @param p_projectName
	 */
	public static void completePackageName(final Text p_packageName, final Text p_projectName) {
		p_packageName.setText(p_packageName.getText().substring(0, p_packageName.getText().indexOf(".") + 1)
				.concat(p_projectName.getText().toLowerCase().replace("-", "_")));
	}
	
	/**
	 * Verifie la coherence pour la selection des services web.
	 * 
	 * @param p_ws
	 * @param p_wms
	 * @return
	 */
	public static boolean checkForWs(final boolean p_ws, final boolean p_wms) {
		return (p_ws && !p_wms) || (p_ws && p_wms) || (!p_ws && !p_wms) ;
	}

	/**
	 * Verifie si le projet existe deja. On ne se base pas sur la fonction exists()
	 * du IProject qui n'est pas complete mais directement sur la presence du
	 * fichier dans le repertoire. Cela permet ainsi de detecter les projets effaces
	 * mais toujour presents sur le disque..
	 * 
	 * @param p_appliNames
	 * @return
	 */
	public static boolean checkForNewProject(final String p_appliName) {

		IWorkspaceRoot v_root = ResourcesPlugin.getWorkspace().getRoot();

		if (null == p_appliName || p_appliName.isEmpty())
			return Boolean.TRUE;

		File v = new File(v_root.getLocationURI().getPath() + File.separator + p_appliName);

		if (v.exists())
			return Boolean.FALSE;

		return Boolean.TRUE;
	}
	
	/**
	 * Verifie si une base de donnees est bien selectionnee (en fonction des autres options).
	 * Tres simple, si pas d'utilisation de bdd, alors la liste est vide, si utilisation alors 
	 * la liste contient au moins la base H2, si elle ne contient que la base H2, alors c'est 
	 * qu'aucune autre base n'a ete selectionnee.
	 * 
	 * @param p_databases
	 * @param p_h2Embedded
	 * @return
	 */
	public static boolean checkForDatabase(final List<String> p_databases, final String p_h2Embedded) {
		
		if(p_databases.size() == 1 && ! Boolean.valueOf(p_h2Embedded))
			return Boolean.FALSE;
		
		return Boolean.TRUE;
	}
	
	/**
	 * Dans le cas d'une base embarquee, verifie qu'aucune base de donnees autre que H2 n'a ete selectionnee.
	 * 
	 * @param p_databases
	 * @param p_h2Embedded
	 * @return
	 */
	public static boolean checkForEmbeddedDatabase(final List<String> p_databases, final String p_h2Embedded) {
		
		if(Boolean.valueOf(p_h2Embedded) && p_databases.size() > 1)
			return Boolean.FALSE;
		
		return Boolean.TRUE;
	}
}
