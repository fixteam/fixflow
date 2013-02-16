package com.founder.fix.fixflow.designer.modeler.ui.property.timereventdefinition;


import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class StartEventTimerEventDefinitionPropertyFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);

		
		if(eObject instanceof StartEvent)
		{
			StartEvent startEvent=(StartEvent)eObject;
			if(startEvent.getEventDefinitions().size()>0)
			{
				
				for (EventDefinition eventDefinition : startEvent.getEventDefinitions()) {
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