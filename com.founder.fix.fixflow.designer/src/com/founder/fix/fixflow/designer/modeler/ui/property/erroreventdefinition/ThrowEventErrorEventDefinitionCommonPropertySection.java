package com.founder.fix.fixflow.designer.modeler.ui.property.erroreventdefinition;

import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.ErrorEventDefinition;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class ThrowEventErrorEventDefinitionCommonPropertySection extends AbstractBpmn2PropertySection{
	
	

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		if(eObject instanceof EndEvent)
		{
			EndEvent startEvent=(EndEvent)eObject;
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

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new ThrowEventErrorEventDefinitionCommonPropertiesComposite(this);
	}
}
