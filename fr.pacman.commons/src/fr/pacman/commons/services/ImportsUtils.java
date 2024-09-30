package fr.pacman.commons.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.obeonetwork.dsl.entity.Entity;
import org.obeonetwork.dsl.environment.Attribute;
import org.obeonetwork.dsl.environment.DTO;
import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.environment.ObeoDSMObject;
import org.obeonetwork.dsl.environment.Reference;
import org.obeonetwork.dsl.environment.StructuredType;
import org.obeonetwork.dsl.environment.Type;
import org.obeonetwork.dsl.soa.Operation;
import org.obeonetwork.dsl.soa.Parameter;
import org.obeonetwork.dsl.soa.Service;

import fr.pacman.commons.convention.project.ProjectProperties;
import fr.pacman.commons.convention.project.Spi4jProperties;
import fr.pacman.commons.properties.PacmanPropertiesManager;

/**
 * Utility class to write imports. Always remember that the java generator that
 * uses this service must absolutely have the Acceleo cache disabled, otherwise
 * you will have unpredictable results. To disable the cache, set the value to
 * "false" in the "getSwitchQueryCache ()" method.
 * 
 * @author MINARM. Gerer les binaires.
 */
public class ImportsUtils {

	/**
	 * List for standard knowable imports.
	 */
	private static final String c_list = "java.util.List";

	private static final String c_date = "java.util.Date";

	private static final String c_timestamp = "java.sql.Timestamp";

	private static final String c_time = "java.sql.Time";

	@SuppressWarnings("unused")
	private static final String c_binary = "java.sql.Time";

	private static final String c_blob = "java.sql.Blob";

	private static final String c_inputStream = "java.io.InputStream";

	private static final String c_arrays = "java.util.Arrays";

	private static final String c_array = "java.util.ArrayList";

	private static final String c_collection = "java.util.Collections";

	/**
	 * Check if in debug mode.
	 */
	@SuppressWarnings("unused")
	private static boolean _debug;

	/**
	 * List of automatic imports to not take in account.
	 */
	private static List<String> _importsNotInAccount;

	/**
	 * List of all imports to flush.
	 */
	private static List<String> _imports;

	/**
	 * List of types for additional field types.
	 */
	private static List<String> _additionalFieldTypes;

	/**
	 * Private constructor.
	 */
	private ImportsUtils() {
		// RAS.
	}

	/**
	 * Initialize the static service, reset the lists and browse the ObeoDSMObject
	 * to add automatically detectable imports.
	 * 
	 * @param p_object      : The object for adding imports.
	 * @param p_onlyPublic  : Set to true if the service must only set the imports
	 *                      for "public" operations.
	 * @param p_notInAcount : Set a list of imports separated by ',' if some of
	 *                      automatically detectable imports must be ignored.
	 * @return
	 */
	public static String initImports(final ObeoDSMObject p_object, final Boolean p_onlyPublic,
			final String p_notInAccount) {

		// Reset the required imports list.
		resetImports();

		// Reset the 'not to take in account' list.
		resetNotInAccount(p_notInAccount);

		// Reset the additional fields type list.
		resetAdditionalFieldTypes();

		// Security. Do not move from here...
		if (null == p_object)
			return null;

		// Do the initialization with auto detectable imports.
		if (p_object instanceof Service) {
			initWithBasicImports((Service) p_object, p_onlyPublic);
		}

		else if (p_object instanceof Operation) {
			initWithBasicImports((Operation) p_object, p_onlyPublic);
		}

		else if (p_object instanceof DTO) {
			initWithBasicImports((DTO) p_object);
		}

		else if (p_object instanceof Entity) {
			initWithBasicImports((Entity) p_object);
		}

		else if (p_object instanceof Reference) {
			initWithBasicImports(((Reference) p_object).getContainingType());
		}

		// For now, in all cases, (must be optimized).
		// Excess imports will be eliminated by the CTRL + SHIFT + O.
		initWithAdditionalBasicFieldTypes();

		// Always send null.
		return null;
	}

	public static String initImportsJavaService(final ObeoDSMObject p_object, final Boolean p_onlyPublic,
			final String p_notInAccount) {
		return initImports(p_object, p_onlyPublic, p_notInAccount);
	}

