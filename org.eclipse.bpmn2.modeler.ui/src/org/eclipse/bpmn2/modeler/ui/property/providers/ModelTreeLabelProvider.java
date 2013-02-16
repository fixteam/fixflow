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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;


/**
 * An ILabelProvider for implementors of ITreeNode.  This class is complementary
 * to ModelTreeContentProvider and its subclasses.
 */
public class ModelTreeLabelProvider implements ILabelProvider {

	protected Map stringToImage;

	public static final Object[] EMPTY_ARRAY = new Object[0];

	public ModelTreeLabelProvider() {
		stringToImage = new HashMap();
	}

	/* ILabelProvider */

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO: hook model listener?
	}

	public Image getImage(Object node) {
		if (!(node instanceof ITreeNode)) return null;
		ITreeNode treeElement = (ITreeNode)node;		
		Image image = treeElement.getImage();
		if (image != null)  stringToImage.put(image.toString(), image);	
		return image;		
	}

	public String getText(Object node) {
		if (!(node instanceof ITreeNode)) return null;
		ITreeNode treeElement = (ITreeNode)node;
		String s1 = treeElement.getLabel();
		String s2 = treeElement.getLabelSuffix();
		if (s2 != null){
			return NLS.bind(Messages.ModelTreeLabelProvider_s1_s2, (new String[] {s1, s2})); 
		}
		return s1;
	}

	/* IBaseLabelProvider */

	public void dispose() {
		stringToImage = null;
	}

	public boolean isLabelProperty(Object element, String property) {
		return true;
	}
	
	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}
	
	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}
}
