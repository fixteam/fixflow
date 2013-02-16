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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.ILiveValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

public class LiveValidationContentAdapter extends EContentAdapter {
	private ILiveValidator validator = null;

	public LiveValidationContentAdapter() {
	}

	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);
		
		if (validator == null) {
			validator = (ILiveValidator)ModelValidationService.getInstance().newValidator(EvaluationMode.LIVE);
		}
		
		IStatus status = validator.validate(notification);
		
		if (!status.isOK()) {
			if (status.isMultiStatus()) {
				status = status.getChildren()[0];
			}
		}
	}

}
