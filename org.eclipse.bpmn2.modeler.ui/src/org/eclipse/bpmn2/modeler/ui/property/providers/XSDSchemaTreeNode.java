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

import java.text.MessageFormat;
import java.util.ArrayList;

import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.xsd.XSDSchema;

/**
 * Tree node to represent an XSD type model object.
 */
public class XSDSchemaTreeNode extends XSDTreeNode {

	public XSDSchemaTreeNode(XSDSchema schema, boolean isCondensed) {
		super(schema, isCondensed);
	}

	@Override
	public boolean isNodeFlattenable() { 
		return true; 
	}
	
	@Override
	public Object[] getChildren() {
		XSDSchema schema = (XSDSchema)modelObject;
		ArrayList<TreeNode> list = new ArrayList<TreeNode>();
		// addNodes(list, schema.getAttributeDeclarations());
		addNodes(list, schema.getElementDeclarations());
		addNodes(list, schema.getTypeDefinitions());
		return list.toArray();
	}

	@Override
	public boolean hasChildren() {
		XSDSchema schema = (XSDSchema)modelObject;
		return schema.getElementDeclarations().size() > 0 ||
		 schema.getTypeDefinitions().size() > 0;
	}
	
	@Override
	public String getLabel() {
		XSDSchema schema = (XSDSchema)modelObject;
		schema.getTargetNamespace();
		
		String tns = schema.getTargetNamespace();
		if (tns == null) {
			return Messages.XSDSchemaTreeNode_0;
		}
		return MessageFormat.format(Messages.XSDSchemaTreeNode_1,
							new Object[]{ tns });		
	}
	
	@Override
	public String getLabelSuffix() {
		return super.getLabelSuffix();
	}
}

