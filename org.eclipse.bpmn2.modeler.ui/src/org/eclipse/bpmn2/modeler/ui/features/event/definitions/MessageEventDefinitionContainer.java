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
package org.eclipse.bpmn2.modeler.ui.features.event.definitions;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.AbstractEventDefinitionFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.CreateEventDefinition;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.DecorationAlgorithm;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil.Envelope;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil.FillStyle;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;

public class MessageEventDefinitionContainer extends AbstractEventDefinitionFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof MessageEventDefinition;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateMessageEventDefinition(fp);
	}

	@Override
	protected Shape drawForStart(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawEnvleope(algorithm, shape);
	}

	@Override
	protected Shape drawForEnd(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawFilledEnvelope(algorithm, shape);
	}

	@Override
	protected Shape drawForThrow(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawFilledEnvelope(algorithm, shape);
	}

	@Override
	protected Shape drawForCatch(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawEnvleope(algorithm, shape);
	}

	@Override
	protected Shape drawForBoundary(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawEnvleope(algorithm, shape);
	}

	private Shape drawEnvleope(DecorationAlgorithm algorithm, ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Shape envelopeShape = Graphiti.getPeService().createShape(shape, false);
		Envelope env = GraphicsUtil.createEventEnvelope(envelopeShape);
		StyleUtil.setFillStyle(env.rect, FillStyle.FILL_STYLE_BACKGROUND);
		StyleUtil.applyStyle(env.rect, be);
		StyleUtil.setFillStyle(env.line, FillStyle.FILL_STYLE_BACKGROUND);
		StyleUtil.applyStyle(env.line, be);
		return envelopeShape;
	}

	private Shape drawFilledEnvelope(DecorationAlgorithm algorithm, ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Shape envelopeShape = Graphiti.getPeService().createShape(shape, false);
		Envelope env = GraphicsUtil.createEventEnvelope(envelopeShape);
		StyleUtil.setFillStyle(env.rect, FillStyle.FILL_STYLE_INVERT);
		StyleUtil.applyStyle(env.rect, be);
		StyleUtil.setFillStyle(env.line, FillStyle.FILL_STYLE_INVERT);
		StyleUtil.applyStyle(env.line, be);
		return envelopeShape;
	}

	public static class CreateMessageEventDefinition extends CreateEventDefinition {

		public CreateMessageEventDefinition(IFeatureProvider fp) {
			super(fp, "消息定义", "Marks that event expects a message");
		}

		@Override
		protected EventDefinition createEventDefinition(ICreateContext context) {
			return Bpmn2ModelerFactory.create(MessageEventDefinition.class);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_MESSAGE;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return MessageEventDefinition.class;
		}
	}
}
