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

import org.eclipse.bpmn2.modeler.ui.editor.BPMN2Editor;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

// TODO: this needs to be a FormDialog
public class FeatureEditingDialog extends Dialog {

	protected BPMN2Editor editor;
	protected EObject object;
	protected EStructuralFeature feature;
	protected EObject newObject;
	protected boolean cancel = false;
	
	public FeatureEditingDialog(BPMN2Editor editor, EObject object, EStructuralFeature feature, EObject value) {
		super(editor.getEditorSite().getShell());
		
		setShellStyle(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.MAX | SWT.RESIZE
				| getDefaultOrientation());
		
		this.editor = editor;
		this.object = object;
		this.feature = feature;
		this.newObject = value;
	}

	@Override
	public int open() {
		String title = null;
		if (newObject!=null)
			title = "Edit " + PropertyUtil.getLabel(newObject);
		create();
		if (cancel)
			return Window.CANCEL;
		
		getShell().setSize(500,500);
		if (title==null)
			title = "Create New " + PropertyUtil.getLabel(newObject);
		getShell().setText(title);
		return super.open();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite dialogArea = (Composite) super.createDialogArea(parent);
		
		EClass eclass = (EClass)feature.getEType();
		if (newObject==null) {
			// create the new object
			ModelSubclassSelectionDialog dialog = new ModelSubclassSelectionDialog(editor, object, feature);
			if (dialog.open()==Window.OK){
				eclass = (EClass)dialog.getResult()[0];
				newObject = PropertyUtil.createObject(object, feature, eclass);
			}
			else
				cancel = true;
		}
		AbstractBpmn2PropertiesComposite comp = PropertiesCompositeFactory.createComposite(
				eclass.getInstanceClass(), dialogArea, SWT.NONE);
		comp.setEObject(BPMN2Editor.getActiveEditor(), newObject);
		
		return dialogArea;
	}
	
	@Override
	protected void cancelPressed() {
		super.cancelPressed();
		newObject = null;
	}

	public EObject getNewObject() {
		return newObject;
	}
}