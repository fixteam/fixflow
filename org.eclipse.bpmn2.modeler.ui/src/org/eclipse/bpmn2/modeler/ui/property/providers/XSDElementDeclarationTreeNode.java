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

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


public class XSDElementDeclarationTreeNode extends XSDTreeNode {

	public XSDElementDeclarationTreeNode(XSDElementDeclaration element, boolean isCondensed) {
		super(element, isCondensed);
	}
	
	public XSDElementDeclaration getResolvedModelObject() {
		return (XSDElementDeclaration)ModelUtil.resolveXSDObject(modelObject);
	}
		
	@Override
	public Object[] getChildren() {
		XSDTypeDefinition xsdType = getResolvedModelObject().getType();
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
		XSDElementDeclaration element = getResolvedModelObject();
		XSDTypeDefinition xsdType = element.getType();
		if (xsdType == null) {
			xsdType = element.getTypeDefinition();
		}
		
		if (xsdType instanceof XSDComplexTypeDefinition) {
			XSDComplexTypeDefinition comp = (XSDComplexTypeDefinition) xsdType;
			return complexTypeHasChildren(comp);
		}
		return false;
	}

	@Override
	public String getLabel() {
		XSDElementDeclaration element = getResolvedModelObject();
		String name = element.getName();
		return (name != null) ? name : ""; //$NON-NLS-1$
	}

	@Override
	public String getLabelSuffix() {
		XSDElementDeclaration element = getResolvedModelObject();
		XSDTypeDefinition type = element.getType();
		return (type != null) ? type.getName() : null;  // note: null, not ""
	}
}
