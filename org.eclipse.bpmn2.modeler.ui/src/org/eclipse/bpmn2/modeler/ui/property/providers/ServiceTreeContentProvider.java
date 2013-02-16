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
package org.eclipse.bpmn2.modeler.ui.property.providers;

import org.eclipse.bpmn2.modeler.ui.util.ListMap;
import org.eclipse.wst.wsdl.Definition;
import org.eclipse.wst.wsdl.PortType;


/**
 * Provides a tree of model objects representing some expansion of the underlying graph
 * of model objects whose roots are the Variables of a Process. 
 */
public class ServiceTreeContentProvider extends ModelTreeContentProvider {

	boolean isPropertyTree;
	private CompositeContentProvider fContentProvider;	
	
	
	public ServiceTreeContentProvider( boolean isCondensed ) {
		super(isCondensed);
		
		fContentProvider = new CompositeContentProvider ();
		fContentProvider.add ( new ServiceContentProvider() );
		fContentProvider.add ( new PortTypeContentProvider() );	
		
	}

	public boolean isPropertyTree() { return isPropertyTree; }

	
	
	@Override
	public Object[] primGetElements (Object inputElement) {
		
		ITreeNode result = getTreeNode ( inputElement );
		if (result != null) {
			return new Object[] { result } ;
		}
		
		
		if (inputElement instanceof Definition) {									
			
			return (Object[]) ListMap.Map ( 
					fContentProvider.getElements( inputElement ) ,						
					new ListMap.Visitor () {		
						public Object visit (Object obj) {
							Object r = getTreeNode ( obj );
							return (r == null ? ListMap.IGNORE : r );
						}					
					},
					EMPTY_ARRAY );							
		}
		
		return EMPTY_ARRAY;
	}
	
	
	ITreeNode getTreeNode ( Object inputElement ) {
		
		if (inputElement instanceof PortType) {
			return new PortTypeTreeNode ((PortType) inputElement,isCondensed);
		}
		
		return null;
		
	}
}