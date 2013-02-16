package com.founder.fix.fixflow.designer.modeler.ui.property.intermediatethrowevent;

import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class IntermediateThrowEventCommonPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		boolean enabled = eObject instanceof IntermediateThrowEvent ;
		return enabled;
	}

}
