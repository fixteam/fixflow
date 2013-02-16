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

import static org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties.PARTICIPANT_REF_IDS;

import java.util.List;

import org.eclipse.bpmn2.ChoreographyActivity;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;

public class UpdateChoreographyParticipantRefsFeature extends AbstractUpdateFeature {

	private final IPeService peService = Graphiti.getPeService();

	public UpdateChoreographyParticipantRefsFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return BusinessObjectUtil.containsElementOfType(context.getPictogramElement(), ChoreographyActivity.class);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		ChoreographyActivity choreography = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
				ChoreographyActivity.class);

		if (!ChoreographyUtil.getParticipantRefIds(choreography).equals(getParticipantRefIds(context))) {
			return Reason.createTrueReason();
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		ChoreographyActivity choreography = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(),
				ChoreographyActivity.class);
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();
		List<Participant> participants = choreography.getParticipantRefs();
		List<ContainerShape> bandContainerShapes = ChoreographyUtil
				.getParticipantBandContainerShapes((ContainerShape) context.getPictogramElement());

		ChoreographyUtil.updateParticipantReferences(containerShape, bandContainerShapes, participants,
				getFeatureProvider(), isShowNames());

		peService.setPropertyValue(context.getPictogramElement(), PARTICIPANT_REF_IDS,
				ChoreographyUtil.getParticipantRefIds(choreography));

		ChoreographyUtil.drawMessageLinks(getFeatureProvider(), (ContainerShape) context.getPictogramElement());
		return true;
	}

	private String getParticipantRefIds(IUpdateContext context) {
		String property = peService.getPropertyValue(context.getPictogramElement(), PARTICIPANT_REF_IDS);
		if (property == null) {
			return new String(); // return empty string
		}
		return property;
	}

	protected boolean isShowNames() {
		return true;
	}
}