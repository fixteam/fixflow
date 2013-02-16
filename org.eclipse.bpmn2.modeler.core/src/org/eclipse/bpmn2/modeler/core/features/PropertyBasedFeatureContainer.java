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
package org.eclipse.bpmn2.modeler.core.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.PropertyContainer;
import org.eclipse.graphiti.services.Graphiti;

public abstract class PropertyBasedFeatureContainer implements FeatureContainer {

	@Override
	public Object getApplyObject(IContext context) {
		if (context instanceof IPictogramElementContext) {
			return ((IPictogramElementContext) context).getPictogramElement();
		}
		return null;
	}

	@Override
	public boolean canApplyTo(Object o) {
		if (!(o instanceof PropertyContainer)) {
			return false;
		}
		String property = Graphiti.getPeService().getPropertyValue((PropertyContainer) o, getPropertyKey());
		if (property == null) {
			return false;
		}
		return canApplyToProperty(property);
	}

	protected abstract String getPropertyKey();

	protected abstract boolean canApplyToProperty(String value);

	@Override
	public IRemoveFeature getRemoveFeature(IFeatureProvider fp) {
		return null;
	}
	
	@Override
	public ICustomFeature[] getCustomFeatures(IFeatureProvider fp) {
		return null;
	}
}