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

package org.eclipse.bpmn2.modeler.ui.adapters.properties;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.InputOutputSpecification;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Bob Brodt
 *
 */
public class MultiInstanceLoopCharacteristicsPropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public MultiInstanceLoopCharacteristicsPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);

    	EStructuralFeature f = Bpmn2Package.eINSTANCE.getMultiInstanceLoopCharacteristics_InputDataItem();
		setFeatureDescriptor(f, new LoopCharacteristicsDataInputFeatureDescriptor(adapterFactory,object, f));

    	f = Bpmn2Package.eINSTANCE.getMultiInstanceLoopCharacteristics_LoopDataInputRef();
		setFeatureDescriptor(f, new LoopCharacteristicsDataInputFeatureDescriptor(adapterFactory,object, f));
	}

	protected class LoopCharacteristicsDataInputFeatureDescriptor extends FeatureDescriptor {

		/**
		 * @param adapterFactory
		 * @param object
		 * @param feature
		 */
		public LoopCharacteristicsDataInputFeatureDescriptor(AdapterFactory adapterFactory, EObject object, EStructuralFeature feature) {
			super(adapterFactory, object, feature);

			setProperty(feature, UI_CAN_EDIT, Boolean.FALSE);
			setProperty(feature, UI_CAN_CREATE_NEW, Boolean.FALSE);
		}
		
		@Override
		public Hashtable<String, Object> getChoiceOfValues(Object context) {
			Hashtable<String, Object> values = new Hashtable<String, Object>();
			EObject object = context instanceof EObject ? (EObject)context : this.object;
			
			EObject container = object.eContainer();
			while (container!=null) {
				if (container instanceof Activity || container instanceof Process) {
					List properties = null;
					if (feature.getName().equals("inputDataItem")) {
						EStructuralFeature f = container.eClass().getEStructuralFeature("ioSpecification");
						if (f!=null) {
							InputOutputSpecification ioSpecification = (InputOutputSpecification)container.eGet(f);
							if (ioSpecification!=null)
								properties = ioSpecification.getDataInputs();
						}
					}
					else {
						EStructuralFeature f = container.eClass().getEStructuralFeature("properties");
						if (f!=null)
							properties = (List)container.eGet(f);
					}
					
					if (properties!=null) {
						for (Object p : properties) {
							values.put( getValueText(p), p);
						}
					}
				}
				container = container.eContainer();
			}
			super.setChoiceOfValues(values);
			return super.getChoiceOfValues(context);
		}
	}
}
