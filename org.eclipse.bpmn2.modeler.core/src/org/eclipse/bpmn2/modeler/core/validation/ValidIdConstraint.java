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
package org.eclipse.bpmn2.modeler.core.validation;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

public class ValidIdConstraint extends AbstractModelConstraint {

	@Override
	public IStatus validate(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		EMFEventType eType = ctx.getEventType();
		
		// In the case of batch mode.
		if (eType == EMFEventType.NULL) {
			String id = null;
			if (eObj instanceof BaseElement) {
				id = ((BaseElement)eObj).getId(); 
			}
			
			if (id == null || id.length() == 0 || id.indexOf(' ')>=0) {
				return ctx.createFailureStatus(new Object[] {eObj.eClass().getName()});
			}
		// In the case of live mode.
		} else {
			Object newValue = ctx.getFeatureNewValue();
			
			if (newValue == null || ((String)newValue).length() == 0 || ((String)newValue).indexOf(' ')>=0) {
				return ctx.createFailureStatus(new Object[] {eObj.eClass().getName()});
			}
		}
		
		return ctx.createSuccessStatus();
	}

}
