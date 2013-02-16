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

import static org.eclipse.bpmn2.modeler.core.features.choreography.ChoreographyProperties.MESSAGE_VISIBLE;

import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.services.Graphiti;

public class UpdateChoreographyMessageLinkFeature extends AbstractUpdateFeature {

	public UpdateChoreographyMessageLinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return ChoreographyUtil.isChoreographyParticipantBand(context.getPictogramElement());
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		if (!ChoreographyUtil.isChoreographyParticipantBand(context.getPictogramElement())) {
			return Reason.createFalseReason();
		}

		BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), BPMNShape.class);
		boolean visible = new Boolean(Graphiti.getPeService().getPropertyValue(context.getPictogramElement(),
				MESSAGE_VISIBLE));

		return bpmnShape.isIsMessageVisible() != visible ? Reason.createTrueReason() : Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {

		ChoreographyUtil.drawMessageLinks(getFeatureProvider(), (ContainerShape) context.getPictogramElement().eContainer());

		BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(context.getPictogramElement(), BPMNShape.class);
		Graphiti.getPeService().setPropertyValue(context.getPictogramElement(), MESSAGE_VISIBLE,
				Boolean.toString(bpmnShape.isIsMessageVisible()));

		return true;
	}

}