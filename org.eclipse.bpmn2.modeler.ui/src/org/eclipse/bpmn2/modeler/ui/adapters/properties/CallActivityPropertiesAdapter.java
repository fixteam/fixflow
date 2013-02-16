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
import org.eclipse.bpmn2.CallActivity;
import org.eclipse.bpmn2.CallableElement;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.bpmn2.modeler.core.adapters.FeatureDescriptor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * @author Bob Brodt
 *
 */
public class CallActivityPropertiesAdapter extends ActivityPropertiesAdapter {

	/**
	 * @param adapterFactory
	 * @param object
	 */
	public CallActivityPropertiesAdapter(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory, object);

    	setProperty(Bpmn2Package.CALL_ACTIVITY__CALLED_ELEMENT_REF, ExtendedPropertiesAdapter.UI_CAN_CREATE_NEW, Boolean.TRUE);
    	EStructuralFeature ce = Bpmn2Package.eINSTANCE.getCallActivity_CalledElementRef();
    	setFeatureDescriptor(ce,
			new FeatureDescriptor(adapterFactory,object,ce) {
				@Override
				public String getLabel(Object context) {
						return "Called Activity";
				}
				
				@Override
				public String getText(Object context) {
					CallActivity object = (CallActivity)this.object;
					if (context instanceof CallActivity)
						object = (CallActivity)context;
					CallableElement ce = object.getCalledElementRef();
					if (ce!=null && ce.eIsProxy()) {
						URI uri = ((InternalEObject)ce).eProxyURI();
						if (uri.hasFragment())
							return uri.fragment();
						return uri.lastSegment();
					}
					return super.getText(context);
				}
			}
    	);
	}

}
