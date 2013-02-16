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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.emf.ecore.EPackage;

public class BaseRuntimeDescriptor {
	
	protected TargetRuntime targetRuntime;
	
	public BaseRuntimeDescriptor() {
	}

	public BaseRuntimeDescriptor(TargetRuntime rt) {
		targetRuntime = rt;
	}
	
	public TargetRuntime getRuntime() {
		return targetRuntime;
	}

	public void setRuntime(TargetRuntime targetRuntime) {
		this.targetRuntime = targetRuntime;
	}
	
	EPackage getEPackage() {
		if (targetRuntime.getModelDescriptor()!=null)
			return targetRuntime.getModelDescriptor().getEPackage();
		return Bpmn2Package.eINSTANCE;
	}
}