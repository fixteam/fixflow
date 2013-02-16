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
package org.eclipse.bpmn2.modeler.ui.features.participant;

import java.util.List;

import org.eclipse.bpmn2.di.BPMNShape;
import org.eclipse.bpmn2.di.ParticipantBandKind;
import org.eclipse.bpmn2.modeler.core.features.DefaultMoveBPMNShapeFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.core.utils.Tuple;
import org.eclipse.bpmn2.modeler.ui.features.choreography.ChoreographyUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class MoveParticipantFeature extends DefaultMoveBPMNShapeFeature {

	public MoveParticipantFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
//		if (ChoreographyUtil.isChoreographyParticipantBand(context.getShape())) {
//			return false;
//		}
		return super.canMoveShape(context);
	}

	@Override
	protected void postMoveShape(IMoveShapeContext context) {
		super.postMoveShape(context);
		
		if (ChoreographyUtil.isChoreographyParticipantBand(context.getShape())) {
			ContainerShape container = context.getTargetContainer();
			ContainerShape shape = (ContainerShape)context.getShape();
			// collect all participant bands into top, middle and bottom
			List<ContainerShape> bands = ChoreographyUtil.getParticipantBandContainerShapes(container);
			Tuple<List<ContainerShape>, List<ContainerShape>> topAndBottom = ChoreographyUtil.getTopAndBottomBands(bands);
			List<ContainerShape> top = topAndBottom.getFirst();
			List<ContainerShape> bottom = topAndBottom.getSecond();
			BPMNShape bpmnShape = BusinessObjectUtil.getFirstElementOfType(shape, BPMNShape.class);
			int height = container.getGraphicsAlgorithm().getHeight();
			int width = container.getGraphicsAlgorithm().getWidth();
			
			for (ContainerShape cs : bottom) {
				if ( cs == shape) {
					bottom.remove(cs);
					break;
				}
			}
			for (ContainerShape cs : top) {
				if ( cs == shape) {
					top.remove(cs);
					break;
				}
			}
			if (context.getY()<height/2) {
				// move up
				top.add(shape);
			}
			else {
				// move down
				bottom.add(0,shape);
			}
			
			// reassign TOP/MIDDLE/BOTTOM bandKinds
			for (int i=0; i<bottom.size(); ++i) {
				bpmnShape = BusinessObjectUtil.getFirstElementOfType(bottom.get(i), BPMNShape.class);
				ParticipantBandKind bandKind = bpmnShape.getParticipantBandKind();
				if (i==bottom.size()-1) {
					if (isInitiating(bandKind))
						bandKind = ParticipantBandKind.BOTTOM_INITIATING;
					else
						bandKind = ParticipantBandKind.BOTTOM_NON_INITIATING;
				}
				else
				{
					if (isInitiating(bandKind))
						bandKind = ParticipantBandKind.MIDDLE_INITIATING;
					else
						bandKind = ParticipantBandKind.MIDDLE_NON_INITIATING;
				}
				bpmnShape.setParticipantBandKind(bandKind);
			}
			for (int i=0; i<top.size(); ++i) {
				bpmnShape = BusinessObjectUtil.getFirstElementOfType(top.get(i), BPMNShape.class);
				ParticipantBandKind bandKind = bpmnShape.getParticipantBandKind();
				if (i==0) {
					if (isInitiating(bandKind))
						bandKind = ParticipantBandKind.TOP_INITIATING;
					else
						bandKind = ParticipantBandKind.TOP_NON_INITIATING;
				}
				else
				{
					if (isInitiating(bandKind))
						bandKind = ParticipantBandKind.MIDDLE_INITIATING;
					else
						bandKind = ParticipantBandKind.MIDDLE_NON_INITIATING;
				}
				bpmnShape.setParticipantBandKind(bandKind);
			}
			
			ChoreographyUtil.resizePartipantBandContainerShapes(width, height,
					top, bottom, getDiagram());
		}
	}

	boolean isInitiating(ParticipantBandKind bandKind) {
		return bandKind==ParticipantBandKind.TOP_INITIATING ||
			bandKind==ParticipantBandKind.BOTTOM_INITIATING ||
			bandKind==ParticipantBandKind.MIDDLE_INITIATING;
	}

}