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
package org.eclipse.bpmn2.modeler.ui.adapters;

import org.eclipse.swt.graphics.Image;

/**
 * An ILabeledElement provides UI facilities for labeling the parameter.
 * This includes both images and a string label.
 */
public interface ILabeledElement {
	
	/**
	 * Return a string labelling the generic type of the model object (e.g. "Sequence")
	 */
	public String getTypeLabel(Object object);
	
	/**
	 * Return a string labelling the given model object.  If no label is available, it
	 * should return the same value as getTypeLabel().
	 */
	public String getLabel(Object object);
	
	/**
	 * Return a small image representing the given model object
	 */
	public Image getSmallImage(Object object);
	
	/**
	 * Return a large image representing the given model object
	 */
	public Image getLargeImage(Object object);	
}
