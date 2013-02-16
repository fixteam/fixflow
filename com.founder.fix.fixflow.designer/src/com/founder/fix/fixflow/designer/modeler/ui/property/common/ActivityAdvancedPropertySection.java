package com.founder.fix.fixflow.designer.modeler.ui.property.common;


import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

public class ActivityAdvancedPropertySection extends AbstractBpmn2PropertySection {
	


	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject eObject = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		boolean enabled = ((eObject instanceof Activity) && (!(eObject instanceof ManualTask)));
		return enabled;
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new ActivityAdvancedPropertiesComposite(this);
	}

	

}
