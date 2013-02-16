/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.core.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * As the name implies, this is a static class of java reflection utilities
 * @author Bob Brodt
 */
public class JavaReflectionUtil {
	
	/**
	 * Find the class with the given simple name in a package hierarchy.
	 * The object is used as the starting point (deepest level)
	 * and the search continues up the package hierarchy.
	 * 
	 * @param object - any object in a package
	 * @param simpleName - simple (non-qualified) class name to search for
	 * @return - the class or null if not found
	 */
	public static Class findClass(Object object, String simpleName) {
		ClassLoader cl = object.getClass().getClassLoader();
		String packageName = object.getClass().getPackage().getName();
		int index;
		while ((index = packageName.lastIndexOf(".")) != -1) {
			String className = packageName + "." + simpleName; 
			try {
				return Class.forName(className, true, cl);
			} catch (ClassNotFoundException e) {
			}
			packageName = packageName.substring(0, index);
		}
		return null;
		
	}
}
