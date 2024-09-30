package fr.pacman.entity.jdbc.ui.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.handlers.HandlerUtil;

import fr.pacman.entity.jdbc.ui.JdbcUIGenerators;

/**
 * UI handler for JDBC generator. Generic algorithm for the method but for
 * pacman generators, only unique selection is allowed.
 * 
 * @author MINARM
 */
public class JdbcGeneratorsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent p_event) throws ExecutionException {
		final Iterator<?> v_iterator = HandlerUtil.getCurrentStructuredSelection(p_event).iterator();

		while (v_iterator.hasNext()) {
			final Object v_selected = v_iterator.next();
			if (v_selected instanceof IFile) {
				new JdbcUIGenerators((IFile) v_selected).generate();
			}
		}
		return null;
	}
}
