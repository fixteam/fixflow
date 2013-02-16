package com.founder.fix.fixflow.designer.modeler.ui.property.common;



import org.eclipse.bpmn2.Participant;
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


public class DataVariablePropertySection extends AbstractBpmn2PropertySection {
	

	

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {

		EObject object = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		
		Bpmn2DiagramType bpmn2DiagramType=FixBpmnUtil.getBpmn2DiagramType(ModelUtil.getDefinitions(object));
		if(bpmn2DiagramType==Bpmn2DiagramType.COLLABORATION){
			boolean enabled = object instanceof Participant ;//ParticipantImpl BPMNDiagramImpl
			return enabled;
		}
		else{
			boolean enabled = object instanceof BPMNDiagram ;//ParticipantImpl BPMNDiagramImpl
			
			return enabled;
		}
		
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new DataVariablePropertiesComposite(this);
	}


}
