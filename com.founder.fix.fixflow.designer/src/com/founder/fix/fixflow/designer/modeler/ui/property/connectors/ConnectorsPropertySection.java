package com.founder.fix.fixflow.designer.modeler.ui.property.connectors;


import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil.Bpmn2DiagramType;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import com.founder.fix.fixflow.designer.util.FixBpmnUtil;

public class ConnectorsPropertySection extends AbstractBpmn2PropertySection {
	
	


	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		
		

		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		
		Bpmn2DiagramType bpmn2DiagramType=FixBpmnUtil.getBpmn2DiagramType(ModelUtil.getDefinitions(eObject));
		if(bpmn2DiagramType==Bpmn2DiagramType.COLLABORATION){
			boolean enabled = ((eObject instanceof Participant || eObject instanceof Task|| eObject instanceof SubProcess||eObject instanceof CallActivity) && (!(eObject instanceof ManualTask)));
			return enabled;
		}
		else{
			
			boolean enabled = ((eObject instanceof BPMNDiagram || eObject instanceof Task|| eObject instanceof SubProcess||eObject instanceof CallActivity) && (!(eObject instanceof ManualTask)));
			return enabled;
			
		}
		
		
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new ConnectorsPropertiesComposite(this);
	}
	

}
