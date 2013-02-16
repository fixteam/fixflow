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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core.features;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;

public abstract class BaseElementConnectionFeatureContainer extends ConnectionFeatureContainer {

	@Override
	public Object getApplyObject(IContext context) {
		if (context instanceof IAddContext) {
			return ((IAddContext) context).getNewObject();
		} else if (context instanceof IPictogramElementContext) {
			return BusinessObjectUtil.getFirstElementOfType(
					(((IPictogramElementContext) context).getPictogramElement()), BaseElement.class);
		} else if (context instanceof IReconnectionContext) {
			IReconnectionContext rc = (IReconnectionContext)context;
			return BusinessObjectUtil.getFirstElementOfType(rc.getConnection(), BaseElement.class);
		}
		return null;
	}

	@Override
	public boolean canApplyTo(Object o) {
		return o instanceof BaseElement;
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(IFeatureProvider fp) {
		return new ReconnectBaseElementFeature(fp);
	}

	@Override
	public IRemoveFeature getRemoveFeature(IFeatureProvider fp) {
		return null;
	}
}