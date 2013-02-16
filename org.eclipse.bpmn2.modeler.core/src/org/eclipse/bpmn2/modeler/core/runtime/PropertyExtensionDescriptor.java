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

package org.eclipse.bpmn2.modeler.core.runtime;

import java.lang.reflect.Constructor;

import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Bob Brodt
 *
 */
public class PropertyExtensionDescriptor extends BaseRuntimeDescriptor {
	
	protected String type;
	protected String adapterClassName;

	/**
	 * @param rt
	 */
	public PropertyExtensionDescriptor(TargetRuntime rt) {
		super(rt);
	}

	public ExtendedPropertiesAdapter getAdapter(AdapterFactory adapterFactory, EObject object) {
		try {
			ClassLoader cl = this.getRuntime().getRuntimeExtension().getClass().getClassLoader();
			Constructor ctor = null;
			Class adapterClass = Class.forName(adapterClassName, true, cl);
			ctor = adapterClass.getConstructor(AdapterFactory.class, EObject.class);
			return (ExtendedPropertiesAdapter)ctor.newInstance(adapterFactory, object);
		} catch (Exception e) {
			Activator.logError(e);
		}
		return null;
	}
}
