/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.adapters;

import org.eclipse.bpel.wsil.model.inspection.util.InspectionAdapterFactory;
import org.eclipse.bpmn2.modeler.core.adapters.AdapterProvider;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;


/**
 * Bpmn2WSILAdapterFactory for generating adapters.
 * 
 * We use an instance of AdapterProvider that caches singleton adapters.
 *
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 10, 2007
 *
 */
public class Bpmn2WSILAdapterFactory extends InspectionAdapterFactory {
	
	static private Bpmn2WSILAdapterFactory instance;	
	
	AdapterProvider provider;
	
	/**
	 * The AdapterFactory constructor. Private, because there is only
	 * 1 instance of the factory obtainable via getInstance() method below. 
	 */
	
	private Bpmn2WSILAdapterFactory () {
		provider = new AdapterProvider();
	}
	
	/**
	 * Get the instance of the factory.
	 * 
	 * @return an instance of this WSIL UI factory
	 */
	
	public static Bpmn2WSILAdapterFactory getInstance() {
		if (instance == null) {
			instance = new Bpmn2WSILAdapterFactory();
		}
		return instance;
	}
	
	/**
	 * Create the inspection adapter.
	 * 
	 * @see org.eclipse.bpel.wsil.model.inspection.util.InspectionAdapterFactory#createInspectionAdapter()
	 */
	@Override
	public Adapter createInspectionAdapter() {
		return provider.getAdapter(WSILInspectionAdapter.class);
	}

	/**
	 * Create the link adapter.
	 * @see org.eclipse.bpel.wsil.model.inspection.util.InspectionAdapterFactory#createLinkAdapter()
	 */
	
	@Override
	public Adapter createLinkAdapter() {	
		return provider.getAdapter(WSILLinkAdapter.class);
	}

	/** Create the service adapter.
	 * 
	 * @see org.eclipse.bpel.wsil.model.inspection.util.InspectionAdapterFactory#createServiceAdapter()
	 */
	@Override
	public Adapter createServiceAdapter() {
		return provider.getAdapter(WSILServiceAdapter.class);
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#adaptNew(org.eclipse.emf.common.notify.Notifier, java.lang.Object)
	 */
	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		Adapter adapter = createAdapter(target, type);
		// Bugzilla 330519
		// only associate the adapter with the target (i.e. add it to the
		// target's eAdapters list) if the adapter is for the requested type
		if (adapter!=null && adapter.isAdapterForType(type)) {
			associate(adapter,target);
			return adapter;
		}
		return null;		
	}
	

	@Override
	protected Object resolve(Object object, Object type) {
		return null;
	}	
	
}
