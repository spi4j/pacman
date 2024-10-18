package fr.pacman.soa;

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

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * 
 * @author MINARM
 */
public class GenerateServerSoa extends PacmanGenerator_Abs {

	/**
	 * Get the module full qualified name.
	 * 
	 * @return the module qualified name under string format.
	 */
	@Override
	public String getModuleQualifiedName() {

		return "fr::pacman::soa::generateServerSoa";
	}

	/**
	 * Get the project specifically impacted by the code generation.
	 * 
	 * @return the project name.
	 */
	@Override
	public String getSubProjectName() {

		return ProjectProperties.getServerProjectName();
	}

	/**
	 * Get the code generation options.
	 * 
	 * @return a list of options for code generation.
	 */
	@Override
	protected Map<String, String> getOptions() {

		Map<String, String> res = new LinkedHashMap<>();
		// res.put(AcceleoUtil.LOG_URI_OPTION, "acceleo.log");
		res.put(AcceleoUtil.NEW_LINE_OPTION, System.lineSeparator());
		return res;
	}

	@Override
	protected List<EObject> getValues(IQualifiedNameQueryEnvironment queryEnvironment,
			final Map<EClass, List<EObject>> valuesCache, TypeLiteral type, ResourceSet resourceSetForModels) {
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
		v_templates.put("GenerateServerSystem", SelectionType_Enum.FILE);
		v_templates.put("GenerateServerComponent", SelectionType_Enum.COMPONENT);
		v_templates.put("GenerateServerService", SelectionType_Enum.SERVICE);
		v_templates.put("GenerateServerDto", SelectionType_Enum.EOBJECT);
		return v_templates;
	}
}
