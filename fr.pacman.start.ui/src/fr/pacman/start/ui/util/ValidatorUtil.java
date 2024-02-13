package fr.pacman.start.ui.util;

import org.eclipse.jface.dialogs.IMessageProvider;

/**
 * 
 * @author MINARM.
 *
 */
public class ValidatorUtil {

	public static ValidatorUtil INSTANCE = new ValidatorUtil();

	private boolean _databaseOK;
	private boolean _eDatabaseOK;
	private boolean _applicationNewOK;
	private boolean _applicationOK;
	private boolean _authorOK;
	private boolean _packageOK;
	private boolean _spi4JOK;
	private boolean _useLibrariesOK;
	private boolean _sqlTablePrefixOK;
	private boolean _sqlTableSchemaOK;
	private boolean _requirementPrefixOK;
	private boolean _additionnalFieldsOK;
	private boolean _oracleOK;
	private boolean _wsOK;

	/**
	 * Constructeur privé pour éviter l'instanciation de la classe.
	 */
	private ValidatorUtil() {
		// EMPTY.
	}

	/**
	 * Calcul de la validité des paramètres en fonction des données saisies.
	 * 
	 * @return boolean
	 */
	public boolean isValid() {

		return _applicationOK 
				&& _databaseOK
				&& _eDatabaseOK
				&& _useLibrariesOK
				&& _applicationOK
				&& _applicationNewOK 
				&& _sqlTablePrefixOK 
				&& _requirementPrefixOK 
				&& _additionnalFieldsOK
				&& _packageOK 
				&& _authorOK
				&& _spi4JOK 
				&& _oracleOK
				&& _wsOK;
	}

	/**
	 * Retourne le message à afficher à l'utilisateur en cas d'erreur de saisie.
	 * 
	 * @return String
	 */
	public String getMessage() {

		if (!_useLibrariesOK)
			return TextUtil.c_ERR_LIBRARIES_EMPTY;

		if (!_applicationNewOK)
			return TextUtil.c_ERR_PROJECT_EXIST;

		if (!_applicationOK)
			return TextUtil.c_ERR_PROJECT_EMPTY;

		if (!_packageOK)
			return TextUtil.c_ERR_PACKAGE_VALID;
		
		if(! _authorOK)
			return TextUtil.c_ERR_AUTHOR_EMPTY;

		if (!_sqlTableSchemaOK)
			return TextUtil.c_ERR_SQL_TABLE_SCHEMA_VALID;

		if (!_sqlTablePrefixOK)
			return TextUtil.c_ERR_SQL_TABLE_PREFIX_VALID;

		if (!_requirementPrefixOK)
			return TextUtil.c_ERR_SQL_REQUIREMENT_PREFIX_VALID;

		if (!_oracleOK)
			return TextUtil.c_ERR_ORACLE_VALID;

		if (!_additionnalFieldsOK)
			return TextUtil.c_ERR_SQL_ADD_FIELDS;
		
		if(!_wsOK)
			return TextUtil.c_ERR_WS_RD_STATE;
		
		if(!_databaseOK)
			return TextUtil.c_ERR_DB_NONE;
		
		if(!_eDatabaseOK)
			return TextUtil.c_ERR_DB_CHOICE;

		return null;
	}

	/**
	 * Typologie du message à retourner (pour l'instant toujours ERR.).
	 * 
	 * @return
	 */
	public int getMessageType() {

		return IMessageProvider.ERROR;
	}

	public void setApplicationOK(boolean p_applicationOK) {
		_applicationOK = p_applicationOK;
	}

	public void setApplicationNewOk(boolean p_applicationNewOK) {
		_applicationNewOK = p_applicationNewOK;
	}

	public void setPackageOK(boolean p_packageOK) {
		_packageOK = p_packageOK;
	}
	
	public void setAuthorOK(boolean p_authorOK) {
		_authorOK = p_authorOK;
	}

	public void setSpi4JOK(boolean p_spi4JOK) {
		_spi4JOK = p_spi4JOK;
	}

	public void setSqlTablePrefixOk(boolean p_sqlTablePrefixOk) {
		_sqlTablePrefixOK = p_sqlTablePrefixOk;
	}

	public void setSqlTableSchema(boolean p_sqlTableSchemaOk) {
		_sqlTableSchemaOK = p_sqlTableSchemaOk;
	}

	public void setRequirementPrefixOK(boolean p_requirementPrefixOk) {
		_requirementPrefixOK = p_requirementPrefixOk;
	}

	public void setAdditionalFieldsOK(boolean p_additionalFieldsOK) {
		_additionnalFieldsOK = p_additionalFieldsOK;
	}

	public void setUseLibrariesOK(boolean p_useLibrariesOK) {
		_useLibrariesOK = p_useLibrariesOK;
	}

	public void setOracleOK(boolean p_oracleOK) {
		_oracleOK = p_oracleOK;
	}
	
	public void setWsOk(boolean p_wsOK) {
		_wsOK = p_wsOK;
	}
	
	public void setDatabaseOK(boolean p_databaseOK) {
		_databaseOK = p_databaseOK;
	}
	
	public void setEmbeddedDatabaseOK(boolean p_eDatabaseOK) {
		_eDatabaseOK = p_eDatabaseOK;
	}
}