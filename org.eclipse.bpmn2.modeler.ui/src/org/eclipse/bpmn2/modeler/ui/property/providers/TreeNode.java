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

import org.eclipse.bpmn2.modeler.core.adapters.AdapterUtil;
import org.eclipse.bpmn2.modeler.ui.adapters.ILabeledElement;
import org.eclipse.swt.graphics.Image;


/**
 * Abstract implementation of ITreeNode which implements default getLabel() and
 * getImage() behaviour by adapting the model object to ILabeledElement.
 * 
 * TODO: condensed trees should be implemented using a ListFlattener rather than
 * the ad-hoc way they are implemented now.  @see ModelTreeContentProvider.
 */
public abstract class TreeNode implements ITreeNode {

	public static final Object[] EMPTY_ARRAY = new Object[0];

	protected Object modelObject;
	protected boolean isCondensed;

	public TreeNode(Object modelObject, boolean isCondensed) {
		this.modelObject = modelObject;
		this.isCondensed = isCondensed;
	}

	/* ITreeNode */

	// May need to override this default getLabel() for some objects!
	public String getLabel() {
		ILabeledElement label = AdapterUtil.adapt(modelObject, ILabeledElement.class);
		if (label == null)  return null;
		return label.getLabel(modelObject);
	}

	public String getLabelSuffix() { return null; }

	public boolean isNodeFlattenable() { return false; }
	public Object getModelObject() { return modelObject; }
	
	public Image getImage() {
		ILabeledElement label = AdapterUtil.adapt(modelObject, ILabeledElement.class);
		if (label == null)  return null;
		return label.getSmallImage(modelObject);		
	}
	
	public abstract Object[] getChildren();
	public abstract boolean hasChildren();
}
