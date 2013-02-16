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
import org.eclipse.bpmn2.EscalationEventDefinition;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.AbstractEventDefinitionFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.CreateEventDefinition;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.DecorationAlgorithm;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil.FillStyle;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public class EscalationEventDefinitionContainer extends AbstractEventDefinitionFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof EscalationEventDefinition;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateEscalationEventDefinition(fp);
	}

	@Override
	protected Shape drawForStart(DecorationAlgorithm algorithm, ContainerShape shape) {
		return draw(algorithm, shape);
	}

	@Override
	protected Shape drawForEnd(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawFilled(algorithm, shape);
	}

	@Override
	protected Shape drawForThrow(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawFilled(algorithm, shape);
	}

	@Override
	protected Shape drawForCatch(DecorationAlgorithm algorithm, ContainerShape shape) {
		return null; // NOT ALLOWED ACCORDING TO SPEC
	}

	@Override
	protected Shape drawForBoundary(DecorationAlgorithm algorithm, ContainerShape shape) {
		return draw(algorithm, shape);
	}

	private Shape draw(DecorationAlgorithm algorithm, ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstBaseElement(shape);
		Shape escalationShape = Graphiti.getPeService().createShape(shape, false);
		Polygon escalation = GraphicsUtil.createEventEscalation(escalationShape);
		StyleUtil.setFillStyle(escalation, FillStyle.FILL_STYLE_BACKGROUND);
		StyleUtil.applyStyle(escalation, be);
		return escalationShape;
	}

	private Shape drawFilled(DecorationAlgorithm algorithm, ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstBaseElement(shape);
		Shape escalationShape = Graphiti.getPeService().createShape(shape, false);
		Polygon escalation = GraphicsUtil.createEventEscalation(escalationShape);
		StyleUtil.setFillStyle(escalation, FillStyle.FILL_STYLE_FOREGROUND);
		StyleUtil.applyStyle(escalation, be);
		return escalationShape;
	}

	public static class CreateEscalationEventDefinition extends CreateEventDefinition {

		public CreateEscalationEventDefinition(IFeatureProvider fp) {
			super(fp, "升级定义", "Adds escalation trigger to event");
		}

		@Override
		public boolean canCreate(ICreateContext context) {
			if (!super.canCreate(context)) {
				return false;
			}

			Event e = (Event) getBusinessObjectForPictogramElement(context.getTargetContainer());

			if (e instanceof StartEvent) {
				EObject container = context.getTargetContainer().eContainer();
				if (container instanceof Shape) {
					Object o = getBusinessObjectForPictogramElement((Shape) container);
					return o != null && o instanceof SubProcess;
				}

				return false;
			}

			if (e instanceof IntermediateCatchEvent) {
				return false;
			}

			return true;
		}

		@Override
		protected EventDefinition createEventDefinition(ICreateContext context) {
			return Bpmn2ModelerFactory.create(EscalationEventDefinition.class);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_ESCAlATION;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return EscalationEventDefinition.class;
		}
	}
}