/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.bpmn2.modeler.core.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.bpmn2.di.impl.BpmnDiPackageImpl;
import org.eclipse.bpmn2.di.provider.BpmnDiItemProviderAdapterFactory;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dd.dc.impl.DcPackageImpl;
import org.eclipse.dd.dc.provider.DcItemProviderAdapterFactory;
import org.eclipse.dd.di.impl.DiPackageImpl;
import org.eclipse.dd.di.provider.DiItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

/**
 * This is the one place where EMF object adapters can be registered. 
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 23, 2007
 *
 */

public class AdapterRegistry {
	
	public static ComposedAdapterFactory BPMN2_ADAPTER_FACTORIES = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);;
	static AdapterFactoryLabelProvider LABEL_PROVIDER;

	/**
	 * The singleton instance of this registry.
	 */
	
	static final public AdapterRegistry INSTANCE = new AdapterRegistry();
	
	static {
		BPMN2_ADAPTER_FACTORIES.addAdapterFactory(new ResourceItemProviderAdapterFactory());
//		BPMN2_ADAPTER_FACTORIES.addAdapterFactory(
//				INSTANCE.registerFactory(BpmnDiPackageImpl.eINSTANCE, new BpmnDiItemProviderAdapterFactory()));
		BPMN2_ADAPTER_FACTORIES.addAdapterFactory(
				INSTANCE.registerFactory(DcPackageImpl.eINSTANCE, new DcItemProviderAdapterFactory()));
		BPMN2_ADAPTER_FACTORIES.addAdapterFactory(
				INSTANCE.registerFactory(DiPackageImpl.eINSTANCE, new DiItemProviderAdapterFactory()));

		BPMN2_ADAPTER_FACTORIES.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	/** For every type of EClass or EPackage, register an Adapter Factory 
	 *   This means that a particular EClass or EPackage may have N
	 *    
	 *   Adapter Factories. 
	 */
	
	HashMap<Object,List<AdapterFactory>> fKeyToAdapterFactory ;
	
	/** The current adapter manager */
	IAdapterManager fAdapterManager;
	
	/**
	 * Private constructor.
	 */
	
	AdapterRegistry () {
		fKeyToAdapterFactory = new HashMap<Object,List<AdapterFactory>>();
		
		if (Platform.isRunning()) {
			fAdapterManager = Platform.getAdapterManager();
		} else {
			fAdapterManager = org.eclipse.core.internal.runtime.AdapterManager.getDefault();
		}		
	}
	
	/**
	 * Register adapter factory for the given EClass.
	 * 
	 * @param key
	 * @param factory
	 */
	
	public void registerAdapterFactory (EClass key, AdapterFactory factory) {
		registerFactory (key,factory);
	}

	/**
	 * Register adapter factory for the given EPackage.
	 * 
	 * @param key
	 * @param factory
	 */
	
	public void registerAdapterFactory (EPackage key, AdapterFactory factory) {
		registerFactory (key,factory);
	}

	
	
	/**
	 * Unregister adapter factory for the given object (EClass)
	 * @param key
	 * @param factory
	 */
	
	public void unregisterAdapterFactory (EClass key, AdapterFactory factory) {
		unregisterFactory (key,factory);
	}

	/**
	 * Unregister adapter factory for the given object (EPackage)
	 * @param key
	 * @param factory
	 */
	
	public void unregisterAdapterFactory (EPackage key, AdapterFactory factory) {
		unregisterFactory (key,factory);
	}

	
	/**
	 * Register the factory (internal method, synchronized).
	 * @param key
	 * @param factory
	 */
	public synchronized AdapterFactory registerFactory ( Object key, AdapterFactory factory) {
		List<AdapterFactory> list = fKeyToAdapterFactory.get(key);
		
		if (list == null) {
			list = new ArrayList<AdapterFactory>( );
			fKeyToAdapterFactory.put( key, list );
			list.add (factory);
		} else {
			if (list.contains(factory) == false) {
				list.add (factory);
			}
		}
		
		return factory;
	}
	
	synchronized void unregisterFactory (Object key, AdapterFactory factory) {
		List<AdapterFactory> list = fKeyToAdapterFactory.get(key);
		
		if (list == null) {
			return ;
		}
		list.remove(factory);		
	}
	
	
	Class<?> adapterInterface ( Object type ) {
		
		if (type instanceof Class) {
			return (Class<?>) type;
		}
		
		if (type instanceof String) {
			try {
				return Class.forName((String)type);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}				
		}
		
		throw new RuntimeException("Adapter type " + type + " is not understood.");		 //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * @param <T>
	 * @param target
	 * @param clazz
	 * @return the adapted interface or object
	 */
		
	
	public <T extends Object> T adapt ( Object target,  Class<T> clazz) {
		return adapt (target,clazz,true);
	}
	

	/**
	 * 
	 * @param <T>
	 * @param target
	 * @param clazz
	 * @param checkWSAdapters Check the Workspace adapters as well.
	 * 
	 * @return the adapted interface or object
	 */

	public <T extends Object> T adapt ( Object target,  Class<T> clazz, boolean checkWSAdapters ) {
	
		if (target == null) {
			return null;
		}
		
		if (clazz.isInstance(target)) {
			return clazz.cast(target);
		}
		
		Object adapter = null;		
		
		if (target instanceof EObject) {
			
			EObject eObj = (EObject) target;
			EClass effectiveClass = eObj.eClass();
			for (Adapter  a : eObj.eAdapters()) {
				if (a != null && clazz.isInstance(a)) {
					return clazz.cast(a);
				}
			}
			
			List<AdapterFactory> list = fKeyToAdapterFactory.get( effectiveClass );
			if (list != null) {
				for(AdapterFactory factory : list) {
					adapter = factory.adapt(target, clazz);
					if (adapter != null && clazz.isInstance(adapter)) {
						return clazz.cast(adapter);
					}
				}

				// adaptNew() maybe?
				for(AdapterFactory factory : list ) {
					adapter = factory.adaptNew((Notifier)target, clazz);
					if (adapter != null && clazz.isInstance(adapter)) {
						return clazz.cast(adapter);
					}
				}					   
			}
			
			list = fKeyToAdapterFactory.get( effectiveClass.getEPackage() );
			if (list != null) {
				for(AdapterFactory factory : list ) {
					adapter = factory.adapt(target, clazz);
					if (adapter != null && clazz.isInstance(adapter)) {
						return clazz.cast(adapter);
					}
				}					   

				// adaptNew() maybe?
				for(AdapterFactory factory : list ) {
					if (factory instanceof ComposedAdapterFactory) {
						ComposedAdapterFactory cf = (ComposedAdapterFactory)factory;
						cf.adaptAllNew((Notifier) target);
					}
					
					adapter = factory.adaptNew((Notifier)target, clazz);
					if (adapter != null && clazz.isInstance(adapter)) {
						return clazz.cast(adapter);
					}
				}					   
			}
		}
				
		if ( checkWSAdapters && fAdapterManager != null ) {
			// otherwise, the object we are adapting is not an EObject, try any other adapters.		
			adapter = fAdapterManager.getAdapter(target, clazz);
			if (adapter != null && clazz.isInstance(adapter)) {
				return clazz.cast(adapter);
			}
		}
		
	    return null;
	}
	
	
	
	/**
	 * This method tries the registered adapter factories one by one, returning
	 * the first non-null result it gets.  If none of the factories can adapt
	 * the result, it returns null.
	 * @param target target object 
	 * @param type type of the adapter to find
	 * @return the adapter for the target.
	 */
	
	public Object adapt (Object target, Object type) {

		if (target == null) {
			return null;
		}		 
		return adapt ( target, adapterInterface(type) );
	}
	
	
	/**
	 * Create an adapter for the given target of the given type. 
	 * In addition, pass a context object to the adapter(s) of the target. 
	 * 
	 * The idea is that some adapters can be stateful and depend not only 
	 * on the objects that they wrap, but also on some other context that is needed
	 * to completely and correctly implement the interface for which the adaptor is
	 * needed.
	 * 
	 * Adapters that are stateless, should ignore any notifications sent to them.
	 *  
	 * @param target the target object
	 * @param type the type it wants to adapt to
	 * @param context the context object
	 * 
	 * @return the adapter
	 */
	public Object adapt (Object target, Object type, Object context) {
		
		Object adapter = adapt (target,type);
		if (adapter == null) {
			return adapter;
		}
		
		if (target instanceof EObject) {				
			EObject eObject = (EObject) target;		
			Notification n = new NotificationImpl(AbstractAdapter.CONTEXT_UPDATE_EVENT_TYPE, null, context);		
			eObject.eNotify(n);
		}
		
		return adapter;
	}

	public void registerAdapterFactory (IAdapterFactory factory) {
		registerAdapterFactory(factory, Object.class);
	}
	
	/**
	 * @param factory
	 * @param class1
	 */
	public void registerAdapterFactory(IAdapterFactory factory,	Class<?> clazz) {
		fAdapterManager.registerAdapters(factory, clazz);		
	}

	public static AdapterFactoryLabelProvider getLabelProvider() {
		if (LABEL_PROVIDER==null)
			LABEL_PROVIDER = new AdapterFactoryLabelProvider(BPMN2_ADAPTER_FACTORIES);
		return LABEL_PROVIDER;
	}
}
