package fr.pacman.soa.ui.handlers;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.dsl.soa.System;

import fr.pacman.soa.ui.SoaUIGenerators;

/**
 * 
 * @author MINARM
 */
public class SoaGeneratorsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent p_event) throws ExecutionException {

		final Iterator<?> v_iterator = HandlerUtil.getCurrentStructuredSelection(p_event).iterator();

		while (v_iterator.hasNext()) {
			final Object v_selected = v_iterator.next();
			if (v_selected instanceof IFile) {
				new SoaUIGenerators((IFile) v_selected).generate();
			} else if (v_selected instanceof System) {
				new SoaUIGenerators((System) v_selected).generate();
			}
		}
		return null;
	}
}
