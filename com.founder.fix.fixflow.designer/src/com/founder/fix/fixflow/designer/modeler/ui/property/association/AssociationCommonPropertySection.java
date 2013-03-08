package com.founder.fix.fixflow.designer.modeler.ui.property.association;


import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;

import org.eclipse.ui.IWorkbenchPart;


public class AssociationCommonPropertySection extends AbstractBpmn2PropertySection{
	

	
	
	@Override
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		EObject object = BusinessObjectUtil.getBusinessObjectForSelection(selection);
		return object!=null && Bpmn2Package.eINSTANCE.getAssociation() == object.eClass();
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new AssociationCommonPropertiesComposite(this);
	}


}
