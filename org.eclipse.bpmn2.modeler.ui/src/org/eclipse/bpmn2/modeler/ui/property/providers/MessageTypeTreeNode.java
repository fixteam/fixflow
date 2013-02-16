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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.wsdl.Input;
import org.eclipse.wst.wsdl.Message;
import org.eclipse.wst.wsdl.Output;
import org.eclipse.wst.wsdl.Part;

/**
 * Tree node to represent a message-type model object.
 */
public class MessageTypeTreeNode extends TreeNode {

	boolean displayParticles;
	
	public MessageTypeTreeNode(Input msg, boolean isCondensed) {
		this(msg, isCondensed, true);		
	}
	
	
	public MessageTypeTreeNode(Output msg, boolean isCondensed) {
		this(msg, isCondensed, true);		
	}
	
	public MessageTypeTreeNode(Message messageType, boolean isCondensed) {
		this(messageType, isCondensed, true);
	}
	
	
	public MessageTypeTreeNode (Message messageType, boolean isCondensed,
		boolean displayParticles) {
		this((EObject) messageType,isCondensed,displayParticles);
	}
	
	
	private MessageTypeTreeNode(EObject obj,  boolean isCondensed,
			boolean displayParticles ) 
	{
		super(obj, isCondensed);
		this.displayParticles = displayParticles;
	}

	/* ITreeNode */

	@Override
	public Object[] getChildren() {
		
		Message msg = getMessage();
		
		if (msg == null) {
			return EMPTY_ARRAY;
		}
		
		if (msg.getParts() == null) {
			return EMPTY_ARRAY;
		}
		
		List<PartTreeNode> list = new ArrayList<PartTreeNode>();
		for (Iterator<Part> it = msg.getParts().values().iterator(); it.hasNext(); ) {
			list.add(new PartTreeNode(it.next(), isCondensed, displayParticles));
		}
		return list.toArray();
	}

	@Override
	public boolean hasChildren() {
		Message msg = getMessage();
		if (msg == null) {
			return false;
		}
		
		return (msg.getParts() != null && !msg.getParts().isEmpty());
	}

	/* other methods */
	
	
	Message getMessage () {
		if (modelObject instanceof Message) {
			return (Message) modelObject;
		}
		if (modelObject instanceof Input) {
			return ((Input)modelObject).getEMessage();
		}
		if (modelObject instanceof Output) {
			return ((Output)modelObject).getEMessage();
		}
		return null;
	}
	
	
}
