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
 * If an adapter implements this interface, it is assumed to be statefull.
 * The AdapterProvider helper class will always return new instances of
 * such adapter classes.
 * 
 * @author Michal Chmielewski (michal.chmielewski@oracle.com)
 * @date Jul 28, 2006
 *
 */

public interface IStatefullAdapter {
	
	/**
	 * Set the target object.
	 * 
	 * @param target the target object
	 */
	public void setTarget (Object target);

}
