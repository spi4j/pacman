package fr.pacman.commons.ui;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.entity.Root;
import org.obeonetwork.dsl.environment.Namespace;
import org.obeonetwork.dsl.environment.NamespacesContainer;

public class PacmanUIPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if ("testNamespaceSoa".equals(property) || "testNamespaceEntity".equals(property)) {

			if (!(receiver instanceof Namespace) || receiver instanceof org.obeonetwork.dsl.soa.System)
				return false;

			return checkTypeNameSpace(property, (EObject) receiver);
		}
		return false;
	}

	private boolean f(final EObject receiver) {

		if (receiver instanceof Root)
			return false;

		if (receiver instanceof org.obeonetwork.dsl.soa.System)
			return false;
		return true;
	}

	private boolean checkTypeNameSpace(final String type, final EObject receiver) {

		if (null == receiver)
			return false;

		NamespacesContainer owner = ((Namespace) receiver).getOwner();

		while (f(owner)) {
			checkTypeNameSpace(type, owner);
		}

//		// On fait au plus simple.
//		String fileName = owner.eResource().getURI().lastSegment();
//		if (null == fileName || fileName.isEmpty() || fileName.isEmpty() || !fileName.contains("."))
//			return false;
//		String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());

		if ("testNamespaceSoa".equals(type) && owner instanceof org.obeonetwork.dsl.soa.System)
			return true;

		if ("testNamespaceEntity".equals(type) && owner instanceof Root)
			return true;

		return false;
	}

}
