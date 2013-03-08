package com.founder.fix.fixflow.designer.modeler.ui.property.task;


import org.eclipse.bpmn2.BusinessRuleTask;
import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.ReceiveTask;
import org.eclipse.bpmn2.ScriptTask;
import org.eclipse.bpmn2.SendTask;
import org.eclipse.bpmn2.ServiceTask;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.UserTask;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class TaskCommonPropertySection extends AbstractBpmn2PropertySection{
	
	

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		boolean enabled = ((eObject instanceof Task) && !(eObject instanceof UserTask) && !(eObject instanceof ScriptTask) && !(eObject instanceof ServiceTask) && !(eObject instanceof SendTask) && !(eObject instanceof ReceiveTask) && !(eObject instanceof ManualTask) && !(eObject instanceof BusinessRuleTask) && !(eObject instanceof ChoreographyTask)) ;
		return enabled;
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new TaskCommonPropertiesComposite(this);
	}
}
