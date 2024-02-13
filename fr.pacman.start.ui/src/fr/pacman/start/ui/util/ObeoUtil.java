package fr.pacman.start.ui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Classe tres simplifiee par rapport a celle d'ISDesigner (OBEO), pour
 * l'instant pas de prise en compte de CDO.
 * 
 * (cf : org.obeonetwork.dsl.is.ui.wizards.AbstractISNewModelWizard)
 * 
 * @author MINARM
 *
 */
public class ObeoUtil {

	public static void setSelectionInActivePart(Resource p_resource) {

		IWorkbenchPart p_activePart = getActivePart();
		if (p_resource != null && p_activePart != null) {
			URI p_uri = p_resource.getURI();
			if (p_uri.isPlatformResource()) {
				String platformString = p_uri.toPlatformString(true);
				IResource member = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
				if (member instanceof IFile) {
					TreeSelection treeSelection = buildTreeSelection((IFile) member);
					p_activePart.getSite().getSelectionProvider().setSelection(treeSelection);
				}
			}
		}
	}

	private static IWorkbenchPart getActivePart() {

		IWorkbench v_workbench = PlatformUI.getWorkbench();
		if (v_workbench != null && v_workbench.getActiveWorkbenchWindow() != null
				&& v_workbench.getActiveWorkbenchWindow().getActivePage() != null) {
			return v_workbench.getActiveWorkbenchWindow().getActivePage().getActivePart();
		}
		return null;
	}

	private static TreeSelection buildTreeSelection(Object object) {

		List<Object> segments = null;
		segments = getTreeSegments((IFile) object);
		Collections.reverse(segments);
		return new TreeSelection(new TreePath(segments.toArray()));
	}

	private static List<Object> getTreeSegments(IResource resource) {
		List<Object> segments = new ArrayList<Object>();
		if (resource != null) {
			segments.add(resource);
			segments.addAll(getTreeSegments(resource.getParent()));
		}
		return segments;
	}
}
