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
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.AbstractEventDefinitionFeatureContainer;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.CreateEventDefinition;
import org.eclipse.bpmn2.modeler.core.features.event.definitions.DecorationAlgorithm;
import org.eclipse.bpmn2.modeler.core.model.Bpmn2ModelerFactory;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil.FillStyle;
import org.eclipse.bpmn2.modeler.ui.ImageProvider;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public class TerminateEventDefinitionFeatureContainer extends AbstractEventDefinitionFeatureContainer {

	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof TerminateEventDefinition;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateTerminateEventDefinition(fp);
	}

	@Override
	protected Shape drawForStart(DecorationAlgorithm algorithm, ContainerShape shape) {
		return null; // NOT ALLOWED ACCORDING TO SPEC
	}

	@Override
	protected Shape drawForEnd(DecorationAlgorithm algorithm, ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Shape terminateShape = Graphiti.getPeService().createShape(shape, false);
		Ellipse ellispe = GraphicsUtil.createEventTerminate(terminateShape);
		StyleUtil.setFillStyle(ellispe, FillStyle.FILL_STYLE_FOREGROUND);
		StyleUtil.applyStyle(ellispe, be);
		return terminateShape;
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
		return null; // NOT ALLOWED ACCORDING TO SPEC
	}

	public static class CreateTerminateEventDefinition extends CreateEventDefinition {

		public CreateTerminateEventDefinition(IFeatureProvider fp) {
			super(fp, "终止定义", "Adds terminate trigger to event");
		}

		@Override
		public boolean canCreate(ICreateContext context) {
			if (!super.canCreate(context)) {
				return false;
			}

			Event e = (Event) getBusinessObjectForPictogramElement(context.getTargetContainer());

//			return e instanceof EndEvent;
			
			if(e instanceof StartEvent) {
				StartEvent startEvent = (StartEvent) e;
				if(startEvent.getEventDefinitions().size()>0) {
					return false;
				}
			}
			else if(e instanceof EndEvent) {
				EndEvent endEvent = (EndEvent) e;
				if(endEvent.getEventDefinitions().size()>0) {
					return false;
				}else {
					return e instanceof EndEvent;
				}
			}
			
			return false;
			
		}

		@Override
		protected EventDefinition createEventDefinition(ICreateContext context) {
			return Bpmn2ModelerFactory.create(TerminateEventDefinition.class);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_TERMINATE;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return TerminateEventDefinition.class;
		}
	}
}