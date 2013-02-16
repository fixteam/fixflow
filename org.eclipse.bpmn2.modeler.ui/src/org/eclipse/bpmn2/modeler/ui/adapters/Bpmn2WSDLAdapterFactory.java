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

import org.eclipse.bpmn2.modeler.core.adapters.AdapterProvider;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory;


/**
 * Bpmn2WSDLAdapterFactory for generating adapters.
 * 
 * We use an instance of AdapterProvider that caches singleton adapters.
 *
 * @author IBM
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date May 23, 2007
 *
 */

@SuppressWarnings("restriction")

public class Bpmn2WSDLAdapterFactory extends WSDLAdapterFactory {
	
	static Bpmn2WSDLAdapterFactory instance;
		
	AdapterProvider provider;	
	
	/**
	 * Private constructor to allow only a singleton instances
	 * @param provider
	 */
	
	private Bpmn2WSDLAdapterFactory () {	
		this.provider = new AdapterProvider();
	}

	
	/**
	 * Get the instance of this factory.
	 * 
	 * @return an instance of this factory.
	 */
	
	public static Bpmn2WSDLAdapterFactory getInstance() {
		if (instance == null) {
			instance = new Bpmn2WSDLAdapterFactory();
		}
		return instance;
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createDefinitionAdapter()
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=330813
	 * https://jira.jboss.org/browse/JBIDE-7107
	 * This adds the INamespace adapter to Definition objects (required for the XPath expression editor)
	 */
	@Override
	public Adapter createDefinitionAdapter() {
		return provider.getAdapter( DefinitionAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createMessageAdapter()
	 */
	@Override
	public Adapter createMessageAdapter() {
		return provider.getAdapter( MessageAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createFaultAdapter()
	 */
	@Override
	public Adapter createFaultAdapter() {
		return provider.getAdapter( FaultAdapter.class );
	}
	
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createOperationAdapter()
	 */
	
	@Override
	public Adapter createOperationAdapter() {
		return provider.getAdapter( OperationAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createPartAdapter()
	 */
	@Override
	public Adapter createPartAdapter() {
		return provider.getAdapter( PartAdapter.class );
	}
	
	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createPortTypeAdapter()
	 */
	@Override
	public Adapter createPortTypeAdapter() {
		return provider.getAdapter( PortTypeAdapter.class );
	}

	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createInputAdapter()
	 */
	@Override	
	public Adapter createInputAdapter() {
		return provider.getAdapter(InputMessageAdapter.class);
	}

	/**
	 * @see org.eclipse.wst.wsdl.internal.util.WSDLAdapterFactory#createOutputAdapter()
	 */
	@Override
	public Adapter createOutputAdapter() {
		return provider.getAdapter(OutputMessageAdapter.class);
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

