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

import java.util.Vector;

import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * Tree node to represent an XSD type model object.
 */
public class XSDTypeDefinitionTreeNode extends XSDTreeNode {

	public XSDTypeDefinitionTreeNode(XSDTypeDefinition xsdType, boolean isCondensed) {
		super(xsdType, isCondensed);
	}

	@Override
	public boolean isNodeFlattenable() { return true; }
	
	@Override
	public Object[] getChildren() {
		XSDTypeDefinition xsdType = (XSDTypeDefinition)modelObject;
		if (xsdType instanceof XSDComplexTypeDefinition) {
			Vector<TreeNode> v = new Vector<TreeNode>();
			XSDComplexTypeDefinition comp = (XSDComplexTypeDefinition) xsdType;
			addComplexTypeContent(comp, v);
			return v.toArray();
		}
		return EMPTY_ARRAY;
	}

	@Override
	public boolean hasChildren() {
		XSDTypeDefinition xsdType = (XSDTypeDefinition)modelObject;
		if (xsdType instanceof XSDComplexTypeDefinition) {
			XSDComplexTypeDefinition comp = (XSDComplexTypeDefinition) xsdType;
			return complexTypeHasChildren(comp);
		}
		return false;
	}
	
	@Override
	public String getLabel() {
		XSDTypeDefinition xsdType = (XSDTypeDefinition)modelObject;
		String name = xsdType.getName();
		return (name != null) ? name : ""; //$NON-NLS-1$
	}
	
	@Override
	public String getLabelSuffix() {
		return super.getLabelSuffix();
	}
}

