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

import org.eclipse.wst.wsdl.Part;

/**
 * Tree node to represent a Part within the messageType of a Variable.
 */
public class PartTreeNode extends TreeNode {

	XSDTypeDefinitionTreeNode xsdType;
	XSDElementDeclarationTreeNode xsdElement;
	
	boolean displayParticles;
	
	public PartTreeNode(Part part, boolean isCondensed) {
		this(part, isCondensed, true);
	}
	public PartTreeNode(Part part, boolean isCondensed, boolean displayParticles) {
		super(part, isCondensed);
		if (part.getTypeDefinition() != null)  {
//			XSDElementDeclaration e = part.getEXSDElement();
			xsdType = new XSDTypeDefinitionTreeNode(part.getTypeDefinition(), false);
		} else if (part.getElementDeclaration() != null) {
			xsdElement = new XSDElementDeclarationTreeNode(part.getElementDeclaration(), false);
		}
		this.displayParticles = displayParticles;
	}

	/* ITreeNode */

	@Override
	public String getLabelSuffix() {
		if (xsdType != null) {  
			String label = xsdType.getLabel();
			return label;
		} else if (xsdElement != null) {
			String label = xsdElement.getLabel();
			return label;
		}
		return null;
	}

	@Override
	public Object[] getChildren() {
		if (xsdType != null && displayParticles)  
			return xsdType.getChildren();
		if (xsdElement != null && displayParticles)
			return xsdElement.getChildren();
		return EMPTY_ARRAY;
	}

	@Override
	public boolean hasChildren() {
		if (xsdType != null && displayParticles)  
			return xsdType.hasChildren();
		if (xsdElement != null && displayParticles)
			return xsdElement.hasChildren();
		return false;
	}

	@Override
	public String getLabel() {
		String label = super.getLabel();
		return label;
	}
}
