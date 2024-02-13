package fr.pacman.test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

class FileHelper4Test {

	// private static final String c_regex_comment =
	// "((['\"])(?:(?!\\2|\\\\).|\\\\.)*\\2)|\\/\\/[^\\n]*|\\/\\*(?:[^*]|\\*(?!\\/))*\\*\\/";
	// private static final String c_regex_comment =
	// "\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*+";

	// Le chemin d'execution pour le framework de test.
	private String _testFrameworkPath;

	// Le type de fichier a generer (fichier java, etc...).
	private FileType4Test_Enum _typeGenerate;

	// Le nom du module principal pour les tests.
	private static final String c_moduleFileName = "genFileFacade";

	// Chemin relatif du modele.
	private static final String c_pathModel = "resources/model/";

	// Chemin relatif du fichier (resultat) attendu.
	private static final String c_pathExpected = "resources-test/files-expected/";

	// Utilisee en mode LOW, MEDIUM.
	private static final String c_regex_blank_line = "(?m)^[ \t]*\r?\n";

	// Uilisee en mode LOW, MEDIUM.
	private static final String c_regex_comment = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";

	// Utilisee en mode LOW.
	private static final String c_regex_space = "\\s+";

	/**
	 * Constructeur prive.
	 */
	public FileHelper4Test(FileType4Test_Enum p_typeGenerate) {

		_typeGenerate = p_typeGenerate;
		_testFrameworkPath = getTestFrameworkPath();
	}

	/**
	 * Recuperer le chemin pour acceder aux fichier de configuration pour les tests.
	 * 
	 * @return le chemin du projet contenant les fichiers.
	 */
	private String getTestFrameworkPath() {

		String v_currentProjectPath = System.getProperty("user.dir");
		v_currentProjectPath = v_currentProjectPath.substring(0, v_currentProjectPath.lastIndexOf(File.separator));
		return v_currentProjectPath.trim();
	}

	/**
	 * Recuperer le corps du fichier attendu pour le test
	 * 
	 * @param p_Path : chemin du fichier
	 * @return corps du fichier
	 * @throws IOException RAS
	 */
	public String getFileBody(final String p_Path) throws IOException {

		InputStream v_Is = null;
		final StringBuilder v_Str = new StringBuilder();
		try {
			v_Is = new FileInputStream(p_Path);
			int v_Read;
			final byte[] v_Buffer = new byte[2048];
			while (v_Is.available() > 0) {
				v_Read = v_Is.read(v_Buffer);
				v_Str.append(new String(v_Buffer, 0, v_Read, "UTF-8"));
			}
		} catch (IOException e) {
			fail("Impossible de trouver le fichier de reference : " + p_Path);
		} finally {
			if (v_Is != null) {
				v_Is.close();
			}
		}
		return v_Str.toString();
	}

	/**
	 * 
	 * @param p_strToClean le contenu du fichier a traiter.
	 * @param p_useStrict
	 * @return
	 */
	public String cleanFile(final String p_strToClean, final Mode4Test_Enum p_mode) {

		if (Mode4Test_Enum.HIGHT == p_mode)
			return p_strToClean.trim();

		String v_strToClean = p_strToClean.replaceAll(c_regex_blank_line, "");

		try {
			v_strToClean = v_strToClean.replaceAll(c_regex_comment, "");

		} catch (StackOverflowError e) {
			// Rare mais peut arriver sur certains fichiers RAS pour l'instant.
		}

		if (Mode4Test_Enum.LOW == p_mode)
			v_strToClean = v_strToClean.replaceAll(c_regex_space, "");

		return v_strToClean.trim();
	}

	/**
	 * 
	 * @param p_packageName
	 * @return
	 */
	public String getModuleFullName(String p_packageName) {

		// Petite securite si oubli du dernier "/" dans le chemin.
		if (p_packageName.lastIndexOf("/") != p_packageName.length() - 1)
			p_packageName = p_packageName + "/";

		return p_packageName + c_moduleFileName;
	}

	/**
	 * 
	 * @param p_model
	 * @return
	 */
	public String getModelFullName(final String p_model) {

		return _testFrameworkPath + File.separator + "fr.pacman.test" + File.separator + c_pathModel + p_model;
	}

	/**
	 * 
	 * @param p_expectedFileName
	 * @return
	 */
	public String getExpectedFileFullName(final String p_expectedFileName) {

		return c_pathExpected + p_expectedFileName + _typeGenerate.get_expectedFileExtension();
	}

	/**
	 * 
	 * @param p_generatedFileName
	 * @return
	 */
	public String getGeneratedFileFullName(final String p_generatedFileName) {

		return p_generatedFileName + _typeGenerate.get_generatedFileExtension();
	}

	/**
	 * 
	 * @return
	 */
	public String getModelPath() {

		return _testFrameworkPath + File.separator + "fr.pacman.test" + File.separator + c_pathModel;
	}
}
