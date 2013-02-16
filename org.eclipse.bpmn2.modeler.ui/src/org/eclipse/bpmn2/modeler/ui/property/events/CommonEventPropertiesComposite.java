/*******************************************************************************
 * Copyright (c) 2011 Red Hat, Inc.
 *  All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 *
 * @author Bob Brodt
 ******************************************************************************/


package org.eclipse.bpmn2.modeler.ui.property.events;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.CatchEvent;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.EndEvent;
import org.eclipse.bpmn2.Event;
import org.eclipse.bpmn2.FlowElementsContainer;
import org.eclipse.bpmn2.ImplicitThrowEvent;
import org.eclipse.bpmn2.Import;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.IntermediateCatchEvent;
import org.eclipse.bpmn2.IntermediateThrowEvent;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.bpmn2.StartEvent;
import org.eclipse.bpmn2.SubProcess;
import org.eclipse.bpmn2.ThrowEvent;
import org.eclipse.bpmn2.Transaction;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2TableComposite;
import org.eclipse.bpmn2.modeler.ui.property.dialogs.ModelSubclassSelectionDialog;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;

public class CommonEventPropertiesComposite extends AbstractBpmn2PropertiesComposite {

	protected AbstractBpmn2TableComposite inputTable;
	protected AbstractBpmn2TableComposite outputTable;
	protected EventDefinitionsTable eventsTable;

	public CommonEventPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public CommonEventPropertiesComposite(AbstractBpmn2PropertySection section) {
		super(section);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite
	 * #createBindings(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void createBindings(EObject be) {
		
		// Attributes
		if (be instanceof StartEvent) {
			bindAttribute(be,"isInterrupting");
		}
		if (be instanceof CatchEvent) {
			bindAttribute(be,"parallelMultiple");
		}
		if (be instanceof BoundaryEvent) {
			bindAttribute(be,"cancelActivity");
		}
		
		// Lists
		if (be instanceof Event) {
			bindList(be,"properties");
		}
		if (be instanceof CatchEvent || be instanceof ThrowEvent) {
			eventsTable = new EventDefinitionsTable(this, (Event)be);
			eventsTable.bindList(be, getFeature(be, "eventDefinitions"));
			eventsTable.setTitle("Event Definitions");

			if (be instanceof ThrowEvent) {
				ThrowEvent throwEvent = (ThrowEvent)be;
				inputTable = new DataInputsTable(this, throwEvent);
				inputTable.bindList(be, getFeature(throwEvent, "dataInputs"));
				inputTable.setTitle("Input Parameters");
			}
			else if (be instanceof CatchEvent) {
				CatchEvent catchEvent = (CatchEvent)be;
				outputTable = new DataOutputsTable(this, catchEvent);
				outputTable.bindList(catchEvent, getFeature(catchEvent, "dataOutputs"));
				outputTable.setTitle("Output Parameters");
			}
		}
	}
	
	public class EventDefinitionsTable extends AbstractBpmn2TableComposite {
		
		protected Event event;
		
		public EventDefinitionsTable(Composite parent, Event event) {
			super(parent, ADD_BUTTON|REMOVE_BUTTON|SHOW_DETAILS);
			this.event = event;
		}
		
