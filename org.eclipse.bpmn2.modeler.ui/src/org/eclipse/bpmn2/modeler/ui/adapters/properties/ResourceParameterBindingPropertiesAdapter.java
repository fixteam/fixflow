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
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Bob Brodt
 *
 */
public class ResourceParameterBindingPropertiesAdapter extends ExtendedPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public ResourceParameterBindingPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);

		// ResourceParameters are contained in Resource, a root element
    	setProperty(Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF, ExtendedPropertiesAdapter.UI_IS_MULTI_CHOICE, Boolean.TRUE);
    	setProperty(Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF, ExtendedPropertiesAdapter.UI_CAN_CREATE_NEW, Boolean.FALSE);
    	setProperty(Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF, ExtendedPropertiesAdapter.UI_CAN_EDIT, Boolean.FALSE);
    	setProperty(Bpmn2Package.RESOURCE_PARAMETER_BINDING__PARAMETER_REF, ExtendedPropertiesAdapter.UI_CAN_SET_NULL, Boolean.TRUE);
	}

}
