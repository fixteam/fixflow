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
package org.eclipse.bpmn2.modeler.core.features.flow;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.features.ReconnectBaseElementFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.ReconnectionContext;

public abstract class AbstractReconnectFlowFeature extends ReconnectBaseElementFeature {

	public AbstractReconnectFlowFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	protected abstract Class<? extends EObject> getTargetClass();
	protected abstract Class<? extends EObject> getSourceClass();
	

	@Override
	public boolean canReconnect(IReconnectionContext context) {
		/*if (super.canReconnect(context)) {
			BaseElement targetElement = BusinessObjectUtil.getFirstElementOfType(context.getTargetPictogramElement(), BaseElement.class);
			if (targetElement!=null) {
				if (context.getReconnectType() == ReconnectionContext.RECONNECT_TARGET)
					return getTargetClass().isAssignableFrom(targetElement.getClass());
				return getSourceClass().isAssignableFrom(targetElement.getClass());
			}
		}*/
		return false;
	}
}
