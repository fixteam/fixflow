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

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.modeler.core.runtime.ModelEnablementDescriptor;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;

/**
 * @author Bob Brodt
 *
 */
public abstract class AbstractBpmn2CreateConnectionFeature extends
		AbstractCreateConnectionFeature {

	/**
	 * @param fp
	 * @param name
	 * @param description
	 */
	public AbstractBpmn2CreateConnectionFeature(IFeatureProvider fp,
			String name, String description) {
		super(fp, name, description);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreateConnection#canCreate(org.eclipse.graphiti.features.context.ICreateConnectionContext)
	 */
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreateConnection#create(org.eclipse.graphiti.features.context.ICreateConnectionContext)
	 */
	@Override
	public Connection create(ICreateConnectionContext context) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.func.ICreateConnection#canStartConnection(org.eclipse.graphiti.features.context.ICreateConnectionContext)
	 */
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		return false;
	}

	@Override
	public boolean isAvailable(IContext context) {
		Object o = null;
		if (context instanceof ICreateConnectionContext) {
			ICreateConnectionContext ccc = (ICreateConnectionContext)context;
			if (ccc.getTargetPictogramElement()!=null) {
				o = BusinessObjectUtil.getFirstElementOfType(
						ccc.getTargetPictogramElement(), BaseElement.class);
			}
			else if (ccc.getSourcePictogramElement()!=null) {
				o = BusinessObjectUtil.getFirstElementOfType(
						ccc.getSourcePictogramElement(), BaseElement.class);
			}
		}
		else if (context instanceof IReconnectionContext) {
			IReconnectionContext rc = (IReconnectionContext)context;
			if (rc.getTargetPictogramElement()!=null) {
				o = BusinessObjectUtil.getFirstElementOfType(
						rc.getTargetPictogramElement(), BaseElement.class);
			}
		}
		
		if (o instanceof EndEvent)
			return false;
		
		if (o instanceof EObject) {
			String n = getBusinessObjectClass().getSimpleName();
			ModelEnablementDescriptor e = TargetRuntime.getCurrentRuntime().getModelEnablements((EObject)o);
			return e.isEnabled(n);
		}
		return false;
	}

	/**
	 * @return
	 */
	public abstract Class getBusinessObjectClass();
}
