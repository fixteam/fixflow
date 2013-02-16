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

import org.eclipse.bpmn2.Bpmn2Package;
import org.eclipse.bpmn2.ItemDefinition;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.bpmn2.modeler.core.adapters.InsertionAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.ObjectDescriptor;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Bob Brodt
 *
 */
public class ItemDefinitionPropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public ItemDefinitionPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);

    	final EStructuralFeature ref = Bpmn2Package.eINSTANCE.getItemDefinition_StructureRef();
    	setFeatureDescriptor(ref,
			new FeatureDescriptor(adapterFactory,object,ref) {
				@Override
				public String getLabel(Object context) {
					EObject object = this.object;
					if (context instanceof EObject)
						object = (EObject)context;
					if (object instanceof ItemDefinition) {
						return "Data Type";
					}
					return super.getLabel(context);
				}

				@Override
				public String getText(Object context) {
					ItemDefinition itemDefinition = context instanceof ItemDefinition ?
							(ItemDefinition)context :
							(ItemDefinition)this.object;
					if (itemDefinition.getStructureRef()!=null) {
						return ModelUtil.getStringWrapperValue(itemDefinition.getStructureRef());
					}
					return "";
					// this bit doesn't allow for manual editing of a QName, so it's been removed
//					final ItemDefinition itemDefinition = context instanceof ItemDefinition ?
//							(ItemDefinition)context :
//							(ItemDefinition)this.object;
//							
//					if (itemDefinition.getStructureRef()!=null) {
//    						String type = " (Undefined";
//        					if (itemDefinition.getItemKind().equals(ItemKind.PHYSICAL))
//        						type = " (Physical";
//        					else if (itemDefinition.getItemKind().equals(ItemKind.INFORMATION))
//        						type = " (Informational";
//        					if (itemDefinition.isIsCollection())
//        						type += " Collection)";
//        					else
//        						type += ")";
//
//						return ModelUtil.getStructureRefValue( itemDefinition.getStructureRef() ) + type;
//					}
//					return super.getText(context);
				}
				
	    		@Override
				public EObject createObject(Object context) {
					final ItemDefinition itemDefinition = context instanceof ItemDefinition ?
							(ItemDefinition)context :
							(ItemDefinition)this.object;

					EObject structureRef = ModelUtil.createStringWrapper("");
					InsertionAdapter.add(itemDefinition, ref, structureRef);
					return structureRef;
	    		}

	    		@Override
	    		public Object getValue(Object context) {
					ItemDefinition itemDefinition = context instanceof ItemDefinition ?
							(ItemDefinition)context :
							(ItemDefinition)this.object;
					return itemDefinition.getStructureRef();
	    		}

	    		@Override
	    		public void setValue(Object context, Object value) {
					ItemDefinition itemDefinition = context instanceof ItemDefinition ?
							(ItemDefinition)context :
							(ItemDefinition)this.object;

	    			if (value instanceof String) {
						value = ModelUtil.createStringWrapper((String)value);
	    			}
	    			else if (!ModelUtil.isStringWrapper(value)) {
	    				return;
	    			}
	    			super.setValue(object, value);
	    		}
			}
    	);
    	
		setObjectDescriptor(new ObjectDescriptor(adapterFactory, object) {
			@Override
			public String getText(Object context) {
				return getFeatureDescriptor(ref).getText(context);
			}
		});
	}

}
