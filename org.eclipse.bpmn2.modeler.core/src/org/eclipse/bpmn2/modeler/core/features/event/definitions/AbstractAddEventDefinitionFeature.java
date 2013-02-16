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
package org.eclipse.bpmn2.modeler.core.features.event.definitions;

import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil;
import org.eclipse.bpmn2.modeler.core.utils.StyleUtil.FillStyle;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public abstract class AbstractAddEventDefinitionFeature extends AbstractAddShapeFeature {


	public AbstractAddEventDefinitionFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer());
		return bo != null && bo instanceof Event;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ContainerShape container = context.getTargetContainer();
		Event event = (Event) getBusinessObjectForPictogramElement(container);

		draw(event, (EventDefinition)context.getNewObject(), container);
		return null;
	}
	
	public void draw(Event event, EventDefinition eventDef, ContainerShape container) {

		List<EventDefinition> eventDefinitions = ModelUtil.getEventDefinitions(event);
		int size = eventDefinitions.size();

		GraphicsUtil.deleteEventShape(container);
		if (size > 1) {
			Shape multipleShape = Graphiti.getPeService().createShape(container, false);
			drawForEvent(event, multipleShape);
			link(multipleShape, eventDefinitions.toArray(new EventDefinition[size]));
		} else {
			Shape addedShape = getDecorationAlgorithm(event).draw(container);
			link(addedShape, eventDef);
		}
	}

	public abstract DecorationAlgorithm getDecorationAlgorithm(Event event);

	private void drawForEvent(Event event, Shape shape) {
		if(event instanceof CatchEvent && ((CatchEvent) event).isParallelMultiple()) {
			drawParallelMultiple(event, shape);
		} else {
			drawMultiple(event, shape);
		}
	}
	
	private void drawMultiple(Event event, Shape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Polygon pentagon = GraphicsUtil.createEventPentagon(shape);
		if (event instanceof ThrowEvent) {
			StyleUtil.setFillStyle(pentagon, FillStyle.FILL_STYLE_FOREGROUND);
		} else {
			StyleUtil.setFillStyle(pentagon, FillStyle.FILL_STYLE_BACKGROUND);
		}
		StyleUtil.applyStyle(pentagon, be);
	}
	
	private void drawParallelMultiple(Event event, Shape shape) {
		BaseElement be = BusinessObjectUtil.getFirstElementOfType(shape, BaseElement.class, true);
		Polygon cross = GraphicsUtil.createEventParallelMultiple(shape);
		StyleUtil.setFillStyle(cross, FillStyle.FILL_STYLE_BACKGROUND);
		StyleUtil.applyStyle(cross, be);
	}
}