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
import org.eclipse.bpmn2.Gateway;
import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.features.MultiUpdateFeature;
import org.eclipse.bpmn2.modeler.core.features.gateway.AbstractCreateGatewayFeature;
import org.eclipse.bpmn2.modeler.core.features.gateway.DefaultAddGatewayFeature;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class EventBasedGatewayFeatureContainer extends AbstractGatewayFeatureContainer {

	static final String INSTANTIATE_PROPERTY = "instantiate";
	static final String EVENT_GATEWAY_TYPE_PROPERTY = "eventGatewayType";

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof EventBasedGateway;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateEventBasedGatewayFeature(fp);
	}

	@Override
	public IAddFeature getAddFeature(IFeatureProvider fp) {
		return new DefaultAddGatewayFeature(fp) {
			@Override
			protected void decorateGateway(ContainerShape container, BPMNShape bpmnShape) {
				Ellipse outer = GraphicsUtil.createGatewayOuterCircle(container);
				Ellipse inner = GraphicsUtil.createGatewayInnerCircle(outer);
				Polygon pentagon = GraphicsUtil.createGatewayPentagon(container);
				pentagon.setFilled(false);
			}

			@Override
			public PictogramElement add(IAddContext context) {
				PictogramElement element = super.add(context);
				IPeService service = Graphiti.getPeService();
				EventBasedGateway gateway = (EventBasedGateway)context.getNewObject();
				service.setPropertyValue(element, INSTANTIATE_PROPERTY, Boolean.toString(gateway.isInstantiate()));
				service.setPropertyValue(element, EVENT_GATEWAY_TYPE_PROPERTY,
						EventBasedGatewayType.EXCLUSIVE.getName());
				return element;
			}
		};
	}

	@Override
	public IUpdateFeature getUpdateFeature(IFeatureProvider fp) {
		MultiUpdateFeature multiUpdate = new MultiUpdateFeature(fp);
		multiUpdate.addUpdateFeature(super.getUpdateFeature(fp));
		multiUpdate.addUpdateFeature(new UpdateEventBasedGatewayFeature(fp));
		return multiUpdate;
	}

	public static class CreateEventBasedGatewayFeature extends AbstractCreateGatewayFeature {

		public CreateEventBasedGatewayFeature(IFeatureProvider fp) {
			super(fp, "事件网关", "Represents a branching point in the process");
		}

		@Override
		protected Gateway createFlowElement(ICreateContext context) {
			EventBasedGateway gateway = Bpmn2ModelerFactory.create(EventBasedGateway.class);
			gateway.setInstantiate(false);
			gateway.setEventGatewayType(EventBasedGatewayType.EXCLUSIVE);
			return gateway;
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_EVENT_BASED_GATEWAY;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractCreateFlowElementFeature#getFlowElementClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return EventBasedGateway.class;
		}
	}
}