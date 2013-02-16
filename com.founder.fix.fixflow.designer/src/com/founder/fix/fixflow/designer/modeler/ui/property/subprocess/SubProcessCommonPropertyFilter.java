package com.founder.fix.fixflow.designer.modeler.ui.property.subprocess;


import org.eclipse.bpmn2.AdHocSubProcess;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class SubProcessCommonPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		boolean enabled = (eObject instanceof SubProcess&& !(eObject instanceof AdHocSubProcess) );
		return enabled;
	}

}
