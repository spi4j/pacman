package fr.pacman.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.eclipse.acceleo.engine.AcceleoEngineMessages;
import org.eclipse.acceleo.engine.AcceleoEnginePlugin;
import org.eclipse.acceleo.engine.AcceleoEvaluationException;
import org.eclipse.acceleo.engine.generation.strategy.PreviewStrategy;
import org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter;

/**
 * Strategie de generation de Pacman pour la previsualisation : Basee sur la
 * {@link PreviewStrategy}.
 * 
 * @author MINARM
 */
class PacmanStrategy4Test extends PreviewStrategy {

	private final String _lineDelimiter;

	/**
	 * Constructeur de stratégie avec délimiteur de lignes
	 * 
	 * @param p_lineDelimiter
	 *            le délimiteur de lignes
	 */
	public PacmanStrategy4Test(final String p_lineDelimiter) {
		_lineDelimiter = p_lineDelimiter;
	}

	@Override
	public AbstractAcceleoWriter createWriterFor(final File p_file, final AbstractAcceleoWriter p_previous,
			final boolean p_appendMode, final boolean p_hasJMergeTags, final String p_charset) throws IOException {
		final AbstractAcceleoWriter v_writer;

		if (p_file.isDirectory()) {
			throw new AcceleoEvaluationException(
					AcceleoEngineMessages.getString("AcceleoEvaluationContext.FileNameIsDirectory", p_file)); //$NON-NLS-1$
		}

		if (p_appendMode && p_previous != null) {
			v_writer = p_previous;
			v_writer.append(LINE_SEPARATOR);
		} else if (!p_appendMode && p_hasJMergeTags) {
			// previous cannot be null if hasJMergeTags is true
			v_writer = p_previous;
			/*
			 * We know the writer is an AcceleoStringWriter, reinitializing it allows it to
			 * know it should merge its content through JMerge when it's next closed.
			 */
			v_writer.reinit();
		} else {
			if (p_charset != null) {
				if (Charset.isSupported(p_charset)) {
					v_writer = new PacmanWriter4Test(_lineDelimiter, p_file, p_appendMode, p_hasJMergeTags,
							p_charset);
				} else {
					final String v_message = AcceleoEngineMessages
							.getString("AcceleoGenerationStrategy.UnsupportedCharset", p_charset); //$NON-NLS-1$
					AcceleoEnginePlugin.log(v_message, false);
					v_writer = new PacmanWriter4Test(_lineDelimiter, p_file, p_appendMode, p_hasJMergeTags);
				}
			} else {
				v_writer = new PacmanWriter4Test(_lineDelimiter, p_file, p_appendMode, p_hasJMergeTags);
			}
		}
		return v_writer;
	}
}
