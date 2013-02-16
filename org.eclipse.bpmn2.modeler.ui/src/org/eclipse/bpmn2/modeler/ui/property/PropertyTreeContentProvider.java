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
package org.eclipse.bpmn2.modeler.ui.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.dd.di.DiagramElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

final class PropertyTreeContentProvider implements ITreeContentProvider {
	/**
	 * 
	 */
	private final AdvancedPropertiesComposite advancedPropertiesComposite;

	/**
	 * @param advancedPropertiesComposite
	 */
	PropertyTreeContentProvider(AdvancedPropertiesComposite improvedAdvancedPropertiesComposite) {
		this.advancedPropertiesComposite = improvedAdvancedPropertiesComposite;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof EObject && !(element instanceof DiagramElement)) {
			List<EObject> ret = getFilteredElements(element);
			return !ret.isEmpty();
		}
		return false;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof EObject && !(inputElement instanceof DiagramElement)) {
			return getFilteredElements(inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof EObject && !(parentElement instanceof DiagramElement)) {
			return getFilteredElements(parentElement).toArray();
		}
		return null;
	}

	private List<EObject> getFilteredElements(Object element) {
		List<EObject> ret = new ArrayList<EObject>();

		for (EObject eObject : ((EObject) element).eContents()) {
			if (!(eObject instanceof DiagramElement) && !(eObject instanceof BPMNDiagram)) {
				ret.add(eObject);
			}
		}
		return ret;
	}
}