	/**
	 * Check if the import is already present. If not, add to the list.
	 * 
	 * @param p_object : The object for traceability.
	 * @param p_import : The full package and name for the import.
	 * @param p_write  : Condition given by a method given by the developer.
	 * 
	 * @return Must always return null value as the query must not write anything in
	 *         the file.
	 */
	public static String addImport(final ObeoDSMObject p_object, final String p_import, final Boolean p_write) {

		if (null != p_import && p_write)
			return addImport(p_import);

		// Always send null.
		return null;
	}

	public static String addImportJavaService(final ObeoDSMObject p_object, final String p_import,
			final Boolean p_write) {
		return addImport(p_object, p_import, p_write);
	}

	/**
	 * Check if the import has already been writed to the document. If not, add to
	 * the list.
	 * 
	 * @param p_import : The import string.
	 * @return Must always return null value as the query must not write anything in
	 *         the file.
	 */
	private static String addImport(final String p_import) {

		if (!p_import.isEmpty() && !_imports.contains(p_import))
			_imports.add(p_import);

		// Always send null.
		return null;
	}

	/**
	 * Add all automatically basic detectable imports for a service.
	 * 
	 * @param p_service    : The service to browse.
	 * @param p_onlyPublic : If set to 'true', only for public operations.
	 */
	private static void initWithBasicImports(final Service p_service, final Boolean p_onlyPublic) {
		for (Operation v_operation : ((Service) p_service).getOwnedInterface().getOwnedOperations()) {
			initWithBasicImports(v_operation, p_onlyPublic);
		}
	}

	/**
	 * Add all automatically basic detectable imports for an operation.
	 * 
	 * @param p_operation  : The operation to browse.
	 * @param p_onlyPublic : If set to 'true', only for public operations.
	 */
	private static void initWithBasicImports(final Operation p_operation, final Boolean p_onlyPublic) {
		if (p_onlyPublic && !p_operation.isPublic())
			return;

		// Init with all input(s) and output(s) parameters.
		initWithBasicImports(p_operation.getOutput());
		initWithBasicImports(p_operation.getInput());
	}

	/**
	 * Add all automatically basic detectable imports for a DTO.
	 * 
	 * @param p_dto : The DTO object to be analyzed.
	 */
	private static void initWithBasicImports(final DTO p_dto) {
		initWithBasicImports(DtoFromEntityUtils.getAttributes(p_dto), p_dto.getReferences());
	}

	/**
	 * Add all automatically basic detectable imports for an Entity.
	 * 
	 * @param p_entity : The Entity object to be analyzed.
	 */
	private static void initWithBasicImports(final Entity p_entity) {
		// EntityFromDtoUtils ...
		initWithBasicImports(p_entity.getAttributes(), p_entity.getReferences());
	}

	/**
	 * Add all automatically basic detectable imports for a Reference.
	 * 
	 * @param p_reference : The Reference object to be analyzed.
	 */
	private static void initWithBasicImports(final StructuredType p_structuredType) {

		if (p_structuredType instanceof DTO) {
			initWithBasicImports((DTO) p_structuredType);
		}

		else if (p_structuredType instanceof Entity) {
			initWithBasicImports((Entity) p_structuredType);
		}
		// initWithBasicImports(p_structuredType.getAttributes(),
		// p_structuredType.getReferences());
	}

	/**
	 * Add all automatically basic detectable imports for an operation.
	 * 
	 * @param p_params : The list of parameters for an operation.
	 */
	private static void initWithBasicImports(final EList<Parameter> p_params) {
		for (Parameter v_param : p_params) {
			initWithBasicType(v_param.getType());
			initWithBasicMultiplicity(v_param.getMultiplicity());
		}
	}

	/**
	 * Add all automatically basic detectable imports for a structured object.
	 * 
	 * @param p_attributes : The list of all attributes for the structured object.
	 * @param p_references : The list of all references for the structured object.
	 */
	private static void initWithBasicImports(final List<Attribute> p_attributes, EList<Reference> p_references) {
		for (Attribute v_attribute : p_attributes) {
			initWithBasicType(v_attribute.getType());
			initWithBasicMultiplicity(v_attribute.getMultiplicity());
		}

		for (Reference v_reference : p_references) {
			initWithBasicMultiplicity(v_reference.getMultiplicity());
		}
	}

