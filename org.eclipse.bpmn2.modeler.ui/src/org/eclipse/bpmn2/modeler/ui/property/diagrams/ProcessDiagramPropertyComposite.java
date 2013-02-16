package org.eclipse.bpmn2.modeler.ui.property.diagrams;

import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

public class ProcessDiagramPropertyComposite extends DefaultPropertiesComposite {

	private AbstractPropertiesProvider propertiesProvider;

	public ProcessDiagramPropertyComposite(Composite parent, int style) {
		super(parent, style);
	}

	public ProcessDiagramPropertyComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	@Override
	public AbstractPropertiesProvider getPropertiesProvider(EObject object) {
		if (propertiesProvider == null) {
			propertiesProvider = new AbstractPropertiesProvider(object) {
				String[] properties = new String[] {
						"id", "name",
						"anyAttribute",
						"processType", "isExecutable", "isClosed",
						"definitionalCollaborationRef",
						"ioSpecification",
						"ioBinding",
						"properties", "laneSets", "correlationSubscriptions"};
				
				@Override
				public String[] getProperties() {
					return properties; 
				}
			};
		}
		return propertiesProvider;
	}
}
