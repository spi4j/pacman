package fr.pacman.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.eclipse.acceleo.engine.generation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.acceleo.engine.service.AcceleoService;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * Launcher pour les "tests macro" : model + mtl = fileExpected
 * 
 * @author MINARM
 */
class PacmanGenerator4Test extends PacmanGenerator_Abs {

	private final String _moduleFileName;

	private static AcceleoService acceleoService;

	/**
	 * Contructeur avec le module et le modele.
	 * 
	 * @param p_Module nom du module mtl en absolu à partir du repertoire bin
	 *                 (exemple : "/fr/pacman/entity/api/files/genEntityItf")
	 * @param p_Model  nom du model (exemple : "resources-test/model/sample.entity")
	 * @throws IOException RAS
	 */
	public PacmanGenerator4Test(final String p_module, final String p_model) throws IOException {

		if (p_model == null || p_model.isEmpty())
			throw new IllegalArgumentException("Le 'p_model' est obligatoire");

		if (p_module == null || p_module.isEmpty())
			throw new IllegalArgumentException("Le 'p_project' est obligatoire");

		_moduleFileName = p_module;

		final String v_ModelUrl = new File(p_model).toURI().toString();

		try {

			initialize(ModelLoader4Test.initializeModelContents(URI.createURI(v_ModelUrl)), new File("gen/"),
					new ArrayList<String>());

		} catch (final IOException v_ioe) {

			if (v_ioe.getMessage().equals('\'' + _moduleFileName + ".emtl' not found")) {
				fail("Module non compilé : " + _moduleFileName);
			} else {
				throw v_ioe;
			}

		} catch (final Exception v_e) {
			throw new IOException("Problème lors du initialize du modèle \"" + v_ModelUrl + "\" avec le module \""
					+ _moduleFileName + "\" : " + v_e.toString(), v_e);
		}
	}

	@Override
	protected URL findModuleURL(String p_module) {

		URL v_moduleUrl = null;
		try {

			v_moduleUrl = new File("bin/" + p_module).toURI().toURL();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return v_moduleUrl;
	}

	/**
	 * Lance l'execution d'un template
	 * 
	 * @param p_templateName      nom du template a lancer (exemple :
	 *                            "genEntityItf")
	 * @param p_generatedFileName nom du fichier a recuperer parmis la liste des
	 *                            fichiers generes.
	 * @return corps du fichier genere recherche
	 * @throws IOException RAS
	 */
	public String executeTemplate(final String p_templateName, final String p_generatedFileName) throws IOException {

		final String v_log = getModuleFileName() + '.' + p_templateName + " avec le modèle en entrée : "
				+ this.getModel().eResource();

		Logger.getLogger(p_templateName).info(v_log);
		final AcceleoService v_service = getAcceleoService();

		// Definit la Map des fichiers generes.
		final Map<String, String> v_map = new HashMap<String, String>();
		v_map.putAll(v_service.doGenerate(this.getModule(), p_templateName, this.getModel(), new ArrayList<String>(),
				new File(""), new BasicMonitor()));

		// Si la map est vide, on sort directement.
		assertFalse(v_map.isEmpty(), "Aucun fichier n'a été généré, le tableau des fichiers est vide !");

		// Tentative de recuperation du fichier qui a ete demande.
		String v_fileTest = getSpecifiedFile(v_map, p_generatedFileName);

		// verifie que le fichier a bien ete recupere, sinon on sort.
		assertNotNull(v_fileTest, "Le fichier " + p_generatedFileName
				+ " ne semble pas avoir été généré. Liste des fichiers générés : " + v_map.keySet());

		return v_fileTest;
	}

	/**
	 * Recuperation du contenu pour le fichier cible a comparer.
	 * 
	 * @param p_map              la map contenant l'ensemble des fichiers generes à
	 *                           partir du model.
	 * @param p_expectedFileName le nom du fichier demande (si plusieurs fichiers
	 *                           generes dans la map).
	 * @return le contenu du fichier cible a comparer.
	 */
	private String getSpecifiedFile(final Map<String, String> p_map, final String p_expectedFileName) {

		// Si plusieurs fichier generes recupere le contenu
		// en fonction du fichier demande.
		String v_actual = null;

		for (Entry<String, String> v_Entry : p_map.entrySet()) {
			if (v_Entry.getKey().endsWith(p_expectedFileName)) {
				v_actual = v_Entry.getValue();
				break;
			}
		}
		return v_actual;
	}

	/**
	 * Retourne l'acceleo service (il n'est construit que la première fois)
	 * 
	 * @return l'acceleo service
	 */
	private AcceleoService getAcceleoService() {
		if (acceleoService == null) {
			acceleoService = createAcceleoService();
		}
		return acceleoService;
	}

	/**
	 * @param p_Uri uri du model
	 * @throws IOException RAS
	 */
	public void setModelURI(final String p_Uri) throws IOException {
		initialize(URI.createFileURI(p_Uri), new File("gen/"), new ArrayList<String>());
	}

	@Override
	public IAcceleoGenerationStrategy getGenerationStrategy() {
		String v_lineDelimiter = ProjectProperties.getDelimiter();

		if ("WINDOWS".equals(v_lineDelimiter))
			v_lineDelimiter = "\r\n";

		if ("UNIX".equals(v_lineDelimiter))
			v_lineDelimiter = "\n";

		if (null == v_lineDelimiter)
			v_lineDelimiter = System.getProperty("line.separator");

		// Pour l'instant on force.
		return new PacmanStrategy4Test(v_lineDelimiter);
	}

	@Override
	protected String getModuleFileName() {
		return _moduleFileName;
	}

	@Override
	protected String[] getModuleTemplates() {
		return null;
	}

	@Override
	public String getProjectName() {
		return "fr.pacman.test";
	}

	@Override
	protected boolean getSwitchQueryCache() {
		return Boolean.FALSE;
	}
}
