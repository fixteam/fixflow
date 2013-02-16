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

package org.eclipse.bpmn2.modeler.core.features;

import java.util.List;

import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;

/**
 * @author Bob Brodt
 *
 */
public abstract class AbstractBpmn2CreateFeature extends AbstractCreateFeature {

	/**
	 * @param fp
	 * @param name
	 * @param description
	 */
	public AbstractBpmn2CreateFeature(IFeatureProvider fp, String name,
			String description) {
		super(fp, name, description);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreate#canCreate(org.eclipse.graphiti.features.context.ICreateContext)
	 */
	@Override
	public boolean canCreate(ICreateContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreate#create(org.eclipse.graphiti.features.context.ICreateContext)
	 */
	@Override
	public Object[] create(ICreateContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAvailable(IContext context) {
		List<ModelEnablementDescriptor> enablements = TargetRuntime.getCurrentRuntime().getModelEnablements();
		for (ModelEnablementDescriptor e : enablements) {
			String n = getBusinessObjectClass().getSimpleName();
			if (e.isEnabled(n))
				return true;
		}
		return false;
	}

	@Override
	public String getCreateDescription() {
		return "Create " + ModelUtil.toDisplayName( getBusinessObjectClass().getSimpleName());
	}

	/**
	 * @return
	 */
	public abstract Class getBusinessObjectClass();
}
