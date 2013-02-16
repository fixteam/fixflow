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

import org.eclipse.swt.graphics.Image;

/**
 * Interface to tree nodes which represent a model object.  The model objects can't
 * represent themselves in the tree because they have to have unique parents, and
 * in general, our model objects don't.  In addition, the model object might want to
 * present different children in different contexts (e.g. variable/property versus
 * variable/part/query).
 */
public interface ITreeNode {

	/**
	 * Returns the regular label for the node (not including a suffix).
	 */
	public String getLabel();

	/**
	 * Returns the suffix which should be appended to the label.  This is useful to
	 * show the label of a condensed node in condensed trees.
	 */
	public String getLabelSuffix();
	
	/**
	 * Returns an image descriptor from which the label provider can create an image
	 * for this node.
	 */
	public Image getImage();

	/**
	 * Returns the underlying model object represented by this node.
	 */
	public Object getModelObject();

	/**
	 * Similar in purpose to the getChildren() method of ITreeContentProvider.
	 */
	public Object[] getChildren();
	
	/**
	 * Similar in purpose to the hasChildren() method of ITreeContentProvider.
	 */
	public boolean hasChildren();
}
