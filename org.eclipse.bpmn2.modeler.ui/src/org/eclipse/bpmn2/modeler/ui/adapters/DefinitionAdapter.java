/*
 * Copyright (c) 2010 JBoss, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.bpmn2.modeler.ui.adapters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.adapters.BasicEObjectAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.INamespaceMap;
import org.eclipse.wst.wsdl.Definition;

/*
 * This adds the INamespace adapter to Definition objects (required for the XPath expression editor)
 *
 * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=330813
 * @see https://jira.jboss.org/browse/JBIDE-7107
 * @author Bob Brodt
 * @date Oct 12, 2010
 */
public class DefinitionAdapter extends BasicEObjectAdapter implements INamespaceMap<String, String> {

	/**
	 * 
	 * @param key
	 *            the namespace to get the reverse mapping for
	 * @return The reverse mapping of the Namespace to namespace prefixes.
	 * @see org.eclipse.bpel.model.adapters.INamespaceMap#getReverse(java.lang.Object)
	 */

	public List<String> getReverse(String key) {
		List<String> list = new ArrayList<String>(1);
		list.add( ((Definition)getTarget()).getPrefix(key) );
		return list;
	}

}
