/******************************************************************************* 
 * Copyright (c) 2011 Red Hat, Inc. 
 *  All rights reserved. 
 * This program is made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, 
 * and is available at http://www.eclipse.org/legal/epl-v10.html 
 * 
 * Contributors: 
 * Red Hat, Inc. - initial API and implementation 
 *
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.views;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.FlowElement;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;

class TreeObject implements IAdaptable {
	private String name;
	private TreeParent parent;
	private BaseElement f;

	public TreeObject(final String name) {
		this.name = name;
	}

	public TreeObject(final BaseElement f) {
		this.f = f;
		if (f instanceof FlowElement) {
			FlowElement flowElem = (FlowElement) f;
			name = flowElem.getName() == null ? "" : flowElem.getName();
			name += " (" + f.eClass().getName() + ")";
		} else {
			name = f.eClass().getName();
		}
	}

	public String getName() {
		return name;
	}

	public void setParent(final TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return getName();
	}

	public Object getAdapter(final Class key) {
		if (key.equals(EObject.class)) {
			return getBaseElement();
		}
		return null;
	}

	public void setBaseElement(final BaseElement f) {
		this.f = f;
	}

	public BaseElement getBaseElement() {
		return f;
	}
}
