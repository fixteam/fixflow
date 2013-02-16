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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.core.features.event;

import java.util.Iterator;
import java.util.List;

import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature;
import org.eclipse.bpmn2.modeler.core.utils.GraphicsUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

/**
 * @author Bob Brodt
 *
 */

public abstract class AbstractUpdateEventFeature extends AbstractUpdateMarkerFeature<Event> {

	/**
	 * @param fp
	 */
	public AbstractUpdateEventFeature(IFeatureProvider fp) {
		super(fp);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature#isPropertyChanged(org.eclipse.bpmn2.FlowElement, java.lang.String)
	 */
	@Override
	protected boolean isPropertyChanged(Event element, String propertyValue) {
		return !getEventDefinitionsValue(element).equals(propertyValue);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature#doUpdate(org.eclipse.bpmn2.FlowElement, org.eclipse.graphiti.mm.pictograms.ContainerShape)
	 */
	@Override
	protected void doUpdate(Event event, ContainerShape container) {
		List<EventDefinition> eventDefinitions = ModelUtil.getEventDefinitions(event);
		int size = eventDefinitions.size();
		
		GraphicsUtil.deleteEventShape(container);
		if (size!=0) {
			EventDefinition eventDefinition = eventDefinitions.get(0);

			// either find the existing Shape that is linked with an EventDefinition...
			PictogramElement eventDefinitionShape = null;
			Iterator<PictogramElement> iterator = Graphiti.getPeService().getAllContainedPictogramElements(container).iterator();
			while (iterator.hasNext()) {
				PictogramElement pe = iterator.next();
				if (pe.getLink() != null) {
					EList<EObject> objects = pe.getLink().getBusinessObjects();
					if (objects.size()==1 && objects.get(0) instanceof EventDefinition) {
						eventDefinition = (EventDefinition)objects.get(0);
						eventDefinitionShape = pe;
						break;
					}
				}
			}
			
			if (eventDefinitionShape==null) {
				// ...or create a temporary Shape that we can link
				// with the event definition business object... 
				eventDefinitionShape = Graphiti.getPeService().createShape(container, true);
				link(eventDefinitionShape,eventDefinition);
			}
			// ...so we can create an UpdateContext...
			UpdateContext context = new UpdateContext(eventDefinitionShape);
			// ...to look up the EventDefinitionUpdateFeature
			IUpdateFeature upateFeature = getFeatureProvider().getUpdateFeature(context);
			if (upateFeature!=null) {
				// and do the update with the Event object (not the EventDefinition!)
				context = new UpdateContext(container);
				upateFeature.update(context);
			}
		}
		
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.bpmn2.modeler.core.features.activity.AbstractUpdateMarkerFeature#convertPropertyToString(org.eclipse.bpmn2.FlowElement)
	 */
	@Override
	protected String convertPropertyToString(Event element) {
		return getEventDefinitionsValue(element);
	}
	
	public static String getEventDefinitionsValue(Event element) {
		String result = "";
		List<EventDefinition> eventDefinitions = ModelUtil.getEventDefinitions(element);
		for (EventDefinition ed : eventDefinitions) {
			if (!result.isEmpty())
				result += " ";
			result += ed.getId();
		}
		// Parallel Multiple has a different visual than Multiple for Catch Events
		if (element instanceof CatchEvent) {
			if (((CatchEvent)element).isParallelMultiple())
				result += "+";
		}
		return result;
	}
}
