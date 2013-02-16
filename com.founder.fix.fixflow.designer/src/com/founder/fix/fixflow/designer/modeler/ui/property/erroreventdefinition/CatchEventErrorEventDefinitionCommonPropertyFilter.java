package com.founder.fix.fixflow.designer.modeler.ui.property.erroreventdefinition;


import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class CatchEventErrorEventDefinitionCommonPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);

		
		if(eObject instanceof BoundaryEvent)
		{
			BoundaryEvent startEvent=(BoundaryEvent)eObject;
			if(startEvent.getEventDefinitions().size()>0)
			{
				
				for (EventDefinition eventDefinition : startEvent.getEventDefinitions()) {
					if(eventDefinition instanceof ErrorEventDefinition)
					{
						return true;
					}
				}
				
			}
		}
		
		
		return false;
	}

}
