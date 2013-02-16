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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.features.gateway;

import org.eclipse.bpmn2.EventBasedGateway;
import org.eclipse.bpmn2.EventBasedGatewayType;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class UpdateEventBasedGatewayFeature extends AbstractUpdateFeature {

	public UpdateEventBasedGatewayFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object o = getBusinessObjectForPictogramElement(context.getPictogramElement());
		return o != null && o instanceof EventBasedGateway;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		IPeService service = Graphiti.getPeService();

		boolean instantiate = Boolean.parseBoolean(service.getPropertyValue(context.getPictogramElement(),
		        EventBasedGatewayFeatureContainer.INSTANTIATE_PROPERTY));
		EventBasedGatewayType gatewayType = EventBasedGatewayType.getByName(service.getPropertyValue(
		        context.getPictogramElement(), EventBasedGatewayFeatureContainer.EVENT_GATEWAY_TYPE_PROPERTY));

		EventBasedGateway gateway = (EventBasedGateway) getBusinessObjectForPictogramElement(context
		        .getPictogramElement());

		boolean changed = instantiate != gateway.isInstantiate() || gatewayType != gateway.getEventGatewayType();
		return changed ? Reason.createTrueReason() : Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		IPeService service = Graphiti.getPeService();

		EventBasedGateway gateway = (EventBasedGateway) getBusinessObjectForPictogramElement(context
		        .getPictogramElement());

		clearGateway(context.getPictogramElement());

		if (gateway.isInstantiate()) {
			if (gateway.getEventGatewayType() == EventBasedGatewayType.PARALLEL) {
				drawParallelMultipleEventBased((ContainerShape) context.getPictogramElement());
			} else {
				drawExclusiveEventBased((ContainerShape) context.getPictogramElement());
			}
		} else {
			drawEventBased((ContainerShape) context.getPictogramElement());
		}

		service.setPropertyValue(context.getPictogramElement(), EventBasedGatewayFeatureContainer.INSTANTIATE_PROPERTY,
		        Boolean.toString(gateway.isInstantiate()));
		service.setPropertyValue(context.getPictogramElement(),
		        EventBasedGatewayFeatureContainer.EVENT_GATEWAY_TYPE_PROPERTY, gateway.getEventGatewayType().getName());
		return true;
	}

	private void clearGateway(PictogramElement element) {
		GraphicsUtil.clearGateway(element);
	}

	private void drawEventBased(ContainerShape container) {
		Ellipse outer = GraphicsUtil.createGatewayOuterCircle(container);
		Ellipse inner = GraphicsUtil.createGatewayInnerCircle(outer);
		Polygon pentagon = GraphicsUtil.createGatewayPentagon(container);
		pentagon.setFilled(false);
	}

	private void drawExclusiveEventBased(ContainerShape container) {
		Ellipse ellipse = GraphicsUtil.createGatewayOuterCircle(container);
		Polygon pentagon = GraphicsUtil.createGatewayPentagon(container);
		pentagon.setFilled(false);
	}

	private void drawParallelMultipleEventBased(ContainerShape container) {
		Ellipse ellipse = GraphicsUtil.createGatewayOuterCircle(container);
		Polygon cross = GraphicsUtil.createEventGatewayParallelCross(container);
	}
}