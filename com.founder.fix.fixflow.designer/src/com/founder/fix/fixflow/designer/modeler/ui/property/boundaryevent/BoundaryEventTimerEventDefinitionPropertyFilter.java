package com.founder.fix.fixflow.designer.modeler.ui.property.boundaryevent;


import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class BoundaryEventTimerEventDefinitionPropertyFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);

		
		if(eObject instanceof BoundaryEvent)
		{
			BoundaryEvent boundaryEvent=(BoundaryEvent)eObject;
			if(boundaryEvent.getEventDefinitions().size()>0)
			{
				
				for (EventDefinition eventDefinition : boundaryEvent.getEventDefinitions()) {
					if(eventDefinition instanceof TimerEventDefinition)
					{
						return true;
					}
				}
				
			}
		}
		
		return false;
	}
}