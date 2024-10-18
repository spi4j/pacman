package fr.pacman.entity.api;

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
public class GenerateCommonEnumEntity extends PacmanGenerator_Abs {

	@Override
	public String getSubProjectName() {
		return ProjectProperties.getCommonProjectName();
	}

	@Override
	public String getModuleQualifiedName() {
		return "fr::pacman::entity::api::generateCommonEnumEntity";
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

	@Override
	protected Map<String, SelectionType_Enum> getTemplates() {
		Map<String, SelectionType_Enum> v_templates = new HashMap<>();
		v_templates.put("generateEnumEntity", SelectionType_Enum.FILE);
		v_templates.put("generateEnumEntityNamespace", SelectionType_Enum.NAMESPACE);
		v_templates.put("generateEnumEntityEntity", SelectionType_Enum.EOBJECT);
		return v_templates;
	}
}
