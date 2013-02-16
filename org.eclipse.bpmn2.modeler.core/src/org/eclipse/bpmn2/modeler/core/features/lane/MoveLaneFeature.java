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

import org.eclipse.bpmn2.Lane;
import org.eclipse.bpmn2.modeler.core.features.DefaultMoveBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.FeatureSupport;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;

public class MoveLaneFeature extends DefaultMoveBPMNShapeFeature {

	private MoveLaneFeature moveStrategy;

	public MoveLaneFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		if (context.getSourceContainer() == null) {
			return false;
		}

		moveStrategy = getStrategy(context);

		if (moveStrategy == null) {
			return super.canMoveShape(context);
		}

		return moveStrategy.canMoveShape(context);
	}

	@Override
	protected void internalMove(IMoveShapeContext context) {
		super.internalMove(context);
		if (moveStrategy != null) {
			moveStrategy.internalMove(context);
		}
	}

	private MoveLaneFeature getStrategy(IMoveShapeContext context) {

		if (context.getSourceContainer().equals(getDiagram())) { // from diagram

			if (FeatureSupport.isTargetLane(context)) { // to lane
				return new MoveFromDiagramToLaneFeature(getFeatureProvider());
			} else if (FeatureSupport.isTargetParticipant(context)) { // to participant
				return new MoveFromDiagramToParticipantFeature(getFeatureProvider());
			}

		} else if (FeatureSupport.isLane(context.getSourceContainer())) { // from lane

			if (context.getTargetContainer().equals(getDiagram())) { // to diagram
				return new MoveFromLaneToDiagramFeature(getFeatureProvider());
			} else if (FeatureSupport.isTargetLane(context)) { // to another lane
				return new MoveFromLaneToLaneFeature(getFeatureProvider());
			} else if (FeatureSupport.isTargetParticipant(context)) { // to participant
				return new MoveFromLaneToParticipantFeature(getFeatureProvider());
			}

		} else if (FeatureSupport.isParticipant(context.getSourceContainer())) { // from participant

			if (context.getTargetContainer().equals(getDiagram())) { // to diagram
				return new MoveFromParticipantToDiagramFeature(getFeatureProvider());
			} else if (FeatureSupport.isTargetLane(context)) { // to another lane
				return new MoveFromParticipantToLaneFeature(getFeatureProvider());
			} else if (FeatureSupport.isTargetParticipant(context)) { // to another participant
				return new MoveFromParticipantToParticipantFeature(getFeatureProvider());
			}
		}

		return null;
	}

	protected Lane getMovedLane(IMoveShapeContext context) {
		return (Lane) getBusinessObjectForPictogramElement(context.getShape());
	}
}