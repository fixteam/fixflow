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

import java.util.HashMap;

import org.eclipse.emf.common.notify.Adapter;

/**
 * The primary motivation for this class is to delegate the decision
 * of whether an adapter is  stateless or statefull. 
 * 
 * In the case of a stateless adapter, the singleton instance is
 * always returned.
 * 
 * The factory classes simply call
 * <pre> 
 *   adapter.getAdatper ( Class )
 * </pre>
 * 
 * An adapter is considered stateful if it implements IStatefullAdapter
 *
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 21, 2006
 *
 */

public final class AdapterProvider {

	/**
	 * The list of adapter singletons that we have created.
	 */
	final HashMap<Class<? extends Adapter>,Adapter> map = new HashMap<Class<? extends Adapter>,Adapter>();
	
	/**
	 * The package list that we search for adapters.
	 */
	String [] packageList = {};
	
	/**
	 * Return a brand new shiny adapter provider.
	 *
	 */
	public AdapterProvider () {
		
	}
	
	/**
	 * Return a brand new shiny adapter provider which searches for
	 * adapters in the packages specified.
	 * @param args
	 */
	
	public AdapterProvider (String ... args) {
		packageList = args;
	}
	
	/**
	 * Return an adapter for the given class
	 * 
	 * @param name the name of the adapter (class name)
	 * @return the Adapter, or null, if the adapter cannot be found.
	 */
	
	
	@SuppressWarnings("nls")
	public Adapter getAdapter ( String name ) {
				
		int absNameIdx = name.indexOf('.');				
		if (absNameIdx > 0) {
			// absolute specification
			return getAdapter ( classFor(name) );
		}
						
		for(String packageName : packageList) {
			Class<? extends Adapter> adapterClazz = classFor( packageName + "." + name );
			if (adapterClazz != null) {
				return getAdapter(adapterClazz);
			}
		}
		
		return null;
	}
	

	
	/**
	 * Get an adapter of the class passed. 
	 * 
	 *  
	 * @param adapterClass
	 * @return the appropriate adapter
	 */
	
	public Adapter getAdapter ( Class<? extends Adapter> adapterClass ) {
		
		Adapter instance = map.get(adapterClass);
		if (instance == null) {
			instance = newAdapter( adapterClass );			  
			map.put(adapterClass,instance);
		}
		
		if (instance instanceof IStatefullAdapter) {
			return newAdapter(adapterClass);
		}
		
		return instance;
	}
	
	
	/**
	 * Get an adapter of the class passed. 
	 * 
	 *  
	 * @param adapterClass
	 * @param target the target object
	 * @return the appropriate adapter
	 */
	
	public Adapter getAdapter ( Class<? extends Adapter> adapterClass , Object target ) {
		
		Adapter adapter = getAdapter(adapterClass);
		
		if (adapter == null) {
			return null;
		}
		
		if (adapter instanceof IStatefullAdapter) {
			IStatefullAdapter statefullAdapter = (IStatefullAdapter) adapter;
			statefullAdapter.setTarget(target);
		}
		
		return adapter;
	}
	

	@SuppressWarnings("unchecked")
	Class<? extends Adapter> classFor ( String name ) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(name);
			return (Class<? extends Adapter>) clazz;
		} catch (ClassNotFoundException e) {
			return null;
		} catch (ClassCastException cce) {
			return null;
		}
	}
	
	
	Adapter newAdapter ( Class<? extends Adapter> clazz ) {
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException( ex );
		}
	}


	
}
