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
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob Brodt
 *
 */
public class BPMNDIAttributeDefaultComboFieldEditor extends ComboFieldEditor {

	/**
	 * @param name
	 * @param labelText
	 * @param entryNamesAndValues
	 * @param parent
	 */
	public BPMNDIAttributeDefaultComboFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, Bpmn2Preferences.getBPMNDIAttributeDefaultChoicesAndValues(), parent);
	}

}
