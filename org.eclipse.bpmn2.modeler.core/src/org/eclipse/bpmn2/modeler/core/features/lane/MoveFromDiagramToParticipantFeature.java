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

public class MoveFromDiagramToParticipantFeature extends MoveLaneFeature {

	public MoveFromDiagramToParticipantFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		Participant p = (Participant) getBusinessObjectForPictogramElement(context.getTargetContainer());

		if (getMovedLane(context).getFlowNodeRefs().isEmpty()) {
			return true;
		}

		if (p.getProcessRef() == null) {
			return true;
		}

		if (!p.getProcessRef().getLaneSets().isEmpty()) {
			return true;
		}

		return false;
	}

	@Override
	protected void internalMove(IMoveShapeContext context) {
		modifyModelStructure(context);
		FeatureSupport.redraw(context.getTargetContainer());
	}

	private void modifyModelStructure(IMoveShapeContext context) {
		try {
			Participant targetParticipant = (Participant) getBusinessObjectForPictogramElement(context
					.getTargetContainer());
			ModelHandler handler = ModelHandler.getInstance(getDiagram());
			Lane movedLane = getMovedLane(context);
			handler.moveLane(movedLane, targetParticipant);
			Participant internalParticipant = handler.getParticipant(getDiagram());
			LaneSet laneSet = null;
			for (LaneSet set : internalParticipant.getProcessRef().getLaneSets()) {
				if (set.getLanes().contains(movedLane)) {
					laneSet = set;
					break;
				}
			}
			if (laneSet != null) {
				laneSet.getLanes().remove(movedLane);
				if (laneSet.getLanes().isEmpty()) {
					internalParticipant.getProcessRef().getLaneSets().remove(laneSet);
				}

				Process process = targetParticipant.getProcessRef();
				if (process.getLaneSets().isEmpty()) {
					LaneSet claneSet = Bpmn2FactoryImpl.eINSTANCE.createLaneSet();
//					claneSet.setId(EcoreUtil.generateUUID());
					process.getLaneSets().add(claneSet);
					ModelUtil.setID(claneSet);
				}
				process.getLaneSets().get(0).getLanes().add(movedLane);
			}
		} catch (IOException e) {
			Activator.logError(e);
		}
	}
}
