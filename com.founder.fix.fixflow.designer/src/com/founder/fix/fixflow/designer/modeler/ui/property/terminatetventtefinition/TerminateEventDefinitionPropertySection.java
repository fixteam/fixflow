package com.founder.fix.fixflow.designer.modeler.ui.property.terminatetventtefinition;


import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.TerminateEventDefinition;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.ui.IWorkbenchPart;



public class TerminateEventDefinitionPropertySection extends AbstractBpmn2PropertySection {
	


	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);

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

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new TerminateEventDefinitionPropertiesComposite(this);
	}
	

}
