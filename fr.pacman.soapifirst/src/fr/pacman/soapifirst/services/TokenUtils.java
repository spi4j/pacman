package fr.pacman.soapifirst.services;

/**
 * Utility class to store the token procurement mode value.
 *
 * @author MINARM
 */
public class TokenUtils {

	private static String _tokenRecoveryMode;

	/**
	 * Set the value for the static value.
	 *
	 * @param p_tokenRecoveryMode : the value for the static value 'HEAD' or
	 *                               'BODY'.
	 * @return boolean for value setted or not.
	 */
	public static boolean setTokenRecoveryMode(final String p_tokenRecoveryMode) {

		if (null != p_tokenRecoveryMode
				&& (TokenMode_Enum.HEAD.get_value().equalsIgnoreCase(p_tokenRecoveryMode)
				|| TokenMode_Enum.BODY.get_value().equalsIgnoreCase(p_tokenRecoveryMode))) {
			_tokenRecoveryMode = p_tokenRecoveryMode;
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Get the value of the static value.
	 *
	 * @return
	 */
	public static String getTokenRecoveryMode() {

		return _tokenRecoveryMode;
	}

	/**
	 * Reset the static value before each generation.
	 *
	 * @return true.
	 */
	public static boolean resetTokenRecoveryMode() {

		_tokenRecoveryMode = null;
		return Boolean.TRUE;
	}
}

enum TokenMode_Enum {

	HEAD("HEAD"), BODY("BODY");

	private String _value;

	TokenMode_Enum(final String p_value) {
		_value = p_value;
	}

	public String get_value() {
		return _value;
	}
}
