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
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;


/**
 * A label provider for objects which adapt to the ILabeledElement interface.
 * This can be used as a "generic" label provider for details pages, etc.
 */


public class ModelLabelProvider implements ILabelProvider {


	private Object context = null;
	
	/**
	 * Create a new shiny label provider.
	 */
	public ModelLabelProvider () {
		
	}
	
	/**
	 * Create a new shiny label provider with the context object.
	 * @param contextObject 
	 * 
	 */
	public ModelLabelProvider (Object contextObject) {
		context = contextObject;
	}
	
	/**
	 * Default implementation does nothing.
	 * 
	 * @param viewer
	 * @param oldInput
	 * @param newInput
	 */
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO: hook model listener?
	}

	
	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	
	public Image getImage (Object object) {
		
		if (object == null) {
			return null;
		}
		ILabeledElement label = (ILabeledElement) AdapterUtil.adapt(object, ILabeledElement.class, context);
		if (label == null) {
			return null;
		}
		
		Image image = label.getSmallImage(object);
		
		// TODO: The adapters must provide images with a lifecycle outlasting any uses,
		// since there is no protocol for destroying the images returned by adapters.
		// If we add methods for releasing the images, call them here.
		//if (image != null)  stringToImage.put(image.toString(), image);	
		return image;
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	
	public String getText (Object object) {
		if (object == null) {
			return "Messages.ModelLabelProvider____None____1"; 
		}
		ILabeledElement label = (ILabeledElement)AdapterUtil.adapt(object, ILabeledElement.class, context );
		if (label == null)  {
			return "<???>"; //$NON-NLS-1$
		}		
		return label.getLabel(object);
	}

	/** (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	
	public void dispose() {
		
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	public boolean isLabelProperty (Object element, String property) {
		return true;
	}
	
	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		// do nothing
	}
	
	/** (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		// do nothing
	}
}
