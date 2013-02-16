package com.founder.fix.fixflow.designer.modeler.ui.property.connectors;



import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.ManualTask;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.Task;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class ConnectorsPropertyFilter extends AbstractPropertySectionFilter {
	
	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		boolean enabled = ((eObject instanceof BPMNDiagram || eObject instanceof Task|| eObject instanceof SubProcess||eObject instanceof CallActivity) && (!(eObject instanceof ManualTask)));
		return enabled;
	}
}