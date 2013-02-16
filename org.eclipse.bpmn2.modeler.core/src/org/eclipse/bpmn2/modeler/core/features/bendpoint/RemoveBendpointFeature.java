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
package org.eclipse.bpmn2.modeler.core.features.bendpoint;

import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.di.BPMNEdge;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.ModelHandlerLocator;
import org.eclipse.bpmn2.modeler.core.utils.AnchorUtil;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveBendpointFeature;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class RemoveBendpointFeature extends DefaultRemoveBendpointFeature {

	public RemoveBendpointFeature(IFeatureProvider fp) {
	    super(fp);
    }
	
	@Override
	public boolean canRemoveBendpoint(IRemoveBendpointContext context) {
		Shape connectionPointShape = AnchorUtil.getConnectionPointAt(context.getConnection(), context.getBendpoint());
		if (connectionPointShape!=null)
			return false;
		
		return super.canRemoveBendpoint(context);
	}

	@Override
	public void removeBendpoint(IRemoveBendpointContext context) {
	    super.removeBendpoint(context);
	    try {
			FreeFormConnection connection = context.getConnection();
			BaseElement element = (BaseElement) BusinessObjectUtil.getFirstElementOfType(connection, BaseElement.class);
			ModelHandler modelHandler = ModelHandlerLocator.getModelHandler(getDiagram().eResource());
			BPMNEdge edge = (BPMNEdge) modelHandler.findDIElement(element);
			edge.getWaypoint().remove(context.getBendpointIndex() + 1);
		} catch (Exception e) {
			Activator.logError(e);
		}
	}
}