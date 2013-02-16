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
package org.eclipse.bpmn2.modeler.ui.property.providers;

import java.util.List;

import org.eclipse.wst.wsdl.Service;

/**
 * Content provider for Ports.
 * 
 * Expects a Service as input.
 */
public class PortContentProvider extends AbstractContentProvider  {

	@Override
	public void collectElements(Object input, List list)  {
		if (input instanceof Service) {
			Service service = (Service) input;
			list.addAll ( service.getEPorts() );			
		} 
		throw new IllegalArgumentException();
	}
}
