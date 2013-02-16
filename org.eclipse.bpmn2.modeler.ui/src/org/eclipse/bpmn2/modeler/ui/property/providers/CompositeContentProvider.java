/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.property.providers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;

/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 18, 2006
 *
 */
public class CompositeContentProvider extends AbstractContentProvider {
	

	List fList = new LinkedList();

	
	/**
	 * 
	 */
	public CompositeContentProvider() {
		super();
	}

	/**
	 * @param providers
	 */
	
	public CompositeContentProvider (IStructuredContentProvider[] providers) {
		super();
		if (providers != null) {
			for(int i=0; i<providers.length; i++) {
				fList.add(providers[i]);
			}
		}		
	}

	public void add ( IStructuredContentProvider provider ) {
		fList.add ( provider );
	}
	
	
	@Override
	public void collectElements (Object input, List list) {
		
		// Check if the input parameter is a complex one
		if (collectComplex(input, list)) {
			return ;
		}
		
		
		Iterator it = fList.iterator();
		
		while (it.hasNext()) {
			
			Object next = it.next();
			
			if (next instanceof AbstractContentProvider) {
				AbstractContentProvider provider = (AbstractContentProvider) next;
				provider.collectElements(input, list);
				
			} else if (next instanceof IStructuredContentProvider) {
				
				IStructuredContentProvider provider = (IStructuredContentProvider) next;
				Object objs [] = provider.getElements(input);			
				if (objs.length > 0) {
					for(int j=0; j<objs.length;j++) {
						list.add(objs[j]);
					}
				}
			} 
			// silently ignore non-conformists ?
			
		}				
	}
	
}
