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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class TaskCommonPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		boolean enabled = ((eObject instanceof Task) && !(eObject instanceof UserTask) && !(eObject instanceof ScriptTask) && !(eObject instanceof ServiceTask) && !(eObject instanceof SendTask) && !(eObject instanceof ReceiveTask) && !(eObject instanceof ManualTask) && !(eObject instanceof BusinessRuleTask) && !(eObject instanceof ChoreographyTask)) ;
		return enabled;
	}

}
