package com.founder.fix.fixflow.designer.modeler.ui.property.definitions;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;


public class ErrorEventDefinitionConfigPropertySection extends AbstractBpmn2PropertySection{
	
	
	

	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		if(eObject instanceof BPMNDiagram)
		{

						return false;

		}
	
		
		return false;
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new ErrorEventDefinitionConfigPropertiesComposite(this);
	}

}
