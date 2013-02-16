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
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
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
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.IColorConstant;

public class TimerEventDefinitionContainer extends AbstractEventDefinitionFeatureContainer {
	
	@Override
	public boolean canApplyTo(Object o) {
		return super.canApplyTo(o) && o instanceof TimerEventDefinition;
	}

	@Override
	public ICreateFeature getCreateFeature(IFeatureProvider fp) {
		return new CreateTimerEventDefinition(fp);
	}

	@Override
	protected Shape drawForStart(DecorationAlgorithm algorithm, ContainerShape shape) {
		return draw(shape);
	}

	@Override
	protected Shape drawForEnd(DecorationAlgorithm algorithm, ContainerShape shape) {
		return draw(shape);
	}

	@Override
	protected Shape drawForThrow(DecorationAlgorithm decorationAlgorithm, ContainerShape shape) {
		return null; // NOT ALLOWED ACCORDING TO SPEC
	}

	@Override
	public Shape drawForCatch(DecorationAlgorithm decorationAlgorithm, ContainerShape shape) {
		return draw(shape);
	}

	@Override
	protected Shape drawForBoundary(DecorationAlgorithm algorithm, ContainerShape shape) {
		return draw(shape);
	}

	private Shape draw(ContainerShape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Shape timerShape = Graphiti.getPeService().createShape(shape, false);
		Image image = GraphicsUtil.createEventImage(timerShape, ImageProvider.IMG_20_TIMER);

		// TODO: can't change foreground color of an Image?
//		Diagram diagram = StyleUtil.findDiagram(image);
//		IGaService gaService = Graphiti.getGaService();
//		image.setForeground(gaService.manageColor(diagram, IColorConstant.GREEN));
//		StyleUtil.setFillStyle(image, FillStyle.FILL_STYLE_NONE);
//		StyleUtil.applyStyle(image, be);
		return timerShape;
	}

	public static class CreateTimerEventDefinition extends CreateEventDefinition {

		public CreateTimerEventDefinition(IFeatureProvider fp) {
			super(fp, "时间定义", "Adds time condition to event");
		}

		@Override
		public boolean canCreate(ICreateContext context) {
			if (!super.canCreate(context)) {
				return false;
			}

			Event e = (Event) getBusinessObjectForPictogramElement(context.getTargetContainer());
			if (e instanceof ThrowEvent) {
				return false;
			}
			
			Event e1 = (Event) getBusinessObjectForPictogramElement(context.getTargetContainer());
			if (e1 instanceof CatchEvent) {
				if(e1 instanceof StartEvent) {
					StartEvent startEvent = (StartEvent) e1;
					if(startEvent.getEventDefinitions().size()>0) {
						return false;
					}
				}
				
				else if(e1 instanceof EndEvent) {
					EndEvent endEvent = (EndEvent) e1;
					if(endEvent.getEventDefinitions().size()>0) {
						return false;
					}
				}
				
				else if(e1 instanceof BoundaryEvent) {
					BoundaryEvent boundaryEvent = (BoundaryEvent) e1;
					if(boundaryEvent.getEventDefinitions().size()>0) {
						return false;
					}
				}
			}

			return true;
		}

		@Override
		protected EventDefinition createEventDefinition(ICreateContext context) {
			return Bpmn2ModelerFactory.create(TimerEventDefinition.class);
		}

		@Override
		protected String getStencilImageId() {
			return ImageProvider.IMG_16_TIMER;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.core.features.AbstractBpmn2CreateFeature#getBusinessObjectClass()
		 */
		@Override
		public Class getBusinessObjectClass() {
			return TimerEventDefinition.class;
		}
	}
}