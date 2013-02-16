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

import java.math.BigInteger;

import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.util.ErrorUtils;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * @author Bob Brodt
 *
 */
public class IntObjectEditor extends ObjectEditor {

	/**
	 * @param parent
	 * @param object
	 * @param feature
	 */
	public IntObjectEditor(AbstractBpmn2PropertiesComposite parent, EObject object, EStructuralFeature feature) {
		super(parent, object, feature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.ui.property.editors.ObjectEditor#createControl(org.eclipse.swt.widgets.Composite, java.lang.String)
	 */
	@Override
	public Control createControl(Composite composite, String label, int style) {
		createLabel(composite,label);

		final Text text = getToolkit().createText(composite, "");
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		text.addVerifyListener(new VerifyListener() {

			/**
			 * taken from
			 * http://dev.eclipse.org/viewcvs/viewvc.cgi/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets
			 * /Snippet19.java?view=co
			 */
			@Override
			public void verifyText(VerifyEvent e) {
				String string = e.text;
				char[] chars = new char[string.length()];
				string.getChars(0, chars.length, chars, 0);
				for (int i = 0; i < chars.length; i++) {
					if (!('0' <= chars[i] && chars[i] <= '9')) {
						e.doit = false;
						return;
					}
				}
			}
		});

		Object eGet = object.eGet(feature);
		if (eGet != null) {
			text.setText(eGet.toString());
		}

		IObservableValue textObserveTextObserveWidget = SWTObservables.observeText(text, SWT.Modify);
		textObserveTextObserveWidget.addValueChangeListener(new IValueChangeListener() {
			@Override
			public void handleValueChange(ValueChangeEvent event) {

				try {
					final int i = Integer.parseInt(text.getText());
					if (!object.eGet(feature).equals(i)) {
						setFeatureValue(i);
					}
				} catch (NumberFormatException e) {
					text.setText((String) object.eGet(feature));
					Activator.logError(e);
				}
			}

			@SuppressWarnings("restriction")
			private void setFeatureValue(final int i) {
				RecordingCommand command = new RecordingCommand(getDiagramEditor().getEditingDomain()) {
					@Override
					protected void doExecute() {
						Class eTypeClass = feature.getEType().getInstanceClass();
						if (BigInteger.class.equals(eTypeClass)) {
							object.eSet(feature, BigInteger.valueOf((long)i));
						}
						else
							object.eSet(feature, i);
					}
				};
				getDiagramEditor().getEditingDomain().getCommandStack().execute(command);
				if (getDiagramEditor().getDiagnostics()!=null) {
					// revert the change and display error status message.
					text.setText((String) object.eGet(feature));
					ErrorUtils.showErrorMessage(getDiagramEditor().getDiagnostics().getMessage());
				}
				else
					ErrorUtils.showErrorMessage(null);
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

}
