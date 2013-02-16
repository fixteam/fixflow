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
 * @author Bob Brodt
 ******************************************************************************/

package org.eclipse.bpmn2.modeler.ui.features.choreography;

import org.eclipse.bpmn2.ChoreographyTask;
import org.eclipse.bpmn2.Participant;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * @author Bob Brodt
 *
 */
public class RemoveChoreographyParticipantFeature extends DefaultRemoveFeature {

	/**
	 * @param fp
	 */
	public RemoveChoreographyParticipantFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		// participant bands in a ChoreographyTask CAN be "removed" (from the modelObject's
		// participantRef list) but not "deleted" (from the model)
		if (ChoreographyUtil.isChoreographyParticipantBand(context.getPictogramElement())) {
			return true;
		}
		return false;
	}

	@Override
	public void execute(IContext context) {
		IRemoveContext dc = (IRemoveContext)context;
		if (ChoreographyUtil.isChoreographyParticipantBand(dc.getPictogramElement())) {
			PictogramElement pe = dc.getPictogramElement();
			Participant participant = (Participant)getBusinessObjectForPictogramElement(pe);
			ContainerShape container = (ContainerShape)pe.eContainer();
			Object bo = getBusinessObjectForPictogramElement(container); 
			if (bo instanceof ChoreographyTask) {
				ChoreographyTask task = (ChoreographyTask)bo;
				task.getParticipantRefs().remove(participant);
			}
		}
		super.execute(context);
	}

}
