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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.bpmn2.modeler.ui.util.XSDUtils;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * Common base class for XSD-related tree nodes (for helpers etc.)
 */
public abstract class XSDTreeNode extends TreeNode {

	public XSDTreeNode(Object modelObject, boolean isCondensed) {
		super(modelObject, isCondensed);
	}

	public static List getInheritedXSDElementsAndAttributes(XSDComplexTypeDefinition complexType) {
		List result = new ArrayList();
		XSDTypeDefinition parent = complexType.getBaseTypeDefinition();
		if (parent instanceof XSDComplexTypeDefinition && complexType.isSetDerivationMethod()) {
			// order switched, from the XSDUtils version
			result.addAll(getInheritedXSDElementsAndAttributes((XSDComplexTypeDefinition)parent));
			result.addAll(XSDUtils.getXSDElementsAndAttributes((XSDComplexTypeDefinition)parent));
		}
		return result;
	}

	protected void addComplexTypeContent(XSDComplexTypeDefinition complexType, Vector<TreeNode> v) {
		List attrs = getInheritedXSDElementsAndAttributes(complexType);
		attrs.addAll(XSDUtils.getXSDElementsAndAttributes(complexType));
		for (Iterator it = attrs.iterator(); it.hasNext(); ) {
			Object attr = it.next();
			if (attr instanceof XSDAttributeDeclaration) {
				v.add(new XSDAttributeDeclarationTreeNode((XSDAttributeDeclaration)attr, isCondensed));
			} else if (attr instanceof XSDElementDeclaration) {
				v.add(new XSDElementDeclarationTreeNode((XSDElementDeclaration)attr, isCondensed));
			} else if (attr instanceof XSDComplexTypeDefinition) {
				v.add(new XSDTypeDefinitionTreeNode((XSDComplexTypeDefinition)attr, isCondensed));
			}
		}
	}
	
	protected boolean complexTypeHasChildren(XSDComplexTypeDefinition complexType) {
		if (!XSDUtils.getXSDElementsAndAttributes(complexType).isEmpty()) return true;
		if (!getInheritedXSDElementsAndAttributes(complexType).isEmpty()) return true;
		return false;
	}
	
	protected void addNodes ( List<TreeNode> dstList, List srcList ) {
		Iterator i = srcList.iterator();
		while (i.hasNext()) {
			Object obj = i.next();
			if (obj instanceof XSDAttributeDeclaration) {
				dstList.add(new XSDAttributeDeclarationTreeNode((XSDAttributeDeclaration)obj, isCondensed));
			} else if (obj instanceof XSDElementDeclaration) {
				dstList.add(new XSDElementDeclarationTreeNode((XSDElementDeclaration)obj, isCondensed));
			} else if (obj instanceof XSDTypeDefinition) {
				dstList.add(new XSDTypeDefinitionTreeNode((XSDTypeDefinition)obj, isCondensed));
			}
		}
	}
	
}
