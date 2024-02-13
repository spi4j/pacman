package fr.pacman.start.ui.exception;

public class PacmanInitModelException extends Exception {

	private static final long serialVersionUID = 1L;

	public PacmanInitModelException(final Exception p_e) {
		super(p_e);
	}

	public PacmanInitModelException(final String p_message) {
		super(p_message);
	}
}
