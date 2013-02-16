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

import org.eclipse.bpmn2.Bpmn2Factory;
import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.DataState;
import org.eclipse.bpmn2.ItemAwareElement;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.adapters.AdapterUtil;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Bob Brodt
 *
 */
public class ItemAwareElementPropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param target
	 */
	public ItemAwareElementPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);
		
    	EStructuralFeature ref = Bpmn2Package.eINSTANCE.getItemAwareElement_ItemSubjectRef();
    	
    	setFeatureDescriptor(ref,
			new FeatureDescriptor(adapterFactory,object,ref) {
				@Override
				public String getLabel(Object context) {
					EObject object = this.object;
					if (context instanceof EObject)
						object = (EObject)context;
					ItemDefinition itemDefinition = null;
					if (object instanceof ItemDefinition)
						itemDefinition = (ItemDefinition) object;
					else if (object instanceof ItemAwareElement)
						itemDefinition = (ItemDefinition) object.eGet(feature);
					if (itemDefinition!=null) {
						ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(itemDefinition, ExtendedPropertiesAdapter.class);
						return adapter.getFeatureDescriptor(Bpmn2Package.eINSTANCE.getItemDefinition_StructureRef()).getLabel(itemDefinition);
					}
					return ModelUtil.getLabel(object) + " Type";
				}

				@Override
				public String getText(Object context) {
					EObject object = this.object;
					if (context instanceof EObject)
						object = (EObject)context;
					ItemDefinition itemDefinition = null;
					if (object instanceof ItemDefinition)
						itemDefinition = (ItemDefinition) object;
					else if (object instanceof ItemAwareElement)
						itemDefinition = (ItemDefinition) object.eGet(feature);
					if (itemDefinition!=null) {
						ExtendedPropertiesAdapter adapter = (ExtendedPropertiesAdapter) AdapterUtil.adapt(itemDefinition, ExtendedPropertiesAdapter.class);
						return adapter.getFeatureDescriptor(Bpmn2Package.eINSTANCE.getItemDefinition_StructureRef()).getText(itemDefinition);
					}
					return super.getText(context);
				}
    		}
    	);
    	
    	ref = Bpmn2Package.eINSTANCE.getItemAwareElement_DataState();
    	setFeatureDescriptor(ref,
			new FeatureDescriptor(adapterFactory,object,ref) {
				@Override
				public void setValue(Object context, Object value) {
					final EObject object = (EObject) (context instanceof EObject ? context : this.object);
					if (value instanceof String) {
						// construct a DataState from the given name string
						DataState ds = Bpmn2Factory.eINSTANCE.createDataState();
						ds.setName((String)value);
						value = ds;
					}
					if (value instanceof DataState) {
						final DataState oldValue = (DataState) object.eGet(feature);
						if (value != oldValue) {
							// if this DataState belongs to some other ItemAwareElement, make a copy
							final DataState newValue = (DataState)(((DataState)value).eContainer()!=null ?
								clone((EObject) value) : value);
							TransactionalEditingDomain editingDomain = getEditingDomain(object);
							if (editingDomain == null) {
								object.eSet(feature, value);
							} else {
								editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
									@Override
									protected void doExecute() {
										object.eSet(feature, newValue);
										ModelUtil.setID(newValue);
									}
								});
							}
						}
					}
				}
			}
    	);
    	
    	setProperty(Bpmn2Package.ITEM_AWARE_ELEMENT__DATA_STATE, ExtendedPropertiesAdapter.UI_IS_MULTI_CHOICE, Boolean.FALSE);
	}

}
