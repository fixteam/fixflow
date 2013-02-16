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
package org.eclipse.bpmn2.modeler.core.adapters;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

/**
* Abstract adapter has basic adapter functionality and it has some
* logic to decide whether adapters are stateful or stateless. 
* 
* @author Michal Chmielewski (michal.chmielewski@oracle.com)
* @date Sep 15, 2006
*
*/

public class AbstractAdapter implements Adapter {
	
	/** The event type that we will consume that indicates a context update */
	static public final int CONTEXT_UPDATE_EVENT_TYPE = 101;
		
	/** Makes sense only when adapters are statefull */
	// Bugzilla 330519
	// need access to this in MarkerDelegateAdapter
	protected Object target = null;
	
	/** additional context that is needed by the adapter to wrap the object */
	private Object context = null;	
	

	/**
	 * Allow the adapter to store new context information. This
	 * only works if the adapter is stateful.
	 * 
	 * 
	 * @param notification the notification to process.
	 */
	public void notifyChanged (Notification notification) {
		
		if (notification.getEventType() == CONTEXT_UPDATE_EVENT_TYPE && isStatefull() ) {
			context = notification.getNewValue();
		}
	}

	
	/**
	 * Return the target. This will always be null in case the 
	 * adapter is stateless.
	 * 
	 * @return the target
	 */
	
	public Notifier getTarget() {
		return getTarget(target,Notifier.class);
	}
	
	/**
	 * Set the target element, only if the adapter is stateless.
	 * 
	 * @param newTarget the target to set
	 */
	
	public void setTarget(Notifier newTarget) {		
		if ( isStatefull() ) {
			target = newTarget;
		}		 		
	}

	
	/**
	 * 
	 * @param newTarget
	 */
	
	public void setTarget (Object newTarget) {
		if (isStatefull()) {
			target = newTarget;
		}
	}
	
	
	/**
	 * Answer true if we are an adapter for the type given
	 * @param type the object type to check. 
	 * @return true if so, false if not 
	 */
	
	public boolean isAdapterForType (Object type) {
		Class<?> clazz = null;
		// what is type ? (an interface)
		if (type instanceof Class) {
			clazz = (Class) type;
			return clazz.isInstance(this);
		}
		// what else could it be ?
		return false;
	}
	
	/**
	 * Return any context object that the adapter is holding.
	 * @return the context object
	 */
	
	public Object getContext () {
		return context;
	}
	
	/**
	 * Answer if we are a stateful adapter or not.
	 * 
	 * @return true if stateful, false if stateless.
	 */
	
	public boolean isStatefull () {
		return IStatefullAdapter.class.isInstance(this);
	}
	
	
	/**
	 * Some adapters rely on interfaces that are stateless, where the target
	 * is passed as an argument to the adapter. This is not necessarily true for
	 * some interfaces we cannot control (like IContentProposal).
	 *  
	 * @param <T> The type class
	 * @param obj the object which might be the adapted target
	 * @param clazz the class that the target must be an instance of. 
	 * @return the target object
	 */
	
	@SuppressWarnings("unchecked")	
	public <T extends Object> T getTarget ( Object obj , Class<T> clazz ) {

		if (obj != null) {
			if (clazz.isInstance(obj)) {
				return (T) obj;
			}
			if (target != null) {
				if (clazz.isInstance(target)) {
					return (T) target;
				}
			}
			// problem !
			throw new RuntimeException("Object is not of type " + clazz.getName()); //$NON-NLS-1$
		}
				
		/** Target is never set unless the object is statefull. */
		
		if (target != null) {			
			if (clazz.isInstance(target)) {
				return (T) target;
			}
			// problem !
			throw new RuntimeException("Target is not of type " + clazz.getName()); //$NON-NLS-1$
		}		

		return null;
			
	
		
	}

}
