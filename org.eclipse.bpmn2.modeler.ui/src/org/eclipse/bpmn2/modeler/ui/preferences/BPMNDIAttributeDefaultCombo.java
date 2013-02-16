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

package org.eclipse.bpmn2.modeler.ui.preferences;

import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences.BPMNDIAttributeDefault;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Bob Brodt
 *
 */

public class BPMNDIAttributeDefaultCombo {
	
	private Label label;
	private Combo combo;
	
	public BPMNDIAttributeDefaultCombo(Composite parent) {
		label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		
		combo = new Combo(parent, SWT.READ_ONLY);
		combo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		String[] values = Bpmn2Preferences.getBPMNDIAttributeDefaultChoices();
		for (String v : values) {
			combo.add(v);
		}
	}
	
	public void setValue(BPMNDIAttributeDefault value) {
		combo.select(value.ordinal());
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public BPMNDIAttributeDefault getValue() {
		int i = combo.getSelectionIndex();
		for (BPMNDIAttributeDefault v : BPMNDIAttributeDefault.values()) {
			if (i==v.ordinal())
				return v;
		}
		return BPMNDIAttributeDefault.USE_DI_VALUE;
	}
}
