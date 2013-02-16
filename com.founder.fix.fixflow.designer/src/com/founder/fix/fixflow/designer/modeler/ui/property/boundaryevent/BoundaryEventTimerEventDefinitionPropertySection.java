package com.founder.fix.fixflow.designer.modeler.ui.property.boundaryevent;


import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.TimerEventDefinition;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class BoundaryEventTimerEventDefinitionPropertySection extends AbstractBpmn2PropertySection {
	
	

	
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
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

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new BoundaryEventTimerEventDefinitionPropertiesComposite(this);
	}

}
