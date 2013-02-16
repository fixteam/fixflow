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
import org.eclipse.bpmn2.impl.Bpmn2FactoryImpl;
import org.eclipse.bpmn2.modeler.core.Activator;
import org.eclipse.bpmn2.modeler.core.ModelHandler;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class MoveFromParticipantToLaneFeature extends MoveLaneFeature {

	public MoveFromParticipantToLaneFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		Lane movedLane = getMovedLane(context);
		boolean moveableHasFlowNodes = movedLane.getFlowNodeRefs().size() > 0;

		Lane targetLane = getTargetLane(context);
		boolean targetHasFlowNodeRefs = targetLane.getFlowNodeRefs().size() > 0;

		if (!moveableHasFlowNodes && !targetHasFlowNodeRefs) {
			return true;
		}

		return moveableHasFlowNodes ^ targetHasFlowNodeRefs;
	}

	@Override
	protected void internalMove(IMoveShapeContext context) {
		modifyModelStructure(context);
		FeatureSupport.redraw(context.getSourceContainer());
		FeatureSupport.redraw(context.getTargetContainer());
	}

	private Lane getTargetLane(IMoveShapeContext context) {
		ContainerShape targetContainer = context.getTargetContainer();
		return (Lane) getBusinessObjectForPictogramElement(targetContainer);
	}

	private void modifyModelStructure(IMoveShapeContext context) {
		Lane movedLane = getMovedLane(context);
		Lane toLane = getTargetLane(context);

		try {
			ModelHandler handler = ModelHandler.getInstance(getDiagram());
			Participant participant = handler.getParticipant(toLane);
			handler.moveLane(movedLane, participant);
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
		}

		if (toLane.getChildLaneSet() == null) {
			LaneSet createLaneSet = Bpmn2FactoryImpl.eINSTANCE.createLaneSet();
//			createLaneSet.setId(EcoreUtil.generateUUID());
			toLane.setChildLaneSet(createLaneSet);
			ModelUtil.setID(createLaneSet);
		}
		toLane.getChildLaneSet().getLanes().add(movedLane);
	}
}