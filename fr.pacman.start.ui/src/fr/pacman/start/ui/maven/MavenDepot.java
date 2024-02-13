package fr.pacman.start.ui.maven;

public class MavenDepot {
	private String _name;
	private String _url;
	private String _login;
	private String _password;
	private String _passwordHidden;
	
	public MavenDepot(String p_name, String p_url, String p_login, String p_password) {
		super();
		this._name = p_name;
		this._url = p_url;
		this._login = p_login;
		this._password = p_password;
		this._passwordHidden = this._password.replaceAll(".", "*");
	}
	public String getName() {
		return _name;
	}
	public void setName(String p_name) {
		this._name = p_name;
	}
	public String getUrl() {
		return _url;
	}
	public void setUrl(String p_url) {
		this._url = p_url;
	}
	public String getLogin() {
		return _login;
	}
	public void setLogin(String p_login) {
		this._login = p_login;
	}
	public String getPassword() {
		return _password;
	}
	public void setPassword(String p_password) {
		this._password = p_password;
		this._passwordHidden = this._password.replaceAll(".", "*");
	}
	public String getPasswordHidden() {
		return _passwordHidden;
	}
	public void setPasswordHidden(String p_passwordHidden) {
		this._passwordHidden = p_passwordHidden;
	}
	
	
}
