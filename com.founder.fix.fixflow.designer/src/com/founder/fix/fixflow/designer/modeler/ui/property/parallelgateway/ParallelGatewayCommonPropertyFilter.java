package com.founder.fix.fixflow.designer.modeler.ui.property.parallelgateway;

import org.eclipse.bpmn2.ParallelGateway;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class ParallelGatewayCommonPropertyFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		boolean enabled = eObject instanceof ParallelGateway ;
		return enabled;
	}
}