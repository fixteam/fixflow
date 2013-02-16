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

import java.text.MessageFormat;

import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.xsd.XSDSchema;


public class XSDSchemaAdapter extends XSDAbstractAdapter  {

		
	@Override
	public String getLabel(Object obj) {
		XSDSchema schema = (XSDSchema) obj;
		String tns = schema.getTargetNamespace();
		
		if (tns == null) {
			return Messages.XSDSchemaAdapter_0;
		}		
		return MessageFormat.format(Messages.XSDSchemaAdapter_1,
										new Object[] { tns } );
	}

	@Override
	public String getTypeLabel(Object object) {
		return Messages.XSDTypeDefinitionAdapter_XSD_Type_1; 
	}
	
}
