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
 * @author Innar Made
 ******************************************************************************/
package org.eclipse.bpmn2.modeler.ui.features.choreography;

import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class UpdateChoreographyMarkerFeature extends AbstractUpdateFeature {

	private final IPeService peService = Graphiti.getPeService();

	public UpdateChoreographyMarkerFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return BusinessObjectUtil.containsElementOfType(context.getPictogramElement(), ChoreographyActivity.class);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		ContainerShape choreographyContainer = (ContainerShape) context.getPictogramElement();
		ChoreographyActivity choreography = BusinessObjectUtil.getFirstElementOfType(choreographyContainer,
				ChoreographyActivity.class);

		String loopType = choreography.getLoopType() == null ? "null" : choreography.getLoopType().getName();
		String property = peService.getPropertyValue(choreographyContainer, ChoreographyProperties.CHOREOGRAPHY_MARKER);

		if (!loopType.equals(property)) {
			return Reason.createTrueReason();
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		ContainerShape choreographyContainer = (ContainerShape) context.getPictogramElement();
		ChoreographyActivity choreography = BusinessObjectUtil.getFirstElementOfType(choreographyContainer,
				ChoreographyActivity.class);

		for (Shape s : peService.getAllContainedShapes(choreographyContainer)) {
			String property = peService.getPropertyValue(s, ChoreographyProperties.CHOREOGRAPHY_MARKER_SHAPE);
			if (property != null && new Boolean(property)) {
				ChoreographyUtil.drawChoreographyLoopType((ContainerShape) s, choreography.getLoopType());
			}
		}

		String loopType = choreography.getLoopType() == null ? "null" : choreography.getLoopType().getName();
		peService.setPropertyValue(choreographyContainer, ChoreographyProperties.CHOREOGRAPHY_MARKER, loopType);

		return true;
	}

}