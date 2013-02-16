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


package org.eclipse.bpmn2.modeler.ui.property.tasks;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.DataInput;
import org.eclipse.bpmn2.DataInputAssociation;
import org.eclipse.bpmn2.DataOutput;
import org.eclipse.bpmn2.DataOutputAssociation;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.InputSet;
import org.eclipse.bpmn2.OutputSet;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertiesComposite;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2PropertySection;
import org.eclipse.bpmn2.modeler.ui.property.AbstractBpmn2TableComposite;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;

/**
 * This class renders the property sheet tab for I/O Parameters
 * defined in Activities, CallableElements and ThrowEvents.
 * 
 * TODO: handle ThrowEvent parameters
 */
public class IoParametersPropertiesComposite extends AbstractBpmn2PropertiesComposite {

	AbstractBpmn2TableComposite inputTable;
	AbstractBpmn2TableComposite outputTable;
	
	public IoParametersPropertiesComposite(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @param section
	 */
	public IoParametersPropertiesComposite(AbstractBpmn2PropertySection section) {
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
	public void createBindings(final EObject be) {
		final EStructuralFeature ioSpecificationFeature = be.eClass().getEStructuralFeature("ioSpecification");
		if (ioSpecificationFeature != null) {
			// the container parameter must be an Activity or CallableElement (i.e. a Process or GlobalTask)
			InputOutputSpecification ioSpecification = (InputOutputSpecification)be.eGet(ioSpecificationFeature);
			if (ioSpecification==null) {
				ioSpecification = FACTORY.createInputOutputSpecification();
				InsertionAdapter.add(be, "ioSpecification", ioSpecification);
			}

			EStructuralFeature dataInputsFeature = getFeature(ioSpecification, "dataInputs");
			inputTable = new IOParametersTable(be, ioSpecification, dataInputsFeature);
			inputTable.bindList(ioSpecification, dataInputsFeature);
			inputTable.setTitle("Input Parameters");

			EStructuralFeature dataOutputsFeature = getFeature(ioSpecification, "dataOutputs");
			outputTable = new IOParametersTable(be, ioSpecification, dataOutputsFeature);
			outputTable.bindList(ioSpecification, dataOutputsFeature);
			outputTable.setTitle("Output Parameters");
		}
		else {
			// the container is a ThrowEvent
		}
	}
	
	public class IOParametersTable extends AbstractBpmn2TableComposite {

		Activity activity;
		CallableElement element;
		InputOutputSpecification ioSpecification;
		EStructuralFeature ioFeature;
		boolean isInput;
		
		public IOParametersTable(EObject container, InputOutputSpecification ioSpecification, EStructuralFeature ioFeature) {
			super(IoParametersPropertiesComposite.this, ADD_BUTTON|REMOVE_BUTTON|SHOW_DETAILS);
			this.ioFeature = ioFeature;
			this.ioSpecification = ioSpecification;
			if (container instanceof Activity) {
				this.activity = (Activity)container;
				columnProvider = new AbstractTableColumnProvider() {
					@Override
					public boolean canModify(EObject object, EStructuralFeature feature, EObject item) {
						return true;
					}
				};

				EClass listItemClass = getListItemClass(ioSpecification, ioFeature);
				EAttribute name = (EAttribute)listItemClass.getEStructuralFeature("name");
				columnProvider.add(new TableColumn(ioSpecification,name));
			}
			else if (container instanceof CallableElement) {
				this.element = (CallableElement)container;
			}
			this.isInput = ("dataInputs".equals(ioFeature.getName()));
		}

		@Override
		protected EObject addListItem(EObject object, EStructuralFeature feature) {
			EObject param = null;
			InputDialog dialog = new InputDialog(getShell(), "Add Parameter",
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
			
			// Make sure that the ioSpecification is contained in our Activity.
			InsertionAdapter.executeIfNeeded(ioSpecification);
			
			param = super.addListItem(object, feature);
			if (param instanceof DataInput) {
				// add the new parameter to the InputSet
				((DataInput)param).setName(name);
				List<InputSet> inputSets = ioSpecification.getInputSets();
				if (inputSets.size()==0) {
					inputSets.add(FACTORY.createInputSet());
				}
				InputSet is = inputSets.get(0);
				is.getDataInputRefs().add((DataInput) param);
			}
			else if (param instanceof DataOutput)
			{
				// add the new parameter to the OutputSet
				((DataOutput)param).setName(name);
				List<OutputSet> outputSets = ioSpecification.getOutputSets();
				if (outputSets.size()==0) {
					outputSets.add(FACTORY.createOutputSet());
				}
				OutputSet os = outputSets.get(0);
				os.getDataOutputRefs().add((DataOutput) param);
			}
			
			if (activity!=null) {
				// this is an Activity - create an Input or Output DataAssociation
				if (param instanceof DataInput) {
					DataInputAssociation inputAssociation = FACTORY.createDataInputAssociation();
					activity.getDataInputAssociations().add(inputAssociation);
					inputAssociation.setTargetRef((DataInput) param);
				}
				else if (param instanceof DataOutput)
				{
					DataOutputAssociation outputAssociation = FACTORY.createDataOutputAssociation();
					activity.getDataOutputAssociations().add(outputAssociation);
					outputAssociation.getSourceRef().clear();
					outputAssociation.getSourceRef().add((DataOutput) param);
				}
			}
			else if (element!=null) {
				// this is a CallableElement - it has no DataAssociations so we're all done
			}
			return param;
		}

		@Override
		protected EObject editListItem(EObject object, EStructuralFeature feature) {
			return super.editListItem(object, feature);
		}

		@Override
		protected Object removeListItem(EObject object, EStructuralFeature feature, int index) {
			EList<EObject> list = (EList<EObject>)object.eGet(feature);
			EObject item = list.get(index);

			if (item instanceof DataInput) {
				// remove parameter from inputSets
				List<InputSet> inputSets = ioSpecification.getInputSets();
				for (InputSet is : inputSets) {
					if (is.getDataInputRefs().contains(item))
						is.getDataInputRefs().remove(item);
				}
			}
			else if (item instanceof DataOutput) {
				// remove parameter from outputSets
				List<OutputSet> OutputSets = ioSpecification.getOutputSets();
				for (OutputSet is : OutputSets) {
					if (is.getDataOutputRefs().contains(item))
						is.getDataOutputRefs().remove(item);
				}
			}
			
			if (activity!=null) {
				// this is an Activity
				// remove Input or Output DataAssociations
				if (item instanceof DataInput) {
					List<DataInputAssociation> dataInputAssociations = activity.getDataInputAssociations();
					List<DataInputAssociation> removed = new ArrayList<DataInputAssociation>();
					for (DataInputAssociation dia : dataInputAssociations) {
						if (dia.getTargetRef().equals(item))
							removed.add(dia);
					}
					dataInputAssociations.removeAll(removed);
				}
				else if (item instanceof DataOutput) {
					List<DataOutputAssociation> dataOutputAssociations = activity.getDataOutputAssociations();
					List<DataOutputAssociation> removed = new ArrayList<DataOutputAssociation>();
					for (DataOutputAssociation doa : dataOutputAssociations) {
						if (doa.getTargetRef().equals(item))
							removed.add(doa);
					}
					dataOutputAssociations.removeAll(removed);
				}
			}
			else if (element!=null) {
				// this is a CallableElement
			}
			else
				return false;
 
			return super.removeListItem(object, feature, index);
		}
		
	}
}