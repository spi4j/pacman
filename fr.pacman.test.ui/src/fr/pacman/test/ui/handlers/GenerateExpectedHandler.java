package fr.pacman.test.ui.handlers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.pacman.cinematic.api.GenerateCinematicApi;
import fr.pacman.cinematic.gwt.GenerateCinematicGwt;
import fr.pacman.cinematic.jsf.GenerateCinematicJsf;
import fr.pacman.cinematic.jsp.GenerateCinematicJsp;
import fr.pacman.cinematic.swing.GenerateCinematicSwing;
import fr.pacman.commons.main.PacmanGenerator_Abs;
import fr.pacman.commons.properties.PacmanPropertiesManager;
import fr.pacman.configuration.GenerateConfiguration;
import fr.pacman.entity.api.GenerateEnumEntity;
import fr.pacman.entity.api.GeneratePersistenceApi;
import fr.pacman.entity.api.dbpopulate.GenerateDbPopulate;
import fr.pacman.entity.api.dbreferentiel.GenerateDbReferentiel;
import fr.pacman.entity.api.sql.GenerateSql;
import fr.pacman.entity.api.xmi.GenerateXmi;
import fr.pacman.entity.jdbc.GenerateEntityJdbc;
import fr.pacman.environment.GenerateTypes;
import fr.pacman.requirement.GenerateRequirement;
import fr.pacman.soa.GenerateClientGwtSoa;
import fr.pacman.soa.GenerateCommonSoa;
import fr.pacman.soa.GenerateEnumDto;
import fr.pacman.soa.GenerateServerSoa;
import fr.pacman.soapifirst.GenerateCommonSoaRs;
import fr.pacman.soapifirst.GenerateServerSoaRs;
import fr.pacman.start.GenerateStart;
import fr.pacman.validation.GenerateValidation;

public class GenerateExpectedHandler extends AbstractHandler {

	private static Map<String, String> RENAMES = initializeRenames();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		final IFolder root = (IFolder) selection.getFirstElement();

