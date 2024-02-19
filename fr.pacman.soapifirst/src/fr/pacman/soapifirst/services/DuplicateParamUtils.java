package fr.pacman.soapifirst.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.obeonetwork.dsl.environment.MultiplicityKind;
import org.obeonetwork.dsl.soa.Operation;
import org.obeonetwork.dsl.soa.Parameter;
import org.obeonetwork.dsl.soa.Service;

/**
 * Utility class to check duplicate input params.
 *
 * @author MINARM
 */
public class DuplicateParamUtils {

	
	/**
	 * Private constructor.
	 */
	private DuplicateParamUtils() {
		//RAS.
	}

	/**
	 * List of already writed parameters. (no use of Map).
	 */
	private static List<GlobalParam> _params;

	/**
	 * Check if the param has already been writed to the document.
	 * 
	 * @param p_param : the parameter to be writed.
	 * @return - 0 : the param is not found, write. - -1 : the param already exists,
	 *         do not write. - > 0 : the param already exist but with a different
	 *         type, suffix the name with calculated return value.
	 */
	public static int checkParamNotAlreadyWrited(Parameter p_param) {

		if (null == _params)
			_params = new ArrayList<GlobalParam>();

		// By default : OK to write.
		int v_returnCode = 0;

		// Browse all parameters in the list.
		for (GlobalParam v_globalParam : _params) {
			// Check if the name of the parameter is found in the list.
			if (v_globalParam.get_param().getName().equals(p_param.getName())) {
				// Check if the type is the same for the parameter.
				if (v_globalParam.get_param().getType().getName().equals(p_param.getType().getName())) {
					// Same name, same type, the parameter already exists -> by default KO to write.
					v_returnCode = -1;
					// But what about multiplicity ? (test only if there is multiplicity).
					if (!checkMultiplicity(p_param, v_globalParam.get_param()))
						// Calculate an index for parameter suffix. -> Ok to write but change the name.
						v_returnCode = calcParameterIndex(p_param);
				} else {
					// Not the same type, insert the parameter and
					// Calculate an index for parameter suffix. -> Ok to write but change the name.
					v_returnCode = calcParameterIndex(p_param);
				}
				break;
			}
		}

		// Check the result if !=0 add the parameter in the list.
		if (v_returnCode >= 0)
			_params.add(new GlobalParam(v_returnCode, p_param));

		return v_returnCode;
	}

	/**
	 * 
	 * @param p_param     : The parameter to check.
	 * @param p_listParam : The global parameter issued from the list.
	 * @return true if same multiplicity.
	 */
	private static boolean checkMultiplicity(final Parameter p_param, final Parameter p_listParam) {

		if (p_param.getMultiplicity().getValue() == MultiplicityKind.ZERO_STAR
				|| p_param.getMultiplicity().getValue() == MultiplicityKind.ONE_STAR) {
			if (p_param.getMultiplicity().getValue() != p_listParam.getMultiplicity().getValue()) {
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}
		return Boolean.TRUE;
	}

	/**
	 * Calculation for the index to be suffixed to the param name.
	 * 
	 * @param p_param : The parameter.
	 */
	private static int calcParameterIndex(Parameter p_param) {

		int v_idx = 0;
		// Browse all parameters in the list.
		for (GlobalParam v_parameter : _params) {
			if (v_parameter.get_param().getName().equals(p_param.getName())) {
				v_idx++;
			}
		}
		return v_idx;
	}

	/**
	 * Get the global name for the parameter, return null if the parameter has not
	 * to be write (duplicate parameter). DUPLICATE NAME GENERATION -> TO BE
	 * MODIFIED !
	 * 
	 * @param p_param The parameter.
	 * @return The name of the parameter (null or paramName or paramName_n).
	 */
	public static String getGlobalParamName(Parameter p_param) {

		// Get the response for the parameter existence
		// and return the builded name..
		int v_returnCode = checkParamNotAlreadyWrited(p_param);

		if (0 == v_returnCode) {
			return p_param.getName();
		} else if (v_returnCode > 0) {
			return p_param.getName() + "_" + v_returnCode;
		} else {
			return null;
		}
	}

	/**
	 * Retrieve the good name for the global parameter (depends of the type and the
	 * multiplicity).
	 * 
	 * @param p_param
	 * @param p_service : The service with all the operations.
	 * @return
	 */
	public static String retreiveGlobalInputParamName(final Parameter p_param, final Service p_service) {

		// If second generation (and more), we have to recreate the
		// list of global params, because of eventual "user code" tags.
		checkAndRegenerateGlobalParamsList(p_service);

		String v_name = "PARAM_NAME_NOT_FOUND_!!";

		// Browse all parameters in the list.
		for (GlobalParam v_globalParam : _params) {
			// Check if the name of the parameter is found in the list.
			if (v_globalParam.get_param().getName().equals(p_param.getName())
					&& v_globalParam.get_param().getType().getName().equals(p_param.getType().getName())
					&& checkMultiplicity(p_param, v_globalParam.get_param())) {
				v_name = v_globalParam.get_globalName();
				break;
			}
		}
		return v_name;
	}

	public static String retreiveGlobalInputParamNameJavaService(final Parameter p_param,final Service p_service){return retreiveGlobalInputParamName(p_param,p_service);}

	/**
	 * Check if we have lost (by reset, or new studio opening, etc...) the list of
	 * global params. Create the list again with all the parameters for all
	 * operations of the service.
	 * 
	 * @param p_service : The service with all the operations.
	 */
	private static void checkAndRegenerateGlobalParamsList(final Service p_service) {

		// Check list nullity.
		if (null == _params) {
			
			// Get all the input parameters of all operations.
			List<Parameter> v_parameters = new ArrayList<Parameter>();
			for (Operation v_Operation : p_service.getOwnedInterface().getOwnedOperations()) {
				v_parameters.addAll(v_Operation.getInput());
			}
			
			// Sort by name (as in OCL script).
			Collections.sort(v_parameters, new Comparator<Parameter>() {

				@Override
				public int compare(Parameter p1, Parameter p2) {
					return p1.getName().compareTo(p2.getName());
				}
			});

			// Iterate over all sorted parameters.
			for (Parameter p_param : v_parameters) {
				// Reconnection to the main method.
				checkParamNotAlreadyWrited(p_param);
			}
		}
	}

	/**
	 * Reset the array list before use.
	 */
	public static void reset() {
		_params = null;
	}

	public static void resetJavaService(Object object){reset();}

	/**
	 * Container for the global parameter.
	 */
	private static class GlobalParam {

		/**
		 * The name to display for the parameter.
		 */
		private String _globalName;

		/**
		 * The initial parameter.
		 */
		private Parameter _param;

		/**
		 * Constructor.
		 * 
		 * @param p_idx
		 * @param p_param
		 */
		GlobalParam(final int p_idx, final Parameter p_param) {

			if (p_idx > 0) {
				_globalName = p_param.getName() + "_" + p_idx;
			} else {
				_globalName = p_param.getName();
			}
			_param = p_param;
		}

		public String get_globalName() {
			return _globalName;
		}

		public Parameter get_param() {
			return _param;
		}
	}
}
