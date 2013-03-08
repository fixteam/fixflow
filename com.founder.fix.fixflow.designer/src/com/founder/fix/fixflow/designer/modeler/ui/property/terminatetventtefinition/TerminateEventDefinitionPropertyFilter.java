package com.founder.fix.fixflow.designer.modeler.ui.property.terminatetventtefinition;



import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class TerminateEventDefinitionPropertyFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);

		
		if(eObject instanceof ThrowEvent)
		{
			ThrowEvent throwEvent=(ThrowEvent)eObject;
			if(throwEvent.getEventDefinitions().size()>0)
			{
				
				for (EventDefinition eventDefinition : throwEvent.getEventDefinitions()) {
					if(eventDefinition instanceof TerminateEventDefinition)
					{
						return false;
					}
				}
				
			}
		}
		
		return false;
	}
}