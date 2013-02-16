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

import javax.xml.namespace.QName;

import org.eclipse.bpmn2.modeler.core.adapters.AbstractAdapter;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.wsdl.PortType;
import org.eclipse.wst.wsdl.WSDLPackage;

public class PortTypeAdapter extends AbstractAdapter implements INamedElement,
	ILabeledElement
{

	/* INamedElement */

	public String getName(Object namedElement) {
		QName name = ((PortType)namedElement).getQName();
		return (name != null)? name.getLocalPart() : null;
	}
	
	public void setName(Object namedElement, String name) {
		PortType portType = (PortType)namedElement;
		String ns = ""; //$NON-NLS-1$
		if (portType.getEnclosingDefinition() != null) {
			ns = portType.getEnclosingDefinition().getTargetNamespace();
		}
		((PortType)namedElement).setQName(new QName(ns, name));
	}
	
	public boolean isNameAffected(Object modelObject, Notification n) {
		return (n.getFeatureID(PortType.class) == WSDLPackage.PORT_TYPE__QNAME);
	}
	
	/* ILabeledElement */
	
	public Image getSmallImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_PORTTYPE_16);
	}
	
	public Image getLargeImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_PORTTYPE_32);
	}

	public String getTypeLabel(Object object) {
		return Messages.PortTypeAdapter_Port_Type_2; 
	}
	
	public String getLabel(Object object) {
		PortType pt = (PortType)object;
		if (pt.getQName() != null) {
			if (pt.getQName().getLocalPart() != null)  return pt.getQName().getLocalPart();
		}
		return getTypeLabel(object);
	}
}
