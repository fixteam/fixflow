/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.dialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ViewerFileFilter extends ViewerFilter {

	private String[] patterns;
	
	static String COMMA_SEPARATOR = ","; //$NON-NLS-1$

	public ViewerFileFilter() {
		this(null);
	}
	/**
	 * Creates a new resource pattern filter.
	 */
	public ViewerFileFilter(String patterns) {
		super();
		
		if (patterns != null) {
			//Get the strings separated by a comma and filter them from the currently
			//defined ones
			StringTokenizer entries = new StringTokenizer(patterns, COMMA_SEPARATOR);
			List localPatterns = new ArrayList();
			
			while (entries.hasMoreElements()) {
				String nextToken = entries.nextToken();
				if (patterns.indexOf(nextToken) > -1)
					localPatterns.add(nextToken);
			}
			
			//Convert to an array of Strings
			String[] patternArray = new String[localPatterns.size()];
			localPatterns.toArray(patternArray);
			setPatterns(patternArray);
		}
	}
	/**
	 * Return the currently configured StringMatchers. If there aren't any look
	 * them up.
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		IResource resource = null;
		if (element instanceof IFile) {
			resource = (IFile) element;
		}
		else
			return true;
		if (resource != null) {
			String name = resource.getName();
			for (int i = 0; i < patterns.length; i++) {
				if (name.endsWith(patterns[i]))
					return true;
			}
			return false;
		}
		return true;
	}
	/**
	 * Sets the patterns to filter out for the receiver.
	 */
	public void setPatterns(String[] newPatterns) {
	
		this.patterns = newPatterns;
	}
}
