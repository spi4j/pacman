/*******************************************************************************
 * Copyright (c) 2008, 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.requirementsgrouper.ui.common;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;


/**
 * Main entry point of the 'RequirementsGrouper' generation module.
 */
public class GenerateAll {

	/**
	 * The model URI.
	 */
	@SuppressWarnings("unused")
	private URI modelURI;

	/**
	 * The output folder.
	 */
	private IContainer targetFolder;

	/**
	 * The other arguments.
	 */
	List<? extends Object> arguments;

	/**
	 * Constructor.
	 * 
	 * @param modelURI
	 *            is the URI of the model.
	 * @param targetFolder
	 *            is the output folder
	 * @param arguments
	 *            are the other arguments
	 * @throws IOException
	 *             Thrown when the output cannot be saved.
	 * @generated
	 */
	public GenerateAll(URI modelURI, IContainer targetFolder, List<? extends Object> arguments) {
		this.modelURI = modelURI;
		this.targetFolder = targetFolder;
		this.arguments = arguments;
	}

	/**
	 * Launches the generation.
	 *
	 * @param monitor
	 *            This will be used to display progress information to the user.
	 * @throws IOException
	 *             Thrown when the output cannot be saved.
	 * @generated
	 */
	public void doGenerate(IProgressMonitor monitor) throws IOException {
		if (!targetFolder.getLocation().toFile().exists()) {
			targetFolder.getLocation().toFile().mkdirs();
		}
		
	}
	
	/**
	 * Finds the template in the plug-in. Returns the template plug-in URI.
	 * 
	 * @param bundleID
	 *            is the plug-in ID
	 * @param relativePath
	 *            is the relative path of the template in the plug-in
	 * @return the template URI
	 * @throws IOException
	 * @generated
	 */
	@SuppressWarnings("unused")
	private URI getTemplateURI(String bundleID, IPath relativePath) throws IOException {
		Bundle bundle = Platform.getBundle(bundleID);
		if (bundle == null) {
			// no need to go any further
			return URI.createPlatformResourceURI(new Path(bundleID).append(relativePath).toString(), false);
		}
		URL url = bundle.getEntry(relativePath.toString());
		if (url == null && relativePath.segmentCount() > 1) {
			Enumeration<URL> entries = bundle.findEntries("/", "*.emtl", true);
			if (entries != null) {
				String[] segmentsRelativePath = relativePath.segments();
				while (url == null && entries.hasMoreElements()) {
					URL entry = entries.nextElement();
					IPath path = new Path(entry.getPath());
					if (path.segmentCount() > relativePath.segmentCount()) {
						path = path.removeFirstSegments(path.segmentCount() - relativePath.segmentCount());
					}
					String[] segmentsPath = path.segments();
					boolean equals = segmentsPath.length == segmentsRelativePath.length;
					for (int i = 0; equals && i < segmentsPath.length; i++) {
						equals = segmentsPath[i].equals(segmentsRelativePath[i]);
					}
					if (equals) {
						url = bundle.getEntry(entry.getPath());
					}
				}
			}
		}
		URI result;
		if (url != null) {
			result = URI.createPlatformPluginURI(new Path(bundleID).append(new Path(url.getPath())).toString(), false);
		} else {
			result = URI.createPlatformResourceURI(new Path(bundleID).append(relativePath).toString(), false);
		}
		return result;
	}

}
