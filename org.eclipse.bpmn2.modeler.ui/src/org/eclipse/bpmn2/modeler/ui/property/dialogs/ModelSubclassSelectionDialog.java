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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.property.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.dialogs.ListDialog;

/**
 * @author Bob Brodt
 *
 */
public class ModelSubclassSelectionDialog extends ListDialog {

	protected EClass listItemClass;
	protected List<EClass> items;

	public ModelSubclassSelectionDialog(BPMN2Editor bpmn2Editor, EObject object, EStructuralFeature feature) {
		this(bpmn2Editor, object, feature, null);
	}

	public ModelSubclassSelectionDialog(BPMN2Editor bpmn2Editor, EObject object, EStructuralFeature feature, EClass listItemClass) {
		super(bpmn2Editor.getSite().getShell());
		
		if (listItemClass==null)
			listItemClass = (EClass)feature.getEType(); // allow a classcast exception
		this.listItemClass = listItemClass;
		
		ModelEnablementDescriptor modelEnablement = bpmn2Editor.getTargetRuntime().getModelEnablements(object);
		items = new ArrayList<EClass>();
		for (EClassifier eclassifier : object.eClass().getEPackage().getEClassifiers() ) {
			if (eclassifier instanceof EClass) {
				EClass eclass = (EClass)eclassifier;
				if (eclass.getESuperTypes().contains(listItemClass)) {
					if (modelEnablement.isEnabled(eclass)) {
						items.add(eclass);
					}
				}
			}
		}
		
		filterList(items);
		
		if (items.size()>1) {
			setContentProvider(new IStructuredContentProvider() {
	
				@Override
				public void dispose() {
				}
	
				@Override
				public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				}
	
				@Override
				public Object[] getElements(Object inputElement) {
					return items.toArray();
				}
				
			});
			setLabelProvider(new ILabelProvider() {
	
				@Override
				public void addListener(ILabelProviderListener listener) {
				}
	
				@Override
				public void dispose() {
				}
	
				@Override
				public boolean isLabelProperty(Object element, String property) {
					return false;
				}
	
				@Override
				public void removeListener(ILabelProviderListener listener) {
				}
	
				@Override
				public Image getImage(Object element) {
					return null;
				}
	
				@Override
				public String getText(Object element) {
					return ModelUtil.toDisplayName( ((EClass)element).getName() );
				}
				
			});
			setTitle("Select a type of "+
					ModelUtil.toDisplayName(listItemClass.getName()));
			setAddCancelButton(true);
			setHelpAvailable(false);
			setInput(new Object());
		}
	}

	// allow override to add/remove items from the superset
	protected void filterList(List<EClass> items) {
	}
	
	@Override
	public int open() {
		if (items.size()<=1) {
			return Window.OK;
		}
		return super.open();
	}

	@Override
	public Object[] getResult() {
		if (items.size()==0) {
			return new Object[] { listItemClass };
		}
		if (items.size()==1) {
			return new Object[] { items.get(0) };
		}
		return super.getResult();
	}

}
