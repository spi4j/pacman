package fr.pacman.start.ui.exception;

public class WizardNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public WizardNotFoundException(final String p_message) {
		super(p_message);
	}

	public WizardNotFoundException(Exception p_e) {
		super(p_e);
	}
}
