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
package org.eclipse.bpmn2.modeler.core.features.lane;

import java.io.IOException;

import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.LaneSet;
import org.eclipse.bpmn2.Participant;
import org.eclipse.bpmn2.Process;
import org.eclipse.bpmn2.impl.Bpmn2FactoryImpl;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;

public class MoveFromParticipantToParticipantFeature extends MoveLaneFeature {

	public MoveFromParticipantToParticipantFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		Participant p1 = (Participant) getBusinessObjectForPictogramElement(context.getSourceContainer());
		Participant p2 = (Participant) getBusinessObjectForPictogramElement(context.getTargetContainer());

		if (p1.equals(p2)) {
			return false;
		}

		if (getMovedLane(context).getFlowNodeRefs().isEmpty()) {
			return true;
		}

		if (p2.getProcessRef() == null) {
			return true;
		}

		if (!p2.getProcessRef().getLaneSets().isEmpty()) {
			return true;
		}

		return false;
	}

	@Override
	protected void internalMove(IMoveShapeContext context) {
		modifyModelStructure(context);
		FeatureSupport.redraw(context.getSourceContainer());
		FeatureSupport.redraw(context.getTargetContainer());
	}

	private void modifyModelStructure(IMoveShapeContext context) {
		Lane movedLane = getMovedLane(context);
		Participant targetParticipant = (Participant) getBusinessObjectForPictogramElement(context.getTargetContainer());

		try {
			ModelHandler handler = ModelHandler.getInstance(getDiagram());
			handler.moveLane(movedLane, targetParticipant);
		} catch (IOException e) {
			Activator.logError(e);
		}

		Participant sourceParticipant = (Participant) getBusinessObjectForPictogramElement(context.getSourceContainer());

		LaneSet laneSet = null;
		for (LaneSet set : sourceParticipant.getProcessRef().getLaneSets()) {
			if (set.getLanes().contains(movedLane)) {
				laneSet = set;
				break;
			}
		}

		if (laneSet != null) {
			laneSet.getLanes().remove(movedLane);
			if (laneSet.getLanes().isEmpty()) {
				sourceParticipant.getProcessRef().getLaneSets().remove(laneSet);
			}

			Process process = targetParticipant.getProcessRef();
			if (process.getLaneSets().isEmpty()) {
				LaneSet createLaneSet = Bpmn2FactoryImpl.eINSTANCE.createLaneSet();
//				createLaneSet.setId(EcoreUtil.generateUUID());
				process.getLaneSets().add(createLaneSet);
				ModelUtil.setID(createLaneSet);
			}
			process.getLaneSets().get(0).getLanes().add(movedLane);
		}
	}
}