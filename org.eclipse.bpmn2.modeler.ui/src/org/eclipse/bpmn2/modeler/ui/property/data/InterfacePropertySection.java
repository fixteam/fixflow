package org.eclipse.bpmn2.modeler.ui.property.data;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.Interface;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2TableComposite;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.DefaultPropertySection;
import org.eclipse.bpmn2.modeler.ui.property.PropertiesCompositeFactory;
import org.eclipse.bpmn2.modeler.ui.util.PropertyUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.widgets.Composite;

public class InterfacePropertySection extends DefaultPropertySection {

	static {
		PropertiesCompositeFactory.register(Interface.class, InterfacePropertiesComposite.class);
	}

	@Override
	protected AbstractBpmn2PropertiesComposite createSectionRoot() {
		return new InterfaceListComposite(this);
	}

	public InterfacePropertySection() {
		super();
	}

	@Override
	protected EObject getBusinessObjectForPictogramElement(PictogramElement pe) {
		EObject bo = super.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Participant) {
			return bo;
		} else if (bo instanceof BPMNDiagram) {
			BaseElement be = ((BPMNDiagram)bo).getPlane().getBpmnElement();
			if (be instanceof Process)
				return be;
		} else if (bo instanceof CallableElement) {
			return bo;
		}
		
		return null;
	}
	
	public class InterfaceListComposite extends DefaultPropertiesComposite {

		InterfaceTable interfaceTable;
		
		/**
		 * @param parent
		 * @param style
		 */
		public InterfaceListComposite(Composite parent, int style) {
			super(parent, style);
		}

		/**
		 * @param section
		 */
		public InterfaceListComposite(AbstractBpmn2PropertySection section) {
			super(section);
		}

		@Override
		public void createBindings(EObject be) {
			if (be instanceof Participant) {
				interfaceTable = new InterfaceTable(this);
				interfaceTable.bindList(be, getFeature(be, "interfaceRefs"));

			}
			else if (be instanceof CallableElement) {
				CallableElement ce = (CallableElement)be;
				interfaceTable = new InterfaceTable(this);
				interfaceTable.bindList(be, getFeature(be, "supportedInterfaceRefs"));
			}
		}

		public class InterfaceTable extends AbstractBpmn2TableComposite {
			
			/**
			 * @param section
			 * @param style
			 */
			public InterfaceTable(Composite parent) {
				super(parent,
						AbstractBpmn2TableComposite.SHOW_DETAILS |
						AbstractBpmn2TableComposite.ADD_BUTTON |
						AbstractBpmn2TableComposite.REMOVE_BUTTON);
			}

			@Override
			protected EObject addListItem(EObject object, EStructuralFeature feature) {
				Interface iface = (Interface) PropertyUtil.createObject(object, feature);
				Definitions defs = ModelUtil.getDefinitions(object);
				defs.getRootElements().add(iface);
				EList<EObject> list = (EList<EObject>)object.eGet(feature);
				list.add(iface);
				return iface;
			}
		}
	}
}
