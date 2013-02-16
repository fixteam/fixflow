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

package org.eclipse.bpmn2.modeler.core.adapters;

import java.lang.reflect.Field;
import java.util.Hashtable;

import org.eclipse.bpmn2.di.BPMNDiagram;
import org.eclipse.bpmn2.modeler.core.utils.JavaReflectionUtil;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @author Bob Brodt
 *
 */
public class ExtendedPropertiesAdapter extends AdapterImpl {

	// common property keys
	public final static String LONG_DESCRIPTION = "long.description";
	public final static String UI_CAN_EDIT = "ui.can.edit";
	// Any adapter that uses this must override setValue() which understands
	// how to convert a String to the required type.
	// This is used in ComboObjectEditor (maybe others in the future)
	public final static String UI_CAN_EDIT_INLINE = "ui.can.edit.inline";
	public final static String UI_CAN_CREATE_NEW = "ui.can.create.new";
	public final static String UI_CAN_SET_NULL = "ui.can.set.null";
	public final static String UI_IS_MULTI_CHOICE = "ui.is.multi.choice";
	public static final String PROPERTY_DESCRIPTOR = "property.descriptor";
	
	protected Hashtable<
		Integer, // feature ID
		Hashtable<String,Object>> // property key and value
			featureProperties = new Hashtable<Integer, Hashtable<String,Object>>();
	protected Hashtable <
		String, // property key
		Object> // value
			objectProperties = new Hashtable <String,Object>();
	
	protected AdapterFactory adapterFactory;
	
	public ExtendedPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super();
		this.adapterFactory = adapterFactory;
		setTarget(object);

		String name = "";
		if (object instanceof BPMNDiagram) {
			switch(ModelUtil.getDiagramType(object)) {
			case NONE:
				name = "UnknownDiagram";
				break;
			case PROCESS:
				name = "ProcessDiagram";
				break;
			case CHOREOGRAPHY:
				name = "ChoreographyDiagram";
				break;
			case COLLABORATION:
				name = "CollaborationDiagram";
				break;
			}
		}
		else {
			name = object.eClass().getName().replaceAll("Impl$", "");
		}
		// Set the model element's long description from the Messages class.
		// The field in Messages that contains the description will have the
		// form: "UI_<BPMN2ElementName>_long_description".
		// The Messages class must be contained somewhere in the package hierarchy
		// that contains the adapter factory class; by default, this will be the
		// BPMN2 modeler UI plugin hierarchy, starting with org.eclipse.bpmn2.modeler.ui.adapters
    	try {
        	String fieldName = "UI_" + name + "_long_description";
        	Class messages = JavaReflectionUtil.findClass(adapterFactory, "Messages");
			Field field = messages.getField(fieldName);
			String text = (String)field.get(null);
			setProperty(LONG_DESCRIPTION, text);
		} catch (Exception e) {
		}
	}
    
	public void setObjectDescriptor(ObjectDescriptor pd) {
		setProperty(PROPERTY_DESCRIPTOR,pd);
	}

	public ObjectDescriptor getObjectDescriptor() {
		ObjectDescriptor pd = (ObjectDescriptor) getProperty(PROPERTY_DESCRIPTOR);
		if (pd==null) {
			pd = new ObjectDescriptor(adapterFactory, (EObject)getTarget());
			setProperty(PROPERTY_DESCRIPTOR,pd);
		}
		return pd;
	}

	public FeatureDescriptor getFeatureDescriptor(EStructuralFeature feature) {
		FeatureDescriptor pd = (FeatureDescriptor) getProperty(feature.getFeatureID(),PROPERTY_DESCRIPTOR);
		if (pd==null) {
			pd = new FeatureDescriptor(adapterFactory, (EObject)getTarget(), feature);
			setProperty(feature.getFeatureID(),PROPERTY_DESCRIPTOR,pd);
		}
		return pd;
	}

	public void setFeatureDescriptor(EStructuralFeature feature, FeatureDescriptor pd) {
		setProperty(feature.getFeatureID(),PROPERTY_DESCRIPTOR,pd);
	}

	public Object getProperty(String key) {
		return objectProperties.get(key);
	}

	public boolean getBooleanProperty(String key) {
		Object result = getProperty(key);
		if (result instanceof Boolean)
			return ((Boolean)result);
		return false;
	}

	public void setProperty(String key, Object value) {
		objectProperties.put(key, value);
	}

	public Object getProperty(EStructuralFeature feature, String key) {
		return getProperty(feature.getFeatureID(), key);
	}

	public boolean getBooleanProperty(EStructuralFeature feature, String key) {
		Object result = getProperty(feature, key);
		if (result instanceof Boolean)
			return ((Boolean)result);
		return false;
	}

	public void setProperty(EStructuralFeature feature, String key, Object value) {
		setProperty(feature.getFeatureID(), key, value);
	}

	public Object getProperty(int id, String key) {
		Integer idObject = Integer.valueOf(id);
		Hashtable<String,Object> props = featureProperties.get(idObject);
		if (props==null) {
			props = new Hashtable<String,Object>();
			featureProperties.put(idObject,props);
		}
		return props.get(key);
	}

	public void setProperty(int id, String key, Object value) {
		Integer idObject = Integer.valueOf(id);
		Hashtable<String,Object> props = featureProperties.get(idObject);
		if (props==null) {
			props = new Hashtable<String,Object>();
			featureProperties.put(idObject,props);
		}
		props.put(key, value);
	}
}
