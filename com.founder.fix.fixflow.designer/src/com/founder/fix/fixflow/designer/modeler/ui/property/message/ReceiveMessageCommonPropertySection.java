package com.founder.fix.fixflow.designer.modeler.ui.property.message;


import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.EventDefinition;
import org.eclipse.bpmn2.MessageEventDefinition;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class ReceiveMessageCommonPropertySection extends AbstractBpmn2PropertySection{
	
	
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		
		
		return false;
		
		/*EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		
		if(eObject instanceof CatchEvent)
		{
			CatchEvent catchEvent=(CatchEvent)eObject;
			if(catchEvent.getEventDefinitions().size()>0)
			{
				
				for (EventDefinition eventDefinition : catchEvent.getEventDefinitions()) {
					if(eventDefinition instanceof MessageEventDefinition)
					{
						return true;
					}
				}
				
			}
		}else if(eObject instanceof ReceiveTask) {
			return true;
		}
		
		return false;*/
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		
		return new ReceiveMessageCommonPropertiesComposite(this);
	}

}
