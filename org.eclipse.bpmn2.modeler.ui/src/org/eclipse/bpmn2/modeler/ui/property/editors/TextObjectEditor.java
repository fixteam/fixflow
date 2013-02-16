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

import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.util.ErrorUtils;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * @author Bob Brodt
 *
 */
public class TextObjectEditor extends ObjectEditor {

	protected Text text;
	
	/**
	 * @param parent
	 * @param object
	 * @param feature
	 */
	public TextObjectEditor(AbstractBpmn2PropertiesComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.editors.ObjectEditor#createControl(org.eclipse.swt.widgets.Composite, java.lang.String)
	 */
	@Override
	public Control createControl(Composite composite, String label, int style) {
		createLabel(composite,label);

		boolean multiLine = ((style & SWT.MULTI) != 0);
		if (multiLine)
			style |= SWT.V_SCROLL;

		text = getToolkit().createText(composite, "", style);
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		if (multiLine) {
			data.heightHint = 100;
		}
		text.setLayoutData(data);

		setText(PropertyUtil.getText(object, feature));

		IObservableValue textObserver = SWTObservables.observeText(text, SWT.Modify);
		textObserver.addValueChangeListener(new IValueChangeListener() {

			@SuppressWarnings("restriction")
			@Override
			public void handleValueChange(final ValueChangeEvent e) {
				updateObject(e.diff.getNewValue());
			}
		});
		
		text.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				ErrorUtils.showErrorMessage(null);
			}
		});

		
		return text;
	}
	
	@Override
	public void setObject(EObject object) {
		super.setObject(object);
		updateText();
	}
	
	@Override
	public void setObject(EObject object, EStructuralFeature feature) {
		super.setObject(object, feature);
		updateText();
	}

	@Override
	protected boolean updateObject(Object result) {
		if (super.updateObject(result)) {
			updateText();
			return true;
		}
		// revert the change on error
		text.setText(PropertyUtil.getText(object, feature));
		return false;
	}

	/**
	 * Update the read-only text field with the give value
	 * 
	 * @param value - new value for the text field
	 */
	protected void updateText() {
		int pos = text.getCaretPosition();
		setText(getText());
		text.setSelection(pos, pos);
	}
	
	protected void setText(String value) {
		if (value==null)
			value = "";
		text.setText(value);
	}
	
	/**
	 * Returns the string representation of the given value used for
	 * display in the text field. The default implementation correctly
	 * handles structureRef values (proxy URIs from a DynamicEObject)
	 * and provides reasonable behavior for EObject values.
	 * 
	 * @param value - new object value. If null is passed in, the implementation
	 * should substitute the original value of the EObject's feature.
	 * 
	 * @return string representation of the EObject feature's value.
	 */
	protected String getText() {
		return PropertyUtil.getText(object, feature);
	}
}
