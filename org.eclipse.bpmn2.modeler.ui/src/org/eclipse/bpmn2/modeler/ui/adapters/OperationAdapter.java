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

import org.eclipse.bpmn2.modeler.core.adapters.AbstractAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.IStatefullAdapter;
import org.eclipse.bpmn2.modeler.ui.Activator;
import org.eclipse.bpmn2.modeler.ui.IConstants;
import org.eclipse.bpmn2.modeler.ui.Messages;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.wsdl.Operation;
import org.eclipse.wst.wsdl.WSDLPackage;
import org.w3c.dom.Element;

public class OperationAdapter extends AbstractAdapter 
	implements ILabeledElement, INamedElement, IContentProposal, IStatefullAdapter {
		
	
	/* ILabeledElement */
	
	public Image getSmallImage(Object object) {
		return Activator.getDefault().getImage(IConstants.ICON_OPERATION_16);
	}
	
	public Image getLargeImage(Object object) {
		return null;
	}
	
	public String getTypeLabel(Object object) {
		return Messages.OperationAdapter_Operation_1; 
	}
	public String getLabel(Object object) {
		Operation op = getTarget( object ,Operation.class );
		String name = op.getName();
		if (name != null)  {
			return name;
		}
		return getTypeLabel (op) ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getContent()
	 */
	public String getContent() {
		return getLabel ( getTarget() );		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getCursorPosition()
	 */
	public int getCursorPosition() {
		// TODO Auto-generated method stub
		return -1;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getDescription()
	 */
	public String getDescription() {
		Operation op = getTarget(null, Operation.class );
		Element elm = op.getDocumentationElement();
		return (elm != null ? elm.getNodeValue() : null);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.fieldassist.IContentProposal#getLabel()
	 */

	public String getLabel() {
		return NLS.bind(Messages.OperationAdapter_0, 
				getLabel( getTarget() ),
				getLabel ( getTarget() ) );
	}
	
	/* INamedElement */

	public String getName(Object modelObject) {
		Operation op = getTarget( modelObject ,Operation.class );
		return op.getName();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.adapters.INamedElement#isNameAffected(java.lang.Object, org.eclipse.emf.common.notify.Notification)
	 */
	public boolean isNameAffected (Object modelObject, Notification n) {
		return (n.getFeatureID(Operation.class) == WSDLPackage.OPERATION__NAME);
	}
	

	/* (non-Javadoc)
	 * @see org.eclipse.bpel.ui.adapters.INamedElement#setName(java.lang.Object, java.lang.String)
	 */
	public void setName(Object modelObject, String name) {
		Operation op = getTarget( modelObject ,Operation.class );
		op.setName( name );		
	}
}