		try {
			for (IResource member : root.members()) {
				if (member instanceof IFolder && !member.getName().endsWith("-fail")) {
					final IFolder testFolder = (IFolder) member;
					final IResource modelResource = testFolder.findMember("model");
					if (modelResource instanceof IFolder) {
						final IFolder modelFolder = (IFolder) modelResource;
						System.out.println("- " + modelFolder.getLocation().toFile().getAbsolutePath());
						PacmanPropertiesManager.initProperties(modelFolder.getLocation().toFile().getAbsolutePath());
						try {
							// PacmanGenerator_Abs
							generate(GenerateCinematicApi.class, modelFolder, "cinematic");
							generate(GenerateCinematicGwt.class, modelFolder, "cinematic");
							generate(GenerateCinematicJsf.class, modelFolder, "cinematic");
							generate(GenerateCinematicJsp.class, modelFolder, "cinematic");
							generate(GenerateCinematicSwing.class, modelFolder, "cinematic");
							generate(GenerateClientGwtSoa.class, modelFolder, "soa");
//							generate(fr.pacman.entity.jdbc.GenerateClientGwtSoaLight.class, modelFolder, "soa");
//							generate(fr.pacman.soalight.GenerateClientGwtSoaLight.class, modelFolder, "soa");
							generate(GenerateCommonSoa.class, modelFolder, "soa");
//							generate(GenerateCommonSoaLight.class, modelFolder, "soa");
							generate(GenerateCommonSoaRs.class, modelFolder, "soa");
							generate(GenerateConfiguration.class, modelFolder, "soa");
							generate(GenerateDbPopulate.class, modelFolder, "entity");
							generate(GenerateDbReferentiel.class, modelFolder, "entity");
//							generate(GenerateEntityItfApiSoaLight.class, modelFolder, "entity");
//							generate(GenerateEntityItfApiSoaLight.class, modelFolder, "environment");
							generate(GenerateEntityJdbc.class, modelFolder, "entity");
							generate(GenerateEntityJdbc.class, modelFolder, "environment");
//							generate(GenerateEntityJdbcSoaLight.class, modelFolder, "entity");
//							generate(GenerateEntityJdbcSoaLight.class, modelFolder, "environment");
							generate(GenerateEnumDto.class, modelFolder, "soa");
							generate(GenerateEnumEntity.class, modelFolder, "entity");
							generate(GeneratePersistenceApi.class, modelFolder, "entity");
							generate(GeneratePersistenceApi.class, modelFolder, "environment");
							generate(GenerateRequirement.class, modelFolder, "requirement");
							generate(GenerateServerSoa.class, modelFolder, "soa");
//							generate(GenerateServerSoaLight.class, modelFolder, "soa");
							generate(GenerateServerSoaRs.class, modelFolder, "soa");
							generate(GenerateSql.class, modelFolder, "entity");
							generate(GenerateStart.class, modelFolder, "overview");
							generate(GenerateTypes.class, modelFolder, "environment");
							generate(GenerateValidation.class, modelFolder, "graal");
							generate(GenerateValidation.class, modelFolder, "requirement");
							generate(GenerateValidation.class, modelFolder, "soa");
							generate(GenerateValidation.class, modelFolder, "entity");
							generate(GenerateXmi.class, modelFolder, "entity");
							// TODO JavaEstimationGenerator
							// TODO JavaValidationGenerator
						} finally {
							PacmanPropertiesManager.clearProperties();
						}
					}
				}
			}
			root.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static Map<String, String> initializeRenames() {
		final Map<String, String> res = new HashMap<>();

		res.put(GenerateCinematicApi.class.getSimpleName(), "GenerateClientCinematicApi");
		res.put(GenerateCinematicGwt.class.getSimpleName(), "GenerateClientCinematicGwt");
		res.put(GenerateCinematicJsf.class.getSimpleName(), "GenerateClientCinematicJsf");
		res.put(GenerateCinematicJsp.class.getSimpleName(), "GenerateClientCinematicJsp");
		res.put(GenerateCinematicSwing.class.getSimpleName(), "GenerateClientCinematicSwing");

		res.put(GenerateDbPopulate.class.getSimpleName(), "GenerateServerDbPopulate");
		res.put(GenerateDbReferentiel.class.getSimpleName(), "GenerateServerDbReferentiel");
		res.put(GenerateEntityJdbc.class.getSimpleName(), "GenerateServerEntityJdbc");
		res.put(GeneratePersistenceApi.class.getSimpleName(), "GenerateServerPersistenceApi");
		res.put(GeneratePersistenceApi.class.getSimpleName(), "GenerateServerPersistenceApi");
		res.put(GenerateSql.class.getSimpleName(), "GenerateServerSql");
		res.put(GenerateXmi.class.getSimpleName(), "GenerateServerXmi");

		res.put(GenerateRequirement.class.getSimpleName(), "GenerateCommonRequirement");
		res.put(GenerateTypes.class.getSimpleName(), "GenerateCommonTypes");
		res.put(GenerateEnumEntity.class.getSimpleName(), "GenerateCommonEnumEntity");

		return res;
	}

	private void generate(Class<? extends PacmanGenerator_Abs> cls, IFolder modelFolder, String modelExtension) {
		final File modelFolderfile = modelFolder.getLocation().toFile();
		for (IFile model : findFileByExtension(modelFolder, modelExtension)) {
			final File modelFile = model.getLocation().toFile();
			final URI modelURI = URI.createFileURI(modelFile.getAbsolutePath());
			final String modelSegment = modelFile.getAbsolutePath()
					.substring(modelFolderfile.getAbsolutePath().length() + 1).replace(File.separator, "_");
			final File target = modelFolder.getParent().getFolder(new Path(
					RENAMES.getOrDefault(cls.getSimpleName(), cls.getSimpleName()) + "-" + modelSegment + "/expected"))
					.getLocation().toFile();
			System.out.println("  - " + modelFile.getAbsolutePath() + " => " + target.getAbsolutePath());
			try {
				final Constructor<? extends PacmanGenerator_Abs> contructor = cls.getConstructor(URI.class, File.class,
						List.class);
				final PacmanGenerator_Abs generator = contructor.newInstance(modelURI, target, new ArrayList<>());
				generator.doGenerate(new BasicMonitor());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private List<IFile> findFileByExtension(IFolder root, String extension) {
		final List<IFile> res = new ArrayList<>();

		try {
			for (IResource member : root.members()) {
				if (member instanceof IFile) {
					if (((IFile) member).getFileExtension().equals(extension)) {
						res.add((IFile) member);
					}
				} else if (member instanceof IFolder) {
					res.addAll(findFileByExtension((IFolder) member, extension));
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
}
