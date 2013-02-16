/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.adapters;

import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.swt.graphics.Image;


public class XSDTypeDefinitionAdapter extends XSDAbstractAdapter  {

	/* ILabeledElement overrides */
	
	@Override
	public Image getSmallImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_XSD_SIMPLE_TYPE_DEFINITION_16);
	}
	
	@Override
	public String getTypeLabel(Object object) {
		return Messages.XSDTypeDefinitionAdapter_XSD_Type_1; 
	}
	
}
