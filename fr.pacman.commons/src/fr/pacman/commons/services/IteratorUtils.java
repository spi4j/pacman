package fr.pacman.commons.services;

import org.eclipse.emf.ecore.EObject;

public class IteratorUtils {

	private static Integer _count;

	public static String initIteratorJavaService(final Integer p_count) {
		_count = p_count;
		return "";
	}

	public static boolean nextIteratorJavaService(final EObject p_EObject) {
		if (_count == 1) {
			return false;
		}
		_count--;
		return true;
	}
}
