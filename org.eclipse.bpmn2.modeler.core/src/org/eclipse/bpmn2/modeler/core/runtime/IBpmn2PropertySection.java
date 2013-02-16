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
package org.eclipse.bpmn2.modeler.core.runtime;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public interface IBpmn2PropertySection {
	
	/**
	 * Checks the project preferences for BPMN2 element enablement.
	 * 
	 * @param part - the BPMN2Editor part
	 * @param selection - the currently selected BPMN2 element
	 * @return true if the element is enabled and should be displayed in the property section
	 */
	public boolean appliesTo(IWorkbenchPart part, ISelection selection);

	/**
	 * Asks the Property Section implementation if tab replacement should be
	 * processed for the given editor selection.
	 * 
	 * @param id - ID of the tab to be replaced
	 * @param part - the BPMN2 Editor
	 * @param selection - the selected object in the editor canvas
	 * @return true if the tab replacement should be done.
	 */
	public boolean doReplaceTab(String id, IWorkbenchPart part, ISelection selection);
}
