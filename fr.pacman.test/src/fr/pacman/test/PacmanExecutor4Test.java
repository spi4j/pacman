package fr.pacman.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import fr.pacman.commons.properties.PacmanPropertiesManager;

/**
 * Classe de test qui contient les tests JUnit : - on teste un template et il
 * est necessaire d'avoir un fichier pour le resultat attendu.
 * 
 * !!! En 'Production' les imports sont ensuite soumis a un CTRL + SHIFT + O ce qui n'est 
 * pas le cas ici dans le cadre des tests. !!!!!
 *
 * @author MINARM
 */
public abstract class PacmanExecutor4Test {

	/**
	 * Mode de fonctionnement pour les tests : comparaison stricte, moyenne, ou plus
	 * laxiste (non prise en compte des commentaires, des espaces, des lignes vides,
	 * etc...)
	 */
	private static Mode4Test_Enum _mode = Mode4Test_Enum.LOW;

	/**
	 * Constructeur prive.
	 */
	private PacmanExecutor4Test() {
		super();
	}

	/**
	 * Demande un test pour la generation de fichiers java (par defaut on teste des
	 * generations .java).
	 */
	public static void doTestGen(final String p_packageName, final String p_templateName, final String p_modelName,
			final String p_fileName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, null, FileType4Test_Enum.JAVA);
	}

	/**
	 * Demande un test pour la generation de fichiers java (par defaut on teste des
	 * generations .java) en specifiant un nom de fichier genere different.
	 */
	public static void doTestGen(final String p_packageName, final String p_templateName, final String p_modelName,
			final String p_fileName, final String p_fileGeneratedName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, p_fileGeneratedName,
				FileType4Test_Enum.JAVA);
	}

	/**
	 * Demande un test pour la generation de fichiers xml.
	 */
	public static void doTestGenForXml(final String p_packageName, final String p_templateName,
			final String p_modelName, final String p_fileName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, null, FileType4Test_Enum.XML);
	}

	/**
	 * Demande un test pour la generation de fichiers xml en specifiant un nom de
	 * fichier genere different.
	 */
	public static void doTestGenForXml(final String p_packageName, final String p_templateName,
			final String p_modelName, final String p_fileName, final String p_fileGeneratedName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, p_fileGeneratedName,
				FileType4Test_Enum.XML);
	}

	/**
	 * Demande un test pour la generation de fichiers sql.
	 */
	public static void doTestGenForSql(final String p_packageName, final String p_templateName,
			final String p_modelName, final String p_fileName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, null, FileType4Test_Enum.SQL);
	}

	/**
	 * Demande un test pour la generation de fichiers sql en specifiant un nom de
	 * fichier genere different.
	 */
	public static void doTestGenForSql(final String p_packageName, final String p_templateName,
			final String p_modelName, final String p_fileName, final String p_fileGeneratedName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, p_fileGeneratedName,
				FileType4Test_Enum.SQL);
	}

	/**
	 * Demande un test pour la generation de fichiers xhtml.
	 */
	public static void doTestGenForXhtml(final String p_packageName, final String p_templateName,
			final String p_modelName, final String p_fileName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, null, FileType4Test_Enum.XHTML);
	}

	/**
	 * Demande un test pour la generation de fichiers xhtml en specifiant un nom de
	 * fichier "Expected" different.
	 */
	public static void doTestGenForXhtml(final String p_packageName, final String p_templateName,
			final String p_modelName, final String p_fileName, final String p_fileGeneratedName) throws IOException {

		doTestFileGen(p_packageName, p_templateName, p_modelName, p_fileName, p_fileGeneratedName,
				FileType4Test_Enum.XHTML);
	}

	/**
	 * Lancer la comparaison entre le fichier attendu en sortie et le resultat de la
	 * generation.
	 *
	 * @param p_projectName       nom du projet (package) a partir du repertoire bin
	 *                            (exemple :
	 *                            "/fr/pacman/entity/api/files/genEntityItf")
	 * @param p_TemplateName      nom du template a appeler (exemple :
	 *                            "genEntityItf")
	 * @param p_ModelName         nom du model (exemple : sample.entity")
	 * @param p_fileName          nom du fichier physique a comparer (exemple :
	 *                            PersonneDto")
	 * @param p_fileGeneratedName nom du fichier si le nom du fichier produit par le
	 *                            generateur est volontairement different de celui
	 *                            attendu.
	 * @param p_typeGenerate      le type de generation demande (fichier Java,
	 *                            etc...)
	 *
	 * @throws IOException RAS
	 */
	private static void doTestFileGen(final String p_packageName, final String p_templateName, final String p_modelName,
			final String p_fileName, final String p_fileGeneratedName, final FileType4Test_Enum p_typeGenerate)
			throws IOException {

		// Initialisation du helper pour les fichiers.
		FileHelper4Test v_fileHelper = new FileHelper4Test(p_typeGenerate);

		// Chargement des proprietes de PacMan.
		PacmanPropertiesManager.initProperties(new File(v_fileHelper.getModelPath()).toURI().getPath());

		// Fichier .mtl à tester ainsi que le modele en entree.
		final PacmanGenerator4Test v_generator = new PacmanGenerator4Test(
				v_fileHelper.getModuleFullName(p_packageName), v_fileHelper.getModelFullName(p_modelName));

		// Contenu du fichier de reference.
		String v_expectedText = v_fileHelper.getFileBody(v_fileHelper.getExpectedFileFullName(p_fileName));

		// Execution et recuperation du contenu pour le fichier genere.
		String v_fileText = v_generator.executeTemplate(p_templateName, v_fileHelper
				.getGeneratedFileFullName((null == p_fileGeneratedName) ? p_fileName : p_fileGeneratedName));

		// Traitement des dates de generation.
		v_expectedText = DateHelper4Test.update(v_expectedText, v_fileText);

		// Comparaison des fichiers.
		doCompare(v_fileHelper, v_expectedText, v_fileText);
	}

	/**
	 * Effectue la comparaison finale entre les deux fichiers.
	 *
	 * @param p_fileHelper    l'instance du Helper pour la gestion des fichiers.
	 * @param p_expectedText  le contenu du fichier attendu.
	 * @param p_generatedText le contenu du fichier genere.
	 */
	private static void doCompare(FileHelper4Test p_fileHelper, String p_expectedText, String p_generatedText) {

		// Recuperation des contenus
		// dans des variables de travail.
		String v_expectedTextWk = p_expectedText;
		String v_generatedTestWk = p_generatedText;

		try {

			// Nettoyage du fichier attendu avant comparaison.
			v_expectedTextWk = p_fileHelper.cleanFile(v_expectedTextWk, _mode);

			// Nettoyage du fichier genere avant comparaison.
			v_generatedTestWk = p_fileHelper.cleanFile(v_generatedTestWk, _mode);

			// Verification si genere est egal a l'attendu.
			assertEquals(v_expectedTextWk, v_generatedTestWk);

			// TODO Devrait être Comparison failure mais problème Tycho.
		} catch (Throwable v_e) {

			if (Mode4Test_Enum.LOW == _mode || Mode4Test_Enum.MEDIUM == _mode) {

				// On relance la comparaison en mode HIGHT pour que les fichiers soient un
				// minimum lisibles par le developpeur.
				_mode = Mode4Test_Enum.HIGHT;
				doCompare(p_fileHelper, p_expectedText, p_generatedText);
			}

			// On relance l'exception pour la passer a JUnit.
			throw v_e;
		}
	}
}