		@Override
		public EClass getListItemClassToAdd(EClass listItemClass) {
			EClass eclass = null;
			ModelSubclassSelectionDialog dialog = new ModelSubclassSelectionDialog(getDiagramEditor(), object, feature) {
				@Override
				protected void filterList(List<EClass> items) {
					BaseElement eventOwner = null;
					if (event instanceof BoundaryEvent) {
						eventOwner = ((BoundaryEvent)event).getAttachedToRef();
					}
					else {
						EObject parent = event.eContainer();
						while (parent!=null) {
							if (parent instanceof FlowElementsContainer ) {
								eventOwner = (BaseElement)parent;
								break;
							}
							parent = parent.eContainer();
						}
					}
					List<EClass> filteredItems = new ArrayList<EClass>();
					List<EClass> allowedItems = new ArrayList<EClass>();
					if (event instanceof BoundaryEvent) {
						if (eventOwner instanceof Transaction) {
							if (((BoundaryEvent)event).isCancelActivity())
								allowedItems.add(PACKAGE.getCancelEventDefinition());
						}
						if (((BoundaryEvent)event).isCancelActivity())
							allowedItems.add(PACKAGE.getCompensateEventDefinition());
						allowedItems.add(PACKAGE.getConditionalEventDefinition());
						if (((BoundaryEvent)event).isCancelActivity())
							allowedItems.add(PACKAGE.getErrorEventDefinition());
						allowedItems.add(PACKAGE.getEscalationEventDefinition());
						allowedItems.add(PACKAGE.getMessageEventDefinition());
						allowedItems.add(PACKAGE.getSignalEventDefinition());
						allowedItems.add(PACKAGE.getTimerEventDefinition());
					}
					else if (event instanceof IntermediateCatchEvent) {
						allowedItems.add(PACKAGE.getConditionalEventDefinition());
						allowedItems.add(PACKAGE.getLinkEventDefinition());
						allowedItems.add(PACKAGE.getMessageEventDefinition());
						allowedItems.add(PACKAGE.getSignalEventDefinition());
						allowedItems.add(PACKAGE.getTimerEventDefinition());
					}
					else if (event instanceof StartEvent) {
						if (eventOwner instanceof SubProcess) {
							if (((StartEvent)event).isIsInterrupting()) {
								allowedItems.add(PACKAGE.getCompensateEventDefinition());
								allowedItems.add(PACKAGE.getErrorEventDefinition());
							}
							allowedItems.add(PACKAGE.getEscalationEventDefinition());
						}
						allowedItems.add(PACKAGE.getConditionalEventDefinition());
						allowedItems.add(PACKAGE.getMessageEventDefinition());
						allowedItems.add(PACKAGE.getSignalEventDefinition());
						allowedItems.add(PACKAGE.getTimerEventDefinition());
					}
					else if (event instanceof EndEvent) {
						if (eventOwner instanceof Transaction)
							allowedItems.add(PACKAGE.getCancelEventDefinition());
						allowedItems.add(PACKAGE.getCompensateEventDefinition());
						allowedItems.add(PACKAGE.getErrorEventDefinition());
						allowedItems.add(PACKAGE.getEscalationEventDefinition());
						allowedItems.add(PACKAGE.getMessageEventDefinition());
						allowedItems.add(PACKAGE.getSignalEventDefinition());
						allowedItems.add(PACKAGE.getTerminateEventDefinition());
					}
					else if (event instanceof ImplicitThrowEvent) {
						allowedItems.add(PACKAGE.getCompensateEventDefinition());
						allowedItems.add(PACKAGE.getEscalationEventDefinition());
						allowedItems.add(PACKAGE.getLinkEventDefinition());
						allowedItems.add(PACKAGE.getMessageEventDefinition());
						allowedItems.add(PACKAGE.getSignalEventDefinition());
					}
					else if (event instanceof IntermediateThrowEvent) {
						allowedItems.add(PACKAGE.getCompensateEventDefinition());
						allowedItems.add(PACKAGE.getEscalationEventDefinition());
						allowedItems.add(PACKAGE.getLinkEventDefinition());
						allowedItems.add(PACKAGE.getMessageEventDefinition());
						allowedItems.add(PACKAGE.getSignalEventDefinition());
					}
					for (EClass eclass : items) {
						if (allowedItems.contains(eclass))
							filteredItems.add(eclass);
					}
					items.clear();
					items.addAll(filteredItems);
				}
			};
			
			if (dialog.open()==Window.OK){
				eclass = (EClass)dialog.getResult()[0];
			}
			return eclass;
		}
	}

	public class DataInputsTable extends AbstractBpmn2TableComposite {

		ThrowEvent throwEvent;
		
		public DataInputsTable(Composite parent, ThrowEvent throwEvent) {
			super(parent, ADD_BUTTON|REMOVE_BUTTON|SHOW_DETAILS);
			this.throwEvent = throwEvent;
		}

