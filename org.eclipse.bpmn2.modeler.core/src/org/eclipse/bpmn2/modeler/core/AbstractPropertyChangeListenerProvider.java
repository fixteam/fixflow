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
 * @author Ivar Meikas
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public abstract class AbstractPropertyChangeListenerProvider {

	ArrayList<PropertyChangeListener> listeners = new ArrayList<PropertyChangeListener>();

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		listeners.remove(pcl);
	}

	public void removePropertyChangeListener(String s, PropertyChangeListener pcl) {
		listeners.remove(pcl);
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		listeners.add(pcl);
	}

	public void addPropertyChangeListener(String s, PropertyChangeListener pcl) {
		listeners.add(pcl);
	}

	protected void fireChangeEvent(PropertyChangeEvent propertyChangeEvent) {
		for (PropertyChangeListener l : listeners) {
			l.propertyChange(propertyChangeEvent);
		}
	}

}