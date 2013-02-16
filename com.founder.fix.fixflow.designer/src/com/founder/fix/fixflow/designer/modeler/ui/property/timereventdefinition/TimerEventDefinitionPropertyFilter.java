package com.founder.fix.fixflow.designer.modeler.ui.property.timereventdefinition;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class TimerEventDefinitionPropertyFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);

		return false;
		/*
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
		*/
	}
}