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
import java.util.List;

import org.eclipse.wst.wsdl.Input;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.Output;

/**
 * Tree node to represent an Operation model object.
 */
public class OperationTreeNode extends TreeNode {

	public OperationTreeNode(Operation operation, boolean isCondensed) {
		super(operation, isCondensed);
	}

	/* ITreeNode */

	@Override
	public Object[] getChildren() {		
		Operation op = (Operation)modelObject;
		List<MessageTypeTreeNode> list = new ArrayList<MessageTypeTreeNode>();
		if (op.getInput() != null ) {
			
			list.add(new MessageTypeTreeNode((Input)op.getInput(),isCondensed));
		}
		if (op.getOutput() != null ) {
			list.add(new MessageTypeTreeNode((Output)op.getOutput(),isCondensed));
		}
		
		return list.toArray();
	}

	@Override
	public boolean hasChildren() {
		Operation op = (Operation) modelObject;
		if (op.getInput() != null) {
			return true;
		}
		
		if (op.getOutput() != null) {
			return true;
		}
		
		return false;
	}
}
