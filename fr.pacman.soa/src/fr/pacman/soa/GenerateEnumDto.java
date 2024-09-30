package fr.pacman.soa;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.aql.AcceleoUtil;
import org.eclipse.acceleo.query.ast.TypeLiteral;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.obeonetwork.dsl.entity.EntityPackage;
import org.obeonetwork.dsl.entity.util.EntityResourceFactoryImpl;
import org.obeonetwork.dsl.environment.EnvironmentPackage;
import org.obeonetwork.dsl.overview.OverviewPackage;
import org.obeonetwork.dsl.overview.util.OverviewResourceFactoryImpl;
import org.obeonetwork.dsl.requirement.RequirementPackage;
import org.obeonetwork.dsl.requirement.util.RequirementResourceFactoryImpl;
import org.obeonetwork.dsl.soa.SoaPackage;
import org.obeonetwork.dsl.soa.util.SoaResourceFactoryImpl;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * 
 * @author MINARM
 */
public class GenerateEnumDto extends PacmanGenerator_Abs {

	/**
	 * Get the list of templates to execute in the selected generator, depending the
	 * type of the selected object.
	 * 
	 * @return the list of templates to execute.
	 */
	@Override
	protected List<String> getTemplates() {
		
		// TODO à modifier / compléter en fonction de la sélection.
		List<String> v_templates = new ArrayList<>();
		v_templates.add("generateEnumDto");
		return v_templates;
	}

	/**
	 * Get the module full qualified name.
	 * 
	 * @return the module qualified name under string format.
	 */
	@Override
	public String getModuleQualifiedName() {
		
		return "fr::pacman::soa::generateEnumDto";
	}

	/**
	 * Get the project specifically impacted by the code generation.
	 * 
	 * @return the project name.
	 */
	@Override
	public String getProjectName() {
		
		return ProjectProperties.getCommonProjectName();
	}

	/**
	 * Get the code generation options.
	 * 
	 * @return a list of options for code generation.
	 */
	@Override
	protected Map<String, String> getOptions() {
		
		Map<String, String> res = new LinkedHashMap<>();
		res.put(AcceleoUtil.LOG_URI_OPTION, "acceleo.log");
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
}
