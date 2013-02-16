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

import org.eclipse.bpmn2.modeler.core.features.activity.task.ICustomTaskFeature;

public class CustomTaskDescriptor extends ModelExtensionDescriptor {

	protected ICustomTaskFeature featureContainer;
	
	public CustomTaskDescriptor(String id, String name) {
		super(id,name);
	}
	
	public ICustomTaskFeature getFeatureContainer() {
		return featureContainer;
	}

	public void setFeatureContainer(ICustomTaskFeature featureContainer) {
		this.featureContainer = featureContainer;
	}
}