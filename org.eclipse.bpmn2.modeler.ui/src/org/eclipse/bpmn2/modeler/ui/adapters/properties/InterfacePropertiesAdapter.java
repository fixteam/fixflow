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
import org.eclipse.bpmn2.Interface;
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
public class InterfacePropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public InterfacePropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);
		
    	final EStructuralFeature ref = Bpmn2Package.eINSTANCE.getInterface_ImplementationRef();
    	setFeatureDescriptor(ref,
			new FeatureDescriptor(adapterFactory,object,ref) {
				@Override
				public String getText(Object context) {
					final Interface iface = context instanceof Interface ?
							(Interface)context :
							(Interface)this.object;
							
					if (iface.getImplementationRef()!=null) {
						return ModelUtil.getStringWrapperValue( iface.getImplementationRef() ); // + type;
					}
					return "";
				}
				
	    		@Override
				public EObject createObject(Object context) {
					final Interface iface = context instanceof Interface ?
							(Interface)context :
							(Interface)this.object;

					EObject impl = ModelUtil.createStringWrapper("");
					InsertionAdapter.add(iface, ref, impl);
					return impl;
	    		}

	    		@Override
	    		public Object getValue(Object context) {
					Interface iface = context instanceof Interface ?
							(Interface)context :
							(Interface)this.object;
					return iface.getImplementationRef();
	    		}

	    		@Override
	    		public void setValue(Object context, Object value) {
					Interface iface = context instanceof Interface ?
							(Interface)context :
							(Interface)this.object;

					if (value instanceof String) {
						value = ModelUtil.createStringWrapper((String)value);
	    			}
	    			else if (!ModelUtil.isStringWrapper(value)) {
	    				return;
	    			}
	    			super.setValue(object,value);
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
