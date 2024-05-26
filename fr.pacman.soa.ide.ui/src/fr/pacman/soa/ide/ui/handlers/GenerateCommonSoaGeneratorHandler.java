//Start of user code copyright
//End of user code

package fr.pacman.soa.ide.ui.handlers;

//Start of user code imports

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.obeonetwork.dsl.soa.System;
import org.eclipse.jface.viewers.IStructuredSelection;
import fr.pacman.soa.ide.ui.GenerateCommonSoaGeneratorEclipse;
import org.eclipse.ui.handlers.HandlerUtil;

//End of user code

/**
 * Command handler for fr::pacman::soa::generateCommonSoa.
 * 
 * @author patri
 * @generated
 */
public class GenerateCommonSoaGeneratorHandler extends AbstractHandler {

	/**
	 * @generated
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IStructuredSelection selection = HandlerUtil.getCurrentStructuredSelection(event);

		final Iterator<?> it = selection.iterator();
		while (it.hasNext()) {
			final Object selected = it.next();
			if (selected instanceof System) {
				final GenerateCommonSoaGeneratorEclipse generator = new GenerateCommonSoaGeneratorEclipse((System)selected);
				generator.generate();
			}
		}

		return null;
	}

}
