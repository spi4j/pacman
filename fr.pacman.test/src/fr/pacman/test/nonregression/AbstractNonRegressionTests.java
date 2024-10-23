package fr.pacman.test.nonregression;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.commons.ui.PacmanUIGeneratorsReport;

@RunWith(Parameterized.class)
public abstract class AbstractNonRegressionTests {

	/**
	 * The mapping from the generator {@link Class} to the {@link List} of file
	 * extensions.
	 */
	private static final Map<Class<? extends PacmanGenerator_Abs>, List<String>> EXTENSIONS_MAP = initializeExtensionsMap();

	/**
	 * The generator {@link Class}.
	 */
	private final Class<? extends PacmanGenerator_Abs> cls;

	/**
	 * The model folder {@link File}
	 */
	private final File modelFolder;

	/**
	 * The model {@link File}.
	 */
	private final File model;

	/**
	 * Constructor.
	 * 
	 * @param cls         the generator {@link Class}
	 * @param model       the model {@link URI}
	 * @param modelFolder the model folder {@link URI}
	 */
	public AbstractNonRegressionTests(Class<? extends PacmanGenerator_Abs> cls, File model, File modelFolder) {
		this.cls = cls;
		this.model = model;
		this.modelFolder = modelFolder;
	}

	@Test
	public void generate() {
		final File expectedTargetFolder = getExpectedTargetFolder();
		final File actualTargetFolder = getActualTargetFolder();
		if (actualTargetFolder.exists()) {
			try (Stream<Path> pathStream = Files.walk(actualTargetFolder.toPath())) {
				pathStream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		PacmanUIGeneratorsReport.reset();
		PacmanPropertiesManager.initProperties(modelFolder.getAbsolutePath());
		
		try {
			final Constructor<? extends PacmanGenerator_Abs> contructor = cls.getConstructor();
			final PacmanGenerator_Abs generator = contructor.newInstance();

			final List<String> resources = new ArrayList<>();
			resources.add(model.getAbsolutePath());
			generator.setResources(resources);
			generator.setRootPath(actualTargetFolder.getAbsolutePath());
			generator.generate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			PacmanPropertiesManager.clearProperties();
			PacmanUIGeneratorsReport.log(false);
		}

		final StringBuilder message = new StringBuilder();
		final int nbDifferences = assertFolder(message, expectedTargetFolder, actualTargetFolder);
		if (nbDifferences != 0) {
			message.append(nbDifferences + " differences");
			fail(message.toString());
		}
	}

	private File getExpectedTargetFolder() {
		final String modelSegment = model.getAbsolutePath().substring(modelFolder.getAbsolutePath().length() + 1)
				.replace(File.separator, "_");
		final File res = modelFolder.getParentFile().toPath()
				.resolve(cls.getSimpleName() + "-" + modelSegment + File.separator + "expected").toFile();

		return res;
	}

	private File getActualTargetFolder() {
		final String modelSegment = model.getAbsolutePath().substring(modelFolder.getAbsolutePath().length() + 1)
				.replace(File.separator, "_");
		final File res = modelFolder.getParentFile().toPath()
				.resolve(cls.getSimpleName() + "-" + modelSegment + File.separator + "actual").toFile();

		return res;
	}

	private static Map<Class<? extends PacmanGenerator_Abs>, List<String>> initializeExtensionsMap() {
		final Map<Class<? extends PacmanGenerator_Abs>, List<String>> res = new LinkedHashMap<>();

//		res.computeIfAbsent(GenerateClientCinematicApi.class, cls -> new ArrayList<>()).add("cinematic");
//		res.computeIfAbsent(GenerateClientCinematicGwt.class, cls -> new ArrayList<>()).add("cinematic");
//		res.computeIfAbsent(GenerateClientCinematicJsf.class, cls -> new ArrayList<>()).add("cinematic");
//		res.computeIfAbsent(GenerateClientCinematicJsp.class, cls -> new ArrayList<>()).add("cinematic");
//		res.computeIfAbsent(GenerateClientCinematicSwing.class, cls -> new ArrayList<>()).add("cinematic");
		// TODO res.computeIfAbsent(GenerateClientGwtSoa.class, cls -> new
		// ArrayList<>()).add("soa");
		// TODO
		// res.computeIfAbsent(fr.pacman.entity.jdbc.GenerateClientGwtSoaLight.class,
		// cls -> new ArrayList<>()).add("soa");
		// TODO res.computeIfAbsent(fr.pacman.soalight.GenerateClientGwtSoaLight.class,
		// cls -> new ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateCommonSoa.class, cls -> new ArrayList<>()).add("soa");
		// TODO res.computeIfAbsent(GenerateCommonSoaLight.class, cls -> new
		// ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateCommonSoaRs.class, cls -> new ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateConfiguration.class, cls -> new ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateServerDbPopulate.class, cls -> new ArrayList<>()).add("entity");
//		res.computeIfAbsent(GenerateServerDbReferentiel.class, cls -> new ArrayList<>()).add("entity");
		// TODO res.computeIfAbsent(GenerateEntityItfApiSoaLight.class, cls -> new
		// ArrayList<>()).add("entity");
		// TODO res.computeIfAbsent(GenerateEntityItfApiSoaLight.class, cls -> new
		// ArrayList<>()).add("environment");
//		res.computeIfAbsent(GenerateServerEntityJdbc.class, cls -> new ArrayList<>()).add("entity");
//		res.computeIfAbsent(GenerateServerEntityJdbc.class, cls -> new ArrayList<>()).add("environment");
		// TODO res.computeIfAbsent(GenerateEntityJdbcSoaLight.class, cls -> new
		// ArrayList<>()).add("entity");
		// TODO res.computeIfAbsent(GenerateEntityJdbcSoaLight.class, cls -> new
		// ArrayList<>()).add("environment");
//		res.computeIfAbsent(GenerateEnumDto.class, cls -> new ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateCommonEnumEntity.class, cls -> new ArrayList<>()).add("entity");
//		res.computeIfAbsent(GenerateServerPersistenceApi.class, cls -> new ArrayList<>()).add("entity");
//		res.computeIfAbsent(GenerateServerPersistenceApi.class, cls -> new ArrayList<>()).add("environment");
//		res.computeIfAbsent(GenerateCommonRequirement.class, cls -> new ArrayList<>()).add("requirement");
//		res.computeIfAbsent(GenerateServerSoa.class, cls -> new ArrayList<>()).add("soa");
		// TODO res.computeIfAbsent(GenerateServerSoaLight.class, cls -> new
		// ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateServerSoaRs.class, cls -> new ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateServerSql.class, cls -> new ArrayList<>()).add("entity");
//		res.computeIfAbsent(GenerateStart.class, cls -> new ArrayList<>()).add("overview");
//		res.computeIfAbsent(GenerateCommonTypes.class, cls -> new ArrayList<>()).add("environment");
//		res.computeIfAbsent(GenerateValidation.class, cls -> new ArrayList<>()).add("graal");
//		res.computeIfAbsent(GenerateValidation.class, cls -> new ArrayList<>()).add("requirement");
//		res.computeIfAbsent(GenerateValidation.class, cls -> new ArrayList<>()).add("soa");
//		res.computeIfAbsent(GenerateValidation.class, cls -> new ArrayList<>()).add("entity");

		return res;
	}

	protected static Collection<Object[]> retrieveTests(String root) {
		final Collection<Object[]> res = new ArrayList<>();

		final File rootFile = new File(root);
		for (File testFolder : rootFile.listFiles()) {
			if (testFolder.isDirectory() && !testFolder.getName().endsWith("-fail")) {
				final File modelFolder = testFolder.toPath().resolve("model").toFile();
				if (modelFolder.isDirectory()) {
					for (Entry<Class<? extends PacmanGenerator_Abs>, List<String>> entry : EXTENSIONS_MAP.entrySet()) {
						for (String extension : entry.getValue()) {
							for (File model : findFileByExtension(modelFolder, extension)) {
								res.add(new Object[] { entry.getKey(), model, modelFolder });
							}
						}
					}
				}
			}
		}

		return res;
	}

	private static List<File> findFileByExtension(File root, String extension) {
		final List<File> res = new ArrayList<>();

		for (File member : root.listFiles()) {
			if (member.isFile()) {
				final URI memberURI = URI.createFileURI(member.getAbsolutePath());
				if (memberURI.fileExtension().equals(extension)) {
					res.add(member);
				}
			} else if (member.isDirectory()) {
				res.addAll(findFileByExtension(member, extension));
			}
		}

		return res;
	}

	/**
	 * Asserts that both {@link Files} have the same contents.
	 * 
	 * @param message  the message {@link StringBuilder}
	 * @param expected the expected {@link Files}
	 * @param actual   the actual {@link File}
	 * @return the number of different files
	 */
	public static int assertFolder(StringBuilder message, File expected, File actual) {
		int res = 0;

		if (expected.isDirectory()) {
			for (File expectedMember : expected.listFiles()) {
				final File actualMember = actual.toPath().resolve(expectedMember.getName()).toFile();
				res += assertFolder(message, expectedMember, actualMember);
			}
		} else if (expected.isFile()) {
			try {
				final String expectedContents = Files.readString(expected.toPath(), StandardCharsets.UTF_8);
				if (actual.isFile() && actual.exists()) {
					final String actualContents = Files.readString(actual.toPath(), StandardCharsets.UTF_8);
					if (!expectedContents.equals(actualContents)) {
						message.append("differences: " + expected.getAbsolutePath() + "\n");
						res = 1;
					}
				} else {
					message.append("missing file: " + actual.getAbsolutePath() + "\n");
					res = 1;
				}
			} catch (IOException e) {
				fail(e.getMessage());
			}
		}

		return res;
	}

}
