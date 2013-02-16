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

package org.eclipse.bpmn2.modeler.ui.property.editors;

import org.eclipse.bpmn2.modeler.core.adapters.AdapterUtil;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.util.ErrorUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Base class for EObject feature editors. All subclasses must render the given object's feature,
 * which may be either an attribute, a reference to an EObject, or a list of EObject references.
 * Subclasses must also provide means to populate the display widgets from the feature and save
 * modifications to the feature made in the display widget.
 * 
 * @author Bob Brodt
 */
public abstract class ObjectEditor {
	protected EObject object;
	protected EStructuralFeature feature;
	protected AbstractBpmn2PropertiesComposite parent;
	
	public ObjectEditor(AbstractBpmn2PropertiesComposite parent, EObject object, EStructuralFeature feature) {
		this.parent = parent;
		this.object = object;
		this.feature = feature;
	}
	
	public abstract Control createControl(Composite composite, String label, int style);
	
	public Control createControl(Composite composite, String label) {
		return createControl(composite,label,SWT.NONE);
	}
	
	public Control createControl(String label) {
		return createControl(parent,label,SWT.NONE);
	}
	
	public void setObject(EObject object) {
		this.object = object;
	}
	
	public void setObject(EObject object, EStructuralFeature feature) {
		this.object = object;
		this.feature = feature;
	}
	
	protected FormToolkit getToolkit() {
		return parent.getToolkit();
	}
	
	protected BPMN2Editor getDiagramEditor() {
		return parent.getDiagramEditor();
	}

	protected Diagram getDiagram() {
		return getDiagramEditor().getDiagramTypeProvider().getDiagram();
	}
	
	protected Label createLabel(Composite parent, String name) {
		Label label = getToolkit().createLabel(parent, name);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		return label;
	}

	protected boolean updateObject(final Object result) {
		ExtendedPropertiesAdapter adapter = AdapterUtil.adapt(object, ExtendedPropertiesAdapter.class);
		Object oldValue = adapter==null ? object.eGet(feature) : adapter.getFeatureDescriptor(feature).getValue();
		boolean valueChanged = (result != oldValue);
		if (result!=null && oldValue!=null)
			valueChanged = !result.equals(oldValue);
		
		if (valueChanged) {
			InsertionAdapter insertionAdapter = AdapterUtil.adapt(object, InsertionAdapter.class);
			if (insertionAdapter!=null) {
				// make sure the new object is added to its container first
				// so that it inherits the container's Resource and EditingDomain
				// before we try to change its value.
				insertionAdapter.execute();
			}
			
			if (isEmpty(result)){
				TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						object.eUnset(feature);
					}
				});
			}
			else if (adapter!=null) { 			// use the Extended Properties adapter if there is one
				adapter.getFeatureDescriptor(feature).setValue(result);
			}
			else {
				// fallback is to set the new value here using good ol' EObject.eSet()
				TransactionalEditingDomain domain = getDiagramEditor().getEditingDomain();
				domain.getCommandStack().execute(new RecordingCommand(domain) {
					@Override
					protected void doExecute() {
						object.eSet(feature, result);
					}
				});
			}
			if (getDiagramEditor().getDiagnostics()!=null) {
				ErrorUtils.showErrorMessage(getDiagramEditor().getDiagnostics().getMessage());
				return false;
			}
			else
				ErrorUtils.showErrorMessage(null);
		}
		
		return true;
	}

	private boolean isEmpty(Object result) {
		if (result == null){
			return true;
		}
		
		if (result instanceof String){
			return ((String) result).isEmpty();
		}
		
		return false;
	}
	
}
