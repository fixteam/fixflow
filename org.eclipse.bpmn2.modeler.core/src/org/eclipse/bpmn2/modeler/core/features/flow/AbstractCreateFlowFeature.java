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
package org.eclipse.bpmn2.modeler.core.features.flow;

import java.io.IOException;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateConnectionFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Connection;

public abstract class AbstractCreateFlowFeature<A extends EObject, B extends EObject> extends AbstractBpmn2CreateConnectionFeature {

	public AbstractCreateFlowFeature(IFeatureProvider fp, String name, String description) {
		super(fp, name, description);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		A source = getSourceBo(context);
		B target = getTargetBo(context);
		return source != null && target != null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		try {
			A source = getSourceBo(context);
			B target = getTargetBo(context);
			ModelHandler mh = ModelHandler.getInstance(getDiagram());
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(),
					context.getTargetAnchor());
			BaseElement flow = createFlow(mh, source, target);
//			flow.setId(EcoreUtil.generateUUID());
			addContext.setNewObject(flow);
			Connection connection = (Connection) getFeatureProvider().addIfPossible(addContext);
			ModelUtil.setID(flow);
			return connection;
		} catch (IOException e) {
			Activator.logError(e);
		}
		return null;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		return getSourceBo(context) != null;
	}

	protected abstract String getStencilImageId();

	@Override
	public String getCreateImageId() {
		return getStencilImageId();
	}

	@Override
	public String getCreateLargeImageId() {
		return getStencilImageId();
	}

	protected abstract BaseElement createFlow(ModelHandler mh, A source, B target);

	protected A getSourceBo(ICreateConnectionContext context) {
		if (context.getSourceAnchor() != null) {
			return BusinessObjectUtil.getFirstElementOfType(context.getSourceAnchor().getParent(), getSourceClass());
		}
		return null;
	}

	protected B getTargetBo(ICreateConnectionContext context) {
		if (context.getTargetAnchor() != null) {
			return BusinessObjectUtil.getFirstElementOfType(context.getTargetAnchor().getParent(), getTargetClass());
		}
		return null;
	}

	protected abstract Class<A> getSourceClass();

	protected abstract Class<B> getTargetClass();
}