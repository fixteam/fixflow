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
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CancelEventDefinition;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.AbstractEventDefinitionFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.CreateEventDefinition;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.DecorationAlgorithm;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil.FillStyle;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public class CancelEventDefinitionContainer extends AbstractEventDefinitionFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof CancelEventDefinition;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateCancelEventDefinition(fp);
	}

	@Override
	protected Shape drawForStart(DecorationAlgorithm algorithm, ContainerShape shape) {
		return null; // NOT ALLOWED ACCORDING TO SPEC
	}

	@Override
	protected Shape drawForEnd(DecorationAlgorithm algorithm, ContainerShape shape) {
		return drawFilled(algorithm, shape);
	}

	@Override
	protected Shape drawForThrow(DecorationAlgorithm algorithm, ContainerShape shape) {
		return null; // NOT ALLOWED ACCORDING TO SPEC
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
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Shape cancelShape = Graphiti.getPeService().createShape(shape, false);
		Polygon link = GraphicsUtil.createEventCancel(cancelShape);
		StyleUtil.setFillStyle(link, FillStyle.FILL_STYLE_BACKGROUND);
		StyleUtil.applyStyle(link, be);
		return cancelShape;
	}

	private Shape drawFilled(DecorationAlgorithm algorithm, ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Shape cancelShape = Graphiti.getPeService().createShape(shape, false);
		Polygon link = GraphicsUtil.createEventCancel(cancelShape);
		StyleUtil.setFillStyle(link, FillStyle.FILL_STYLE_FOREGROUND);
		StyleUtil.applyStyle(link, be);
		return cancelShape;
	}

	public static class CreateCancelEventDefinition extends CreateEventDefinition {

		public CreateCancelEventDefinition(IFeatureProvider fp) {
			super(fp, "取消定义", "Adds cancel trigger to event");
		}

		@Override
		public boolean canCreate(ICreateContext context) {
			if (!super.canCreate(context)) {
				return false;
			}

			Event e = (Event) getBusinessObjectForPictogramElement(context.getTargetContainer());

			if (e instanceof BoundaryEvent) {
				BoundaryEvent be = (BoundaryEvent) e;
				return be.isCancelActivity();
			}

			if (e instanceof CatchEvent || e instanceof IntermediateThrowEvent) {
				return false;
			}

			return true;
		}

		@Override
		protected EventDefinition createEventDefinition(ICreateContext context) {
			CancelEventDefinition definition = Bpmn2ModelerFactory.create(CancelEventDefinition.class);
			ModelUtil.setID(definition);
			return definition;
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_CANCEL;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return CancelEventDefinition.class;
		}
	}
}
