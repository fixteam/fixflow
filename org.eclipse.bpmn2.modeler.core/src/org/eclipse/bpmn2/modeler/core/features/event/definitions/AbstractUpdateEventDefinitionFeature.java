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
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

public abstract class AbstractUpdateEventDefinitionFeature extends AbstractUpdateFeature {

	public AbstractUpdateEventDefinitionFeature(IFeatureProvider fp) {
		super(fp);
	}

	public void draw(Event event, ContainerShape container) {

		List<EventDefinition> eventDefinitions = ModelUtil.getEventDefinitions(event);
		int size = eventDefinitions.size();

		GraphicsUtil.deleteEventShape(container);
		if (size==1) {
			Shape addedShape = getDecorationAlgorithm(event).draw(container);
			link(addedShape, eventDefinitions.get(0));
		}
		else if (size > 1) {
			Shape multipleShape = Graphiti.getPeService().createShape(container, false);
			drawForEvent(event, multipleShape);
			link(multipleShape, eventDefinitions.toArray(new EventDefinition[size]));
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