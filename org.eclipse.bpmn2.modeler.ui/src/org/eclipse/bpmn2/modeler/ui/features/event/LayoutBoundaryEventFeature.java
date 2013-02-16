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

import org.eclipse.bpmn2.BoundaryEvent;
import org.eclipse.bpmn2.modeler.core.di.DIUtils;
import org.eclipse.bpmn2.modeler.core.utils.BusinessObjectUtil;
import org.eclipse.bpmn2.modeler.ui.features.event.PositionOnLine.LocationType;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class LayoutBoundaryEventFeature extends AbstractLayoutFeature {

	public LayoutBoundaryEventFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		return true;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean layout = false;

		PictogramElement element = context.getPictogramElement();
		GraphicsAlgorithm eventGa = element.getGraphicsAlgorithm();
		BoundaryEvent event = BusinessObjectUtil.getFirstElementOfType(element, BoundaryEvent.class);

		PictogramElement activityContainer = BusinessObjectUtil.getFirstBaseElementFromDiagram(getDiagram(),
		        event.getAttachedToRef());
		GraphicsAlgorithm activityGa = activityContainer.getGraphicsAlgorithm();

		PositionOnLine pos = BoundaryEventPositionHelper.getPositionOnLineProperty(element);

		switch (pos.getLineType()) {
		case X:
			moveX(eventGa, activityGa, pos.getLocationType());
			layout = true;
			break;
		case Y:
			moveY(eventGa, activityGa, pos.getLocationType());
			layout = true;
			break;
		case XY:
			moveX(eventGa, activityGa, pos.getLocationType());
			moveY(eventGa, activityGa, pos.getLocationType());
			layout = true;
			break;
		default:
			layout = false;
			break;
		}

		DIUtils.updateDIShape(element);
		if (layout) {
			PositionOnLine newPos = BoundaryEventPositionHelper.getPositionOnLineUsingAbsoluteCoordinates(
			        (Shape) element, (Shape) activityContainer);
			BoundaryEventPositionHelper.assignPositionOnLineProperty(element, newPos);
		}
		return layout;
	}

	private void moveX(GraphicsAlgorithm ga, GraphicsAlgorithm parentGa, LocationType locType) {
		IGaService gaService = Graphiti.getGaService();
		if (isLeft(locType)) {
			gaService.setLocation(ga, parentGa.getX() - (ga.getWidth() / 2), ga.getY());
		} else {
			gaService.setLocation(ga, parentGa.getX() + parentGa.getWidth() - (ga.getWidth() / 2), ga.getY());
		}
	}

	private boolean isLeft(LocationType locType) {
		return locType == LocationType.TOP_LEFT || locType == LocationType.LEFT || locType == LocationType.BOTTOM_LEFT;
	}

	private void moveY(GraphicsAlgorithm ga, GraphicsAlgorithm parentGa, LocationType locType) {
		IGaService gaService = Graphiti.getGaService();
		if (isTop(locType)) {
			gaService.setLocation(ga, ga.getX(), parentGa.getY() - (ga.getHeight() / 2));
		} else {
			gaService.setLocation(ga, ga.getX(), parentGa.getY() + parentGa.getHeight() - (ga.getHeight() / 2));
		}
	}

	private boolean isTop(LocationType locType) {
		return locType == LocationType.TOP_LEFT || locType == LocationType.TOP || locType == LocationType.TOP_RIGHT;
	}
}