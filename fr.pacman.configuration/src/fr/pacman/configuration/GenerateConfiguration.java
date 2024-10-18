package fr.pacman.configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * Here no subproject to declare as the generation must process on the rootPath.
 * 
 * @author MINARM
 */
public class GenerateConfiguration extends PacmanGenerator_Abs {

	/**
	 * Used by plugins pacman.configuration and pacman.start.
	 */
	public static final String c_PROP_PROJECT_VERSION = "version";

	@Override
	public String getSubProjectName() {
		return null;
	}

	@Override
	public String getModuleQualifiedName() {
		return "fr::pacman::configuration::generateConfiguration";
	}

	@Override
	protected Map<String, String> getOptions() {
		Map<String, String> v_res = new LinkedHashMap<>();
		v_res.put(AcceleoUtil.LOG_URI_OPTION, "acceleo.log");
		v_res.put(AcceleoUtil.NEW_LINE_OPTION, System.lineSeparator());
		return v_res;
	}

	@Override
	protected List<EObject> getValues(IQualifiedNameQueryEnvironment queryEnvironment,
			Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels) {
		final List<EObject> values = AcceleoUtil.getValues(type, queryEnvironment, resourceSetForModels.getResources(),
				valuesCache);
		return values;
	}

	/**
	 * Get the list of templates to execute in the selected generator, depending the
	 * type of the selected object.
	 * 
	 * @return the list of templates to execute.
	 */
	@Override
	protected Map<String, SelectionType_Enum> getTemplates() {
		Map<String, SelectionType_Enum> v_templates = new HashMap<>();
		v_templates.put("generateRootConfiguration", SelectionType_Enum.FILE);
		return v_templates;
	}
}