		@Override
		protected EObject addListItem(EObject object, EStructuralFeature feature) {
			EObject param = null;
			InputDialog dialog = new InputDialog(getShell(), "Add Input Parameter",
					"Enter new parameter name","", new IInputValidator() {

						@Override
						public String isValid(String newText) {
							if (newText==null || newText.isEmpty() || newText.contains(" "))
								return "Please enter a valid name";
							return null;
						}
						
					});
			if (dialog.open()!=Window.OK){
				return null;
			}
			String name = dialog.getValue();
			
			param = super.addListItem(object, feature);
			// add the new parameter to the InputSet
			((DataInput)param).setName(name);
			InputSet inputSet = throwEvent.getInputSet();
			if (inputSet==null) {
				inputSet = FACTORY.createInputSet();
				throwEvent.setInputSet(inputSet);
			}
			inputSet.getDataInputRefs().add((DataInput) param);
			
			// create a DataInputAssociation
			DataInputAssociation inputAssociation = FACTORY.createDataInputAssociation();
			throwEvent.getDataInputAssociation().add(inputAssociation);
			inputAssociation.setTargetRef((DataInput) param);
			return param;
		}

		@Override
		protected EObject editListItem(EObject object, EStructuralFeature feature) {
			return super.editListItem(object, feature);
		}

		@Override
		protected Object removeListItem(EObject object, EStructuralFeature feature, int index) {
			// remove parameter from inputSets
			EList<EObject> list = (EList<EObject>)object.eGet(feature);
			EObject item = list.get(index);
			InputSet inputSet = throwEvent.getInputSet();
			if (inputSet.getDataInputRefs().contains(item))
				inputSet.getDataInputRefs().remove(item);
			
			// remove Input or Output DataAssociations
			List<DataInputAssociation> dataInputAssociations = throwEvent.getDataInputAssociation();
			List<DataInputAssociation> removed = new ArrayList<DataInputAssociation>();
			for (DataInputAssociation dia : dataInputAssociations) {
				if (dia.getTargetRef().equals(item))
					removed.add(dia);
			}
			dataInputAssociations.removeAll(removed);
 
			return super.removeListItem(object, feature, index);
		}
	}

	
	public class DataOutputsTable extends AbstractBpmn2TableComposite {

		CatchEvent catchEvent;
		
		public DataOutputsTable(Composite parent, CatchEvent catchEvent) {
			super(parent, ADD_BUTTON|REMOVE_BUTTON|SHOW_DETAILS);
			this.catchEvent = catchEvent;
		}

		@Override
		protected EObject addListItem(EObject object, EStructuralFeature feature) {
			EObject param = null;
			InputDialog dialog = new InputDialog(getShell(), "Add Output Parameter",
					"Enter new parameter name","", new IInputValidator() {

						@Override
						public String isValid(String newText) {
							if (newText==null || newText.isEmpty() || newText.contains(" "))
								return "Please enter a valid name";
							return null;
						}
						
					});
			if (dialog.open()!=Window.OK){
				return null;
			}
			String name = dialog.getValue();
			
			param = super.addListItem(object, feature);
			// add the new parameter to the OutputSet
			((DataOutput)param).setName(name);
			OutputSet outputSet = catchEvent.getOutputSet();
			if (outputSet==null) {
				outputSet = FACTORY.createOutputSet();
				catchEvent.setOutputSet(outputSet);
			}
			outputSet.getDataOutputRefs().add((DataOutput) param);

			
			// create a Data OutputAssociation
			DataOutputAssociation outputAssociation = FACTORY.createDataOutputAssociation();
			catchEvent.getDataOutputAssociation().add(outputAssociation);
			outputAssociation.getSourceRef().clear();
			outputAssociation.getSourceRef().add((DataOutput) param);
			return param;
		}

		@Override
		protected EObject editListItem(EObject object, EStructuralFeature feature) {
			return super.editListItem(object, feature);
		}

		@Override
		protected Object removeListItem(EObject object, EStructuralFeature feature, int index) {
			// remove parameter from outputSets
			EList<EObject> list = (EList<EObject>)object.eGet(feature);
			EObject item = list.get(index);
			OutputSet outputSet = catchEvent.getOutputSet();
			if (outputSet.getDataOutputRefs().contains(item))
				outputSet.getDataOutputRefs().remove(item);
			
			// remove Input or Output DataAssociations
			List<DataOutputAssociation> dataOutputAssociations = catchEvent.getDataOutputAssociation();
			List<DataOutputAssociation> removed = new ArrayList<DataOutputAssociation>();
			for (DataOutputAssociation doa : dataOutputAssociations) {
				if (doa.getTargetRef().equals(item))
					removed.add(doa);
			}
			dataOutputAssociations.removeAll(removed);
 
			return super.removeListItem(object, feature, index);
		}
		
		
	}
}