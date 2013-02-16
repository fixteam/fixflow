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

import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDTypeDefinition;


public class XSDAttributeDeclarationTreeNode extends TreeNode {

	public XSDAttributeDeclarationTreeNode(XSDAttributeDeclaration modelObject, boolean isCondensed) {
		super(modelObject, isCondensed);
	}

	public XSDAttributeDeclaration getResolvedModelObject() {
		return (XSDAttributeDeclaration)ModelUtil.resolveXSDObject(modelObject);
	}
	
	@Override
	public Object[] getChildren() {
		return EMPTY_ARRAY;
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public String getLabel() {
		XSDAttributeDeclaration attribute = getResolvedModelObject();
		String name = attribute.getName();
		return (name != null) ? name : ""; //$NON-NLS-1$
	}

	@Override
	public String getLabelSuffix() {
		XSDAttributeDeclaration attribute = getResolvedModelObject();
		XSDTypeDefinition type = attribute.getType();
		return (type != null) ? type.getName() : ""; //$NON-NLS-1$
	}
}