	/**
	 * Add imports for additional field types if needed.
	 */
	private static void initWithAdditionalBasicFieldTypes() {
		for (String v_type : _additionalFieldTypes) {
			initWithBasicType(v_type);
		}
	}

	/**
	 * Check if we have to add a 'List' import.
	 * 
	 * @param p_multiplicity : If 'true' add the 'List' import.
	 */
	private static void initWithBasicMultiplicity(final MultiplicityKind p_multiplicity) {

		if (MultiplicityKind.ONE_STAR_LITERAL == p_multiplicity
				|| MultiplicityKind.ZERO_STAR_LITERAL == p_multiplicity) {
			addBasicImport(c_list);
			addBasicImport(c_array);
			addBasicImport(c_collection);
		}
	}

	/**
	 * Add automatically basic detected import.
	 * 
	 * @param p_type : The type for the parameter.
	 */
	private static void initWithBasicType(final Type p_type) {
		if (null == p_type)
			return;

		initWithBasicType(p_type.getName());
	}

	/**
	 * Add automatically basic detected import.
	 * 
	 * @param p_type : The string format type for the parameter.
	 */
	private static void initWithBasicType(final String p_type) {

		if ("Date".equalsIgnoreCase(p_type))
			addBasicImport(c_date);

		if ("Timestamp".equalsIgnoreCase(p_type))
			addBasicImport(c_timestamp);

		if ("Time".equalsIgnoreCase(p_type))
			addBasicImport(c_time);

		if ("Binary".equalsIgnoreCase(p_type)) {
			addBasicImport(Spi4jProperties.getImportForBinary());
			addBasicImport(c_inputStream);
			addBasicImport(c_blob);
			addBasicImport(c_arrays);
			// ocl getImportForBinary()
		}
	}

	/**
	 * Add an automatically basic detectable import.
	 * 
	 * @param p_import : The basic detectable import to add.
	 */
	private static void addBasicImport(final String p_import) {
		if (null == _importsNotInAccount)
			addImport(p_import);

		else if (!_importsNotInAccount.contains(p_import))
			addImport(p_import);
	}

	/**
	 * Write (flush) all the imports in one shot to the file.
	 * 
	 * @return The list of all imports for the class.
	 */
	public static String flushImports(final ObeoDSMObject p_object) {
		String v_newLine = System.lineSeparator();
		StringBuilder v_builder = new StringBuilder();

		// Basic sort.
		Collections.sort(_imports);

		for (String v_import : _imports) {
			v_builder.append("import ").append(v_import);
			v_builder.append(";").append(v_newLine);
		}

		return v_builder.toString();
	}

	public static String flushImportsJavaService(final ObeoDSMObject p_object) {
		return flushImports(p_object);
	}

	/**
	 * Reset a specific list of imports to not take in account (if needed). The
	 * imports must be separated by ','.
	 * 
	 * @param p_notInAccount : The list of imports to ignore if automatically
	 *                       detected.
	 */
	private static void resetNotInAccount(final String p_notInAccount) {
		_importsNotInAccount = null;

		if (null != p_notInAccount && !p_notInAccount.isEmpty())
			_importsNotInAccount = new ArrayList<String>(Arrays.asList(p_notInAccount.split(",")));
	}

	/**
	 * Reset a specific list for additional SQL types (if needed). This reset is
	 * ordered by the generator.
	 */
	private static void resetAdditionalFieldTypes() {
		if (null == _additionalFieldTypes) {
			_additionalFieldTypes = new ArrayList<String>();
			String v_additionalFields = ProjectProperties.getSqlTableAdditionalFields();
			if (null != v_additionalFields && !v_additionalFields.isEmpty()) {
				String[] v_tabAdditionalFields = v_additionalFields.split(",");
				for (String v_additionalField : v_tabAdditionalFields) {
					// Improve if possible.
					_additionalFieldTypes.add(PacmanPropertiesManager.get_property(v_additionalField.trim() + ".type"));
				}
			}
		}
	}

	/**
	 * Reset the specific list for all required imports.
	 */
	private static void resetImports() {
		_imports = new ArrayList<>();
	}

	/**
	 * Reset the specific list for additional field types.
	 */
	public static void resetAdditionalTypes() {
		_additionalFieldTypes = null;
	}
}
