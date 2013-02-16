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


/**
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @param <K> 
 * @param <V> 
 * @date Jul 23, 2007
 *
 */
public interface IProperty<K,V> {

	/**
	 * Get the property.
	 *  
	 * @param key the 
	 * @return the property under the key key.
	 */
	public V getProperty ( K key );
	
	/**
	 * Set the property.
	 * @param key  the key
	 * @param value the value
	 * @return the old value of the property, if set, null otherwise.
	 */
	
	public V setProperty (K key, V value);		
	
}
