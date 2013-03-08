package com.founder.fix.fixflow.designer.modeler.ui.property.message;


import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class SendMessageCommonPropertySection extends AbstractBpmn2PropertySection{
	
	
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		
		
		return false;
		/*
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);

		if(eObject instanceof ThrowEvent)
		{
			ThrowEvent throwEvent=(ThrowEvent)eObject;
			if(throwEvent.getEventDefinitions().size()>0)
			{
				
				for (EventDefinition eventDefinition : throwEvent.getEventDefinitions()) {
					if(eventDefinition instanceof MessageEventDefinition)
					{
						return true;
					}
				}
				
			}
		}else if(eObject instanceof SendTask) {
			return true;
		}
		
		return false;
		*/
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new SendMessageCommonPropertiesComposite(this);
	}

}
