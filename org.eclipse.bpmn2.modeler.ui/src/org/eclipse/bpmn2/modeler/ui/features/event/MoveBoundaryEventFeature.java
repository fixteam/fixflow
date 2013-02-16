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
package org.eclipse.bpmn2.modeler.ui.features.event;

import org.eclipse.bpmn2.Activity;
import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.modeler.core.features.MoveFlowNodeFeature;
import org.eclipse.bpmn2.modeler.core.features.activity.MoveActivityFeature;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.impl.MoveShapeContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

public class MoveBoundaryEventFeature extends MoveFlowNodeFeature {

	public MoveBoundaryEventFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected void preMoveShape(IMoveShapeContext context) {
		ContainerShape targetContainer = context.getTargetContainer();
		Activity activity = BusinessObjectUtil.getFirstElementOfType(targetContainer, Activity.class);
		Object property = context.getProperty(MoveActivityFeature.ACTIVITY_MOVE_PROPERTY);
		if (activity != null && property == null) {
			ContainerShape taskContainer = context.getTargetContainer();
			ContainerShape parentContainer = (ContainerShape) context.getPictogramElement().eContainer();

			IPeService peService = Graphiti.getPeService();

			ILocation loc = peService.getLocationRelativeToDiagram(taskContainer);
			MoveShapeContext c = (MoveShapeContext) context;
			int eventX = loc.getX() + context.getX();
			int eventY = loc.getY() + context.getY();

			if (!(parentContainer instanceof Diagram)) {
				loc = peService.getLocationRelativeToDiagram(parentContainer);
				eventX = eventX - loc.getX();
				eventY = eventY - loc.getY();
			}

			c.setLocation(eventX, eventY);
			c.setTargetContainer(parentContainer);
		}
	}

	@Override
	public boolean canMoveShape(IMoveShapeContext context) {
		Object property = context.getProperty("activity.move");
		boolean movedByActivity = property != null && (Boolean) property;
		if (!movedByActivity && !BoundaryEventPositionHelper.canMoveTo(context, getDiagram())) {
			return false;
		}
		return super.canMoveShape(context);
	}

	@Override
	protected boolean onMoveAlgorithmNotFound(IMoveShapeContext context) {
		return true;
	}

	@Override
	protected void postMoveShape(IMoveShapeContext context) {
		ContainerShape containerShape = (ContainerShape) context.getPictogramElement();

		Object property = context.getProperty("activity.move");
		if (property != null && (Boolean) property) {
			IGaService gaService = Graphiti.getGaService();
			GraphicsAlgorithm ga = containerShape.getGraphicsAlgorithm();
			gaService.setLocation(ga, ga.getX() + context.getDeltaX(), ga.getY() + context.getDeltaY());
		} else {
			BoundaryEvent event = BusinessObjectUtil.getFirstElementOfType(containerShape, BoundaryEvent.class);
			PictogramElement activityContainer = BusinessObjectUtil.getFirstBaseElementFromDiagram(getDiagram(),
					event.getAttachedToRef());
			PositionOnLine newPos = BoundaryEventPositionHelper.getPositionOnLineUsingAbsoluteCoordinates(
					containerShape, (Shape) activityContainer);
			BoundaryEventPositionHelper.assignPositionOnLineProperty(containerShape, newPos);
		}
		Graphiti.getPeService().sendToFront(containerShape);

		super.postMoveShape(context);
	}
}